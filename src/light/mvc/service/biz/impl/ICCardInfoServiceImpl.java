package light.mvc.service.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.base.TPsPiPort;
import light.mvc.model.base.TPsPollutionSourceInfo;
import light.mvc.model.biz.TICCardInfo;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.biz.ICCardInfo;
import light.mvc.service.biz.ICCardInfoServiceI;

@Service
public class ICCardInfoServiceImpl implements ICCardInfoServiceI{

	@Autowired
	private BaseDaoI<TICCardInfo> tDao;
	@Autowired
	private BaseDaoI<TPsPollutionSourceInfo> psDao;
	@Autowired
	private BaseDaoI<TPsPiPort> piDao;
	@Override
	public List<Tree> tree() {
		List<TICCardInfo> l = null;
		List<Tree> lt = new ArrayList<Tree>();
		l = tDao.find("select distinct t from TICCardInfo t");
		if ((l != null) && (l.size() > 0)) {
			for (TICCardInfo r : l) {
				Tree tree = new Tree();
				tree.setId(r.getId().toString());
				tree.setText(r.getCardNum());
				tree.setValue(r.getCardNum());
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
				tree.setValue(r.getPsName());
				lt.add(tree);
			}
		}
		return lt;
	}
	@Override
	public void add(ICCardInfo r) {
		TICCardInfo t = new TICCardInfo();
		BeanUtils.copyProperties(r, t);
		tDao.save(t);
	}
	@Override
	public void delete(Integer id) {
		TICCardInfo t = tDao.get(TICCardInfo.class, id);
		tDao.delete(t);
	}
	@Override
	public void edit(ICCardInfo r) {
		TICCardInfo t = tDao.get(TICCardInfo.class, r.getId());
		BeanUtils.copyProperties(r, t);
		tDao.update(t);
	}
	@Override
	public ICCardInfo get(Integer id) {
		TICCardInfo t = tDao.get(TICCardInfo.class, id);
		ICCardInfo r= new ICCardInfo();
		BeanUtils.copyProperties(t, r);
		return r;
	}
	@Override
	public List<ICCardInfo> dataGrid(ICCardInfo info,PageFilter ph) {
		List<ICCardInfo> ul = new ArrayList<ICCardInfo>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TICCardInfo t ";
		List<TICCardInfo> l = tDao.find(hql + whereHql(info, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		
		String hql1 = " from TPsPollutionSourceInfo t";
		List<TPsPollutionSourceInfo> l1 = psDao.find(hql1);
		Map<Integer, String> m1 = new HashMap<Integer, String>();
		for(int i =0;i<l1.size();i++){
			m1.put(l1.get(i).getId(), l1.get(i).getPsName());
		}
		
		String hql2 = " from TPsPiPort t";
		List<TPsPiPort> l2 = piDao.find(hql2);
		Map<Integer, String> m2 = new HashMap<Integer, String>();
		for(int i =0;i<l2.size();i++){
			m2.put(l2.get(i).getId(), l2.get(i).getPortName());
		}
		
		for (TICCardInfo t : l) {
			ICCardInfo u = new ICCardInfo();
			BeanUtils.copyProperties(t, u);
			u.setPsBaseName(m1.get(t.getPsBaseId()));
			u.setPsPiName(m2.get(t.getPsPiId()));
			
			ul.add(u);
		}
		return ul;
	}
	
	@Override
	public Long count(ICCardInfo demo, PageFilter ph){
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TICCardInfo t ";
		return tDao.count("select count(*) " + hql + whereHql(demo, params), params);
	}
	
	private String whereHql(ICCardInfo demo, Map<String, Object> params) {
		String hql = "";
		if (demo != null) {
			hql += " where 1=1 ";
			if (demo.getCardNum() != null) {
				hql += " and t.cardNum like :name";
				params.put("name", "%%" + demo.getCardNum() + "%%");
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
