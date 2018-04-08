package light.mvc.service.demo.impl;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.base.TColumnsReport;
import light.mvc.model.base.TPsPiPort;
import light.mvc.model.base.TPsPollutionSourceInfo;
import light.mvc.model.base.TScPollutantFactor;
import light.mvc.model.base.TScPollutantType;
import light.mvc.model.data.Data;
import light.mvc.model.data.DataDay;
import light.mvc.model.data.DataDayMain;
import light.mvc.model.data.DataDayMainLatest;
import light.mvc.model.data.DataHour;
import light.mvc.model.data.DataHourMain;
import light.mvc.model.data.DataHourMainLatest;
import light.mvc.model.data.DataMain;
import light.mvc.model.data.DataMonth;
import light.mvc.model.data.DataMonthMain;
import light.mvc.model.data.DataMonthMainLatest;
import light.mvc.model.data.DataYear;
import light.mvc.model.data.DataYearMain;
import light.mvc.model.data.DataYearMainLatest;
import light.mvc.pageModel.base.ColumnsReport;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.data.StatisticsData;
import light.mvc.service.demo.ReportDataServiceI;
import light.mvc.utils.echarts.Legend;
import light.mvc.utils.echarts.Option;
import light.mvc.utils.echarts.Toolbox;
import light.mvc.utils.echarts.Tooltip;
import light.mvc.utils.echarts.axis.Axis;
import light.mvc.utils.echarts.axis.CategoryAxis;
import light.mvc.utils.echarts.axis.ValueAxis;
import light.mvc.utils.echarts.code.Orient;
import light.mvc.utils.echarts.code.PointerType;
import light.mvc.utils.echarts.code.Trigger;
import light.mvc.utils.echarts.code.X;
import light.mvc.utils.echarts.data.PieData;
import light.mvc.utils.echarts.feature.DataView;
import light.mvc.utils.echarts.feature.MagicType;
import light.mvc.utils.echarts.feature.Mark;
import light.mvc.utils.echarts.feature.Restore;
import light.mvc.utils.echarts.feature.SaveAsImage;
import light.mvc.utils.echarts.series.Bar;
import light.mvc.utils.echarts.series.Line;
import light.mvc.utils.echarts.series.MarkLine;
import light.mvc.utils.echarts.series.Pie;
import light.mvc.utils.echarts.style.ItemStyle;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportDataServiceImpl implements ReportDataServiceI {

	@Autowired
	private BaseDaoI<DataHour> dataHourDao;
	@Autowired
	private BaseDaoI<DataDay> dataDayDao;
	@Autowired
	private BaseDaoI<DataMonth> dataMonthDao;
	@Autowired
	private BaseDaoI<DataYear> dataYearDao;
	@Autowired
	private BaseDaoI<DataHourMain> dataHourMainDao;
	@Autowired
	private BaseDaoI<DataDayMain> dataDayMainDao;
	@Autowired
	private BaseDaoI<DataMonthMain> dataMonthMainDao;
	@Autowired
	private BaseDaoI<DataYearMain> dataYearMainDao;
	@Autowired
	private BaseDaoI<DataHourMainLatest> dataHourMainLatestDao;
	@Autowired
	private BaseDaoI<DataDayMainLatest> dataDayMainLatestDao;
	@Autowired
	private BaseDaoI<DataMonthMainLatest> dataMonthMainLatestDao;
	@Autowired
	private BaseDaoI<DataYearMainLatest> dataYearMainLatestDao;
	@Autowired
	private BaseDaoI<TPsPiPort> portDao;
	@Autowired
	private BaseDaoI<TPsPollutionSourceInfo> psDao;
	@Autowired
	private BaseDaoI<TColumnsReport> columnsDao;
	@Autowired
	private BaseDaoI<TScPollutantFactor> factorDao;
	@Autowired
	private BaseDaoI<TScPollutantType> pollutantTypeDao;
	
	public String getPollutantCode(String pollutantType){
		String hql = " from TScPollutantType t where t.name = '"+pollutantType+"'";
		List<TScPollutantType> l = pollutantTypeDao.find(hql);
		if(l!=null)
			return l.get(0).getCode();
		return null;
	}
	public String getFactorCode(String factorType){
		String hql = " from TScPollutantFactor t where t.name = '"+factorType+"'";
		List<TScPollutantFactor> l = factorDao.find(hql);
		if(l!=null && l.size()!=0)
			return l.get(0).getCode();
		return null;
	}
	public String getFactorName(String code){
		String hql = " from TScPollutantFactor t where t.code = '"+code+"'";
		List<TScPollutantFactor> l = factorDao.find(hql);
		if(l!=null && l.size()!=0)
			return l.get(0).getName();
		return null;
	}
	public List<TPsPiPort> getPort(String code){
		return portDao.find("select distinct t from TPsPiPort t where t.pollutantTypeCode = '"+code+"'");
	}
	public Map<String, String> getPsMap(){
		String hql2 = " from TPsPollutionSourceInfo t";
		List<TPsPollutionSourceInfo> psl = psDao.find(hql2);//获取企业信息
		Map<String, String> m = new HashMap<String, String>();//企业code-名称map
		for(int i =0;i<psl.size();i++){
			m.put(psl.get(i).getCode(), psl.get(i).getPsName());
		}
		return m;
	}
	public List getDataMainLatest(String timeType){
		List l = null;
		if(timeType.equals("year")){l = dataYearMainLatestDao.find("select distinct t from DataYearMainLatest t");}
		if(timeType.equals("month")){l = dataMonthMainLatestDao.find("select distinct t from DataMonthMainLatest t");}
		if(timeType.equals("day")){l = dataDayMainLatestDao.find("select distinct t from DataDayMainLatest t");}
		if(timeType.equals("hour")){l = dataHourMainLatestDao.find("select distinct t from DataHourMainLatest t");}
		return l;
	}
	public List getDataByMainIds(String idsStr,String tType,String factorCode){
		String hqldata = null;
		if(tType.equals("year")){hqldata = "from DataYear t where t.mainId in ("+idsStr +") "+"and t.pollutantCode = '"+factorCode+"'";
		return dataYearDao.find(hqldata);}
		if(tType.equals("month")){hqldata = "from DataMonth t where t.mainId in ("+idsStr +") "+"and t.pollutantCode = '"+factorCode+"'";
		return dataMonthDao.find(hqldata);}
		if(tType.equals("day")){hqldata = "from DataDay t where t.mainId in ("+idsStr +") "+"and t.pollutantCode = '"+factorCode+"'";
		return dataDayDao.find(hqldata);}
		if(tType.equals("hour")){hqldata = "from DataHour t where t.mainId in ("+idsStr +") "+"and t.pollutantCode = '"+factorCode+"'";
		return dataHourDao.find(hqldata);}
		return null;
	}
	@Override
	public List<StatisticsData> dataGrid(StatisticsData data, PageFilter ph,String tType,String pollutantType){
		if(data!=null && data.getPollutionSourceName()!=null && data.getPollutionSourceName().length()>0){
			//根据表单查询数据。
			return dataGridByForm(data,ph,tType,pollutantType);
		}
		List<StatisticsData> listStatisticsData = new ArrayList<StatisticsData>();//返回的数据
		List<TPsPiPort> listPort = null;//排口信息
		List l1 = null;//从数据库读取的数据主表信息
		List l2 = null;
		List<DataMain> listMain = null;//主数据
		List<Data> listData = null;
		List<TColumnsReport> listTColumns = null;//数据报表要展示的列
		List<ColumnsReport> listColumns = new ArrayList<ColumnsReport>();
		List<BigInteger> ids = new ArrayList<BigInteger>();
		String idsStr = "";
		String pCode = getPollutantCode(pollutantType);
		if(pCode==null)
			return null;
		
		if(tType.equals("year")){l1 = dataYearMainLatestDao.find("select distinct t from DataYearMainLatest t");}
		if(tType.equals("month")){l1 = dataMonthMainLatestDao.find("select distinct t from DataMonthMainLatest t");}
		if(tType.equals("day")){l1 = dataDayMainLatestDao.find("select distinct t from DataDayMainLatest t");}
		if(tType.equals("hour")){l1 = dataHourMainLatestDao.find("select distinct t from DataHourMainLatest t");}
		listMain = l1;//获取主数据
		
		listPort = portDao.find("select distinct t from TPsPiPort t where t.pollutantTypeCode = '"+pCode+"'");//获取排口信息
		listTColumns = columnsDao.find("select distinct t from TColumnsReport t where t.type = '"+pollutantType+"报表' order by t.columnSort");
		
		if(listMain==null)
			return null;
		if(listPort==null)
			return null;
		if(listTColumns==null)
			return null;
		
		String hql2 = " from TPsPollutionSourceInfo t";
		List<TPsPollutionSourceInfo> psl = psDao.find(hql2);//获取企业信息
		Map<String, String> m = new HashMap<String, String>();//企业code-名称map
		for(int i =0;i<psl.size();i++){
			m.put(psl.get(i).getCode(), psl.get(i).getPsName());
		}
		//获取数据主表的id------start
		for(int i=0;i<listPort.size();i++){
			StatisticsData d = new StatisticsData();
			d.setMn(listPort.get(i).getMn());
			d.setPort(listPort.get(i).getPortName());
			d.setCode(listPort.get(i).getPsCode());
			d.setPollutionSourceName(m.get(listPort.get(i).getPsCode()));
			d.setPh(ph);
			for(int j=0;j<listMain.size();j++){
				if(listMain.get(j).getMn().equals(listPort.get(i).getMn())){
					d.setTime(listMain.get(j).getMonitorTime());
					d.setDataMainId(listMain.get(j).getId());
					ids.add(listMain.get(j).getId());
					break;
				}
			}
			if(d.getDataMainId()!=null){
				listStatisticsData.add(d);
			}
			
		}
		if(ids.size() == 0)
			return null;
		for(int i=0;i<ids.size();i++){
			if(i == ids.size()-1){
				idsStr = idsStr + ids.get(i).toString();
			}
			else{
				idsStr = idsStr + ids.get(i).toString() + ",";
			}
		}//获取数据主表的id------end
		//System.out.println("idsStr"+idsStr);
		//获取污染物因子的code-----------start
//		String hql1 = " from TScPollutantFactor t";
//		List<TScPollutantFactor> sl = factorDao.find(hql1);
//		Map<Integer, String> m1 = new HashMap<Integer, String>();
//		for(int i =0;i<sl.size();i++){
//			m1.put(sl.get(i).getId(), sl.get(i).getCode());
//		}
		for (TColumnsReport t : listTColumns) {
			ColumnsReport u = new ColumnsReport();
			BeanUtils.copyProperties(t, u);
			u.setPollutantFactorCode(u.getPollutantFactorCode());
			listColumns.add(u);
		}//---------------------end
		for(int i=0;i<listTColumns.size();i++){
			String hqldata = null;
			if(tType.equals("year")){hqldata = "from DataYear t where t.mainId in ("+idsStr +") "+"and t.pollutantCode = '"+listColumns.get(i).getPollutantFactorCode()+"'";
			l2 = dataYearDao.find(hqldata);}
			if(tType.equals("month")){hqldata = "from DataMonth t where t.mainId in ("+idsStr +") "+"and t.pollutantCode = '"+listColumns.get(i).getPollutantFactorCode()+"'";
			l2 = dataMonthDao.find(hqldata);}
			if(tType.equals("day")){hqldata = "from DataDay t where t.mainId in ("+idsStr +") "+"and t.pollutantCode = '"+listColumns.get(i).getPollutantFactorCode()+"'";
			l2 = dataDayDao.find(hqldata);}
			if(tType.equals("hour")){hqldata = "from DataHour t where t.mainId in ("+idsStr +") "+"and t.pollutantCode = '"+listColumns.get(i).getPollutantFactorCode()+"'";
			l2 = dataHourDao.find(hqldata);}
			listData = l2;
			if(listData==null)
				break;
			System.out.println(listStatisticsData);
			System.out.println(listData);
			for(int j=0;j<listStatisticsData.size();j++){
				for(int k=0;k<listData.size();k++){
					if(listStatisticsData.get(j).getDataMainId().equals(listData.get(k).getMainId())){
						fieldSet(listStatisticsData.get(j),i,getColumnData(listTColumns.get(i).getDataType(),listData.get(k)));
						break;
					}
				}
			}
		}
		if(listStatisticsData!=null && listStatisticsData.size()>0){
			if(ph!=null&&ph.getSort()!=null){
				Collections.sort(listStatisticsData);
			}
		}
		return listStatisticsData;
	}
	public void fieldSet(StatisticsData d,Integer i,Float f){
		if(i==0){d.setField1(f);}
		if(i==1){d.setField2(f);}
		if(i==2){d.setField3(f);}
		if(i==3){d.setField4(f);}
		if(i==4){d.setField5(f);}
		if(i==5){d.setField6(f);}
		if(i==6){d.setField7(f);}
		if(i==7){d.setField8(f);}
		if(i==8){d.setField9(f);}
		if(i==9){d.setField10(f);}
	}
	public Float getColumnData(String type,Data data){
		if(type.equals("Cou")){return data.getCou();}
		if(type.equals("Avg")){return data.getAvg();}
		if(type.equals("Min")){return data.getMin();}
		if(type.equals("Max")){return data.getMax();}
		return null;
	}
	public String getMn(TPsPiPort port,StatisticsData data,String pollutantType){
		String pCode = getPollutantCode(pollutantType);
		if(pCode==null)
			return null;
		List<TPsPiPort> list = portDao.find(" from TPsPiPort t where t.psCode = '"+data.getPollutionSourceName()+"' and t.pollutantTypeCode = '"+pCode+"'");
		if(list != null&&list.size()>0){
			//port = list.get(0);
			BeanUtils.copyProperties(list.get(0), port);
			data.setMn(list.get(0).getMn());
			return list.get(0).getMn();
		}
		return null;
	}
	
	@Override
	public List<StatisticsData> dataGridByForm(StatisticsData data, PageFilter ph,String tType,String pollutantType) {
		List<StatisticsData> listStatisticsData = new ArrayList<StatisticsData>();//返回的数据
		int maxItem = 400;
		TPsPiPort port = new TPsPiPort();//根据id查出的排口信息
		
		String mn = getMn(port,data,pollutantType);
		List l1 = null;
		List l2 = null;
		List<Data> listData = null;
		List<DataMain> listDataMain = null;
		List<TColumnsReport> listTColumns = null;//数据报表要展示的列
		List<ColumnsReport> listColumns = new ArrayList<ColumnsReport>();
		List<BigInteger> ids = new ArrayList<BigInteger>();
		String idsStr = "";
		
		if(mn==null)
			return null;
		Map<String, Object> params = new HashMap<String, Object>();
		if(tType.equals("year")){l1 = dataYearMainDao.find("from DataYearMain t"+whereHql(data,params)+" order by t.monitorTime desc",params);}
		if(tType.equals("month")){l1 = dataMonthMainDao.find("from DataMonthMain t"+whereHql(data,params)+" order by t.monitorTime desc",params);}
		if(tType.equals("day")){l1 = dataDayMainDao.find("from DataDayMain t"+whereHql(data,params)+" order by t.monitorTime desc",params);}
		if(tType.equals("hour")){l1 = dataHourMainDao.find("from DataHourMain t"+whereHql(data,params)+" order by t.monitorTime desc",params);}
		listDataMain = l1;
		
		listTColumns = columnsDao.find("select distinct t from TColumnsReport t order by t.columnSort");
		if(listDataMain==null)
			return null;
		if(listTColumns==null)
			return null;
		String hql2 = " from TPsPollutionSourceInfo t";
		List<TPsPollutionSourceInfo> psl = psDao.find(hql2);//获取企业信息
		Map<String, String> m = new HashMap<String, String>();//企业id-名称map
		for(int i =0;i<psl.size();i++){
			m.put(psl.get(i).getCode(), psl.get(i).getPsName());
		}
		//获取数据主表的id------start
		for(int j=0;j<listDataMain.size();j++){
			if(j>=maxItem)
				break;
			ids.add(listDataMain.get(j).getId());
			StatisticsData d = new StatisticsData();
			d.setTime(listDataMain.get(j).getMonitorTime());
			d.setDataMainId(listDataMain.get(j).getId());
			d.setMn(mn);
			d.setPort(port.getPortName());
			d.setCode(port.getPsCode());
			d.setPollutionSourceName(m.get(port.getPsCode()));
			d.setPh(ph);
			listStatisticsData.add(d);
		}
		if(ids.size() == 0)
			return null;
		for(int i=0;i<ids.size();i++){
			if(i == ids.size()-1){
				idsStr = idsStr + ids.get(i).toString();
			}
			else{
				idsStr = idsStr + ids.get(i).toString() + ",";
			}
		}//获取数据主表的id------end
		//System.out.println("idsStr:"+idsStr);
		//获取污染物因子的code-----------start
		String hql1 = " from TScPollutantFactor t";
		List<TScPollutantFactor> sl = factorDao.find(hql1);
		Map<Integer, String> m1 = new HashMap<Integer, String>();
		for(int i =0;i<sl.size();i++){
			m1.put(sl.get(i).getId(), sl.get(i).getCode());
		}
		for (TColumnsReport t : listTColumns) {
			ColumnsReport u = new ColumnsReport();
			BeanUtils.copyProperties(t, u);
			u.setPollutantFactorCode(m1.get(u.getPollutantFactorCode()));
			listColumns.add(u);
		}//---------------------end
		for(int i=0;i<listTColumns.size();i++){
			String hqldata = null;
			l2 = null;
			listData = null;
			if(tType.equals("year")){hqldata = "from DataYear t where t.mainId in ("+idsStr +") "+"and t.pollutantCode = '"+listTColumns.get(i).getPollutantFactorCode()+"'";
			l2 = dataYearDao.find(hqldata);}
			if(tType.equals("month")){hqldata = "from DataMonth t where t.mainId in ("+idsStr +") "+"and t.pollutantCode = '"+listTColumns.get(i).getPollutantFactorCode()+"'";
			l2 = dataMonthDao.find(hqldata);}
			if(tType.equals("day")){hqldata = "from DataDay t where t.mainId in ("+idsStr +") "+"and t.pollutantCode = '"+listTColumns.get(i).getPollutantFactorCode()+"'";
			l2 = dataDayDao.find(hqldata);}
			if(tType.equals("hour")){hqldata = "from DataHour t where t.mainId in ("+idsStr +") "+"and t.pollutantCode = '"+listTColumns.get(i).getPollutantFactorCode()+"'";
			l2 = dataHourDao.find(hqldata);}
			listData = l2;
			if(listData==null)
				break;
			for(int j=0;j<listStatisticsData.size();j++){
				for(int k=0;k<listData.size();k++){
					if(listStatisticsData.get(j).getDataMainId().equals(listData.get(k).getMainId())){
						fieldSet(listStatisticsData.get(j),i,getColumnData(listTColumns.get(i).getDataType(),listData.get(k)));
						break;
					}
				}
			}
		}
		if(listStatisticsData!=null && listStatisticsData.size()>0){
			if(ph!=null&&ph.getSort()!=null){
				Collections.sort(listStatisticsData);
			}
		}
		
		return listStatisticsData;
	}
	
	
	//=========================================================================================================
	
	private String whereHql(StatisticsData demo, Map<String, Object> params) {
		String hql = "";
		if (demo != null) {
			hql += " where 1=1 ";
			if (demo.getMn() != null) {
				String mn = demo.getMn();
				if(mn != null){
					hql += " and t.mn like :type";
					params.put("type", "%%" + mn + "%%");
					//System.out.println("start:"+demo.getStartTime()+"|end:"+demo.getEndTime());
					if (demo.getStartTime() != null) {
						hql += " and t.monitorTime >= :createdatetimeStart";
						params.put("createdatetimeStart", demo.getStartTime());
					}
					if (demo.getEndTime() != null) {
						hql += " and t.monitorTime <= :createdatetimeEnd";
						params.put("createdatetimeEnd", demo.getEndTime());
					}
				}
			}
			
		}
		return hql;
	}

	//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$4
	@Override
	public Option selectRemoveCauses() throws Exception {  
	    //查询前20  
	    //PageHelper.startPage(1, 20, false);  
	    //数据库查询获取统计数据  
	    //List<Map<String, Object>> list = kc22Mapper.selectRemoveCauses();
	    List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
	    String[] nameArray = {"a","b","c","d","e","f","g","h"};
	    int[] intArray = {1,2,3,4,5,6,7,8};
	    int[] intArray2 = {3,3,2,4,7,9,8,6};
	    for(int i=0;i<8;i++){
	    	Map<String, Object> m = new HashMap<String, Object>();
	    	m.put("NAME",i+1+"月");
	    	m.put("TOTAL",i);
	    	list.add(m);
	    }
	    //为了数据从大到小排列，这里需要倒叙  
//	    Collections.sort(list, new Comparator<Map<String, Object>>() {  
//	        @Override  
//	        public int compare(Map<String, Object> o1, Map<String, Object> o2) {  
//	            return -1;  
//	        }  
//	    });  
	    //创建Option  
	    Option option = new Option();  
	    option.title("某地区蒸发量和降水量","纯属虚构").tooltip(Trigger.axis).legend("蒸发量","降水量");  
	    //toolbox
	    Toolbox toolbox = new Toolbox();
	    toolbox.setShow(true);
	    toolbox.feature(new Mark(),new DataView(),new MagicType(),new Restore(),new SaveAsImage());
	    option.setToolbox(toolbox);
	    //
	    option.setCalculable(true);
	    //
	    
	    //横轴为值轴  
	    option.xAxis(new ValueAxis().boundaryGap(0d, 0.2));  
	    //创建类目轴  
	    CategoryAxis category = new CategoryAxis();
	    //category.data(nameArray);
	    //for(int i=0;i<nameArray.length;i++){
	    	category.data(nameArray);
	    //}
	    //设置类目轴  
	    option.yAxis(category);  
	    //柱状数据  
	    
	    //饼图数据  
	    //Pie pie = new Pie("金额（元）");  
	    //循环数据
	    
	    //for (Map<String, Object> objectMap : list) {  
	        //设置类目
	        //category.data(objectMap.get("NAME"));
	        //类目对应的柱状图
	        //bar.data(objectMap.get("TOTAL"));
	        //饼图数据  
	        //pie.data(new PieData(objectMap.get("NAME").toString(), objectMap.get("TOTAL")));  
	    //}
	    Bar bar1 = new Bar();
	    bar1.setName("蒸发量");
	    for(int i=0;i<intArray.length;i++){
	    	bar1.data(intArray[i]);
	    }
	    MarkLine line = new MarkLine();
	    //List<Map<String, Object>> list2 = new ArrayList<Map<String,Object>>();
	    Map<String, Object> m2 = new HashMap<String, Object>();
	    m2.put("type", "average");
	    m2.put("name", "平均值");
	    line.data(m2);
	    bar1.setMarkLine(line);
	    
	    Bar bar2 = new Bar();
	    bar2.setName("降水量");
	    for(int i=0;i<intArray2.length;i++){
	    	bar2.data(intArray2[i]);
	    }
	    
	    Bar[] bar = {bar1,bar2};
	    option.series(bar);
	    
	    //饼图的圆心和半径  
	    //pie.center(900,380).radius(100);  
	    //设置数据  
	    
	    //由于药品名字过长，图表距离左侧距离设置180，关于grid可以看ECharts的官方文档  
	    //option.grid().x(100);  
	    //返回Option
	    return option;  
	}
	@Override
	public Option getChartData(String pollutantType, String factorType,String timeType) {
		/*
		 * 获取当天、当月、当年 各企业的排放量;data_day_main data_month_main data_year_main最新的一条数据
		 * */
		
		SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfMonth = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
		// 根据factorType获取因子code
		String code = getPollutantCode(pollutantType);
		String factorCode = getFactorCode(factorType);
		//1.根据pollutantType获取排口信息、mn
		if(code == null)
			return null;
		List<TPsPiPort> ports = getPort(code);
		//2.获取企业信息
		Map<String,String> psMap = getPsMap();
		//3.从data_main中获取最新的一条数据
		List<DataMain> listMain = getDataMainLatest(timeType);
		if(listMain == null)
			return null;
		for(int i=0;i<listMain.size();i++){
			DataMain m = listMain.get(i);
			if(timeType.equals("day")){
				if(!sdfDay.format(m.getMonitorTime()).equals(sdfDay.format(new Date()))){
					listMain.remove(m);
				}
			}
			else if(timeType.equals("month")){
				if(!sdfMonth.format(m.getMonitorTime()).equals(sdfMonth.format(new Date()))){
					listMain.remove(m);
				}
			}
			else if(timeType.equals("year")){
				if(!sdfYear.format(m.getMonitorTime()).equals(sdfYear.format(new Date()))){
					listMain.remove(m);
				}
			}
		}
		if(listMain == null)
			return null;
		//获取mainid
		List<BigInteger> ids = new ArrayList<BigInteger>();
		String idsStr = "";
		List<List<StatisticsData>> listStatisticsData =new ArrayList<List<StatisticsData>>();
		for(int i=0;i<ports.size();i++){
			List<StatisticsData> l = new ArrayList<StatisticsData>();
			StatisticsData d = new StatisticsData();
			d.setMn(ports.get(i).getMn());
			d.setPort(ports.get(i).getPortName());
			d.setCode(ports.get(i).getPsCode());
			d.setPollutionSourceName(psMap.get(ports.get(i).getPsCode()));
			for(int j=0;j<listMain.size();j++){
				
				if(listMain.get(j).getMn().equals(ports.get(i).getMn())){
					d.setTime(listMain.get(j).getMonitorTime());
					d.setDataMainId(listMain.get(j).getId());
					ids.add(listMain.get(j).getId());
					l.add(d);
					break;
				}
			}
			if(d.getDataMainId()!=null){
				listStatisticsData.add(l);
			}
		}
		if(ids.size() == 0)
			return null;
		for(int i=0;i<ids.size();i++){
			if(i == ids.size()-1){
				idsStr = idsStr + ids.get(i).toString();
			}
			else{
				idsStr = idsStr + ids.get(i).toString() + ",";
			}
		}
		//从data中获取数据
		List<Data> listData = getDataByMainIds(idsStr, timeType, factorCode);
		//
		Map<BigInteger,Float> dataMap = new HashMap<BigInteger, Float>();
		for(Data data: listData){
			dataMap.put(data.getMainId(), data.getCou());
		}
		for(int i = 0;i<listStatisticsData.size();i++){//去掉没有数据的项
			List<StatisticsData> l = listStatisticsData.get(i);
			if(dataMap.get(l.get(0).getDataMainId())==null){
				listStatisticsData.remove(l);
			}
		}
		try {
			return chartOption(listStatisticsData,dataMap,timeType,factorType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Option chartOption(List<List<StatisticsData>> listStatisticsData,Map<BigInteger,Float> dataMap,String timeType,String factorType) throws Exception {  

	    List<String> nameList = new ArrayList<String>();
	    List<Float> dataList = new ArrayList<Float>();
	    List<Float> dataFuzhuList = new ArrayList<Float>();
	    Stack<Float> fuzhuStack = new Stack<Float>();
	    nameList.add("总排放量");
	    Float total = (float) 0;
	    dataList.add(total);
	    for(List<StatisticsData> l:listStatisticsData){
	    	nameList.add(l.get(0).getPollutionSourceName());
	    	Float f = dataMap.get(l.get(0).getDataMainId());
	    	dataList.add(f);
	    	total = total + f;
	    }
	    dataList.set(0, total);
	    Float fuzhu = (float) 0;
	    fuzhuStack.push(fuzhu);
	    for(int i=dataList.size();i>1;i--){
	    	if(i==2){
	    		fuzhuStack.push((float) 0);
	    	}
	    	else{
	    		fuzhu = fuzhu + dataList.get(i-1);
		    	fuzhuStack.push(fuzhu);
	    	}
	    }
	    for(int i=0;i<dataList.size();i++){
	    	dataFuzhuList.add(fuzhuStack.pop());
	    }

	    Option option = new Option();
	    Tooltip tooltip = new Tooltip();
	    tooltip.trigger(Trigger.axis);
	    tooltip.axisPointer().type(PointerType.shadow);
	    //tooltip.formatter("function (params) {var tar = params[0];return tar.name + '<br/>' + tar.seriesName + ' : ' + tar.value;}");
	    String subText = "";
	    if(timeType.equals("hour")){subText = "小时排放量";}
	    else if(timeType.equals("day")){SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");subText = "日排放量"+"("+sdf.format(new Date())+")";}
	    else if(timeType.equals("month")){SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");subText = "月排放量"+"("+sdf.format(new Date())+")";}
	    else if(timeType.equals("year")){SimpleDateFormat sdf = new SimpleDateFormat("yyyy");subText = "年排放量"+"("+sdf.format(new Date())+")";}
	    option.title(factorType,subText).tooltip(tooltip);
	    
	    //toolbox
	    Toolbox toolbox = new Toolbox();
	    toolbox.setShow(true);
	    toolbox.feature(new Mark(),new DataView(),new Restore(),new SaveAsImage());
	    option.setToolbox(toolbox);
	    //option.setCalculable(true);
	    //zong轴为值轴  
	    option.yAxis(new ValueAxis().boundaryGap(0d, 0.01));  
	    //创建类目轴  
	    CategoryAxis category = new CategoryAxis();
	    //category.data(nameArray);
	    for(int i=0;i<nameList.size();i++){
	    	category.data(nameList.get(i));
	    }
	    option.xAxis(category);
	    
	    Bar bar1 = new Bar();
	    bar1.setName("辅助");
	    for(int i=0;i<dataFuzhuList.size();i++){
	    	bar1.data(dataFuzhuList.get(i));
	    }
	    bar1.setStack("总量");
	    ItemStyle style = new ItemStyle();
	    style.normal().setBarBorderColor("rgba(0,0,0,0)");
	    style.normal().setColor("rgba(0,0,0,0)");
	    style.emphasis().setBarBorderColor("rgba(0,0,0,0)");
	    style.emphasis().setColor("rgba(0,0,0,0)");
	    bar1.setItemStyle(style);
	    
	    Bar bar2 = new Bar();
	    bar2.setName("排放量");
	    for(int i=0;i<dataList.size();i++){
	    	bar2.data(dataList.get(i));
	    }
	    bar2.setStack("总量");
	    bar2.itemStyle().normal().label().setShow(true);
	    bar2.itemStyle().normal().label().setPosition("inside");
	    Bar[] bar = {bar1,bar2};
	    option.series(bar);
	    
	    return option;  
	}
	public void getInfoBoardPsPortCount(){}
	@Override
	public Option getInfoBoardOnlineRate(){
		Option option = new Option();
	    Tooltip tooltip = new Tooltip();
	    tooltip.trigger(Trigger.item).formatter("{a} <br/>{b} : {c} ({d}%)");
	    Legend legend = new Legend();
	    legend.orient(Orient.vertical).x(X.left);
	    for(int i=1;i<6;i++){
	    	legend.data("item"+i);
	    }
	    option.calculable(true).tooltip(tooltip).legend(legend);
	    int radius[] = {40,55};
	    Pie pie = new Pie();
	    pie.name("访问来源");
	    pie.radius(radius);
	    ItemStyle style = new ItemStyle();
	    style.normal().label().show(true);
	    style.normal().labelLine().show(false);
	    style.emphasis().label().show(true).position("center").textStyle().fontSize(20).fontWeight("bold");
	    pie.itemStyle(style);
	    for(int i=1;i<6;i++){
	    	light.mvc.utils.echarts.data.Data d = new light.mvc.utils.echarts.data.Data();
	    	d.value(i).name("item"+i);
	    	pie.data(d);
	    }
//	    Pie[] pies = {pie};
	    option.series(pie);
	    return option;
	}
	
	public ItemStyle getLabelTop(){
		ItemStyle labelTop = new ItemStyle();
		labelTop.normal().labelLine().show(false);
		labelTop.normal().label().show(true).position("center").formatter("{b}").textStyle().baseline("bottom");
		return labelTop;
	}
	
	public ItemStyle getLabelFormatter(){
		ItemStyle labelFormatter = new ItemStyle();
		labelFormatter.normal().label().formatter("function(params){ return 100-params.value+'%'}").textStyle().baseline("top");
		return labelFormatter;
	}
	
	public ItemStyle getLabelBottom(){
		ItemStyle labelBottom = new ItemStyle();
		labelBottom.normal().color("#ccc").labelLine().show(false);
		labelBottom.normal().label().show(true);
		labelBottom.normal().label().position("center");
		labelBottom.emphasis().color("rgba(0,0,0,0)");
		return labelBottom;
	}
	@Override
	public Option getInfoBoardOnlineRate2(){
		Option option = new Option();
		int radius[] = {55,76};
		Tooltip tooltip = new Tooltip();
	    tooltip.trigger(Trigger.item).formatter("{a} <br/>{b} : {c} ({d}%)");
	    String names[] = {"正常数","超标数","预警数","断线数","传输有效率"};
		Legend legend = new Legend();
		legend.x("center").y("bottom");
		for(int i=0;i<5;i++){
			legend.data(names[i]);
		}
		ItemStyle style1 = getLabelTop();
		ItemStyle style2 = getLabelFormatter();
		ItemStyle style3 = getLabelBottom();
		option.legend(legend).tooltip(tooltip);
		//option.title().text("The App World").subtext("from global web index").x("center");
	    for(int i=0;i<5;i++){
	    	Pie pie = new Pie();
	    	String center[]={(i*20+10)+"%","43%"};
	    	light.mvc.utils.echarts.data.Data d = new light.mvc.utils.echarts.data.Data();
	    	light.mvc.utils.echarts.data.Data d2 = new light.mvc.utils.echarts.data.Data();
	    	d.name("other").value(10+10*i).itemStyle(style3);
	    	d2.name(names[i]).value(100-10*i).itemStyle(style1);
	    	pie.center(center).radius(radius).x(""+i*20+"%");
	    	pie.data(d).data(d2);
	    	option.series(pie);
	    }
		return option;
	}
	
	@Override
	public Option getInfoBoardLargeAmountPs(String code){
		String names[]={"企业一","企业二","企业三","企业四","企业五"};
		int values[]={333,380,430,576,632};
		Option option = new Option();
		option.title("排污大户", "总烃排放量");
		option.tooltip().trigger(Trigger.axis);
		option.calculable(true);
		
		ValueAxis v = new ValueAxis();
		v.boundaryGap(0d, 0.01);
		option.xAxis(v);
		
		CategoryAxis category = new CategoryAxis();
		for(int i=0;i<names.length;i++){
			category.data(names[i]);
		}
		option.yAxis(category);
		
		Bar bar = new Bar("总烃排放量");
		for(int i=0;i<values.length;i++){
			bar.data(values[i]);
		}
		option.series(bar);
		
		return option;
	}
	/*
	 * type:hour,day,month,year
	 */
	public Boolean timeMatch(Date time1,Date time2,String type){
		if(type.equals("hour")){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");
			return format.format(time1).equals(format.format(time2));
		}
		else if(type.equals("day")){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			return format.format(time1).equals(format.format(time2));
		}
		else if(type.equals("month")){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
			return format.format(time1).equals(format.format(time2));
		}
		else if(type.equals("year")){
			SimpleDateFormat format = new SimpleDateFormat("yyyy");
			return format.format(time1).equals(format.format(time2));
		}
		return false;
	}
	public Map<String,String> getPsCodeNameMap(){
		String hql = "from TPsPollutionSourceInfo";
		List<TPsPollutionSourceInfo> list = psDao.find(hql);
		Map<String,String> map = new HashMap<String, String>();
		if(list!=null&&list.size()>0){
			for(TPsPollutionSourceInfo ps:list){
				map.put(ps.getCode(), ps.getPsName());
			}
		}
		return map;
	}
	public Map<String,String> getPiCodeNameMap(){
		String hql = "from TPsPiPort";
		List<TPsPiPort> list = portDao.find(hql);
		Map<String,String> map = new HashMap<String, String>();
		if(list!=null&&list.size()>0){
			for(TPsPiPort ps:list){
				map.put(ps.getPortCode(), ps.getPortName());
			}
		}
		return map;
	}
	public Map<String,String> getPiPsMap(){
		Map<String,String> psMap = getPsCodeNameMap();
		String hql = "from TPsPiPort";
		List<TPsPiPort> list= portDao.find(hql);
		Map<String,String> map = new HashMap<String, String>();
		if(list !=null&&list.size()>0){
			for(TPsPiPort port:list){
				String name = psMap.get(port.getPsCode());
				if(name!=null){
					map.put(port.getPortCode(), (String) psMap.get(port.getPsCode()));
				}
			}
		}
		return map;
	}
	class PsValue implements Comparable<PsValue>{
		String psName;Float value;Date time;
		public String getPsName() {return psName;}
		public void setPsName(String psName) {this.psName = psName;}
		public Float getValue() {return value;}
		public void setValue(Float value) {this.value = value;}
		public Date getTime() {return time;}
		public void setTime(Date time) {this.time = time;}
		@Override
		public int compareTo(PsValue o) {
			if(this.value!=null&&o.value==null){
				return -1;
			}
			else if(this.value==null&&o.value!=null){
				return 1;
			}
			else if(this.value==null&&o.value==null){
				return 0;
			}
			else if(this.value>o.value){
				return -1;
			}else if(this.value<o.value){
				return 1;
			}
			else{
				return 0;
			}
		}
	}
	@Override
	public Option getInfoBoardLargeAmountPs2(String code){
		Option option = new Option();
		String name = getFactorName(code);
		String hql1="from DataYearMainLatest ";
		List<DataYearMainLatest> listMain = dataYearMainLatestDao.find(hql1);
		Map<String,String> piCodePsNameMap = getPiPsMap();
		String idsStr = null;
		if(listMain!=null&&listMain.size()>0){
			for(DataYearMainLatest m:listMain){
				if(timeMatch(m.getMonitorTime(),new Date(),"year")){
					if(idsStr==null)
						idsStr = m.getId()+"";
					else
						idsStr += ","+m.getId();
				}
			}
		}
		else{
			return option;
		}
		String hql2 = "from DataYear t where t.pollutantCode = '"+code+"' and t.mainId in ("+idsStr+")";
		List<DataYear> listData = dataYearDao.find(hql2);
		System.out.println(listMain.size());
		System.out.println(listData.size());
		Map<String,Float> piCouMap = new HashMap<String, Float>();
		if(listData!=null&&listData.size()>0){
			for(DataYearMainLatest main:listMain){
				for(DataYear data:listData){
					if(data.getMainId().compareTo(main.getId())==0){
						piCouMap.put(main.getPiCode(), data.getCou());
						break;
					}
				}
			}
		}
		
		List<PsValue> listPsValue = new ArrayList<ReportDataServiceImpl.PsValue>();
		for(DataYearMainLatest main:listMain){
			String pCode = main.getPiCode();
			Boolean b = true;
			for(PsValue pv:listPsValue){
				if(pv.getPsName().equals(piCodePsNameMap.get(pCode))){
					b=false;
					Float f1 = pv.getValue();
					Float f2 = piCouMap.get(pCode);
					if(f1!=null&&f2!=null){
						pv.setValue(f1+f2);
					}
					else if(f1==null&&f2!=null){
						pv.setValue(f2);
					}
					else if(f1==null&&f2==null){
						
					}
					else if(f1!=null&&f2==null){
						
					}
					break;
				}
			}
			if(b){
				PsValue psValue = new PsValue();
				psValue.setPsName(piCodePsNameMap.get(pCode));
				psValue.setTime(main.getMonitorTime());
				psValue.setValue(piCouMap.get(pCode));
				listPsValue.add(psValue);
			}
		}
		Collections.sort(listPsValue);
		
		option.title("排污大户", name+"排放量");
		option.tooltip().trigger(Trigger.axis);
		option.calculable(true);
		
		ValueAxis v = new ValueAxis();
		v.boundaryGap(0d, 0.01);
		option.xAxis(v);
		
		CategoryAxis category = new CategoryAxis();
		Bar bar = new Bar(name+"排放量");
		if(listPsValue.size()<5){
			int size = listPsValue.size();
			for(int i=0;i<size;i++){
				category.data(listPsValue.get(size-1-i).getPsName());
				bar.data(listPsValue.get(size-1-i).getValue());
			}
		}
		else if(listPsValue.size()>=5){
			for(int i=0;i<5;i++){
				category.data(listPsValue.get(4-i).getPsName());
				bar.data(listPsValue.get(4-i).getValue());
			}
		}
		option.yAxis(category);
		option.series(bar);
		return option;
	}
	
	@Override
	public Option getInfoBoardTrend(){
		Option option=new Option();
		option.title("浓度趋势", "企业一");
		option.tooltip().trigger(Trigger.axis);
		String factors[] = {"总烃","甲烷","非甲烷总烃"};
		String timeStamps[] = {"7:00","8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00"};
		int values1[] = {120,132,101,134,90,230,210,124,155,167};
		int values2[] = {127,156,78,156,144,345,111,232,452,189};
		int values3[] = {78,98,132,178,76,233,278,345,421,301};
		List<int[]> list = new ArrayList<int[]>();
		list.add(values1);
		list.add(values2);
		list.add(values3);
		for(int i=0;i<factors.length;i++){
			option.legend().data(factors[i]);
		}
		option.calculable(true);
		CategoryAxis category = new CategoryAxis();
		category.boundaryGap(false);
		for(int i=0;i<timeStamps.length;i++){
			category.data(timeStamps[i]);
		}
		ValueAxis value = new ValueAxis();
		option.xAxis(category).yAxis(value);
		for(int i=0;i<factors.length;i++){
			Line line = new Line(factors[i]);
			line.stack("浓度");
			for(int k=0;k<list.get(i).length;k++){
				line.data(list.get(i)[k]);
			}
			option.series(line);
		}
		return option;
	}
	public Map<String,String> getFactorMap(){
		String hql = "from TScPollutantFactor t";
		List<TScPollutantFactor> list = factorDao.find(hql);
		Map<String,String> map = new HashMap<String, String>();
		for(TScPollutantFactor factor:list){
			map.put(factor.getName(), factor.getCode());
		}
		return map;
	}
	public Date addHours(Date d,Integer n){
		long value = d.getTime()+n*60*60*1000;
		Date d1 = new Date(value);
		return d1;
	}
	public Boolean compareDate(Date dd,String sd,String f){
		SimpleDateFormat format = new SimpleDateFormat(f);
		return format.format(dd).equals(sd);
	}
	@Override
	public Option getLineChartData(String psName,String piCode,String timeType){
		Option option=new Option();
		
		option.tooltip().trigger(Trigger.axis);
		SimpleDateFormat format = new SimpleDateFormat("MM-dd HH");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH");
		Date endTime = new Date();
		Date startTime = null;
		List<String> timeStamps = new ArrayList<String>();
		Map<String,String> factorMap = getFactorMap();
		Map<String, Float> ztMap = new HashMap<String, Float>();
		Map<String, Float> jwMap = new HashMap<String, Float>();
		Map<String, Float> fjMap = new HashMap<String, Float>();
		Map<String,String> piMap = getPiCodeNameMap();
		if(timeType==null)
			return option;
		if(timeType.equals("24h")){
			try {
				startTime = format2.parse(format2.format(addHours(endTime,-24)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			option.title("24小时数据", psName+"-"+piMap.get(piCode));
			for(int i=0;i<24;i++){
				timeStamps.add(format.format(addHours(startTime,i)));
			}
		}
		if(startTime==null)
			return option;
		//String factor = factorName;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		String hql = "from DataHourMain t where t.piCode = '"+piCode+"' and t.monitorTime >= :startTime and t.monitorTime <= :endTime";
		List<DataHourMain> listHourMain= dataHourMainDao.find(hql,params);
		
		for(int i=0;i<timeStamps.size();i++){
			ztMap.put(timeStamps.get(i), null);
			for(DataHourMain m:listHourMain){
				if(compareDate(m.getMonitorTime(),timeStamps.get(i),"MM-dd HH")){
					String h = "from DataHour t where t.mainId = "+m.getId();
					List<DataHour> listHour = dataHourDao.find(h);
					if(listHour!=null && listHour.size()>0){
						for(DataHour d:listHour){
							if(d.getPollutantCode().equals(factorMap.get("总烃"))){
								ztMap.put(timeStamps.get(i), d.getAvg());
							}
							else if(d.getPollutantCode().equals(factorMap.get("甲烷"))){
								jwMap.put(timeStamps.get(i), d.getAvg());
							}
							else if(d.getPollutantCode().equals(factorMap.get("非甲烷总烃"))){
								fjMap.put(timeStamps.get(i), d.getAvg());
							}
						}
					}
					break;
				}
			}
		}
		
//		option.legend().data(factor);
		option.calculable(true);
		CategoryAxis category = new CategoryAxis();
		category.boundaryGap(false);
		Line line1 = new Line("总烃");
		Line line2 = new Line("甲烷");
		Line line3 = new Line("非甲烷总烃");
		for(int i=0;i<timeStamps.size();i++){
			category.data(timeStamps.get(i));
			if(ztMap.get(timeStamps.get(i))!=null){
				line1.data(ztMap.get(timeStamps.get(i)));
			}
			else{
				line1.data("");
			}
			if(jwMap.get(timeStamps.get(i))!=null){
				line2.data(jwMap.get(timeStamps.get(i)));
			}
			else{
				line2.data("");
			}
			if(fjMap.get(timeStamps.get(i))!=null){
				line3.data(fjMap.get(timeStamps.get(i)));
			}
			else{
				line3.data("");
			}
		}
		ValueAxis value = new ValueAxis();
		option.xAxis(category).yAxis(value);
		option.series(line1).series(line2).series(line3);
		
		return option;
	}
}
