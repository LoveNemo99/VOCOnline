package light.mvc.service.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.base.TPsPollutionSourceInfo;
import light.mvc.model.sys.TAlarmContact;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.sys.AlarmContact;
import light.mvc.service.biz.AlarmContactServiceI;

@Service
public class AlarmContactServiceImpl implements AlarmContactServiceI{

	@Autowired
	private BaseDaoI<TAlarmContact> tDao;
	@Autowired
	private BaseDaoI<TPsPollutionSourceInfo> psDao;
	@Override
	public List<Tree> tree() {
		List<TAlarmContact> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		l = tDao.find("select distinct t from TAlarmContact t");

		if ((l != null) && (l.size() > 0)) {
			for (TAlarmContact r : l) {
				Tree tree = new Tree();
				tree.setId(r.getId().toString());
				tree.setText(r.getMan());
				tree.setValue(r.getMan());
				lt.add(tree);
			}
		}
		return lt;
	}
	@Override
	public List<Tree> psTree() {
		List<TPsPollutionSourceInfo> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		l = psDao.find("select distinct t from TPsPollutionSourceInfo t");

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
	public void add(AlarmContact r) {
		TAlarmContact t = new TAlarmContact();
		BeanUtils.copyProperties(r, t);
		tDao.save(t);
	}
	@Override
	public void delete(Integer id) {
		TAlarmContact t = tDao.get(TAlarmContact.class, id);
		tDao.delete(t);
	}
	@Override
	public void edit(AlarmContact r) {
		TAlarmContact t = tDao.get(TAlarmContact.class, r.getId());
		BeanUtils.copyProperties(r, t);
		tDao.update(t);
	}
	@Override
	public AlarmContact get(Integer id) {
		TAlarmContact t = tDao.get(TAlarmContact.class, id);
		AlarmContact r= new AlarmContact();
		BeanUtils.copyProperties(t, r);
		return r;
	}
	@Override
	public List<AlarmContact> dataGrid(AlarmContact info,PageFilter ph) {
		List<AlarmContact> ul = new ArrayList<AlarmContact>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TAlarmContact t ";
		List<TAlarmContact> l = tDao.find(hql + whereHql(info, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		
		String hql1 = " from TPsPollutionSourceInfo t";
		List<TPsPollutionSourceInfo> l1 = psDao.find(hql1);
		Map<String, String> m1 = new HashMap<String, String>();
		for(int i =0;i<l1.size();i++){
			m1.put(l1.get(i).getCode(), l1.get(i).getPsName());
		}
		for (TAlarmContact t : l) {
			AlarmContact u = new AlarmContact();
			BeanUtils.copyProperties(t, u);
			u.setPsCode(m1.get(u.getPsCode()));
			ul.add(u);
		}
		return ul;
	}
	
	@Override
	public Long count(AlarmContact demo, PageFilter ph){
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TAlarmContact t ";
		return tDao.count("select count(*) " + hql + whereHql(demo, params), params);
	}
	
	private String whereHql(AlarmContact demo, Map<String, Object> params) {
		String hql = "";
		if (demo != null) {
			hql += " where 1=1 ";
			if (demo.getMan() != null) {
				hql += " and t.name like :name";
				params.put("name", "%%" + demo.getMan() + "%%");
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
