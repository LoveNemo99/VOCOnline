package light.mvc.service.base.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.base.TPsPollutionSourceInfo;
import light.mvc.model.base.TScAdministrativeDivision;
import light.mvc.model.base.TScBasin;
import light.mvc.model.base.TScEnterpriseAffiliation;
import light.mvc.model.base.TScEnterpriseCategory;
import light.mvc.model.base.TScEnterpriseRegistration;
import light.mvc.model.base.TScEnterpriseSize;
import light.mvc.model.base.TScIndustryCategory;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.PsPollutionSourceInfo;
import light.mvc.pageModel.base.Tree;
import light.mvc.service.base.PollutionSourceInfoServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PollutionSourceInfoServiceImpl implements PollutionSourceInfoServiceI{

	@Autowired
	private BaseDaoI<TPsPollutionSourceInfo> psDao;
	@Autowired
	private BaseDaoI<TScBasin> basinDao;//流域
	@Autowired
	private BaseDaoI<TScAdministrativeDivision> divisionDao;//行政区划
	@Autowired
	private BaseDaoI<TScEnterpriseAffiliation> affiliationDao;//隶属关系
	@Autowired
	private BaseDaoI<TScEnterpriseCategory> enCategoryDao;//企业类别
	@Autowired
	private BaseDaoI<TScEnterpriseRegistration> enRegistrationDao;//企业注册类型
	@Autowired
	private BaseDaoI<TScEnterpriseSize> sizeDao;//企业规模
	@Autowired
	private BaseDaoI<TScIndustryCategory> inCategoryDao;//行业类别
	@Override
	public List<Tree> tree() {
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
	public List<Tree> inCategoryTree() {
		List<TScIndustryCategory> l = null;List<Tree> lt = new ArrayList<Tree>();
		l = inCategoryDao.find("select distinct t from TScIndustryCategory t");
		if ((l != null) && (l.size() > 0)) {
			for (TScIndustryCategory r : l) {
				Tree tree = new Tree();tree.setId(r.getId().toString());
				tree.setText(r.getName());tree.setValue(r.getCode());
				lt.add(tree);}
		}return lt;
	}
	@Override
	public List<Tree> sizeTree() {
		List<TScEnterpriseSize> l = null;List<Tree> lt = new ArrayList<Tree>();
		l = sizeDao.find("select distinct t from TScEnterpriseSize t");
		if ((l != null) && (l.size() > 0)) {
			for (TScEnterpriseSize r : l) {
				Tree tree = new Tree();tree.setId(r.getId().toString());
				tree.setText(r.getName());tree.setValue(r.getCode());
				lt.add(tree);}
		}return lt;
	}
	@Override
	public List<Tree> enRegistrationTree() {
		List<TScEnterpriseRegistration> l = null;List<Tree> lt = new ArrayList<Tree>();
		l = enRegistrationDao.find("select distinct t from TScEnterpriseRegistration t");
		if ((l != null) && (l.size() > 0)) {
			for (TScEnterpriseRegistration r : l) {
				Tree tree = new Tree();tree.setId(r.getId().toString());
				tree.setText(r.getName());tree.setValue(r.getCode());
				lt.add(tree);}
		}return lt;
	}
	@Override
	public List<Tree> enCategoryTree() {
		List<TScEnterpriseCategory> l = null;List<Tree> lt = new ArrayList<Tree>();
		l = enCategoryDao.find("select distinct t from TScEnterpriseCategory t");
		if ((l != null) && (l.size() > 0)) {
			for (TScEnterpriseCategory r : l) {
				Tree tree = new Tree();tree.setId(r.getId().toString());
				tree.setText(r.getName());tree.setValue(r.getCode());
				lt.add(tree);}
		}return lt;
	}
	@Override
	public List<Tree> affiliationTree() {
		List<TScEnterpriseAffiliation> l = null;List<Tree> lt = new ArrayList<Tree>();
		l = affiliationDao.find("select distinct t from TScEnterpriseAffiliation t");
		if ((l != null) && (l.size() > 0)) {
			for (TScEnterpriseAffiliation r : l) {
				Tree tree = new Tree();tree.setId(r.getId().toString());
				tree.setText(r.getName());tree.setValue(r.getCode());
				lt.add(tree);}
		}return lt;
	}
	@Override
	public List<Tree> divisionTree() {
		List<TScAdministrativeDivision> l = null;List<Tree> lt = new ArrayList<Tree>();
		l = divisionDao.find("select distinct t from TScAdministrativeDivision t");
		if ((l != null) && (l.size() > 0)) {
			for (TScAdministrativeDivision r : l) {
				Tree tree = new Tree();tree.setId(r.getId().toString());
				tree.setText(r.getName());tree.setValue(r.getCode());
				lt.add(tree);}
		}return lt;
	}
	@Override
	public List<Tree> basinTree() {
		List<TScBasin> l = null;List<Tree> lt = new ArrayList<Tree>();
		l = basinDao.find("select distinct t from TScBasin t");
		if ((l != null) && (l.size() > 0)) {
			for (TScBasin r : l) {
				Tree tree = new Tree();tree.setId(r.getId().toString());
				tree.setText(r.getBasinName());tree.setValue(r.getBasinCode());
				lt.add(tree);}
		}return lt;
	}
	@Override
	public void add(PsPollutionSourceInfo r) {
		TPsPollutionSourceInfo t = new TPsPollutionSourceInfo();
		BeanUtils.copyProperties(r, t);
		if(r.getBasinCode()!=null&&r.getBasinCode().length()!=0){
			t.setBasinCode(r.getBasinCode());
		}
		if(r.getEnterpriseSizeCode()!=null&&r.getEnterpriseSizeCode().length()!=0){
			t.setEnterpriseSizeCode(r.getEnterpriseSizeCode());
		}
		if(r.getEnterpriseCategoryCode()!=null&&r.getEnterpriseCategoryCode().length()!=0){
			t.setEnterpriseCategoryCode(r.getEnterpriseCategoryCode());
		}
		if(r.getIndustryCategoryCode()!=null&&r.getIndustryCategoryCode().length()!=0){
			t.setIndustryCategoryCode(r.getIndustryCategoryCode());
		}
		if(r.getAdministrativeDivisionCode()!=null&&r.getAdministrativeDivisionCode().length()!=0){
			t.setAdministrativeDivisionCode(r.getAdministrativeDivisionCode());
		}
		if(r.getRegistrationCode()!=null&&r.getRegistrationCode().length()!=0){
			t.setRegistrationCode(r.getRegistrationCode());
		}
		if(r.getEnterpriseAffiliationCode()!=null&&r.getEnterpriseAffiliationCode().length()!=0){
			t.setEnterpriseAffiliationCode(r.getEnterpriseAffiliationCode());
		}
		psDao.save(t);
		
	}
	@Override
	public void delete(Integer id) {
		TPsPollutionSourceInfo t = psDao.get(TPsPollutionSourceInfo.class, id);
		psDao.delete(t);
		
	}
	@Override
	public void edit(PsPollutionSourceInfo r) {
		String hql1 = " from TScAdministrativeDivision t where t.name = '"+r.getAdministrativeDivisionCode()+"'";
		List<TScAdministrativeDivision> l1 = divisionDao.find(hql1);
		String hql2 = " from TScBasin t where t.basinName = '"+r.getBasinCode()+"'";
		List<TScBasin> l2 = basinDao.find(hql2);
		String hql3 = " from TScEnterpriseAffiliation t where t.name = '"+r.getEnterpriseAffiliationCode()+"'";
		List<TScEnterpriseAffiliation> l3 = affiliationDao.find(hql3);
		String hql4 = " from TScEnterpriseCategory t where t.name = '"+r.getEnterpriseCategoryCode()+"'";
		List<TScEnterpriseCategory> l4 = enCategoryDao.find(hql4);
		
		String hql5 = " from TScEnterpriseRegistration t where t.name = '"+r.getRegistrationCode()+"'";
		List<TScEnterpriseRegistration> l5 = enRegistrationDao.find(hql5);
		String hql6 = " from TScEnterpriseSize t where t.name = '"+r.getEnterpriseSizeCode()+"'";
		List<TScEnterpriseSize> l6 = sizeDao.find(hql6);
		String hql7= " from TScIndustryCategory t where t.name = '"+r.getIndustryCategoryCode()+"'";
		List<TScIndustryCategory> l7 = inCategoryDao.find(hql7);
		TPsPollutionSourceInfo t = psDao.get(TPsPollutionSourceInfo.class, r.getId());
		BeanUtils.copyProperties(r, t);
		
		if(l1!=null&&l1.size()!=0){
			t.setAdministrativeDivisionCode(l1.get(0).getCode());
		}
		if(l2!=null&&l2.size()!=0){
			t.setBasinCode(l2.get(0).getBasinCode());
		}
		if(l3!=null&&l3.size()!=0){
			t.setEnterpriseAffiliationCode(l3.get(0).getCode());
		}
		if(l4!=null&&l4.size()!=0){
			t.setEnterpriseCategoryCode(l4.get(0).getCode());
		}
		if(l5!=null&&l5.size()!=0){
			t.setRegistrationCode(l5.get(0).getCode());
		}
		if(l6!=null&&l6.size()!=0){
			t.setEnterpriseSizeCode(l6.get(0).getCode());
		}
		if(l7!=null&&l7.size()!=0){
			t.setIndustryCategoryCode(l7.get(0).getCode());
		}
		psDao.update(t);
	}
	@Override
	public PsPollutionSourceInfo get(Integer id) {
		TPsPollutionSourceInfo t = psDao.get(TPsPollutionSourceInfo.class, id);
		PsPollutionSourceInfo r= new PsPollutionSourceInfo();
		BeanUtils.copyProperties(t, r);
		String hql1 = " from TScAdministrativeDivision t";
		List<TScAdministrativeDivision> l1 = divisionDao.find(hql1);
		Map<String, String> m1 = new HashMap<String, String>();
		for(int i =0;i<l1.size();i++){
			m1.put(l1.get(i).getCode(), l1.get(i).getName());
		}
		String hql2 = " from TScBasin t";
		List<TScBasin> l2 = basinDao.find(hql2);
		Map<String, String> m2 = new HashMap<String, String>();
		for(int i =0;i<l2.size();i++){
			m2.put(l2.get(i).getBasinCode(), l2.get(i).getBasinName());
		}
		String hql3 = " from TScEnterpriseAffiliation t";
		List<TScEnterpriseAffiliation> l3 = affiliationDao.find(hql3);
		Map<String, String> m3 = new HashMap<String, String>();
		for(int i =0;i<l3.size();i++){
			m3.put(l3.get(i).getCode(), l3.get(i).getName());
		}
		String hql4 = " from TScEnterpriseCategory t";
		List<TScEnterpriseCategory> l4 = enCategoryDao.find(hql4);
		Map<String, String> m4 = new HashMap<String, String>();
		for(int i =0;i<l4.size();i++){
			m4.put(l4.get(i).getCode(), l4.get(i).getName());
		}
		String hql5 = " from TScEnterpriseRegistration t";
		List<TScEnterpriseRegistration> l5 = enRegistrationDao.find(hql5);
		Map<String, String> m5 = new HashMap<String, String>();
		for(int i =0;i<l5.size();i++){
			m5.put(l5.get(i).getCode(), l5.get(i).getName());
		}
		String hql6 = " from TScEnterpriseSize t";
		List<TScEnterpriseSize> l6 = sizeDao.find(hql6);
		Map<String, String> m6 = new HashMap<String, String>();
		for(int i =0;i<l6.size();i++){
			m6.put(l6.get(i).getCode(), l6.get(i).getName());
		}
		String hql7 = " from TScIndustryCategory t";
		List<TScIndustryCategory> l7 = inCategoryDao.find(hql7);
		Map<String, String> m7 = new HashMap<String, String>();
		for(int i =0;i<l7.size();i++){
			m7.put(l7.get(i).getCode(), l7.get(i).getName());
		}
		
		if(t.getAdministrativeDivisionCode()!=null){
			r.setAdministrativeDivisionCode(m1.get(t.getAdministrativeDivisionCode()));
		}
		if(t.getBasinCode()!=null){
			r.setBasinCode(m2.get(t.getBasinCode()));
		}
		if(t.getEnterpriseAffiliationCode()!=null){
			r.setEnterpriseAffiliationCode(m3.get(t.getEnterpriseAffiliationCode()));
		}
		if(t.getEnterpriseCategoryCode()!=null){
			r.setEnterpriseCategoryCode(m4.get(t.getEnterpriseCategoryCode()));
		}
		if(t.getRegistrationCode()!=null){
			r.setRegistrationCode(m5.get(t.getRegistrationCode()));
		}
		if(t.getEnterpriseSizeCode()!=null){
			r.setEnterpriseSizeCode(m6.get(t.getEnterpriseSizeCode()));
		}
		if(t.getIndustryCategoryCode()!=null){
			r.setIndustryCategoryCode(m7.get(t.getIndustryCategoryCode()));
		}
		return r;
	}
	@Override
	public List<PsPollutionSourceInfo> dataGrid(PsPollutionSourceInfo info,PageFilter ph) {
		List<PsPollutionSourceInfo> ul = new ArrayList<PsPollutionSourceInfo>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TPsPollutionSourceInfo t ";
		List<TPsPollutionSourceInfo> l = psDao.find(hql + whereHql(info, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		
		String hql1 = " from TScAdministrativeDivision t";
		List<TScAdministrativeDivision> l1 = divisionDao.find(hql1);
		Map<String, String> m1 = new HashMap<String, String>();
		for(int i =0;i<l1.size();i++){
			m1.put(l1.get(i).getCode(), l1.get(i).getName());
		}
		String hql2 = " from TScBasin t";
		List<TScBasin> l2 = basinDao.find(hql2);
		Map<String, String> m2 = new HashMap<String, String>();
		for(int i =0;i<l2.size();i++){
			m2.put(l2.get(i).getBasinCode(), l2.get(i).getBasinName());
		}
		String hql3 = " from TScEnterpriseAffiliation t";
		List<TScEnterpriseAffiliation> l3 = affiliationDao.find(hql3);
		Map<String, String> m3 = new HashMap<String, String>();
		for(int i =0;i<l3.size();i++){
			m3.put(l3.get(i).getCode(), l3.get(i).getName());
		}
		String hql4 = " from TScEnterpriseCategory t";
		List<TScEnterpriseCategory> l4 = enCategoryDao.find(hql4);
		Map<String, String> m4 = new HashMap<String, String>();
		for(int i =0;i<l4.size();i++){
			m4.put(l4.get(i).getCode(), l4.get(i).getName());
		}
		String hql5 = " from TScEnterpriseRegistration t";
		List<TScEnterpriseRegistration> l5 = enRegistrationDao.find(hql5);
		Map<String, String> m5 = new HashMap<String, String>();
		for(int i =0;i<l5.size();i++){
			m5.put(l5.get(i).getCode(), l5.get(i).getName());
		}
		String hql6 = " from TScEnterpriseSize t";
		List<TScEnterpriseSize> l6 = sizeDao.find(hql6);
		Map<String, String> m6 = new HashMap<String, String>();
		for(int i =0;i<l6.size();i++){
			m6.put(l6.get(i).getCode(), l6.get(i).getName());
		}
		String hql7 = " from TScIndustryCategory t";
		List<TScIndustryCategory> l7 = inCategoryDao.find(hql7);
		Map<String, String> m7 = new HashMap<String, String>();
		for(int i =0;i<l7.size();i++){
			m7.put(l7.get(i).getCode(), l7.get(i).getName());
		}
		
		for (TPsPollutionSourceInfo t : l) {
			PsPollutionSourceInfo u = new PsPollutionSourceInfo();
			BeanUtils.copyProperties(t, u);
			if(t.getAdministrativeDivisionCode()!=null){
				u.setAdministrativeDivisionCode(m1.get(t.getAdministrativeDivisionCode()));
			}
			if(t.getBasinCode()!=null){
				u.setBasinCode(m2.get(t.getBasinCode()));
			}
			if(t.getEnterpriseAffiliationCode()!=null){
				u.setEnterpriseAffiliationCode(m3.get(t.getEnterpriseAffiliationCode()));
			}
			if(t.getEnterpriseCategoryCode()!=null){
				u.setEnterpriseCategoryCode(m4.get(t.getEnterpriseCategoryCode()));
			}
			if(t.getRegistrationCode()!=null){
				u.setRegistrationCode(m5.get(t.getRegistrationCode()));
			}
			if(t.getEnterpriseSizeCode()!=null){
				u.setEnterpriseSizeCode(m6.get(t.getEnterpriseSizeCode()));
			}
			if(t.getIndustryCategoryCode()!=null){
				u.setIndustryCategoryCode(m7.get(t.getIndustryCategoryCode()));
			}
			
			ul.add(u);
		}
		return ul;
	}
	
	@Override
	public Long count(PsPollutionSourceInfo demo, PageFilter ph){
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TPsPollutionSourceInfo t ";
		return psDao.count("select count(*) " + hql + whereHql(demo, params), params);
	}
	
	private String whereHql(PsPollutionSourceInfo demo, Map<String, Object> params) {
		String hql = "";
		if (demo != null) {
			hql += " where 1=1 ";
			if (demo.getPsName() != null) {
				hql += " and t.psxName like :name";
				params.put("name", "%%" + demo.getPsName() + "%%");
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
