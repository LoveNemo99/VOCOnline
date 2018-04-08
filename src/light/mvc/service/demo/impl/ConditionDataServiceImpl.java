package light.mvc.service.demo.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.base.TPsPiPort;
import light.mvc.model.base.TPsPollutionSourceInfo;
import light.mvc.model.data.WorkingCondition;
import light.mvc.model.data.WorkingConditionMain;
import light.mvc.model.data.WorkingConditionMainLatest;
import light.mvc.pageModel.data.ConditionData;
import light.mvc.pageModel.data.StatisticsData;
import light.mvc.service.demo.ConditionDataServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConditionDataServiceImpl implements ConditionDataServiceI{
	@Autowired
	private BaseDaoI<TPsPiPort> portDao;
	@Autowired
	private BaseDaoI<TPsPollutionSourceInfo> psDao;
	
	@Autowired
	private BaseDaoI<WorkingConditionMain> conditionMainDao;
	@Autowired
	private BaseDaoI<WorkingConditionMainLatest> conditionMainLatestDao;
	@Autowired
	private BaseDaoI<WorkingCondition> conditionDao;
	
	@Override
	public List<ConditionData> RealtimeCondition(){
		List<ConditionData> conditionDataList = new ArrayList<ConditionData>();
		List<String> conditionNames = getConditionName();
		if(conditionNames == null)
			return null;
		String hql = "from WorkingConditionMainLatest ";
		List<WorkingConditionMainLatest> listMain = conditionMainLatestDao.find(hql);
		Map<String,String> portMap = getPiMap();
		Map<String,String> psMap = getPsMap();
		
		List<BigInteger> ids = new ArrayList<BigInteger>();
		for(int i=0;i<listMain.size();i++){
			ConditionData data = new ConditionData();
			WorkingConditionMainLatest m = listMain.get(i);
			data.setDataMainId(m.getId());
			data.setTime(m.getTime());
			data.setMn(m.getMn());
			data.setPort(portMap.get(m.getPiCode()));
			data.setPsName(psMap.get(m.getPsCode()));
			ids.add(m.getId());
			conditionDataList.add(data);
		}
		if(ids.size() == 0)
			return null;
		String idsStr = "";
		for(int i=0;i<ids.size();i++){
			if(i == ids.size()-1){
				idsStr = idsStr + ids.get(i).toString();
			}
			else{
				idsStr = idsStr + ids.get(i).toString() + ",";
			}
		}
		for(int i=0;i<conditionNames.size();i++){
			String hqldata = "from WorkingCondition t where t.mainId in ("+idsStr +") "+"and t.conditionName = '"+conditionNames.get(i)+"'";
			List<WorkingCondition> listData = conditionDao.find(hqldata);
			if(listData == null){
				System.out.println("nulllll");
			}
			else{
				for(int j =0;j<conditionDataList.size();j++){
					for(int k=0;k<listData.size();k++){
						System.out.println(listData.get(k).getMainId());
						if(listData.get(k).getMainId().equals(conditionDataList.get(j).getDataMainId())){
							fieldSet2(conditionDataList.get(j),i,listData.get(k).getCondition());
							break;
						}
					}
				}
			}
		}
		
		return conditionDataList;
	}
	public void fieldSet2(ConditionData d,Integer i,String f){
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
	@Override
	public List<String> getConditionName(){
		String hql = "select distinct conditionName from WorkingCondition";
		List l= conditionDao.find(hql);
		
		List<String> names = new ArrayList<String>();
		for(int i=0;i<l.size();i++){
			names.add((String) l.get(i));
		}
		if(names.size()==0)
			return null;
		return names;
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
	public Map<String, String> getPiMap(){
		String hql2 = " from TPsPiPort t";
		List<TPsPiPort> psl = portDao.find(hql2);//获取排口信息
		Map<String, String> m = new HashMap<String, String>();//排口code-名称map
		for(int i =0;i<psl.size();i++){
			m.put(psl.get(i).getPortCode(), psl.get(i).getPortName());
		}
		return m;
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
}
