package light.mvc.service.base.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.base.TPollutantType;
import light.mvc.model.base.TScMeasureUnit;
import light.mvc.model.base.TScPollutantFactor;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.ScPollutantFactor;
import light.mvc.pageModel.base.Tree;
import light.mvc.service.base.ScPollutantFactorServiceI;

@Service
public class ScPollutantFactorServiceImpl implements ScPollutantFactorServiceI{

	@Autowired
	private BaseDaoI<TScPollutantFactor> psDao;
	@Autowired
	private BaseDaoI<TScMeasureUnit> unitDao;
	@Autowired
	private BaseDaoI<TPollutantType> pollutantTypeDao;
	
	@Override
	public List<Tree> tree() {
		List<TScPollutantFactor> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		l = psDao.find("select distinct t from TScPollutantFactor t");

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
	@Override
	public List<Tree> pollutantTypeTree(){
		List<TPollutantType> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		l = pollutantTypeDao.find("select distinct t from TPollutantType t");

		if ((l != null) && (l.size() > 0)) {
			for (TPollutantType r : l) {
				Tree tree = new Tree();
				tree.setId(r.getId().toString());
				tree.setText(r.getName());
				tree.setValue(r.getCode());
				lt.add(tree);
			}
		}
		return lt;
	}
	@Override
	public List<Tree> unitTree(){
		List<TScMeasureUnit> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		l = unitDao.find("select distinct t from TScMeasureUnit t");

		if ((l != null) && (l.size() > 0)) {
			for (TScMeasureUnit r : l) {
				Tree tree = new Tree();
				tree.setId(r.getId().toString());
				tree.setText(r.getName());
				tree.setValue(r.getCode());
				lt.add(tree);
			}
		}
		return lt;
	}
	@Override
	public void add(ScPollutantFactor r) {
		TScPollutantFactor t = new TScPollutantFactor();
		BeanUtils.copyProperties(r, t);
		t.setMeasurementRtdUnitCode(r.getMeasurementRtdUnitCode());
		t.setMeasurementUnitCode(r.getMeasurementUnitCode());
		psDao.save(t);
	}
	@Override
	public void delete(Integer id) {
		TScPollutantFactor t = psDao.get(TScPollutantFactor.class, id);
		psDao.delete(t);
	}
	@Override
	public void edit(ScPollutantFactor r) {
		TScPollutantFactor t = psDao.get(TScPollutantFactor.class, r.getId());
//		String hql1 = " from TScMeasureUnit t where t.name = '"+r.getMeasurementUnitCode()+"'";
//		List<TScMeasureUnit> l1 = unitDao.find(hql1);
//		String hql2 = " from TScMeasureUnit t where t.name = '"+r.getMeasurementRtdUnitCode()+"'";
//		List<TScMeasureUnit> l2 = unitDao.find(hql2);
//		String hql3 = " from TPollutantType t where t.name = '"+r.getCategoryCode()+"'";
//		List<TScMeasureUnit> l3 = unitDao.find(hql3);
		BeanUtils.copyProperties(r, t);
//		t.setMeasurementUnitCode(l1.get(0).getCode());
//		t.setMeasurementRtdUnitCode(l2.get(0).getCode());
//		t.setCategoryCode(l3.get(0).getCode());
		psDao.update(t);
	}
	@Override
	public ScPollutantFactor get(Integer id) {
		TScPollutantFactor t = psDao.get(TScPollutantFactor.class, id);
		ScPollutantFactor r= new ScPollutantFactor();
		BeanUtils.copyProperties(t, r);
		return r;
	}
	@Override
	public List<ScPollutantFactor> dataGrid(ScPollutantFactor info,PageFilter ph) {
		List<ScPollutantFactor> ul = new ArrayList<ScPollutantFactor>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TScPollutantFactor t ";
		List<TScPollutantFactor> l = psDao.find(hql + whereHql(info, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		
		String hql1 = " from TScMeasureUnit t";
		List<TScMeasureUnit> l1 = unitDao.find(hql1);
		Map<String, String> m1 = new HashMap<String, String>();
		for(int i =0;i<l1.size();i++){
			m1.put(l1.get(i).getCode(), l1.get(i).getName());
		}
		String hql2 = " from TPollutantType t";
		List<TPollutantType> l2 = pollutantTypeDao.find(hql2);
		Map<String, String> m2 = new HashMap<String, String>();
		for(int i =0;i<l2.size();i++){
			m2.put(l2.get(i).getCode(), l2.get(i).getName());
		}
		
		for (TScPollutantFactor t : l) {
			ScPollutantFactor u = new ScPollutantFactor();
			BeanUtils.copyProperties(t, u);
			u.setMeasurementUnitCode(m1.get(u.getMeasurementUnitCode()));
			u.setMeasurementRtdUnitCode(m1.get(u.getMeasurementRtdUnitCode()));
			u.setCategoryCode(m2.get(u.getCategoryCode()));
			ul.add(u);
		}
		return ul;
	}
	
	@Override
	public Long count(ScPollutantFactor demo, PageFilter ph){
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TScPollutantFactor t ";
		return psDao.count("select count(*) " + hql + whereHql(demo, params), params);
	}
	
	private String whereHql(ScPollutantFactor demo, Map<String, Object> params) {
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
