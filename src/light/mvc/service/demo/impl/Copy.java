package light.mvc.service.demo.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import light.mvc.model.base.WaterPortInfo;
import light.mvc.model.data.YearDataAxias;
import light.mvc.model.sys.YearState;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.data.StatisticsData;

public class Copy {
//	public List<StatisticsData> dataGrid(StatisticsData data, PageFilter ph) {
//		//System.out.println(ph.getRows()+"|"+ph.getSort()+"|"+ph.getOrder()+"|"+ph.getPage());
//		List<YearState> listState = null;
//		List<WaterPortInfo> listPortInfo = null;
//		List<YearDataAxias> listData = null;
//		List<Integer> ids = new ArrayList<Integer>();
//		List<StatisticsData> listStatisticsData = new ArrayList<StatisticsData>();
//		String idsStr = "";
//
//		listState = yearStateDao.find("select distinct t from YearState t");
//		listPortInfo = waterPortDao.find("select distinct t from WaterPortInfo t");
//		if(listState==null)
//			return null;
//		if(listPortInfo==null)
//			return null;
//		
//		for(int i=0;i<listPortInfo.size();i++){
//			for(int j=0;j<listState.size();j++){
//				if(listState.get(j).getMN().equals(listPortInfo.get(i).getCode())){
//					ids.add(listState.get(j).getId());
//					break;
//				}
//			}
//		}
//		if(ids.size() == 0)
//			return null;
//		for(int i=0;i<ids.size();i++){
//			if(i == ids.size()-1){
//				idsStr = idsStr + ids.get(i).toString();
//			}
//			else{
//				idsStr = idsStr + ids.get(i).toString() + ",";
//			}
//		}
//		String hql = "from YearDataAxias t where t.xAxisId in ("+idsStr +")";
//		listData = yearDataDao.find(hql);
//		if(listData != null){
//			for(int i=0;i<listPortInfo.size();i++){
//				WaterPortInfo wp = listPortInfo.get(i);
//				for(int j=0;j<listState.size();j++){
//					
//					if(listState.get(j).getMN().equals(listPortInfo.get(i).getCode())){
//						YearState ys = listState.get(j);
//						StatisticsData stData = new StatisticsData();
//						for(int k=0;k<listData.size();k++){
//							if(listData.get(k).getxAxisId().intValue() == listState.get(j).getId().intValue()){
//								YearDataAxias yd = listData.get(k);
//								stData.setCode(ys.getMN());
//								stData.setIsOnline(wp.getIsOnline());
//								stData.setPollutionSourceId(wp.getPollutionSourceId());
//								stData.setPollutionSourceName(wp.getPollutionSourceName());
//								stData.setSewageTreatmentFactory(wp.getSewageTreatmentFactory());
//								stData.setTime(ys.getDatatime());
//								stData.setPh(ph);
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
//							}
//						}
//						if(stData.getPollutionSourceName()!=null){
//							listStatisticsData.add(stData);
//						}
//						break;
//					}
//				}
//			}
//		}
//		if(listStatisticsData!=null && listStatisticsData.size()>0){
//			if(ph!=null&&ph.getSort()!=null){
//				Collections.sort(listStatisticsData);
//			}
//		}
//		return listStatisticsData;
//	}
}
