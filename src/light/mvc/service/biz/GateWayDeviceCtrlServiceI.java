package light.mvc.service.biz;

import java.util.List;

import light.mvc.model.base.TPsPiPort;
import light.mvc.model.base.TPsPollutionSourceInfo;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.biz.GateWayDeviceCtrl;

public interface GateWayDeviceCtrlServiceI {
	public void add(GateWayDeviceCtrl r);
	public void edit(GateWayDeviceCtrl r);
	public void deleteGateWay(Integer id);
	public GateWayDeviceCtrl getGateWay(Integer id);
	public List<GateWayDeviceCtrl> dataGridGateWay(GateWayDeviceCtrl demo,PageFilter ph, int type);
	public Long count(GateWayDeviceCtrl demo, PageFilter ph, int type);
	public TPsPiPort getPortInfo(String psId);
	public TPsPollutionSourceInfo getPsInfo(String psId);
	public Boolean validatePwd(String userPwd);
}
