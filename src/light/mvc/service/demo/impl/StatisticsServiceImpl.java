package light.mvc.service.demo.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.base.GasPortInfo;
import light.mvc.model.base.WaterPortInfo;
import light.mvc.model.data.DataAxis;
import light.mvc.model.data.DayDataAxias;
import light.mvc.model.data.DayXAxiasHistory;
import light.mvc.model.data.HourDataAxias;
import light.mvc.model.data.HourXAxiasHistory;
import light.mvc.model.data.MonthDataAxias;
import light.mvc.model.data.MonthXAxiasHistory;
import light.mvc.model.data.RealTimeDataAxis;
import light.mvc.model.data.YearDataAxias;
import light.mvc.model.data.YearXAxiasHistory;
import light.mvc.model.sys.DayState;
import light.mvc.model.sys.HourState;
import light.mvc.model.sys.MonthState;
import light.mvc.model.sys.RealTimeState;
import light.mvc.model.sys.XAxis;
import light.mvc.model.sys.YearState;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.data.StatisticsData;
import light.mvc.pageModel.data.StatisticsDataGas;
import light.mvc.service.demo.StatisticsServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl implements StatisticsServiceI {

	@Autowired
	private BaseDaoI<YearDataAxias> yearDataDao;
	@Autowired
	private BaseDaoI<MonthDataAxias> monthDataDao;
	@Autowired
	private BaseDaoI<DayDataAxias> dayDataDao;
	@Autowired
	private BaseDaoI<HourDataAxias> hourDataDao;
	@Autowired
	private BaseDaoI<RealTimeDataAxis> realTimeDataDao;
	
	@Autowired
	private BaseDaoI<YearState> yearStateDao;
	@Autowired
	private BaseDaoI<MonthState> monthStateDao;
	@Autowired
	private BaseDaoI<DayState> dayStateDao;
	@Autowired
	private BaseDaoI<HourState> hourStateDao;
	@Autowired
	private BaseDaoI<RealTimeState> realTimeStateDao;
	
	@Autowired
	private BaseDaoI<YearXAxiasHistory> yearHistoryDao;
	@Autowired
	private BaseDaoI<MonthXAxiasHistory> monthHistoryDao;
	@Autowired
	private BaseDaoI<DayXAxiasHistory> dayHistoryDao;
	@Autowired
	private BaseDaoI<HourXAxiasHistory> hourHistoryDao;
	
	@Autowired
	private BaseDaoI<WaterPortInfo> waterPortDao;
	@Autowired
	private BaseDaoI<GasPortInfo> gasPortDao;
	
	@Override
	public List<StatisticsData> dataGrid(StatisticsData data, PageFilter ph,String tType) {
		if(data!=null && data.getPollutionSourceName()!=null && data.getPollutionSourceName().length()>0){
			return dataGridByForm(data,ph,tType);
		}
		List l1 = null;
		List l2 = null;
		List<XAxis> listState = null;
		List<WaterPortInfo> listPortInfo = null;
		List<DataAxis> listData = null;
		List<Integer> ids = new ArrayList<Integer>();
		List<StatisticsData> listStatisticsData = new ArrayList<StatisticsData>();
		String idsStr = "";

		if(tType.equals("year")){l1 = yearStateDao.find("select distinct t from YearState t");}
		if(tType.equals("month")){l1 = monthStateDao.find("select distinct t from MonthState t");}
		if(tType.equals("day")){l1 = dayStateDao.find("select distinct t from DayState t");}
		if(tType.equals("hour")){l1 = hourStateDao.find("select distinct t from HourState t");}
		listState = l1;
		listPortInfo = waterPortDao.find("select distinct t from WaterPortInfo t");
		if(listState==null)
			return null;
		if(listPortInfo==null)
			return null;
		
		for(int i=0;i<listPortInfo.size();i++){
			for(int j=0;j<listState.size();j++){
				if(listState.get(j).getMN().equals(listPortInfo.get(i).getCode())){
					ids.add(listState.get(j).getId());
					break;
				}
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
		String hql = null;
		if(tType.equals("year")){hql = "from YearDataAxias t where t.xAxisId in ("+idsStr +")";
		l2 = yearDataDao.find(hql);}
		if(tType.equals("month")){hql = "from MonthDataAxias t where t.xAxisId in ("+idsStr +")";
		l2 = monthDataDao.find(hql);}
		if(tType.equals("day")){hql = "from DayDataAxias t where t.xAxisId in ("+idsStr +")";
		l2 = dayDataDao.find(hql);}
		if(tType.equals("hour")){hql = "from HourDataAxias t where t.xAxisId in ("+idsStr +")";
		l2 = hourDataDao.find(hql);}
		
		if(hql==null)
			return null;
		
		listData = l2;
		if(listData != null){
			for(int i=0;i<listPortInfo.size();i++){
				WaterPortInfo wp = listPortInfo.get(i);
				for(int j=0;j<listState.size();j++){
					
					if(listState.get(j).getMN().equals(listPortInfo.get(i).getCode())){
						XAxis ys = listState.get(j);
						StatisticsData stData = new StatisticsData();
						for(int k=0;k<listData.size();k++){
							if(listData.get(k).getxAxisId().intValue() == listState.get(j).getId().intValue()){
								DataAxis yd = listData.get(k);
								stData.setCode(ys.getMN());
								stData.setIsOnline(wp.getIsOnline());
								//stData.setPollutionSourceId(wp.getPollutionSourceId());
								stData.setPollutionSourceName(wp.getPollutionSourceName());
								stData.setSewageTreatmentFactory(wp.getSewageTreatmentFactory());
								stData.setTime(ys.getDatatime());
								stData.setPh(ph);
//								if(yd.getPollutant().equals("B01")){
//									stData.setAvgB01(yd.getAvg());
//									stData.setCouB01(yd.getCou());
//								}
//								if(yd.getPollutant().equals("101")){
//									stData.setAvg101(yd.getAvg());
//									stData.setCou101(yd.getCou());
//								}
//								if(yd.getPollutant().equals("060")){
//									stData.setAvg060(yd.getAvg());
//									stData.setCou060(yd.getCou());
//								}
//								if(yd.getPollutant().equals("011")){
//									stData.setAvg011(yd.getAvg());
//									stData.setCou011(yd.getCou());
//								}
//								if(yd.getPollutant().equals("463")){
//									stData.setAvg463(yd.getAvg());
//									stData.setCou463(yd.getCou());
//								}
							}
						}
						if(stData.getPollutionSourceName()!=null){
							listStatisticsData.add(stData);
						}
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
	
	public List<StatisticsData> dataGridByForm(StatisticsData data, PageFilter ph,String tType) {
		if(data==null || data.getPollutionSourceName()==null)
			return null;
		List l1 = null;
		List l2 = null;
		List<XAxis> listState = null;
		List<WaterPortInfo> listPortInfo = null;
		List<WaterPortInfo> portInfo = new ArrayList<WaterPortInfo>();
		List<DataAxis> listData = null;
		List<Integer> ids = new ArrayList<Integer>();
		List<StatisticsData> listStatisticsData = new ArrayList<StatisticsData>();
		String idsStr = "";
		Map<String, Object> params = new HashMap<String, Object>();

		if(tType.equals("year")){l1 = yearHistoryDao.find("from YearXAxiasHistory t"+whereHql(data,params)+" order by t.datatime desc",params);}
		if(tType.equals("month")){l1 = monthHistoryDao.find("from MonthXAxiasHistory t"+whereHql(data,params)+" order by t.datatime desc",params);}
		if(tType.equals("day")){l1 = dayHistoryDao.find("from DayXAxiasHistory t"+whereHql(data,params)+" order by t.datatime desc",params);}
		if(tType.equals("hour")){l1 = hourHistoryDao.find("from HourXAxiasHistory t"+whereHql(data,params)+" order by t.datatime desc",params);}
		listState = l1;
		System.out.println("l1 size:"+l1.size());
		listPortInfo = waterPortDao.find("select distinct t from WaterPortInfo t");
		if(listState==null)
			return null;
		if(listPortInfo==null)
			return null;
		
		for(int i=0;i<listPortInfo.size();i++){
			for(int j=0;j<listState.size();j++){
				if(listState.get(j).getMN().equals(listPortInfo.get(i).getCode())){
					ids.add(listState.get(j).getId());
				}
			}
		}
		for(int i=0;i<listPortInfo.size();i++){
			if(listPortInfo.get(i).getPollutionSourceName()!=null && listPortInfo.get(i).getPollutionSourceName().equals(data.getPollutionSourceName())){
				portInfo.add(listPortInfo.get(i));
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
		System.out.println("ids:"+idsStr);
		String hql = null;
		if(tType.equals("year")){hql = "from YearDataAxias t where t.xAxisId in ("+idsStr +")";
		l2 = yearDataDao.find(hql);}
		if(tType.equals("month")){hql = "from MonthDataAxias t where t.xAxisId in ("+idsStr +")";
		l2 = monthDataDao.find(hql);}
		if(tType.equals("day")){hql = "from DayDataAxias t where t.xAxisId in ("+idsStr +")";
		l2 = dayDataDao.find(hql);}
		if(tType.equals("hour")){hql = "from HourDataAxias t where t.xAxisId in ("+idsStr +")";
		l2 = hourDataDao.find(hql);}
		
		if(hql==null)
			return null;
		
		listData = l2;
		if(listData != null){
			WaterPortInfo wp = portInfo.get(0);
			for(int j=0;j<listState.size();j++){	
				//if(listState.get(j).getMN().equals(wp.getCode())){
					XAxis ys = listState.get(j);
					StatisticsData stData = new StatisticsData();
					for(int k=0;k<listData.size();k++){
						if(listData.get(k).getxAxisId().intValue() == listState.get(j).getId().intValue()){
							DataAxis yd = listData.get(k);
							stData.setCode(ys.getMN());
							stData.setIsOnline(wp.getIsOnline());
							//stData.setPollutionSourceId(wp.getPollutionSourceId());
							stData.setPollutionSourceName(wp.getPollutionSourceName());
							stData.setSewageTreatmentFactory(wp.getSewageTreatmentFactory());
							stData.setTime(ys.getDatatime());
							stData.setPh(ph);
//							if(yd.getPollutant().equals("B01")){
//								stData.setAvgB01(yd.getAvg());
//								stData.setCouB01(yd.getCou());
//							}
//							if(yd.getPollutant().equals("101")){
//								stData.setAvg101(yd.getAvg());
//								stData.setCou101(yd.getCou());
//							}
//							if(yd.getPollutant().equals("060")){
//								stData.setAvg060(yd.getAvg());
//								stData.setCou060(yd.getCou());
//							}
//							if(yd.getPollutant().equals("011")){
//								stData.setAvg011(yd.getAvg());
//								stData.setCou011(yd.getCou());
//							}
//							if(yd.getPollutant().equals("463")){
//								stData.setAvg463(yd.getAvg());
//								stData.setCou463(yd.getCou());
//							}
						}
					}
					if(stData.getPollutionSourceName()!=null){
						if(listStatisticsData.size()<500){
							listStatisticsData.add(stData);
						}
						else{
							break;
						}
					}
				//}
			}
		}
		if(listStatisticsData!=null && listStatisticsData.size()>0){
			if(ph!=null&&ph.getSort()!=null){
				Collections.sort(listStatisticsData);
			}
		}
		return listStatisticsData;
	}
	
	@Override
	public List<StatisticsData> dataGridRealTime(StatisticsData data, PageFilter ph) {
		List l1 = null;
		List l2 = null;
		List<XAxis> listState = null;
		List<WaterPortInfo> listPortInfo = null;
		List<RealTimeDataAxis> listData = null;
		List<Integer> ids = new ArrayList<Integer>();
		List<StatisticsData> listStatisticsData = new ArrayList<StatisticsData>();
		String idsStr = "";

		l1 = realTimeStateDao.find("select distinct t from RealTimeState t");
		listState = l1;
		listPortInfo = waterPortDao.find("select distinct t from WaterPortInfo t");
		if(listState==null)
			return null;
		if(listPortInfo==null)
			return null;
		
		for(int i=0;i<listPortInfo.size();i++){
			for(int j=0;j<listState.size();j++){
				if(listState.get(j).getMN().equals(listPortInfo.get(i).getCode())){
					ids.add(listState.get(j).getId());
					break;
				}
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
		String hql = null;
		hql = "from RealTimeDataAxis t where t.xAxisId in ("+idsStr +")";
		l2 = realTimeDataDao.find(hql);
		
		listData = l2;
		if(listData != null){
			for(int i=0;i<listPortInfo.size();i++){
				WaterPortInfo wp = listPortInfo.get(i);
				for(int j=0;j<listState.size();j++){
					
					if(listState.get(j).getMN().equals(listPortInfo.get(i).getCode())){
						XAxis ys = listState.get(j);
						StatisticsData stData = new StatisticsData();
						for(int k=0;k<listData.size();k++){
							if(listData.get(k).getxAxisId().intValue() == listState.get(j).getId().intValue()){
								RealTimeDataAxis yd = listData.get(k);
								stData.setCode(ys.getMN());
								stData.setIsOnline(wp.getIsOnline());
								//stData.setPollutionSourceId(wp.getPollutionSourceId());
								stData.setPollutionSourceName(wp.getPollutionSourceName());
								stData.setSewageTreatmentFactory(wp.getSewageTreatmentFactory());
								stData.setTime(ys.getDatatime());
								stData.setPh(ph);
//								if(yd.getPollutant().equals("B01")){
//									stData.setAvgB01(yd.getRtd());
//								}
//								if(yd.getPollutant().equals("101")){
//									stData.setAvg101(yd.getRtd());
//								}
//								if(yd.getPollutant().equals("060")){
//									stData.setAvg060(yd.getRtd());
//								}
//								if(yd.getPollutant().equals("011")){
//									stData.setAvg011(yd.getRtd());
//								}
//								if(yd.getPollutant().equals("463")){
//									stData.setAvg463(yd.getRtd());
//								}
//								if(yd.getPollutant().equals("001")){
//									stData.setAvg001(yd.getRtd());
//								}
							}
						}
						if(stData.getPollutionSourceName()!=null){
							listStatisticsData.add(stData);
						}
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
	@Override
	public List<StatisticsDataGas> dataGridGas(StatisticsDataGas data, PageFilter ph,String tType) {
		if(data!=null && data.getPollutionSourceName()!=null && data.getPollutionSourceName().length()>0){
			return dataGridGasByForm(data,ph,tType);
		}
		List l1 = null;
		List l2 = null;
		List<XAxis> listState = null;
		List<GasPortInfo> listPortInfo = null;
		List<DataAxis> listData = null;
		List<Integer> ids = new ArrayList<Integer>();
		List<StatisticsDataGas> listStatisticsData = new ArrayList<StatisticsDataGas>();
		String idsStr = "";

		if(tType.equals("year")){l1 = yearStateDao.find("select distinct t from YearState t");}
		if(tType.equals("month")){l1 = monthStateDao.find("select distinct t from MonthState t");}
		if(tType.equals("day")){l1 = dayStateDao.find("select distinct t from DayState t");}
		if(tType.equals("hour")){l1 = hourStateDao.find("select distinct t from HourState t");}
		listState = l1;
		listPortInfo = gasPortDao.find("select distinct t from GasPortInfo t");
		if(listState==null)
			return null;
		if(listPortInfo==null)
			return null;
		
		for(int i=0;i<listPortInfo.size();i++){
			for(int j=0;j<listState.size();j++){
				if(listState.get(j).getMN().equals(listPortInfo.get(i).getCode())){
					ids.add(listState.get(j).getId());
					break;
				}
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
		String hql = null;
		if(tType.equals("year")){hql = "from YearDataAxias t where t.xAxisId in ("+idsStr +")";
		l2 = yearDataDao.find(hql);}
		if(tType.equals("month")){hql = "from MonthDataAxias t where t.xAxisId in ("+idsStr +")";
		l2 = monthDataDao.find(hql);}
		if(tType.equals("day")){hql = "from DayDataAxias t where t.xAxisId in ("+idsStr +")";
		l2 = dayDataDao.find(hql);}
		if(tType.equals("hour")){hql = "from HourDataAxias t where t.xAxisId in ("+idsStr +")";
		l2 = hourDataDao.find(hql);}
		
		if(hql==null)
			return null;
		
		listData = l2;
		if(listData != null){
			for(int i=0;i<listPortInfo.size();i++){
				GasPortInfo wp = listPortInfo.get(i);
				for(int j=0;j<listState.size();j++){
					
					if(listState.get(j).getMN().equals(listPortInfo.get(i).getCode())){
						XAxis ys = listState.get(j);
						StatisticsDataGas stData = new StatisticsDataGas();
						for(int k=0;k<listData.size();k++){
							if(listData.get(k).getxAxisId().intValue() == listState.get(j).getId().intValue()){
								DataAxis yd = listData.get(k);
								stData.setCode(ys.getMN());
								stData.setIsOnline(wp.getIsOnline());
								stData.setPollutionSourceId(wp.getPollutionSourceId());
								stData.setPollutionSourceName(wp.getPollutionSourceName());
								stData.setSewageTreatmentFactory(wp.getSewageTreatmentFactory());
								stData.setTime(ys.getDatatime());
								stData.setPh(ph);
								if(yd.getPollutant().equals("B02")){
									stData.setAvgB02(yd.getAvg());
									stData.setCouB02(yd.getCou());
								}
								if(yd.getPollutant().equals("S01")){
									stData.setAvgS01(yd.getAvg());
									stData.setCouS01(yd.getCou());
								}
								if(yd.getPollutant().equals("S03")){
									stData.setAvgS03(yd.getAvg());
									stData.setCouS03(yd.getCou());
								}
								if(yd.getPollutant().equals("01")){
									stData.setAvg01(yd.getAvg());
									stData.setCou01(yd.getCou());
								}
								if(yd.getPollutant().equals("02")){
									stData.setAvg02(yd.getAvg());
									stData.setCou02(yd.getCou());
								}
								if(yd.getPollutant().equals("03")){
									stData.setAvg03(yd.getAvg());
									stData.setCou03(yd.getCou());
								}
							}
						}
						if(stData.getPollutionSourceName()!=null){
							listStatisticsData.add(stData);
						}
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
	
	public List<StatisticsDataGas> dataGridGasByForm(StatisticsDataGas data, PageFilter ph,String tType) {
		if(data==null || data.getPollutionSourceName()==null)
			return null;
		List l1 = null;
		List l2 = null;
		List<XAxis> listState = null;
		List<GasPortInfo> listPortInfo = null;
		List<GasPortInfo> portInfo = new ArrayList<GasPortInfo>();
		List<DataAxis> listData = null;
		List<Integer> ids = new ArrayList<Integer>();
		List<StatisticsDataGas> listStatisticsData = new ArrayList<StatisticsDataGas>();
		String idsStr = "";
		Map<String, Object> params = new HashMap<String, Object>();

		if(tType.equals("year")){l1 = yearHistoryDao.find("from YearXAxiasHistory t"+whereHql(data,params)+" order by t.datatime desc",params);}
		if(tType.equals("month")){l1 = monthHistoryDao.find("from MonthXAxiasHistory t"+whereHql(data,params)+" order by t.datatime desc",params);}
		if(tType.equals("day")){l1 = dayHistoryDao.find("from DayXAxiasHistory t"+whereHql(data,params)+" order by t.datatime desc",params);}
		if(tType.equals("hour")){l1 = hourHistoryDao.find("from HourXAxiasHistory t"+whereHql(data,params)+" order by t.datatime desc",params);}
		listState = l1;
		System.out.println("l1 size:"+l1.size());
		listPortInfo = gasPortDao.find("select distinct t from GasPortInfo t");
		if(listState==null)
			return null;
		if(listPortInfo==null)
			return null;
		
		for(int i=0;i<listPortInfo.size();i++){
			for(int j=0;j<listState.size();j++){
				if(listState.get(j).getMN().equals(listPortInfo.get(i).getCode())){
					ids.add(listState.get(j).getId());
				}
			}
		}
		for(int i=0;i<listPortInfo.size();i++){
			if(listPortInfo.get(i).getPollutionSourceName()!=null && listPortInfo.get(i).getPollutionSourceName().equals(data.getPollutionSourceName())){
				portInfo.add(listPortInfo.get(i));
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
		System.out.println("ids:"+idsStr);
		String hql = null;
		if(tType.equals("year")){hql = "from YearDataAxias t where t.xAxisId in ("+idsStr +")";
		l2 = yearDataDao.find(hql);}
		if(tType.equals("month")){hql = "from MonthDataAxias t where t.xAxisId in ("+idsStr +")";
		l2 = monthDataDao.find(hql);}
		if(tType.equals("day")){hql = "from DayDataAxias t where t.xAxisId in ("+idsStr +")";
		l2 = dayDataDao.find(hql);}
		if(tType.equals("hour")){hql = "from HourDataAxias t where t.xAxisId in ("+idsStr +")";
		l2 = hourDataDao.find(hql);}
		
		if(hql==null)
			return null;
		
		listData = l2;
		if(listData != null){
			GasPortInfo wp = portInfo.get(0);
			for(int j=0;j<listState.size();j++){	
				//if(listState.get(j).getMN().equals(wp.getCode())){
					XAxis ys = listState.get(j);
					StatisticsDataGas stData = new StatisticsDataGas();
					for(int k=0;k<listData.size();k++){
						if(listData.get(k).getxAxisId().intValue() == listState.get(j).getId().intValue()){
							DataAxis yd = listData.get(k);
							stData.setCode(ys.getMN());
							stData.setIsOnline(wp.getIsOnline());
							stData.setPollutionSourceId(wp.getPollutionSourceId());
							stData.setPollutionSourceName(wp.getPollutionSourceName());
							stData.setSewageTreatmentFactory(wp.getSewageTreatmentFactory());
							stData.setTime(ys.getDatatime());
							stData.setPh(ph);
							if(yd.getPollutant().equals("01")){
								stData.setAvg01(yd.getAvg());
								stData.setCou01(yd.getCou());
							}
							if(yd.getPollutant().equals("02")){
								stData.setAvg02(yd.getAvg());
								stData.setCou02(yd.getCou());
							}
							if(yd.getPollutant().equals("03")){
								stData.setAvg03(yd.getAvg());
								stData.setCou03(yd.getCou());
							}
							if(yd.getPollutant().equals("B02")){
								stData.setAvgB02(yd.getAvg());
								stData.setCouB02(yd.getCou());
							}
							if(yd.getPollutant().equals("S01")){
								stData.setAvgS01(yd.getAvg());
								stData.setCouS01(yd.getCou());
							}
							if(yd.getPollutant().equals("S03")){
								stData.setAvgS03(yd.getAvg());
								stData.setCouS03(yd.getCou());
							}
						}
					}
					if(stData.getPollutionSourceName()!=null){
						if(listStatisticsData.size()<500){
							listStatisticsData.add(stData);
						}
						else{
							break;
						}
					}
				//}
			}
		}
		if(listStatisticsData!=null && listStatisticsData.size()>0){
			if(ph!=null&&ph.getSort()!=null){
				Collections.sort(listStatisticsData);
			}
		}
		return listStatisticsData;
	}
	
	@Override
	public List<StatisticsDataGas> dataGridRealTimeGas(StatisticsDataGas data, PageFilter ph) {
		List l1 = null;
		List l2 = null;
		List<XAxis> listState = null;
		List<GasPortInfo> listPortInfo = null;
		List<RealTimeDataAxis> listData = null;
		List<Integer> ids = new ArrayList<Integer>();
		List<StatisticsDataGas> listStatisticsData = new ArrayList<StatisticsDataGas>();
		String idsStr = "";

		l1 = realTimeStateDao.find("select distinct t from RealTimeState t");
		listState = l1;
		listPortInfo = gasPortDao.find("select distinct t from GasPortInfo t");
		if(listState==null)
			return null;
		if(listPortInfo==null)
			return null;
		
		for(int i=0;i<listPortInfo.size();i++){
			for(int j=0;j<listState.size();j++){
				if(listState.get(j).getMN().equals(listPortInfo.get(i).getCode())){
					ids.add(listState.get(j).getId());
					break;
				}
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
		String hql = null;
		hql = "from RealTimeDataAxis t where t.xAxisId in ("+idsStr +")";
		l2 = realTimeDataDao.find(hql);
		
		listData = l2;
		if(listData != null){
			for(int i=0;i<listPortInfo.size();i++){
				GasPortInfo wp = listPortInfo.get(i);
				for(int j=0;j<listState.size();j++){
					
					if(listState.get(j).getMN().equals(listPortInfo.get(i).getCode())){
						XAxis ys = listState.get(j);
						StatisticsDataGas stData = new StatisticsDataGas();
						for(int k=0;k<listData.size();k++){
							if(listData.get(k).getxAxisId().intValue() == listState.get(j).getId().intValue()){
								RealTimeDataAxis yd = listData.get(k);
								stData.setCode(ys.getMN());
								stData.setIsOnline(wp.getIsOnline());
								stData.setPollutionSourceId(wp.getPollutionSourceId());
								stData.setPollutionSourceName(wp.getPollutionSourceName());
								stData.setSewageTreatmentFactory(wp.getSewageTreatmentFactory());
								stData.setTime(ys.getDatatime());
								stData.setPh(ph);
								if(yd.getPollutant().equals("01")){
									stData.setAvg01(yd.getRtd());
								}
								if(yd.getPollutant().equals("02")){
									stData.setAvg02(yd.getRtd());
								}
								if(yd.getPollutant().equals("03")){
									stData.setAvg03(yd.getRtd());
								}
								if(yd.getPollutant().equals("B02")){
									stData.setAvgB02(yd.getRtd());
								}
								if(yd.getPollutant().equals("S01")){
									stData.setAvgS01(yd.getRtd());
								}
								if(yd.getPollutant().equals("S03")){
									stData.setAvgS03(yd.getRtd());
								}
							}
						}
						if(stData.getPollutionSourceName()!=null){
							listStatisticsData.add(stData);
						}
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
	
	@Override
	public List<StatisticsData> dataGrid(String type){
		List<YearState> listYearState = null;
		List<WaterPortInfo> listWaterPortInfo = null;
		List<YearDataAxias> listYearData = null;
		List<Integer> ids = new ArrayList<Integer>();
		List<StatisticsData> listStatisticsData = new ArrayList<StatisticsData>();
		String idsStr = "";

		listYearState = yearStateDao.find("select distinct t from YearState t");
		listWaterPortInfo = waterPortDao.find("select distinct t from WaterPortInfo t");
		//System.out.println(listYearState.size()+"||||"+listWaterPortInfo.size());
		if(listYearState==null)
			return null;
		if(listWaterPortInfo==null)
			return null;
		
		for(int i=0;i<listWaterPortInfo.size();i++){
			for(int j=0;j<listYearState.size();j++){
				if(listYearState.get(j).getMN().equals(listWaterPortInfo.get(i).getCode())){
					ids.add(listYearState.get(j).getId());
					break;
				}
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
		String hql = "from YearDataAxias t where t.xAxisId in ("+idsStr +") and t.pollutant = '"+type+"' "+"order by t.cou desc";
		listYearData = yearDataDao.find(hql);
		if(listYearData != null){
			for(int k=0;k<listYearData.size();k++){
				for(int i=0;i<listWaterPortInfo.size();i++){
					WaterPortInfo wp = listWaterPortInfo.get(i);
					for(int j=0;j<listYearState.size();j++){
						
						if(listYearState.get(j).getMN().equals(listWaterPortInfo.get(i).getCode())){
							YearState ys = listYearState.get(j);
							StatisticsData stData = new StatisticsData();
							
							if(listYearData.get(k).getxAxisId().intValue() == listYearState.get(j).getId().intValue()){
								YearDataAxias yd = listYearData.get(k);
								stData.setCode(ys.getMN());
								stData.setIsOnline(wp.getIsOnline());
								//stData.setPollutionSourceId(wp.getPollutionSourceId());
								stData.setPollutionSourceName(wp.getPollutionSourceName());
								stData.setSewageTreatmentFactory(wp.getSewageTreatmentFactory());
//
//								stData.setAvgB01(yd.getAvg());
//  								stData.setCouB01(yd.getCou());
								
							}
							if(stData.getPollutionSourceName()!=null){
								listStatisticsData.add(stData);
							}
							break;
						}
					}
				}
			}
		}
		return listStatisticsData;
	}
	
	@Override
	public List<Tree> gasTree() {
		List<GasPortInfo> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		l = gasPortDao.find("select distinct t from GasPortInfo t");

		if ((l != null) && (l.size() > 0)) {
			for (GasPortInfo r : l) {
				Tree tree = new Tree();
				tree.setId(r.getPollutionSourceId().toString());
				tree.setText(r.getPollutionSourceName());
				//tree.setIconCls("icon-company");
				lt.add(tree);
			}
		}
		return lt;
	}

	private String getMN(String company){
		List<WaterPortInfo> l = waterPortDao.find("from WaterPortInfo t where t.pollutionSourceName = '"+company+"'");
		if(l!=null && l.size()>0)
			return l.get(0).getCode();
		return null;
	}
	
	private String getGasMN(String company){
		List<GasPortInfo> l = gasPortDao.find("from GasPortInfo t where t.pollutionSourceName = '"+company+"'");
		if(l!=null && l.size()>0)
			return l.get(0).getCode();
		return null;
	}
	
	private String whereHql(StatisticsData demo, Map<String, Object> params) {
		String hql = "";
		if (demo != null) {
			hql += " where 1=1 ";
			if (demo.getPollutionSourceName() != null) {
				String mn = getMN(demo.getPollutionSourceName());
				if(mn != null){
					hql += " and t.MN like :type";
					params.put("type", "%%" + mn + "%%");
					//System.out.println("start:"+demo.getStartTime()+"|end:"+demo.getEndTime());
					if (demo.getStartTime() != null) {
						hql += " and t.datatime >= :createdatetimeStart";
						params.put("createdatetimeStart", demo.getStartTime());
					}
					if (demo.getEndTime() != null) {
						hql += " and t.datatime <= :createdatetimeEnd";
						params.put("createdatetimeEnd", demo.getEndTime());
					}
				}
			}
			
		}
		return hql;
	}
	
	private String whereHql(StatisticsDataGas demo, Map<String, Object> params) {
		String hql = "";
		if (demo != null) {
			hql += " where 1=1 ";
			if (demo.getPollutionSourceName() != null) {
				String mn = getGasMN(demo.getPollutionSourceName());
				if(mn != null){
					hql += " and t.MN like :type";
					params.put("type", "%%" + mn + "%%");
					//System.out.println("start:"+demo.getStartTime()+"|end:"+demo.getEndTime());
					if (demo.getStartTime() != null) {
						hql += " and t.datatime >= :createdatetimeStart";
						params.put("createdatetimeStart", demo.getStartTime());
					}
					if (demo.getEndTime() != null) {
						hql += " and t.datatime <= :createdatetimeEnd";
						params.put("createdatetimeEnd", demo.getEndTime());
					}
				}
			}
			
		}
		return hql;
	}

	private String orderHql(PageFilter ph) {
		String orderString = "";
		if ((ph.getSort() != null) && (ph.getOrder() != null)) {
			orderString = " order by t." + ph.getSort() + " " + ph.getOrder();
		}else if(ph.getOrder() != null){
			orderString = " order by t.id" + " " + ph.getOrder();
		}
		return orderString;
	}

	@Override
	public Long count(StatisticsData data, PageFilter ph) {
		// TODO Auto-generated method stub
		return null;
	}

}
