package light.mvc.controller.base;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import light.mvc.controller.base.BaseController;
import light.mvc.pageModel.base.Grid;
import light.mvc.pageModel.base.Json;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.PollutantType;
import light.mvc.pageModel.base.PsPiPort;
import light.mvc.pageModel.base.PsPollutionSourceInfo;
import light.mvc.pageModel.base.Tree;
import light.mvc.service.base.PsPiPortServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/piport")
public class PsPiPortController extends BaseController {

	@Autowired
	private PsPiPortServiceI pService;
	
	@RequestMapping("/manager")
	public String manager() {
		return "/base/piport";
	}

	@RequestMapping("/porttree")
	@ResponseBody
	public List<Tree> portTree(PsPiPort demo, PageFilter ph) {
		return pService.tree();
	}
	
	@RequestMapping("/pstree")
	@ResponseBody
	public List<Tree> psTree(PsPollutionSourceInfo demo, PageFilter ph) {
		return pService.psTree();
	}
	
	@RequestMapping("/pollutanttree")
	@ResponseBody
	public List<Tree> pollutantTree(PollutantType demo, PageFilter ph) {
		return pService.pollutantTypeTree();
	}

	@RequestMapping("/addPage")
	public String addPage() {
		return "/base/piportAdd";
	}

	@RequestMapping("/add")
	@ResponseBody
	public Json add(PsPiPort demo) {
		Json j = new Json();
		try {
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
	public PsPiPort get(Integer id) {
		return pService.get(id);
	}

	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		PsPiPort r = pService.get(id);
		request.setAttribute("pageObj", r);
		return "/base/piportEdit";
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(PsPiPort demo) {
		Json j = new Json();
		try {
			System.out.println(demo.toString());
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
	public Grid dataGrid(PsPiPort record, PageFilter ph) {
		Grid grid = new Grid();
		grid.setRows(pService.dataGrid(record, ph));
		grid.setTotal(pService.count(record, ph));
		return grid;
	}
}
