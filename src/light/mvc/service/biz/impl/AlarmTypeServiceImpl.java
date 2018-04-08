package light.mvc.service.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.sys.TAlarmType;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.sys.AlarmType;
import light.mvc.service.biz.AlarmTypeServiceI;

@Service
public class AlarmTypeServiceImpl implements AlarmTypeServiceI{

	@Autowired
	private BaseDaoI<TAlarmType> tDao;
	@Override
	public List<Tree> tree() {
		List<TAlarmType> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		l = tDao.find("select distinct t from TAlarmType t");

		if ((l != null) && (l.size() > 0)) {
			for (TAlarmType r : l) {
				Tree tree = new Tree();
				tree.setId(r.getId().toString());
				tree.setText(r.getType());
				tree.setValue(r.getCode());
				lt.add(tree);
			}
		}
		return lt;
	}
	@Override
	public void add(AlarmType r) {
		TAlarmType t = new TAlarmType();
		BeanUtils.copyProperties(r, t);
		tDao.save(t);
	}
	@Override
	public void delete(Integer id) {
		TAlarmType t = tDao.get(TAlarmType.class, id);
		tDao.delete(t);
	}
	@Override
	public void edit(AlarmType r) {
		TAlarmType t = tDao.get(TAlarmType.class, r.getId());
		BeanUtils.copyProperties(r, t);
		tDao.update(t);
	}
	@Override
	public AlarmType get(Integer id) {
		TAlarmType t = tDao.get(TAlarmType.class, id);
		AlarmType r= new AlarmType();
		BeanUtils.copyProperties(t, r);
		return r;
	}
	@Override
	public List<AlarmType> dataGrid(AlarmType info,PageFilter ph) {
		List<AlarmType> ul = new ArrayList<AlarmType>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TAlarmType t ";
		List<TAlarmType> l = tDao.find(hql + whereHql(info, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		for (TAlarmType t : l) {
			AlarmType u = new AlarmType();
			BeanUtils.copyProperties(t, u);
			ul.add(u);
		}
		return ul;
	}
	
	@Override
	public Long count(AlarmType demo, PageFilter ph){
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TAlarmType t ";
		return tDao.count("select count(*) " + hql + whereHql(demo, params), params);
	}
	
	private String whereHql(AlarmType demo, Map<String, Object> params) {
		String hql = "";
		if (demo != null) {
			hql += " where 1=1 ";
			if (demo.getType() != null) {
				hql += " and t.type like :name";
				params.put("name", "%%" + demo.getType() + "%%");
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
