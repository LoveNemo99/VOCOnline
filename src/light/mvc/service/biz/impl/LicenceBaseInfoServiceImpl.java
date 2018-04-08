package light.mvc.service.biz.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.base.TPsPollutionSourceInfo;
import light.mvc.model.base.TScPollutantFactor;
import light.mvc.model.biz.TLicenceBaseInfo;
import light.mvc.model.biz.TLicencePollutantInfo;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.biz.LicenceBaseInfo;
import light.mvc.pageModel.biz.LicencePollutantInfo;
import light.mvc.service.biz.LicenceBaseInfoServiceI;

@Service
public class LicenceBaseInfoServiceImpl implements LicenceBaseInfoServiceI{

	@Autowired
	private BaseDaoI<TLicenceBaseInfo> lbDao;
	@Autowired
	private BaseDaoI<TPsPollutionSourceInfo> psDao;
	@Autowired
	private BaseDaoI<TLicencePollutantInfo> lpDao;
	@Autowired
	private BaseDaoI<TScPollutantFactor> factorDao;

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
	public void add(LicenceBaseInfo r) {
		TLicenceBaseInfo t = new TLicenceBaseInfo();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			r.setStartDate(sdf.parse(r.getStartDateStr()));
			r.setEndDate(sdf.parse(r.getEndDateStr()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		BeanUtils.copyProperties(r, t);
		t.setIsSynchronize(false);
		lbDao.save(t);
		
	}
	@Override
	public void delete(Integer id) {
		TLicenceBaseInfo t = lbDao.get(TLicenceBaseInfo.class, id);
		lbDao.delete(t);
		String hql = " from TLicencePollutantInfo t where t.licenceBaseId ="+id;
		List<TLicencePollutantInfo> l = lpDao.find(hql);
		if(l!=null&&l.size()!=0){
			for(TLicencePollutantInfo lp:l){
				lpDao.delete(lp);
			}
		}
	}
	@Override
	public void edit(LicenceBaseInfo r) {
		TLicenceBaseInfo t = lbDao.get(TLicenceBaseInfo.class, r.getId());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			r.setEndDate(sdf.parse(r.getEndDateStr()));
			r.setStartDate(sdf.parse(r.getStartDateStr()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String hql1 = " from TPsPollutionSourceInfo t where t.code = '"+r.getPsCode()+"'";
		List<TPsPollutionSourceInfo> l1 = psDao.find(hql1);
		
		BeanUtils.copyProperties(r, t);
		
		if(l1!=null&&l1.size()!=0){
			t.setPsCode(l1.get(0).getCode());
		}
		t.setIsSynchronize(false);
		
		lbDao.update(t);
	}
	@Override
	public LicenceBaseInfo get(Integer id) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		TLicenceBaseInfo t = lbDao.get(TLicenceBaseInfo.class, id);
		//TPsPollutionSourceInfo ps = psDao.get(TPsPollutionSourceInfo.class, t.getPsBaseId());
		LicenceBaseInfo r= new LicenceBaseInfo();
		BeanUtils.copyProperties(t, r);
		if(r.getEndDate()!=null){
			r.setEndDateStr(sdf.format(r.getEndDate()));
		}
		if(r.getStartDate()!=null){
			r.setStartDateStr(sdf.format(r.getStartDate()));
		}
		
		//r.setPsName(ps.getPsName());
		
		return r;
	}
	@Override
	public List<LicencePollutantInfo> getFactorLimit(Integer id){
		List<LicencePollutantInfo> data = new ArrayList<LicencePollutantInfo>();
		String hql = " from TScPollutantFactor t where t.state = '启用'";
		List<TScPollutantFactor> factorList = factorDao.find(hql);
		String hql2 = " from TLicenceBaseInfo t where t.id = "+id;
		List<TLicenceBaseInfo> l2 = lbDao.find(hql2);
		String hql1 = " from TLicencePollutantInfo t where t.licenceNum = '"+l2.get(0).getLicenceNum()+"'";
		List<TLicencePollutantInfo> lpList = lpDao.find(hql1);
		TLicenceBaseInfo lbInfo = lbDao.get(TLicenceBaseInfo.class, id);
		if(factorList!=null){
			for(TScPollutantFactor factor:factorList){
				LicencePollutantInfo lInfo = new LicencePollutantInfo();
				for(TLicencePollutantInfo tlInfo:lpList){
					if(tlInfo.getPollutantCode().equals(factor.getCode())){
						BeanUtils.copyProperties(tlInfo, lInfo);
					}
				}
				//lInfo.setLicenceBaseId(id);
				lInfo.setPollutantCode(factor.getCode());
				lInfo.setFactorName(factor.getName());
				lInfo.setLicenceNum(lbInfo.getLicenceNum());
				data.add(lInfo);
			}
		}
		return data;	
	}
	@Override
	public void editFactor(List<LicencePollutantInfo> r){
		//System.out.println(r);
		String hql2 = " from TLicenceBaseInfo t where t.licenceNum = "+r.get(0).getLicenceNum();
		List<TLicenceBaseInfo> l2 = lbDao.find(hql2);
		TLicenceBaseInfo bInfo = l2.get(0);
		bInfo.setIsSynchronize(false);
		
		lbDao.saveOrUpdate(bInfo);
		for(LicencePollutantInfo info:r){
			TLicencePollutantInfo t = new TLicencePollutantInfo();
			BeanUtils.copyProperties(info, t);
			lpDao.saveOrUpdate(t);
		}
	}
	@Override
	public List<LicenceBaseInfo> dataGrid(LicenceBaseInfo info,PageFilter ph) {
		List<LicenceBaseInfo> ul = new ArrayList<LicenceBaseInfo>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TLicenceBaseInfo t ";
		List<TLicenceBaseInfo> l = lbDao.find(hql + whereHql(info, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		
		String hql1 = " from TPsPollutionSourceInfo t";
		List<TPsPollutionSourceInfo> l1 = psDao.find(hql1);
		Map<String, String> m1 = new HashMap<String, String>();
		for(int i =0;i<l1.size();i++){
			m1.put(l1.get(i).getCode(), l1.get(i).getPsName());
		}
		
		for (TLicenceBaseInfo t : l) {
			LicenceBaseInfo u = new LicenceBaseInfo();
			BeanUtils.copyProperties(t, u);
			u.setPsCode(m1.get(t.getPsCode()));
			ul.add(u);
		}
		return ul;
	}
	
	@Override
	public Long count(LicenceBaseInfo demo, PageFilter ph){
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TLicenceBaseInfo t ";
		return lbDao.count("select count(*) " + hql + whereHql(demo, params), params);
	}
	
	private String whereHql(LicenceBaseInfo demo, Map<String, Object> params) {
		String hql = "";
		if (demo != null) {
			hql += " where 1=1 ";
			/*if (demo.getName() != null) {
				hql += " and t.name like :name";
				params.put("name", "%%" + demo.get + "%%");
			}*/
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
