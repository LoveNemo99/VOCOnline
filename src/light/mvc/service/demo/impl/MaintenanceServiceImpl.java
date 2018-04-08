package light.mvc.service.demo.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.maintenance.TMaintenanceWorker;
import light.mvc.model.sys.TAlarmContact;
import light.mvc.model.sys.TAlarmType;
import light.mvc.pageModel.maintenance.MaintenanceWorker;
import light.mvc.pageModel.sys.AlarmContact;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.service.demo.MaintenanceServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaintenanceServiceImpl implements MaintenanceServiceI {

	@Autowired
	private BaseDaoI<TMaintenanceWorker> maintenanceWorkerDao;
	
	@Autowired
	private BaseDaoI<TAlarmType> typeDao;
	
	@Autowired
	private BaseDaoI<TAlarmContact> contactDao;

	/*-------------------------------------------------------------------------------------------------*/
	@Override
	public void add(MaintenanceWorker r) {
		TMaintenanceWorker t = new TMaintenanceWorker();
		
		t.setCompany(r.getCompany());
		t.setCode(r.getCode());
		t.setEducation(r.getEducation());
		t.setEmployeeType(r.getEmployeeType());
		t.setMajor(r.getMajor());
		t.setName(r.getName());
		t.setRemarks(r.getRemarks());
		t.setSex(r.getSex());
		t.setTeamName(r.getTeamName());
		t.setTel(r.getTel());
		
		maintenanceWorkerDao.save(t);
	}

	@Override
	public void delete(Integer id) {
		TMaintenanceWorker t = maintenanceWorkerDao.get(TMaintenanceWorker.class, id);
		maintenanceWorkerDao.delete(t);
	}

	@Override
	public void edit(MaintenanceWorker r) {
		TMaintenanceWorker t = maintenanceWorkerDao.get(TMaintenanceWorker.class, r.getId());
		t.setCompany(r.getCompany());
		t.setCode(r.getCode());
		t.setEducation(r.getEducation());
		t.setEmployeeType(r.getEmployeeType());
		t.setMajor(r.getMajor());
		t.setName(r.getName());
		t.setRemarks(r.getRemarks());
		t.setSex(r.getSex());
		t.setTeamName(r.getTeamName());
		t.setTel(r.getTel());
		maintenanceWorkerDao.update(t);
	}

	@Override
	public MaintenanceWorker get(Integer id) {
		TMaintenanceWorker t = maintenanceWorkerDao.get(TMaintenanceWorker.class, id);
		MaintenanceWorker r = new MaintenanceWorker();
		r.setId(t.getId());
		r.setCompany(t.getCompany());
		r.setCode(t.getCode());
		r.setEducation(t.getEducation());
		r.setEmployeeType(t.getEmployeeType());
		r.setMajor(t.getMajor());
		r.setName(t.getName());
		r.setRemarks(t.getRemarks());
		r.setSex(t.getSex());
		r.setTeamName(t.getTeamName());
		r.setTel(t.getTel());
		return r;
	}

	@Override
	public List<MaintenanceWorker> dataGridWorker(MaintenanceWorker demo, PageFilter ph) {
		List<MaintenanceWorker> ul = new ArrayList<MaintenanceWorker>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TMaintenanceWorker t ";
		List<TMaintenanceWorker> l = maintenanceWorkerDao.find(hql + whereHql(demo, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		for (TMaintenanceWorker t : l) {
			MaintenanceWorker u = new MaintenanceWorker();
			BeanUtils.copyProperties(t, u);
			ul.add(u);
		}
		return ul;
	}
	
	@Override
	public Long count(MaintenanceWorker demo, PageFilter ph) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TMaintenanceWorker t ";
		return maintenanceWorkerDao.count("select count(*) " + hql + whereHql(demo, params), params);
	}
	
	private String whereHql(MaintenanceWorker demo, Map<String, Object> params) {
		String hql = "";
		if (demo != null) {
			hql += " where 1=1 ";
			if (demo.getCompany() != null) {
				hql += " and t.company like :name";
				params.put("name", "%%" + demo.getCompany() + "%%");
			}
		}
		return hql;
	}
	
	/*-------------------------------------------------------------------------------------------------*/
	
	private String whereHql(AlarmContact demo, Map<String, Object> params) {
		String hql = "";
		if (demo != null) {
			hql += " where 1=1 ";
			if (demo.getPsCode() != null) {
				hql += " and t.psCode like :name";
				params.put("name", "%%" + demo.getPsCode() + "%%");
			}
		}
		return hql;
	}
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
