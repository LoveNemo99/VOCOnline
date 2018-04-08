package light.mvc.service.base.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.base.TScEmissionRegularity;
import light.mvc.pageModel.base.EmissionRegularity;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.service.base.EmissionRegularityServiceI;

@Service
public class EmissionRegularityServiceImpl implements EmissionRegularityServiceI{

	@Autowired
	private BaseDaoI<TScEmissionRegularity> tDao;
	@Override
	public List<Tree> tree() {
		List<TScEmissionRegularity> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		l = tDao.find("select distinct t from TScEmissionRegularity t");

		if ((l != null) && (l.size() > 0)) {
			for (TScEmissionRegularity r : l) {
				Tree tree = new Tree();
				tree.setId(r.getId().toString());
				tree.setText(r.getName());
				lt.add(tree);
			}
		}
		return lt;
	}
	@Override
	public void add(EmissionRegularity r) {
		TScEmissionRegularity t = new TScEmissionRegularity();
		BeanUtils.copyProperties(r, t);
		tDao.save(t);
	}
	@Override
	public void delete(Integer id) {
		TScEmissionRegularity t = tDao.get(TScEmissionRegularity.class, id);
		tDao.delete(t);
	}
	@Override
	public void edit(EmissionRegularity r) {
		TScEmissionRegularity t = tDao.get(TScEmissionRegularity.class, r.getId());
		BeanUtils.copyProperties(r, t);
		tDao.update(t);
	}
	@Override
	public EmissionRegularity get(Integer id) {
		TScEmissionRegularity t = tDao.get(TScEmissionRegularity.class, id);
		EmissionRegularity r= new EmissionRegularity();
		BeanUtils.copyProperties(t, r);
		return r;
	}
	@Override
	public List<EmissionRegularity> dataGrid(EmissionRegularity info,PageFilter ph) {
		List<EmissionRegularity> ul = new ArrayList<EmissionRegularity>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TScEmissionRegularity t ";
		List<TScEmissionRegularity> l = tDao.find(hql + whereHql(info, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		for (TScEmissionRegularity t : l) {
			EmissionRegularity u = new EmissionRegularity();
			BeanUtils.copyProperties(t, u);
			ul.add(u);
		}
		return ul;
	}
	
	@Override
	public Long count(EmissionRegularity demo, PageFilter ph){
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TScEmissionRegularity t ";
		return tDao.count("select count(*) " + hql + whereHql(demo, params), params);
	}
	
	private String whereHql(EmissionRegularity demo, Map<String, Object> params) {
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
