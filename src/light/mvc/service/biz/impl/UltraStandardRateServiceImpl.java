package light.mvc.service.biz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.base.TPsPiPort;
import light.mvc.model.base.TPsPollutionSourceInfo;
import light.mvc.model.base.TScPollutantFactor;
import light.mvc.model.biz.TUltraStandardRate;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.biz.UltraStandardRate;
import light.mvc.service.biz.UltraStandardRateServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UltraStandardRateServiceImpl implements UltraStandardRateServiceI {

	@Autowired
	private BaseDaoI<TUltraStandardRate> rateDao;
	@Autowired
	private BaseDaoI<TPsPiPort> portDao;
	@Autowired
	private BaseDaoI<TPsPollutionSourceInfo> psDao;
	@Autowired
	private BaseDaoI<TScPollutantFactor> factorDao;

	/*-------------------------------------------------------------------------------------------------*/
	@Override
	public List<Tree> factorTree() {
		List<TScPollutantFactor> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		l = factorDao.find("select distinct t from TScPollutantFactor t");
		if ((l != null) && (l.size() > 0)) {
			for (TScPollutantFactor r : l) {
				Tree tree = new Tree();
				tree.setId(r.getId() + "");
				tree.setText(r.getName());
				//tree.setIconCls("icon-company");
				tree.setValue(r.getCode());
				lt.add(tree);
			}
		}
		return lt;
	}
	
	@Override
	public List<Tree> portTree(String psCode) {
		List<TPsPiPort> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		l = portDao.find("select distinct t from TPsPiPort t where t.psCode = '"+psCode+"'");
		if ((l != null) && (l.size() > 0)) {
			for (TPsPiPort r : l) {
				Tree tree = new Tree();
				tree.setId(r.getId() + "");
				tree.setText(r.getPortName());
				//tree.setIconCls("icon-company");
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

		l = psDao.find("select distinct t from TPsPollutionSourceInfo t");
		if ((l != null) && (l.size() > 0)) {
			for (TPsPollutionSourceInfo r : l) {
				Tree tree = new Tree();
				tree.setId(r.getId() + "");
				tree.setText(r.getPsName());
				//tree.setIconCls("icon-company");
				tree.setValue(r.getCode());
				lt.add(tree);
			}
		}
		return lt;
	}
	
	@Override
	public void add(UltraStandardRate r) {
		TUltraStandardRate t = new TUltraStandardRate();
		BeanUtils.copyProperties(r, t);
		rateDao.save(t);
	}

	@Override
	public void delete(Integer id) {
		TUltraStandardRate t = rateDao.get(TUltraStandardRate.class, id);
		rateDao.delete(t);
	}

	@Override
	public void edit(UltraStandardRate r) {
		TUltraStandardRate t = rateDao.get(TUltraStandardRate.class, r.getId());
		BeanUtils.copyProperties(r, t);
		rateDao.update(t);
	}

	@Override
	public UltraStandardRate get(Integer id) {
		TUltraStandardRate t = rateDao.get(TUltraStandardRate.class, id);
		UltraStandardRate r = new UltraStandardRate();
		BeanUtils.copyProperties(t, r);
		return r;
	}

	public Date addHours(Date d,Integer n){
		if(n==null)
			return d;
		long value = d.getTime()+n*60*60*1000;
		Date d1 = new Date(value);
		return d1;
	}
	@Override
	public List<UltraStandardRate> dataGrid(UltraStandardRate demo, PageFilter ph) {
		List<UltraStandardRate> ul = new ArrayList<UltraStandardRate>();
		Map<String, Object> params = new HashMap<String, Object>();
		if(demo.getCreatDateTimeEnd()==null&&demo.getCreatDateTimeStart()==null){
			Date end = new Date();
			demo.setCreatDateTimeEnd(end);
			demo.setCreatDateTimeStart(addHours(end, -1));
		}
		else if(demo.getCreatDateTimeEnd()!=null&&demo.getCreatDateTimeStart()==null){
			demo.setCreatDateTimeStart(addHours(demo.getCreatDateTimeEnd(), -1));
		}
		else if(demo.getCreatDateTimeEnd()==null&&demo.getCreatDateTimeStart()!=null){
			demo.setCreatDateTimeEnd(addHours(demo.getCreatDateTimeStart(), 1));
		}
		String hql = " from TUltraStandardRate t ";
		List<TUltraStandardRate> l = rateDao.find(hql + whereHql(demo, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		
//		List<String> codes = new ArrayList<String>();
		Set<String> codes = new HashSet<String>();
		for(TUltraStandardRate r:l){
			codes.add(r.getPortCode());
		}
		
		String hql1 = " from TPsPollutionSourceInfo t";
		List<TPsPollutionSourceInfo> l1 = psDao.find(hql1);
		Map<String, String> m1 = new HashMap<String, String>();
		for(int i =0;i<l1.size();i++){
			m1.put(l1.get(i).getCode(), l1.get(i).getPsName());
		}
		String hql2 = " from TPsPiPort t";
		List<TPsPiPort> l2 = portDao.find(hql2);
		Map<String, String> m2 = new HashMap<String, String>();
		for(int i =0;i<l2.size();i++){
			m2.put(l2.get(i).getPortCode(), l2.get(i).getPortName());
		}
		String hql3 = " from TScPollutantFactor t";
		List<TScPollutantFactor> l3 = factorDao.find(hql3);
		Map<String, String> m3 = new HashMap<String, String>();
		for(int i =0;i<l3.size();i++){
			m3.put(l3.get(i).getCode(), l3.get(i).getName());
		}
		
		for(String code:codes){
			Integer totalMonitorTimes = 0;
			Integer totalOverTimes = 0;
			Float rate;
			UltraStandardRate u = new UltraStandardRate();
			for (TUltraStandardRate t : l) {
				if(t.getPortCode().equals(code)){
					totalMonitorTimes += t.getMonitorTimes();
					totalOverTimes += t.getOverTimes();
					if(u.getPortCode()==null){
						u.setPortCode(m2.get(t.getPortCode()));
					}
					if(u.getPsCode()==null){
						u.setPsCode(m1.get(t.getPsCode()));
					}
				}
			}
			if(totalMonitorTimes==0){
				rate = 0.00f;
			}else{
				float f = (float)(totalOverTimes*100/totalMonitorTimes)/100;
				rate = f;
			}
			u.setRate(rate);
			u.setCreatDateTimeStart(demo.getCreatDateTimeStart());
			u.setCreatDateTimeEnd(demo.getCreatDateTimeEnd());
			ul.add(u);
		}
		return ul;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public List<UltraStandardRate> today(UltraStandardRate demo, PageFilter ph) {
		if(demo==null){
			demo=new UltraStandardRate();
		}
		if(ph==null){
			ph=new PageFilter();
		}
		ph.setPage(1);
		ph.setRows(50);
		ph.setOrder("desc");
		Date now=new Date();
		demo.setCreatDateTimeStart(new Date(now.getYear(),now.getMonth(),now.getDate()));
		demo.setCreatDateTimeEnd(now);
		List<UltraStandardRate> ul = new ArrayList<UltraStandardRate>();
		Map<String, Object> params = new HashMap<String, Object>();
		
		String hql = " from TUltraStandardRate t ";
		List<TUltraStandardRate> l = rateDao.find(hql + whereHql(demo, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		
		String hql1 = " from TPsPollutionSourceInfo t";
		List<TPsPollutionSourceInfo> l1 = psDao.find(hql1);
		Map<String, String> m1 = new HashMap<String, String>();
		for(int i =0;i<l1.size();i++){
			m1.put(l1.get(i).getCode(), l1.get(i).getPsName());
		}
		String hql2 = " from TPsPiPort t";
		List<TPsPiPort> l2 = portDao.find(hql2);
		Map<String, String> m2 = new HashMap<String, String>();
		for(int i =0;i<l2.size();i++){
			m2.put(l2.get(i).getPortCode(), l2.get(i).getPortName());
		}
		String hql3 = " from TScPollutantFactor t";
		List<TScPollutantFactor> l3 = factorDao.find(hql3);
		Map<String, String> m3 = new HashMap<String, String>();
		for(int i =0;i<l3.size();i++){
			m3.put(l3.get(i).getCode(), l3.get(i).getName());
		}
		for (TUltraStandardRate t : l) {
			//if(i++>50) break;
			UltraStandardRate u = new UltraStandardRate();
			BeanUtils.copyProperties(t, u);
			u.setPsCode(m1.get(t.getPsCode()));
			u.setPortCode(m2.get(t.getPortCode()));
			ul.add(u);
		}
		return ul;
	}

	
	@Override
	public Long count(UltraStandardRate demo, PageFilter ph) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TUltraStandardRate t ";
		return rateDao.count("select count(*) " + hql + whereHql(demo, params), params);
	}
	
	private String whereHql(UltraStandardRate demo, Map<String, Object> params) {
		String hql = "";
		if (demo != null) {
			hql += " where 1=1 ";
			if (demo.getPsCode() != null) {
				hql += " and t.psCode like :ps";
				params.put("ps", "%%" + demo.getPsCode() + "%%");
			}
			if (demo.getPortCode() != null) {
				hql += " and t.portCode like :port";
				params.put("port", "%%" + demo.getPortCode() + "%%");
			}
			if (demo.getCreatDateTimeStart() != null) {
				hql += " and t.time >= :createdatetimeStart";
				params.put("createDatetimeStart", demo.getCreatDateTimeStart());
			}
			if (demo.getCreatDateTimeEnd() != null) {
				hql += " and t.time <= :createdatetimeEnd";
				params.put("createDatetimeEnd", demo.getCreatDateTimeEnd());
			}
		}
		return hql;
	}
	
	/*-------------------------------------------------------------------------------------------------*/
	
	/*-------------------------------------------------------------------------------------------------*/
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
