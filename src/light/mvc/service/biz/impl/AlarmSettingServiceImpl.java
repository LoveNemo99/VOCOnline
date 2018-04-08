package light.mvc.service.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.base.TScPollutantFactor;
import light.mvc.model.biz.TAlarmMode;
import light.mvc.model.biz.TAlarmSetting;
import light.mvc.model.sys.TAlarmOutlierDataType;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.biz.AlarmSetting;
import light.mvc.service.biz.AlarmSettingServiceI;

@Service
public class AlarmSettingServiceImpl implements AlarmSettingServiceI{

	@Autowired
	private BaseDaoI<TAlarmSetting> tDao;
//	@Autowired
//	private BaseDaoI<TAlarmMode> alarmModeDao;
	@Autowired
	private BaseDaoI<TScPollutantFactor> factorDao;
//	@Autowired
//	private BaseDaoI<TAlarmOutlierDataType> alarmTypeDao;
	
	@Override
	public List<Tree> factorTree() {
		List<TScPollutantFactor> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		l = factorDao.find("select distinct t from TScPollutantFactor t");
		if ((l != null) && (l.size() > 0)) {
			for (TScPollutantFactor r : l) {
				Tree tree = new Tree();
				tree.setId(r.getId().toString());
				tree.setText(r.getName());
				tree.setValue(r.getCode());
				lt.add(tree);
			}
		}
		return lt;
	}

	public Boolean factorSettingExists(String code){
		List<TScPollutantFactor> l = null;
		l = factorDao.find("select distinct t from TAlarmSetting t where t.factorCode = '"+code+"'");
		if(l!=null&&l.size()!=0){
			return true;
		}
		return false;
	}
	public String getFactorName(String code){
		List<TScPollutantFactor> l = null;
		l = factorDao.find("select distinct t from TScPollutantFactor t where t.code = '"+code+"'");
		if(l!=null&&l.size()!=0){
			return l.get(0).getName();
		}
		return null;
	}
	@Override
	public void add(AlarmSetting r) {
		TAlarmSetting t = new TAlarmSetting();
		BeanUtils.copyProperties(r, t);
		if(factorSettingExists(t.getFactorCode()))
			return;
		String factorName = getFactorName(t.getFactorCode());
		if(factorName!=null){
			t.setFactorName(factorName);
		}
		tDao.save(t);
	}
	@Override
	public void delete(Integer id) {
		TAlarmSetting t = tDao.get(TAlarmSetting.class, id);
		tDao.delete(t);
	}
	@Override
	public void edit(AlarmSetting r) {
		TAlarmSetting t = tDao.get(TAlarmSetting.class, r.getId());
		String code = t.getFactorCode();
		BeanUtils.copyProperties(r, t);
		t.setFactorCode(code);
		tDao.update(t);
	}
	@Override
	public AlarmSetting get(Integer id) {
		TAlarmSetting t = tDao.get(TAlarmSetting.class, id);
		AlarmSetting r= new AlarmSetting();
		BeanUtils.copyProperties(t, r);
		
		return r;
	}
	@Override
	public List<AlarmSetting> dataGrid(AlarmSetting info,PageFilter ph) {
		List<AlarmSetting> ul = new ArrayList<AlarmSetting>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TAlarmSetting t ";
		List<TAlarmSetting> l = tDao.find(hql + whereHql(info, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		
		String hql2 = " from TScPollutantFactor t";
		List<TScPollutantFactor> l2 = factorDao.find(hql2);
		Map<Integer, String> m2 = new HashMap<Integer, String>();
		for(int i =0;i<l2.size();i++){
			m2.put(l2.get(i).getId(), l2.get(i).getName());
		}
		
		for (TAlarmSetting t : l) {
			AlarmSetting u = new AlarmSetting();
			BeanUtils.copyProperties(t, u);
			ul.add(u);
		}
		return ul;
	}
	
	@Override
	public Long count(AlarmSetting demo, PageFilter ph){
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TAlarmSetting t ";
		return tDao.count("select count(*) " + hql + whereHql(demo, params), params);
	}
	
	private String whereHql(AlarmSetting demo, Map<String, Object> params) {
		String hql = "";
		if (demo != null) {
			hql += " where 1=1 ";
			/*if (demo.getName() != null) {
				hql += " and t.name like :name";
				params.put("name", "%%" + demo.get + "%%");
			}*/
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

}
