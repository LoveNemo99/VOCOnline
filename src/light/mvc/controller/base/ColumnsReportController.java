package light.mvc.controller.base;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import light.mvc.controller.base.BaseController;
import light.mvc.pageModel.base.ColumnsReport;
import light.mvc.pageModel.base.Grid;
import light.mvc.pageModel.base.Json;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.service.base.ColumnsReportServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/columnsReport")
public class ColumnsReportController extends BaseController {

	@Autowired
	private ColumnsReportServiceI pService;
	
	@RequestMapping("/manager")
	public String manager() {
		return "/base/columnsreport";
	}

	@RequestMapping("/factortree")
	@ResponseBody
	public List<Tree> factorTree(ColumnsReport demo, PageFilter ph) {
		return pService.factorTree();
	}
	
	@RequestMapping("/unittree")
	@ResponseBody
	public List<Tree> unitTree(ColumnsReport demo, PageFilter ph) {
		return pService.unitTree();
	}

	@RequestMapping("/addPage")
	public String addPage() {
		return "/base/columnsreportAdd";
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
	public ColumnsReport get(Integer id) {
		return pService.get(id);
	}

	@RequestMapping("/add")
	@ResponseBody
	public Json add(ColumnsReport demo) {
		Json j = new Json();
		try {
			pService.add(demo,"废气报表");
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		ColumnsReport r = pService.get(id);
		request.setAttribute("pageObj", r);
		return "/base/columnsreportEdit";
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(ColumnsReport demo) {
		Json j = new Json();
		try {
			pService.edit(demo,"废气报表");
			j.setSuccess(true);
			j.setMsg("编辑成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public Grid dataGrid(ColumnsReport record, PageFilter ph) {
		Grid grid = new Grid();
		grid.setRows(pService.dataGrid(record, ph,"污水报表"));
		grid.setTotal(pService.count(record, ph));
		return grid;
	}
	
	@RequestMapping("/titles")
	@ResponseBody
	public List<ColumnsReport> columns(ColumnsReport record){
		PageFilter ph = new PageFilter();
		ph.setSort("columnSort");
		ph.setOrder("asc");
		List<ColumnsReport> data = pService.dataGrid(record, ph,"废气报表");
		return data;
	}
	
	/*===============================================================================*/
	@RequestMapping("/managergas")
	public String managerGas() {
		return "/base/columnsreportgas";
	}
	@RequestMapping("/addgasPage")
	public String addgasPage() {
		return "/base/columnsreportgasAdd";
	}
	@RequestMapping("/addgas")
	@ResponseBody
	public Json addGas(ColumnsReport demo) {
		Json j = new Json();
		try {
			pService.add(demo,"废气报表");
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@RequestMapping("/editgasPage")
	public String editGasPage(HttpServletRequest request, Integer id) {
		ColumnsReport r = pService.get(id);
		request.setAttribute("pageObj", r);
		return "/base/columnsreportgasEdit";
	}

	@RequestMapping("/editgas")
	@ResponseBody
	public Json editGas(ColumnsReport demo) {
		Json j = new Json();
		try {
			pService.edit(demo,"废气报表");
			j.setSuccess(true);
			j.setMsg("编辑成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/dataGridGas")
	@ResponseBody
	public Grid dataGridGas(ColumnsReport record, PageFilter ph) {
		Grid grid = new Grid();
		grid.setRows(pService.dataGrid(record, ph,"废气报表"));
		grid.setTotal(pService.count(record, ph));
		return grid;
	}
	
	@RequestMapping("/titlesgas")
	@ResponseBody
	public List<ColumnsReport> columnsGas(ColumnsReport record){
		PageFilter ph = new PageFilter();
		ph.setSort("columnSort");
		ph.setOrder("asc");
		List<ColumnsReport> data = pService.dataGrid(record, ph,"废气报表");
		return data;
	}
}
