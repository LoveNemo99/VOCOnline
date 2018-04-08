package light.mvc.controller.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import light.mvc.controller.base.BaseController;
import light.mvc.pageModel.base.Grid;
import light.mvc.pageModel.base.Json;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.SessionInfo;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.biz.MonthPlanFactors;
import light.mvc.pageModel.biz.MonthPlanMain;
import light.mvc.pageModel.biz.MonthPlanPollutant;
import light.mvc.pageModel.sys.User;
import light.mvc.service.biz.MonthPlanServiceI;
import light.mvc.service.sys.UserServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/monthplan")
public class MonthPlanController extends BaseController{

	@Autowired
	private MonthPlanServiceI mService;
	@Autowired
	private UserServiceI userService;
	
	@RequestMapping("/manager")
	public String manager() {
		return "/base/monthplan";
	}
	
	@RequestMapping("/pstree")
	@ResponseBody
	public List<Tree> psTree(HttpServletRequest request) {
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute("sessionInfo");
		User user = userService.getByLoginName(sessionInfo.getLoginname());
		return mService.psTree(user.getPsCode());
	}
	
	@RequestMapping("/addPage")
	public String addPage() {
		return "/base/monthplanAdd";
	}
	
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		MonthPlanMain r = mService.get(id);
		
		request.setAttribute("pageObj", r);
		return "/base/monthplanEdit";
	}
	
