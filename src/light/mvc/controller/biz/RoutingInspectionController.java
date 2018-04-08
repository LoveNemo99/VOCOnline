package light.mvc.controller.biz;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import light.mvc.controller.base.BaseController;
import light.mvc.pageModel.base.Grid;
import light.mvc.pageModel.base.Json;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.SessionInfo;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.biz.RoutingInspection;
import light.mvc.service.biz.RoutingInspectionServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/inspection")
public class RoutingInspectionController extends BaseController {

	@Autowired
	private RoutingInspectionServiceI pService;
	
	@RequestMapping("/manager")
	public String manager() {
		return "/base/inspection";
	}

	@RequestMapping("/pstree")
	@ResponseBody
	public List<Tree> pInfo(RoutingInspection demo, PageFilter ph) {
		return pService.psTree();
	}
	@RequestMapping("/statetree")
	@ResponseBody
	public List<Tree> stateInfo(RoutingInspection demo, PageFilter ph) {
		return pService.stateTree();
	}
	@RequestMapping("/upstree")
	@ResponseBody
	public List<Tree> upInfo(RoutingInspection demo, PageFilter ph) {
		return pService.unInspectPsTree();
	}

	@RequestMapping("/addPage")
	public String addPage() {
		return "/base/inspectionAdd";
	}

	@RequestMapping("/add")
	@ResponseBody
	public Json add(HttpServletRequest request,RoutingInspection demo) {
		Json j = new Json();
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute("sessionInfo");
		try {
			demo.setCreateTime(new Date());
			demo.setCreateMan(sessionInfo.getName());
			pService.add(demo);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer id) {
		Json j = new Json();
		try {
			pService.delete(id);
			j.setMsg("删除成功！");
			j.setSuccess(true);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/get")
	@ResponseBody
	public RoutingInspection get(Integer id) {
		return pService.get(id);
	}

	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		RoutingInspection r = pService.get(id);
		request.setAttribute("pageObj", r);
		return "/base/inspectionEdit";
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(HttpServletRequest request, RoutingInspection demo) {
		Json j = new Json();
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute("sessionInfo");
		try {
			demo.setModifyMan(sessionInfo.getName());
			pService.edit(demo);
			j.setSuccess(true);
			j.setMsg("编辑成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public Grid dataGrid(RoutingInspection record, PageFilter ph) {
		Grid grid = new Grid();
		grid.setRows(pService.dataGrid(record, ph));
		grid.setTotal(pService.count(record, ph));
		return grid;
	}
}
