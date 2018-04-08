package light.mvc.service.base.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.base.TScAdministrativeDivision;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.ScAdministrativeDivision;
import light.mvc.pageModel.base.Tree;
import light.mvc.service.base.ScAdministrativeDivisionServiceI;

@Service
public class ScAdministrativeDivisionServiceImpl implements ScAdministrativeDivisionServiceI{

	@Autowired
	private BaseDaoI<TScAdministrativeDivision> tDao;
	@Override
	public List<Tree> tree() {
		List<TScAdministrativeDivision> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		l = tDao.find("select distinct t from TScAdministrativeDivision t");

		if ((l != null) && (l.size() > 0)) {
			for (TScAdministrativeDivision r : l) {
				Tree tree = new Tree();
				tree.setId(r.getId()+"");
				tree.setText(r.getName());
				tree.setValue(r.getCode());
				lt.add(tree);
			}
		}
		return lt;
	}
	@Override
	public void add(ScAdministrativeDivision r) {
		TScAdministrativeDivision t = new TScAdministrativeDivision();
		BeanUtils.copyProperties(r, t);
		tDao.save(t);
	}
	@Override
	public void delete(Integer id) {
		TScAdministrativeDivision t = tDao.get(TScAdministrativeDivision.class, id);
		tDao.delete(t);
	}
	@Override
	public void edit(ScAdministrativeDivision r) {
		TScAdministrativeDivision t = tDao.get(TScAdministrativeDivision.class, r.getId());
		BeanUtils.copyProperties(r, t);
		tDao.update(t);
	}
	@Override
	public ScAdministrativeDivision get(Integer id) {
		TScAdministrativeDivision t = tDao.get(TScAdministrativeDivision.class, id);
		ScAdministrativeDivision r= new ScAdministrativeDivision();
		BeanUtils.copyProperties(t, r);
		return r;
	}
	@Override
	public List<ScAdministrativeDivision> dataGrid(ScAdministrativeDivision info,PageFilter ph) {
		List<ScAdministrativeDivision> ul = new ArrayList<ScAdministrativeDivision>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TScAdministrativeDivision t ";
		List<TScAdministrativeDivision> l = tDao.find(hql + whereHql(info, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		
		Map<String, String> m1 = new HashMap<String, String>();
		for(int i =0;i<l.size();i++){
			m1.put(l.get(i).getCode(), l.get(i).getName());
		}
		for (TScAdministrativeDivision t : l) {
			ScAdministrativeDivision u = new ScAdministrativeDivision();
			BeanUtils.copyProperties(t, u);
			u.setParentCode(m1.get(u.getParentCode()));
			ul.add(u);
		}
		return ul;
	}
	
	@Override
	public Long count(ScAdministrativeDivision demo, PageFilter ph){
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TScAdministrativeDivision t ";
		return tDao.count("select count(*) " + hql + whereHql(demo, params), params);
	}
	
	private String whereHql(ScAdministrativeDivision demo, Map<String, Object> params) {
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
