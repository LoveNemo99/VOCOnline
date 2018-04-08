package light.mvc.service.biz.impl;

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
import light.mvc.model.base.TScPollutantFactor;
import light.mvc.model.biz.TMonthPlan;
import light.mvc.model.biz.TMonthPlanMain;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.biz.MonthPlanMain;
import light.mvc.pageModel.biz.MonthPlanPollutant;
import light.mvc.service.biz.MonthPlanServiceI;


@Service
public class MonthPlanServiceImpl implements MonthPlanServiceI{

	@Autowired
	private BaseDaoI<TMonthPlanMain> lbDao;
	@Autowired
	private BaseDaoI<TPsPollutionSourceInfo> psDao;
	@Autowired
	private BaseDaoI<TMonthPlan> lpDao;
	@Autowired
	private BaseDaoI<TScPollutantFactor> factorDao;

	@Override
	public List<Tree> psTree(String code) {
		List<TPsPollutionSourceInfo> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		if(code != null && !code.equals("")){
			l = psDao.find("select distinct t from TPsPollutionSourceInfo t where t.code = '"+code+"'");
		}
		else{
			l = psDao.find("select distinct t from TPsPollutionSourceInfo t");
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
	
	@Override
	public void add(MonthPlanMain r) {
		TMonthPlanMain t = new TMonthPlanMain();
		BeanUtils.copyProperties(r, t);
		t.setIsSynchronize(false);
		t.setApprove("未申请");
		t.setSubmit("未提交");
		lbDao.save(t);
		
	}
	@Override
	public void delete(Integer id) {
		TMonthPlanMain t = lbDao.get(TMonthPlanMain.class, id);
		lbDao.delete(t);
		String hql = " from TMonthPlan t where t.mainId ="+id;
		List<TMonthPlan> l = lpDao.find(hql);
		if(l!=null&&l.size()!=0){
			for(TMonthPlan lp:l){
				lpDao.delete(lp);
			}
		}
	}
	@Override
	public Boolean submit(Integer id,String man) {
		TMonthPlanMain t = lbDao.get(TMonthPlanMain.class, id);
		if(t!=null){
			if(t.getSubmit()!=null && t.getSubmit().equals("未提交")){
				t.setSubmit("已提交");
				t.setApprove("已申请");
				t.setApplyMan(man);
				t.setApplyTime(new Date());
				lbDao.update(t);
				return true;
			}
		}
		return false;
	}
	@Override
	public Boolean grant(Integer id,String man,String s) {
		TMonthPlanMain t = lbDao.get(TMonthPlanMain.class, id);
		if(t!=null){
			if(t.getApprove()!=null && t.getApprove().equals("已申请")){
				t.setApprove(s);
				t.setApproveMan(man);
				t.setApproveTime(new Date());
				lbDao.update(t);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void edit(MonthPlanMain r) {
		TMonthPlanMain t = lbDao.get(TMonthPlanMain.class, r.getId());
//		String hql1 = " from TPsPollutionSourceInfo t where t.code = '"+r.getPsCode()+"'";
//		List<TPsPollutionSourceInfo> l1 = psDao.find(hql1);
		
		BeanUtils.copyProperties(r, t);
		t.setSubmit("未提交");
		t.setApprove("未申请");
//		if(l1!=null&&l1.size()!=0){
//			t.setPsCode(l1.get(0).getCode());
//		}
		t.setIsSynchronize(false);
		System.out.println(t);
		lbDao.saveOrUpdate(t);
	}
	@Override
	public void cause(Integer id ,String cause){
		if(id!=null){
			TMonthPlanMain t = lbDao.get(TMonthPlanMain.class, id);
			t.setCause(cause);
			lbDao.saveOrUpdate(t);
		}
	}
	@Override
	public MonthPlanMain get(Integer id) {
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		TMonthPlanMain t = lbDao.get(TMonthPlanMain.class, id);
		MonthPlanMain r= new MonthPlanMain();
		BeanUtils.copyProperties(t, r);
//		if(r.getEndDate()!=null){
//			r.setEndDateStr(sdf.format(r.getEndDate()));
//		}
//		if(r.getStartDate()!=null){
//			r.setStartDateStr(sdf.format(r.getStartDate()));
//		}
		return r;
	}
	@Override
	public List<MonthPlanPollutant> getFactorLimit(Integer id){
		List<MonthPlanPollutant> data = new ArrayList<MonthPlanPollutant>();
		String hql = " from TScPollutantFactor t where t.state = '启用'";
		List<TScPollutantFactor> factorList = factorDao.find(hql);
		String hql2 = " from TMonthPlanMain t where t.id = "+id;
		List<TMonthPlanMain> l2 = lbDao.find(hql2);
		String hql1 = " from TMonthPlan t where t.mainId = '"+l2.get(0).getId()+"'";
		List<TMonthPlan> lpList = lpDao.find(hql1);
		//TMonthPlanMain lbInfo = lbDao.get(TMonthPlanMain.class, id);
		if(factorList!=null){
			for(TScPollutantFactor factor:factorList){
				MonthPlanPollutant lInfo = new MonthPlanPollutant();
				for(TMonthPlan tlInfo:lpList){
					if(tlInfo.getCode().equals(factor.getCode())){
						BeanUtils.copyProperties(tlInfo, lInfo);
					}
				}
				lInfo.setMainId(id);
				lInfo.setFactorName(factor.getName());
				lInfo.setCode(factor.getCode());
				data.add(lInfo);
			}
		}
		return data;	
	}
	@Override
	public void editFactor(List<MonthPlanPollutant> r){
		//System.out.println(r);
		String hql2 = " from TMonthPlanMain t where t.id = "+r.get(0).getMainId();
		List<TMonthPlanMain> l2 = lbDao.find(hql2);
		TMonthPlanMain bInfo = l2.get(0);
		bInfo.setIsSynchronize(false);
		bInfo.setSubmit("未提交");
		bInfo.setApprove("未申请");
		lbDao.saveOrUpdate(bInfo);
		for(MonthPlanPollutant info:r){
			TMonthPlan t = new TMonthPlan();
			BeanUtils.copyProperties(info, t);
			lpDao.saveOrUpdate(t);
		}
	}
	@Override
	public List<MonthPlanMain> dataGrid(MonthPlanMain info,PageFilter ph) {
		List<MonthPlanMain> ul = new ArrayList<MonthPlanMain>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TMonthPlanMain t ";
		List<TMonthPlanMain> l = lbDao.find(hql + whereHql(info, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		
		String hql1 = " from TPsPollutionSourceInfo t";
		List<TPsPollutionSourceInfo> l1 = psDao.find(hql1);
		Map<String, String> m1 = new HashMap<String, String>();
		for(int i =0;i<l1.size();i++){
			m1.put(l1.get(i).getCode(), l1.get(i).getPsName());
		}
		
		for (TMonthPlanMain t : l) {
			MonthPlanMain u = new MonthPlanMain();
			BeanUtils.copyProperties(t, u);
			u.setPsCode(m1.get(t.getPsCode()));
			ul.add(u);
		}
		return ul;
	}
	
	@Override
	public Long count(MonthPlanMain demo, PageFilter ph){
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TMonthPlanMain t ";
		return lbDao.count("select count(*) " + hql + whereHql(demo, params), params);
	}
	
	private String whereHql(MonthPlanMain demo, Map<String, Object> params) {
		String hql = "";
		if (demo != null) {
			hql += " where 1=1 ";
			if (demo.getPsCode() != null) {
				hql += " and t.psCode like :name";
				params.put("name", "%%" + demo.getPsCode() + "%%");
			}
			if(demo.getMonth() != null){
				hql += " and t.month like :month";
				params.put("month", "%%" + demo.getMonth() + "%%");
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
