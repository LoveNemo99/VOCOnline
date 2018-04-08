package light.mvc.service.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.base.TPsPiPort;
import light.mvc.model.base.TPsPollutionSourceInfo;
import light.mvc.model.biz.TCtrlPwd;
import light.mvc.model.biz.TGateWaySampleTake;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.biz.GateWaySampleTake;
import light.mvc.service.biz.GateWaySampleTakeServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GateWaySampleTakeServiceImpl implements GateWaySampleTakeServiceI {
	@Autowired
	private BaseDaoI<TGateWaySampleTake> sampleTakeDao;
	@Autowired
	private BaseDaoI<TPsPiPort> portDao;
	@Autowired
	private BaseDaoI<TPsPollutionSourceInfo> sourceDao;
	@Autowired
	private BaseDaoI<TCtrlPwd> pwdDao;
	/*#########################################################################################################*/
	@Override
	public List<Tree> psTree() {
		List<TPsPollutionSourceInfo> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		l = sourceDao.find("select distinct t from TPsPollutionSourceInfo t");
		if ((l != null) && (l.size() > 0)) {
			for (TPsPollutionSourceInfo r : l) {
				Tree tree = new Tree();
				tree.setId(r.getId().toString());
				tree.setText(r.getPsName());
				tree.setValue(r.getPsName());
				lt.add(tree);
			}
		}
		return lt;
	}
	@Override
	public List<Tree> piTree(Integer id) {
		List<TPsPiPort> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		l = portDao.find("select distinct t from TPsPiPort t where t.psBaseId = "+id);
		if ((l != null) && (l.size() > 0)) {
			for (TPsPiPort r : l) {
				Tree tree = new Tree();
				tree.setId(r.getId().toString());
				tree.setText(r.getPortName());
				tree.setValue(r.getMn());
				lt.add(tree);
			}
		}
		return lt;
	}
	@Override
	public TPsPiPort getPortInfo(String psId){
		String hql = "from TPsPiPort t where t.psBaseId = "+psId;
		List<TPsPiPort> l = portDao.find(hql);
		if(l.size()>=0){
			return l.get(0);
		}
		return null;
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
	public TPsPollutionSourceInfo getPsInfo(String psId){
		TPsPollutionSourceInfo info = sourceDao.get(TPsPollutionSourceInfo.class, Integer.parseInt(psId));
		return info;
	}
	
	@Override
	public void add(GateWaySampleTake r) {
		TGateWaySampleTake t = new TGateWaySampleTake();
		BeanUtils.copyProperties(r, t);
//		t.setCtrlCode(r.getCtrlCode());
//		t.setExecutionTime(r.getExecutionTime());
//		t.setExeRtn(r.getExeRtn());
//		t.setMN(r.getMN());
//		t.setParam(r.getParam());
//		t.setPollutantSourceAlias(r.getPollutantSourceAlias());
//		t.setPollutantSourceId(r.getPollutantSourceId());
//		t.setPollutantSourceName(r.getPollutantSourceName());
//		t.setQN(r.getQN());
//		t.setQNRtn(r.getQNRtn());
//		t.setResult(r.getResult());
//		t.setRemark(r.getRemark());
//		t.setBottleId(r.getBottleId());
//		t.setPollutantCode(r.getPollutantCode());
		sampleTakeDao.save(t);
	}

	@Override
	public void deleteGateWay(Integer id) {
		TGateWaySampleTake t = sampleTakeDao.get(TGateWaySampleTake.class, id);
		sampleTakeDao.delete(t);
	}

	@Override
	public void edit(GateWaySampleTake r) {
		TGateWaySampleTake t = sampleTakeDao.get(TGateWaySampleTake.class, r.getId());
		BeanUtils.copyProperties(r, t);
//		t.setCtrlCode(r.getCtrlCode());
//		t.setExecutionTime(r.getExecutionTime());
//		t.setExeRtn(r.getExeRtn());
//		t.setMN(r.getMN());
//		t.setParam(r.getParam());
//		t.setPollutantSourceAlias(r.getPollutantSourceAlias());
//		t.setPollutantSourceId(r.getPollutantSourceId());
//		t.setPollutantSourceName(r.getPollutantSourceName());
//		t.setQN(r.getQN());
//		t.setQNRtn(r.getQNRtn());
//		t.setResult(r.getResult());
//		t.setRemark(r.getRemark());
		sampleTakeDao.update(t);
	}

	@Override
	public GateWaySampleTake getGateWay(Integer id) {
		TGateWaySampleTake t = sampleTakeDao.get(TGateWaySampleTake.class, id);
		GateWaySampleTake r = new GateWaySampleTake();
		BeanUtils.copyProperties(t, r);
//		r.setId(t.getId());
//		r.setCtrlCode(t.getCtrlCode());
//		r.setExecutionTime(t.getExecutionTime());
//		r.setExeRtn(t.getExeRtn());
//		r.setMN(t.getMN());
//		r.setParam(t.getParam());
//		r.setPollutantSourceAlias(t.getPollutantSourceAlias());
//		r.setPollutantSourceId(t.getPollutantSourceId());
//		r.setPollutantSourceName(t.getPollutantSourceName());
//		r.setQN(t.getQN());
//		r.setQNRtn(t.getQNRtn());
//		r.setResult(t.getResult());
//		r.setRemark(t.getRemark());
		return r;
	}

	@Override
	public List<GateWaySampleTake> dataGridGateWay(GateWaySampleTake demo, PageFilter ph,int type) {
		List<GateWaySampleTake> ul = new ArrayList<GateWaySampleTake>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TGateWaySampleTake t ";
		List<TGateWaySampleTake> l = sampleTakeDao.find(hql + whereHql(demo, params,type) + orderHql(ph), params, ph.getPage(), ph.getRows());
		for (TGateWaySampleTake t : l) {
			GateWaySampleTake u = new GateWaySampleTake();
			BeanUtils.copyProperties(t, u);
			ul.add(u);
		}
		return ul;
	}
	
	@Override
	public Long count(GateWaySampleTake demo, PageFilter ph,int type) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TGateWaySampleTake t ";
		return sampleTakeDao.count("select count(*) " + hql + whereHql(demo, params,type), params);
	}
	
	private String whereHql(GateWaySampleTake demo, Map<String, Object> params,int type) {
		String hql = "";
		if (demo != null) {
			hql += " where 1=1 ";
			if (demo.getPollutantSourceName() != null) {
				hql += " and t.pollutantSourceName like :name";
				params.put("name", "%%" + demo.getPollutantSourceName() + "%%");
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
				hql += " and (t.ctrlCode = '3012')";
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
