package light.mvc.service.base.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.base.TScMeasureUnit;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.ScMeasureUnit;
import light.mvc.pageModel.base.Tree;
import light.mvc.service.base.MeasureUnitServiceI;

@Service
public class MeasureUnitServiceImpl implements MeasureUnitServiceI{

	@Autowired
	private BaseDaoI<TScMeasureUnit> tDao;
	@Override
	public List<Tree> tree() {
		List<TScMeasureUnit> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		l = tDao.find("select distinct t from TScMeasureUnit t");

		if ((l != null) && (l.size() > 0)) {
			for (TScMeasureUnit r : l) {
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
	public void add(ScMeasureUnit r) {
		TScMeasureUnit t = new TScMeasureUnit();
		BeanUtils.copyProperties(r, t);
		tDao.save(t);
	}
	@Override
	public void delete(Integer id) {
		TScMeasureUnit t = tDao.get(TScMeasureUnit.class, id);
		tDao.delete(t);
	}
	@Override
	public void edit(ScMeasureUnit r) {
		TScMeasureUnit t = tDao.get(TScMeasureUnit.class, r.getId());
		BeanUtils.copyProperties(r, t);
		tDao.update(t);
	}
	@Override
	public ScMeasureUnit get(Integer id) {
		TScMeasureUnit t = tDao.get(TScMeasureUnit.class, id);
		ScMeasureUnit r= new ScMeasureUnit();
		BeanUtils.copyProperties(t, r);
		return r;
	}
	@Override
	public List<ScMeasureUnit> dataGrid(ScMeasureUnit info,PageFilter ph) {
		List<ScMeasureUnit> ul = new ArrayList<ScMeasureUnit>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TScMeasureUnit t ";
		List<TScMeasureUnit> l = tDao.find(hql + whereHql(info, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		for (TScMeasureUnit t : l) {
			ScMeasureUnit u = new ScMeasureUnit();
			BeanUtils.copyProperties(t, u);
			ul.add(u);
		}
		return ul;
	}
	
	@Override
	public Long count(ScMeasureUnit demo, PageFilter ph){
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TScMeasureUnit t ";
		return tDao.count("select count(*) " + hql + whereHql(demo, params), params);
	}
	
	private String whereHql(ScMeasureUnit demo, Map<String, Object> params) {
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
