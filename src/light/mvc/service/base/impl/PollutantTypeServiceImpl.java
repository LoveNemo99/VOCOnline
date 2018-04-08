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
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.PollutantType;
import light.mvc.pageModel.base.Tree;
import light.mvc.service.base.PollutantTypeServiceI;

@Service
public class PollutantTypeServiceImpl implements PollutantTypeServiceI{

	@Autowired
	private BaseDaoI<TPollutantType> tDao;
	@Override
	public List<Tree> tree() {
		List<TPollutantType> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		l = tDao.find("select distinct t from TPollutantType t");

		if ((l != null) && (l.size() > 0)) {
			for (TPollutantType r : l) {
				Tree tree = new Tree();
				tree.setId(r.getId().toString());
				tree.setText(r.getName());
				lt.add(tree);
			}
		}
		return lt;
	}
	@Override
	public void add(PollutantType r) {
		TPollutantType t = new TPollutantType();
		BeanUtils.copyProperties(r, t);
		tDao.save(t);
	}
	@Override
	public void delete(Integer id) {
		TPollutantType t = tDao.get(TPollutantType.class, id);
		tDao.delete(t);
	}
	@Override
	public void edit(PollutantType r) {
		TPollutantType t = tDao.get(TPollutantType.class, r.getId());
		BeanUtils.copyProperties(r, t);
		tDao.update(t);
	}
	@Override
	public PollutantType get(Integer id) {
		TPollutantType t = tDao.get(TPollutantType.class, id);
		PollutantType r= new PollutantType();
		BeanUtils.copyProperties(t, r);
		return r;
	}
	@Override
	public List<PollutantType> dataGrid(PollutantType info,PageFilter ph) {
		List<PollutantType> ul = new ArrayList<PollutantType>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TPollutantType t ";
		List<TPollutantType> l = tDao.find(hql + whereHql(info, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		for (TPollutantType t : l) {
			PollutantType u = new PollutantType();
			BeanUtils.copyProperties(t, u);
			ul.add(u);
		}
		return ul;
	}
	
	@Override
	public Long count(PollutantType demo, PageFilter ph){
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TPollutantType t ";
		return tDao.count("select count(*) " + hql + whereHql(demo, params), params);
	}
	
	private String whereHql(PollutantType demo, Map<String, Object> params) {
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
