package light.mvc.controller.biz;

import java.util.Date;

import light.mvc.controller.base.BaseController;
import light.mvc.model.base.TPsPiPort;
import light.mvc.model.base.TPsPollutionSourceInfo;
import light.mvc.pageModel.base.Grid;
import light.mvc.pageModel.base.Json;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.biz.GateWayDeviceCtrl;
import light.mvc.service.biz.GateWayDeviceCtrlServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/maintenance")
public class GateWayDeviceCtrlController extends BaseController{

	@Autowired
	private GateWayDeviceCtrlServiceI gatewayDeviceCtrlService;
	/*#########################################################################################################*/
	@RequestMapping("/managerTotalCtrl")
	public String managerTotalCtrl() {
		return "/base/totalctrl";
	}
	
	@RequestMapping("/dataGridTotalCtrl")
	@ResponseBody
	public Grid dataGridTotalCtrl(GateWayDeviceCtrl record, PageFilter ph) {
		Grid grid = new Grid();
		grid.setRows(gatewayDeviceCtrlService.dataGridGateWay(record, ph, 1));
		grid.setTotal(gatewayDeviceCtrlService.count(record, ph, 1));
		return grid;
	}
	
	@RequestMapping("/totalCtrlPage")
	public String addTotalCtrlPage() {
		return "/base/totalCtrlAdd";
	}
	
	@RequestMapping("/totalTiPage")
	public String addTotalTiPage() {
		return "/base/totalTiAdd";
	}
	
	@RequestMapping("/addtotalctrl")
	@ResponseBody
	public Json addTotalCtrl(GateWayDeviceCtrl record) {
		Json j = new Json();
		if(record ==null){
			j.setSuccess(false);
			j.setMsg("指令发送失败！");
			return j;
		}
		if(record.getPsCode()==null||record.getPsCode().length()==0|| record.getParam()==null){
			j.setMsg("指令发送失败！");
			return j;
		}
		if(record.getPwd()==null){
			j.setSuccess(false);
			j.setMsg("请输入密码！");
			return j;
		}
		if(!gatewayDeviceCtrlService.validatePwd(record.getPwd())){
			j.setSuccess(false);
			j.setMsg("密码错误！");
			return j;
		}
		
		TPsPiPort port = gatewayDeviceCtrlService.getPortInfo(record.getPsCode());
		TPsPollutionSourceInfo info = gatewayDeviceCtrlService.getPsInfo(record.getPsCode());
		if(port==null){
			j.setMsg("指令发送失败！");
			return j;
		}
		try {
			record.setCtrlCode("3051");
			record.setResult("指令已下发");
			record.setExecuteTime(new Date());
			record.setPsName(info.getPsName());
			record.setMn(port.getMn());
			record.setPsCode(info.getCode());
			gatewayDeviceCtrlService.add(record);
			j.setSuccess(true);
			j.setMsg("指令发送成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@RequestMapping("/addtotalti")
	@ResponseBody
	public Json addTotalTi(GateWayDeviceCtrl record) {
		Json j = new Json();
		if(record.getPsCode()==null){
			j.setMsg("指令发送失败！");
			return j;
		}
		TPsPiPort port = gatewayDeviceCtrlService.getPortInfo(record.getPsCode());
		TPsPollutionSourceInfo info = gatewayDeviceCtrlService.getPsInfo(record.getPsCode());
		if(port==null){
			j.setMsg("指令发送失败！");
			return j;
		}
		try {
			record.setCtrlCode("valve_state");
			record.setResult("指令已下发");
			record.setExecuteTime(new Date());
			record.setPsCode(info.getCode());
			record.setMn(port.getMn());
			record.setPsName(info.getPsName());
			gatewayDeviceCtrlService.add(record);
			j.setSuccess(true);
			j.setMsg("指令发送成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	/*#########################################################################################################*/
	@RequestMapping("/managerAccessCtrl")
	public String managerAccessCtrl() {
		return "/base/accessctrl";
	}
	
	@RequestMapping("/dataGridAccessCtrl")
	@ResponseBody
	public Grid dataGridAccessCtrl(GateWayDeviceCtrl record, PageFilter ph) {
		Grid grid = new Grid();
		grid.setRows(gatewayDeviceCtrlService.dataGridGateWay(record, ph, 2));
		grid.setTotal(gatewayDeviceCtrlService.count(record, ph, 2));
		return grid;
	}
	
	@RequestMapping("/accessCtrlPage")
	public String addAccessCtrlPage() {
		return "/base/accessCtrlAdd";
	}
	
	@RequestMapping("/accessTiPage")
	public String addAccessTiPage() {
		return "/base/accessTiAdd";
	}
	
	@RequestMapping("/addaccessctrl")
	@ResponseBody
	public Json addAccessCtrl(GateWayDeviceCtrl record) {
		Json j = new Json();
		if(record ==null){
			j.setSuccess(false);
			j.setMsg("指令发送失败！");
			return j;
		}
		if(record.getPsCode()==null||record.getPsCode().length()==0){
			j.setMsg("指令发送失败！");
			return j;
		}
		if(record.getPwd()==null){
			j.setSuccess(false);
			j.setMsg("请输入密码！");
			return j;
		}
		if(!gatewayDeviceCtrlService.validatePwd(record.getPwd())){
			j.setSuccess(false);
			j.setMsg("密码错误！");
			return j;
		}
		TPsPiPort port = gatewayDeviceCtrlService.getPortInfo(record.getPsCode());
		TPsPollutionSourceInfo info = gatewayDeviceCtrlService.getPsInfo(record.getPsCode());
		if(port==null){
			j.setMsg("指令发送失败！");
			return j;
		}
		try {
			record.setCtrlCode("3097");
			record.setResult("指令已下发");
			record.setExecuteTime(new Date());
			record.setParam((double) 100);
			record.setPsName(info.getPsName());
			record.setMn(port.getMn());
			record.setPsCode(info.getCode());
			gatewayDeviceCtrlService.add(record);
			j.setSuccess(true);
			j.setMsg("指令发送成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@RequestMapping("/addaccessti")
	@ResponseBody
	public Json addAccessTi(GateWayDeviceCtrl record) {
		Json j = new Json();
		if(record.getPsCode()==null){
			j.setMsg("指令发送失败！");
			return j;
		}
		TPsPiPort port = gatewayDeviceCtrlService.getPortInfo(record.getPsCode());
		TPsPollutionSourceInfo info = gatewayDeviceCtrlService.getPsInfo(record.getPsCode());
		if(port==null){
			j.setMsg("指令发送失败！");
			return j;
		}
		try {
			record.setCtrlCode("access_state");
			record.setResult("指令已下发");
			record.setExecuteTime(new Date());
			record.setPsName(info.getPsName());
			record.setMn(port.getMn());
			record.setPsCode(port.getPsCode());
			gatewayDeviceCtrlService.add(record);
			j.setSuccess(true);
			j.setMsg("指令发送成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	/*#########################################################################################################*/
}
