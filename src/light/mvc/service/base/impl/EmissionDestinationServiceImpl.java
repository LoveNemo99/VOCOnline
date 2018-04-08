package light.mvc.service.base.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.base.TScEmissionDestination;
import light.mvc.pageModel.base.EmissionDestination;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.service.base.EmissionDestinationServiceI;

@Service
public class EmissionDestinationServiceImpl implements EmissionDestinationServiceI{

	@Autowired
	private BaseDaoI<TScEmissionDestination> tDao;
	@Override
	public List<Tree> tree() {
		List<TScEmissionDestination> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		l = tDao.find("select distinct t from TScEmissionDestination t");

		if ((l != null) && (l.size() > 0)) {
			for (TScEmissionDestination r : l) {
				Tree tree = new Tree();
				tree.setId(r.getId().toString());
				tree.setText(r.getName());
				lt.add(tree);
			}
		}
		return lt;
	}
	@Override
	public void add(EmissionDestination r) {
		TScEmissionDestination t = new TScEmissionDestination();
		BeanUtils.copyProperties(r, t);
		tDao.save(t);
	}
	@Override
	public void delete(Integer id) {
		TScEmissionDestination t = tDao.get(TScEmissionDestination.class, id);
		tDao.delete(t);
	}
	@Override
	public void edit(EmissionDestination r) {
		TScEmissionDestination t = tDao.get(TScEmissionDestination.class, r.getId());
		BeanUtils.copyProperties(r, t);
		tDao.update(t);
	}
	@Override
	public EmissionDestination get(Integer id) {
		TScEmissionDestination t = tDao.get(TScEmissionDestination.class, id);
		EmissionDestination r= new EmissionDestination();
		BeanUtils.copyProperties(t, r);
		return r;
	}
	@Override
	public List<EmissionDestination> dataGrid(EmissionDestination info,PageFilter ph) {
		List<EmissionDestination> ul = new ArrayList<EmissionDestination>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TScEmissionDestination t ";
		List<TScEmissionDestination> l = tDao.find(hql + whereHql(info, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		for (TScEmissionDestination t : l) {
			EmissionDestination u = new EmissionDestination();
			BeanUtils.copyProperties(t, u);
			ul.add(u);
		}
		return ul;
	}
	
	@Override
	public Long count(EmissionDestination demo, PageFilter ph){
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TScEmissionDestination t ";
		return tDao.count("select count(*) " + hql + whereHql(demo, params), params);
	}
	
	private String whereHql(EmissionDestination demo, Map<String, Object> params) {
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
