package light.mvc.service.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.biz.TAlarmMode;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.biz.AlarmMode;
import light.mvc.service.biz.AlarmModeServiceI;

@Service
public class AlarmModeServiceImpl implements AlarmModeServiceI{

	@Autowired
	private BaseDaoI<TAlarmMode> tDao;
	@Override
	public List<Tree> tree() {
		List<TAlarmMode> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		l = tDao.find("select distinct t from TAlarmMode t");

		if ((l != null) && (l.size() > 0)) {
			for (TAlarmMode r : l) {
				Tree tree = new Tree();
				tree.setId(r.getId().toString());
				tree.setText(r.getAlarmModeName());
				tree.setValue(r.getAlarmModeName());
				lt.add(tree);
			}
		}
		return lt;
	}
	@Override
	public void add(AlarmMode r) {
		TAlarmMode t = new TAlarmMode();
		BeanUtils.copyProperties(r, t);
		tDao.save(t);
	}
	@Override
	public void delete(Integer id) {
		TAlarmMode t = tDao.get(TAlarmMode.class, id);
		tDao.delete(t);
	}
	@Override
	public void edit(AlarmMode r) {
		TAlarmMode t = tDao.get(TAlarmMode.class, r.getId());
		BeanUtils.copyProperties(r, t);
		tDao.update(t);
	}
	@Override
	public AlarmMode get(Integer id) {
		TAlarmMode t = tDao.get(TAlarmMode.class, id);
		AlarmMode r= new AlarmMode();
		BeanUtils.copyProperties(t, r);
		return r;
	}
	@Override
	public List<AlarmMode> dataGrid(AlarmMode info,PageFilter ph) {
		List<AlarmMode> ul = new ArrayList<AlarmMode>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TAlarmMode t ";
		List<TAlarmMode> l = tDao.find(hql + whereHql(info, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		for (TAlarmMode t : l) {
			AlarmMode u = new AlarmMode();
			BeanUtils.copyProperties(t, u);
			ul.add(u);
		}
		return ul;
	}
	
	@Override
	public Long count(AlarmMode demo, PageFilter ph){
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TAlarmMode t ";
		return tDao.count("select count(*) " + hql + whereHql(demo, params), params);
	}
	
	private String whereHql(AlarmMode demo, Map<String, Object> params) {
		String hql = "";
		if (demo != null) {
			hql += " where 1=1 ";
			if (demo.getAlarmModeName() != null) {
				hql += " and t.alarmModeName like :name";
				params.put("name", "%%" + demo.getAlarmModeName() + "%%");
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

}
