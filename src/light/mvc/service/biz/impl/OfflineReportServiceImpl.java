package light.mvc.service.biz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.base.TPsPiPort;
import light.mvc.model.base.TPsPollutionSourceInfo;
import light.mvc.model.biz.TOfflineReport;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.biz.OfflineReport;
import light.mvc.service.biz.OfflineReportServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfflineReportServiceImpl implements OfflineReportServiceI {

	@Autowired
	private BaseDaoI<TOfflineReport> recordDao;
	@Autowired
	private BaseDaoI<TPsPiPort> portDao;
	@Autowired
	private BaseDaoI<TPsPollutionSourceInfo> psDao;

	/*-------------------------------------------------------------------------------------------------*/
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
	public void add(OfflineReport r) {
		TOfflineReport t = new TOfflineReport();
		BeanUtils.copyProperties(r, t);
		recordDao.save(t);
	}

	@Override
	public void delete(Integer id) {
		TOfflineReport t = recordDao.get(TOfflineReport.class, id);
		recordDao.delete(t);
	}

	@Override
	public void edit(OfflineReport r) {
		TOfflineReport t = recordDao.get(TOfflineReport.class, r.getId());
		BeanUtils.copyProperties(r, t);
		recordDao.update(t);
	}

	@Override
	public OfflineReport get(Long id) {
		TOfflineReport t = recordDao.get(TOfflineReport.class, id);
		OfflineReport r = new OfflineReport();
		BeanUtils.copyProperties(t, r);
		return r;
	}

	@Override
	public List<OfflineReport> dataGrid(OfflineReport demo, PageFilter ph) {
		List<OfflineReport> ul = new ArrayList<OfflineReport>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TOfflineReport t ";
		List<TOfflineReport> l = recordDao.find(hql + whereHql(demo, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		
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
		for (TOfflineReport t : l) {
			OfflineReport u = new OfflineReport();
			BeanUtils.copyProperties(t, u);
			u.setPsCode(m1.get(t.getPsCode()));
			u.setPortCode(m2.get(t.getPortCode()));
			ul.add(u);
		}
		return ul;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public List<OfflineReport> today(OfflineReport demo, PageFilter ph) {
		if(demo==null){
			demo=new OfflineReport();
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
		List<OfflineReport> ul = new ArrayList<OfflineReport>();
		Map<String, Object> params = new HashMap<String, Object>();
		
		String hql = " from TOfflineReport t ";
		List<TOfflineReport> l = recordDao.find(hql + whereHql(demo, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		
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
		
		for (TOfflineReport t : l) {
			//if(i++>50) break;
			OfflineReport u = new OfflineReport();
			BeanUtils.copyProperties(t, u);
			u.setPsCode(m1.get(t.getPsCode()));
			u.setPortCode(m2.get(t.getPortCode()));
			ul.add(u);
		}
		return ul;
	}

	
	@Override
	public Long count(OfflineReport demo, PageFilter ph) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TOfflineReport t ";
		return recordDao.count("select count(*) " + hql + whereHql(demo, params), params);
	}
	
	private String whereHql(OfflineReport demo, Map<String, Object> params) {
		String hql = "";
		if (demo != null) {
			hql += " where 1=1 ";
			if (demo.getPsCode() != null) {
				hql += " and t.psCode like :name";
				params.put("name", "%%" + demo.getPsCode() + "%%");
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
