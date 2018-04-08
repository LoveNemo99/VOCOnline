package light.mvc.controller.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import light.mvc.controller.base.BaseController;
import light.mvc.pageModel.base.Grid;
import light.mvc.pageModel.base.Json;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.PsPollutionSourceInfo;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.sys.AlarmContact;
import light.mvc.service.biz.AlarmContactServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/alarmcontact")
public class AlarmContactController extends BaseController {

	@Autowired
	private AlarmContactServiceI pService;
	
	@RequestMapping("/manager")
	public String manager() {
		return "/base/alarmcontact";
	}

	@RequestMapping("/tree")
	@ResponseBody
	public List<Tree> pInfo(AlarmContact demo, PageFilter ph) {
		return pService.tree();
	}
	
	@RequestMapping("/pstree")
	@ResponseBody
	public List<Tree> psTree(PsPollutionSourceInfo demo, PageFilter ph) {
		return pService.psTree();
	}

	@RequestMapping("/addPage")
	public String addPage() {
		return "/base/alarmcontactAdd";
	}

	@RequestMapping("/add")
	@ResponseBody
	public Json add(AlarmContact demo) {
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
	public AlarmContact get(Integer id) {
		return pService.get(id);
	}

	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		AlarmContact r = pService.get(id);
		request.setAttribute("pageObj", r);
		return "/base/alarmcontactEdit";
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(AlarmContact demo) {
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
	public Grid dataGrid(AlarmContact record, PageFilter ph) {
		Grid grid = new Grid();
		grid.setRows(pService.dataGrid(record, ph));
		grid.setTotal(pService.count(record, ph));
		return grid;
	}
}
