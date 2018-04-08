package light.mvc.service.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.base.TPsPiPort;
import light.mvc.model.base.TPsPollutionSourceInfo;
import light.mvc.model.biz.TCtrlPwd;
import light.mvc.model.biz.TGateWayDeviceCtrl;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.biz.GateWayDeviceCtrl;
import light.mvc.service.biz.GateWayDeviceCtrlServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GateWayDeviceCtrlServiceImpl implements GateWayDeviceCtrlServiceI {
	@Autowired
	private BaseDaoI<TGateWayDeviceCtrl> deviceCtrlDao;
	@Autowired
	private BaseDaoI<TPsPiPort> portDao;
	@Autowired
	private BaseDaoI<TPsPollutionSourceInfo> sourceDao;
	@Autowired
	private BaseDaoI<TCtrlPwd> pwdDao;
	/*#########################################################################################################*/
	
	@Override
	public TPsPiPort getPortInfo(String psCode){
		String hql = "from TPsPiPort t where t.psCode = '"+psCode+"'";
		List<TPsPiPort> l = portDao.find(hql);
		if(l==null)
			return null;
		return l.get(0);
	}
	@Override
	public Boolean validatePwd(String userPwd){
		if(userPwd==null)
			return false;
		String hql = "from TCtrlPwd t";
		List<TCtrlPwd> l = pwdDao.find(hql);
		if(l==null)
			return false;
		if(l.size()!=0){
			if(userPwd.equals(l.get(0).getPassword())){
				return true;
			}
		}
		return false;
	}
	@Override
	public TPsPollutionSourceInfo getPsInfo(String psCode){
		String hql = "from TPsPollutionSourceInfo t where t.code = '"+psCode+"'";
		List<TPsPollutionSourceInfo> info = sourceDao.find(hql);
		if(info == null)
			return null;
		return info.get(0);
	}
	
	@Override
	public void add(GateWayDeviceCtrl r) {
		TGateWayDeviceCtrl t = new TGateWayDeviceCtrl();
		t.setCtrlCode(r.getCtrlCode());
		t.setExecuteTime(r.getExecuteTime());
		t.setExeRtn(r.getExeRtn());
		t.setMn(r.getMn());
		t.setParam(r.getParam());
		t.setPsCode(r.getPsCode());
		t.setPsName(r.getPsName());
		t.setQn(r.getQn());
		t.setQnRtn(r.getQnRtn());
		t.setResult(r.getResult());
		t.setRemark(r.getRemark());
		deviceCtrlDao.save(t);
	}

	@Override
	public void deleteGateWay(Integer id) {
		TGateWayDeviceCtrl t = deviceCtrlDao.get(TGateWayDeviceCtrl.class, id);
		deviceCtrlDao.delete(t);
	}

	@Override
	public void edit(GateWayDeviceCtrl r) {
		TGateWayDeviceCtrl t = deviceCtrlDao.get(TGateWayDeviceCtrl.class, r.getId());
		t.setCtrlCode(r.getCtrlCode());
		t.setExecuteTime(r.getExecuteTime());
		t.setExeRtn(r.getExeRtn());
		t.setMn(r.getMn());
		t.setParam(r.getParam());
		t.setPsCode(r.getPsCode());
		t.setPsName(r.getPsName());
		t.setQn(r.getQn());
		t.setQnRtn(r.getQnRtn());
		t.setResult(r.getResult());
		t.setRemark(r.getRemark());
		deviceCtrlDao.update(t);
	}

	@Override
	public GateWayDeviceCtrl getGateWay(Integer id) {
		TGateWayDeviceCtrl t = deviceCtrlDao.get(TGateWayDeviceCtrl.class, id);
		GateWayDeviceCtrl r = new GateWayDeviceCtrl();
		r.setId(t.getId());
		r.setCtrlCode(t.getCtrlCode());
		r.setExecuteTime(t.getExecuteTime());
		r.setExeRtn(t.getExeRtn());
		r.setMn(t.getMn());
		r.setParam(t.getParam());
		r.setPsCode(t.getPsCode());
		r.setPsName(t.getPsName());
		r.setQn(t.getQn());
		r.setQnRtn(t.getQnRtn());
		r.setResult(t.getResult());
		r.setRemark(t.getRemark());
		return r;
	}

	@Override
	public List<GateWayDeviceCtrl> dataGridGateWay(GateWayDeviceCtrl demo, PageFilter ph,int type) {
		List<GateWayDeviceCtrl> ul = new ArrayList<GateWayDeviceCtrl>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TGateWayDeviceCtrl t ";
		List<TGateWayDeviceCtrl> l = deviceCtrlDao.find(hql + whereHql(demo, params,type) + orderHql(ph), params, ph.getPage(), ph.getRows());
		for (TGateWayDeviceCtrl t : l) {
			GateWayDeviceCtrl u = new GateWayDeviceCtrl();
			BeanUtils.copyProperties(t, u);
			ul.add(u);
		}
		return ul;
	}
	
	@Override
	public Long count(GateWayDeviceCtrl demo, PageFilter ph,int type) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TGateWayDeviceCtrl t ";
		return deviceCtrlDao.count("select count(*) " + hql + whereHql(demo, params,type), params);
	}
	
	private String whereHql(GateWayDeviceCtrl demo, Map<String, Object> params,int type) {
		String hql = "";
		if (demo != null) {
			hql += " where 1=1 ";
			if (demo.getPsCode() != null) {
				hql += " and t.psCode like :name";
				params.put("name", "%%" + demo.getPsCode() + "%%");
			}
			if (demo.getStartTime() != null) {
				hql += " and t.executionTime >= :createdatetimeStart";
				params.put("createdatetimeStart", demo.getStartTime());
			}
			if (demo.getEndTime() != null) {
				hql += " and t.executionTime <= :createdatetimeEnd";
				params.put("createdatetimeEnd", demo.getEndTime());
			}
			if(type==1){
				hql += " and (t.ctrlCode = '3051' or t.ctrlCode = '3052')";
			}
			if(type==2){
				hql += " and (t.ctrlCode = '3097' or t.ctrlCode = '3098')";
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
	/*#########################################################################################################*/

}
