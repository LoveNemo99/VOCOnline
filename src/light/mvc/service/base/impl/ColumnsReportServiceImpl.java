package light.mvc.service.base.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.base.TColumnsReport;
import light.mvc.model.base.TScMeasureUnit;
import light.mvc.model.base.TScPollutantFactor;
import light.mvc.pageModel.base.ColumnsReport;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.service.base.ColumnsReportServiceI;

@Service
public class ColumnsReportServiceImpl implements ColumnsReportServiceI{

	@Autowired
	private BaseDaoI<TColumnsReport> tDao;
	@Autowired
	private BaseDaoI<TScPollutantFactor> factorDao;
	@Autowired
	private BaseDaoI<TScMeasureUnit> unitDao;
	@Override
	public List<Tree> unitTree() {
		List<TScMeasureUnit> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		l = unitDao.find("select distinct t from TScMeasureUnit t");

		if ((l != null) && (l.size() > 0)) {
			for (TScMeasureUnit r : l) {
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
	public List<Tree> factorTree() {
		List<TScPollutantFactor> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		l = factorDao.find("select distinct t from TScPollutantFactor t");

		if ((l != null) && (l.size() > 0)) {
			for (TScPollutantFactor r : l) {
				Tree tree = new Tree();
				tree.setId(r.getId().toString());
				tree.setText(r.getCode());
				tree.setValue(r.getCode());
				lt.add(tree);
			}
		}
		return lt;
	}
	@Override
	public void add(ColumnsReport r,String type) {
		TColumnsReport t = new TColumnsReport();
		BeanUtils.copyProperties(r, t);
		t.setType(type);
		tDao.save(t);
	}
	@Override
	public void delete(Integer id) {
		TColumnsReport t = tDao.get(TColumnsReport.class, id);
		tDao.delete(t);
	}
	@Override
	public void edit(ColumnsReport r,String type) {
		TColumnsReport t = tDao.get(TColumnsReport.class, r.getId());
		BeanUtils.copyProperties(r, t);
		t.setType(type);
		tDao.update(t);
	}
	@Override
	public ColumnsReport get(Integer id) {
		TColumnsReport t = tDao.get(TColumnsReport.class, id);
//		String hql2 = " from TScPollutantFactor t";
//		List<TScPollutantFactor> sl = factorDao.find(hql2);
//		Map<String, String> m1 = new HashMap<String, String>();
//		for(int i =0;i<sl.size();i++){
//			m1.put(sl.get(i).getCode(), sl.get(i).getName());
//		}
//		
//		String hql3 = " from TScMeasureUnit t";
//		List<TScMeasureUnit> tl = unitDao.find(hql3);
//		Map<String, String> m3 = new HashMap<String, String>();
//		Map<String, String> m4 = new HashMap<String, String>();
//		for(int i =0;i<tl.size();i++){
//			m3.put(tl.get(i).getCode(), tl.get(i).getName());
//			m4.put(tl.get(i).getCode(), tl.get(i).getSymbol());
//		}
		ColumnsReport r= new ColumnsReport();
		BeanUtils.copyProperties(t, r);
		
//		r.setPollutantFactorCode(m1.get(t.getPollutantFactorCode()));
//		r.setUnitCode(m3.get(t.getUnitCode()));
//		r.setUnitSymbol(m4.get(t.getUnitCode()));
		return r;
	}
	
	@Override
	public List<ColumnsReport> dataGrid(ColumnsReport info,PageFilter ph,String type) {
		List<ColumnsReport> ul = new ArrayList<ColumnsReport>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TColumnsReport t where t.type = '"+type+"'";
		List<TColumnsReport> l = tDao.find(hql + orderHql(ph), params, ph.getPage(), ph.getRows());
		
		String hql2 = " from TScPollutantFactor t";
		List<TScPollutantFactor> sl = factorDao.find(hql2);
		Map<String, String> m1 = new HashMap<String, String>();
		for(int i =0;i<sl.size();i++){
			m1.put(sl.get(i).getCode(), sl.get(i).getName());
		}
		
		String hql3 = " from TScMeasureUnit t";
		List<TScMeasureUnit> tl = unitDao.find(hql3);
		Map<String, String> m3 = new HashMap<String, String>();
		Map<String, String> m4 = new HashMap<String, String>();
		for(int i =0;i<tl.size();i++){
			m3.put(tl.get(i).getCode(), tl.get(i).getName());
			m4.put(tl.get(i).getCode(), tl.get(i).getSymbol());
		}
		
		for (TColumnsReport t : l) {
			ColumnsReport u = new ColumnsReport();
			BeanUtils.copyProperties(t, u);
			//u.setPollutantFactorCode(m1.get(t.getPollutantFactorCode()));
			u.setUnitCode(m3.get(t.getUnitCode()));
			u.setUnitSymbol(m4.get(t.getUnitCode()));
			ul.add(u);
		}
		return ul;
	}
	
	@Override
	public Long count(ColumnsReport demo, PageFilter ph){
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TPsPiPort t ";
		return tDao.count("select count(*) " + hql + whereHql(demo, params), params);
	}
	
	private String whereHql(ColumnsReport demo, Map<String, Object> params) {
		String hql = "";
		if (demo != null) {
			hql += " where 1=1 ";
			if (demo.getColumnName() != null) {
				hql += " and t.portname like :name";
				params.put("name", "%%" + demo.getColumnName() + "%%");
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
