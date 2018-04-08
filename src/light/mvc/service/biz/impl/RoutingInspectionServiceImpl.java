package light.mvc.service.biz.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.base.TPsPollutionSourceInfo;
import light.mvc.model.base.TScState;
import light.mvc.model.biz.TRoutingInspection;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.biz.RoutingInspection;
import light.mvc.service.biz.RoutingInspectionServiceI;

@Service
public class RoutingInspectionServiceImpl implements RoutingInspectionServiceI{

	@Autowired
	private BaseDaoI<TRoutingInspection> tDao;
	@Autowired
	private BaseDaoI<TPsPollutionSourceInfo> psDao;
	@Autowired
	private BaseDaoI<TScState> stateDao;
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
	public List<Tree> stateTree() {
		List<TScState> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		l = stateDao.find("select distinct t from TScState t");

		if ((l != null) && (l.size() > 0)) {
			for (TScState r : l) {
				Tree tree = new Tree();
				tree.setId(r.getId().toString());
				tree.setText(r.getState());
				tree.setValue(r.getState());
				lt.add(tree);
			}
		}
		return lt;
	}
	@Override
	public void add(RoutingInspection r) {
		TRoutingInspection t = new TRoutingInspection();
		BeanUtils.copyProperties(r, t);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		t.setYearMonth(sdf.format(new Date()));
		System.out.println(t);
		tDao.save(t);
	}
	@Override
	public void delete(Integer id) {
		TRoutingInspection t = tDao.get(TRoutingInspection.class, id);
		tDao.delete(t);
	}
	@Override
	public void edit(RoutingInspection r) {
		TRoutingInspection t = tDao.get(TRoutingInspection.class, r.getId());
		BeanUtils.copyProperties(r, t);
		t.setModifyTime(new Date());
		tDao.update(t);
	}
	@Override
	public RoutingInspection get(Integer id) {
		TRoutingInspection t = tDao.get(TRoutingInspection.class, id);
		RoutingInspection r= new RoutingInspection();
		BeanUtils.copyProperties(t, r);
		return r;
	}
	@Override
	public List<RoutingInspection> dataGrid(RoutingInspection info,PageFilter ph) {
		List<RoutingInspection> ul = new ArrayList<RoutingInspection>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TRoutingInspection t ";
		if((info.getPsCode()==null||info.getPsCode().equals(""))&&(info.getYearMonth()==null||info.getYearMonth().equals(""))){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			info.setYearMonth(sdf.format(new Date()));
		}
		List<TRoutingInspection> l = tDao.find(hql + whereHql(info, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		
		String hql1 = " from TPsPollutionSourceInfo t";
		List<TPsPollutionSourceInfo> l1 = psDao.find(hql1);
		Map<String, String> m1 = new HashMap<String, String>();
		for(int i =0;i<l1.size();i++){
			m1.put(l1.get(i).getCode(), l1.get(i).getPsName());
		}
		
		for (TRoutingInspection t : l) {
			RoutingInspection u = new RoutingInspection();
			BeanUtils.copyProperties(t, u);
			u.setPsCode(m1.get(t.getPsCode()));
			ul.add(u);
		}
		return ul;
	}
	
	@Override
	public Long count(RoutingInspection demo, PageFilter ph){
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TRoutingInspection t ";
		return tDao.count("select count(*) " + hql + whereHql(demo, params), params);
	}
	
	private String whereHql(RoutingInspection demo, Map<String, Object> params) {
		String hql = "";
		if (demo != null) {
			hql += " where 1=1 ";
			if (demo.getYearMonth() != null) {
				hql += " and t.yearMonth like :month";
				params.put("month", "%%" + demo.getYearMonth() + "%%");
			}
			if (demo.getPsCode() != null) {
				hql += " and t.psCode like :code";
				params.put("code", "%%" + demo.getPsCode() + "%%");
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
	@Override
	public List<Tree> unInspectPsTree() {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TRoutingInspection t ";
		RoutingInspection info = new RoutingInspection();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		info.setYearMonth(sdf.format(new Date()));
		List<TRoutingInspection> lr = tDao.find(hql + whereHql(info, params), params);
		String codes = "";
		if((lr != null) && lr.size()>0){
			for(TRoutingInspection inspection:lr){
				codes = codes + ",'" + inspection.getPsCode()+"'";
			}
			if(!codes.equals("")){
				codes = codes.substring(1);
			}
			System.out.println("codes:"+codes);
		}
		
		List<TPsPollutionSourceInfo> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		if(codes.equals("")){
			l = psDao.find("select distinct t from TPsPollutionSourceInfo t ");
		}
		else{
			codes = "("+codes+")";
			l = psDao.find("select distinct t from TPsPollutionSourceInfo t where t.code not in "+codes);
		}
		

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

}
