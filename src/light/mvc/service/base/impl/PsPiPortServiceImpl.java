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
import light.mvc.model.base.TPsPiPort;
import light.mvc.model.base.TPsPollutionSourceInfo;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.PsPiPort;
import light.mvc.pageModel.base.Tree;
import light.mvc.service.base.PsPiPortServiceI;

@Service
public class PsPiPortServiceImpl implements PsPiPortServiceI{

	@Autowired
	private BaseDaoI<TPsPiPort> tDao;
	@Autowired
	private BaseDaoI<TPsPollutionSourceInfo> sourceDao;
	@Autowired
	private BaseDaoI<TPollutantType> typeDao;
	@Override
	public List<Tree> tree() {
		List<TPsPiPort> l = null;
		List<Tree> lt = new ArrayList<Tree>();
		l = tDao.find("select distinct t from TPsPiPort t");

		if ((l != null) && (l.size() > 0)) {
			for (TPsPiPort r : l) {
				Tree tree = new Tree();
				tree.setId(r.getId().toString());
				tree.setText(r.getPortName());
				tree.setValue(r.getPortCode());
				lt.add(tree);
			}
		}
		return lt;
	}
	@Override
	public List<Tree> psTree() {
		List<TPsPollutionSourceInfo> l = null;
		List<Tree> lt = new ArrayList<Tree>();
		l = sourceDao.find("select distinct t from TPsPollutionSourceInfo t");

		if ((l != null) && (l.size() > 0)) {
			for (TPsPollutionSourceInfo r : l) {
				Tree tree = new Tree();
				tree.setId(r.getId().toString());
				tree.setText(r.getPsName());
				tree.setValue(r.getCode());
				lt.add(tree);
			}
		}
		return lt;
	}
	
	@Override
	public List<Tree> pollutantTypeTree() {
		List<TPollutantType> l = null;
		List<Tree> lt = new ArrayList<Tree>();
		l = typeDao.find("select distinct t from TPollutantType t");

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
	public void add(PsPiPort r) {
		TPsPiPort t = new TPsPiPort();
		BeanUtils.copyProperties(r, t);
		tDao.save(t);
	}
	@Override
	public void delete(Integer id) {
		TPsPiPort t = tDao.get(TPsPiPort.class, id);
		tDao.delete(t);
	}
	@Override
	public void edit(PsPiPort r) {
		TPsPiPort t = tDao.get(TPsPiPort.class, r.getId());
		BeanUtils.copyProperties(r, t);
		tDao.update(t);
	}
	@Override
	public PsPiPort get(Integer id) {
		TPsPiPort t = tDao.get(TPsPiPort.class, id);
		PsPiPort r= new PsPiPort();
		BeanUtils.copyProperties(t, r);
		return r;
	}
	@Override
	public List<PsPiPort> dataGrid(PsPiPort info,PageFilter ph) {
		List<PsPiPort> ul = new ArrayList<PsPiPort>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TPsPiPort t ";
		List<TPsPiPort> l = tDao.find(hql + whereHql(info, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		
		String hql2 = " from TPsPollutionSourceInfo t";
		List<TPsPollutionSourceInfo> sl = sourceDao.find(hql2);
		Map<String, String> m = new HashMap<String, String>();//企业  编码-名称
		for(int i =0;i<sl.size();i++){
			m.put(sl.get(i).getCode(), sl.get(i).getPsName());
		}
		
		String hql3 = " from TPollutantType t";
		List<TPollutantType> tl = typeDao.find(hql3);
		Map<String, String> m3 = new HashMap<String, String>();//污染物类型  编码-名称
		for(int i =0;i<tl.size();i++){
			m3.put(tl.get(i).getCode(), tl.get(i).getName());
		}
		
		for (TPsPiPort t : l) {
			PsPiPort u = new PsPiPort();
			BeanUtils.copyProperties(t, u);
			u.setPsCode(m.get(u.getPsCode()));
			u.setPollutantTypeCode(m3.get(u.getPollutantTypeCode()));
			ul.add(u);
		}
		return ul;
	}
	
	@Override
	public Long count(PsPiPort demo, PageFilter ph){
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TPsPiPort t ";
		return tDao.count("select count(*) " + hql + whereHql(demo, params), params);
	}
	
	private String whereHql(PsPiPort demo, Map<String, Object> params) {
		String hql = "";
		if (demo != null) {
			hql += " where 1=1 ";
			if (demo.getPortName() != null) {
				hql += " and t.portname like :name";
				params.put("name", "%%" + demo.getPortName() + "%%");
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
