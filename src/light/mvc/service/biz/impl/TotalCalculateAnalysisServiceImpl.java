package light.mvc.service.biz.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.base.TPsPiPort;
import light.mvc.model.base.TPsPollutionSourceInfo;
import light.mvc.model.biz.TLicenceBaseInfo;
import light.mvc.model.biz.TLicencePollutantInfo;
import light.mvc.model.data.DataHourMain;
import light.mvc.model.data.DataMonth;
import light.mvc.model.data.DataMonthMain;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.biz.TotalCalculateAnalysis;
import light.mvc.service.biz.TotalCalculateAnalysisServiceI;
import light.mvc.utils.echarts.Option;
import light.mvc.utils.echarts.axis.CategoryAxis;
import light.mvc.utils.echarts.axis.ValueAxis;
import light.mvc.utils.echarts.code.Trigger;
import light.mvc.utils.echarts.series.Line;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TotalCalculateAnalysisServiceImpl implements TotalCalculateAnalysisServiceI {

	@Autowired
	private BaseDaoI<TPsPiPort> portDao;
	@Autowired
	private BaseDaoI<TPsPollutionSourceInfo> psDao;
	@Autowired
	private BaseDaoI<DataMonthMain> monthMainDao;
	@Autowired
	private BaseDaoI<DataMonth> monthDao;
	@Autowired
	private BaseDaoI<TLicenceBaseInfo> licenceBaseDao;
	@Autowired
	private BaseDaoI<TLicencePollutantInfo> licencePollutantDao;
	/*-------------------------------------------------------------------------------------------------*/
	@Override
	public List<Tree> portTree(String psCode) {
		List<TPsPiPort> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		l = portDao.find("select distinct t from TPsPiPort t where t.psCode = '"+psCode+"'");
		if ((l != null) && (l.size() > 0)) {
			for (TPsPiPort r : l) {
				Tree tree = new Tree();
				tree.setId(r.getId() + "");
				tree.setText(r.getPortName());
				//tree.setIconCls("icon-company");
				tree.setValue(r.getPortCode());
				lt.add(tree);
			}
		}
		return lt;
	}
	
	@Override
	public List<Tree> psTree() {
		List<TPsPollutionSourceInfo> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		l = psDao.find("select distinct t from TPsPollutionSourceInfo t");
		if ((l != null) && (l.size() > 0)) {
			for (TPsPollutionSourceInfo r : l) {
				Tree tree = new Tree();
				tree.setId(r.getId() + "");
				tree.setText(r.getPsName());
				//tree.setIconCls("icon-company");
				tree.setValue(r.getCode());
				lt.add(tree);
			}
		}
		return lt;
	}

//	@Override
//	public TotalCalculateAnalysis get(Long id) {
//		TTotalCalculateAnalysis t = recordDao.get(TTotalCalculateAnalysis.class, id);
//		TotalCalculateAnalysis r = new TotalCalculateAnalysis();
//		BeanUtils.copyProperties(t, r);
//		return r;
//	}

	@Override
	public List<TotalCalculateAnalysis> dataGrid(TotalCalculateAnalysis demo, PageFilter ph) {
		List<TotalCalculateAnalysis> ul = new ArrayList<TotalCalculateAnalysis>();
		Map<String, Object> params = new HashMap<String, Object>();
//		String hql = " from TTotalCalculateAnalysis t ";
//		List<TTotalCalculateAnalysis> l = recordDao.find(hql + whereHql(demo, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		TotalCalculateAnalysis data1=new TotalCalculateAnalysis(5,"004",23.4f,24.56f,65.32f,500f,400f,450f);
		TotalCalculateAnalysis data2=new TotalCalculateAnalysis(10,"005",53.4f,34.56f,75.32f,600f,500f,480f);
		List<TotalCalculateAnalysis> l = new ArrayList<TotalCalculateAnalysis>();
		l.add(data1);
		l.add(data2);
		
		String hql1 = " from TPsPollutionSourceInfo t";
		List<TPsPollutionSourceInfo> l1 = psDao.find(hql1);
		Map<String, String> m1 = new HashMap<String, String>();
		for(int i =0;i<l1.size();i++){
			m1.put(l1.get(i).getCode(), l1.get(i).getPsName());
		}
		for (TotalCalculateAnalysis t : l) {
			TotalCalculateAnalysis u = new TotalCalculateAnalysis();
			BeanUtils.copyProperties(t, u);
			u.setCode(t.getPsCode());
			u.setPsCode(m1.get(t.getPsCode()));
			ul.add(u);
		}
		return ul;
	}

	@Override
	public Option getTotalAnalysis(Integer psId){
		Option option=new Option();
		option.title("总量趋势-2018", "企业一");
		option.tooltip().trigger(Trigger.axis);
		
		String factor = "总烃";
		Float allow = 800f;
		Float values[] = {56f,71.32f,43.45f};
		String timeStamps[] = {"一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"};
		
		option.legend().data("计划量");
		option.legend().data(factor);
		option.calculable(true);
		CategoryAxis category = new CategoryAxis();
		category.boundaryGap(false);
		for(int i=0;i<timeStamps.length;i++){
			category.data(timeStamps[i]);
		}
		ValueAxis value = new ValueAxis();
		option.xAxis(category).yAxis(value);
		
		Line line1 = new Line(factor);
		for(int i=0;i<values.length;i++){
			Float v = 0f;
			for(int j=0;j<i+1;j++){
				v=v+values[j];
			}
			line1.data(v);
		}
		Line line2 = new Line("计划量");
		for(int i=0;i<12;i++){
			line2.data(allow*(i+1)/12);
		}
		option.series(line1).series(line2);

		return option;
	}
	public Float getAllowValue(String psCode,String factorCode){
		String hql = "from TLicenceBaseInfo t where t.psCode = '"+psCode+"'";
		List<TLicenceBaseInfo> listBase = licenceBaseDao.find(hql);
		if(listBase==null||listBase.size()==0)
			return null;
		String num=listBase.get(0).getLicenceNum();
		String hql2 = "from TLicencePollutantInfo t where t.licenceNum = '"+num+"'"+" and t.pollutantCode = '"+factorCode+"'";
		List<TLicencePollutantInfo> listPollutant = licencePollutantDao.find(hql2);
		if(listPollutant==null||listPollutant.size()==0)
			return null;
		return listPollutant.get(0).getYearPi();
	}
	
	@Override
	public Option getTotalAnalysisCopy(Integer psId,String psCode,String psName,String factorCode,String factorName){
		Option option=new Option();
		String factor = factorName;
		SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
		SimpleDateFormat formatMonth = new SimpleDateFormat("M");
		Date thisYear=null;
		try {
			thisYear = formatYear.parse(formatYear.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(thisYear==null)
			return option;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startTime", thisYear);
		params.put("endTime", new Date());
		String hql = "from DataMonthMain t where t.psCode = '"+psCode+"'"+" and t.monitorTime >= :startTime and t.monitorTime <= :endTime";
		List<DataMonthMain> listMonthMain = monthMainDao.find(hql,params);
		String timeStamps[] = {"一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"};
		Map<String,Float> map = new HashMap<String, Float>();
		for(int i=0;i<12;i++){
			List<Float> cous = new ArrayList<Float>();
			
			for(DataMonthMain m:listMonthMain){
				if(formatMonth.format(m.getMonitorTime()).equals(i+1+"")){
					String h = "from DataMonth t where t.mainId = "+m.getId()+" and t.pollutantCode = '"+factorCode+"'";
					List<DataMonth> l = monthDao.find(h);
					if(l!=null&&l.size()>0){
						for(DataMonth dm:l){
							cous.add(dm.getCou());
						}
						//cous.add(l.get(0).getCou());
					}
				}
			}
			if(cous.size()==0){
				map.put(timeStamps[i], null);
			}
			else{
				Float f = 0f;
				for(Float cou:cous){
					f=f+cou;
				}
				map.put(timeStamps[i], f);
			}
		}
		option.title("总量趋势-"+formatYear.format(thisYear), psName);
		option.tooltip().trigger(Trigger.axis);
		
		option.legend().data("计划量");
		option.legend().data(factor);
		option.calculable(true);
		CategoryAxis category = new CategoryAxis();
		category.boundaryGap(false);
		for(int i=0;i<timeStamps.length;i++){
			category.data(timeStamps[i]);
		}
		ValueAxis value = new ValueAxis();
		option.xAxis(category).yAxis(value);
		
		Integer currentMonth = null;
		currentMonth = Integer.parseInt(formatMonth.format(new Date()));
		if(currentMonth!=null){
			Line line1 = new Line(factor);
			for(int i=0;i<currentMonth;i++){
				Float v = 0f;
				for(int j=0;j<i+1;j++){
					if(map.get(timeStamps[j])!=null){
						v=v+map.get(timeStamps[j]);
					}
				}
				line1.data(v);
			}
			option.series(line1);
		}
		
		Float allow = getAllowValue(psCode,factorCode);
		System.out.println(allow);
		if(allow!=null){
			Line line2 = new Line("计划量");
			for(int i=0;i<12;i++){
				line2.data(allow*(i+1)/12);
			}
			option.series(line2);
		}
		return option;
	}
	
//	@Override
//	public Long count(TotalCalculateAnalysis demo, PageFilter ph) {
//		Map<String, Object> params = new HashMap<String, Object>();
//		String hql = " from TTotalCalculateAnalysis t ";
//		return recordDao.count("select count(*) " + hql + whereHql(demo, params), params);
//	}
	
	private String whereHql(TotalCalculateAnalysis demo, Map<String, Object> params) {
		String hql = "";
		if (demo != null) {
			hql += " where 1=1 ";
			if (demo.getPsCode() != null) {
				hql += " and t.psCode like :name";
				params.put("name", "%%" + demo.getPsCode() + "%%");
			}
		}
		return hql;
	}
	
	/*-------------------------------------------------------------------------------------------------*/
	
	/*-------------------------------------------------------------------------------------------------*/
	private String orderHql(PageFilter ph) {
		String orderString = "";
		if ((ph.getSort() != null) && (ph.getOrder() != null)) {
			orderString = " order by t." + ph.getSort() + " " + ph.getOrder();
		}else if(ph.getOrder() != null){
			orderString = " order by t.id" + " " + ph.getOrder();
		}
		return orderString;
	}

}
