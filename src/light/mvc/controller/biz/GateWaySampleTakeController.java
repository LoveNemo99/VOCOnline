package light.mvc.controller.biz;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import light.mvc.controller.base.BaseController;
import light.mvc.model.base.TPsPiPort;
import light.mvc.model.base.TPsPollutionSourceInfo;
import light.mvc.pageModel.base.Grid;
import light.mvc.pageModel.base.Json;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.biz.GateWaySampleTake;
import light.mvc.service.biz.GateWaySampleTakeServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sample")
public class GateWaySampleTakeController extends BaseController{

	@Autowired
	private GateWaySampleTakeServiceI gatewaySampleTakeService;
	
	/*##########################################################################################################*/
	@RequestMapping("/manager")
	public String managerTotalCtrl() {
		return "/base/sampletake";
	}
	@RequestMapping("/pstree")
	@ResponseBody
	public List<Tree> psTree() {
		return gatewaySampleTakeService.psTree();
	}
	
	@RequestMapping("/pitree")
	@ResponseBody
	public List<Tree> piTree(Integer id) {
		return gatewaySampleTakeService.piTree(id);
	}
	
	@RequestMapping("/choosetree")
	@ResponseBody
	public List<Tree> chooseTree() {
		List<Tree> lt = new ArrayList<Tree>();
		for(int i=1;i<=24;i++){
			Tree tree = new Tree();
			tree.setId(i+"");
			tree.setText(i+"");
			tree.setValue(i+"");
			lt.add(tree);
		}
		return lt;
	}
	
	@RequestMapping("/dataGrid")
	@ResponseBody
	public Grid dataGridTotalCtrl(GateWaySampleTake record, PageFilter ph) {
		Grid grid = new Grid();
		grid.setRows(gatewaySampleTakeService.dataGridGateWay(record, ph, 1));
		grid.setTotal(gatewaySampleTakeService.count(record, ph, 1));
		return grid;
	}
	
	@RequestMapping("/addPage")
	public String addTotalCtrlPage() {
		return "/base/sampletakeAdd";
	}
	
	@RequestMapping("/totalTiPage")
	public String addTotalTiPage() {
		return "/base/totalTiAdd";
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Json add(GateWaySampleTake record) {
		Json j = new Json();
		if(record ==null){
			j.setSuccess(false);
			j.setMsg("指令发送失败！");
			return j;
		}
		if(record.getPollutantSourceName()==null||record.getPollutantSourceName().length()==0){
			j.setMsg("指令发送失败！");
			return j;
		}
		if(record.getPwd()==null){
			j.setSuccess(false);
			j.setMsg("请输入密码！");
			return j;
		}
		if(!gatewaySampleTakeService.validatePwd(record.getPwd())){
			j.setSuccess(false);
			j.setMsg("密码错误！");
			return j;
		}
		
		//TPsPiPort port = gatewaySampleTakeService.getPortInfo(record.getPollutantSourceName());
		TPsPollutionSourceInfo info = gatewaySampleTakeService.getPsInfo(record.getPollutantSourceName());
//		if(port==null){
//			j.setMsg("指令发送失败！");
//			return j;
//		}
		try {
			record.setCtrlCode("3012");
			record.setResult("指令已下发");
			record.setExecutionTime(new Date());
			record.setPollutantSourceName(info.getPsName());
			//record.setMN(port.getMn());
			record.setPollutantSourceId(info.getId());
			record.setPollutantSourceAlias(info.getPsName());
			gatewaySampleTakeService.add(record);
			j.setSuccess(true);
			j.setMsg("指令发送成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	/*#########################################################################################################*/
}
