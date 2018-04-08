package light.mvc.controller.base;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import light.mvc.controller.base.BaseController;
import light.mvc.pageModel.base.Grid;
import light.mvc.pageModel.base.Json;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.ScMeasureRtdUnit;
import light.mvc.pageModel.base.Tree;
import light.mvc.service.base.MeasureRtdUnitServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/measureRtdUnit")
public class ScMeasureRtdUnitController extends BaseController {

	@Autowired
	private MeasureRtdUnitServiceI pService;
	
	@RequestMapping("/manager")
	public String manager() {
		return "/base/unitrtd";
	}

	@RequestMapping("/tree")
	@ResponseBody
	public List<Tree> tree(ScMeasureRtdUnit demo, PageFilter ph) {
		return pService.tree();
	}

	@RequestMapping("/addPage")
	public String addPage() {
		return "/base/unitrtdAdd";
	}

	@RequestMapping("/add")
	@ResponseBody
	public Json add(ScMeasureRtdUnit demo) {
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
	public ScMeasureRtdUnit get(Integer id) {
		return pService.get(id);
	}

	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		ScMeasureRtdUnit r = pService.get(id);
		request.setAttribute("pageObj", r);
		return "/base/unitrtdEdit";
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(ScMeasureRtdUnit demo) {
		Json j = new Json();
		try {
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
	public Grid dataGrid(ScMeasureRtdUnit record, PageFilter ph) {
		Grid grid = new Grid();
		grid.setRows(pService.dataGrid(record, ph));
		grid.setTotal(pService.count(record, ph));
		return grid;
	}
}
