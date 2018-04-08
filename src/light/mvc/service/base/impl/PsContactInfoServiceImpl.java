package light.mvc.service.base.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.base.TPsContactInfo;
import light.mvc.model.base.TPsPollutionSourceInfo;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.PsContactInfo;
import light.mvc.pageModel.base.Tree;
import light.mvc.service.base.PsContactInfoServiceI;

@Service
public class PsContactInfoServiceImpl implements PsContactInfoServiceI{

	@Autowired
	private BaseDaoI<TPsContactInfo> contactDao;
	@Autowired
	private BaseDaoI<TPsPollutionSourceInfo> sourceDao;
	
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
	public void add(PsContactInfo r) {
		TPsContactInfo t = new TPsContactInfo();
		BeanUtils.copyProperties(r, t);
		
		contactDao.save(t);
		
	}
	@Override
	public void delete(Integer id) {
		TPsContactInfo t = contactDao.get(TPsContactInfo.class, id);
		contactDao.delete(t);
	}
	@Override
	public void edit(PsContactInfo r) {
		TPsContactInfo t = contactDao.get(TPsContactInfo.class, r.getId());
		BeanUtils.copyProperties(r, t);
		contactDao.update(t);
	}
	@Override
	public PsContactInfo get(Integer id) {
		TPsContactInfo t = contactDao.get(TPsContactInfo.class, id);
		PsContactInfo r= new PsContactInfo();
		BeanUtils.copyProperties(t, r);
		return r;
	}
	@Override
	public List<PsContactInfo> dataGrid(PsContactInfo info,PageFilter ph) {
		List<PsContactInfo> ul = new ArrayList<PsContactInfo>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TPsContactInfo t ";
		List<TPsContactInfo> l = contactDao.find(hql + whereHql(info, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		
		String hql2 = " from TPsPollutionSourceInfo t";
		List<TPsPollutionSourceInfo> sl = sourceDao.find(hql2);
		Map<String, String> m = new HashMap<String, String>();
		for(int i =0;i<sl.size();i++){
			m.put(sl.get(i).getCode(), sl.get(i).getPsName());
		}
		
		for (TPsContactInfo t : l) {
			PsContactInfo u = new PsContactInfo();
			BeanUtils.copyProperties(t, u);
			u.setPsCode(m.get(u.getPsCode()));
			ul.add(u);
		}
		return ul;
	}
	
	@Override
	public Long count(PsContactInfo demo, PageFilter ph){
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TPsContactInfo t ";
		return contactDao.count("select count(*) " + hql + whereHql(demo, params), params);
	}
	
	private String whereHql(PsContactInfo demo, Map<String, Object> params) {
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
