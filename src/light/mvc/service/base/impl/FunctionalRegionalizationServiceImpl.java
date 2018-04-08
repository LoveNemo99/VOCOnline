package light.mvc.service.base.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.base.TScFunctionalRegionalization;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.FunctionalRegionalization;
import light.mvc.pageModel.base.Tree;
import light.mvc.service.base.FunctionalRegionalizationServiceI;

@Service
public class FunctionalRegionalizationServiceImpl implements FunctionalRegionalizationServiceI{

	@Autowired
	private BaseDaoI<TScFunctionalRegionalization> tDao;
	@Override
	public List<Tree> tree() {
		List<TScFunctionalRegionalization> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		l = tDao.find("select distinct t from TScFunctionalRegionalization t");

		if ((l != null) && (l.size() > 0)) {
			for (TScFunctionalRegionalization r : l) {
				Tree tree = new Tree();
				tree.setId(r.getId().toString());
				tree.setText(r.getName());
				lt.add(tree);
			}
		}
		return lt;
	}
	@Override
	public void add(FunctionalRegionalization r) {
		TScFunctionalRegionalization t = new TScFunctionalRegionalization();
		BeanUtils.copyProperties(r, t);
		tDao.save(t);
	}
	@Override
	public void delete(Integer id) {
		TScFunctionalRegionalization t = tDao.get(TScFunctionalRegionalization.class, id);
		tDao.delete(t);
	}
	@Override
	public void edit(FunctionalRegionalization r) {
		TScFunctionalRegionalization t = tDao.get(TScFunctionalRegionalization.class, r.getId());
		BeanUtils.copyProperties(r, t);
		tDao.update(t);
	}
	@Override
	public FunctionalRegionalization get(Integer id) {
		TScFunctionalRegionalization t = tDao.get(TScFunctionalRegionalization.class, id);
		FunctionalRegionalization r= new FunctionalRegionalization();
		BeanUtils.copyProperties(t, r);
		return r;
	}
	@Override
	public List<FunctionalRegionalization> dataGrid(FunctionalRegionalization info,PageFilter ph) {
		List<FunctionalRegionalization> ul = new ArrayList<FunctionalRegionalization>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TScFunctionalRegionalization t ";
		List<TScFunctionalRegionalization> l = tDao.find(hql + whereHql(info, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		for (TScFunctionalRegionalization t : l) {
			FunctionalRegionalization u = new FunctionalRegionalization();
			BeanUtils.copyProperties(t, u);
			ul.add(u);
		}
		return ul;
	}
	
	@Override
	public Long count(FunctionalRegionalization demo, PageFilter ph){
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TScFunctionalRegionalization t ";
		return tDao.count("select count(*) " + hql + whereHql(demo, params), params);
	}
	
	private String whereHql(FunctionalRegionalization demo, Map<String, Object> params) {
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