package light.mvc.service.base.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.base.TScMeasureRtdUnit;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.ScMeasureRtdUnit;
import light.mvc.pageModel.base.Tree;
import light.mvc.service.base.MeasureRtdUnitServiceI;

@Service
public class MeasureRtdUnitServiceImpl implements MeasureRtdUnitServiceI{

	@Autowired
	private BaseDaoI<TScMeasureRtdUnit> tDao;
	@Override
	public List<Tree> tree() {
		List<TScMeasureRtdUnit> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		l = tDao.find("select distinct t from TScMeasureRtdUnit t");

		if ((l != null) && (l.size() > 0)) {
			for (TScMeasureRtdUnit r : l) {
				Tree tree = new Tree();
				tree.setId(r.getId().toString());
				tree.setText(r.getName());
				tree.setValue(r.getName());
				lt.add(tree);
			}
		}
		return lt;
	}
	@Override
	public void add(ScMeasureRtdUnit r) {
		TScMeasureRtdUnit t = new TScMeasureRtdUnit();
		BeanUtils.copyProperties(r, t);
		tDao.save(t);
	}
	@Override
	public void delete(Integer id) {
		TScMeasureRtdUnit t = tDao.get(TScMeasureRtdUnit.class, id);
		tDao.delete(t);
	}
	@Override
	public void edit(ScMeasureRtdUnit r) {
		TScMeasureRtdUnit t = tDao.get(TScMeasureRtdUnit.class, r.getId());
		BeanUtils.copyProperties(r, t);
		tDao.update(t);
	}
	@Override
	public ScMeasureRtdUnit get(Integer id) {
		TScMeasureRtdUnit t = tDao.get(TScMeasureRtdUnit.class, id);
		ScMeasureRtdUnit r= new ScMeasureRtdUnit();
		BeanUtils.copyProperties(t, r);
		return r;
	}
	@Override
	public List<ScMeasureRtdUnit> dataGrid(ScMeasureRtdUnit info,PageFilter ph) {
		List<ScMeasureRtdUnit> ul = new ArrayList<ScMeasureRtdUnit>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TScMeasureRtdUnit t ";
		List<TScMeasureRtdUnit> l = tDao.find(hql + whereHql(info, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		for (TScMeasureRtdUnit t : l) {
			ScMeasureRtdUnit u = new ScMeasureRtdUnit();
			BeanUtils.copyProperties(t, u);
			ul.add(u);
		}
		return ul;
	}
	
	@Override
	public Long count(ScMeasureRtdUnit demo, PageFilter ph){
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TScMeasureRtdUnit t ";
		return tDao.count("select count(*) " + hql + whereHql(demo, params), params);
	}
	
	private String whereHql(ScMeasureRtdUnit demo, Map<String, Object> params) {
		String hql = "";
		if (demo != null) {
			hql += " where 1=1 ";
			if (demo.getName() != null) {
				hql += " and t.name like :name";
				params.put("name", "%%" + demo.getName() + "%%");
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
