package light.mvc.service.biz;

import java.util.List;

import light.mvc.model.base.TPsPiPort;
import light.mvc.model.base.TPsPollutionSourceInfo;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.biz.GateWaySampleTake;

public interface GateWaySampleTakeServiceI {
	public void add(GateWaySampleTake r);
	public void edit(GateWaySampleTake r);
	public void deleteGateWay(Integer id);
	public GateWaySampleTake getGateWay(Integer id);
	public List<GateWaySampleTake> dataGridGateWay(GateWaySampleTake demo,PageFilter ph, int type);
	public Long count(GateWaySampleTake demo, PageFilter ph, int type);
	public TPsPiPort getPortInfo(String psId);
	public TPsPollutionSourceInfo getPsInfo(String psId);
	public Boolean validatePwd(String userPwd);
	public List<Tree> psTree();
	public List<Tree> piTree(Integer id);
}
