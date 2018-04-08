package light.mvc.service.biz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.base.TPsPiPort;
import light.mvc.model.base.TPsPollutionSourceInfo;
import light.mvc.model.base.TScPollutantFactor;
import light.mvc.model.biz.TUltraStandardReport;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.biz.UltraStandardReport;
import light.mvc.service.biz.UltraStandardReportServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UltraStandardReportServiceImpl implements UltraStandardReportServiceI {

	@Autowired
	private BaseDaoI<TUltraStandardReport> reportDao;
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
	public void add(UltraStandardReport r) {
		TUltraStandardReport t = new TUltraStandardReport();
		BeanUtils.copyProperties(r, t);
		reportDao.save(t);
	}

	@Override
	public void delete(Integer id) {
		TUltraStandardReport t = reportDao.get(TUltraStandardReport.class, id);
		reportDao.delete(t);
	}

	@Override
	public void edit(UltraStandardReport r) {
		TUltraStandardReport t = reportDao.get(TUltraStandardReport.class, r.getId());
		BeanUtils.copyProperties(r, t);
		reportDao.update(t);
	}

	@Override
	public UltraStandardReport get(Integer id) {
		TUltraStandardReport t = reportDao.get(TUltraStandardReport.class, id);
		UltraStandardReport r = new UltraStandardReport();
		BeanUtils.copyProperties(t, r);
		return r;
	}

	@Override
	public List<UltraStandardReport> dataGrid(UltraStandardReport demo, PageFilter ph) {
		List<UltraStandardReport> ul = new ArrayList<UltraStandardReport>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TUltraStandardReport t ";
		List<TUltraStandardReport> l = reportDao.find(hql + whereHql(demo, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		
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
		for (TUltraStandardReport t : l) {
			UltraStandardReport u = new UltraStandardReport();
			BeanUtils.copyProperties(t, u);
			u.setPsCode(m1.get(t.getPsCode()));
			u.setPortCode(m2.get(t.getPortCode()));
			u.setFactorCode(m3.get(t.getFactorCode()));
			ul.add(u);
		}
		return ul;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public List<UltraStandardReport> today(UltraStandardReport demo, PageFilter ph) {
		if(demo==null){
			demo=new UltraStandardReport();
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
		List<UltraStandardReport> ul = new ArrayList<UltraStandardReport>();
		Map<String, Object> params = new HashMap<String, Object>();
		
		String hql = " from TUltraStandardReport t ";
		List<TUltraStandardReport> l = reportDao.find(hql + whereHql(demo, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		
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
		for (TUltraStandardReport t : l) {
			//if(i++>50) break;
			UltraStandardReport u = new UltraStandardReport();
			BeanUtils.copyProperties(t, u);
			u.setPsCode(m1.get(t.getPsCode()));
			u.setPortCode(m2.get(t.getPortCode()));
			u.setFactorCode(m3.get(t.getFactorCode()));
			ul.add(u);
		}
		return ul;
	}

	
	@Override
	public Long count(UltraStandardReport demo, PageFilter ph) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TUltraStandardReport t ";
		return reportDao.count("select count(*) " + hql + whereHql(demo, params), params);
	}
	
	private String whereHql(UltraStandardReport demo, Map<String, Object> params) {
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
			if (demo.getFactorCode() != null) {
				hql += " and t.psCode like :factor";
				params.put("factor", "%%" + demo.getFactorCode() + "%%");
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
