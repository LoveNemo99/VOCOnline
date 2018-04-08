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
import light.mvc.model.biz.TDataRealtimeMonitor;
import light.mvc.model.data.DataHour;
import light.mvc.model.data.DataHourMain;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.biz.DataRealtimeMonitor;
import light.mvc.service.biz.DataRealtimeMonitorServiceI;
import light.mvc.utils.echarts.Option;
import light.mvc.utils.echarts.axis.CategoryAxis;
import light.mvc.utils.echarts.axis.ValueAxis;
import light.mvc.utils.echarts.code.Trigger;
import light.mvc.utils.echarts.series.Line;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataRealtimeMonitorServiceImpl implements DataRealtimeMonitorServiceI {

	@Autowired
	private BaseDaoI<TDataRealtimeMonitor> monitorDao;
	@Autowired
	private BaseDaoI<TPsPollutionSourceInfo> psDao;
	@Autowired
	private BaseDaoI<TPsPiPort> portDao;
	@Autowired
	private BaseDaoI<DataHour> hourDao;
	@Autowired
	private BaseDaoI<DataHourMain> hourMainDao;

	/*-------------------------------------------------------------------------------------------------*/

	public Date addHours(Date d,Integer n){
		long value = d.getTime()+n*60*60*1000;
		Date d1 = new Date(value);
		return d1;
	}
	public Boolean compareDate(Date dd,String sd,String f){
		SimpleDateFormat format = new SimpleDateFormat(f);
		return format.format(dd).equals(sd);
	}
	/*
	 * timeType: 24h 48h
	 */
	@Override
	public Option getLineChartData(String psName,String piName,String piCode,String factorCode,String factorName,String timeType){
		Option option=new Option();
		
		option.tooltip().trigger(Trigger.axis);
		SimpleDateFormat format = new SimpleDateFormat("MM-dd HH");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH");
		Date endTime = new Date();
		Date startTime = null;
		List<String> timeStamps = new ArrayList<String>();
		Map<String, Float> map = new HashMap<String, Float>();
		if(timeType==null)
			return option;
		if(timeType.equals("24h")){
			try {
				startTime = format2.parse(format2.format(addHours(endTime,-24)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			option.title("24小时数据", psName+"-"+piName);
			for(int i=0;i<24;i++){
				timeStamps.add(format.format(addHours(startTime,i)));
			}
		}
		else if(timeType.equals("48h")){
			try {
				startTime = format2.parse(format2.format(addHours(endTime,-48)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			option.title("48小时数据", psName+"-"+piName);
			for(int i=0;i<48;i++){
				timeStamps.add(format.format(addHours(startTime,i)));
			}
		}
		if(startTime==null)
			return option;
		String factor = factorName;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		String hql = "from DataHourMain t where t.piCode = '"+piCode+"' and t.monitorTime >= :startTime and t.monitorTime <= :endTime";
		List<DataHourMain> listHourMain= hourMainDao.find(hql,params);
		
		for(int i=0;i<timeStamps.size();i++){
			map.put(timeStamps.get(i), null);
			for(DataHourMain m:listHourMain){
				if(compareDate(m.getMonitorTime(),timeStamps.get(i),"MM-dd HH")){
					String h = "from DataHour t where t.mainId = "+m.getId()+" and t.pollutantCode = '"+factorCode+"'";
					List<DataHour> listHour = hourDao.find(h);
					if(listHour!=null && listHour.size()>0){
						map.put(timeStamps.get(i), listHour.get(0).getAvg());
					}
					break;
				}
			}
		}
		
		option.legend().data(factor);
		option.calculable(true);
		CategoryAxis category = new CategoryAxis();
		category.boundaryGap(false);
		Line line1 = new Line(factor);
		for(int i=0;i<timeStamps.size();i++){
			category.data(timeStamps.get(i));
			if(map.get(timeStamps.get(i))!=null){
				line1.data(map.get(timeStamps.get(i)));
			}
			else{
				line1.data("");
			}
		}
		ValueAxis value = new ValueAxis();
		option.xAxis(category).yAxis(value);
		option.series(line1);
		
		return option;
	}
	@Override
	public List<Tree> psTree() {
		List<TPsPollutionSourceInfo> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		l = psDao.find("select distinct t from TPsPollutionSourceInfo t");

		if ((l != null) && (l.size() > 0)) {
			for (TPsPollutionSourceInfo r : l) {
				Tree tree = new Tree();
				tree.setId(r.getId().toString());
				tree.setText(r.getPsName());
				tree.setValue(r.getCode());
				lt.add(tree);
			}
		}
		return lt;
	}
	
	@Override
	public DataRealtimeMonitor get(Long id) {
		TDataRealtimeMonitor t = monitorDao.get(TDataRealtimeMonitor.class, id);
		DataRealtimeMonitor r = new DataRealtimeMonitor();
		BeanUtils.copyProperties(t, r);
		return r;
	}

	@Override
	public List<DataRealtimeMonitor> dataGrid(DataRealtimeMonitor demo, PageFilter ph) {
		List<DataRealtimeMonitor> ul = new ArrayList<DataRealtimeMonitor>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TDataRealtimeMonitor t where not exists(select 1 from TDataRealtimeMonitor b where b.piCode = t.piCode and b.dataTime > t.dataTime)";
		List<TDataRealtimeMonitor> l = monitorDao.find(hql + whereHql(demo, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		
		String hql1 = " from TPsPollutionSourceInfo t";
		List<TPsPollutionSourceInfo> l1 = psDao.find(hql1);
		Map<String, String> m1 = new HashMap<String, String>();
		for(int i =0;i<l1.size();i++){
			m1.put(l1.get(i).getCode(), l1.get(i).getPsName());
		}
		String hql2 = " from TPsPiPort t";
		List<TPsPiPort> l2 = portDao.find(hql2);
		Map<String, String> m2 = new HashMap<String, String>();
		for(int i =0;i<l2.size();i++){
			m2.put(l2.get(i).getPortCode(), l2.get(i).getPortName());
		}
		
		for (TDataRealtimeMonitor t : l) {
			DataRealtimeMonitor u = new DataRealtimeMonitor();
			BeanUtils.copyProperties(t, u);
			u.setCode(t.getPiCode());
			u.setPsCode(m1.get(t.getPsCode()));
			u.setPiCode(m2.get(t.getPiCode()));
			ul.add(u);
		}
		return ul;
	}

	@Override
	public Long count(DataRealtimeMonitor demo, PageFilter ph) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TDataRealtimeMonitor t ";
		return monitorDao.count("select count(*) " + hql + whereHql(demo, params), params);
	}
	
	private String whereHql(DataRealtimeMonitor demo, Map<String, Object> params) {
		String hql = "";
		if (demo != null) {
			//hql += " where 1=1 ";
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