//	@RequestMapping("/grantPage")
//	public String grantPage(HttpServletRequest request, Integer id) {
//		MonthPlanMain r = mService.get(id);
//		request.setAttribute("pageObj", r);
//		return "/base/monthplanGrant";
//	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Json add(HttpServletRequest request,MonthPlanMain record) {
		Json j = new Json();
		
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute("sessionInfo");
		User user = userService.getByLoginName(sessionInfo.getLoginname());
		if(user.getRoleName().equals("企业用户")){
			try {
				mService.add(record);
				j.setSuccess(true);
				j.setMsg("添加成功！");
			} catch (Exception e) {
				j.setMsg(e.getMessage());
			}
		}
		else{
			j.setSuccess(false);
			j.setMsg("没有权限！");
		}
		return j;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(HttpServletRequest request,Integer id) {
		Json j = new Json();
		
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute("sessionInfo");
		User user = userService.getByLoginName(sessionInfo.getLoginname());
		if(user.getRoleName().equals("企业用户")){
			try {
				System.out.println("id:-------"+id.toString());
				mService.delete(id);
				j.setMsg("删除成功！");
				j.setSuccess(true);
			} catch (Exception e) {
				j.setMsg(e.getMessage());
			}
		}
		else{
			j.setMsg("没有权限！");
			j.setSuccess(false);
		}
		return j;
	}

	@RequestMapping("/submit")
	@ResponseBody
	public Json submit(HttpServletRequest request,Integer id) {
		Json j = new Json();
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute("sessionInfo");
		User user = userService.getByLoginName(sessionInfo.getLoginname());
		if(user.getRoleName().equals("企业用户")){
			try {
				String man = user.getName();
				Boolean b = mService.submit(id,man);
				if(b){
					j.setMsg("提交成功！");
				}else{
					j.setMsg("提交失败！");
				}
				j.setSuccess(b);
			} catch (Exception e) {
				j.setMsg(e.getMessage());
			}
		}
		else{
			j.setMsg("没有权限！");
			j.setSuccess(false);
		}
		return j;
	}
	
	@RequestMapping("/grant")
	@ResponseBody
	public Json grant(HttpServletRequest request,Integer id) {
		Json j = new Json();
		System.out.println("id:--------"+id);
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute("sessionInfo");
		User user = userService.getByLoginName(sessionInfo.getLoginname());
		if(user.getRoleName().equals("环保部门")){
			try {
				String man = user.getName();
				Boolean b = mService.grant(id,man,"审核通过");
				if(b){
					j.setMsg("审核成功！");
				}else{
					j.setMsg("审核失败！");
				}
				j.setSuccess(b);
			} catch (Exception e) {
				j.setMsg(e.getMessage());
			}
		}
		else{
			j.setMsg("没有权限！");
			j.setSuccess(false);
		}
		return j;
	}
	
	@RequestMapping("/notgrant")
	@ResponseBody
	public Json notGrant(HttpServletRequest request,Integer id) {
		Json j = new Json();
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute("sessionInfo");
		User user = userService.getByLoginName(sessionInfo.getLoginname());
		if(user.getRoleName().equals("环保部门")){
			try {
				String man = user.getName();
				Boolean b = mService.grant(id,man,"审核未通过");
				if(b){
					j.setMsg("审核成功！");
				}else{
					j.setMsg("审核失败！");
				}
				j.setSuccess(b);
			} catch (Exception e) {
				j.setMsg(e.getMessage());
			}
		}
		else{
			j.setMsg("没有权限！");
			j.setSuccess(false);
		}
		return j;
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(HttpServletRequest request,MonthPlanMain record) {
		Json j = new Json();
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute("sessionInfo");
		User user = userService.getByLoginName(sessionInfo.getLoginname());
		if(user.getRoleName().equals("企业用户")){
			try {
				mService.edit(record);
				j.setSuccess(true);
				j.setMsg("编辑成功！");
			} catch (Exception e) {
				j.setMsg(e.getMessage());
			}
		}
		else{
			j.setSuccess(false);
			j.setMsg("没有权限！");
		}
		return j;
	}
	
	@RequestMapping("/dataGrid")
	@ResponseBody
	public Grid dataGrid(HttpServletRequest request, MonthPlanMain record, PageFilter ph) {
		Grid grid = new Grid();
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute("sessionInfo");
		User user = userService.getByLoginName(sessionInfo.getLoginname());
		if(!user.getRoleName().equals("企业用户") && !user.getRoleName().equals("环保部门") && !user.getRoleName().equals("超级管理员")){
			return grid;
		}
		if(user.getRoleName().equals("企业用户")){
			record.setPsCode(user.getPsCode());
		}
		grid.setRows(mService.dataGrid(record, ph));
		grid.setTotal(mService.count(record, ph));
		return grid;
	}
	
	@RequestMapping("/factorPage")
	public String factorInfo(HttpServletRequest request, Integer id){
		//Gson gson = new  Gson();
		request.setAttribute("factors",id);
		return "/base/monthplanfactorEdit";
	}
	
	@RequestMapping("/grantPage")
	public String grantInfo(HttpServletRequest request, Integer id){
		request.setAttribute("factors",id);
		return "/base/monthplanGrant";
	}
	
	@RequestMapping("/editFactor1")
	@ResponseBody
	public List<MonthPlanPollutant> factorIn(Integer id){
		List<MonthPlanPollutant> l = mService.getFactorLimit(id);
		//System.out.println("editFactor1:"+l);
		return l;
	}
	@RequestMapping("/editFactor")
	@ResponseBody
	public Json editFactor(HttpServletRequest request,MonthPlanFactors records) {
		Json j = new Json();
		try {
			SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute("sessionInfo");
			User user = userService.getByLoginName(sessionInfo.getLoginname());
			if(user.getRoleName().equals("企业用户")){
				mService.editFactor(records.getList());
				j.setSuccess(true);
				j.setMsg("编辑成功！");
			}
			else{
				j.setSuccess(false);
				j.setMsg("没有权限！");
			}
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@RequestMapping("/cause")
	@ResponseBody
	public Json cause(HttpServletRequest request,MonthPlanFactors records,String cause) {
		Json j = new Json();
		try {
			SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute("sessionInfo");
			User user = userService.getByLoginName(sessionInfo.getLoginname());
			if(user.getRoleName().equals("环保部门")){
				mService.cause(records.getList().get(0).getMainId(),cause);
				j.setSuccess(true);
				j.setMsg("编辑成功！");
			}
			else{
				j.setSuccess(false);
				j.setMsg("没有权限！");
			}
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
}
