package light.mvc.controller.base;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import light.mvc.controller.base.BaseController;
import light.mvc.pageModel.base.Grid;
import light.mvc.pageModel.base.Json;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.PsContactInfo;
import light.mvc.pageModel.base.Tree;
import light.mvc.service.base.PsContactInfoServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/pscontact")
public class PsContactInfoController extends BaseController {

	@Autowired
	private PsContactInfoServiceI psService;
	
	@RequestMapping("/manager")
	public String manager() {
		return "/base/pscontactinfo";
	}

	@RequestMapping("/pstree")
	@ResponseBody
	public List<Tree> psInfo(PsContactInfo demo, PageFilter ph) {
		return psService.psTree();
	}

	@RequestMapping("/addPage")
	public String addPage() {
		return "/base/pscontactinfoAdd";
	}

	@RequestMapping("/add")
	@ResponseBody
	public Json add(PsContactInfo demo) {
		Json j = new Json();
		System.out.println(demo);
		
		try {
			psService.add(demo);
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
			psService.delete(id);
			j.setMsg("删除成功！");
			j.setSuccess(true);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/get")
	@ResponseBody
	public PsContactInfo get(Integer id) {
		return psService.get(id);
	}

	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		PsContactInfo r = psService.get(id);
		request.setAttribute("contact", r);
		return "/base/pscontactinfoEdit";
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(PsContactInfo demo) {
		Json j = new Json();
		System.out.println(demo);
		try {
			psService.edit(demo);
			j.setSuccess(true);
			j.setMsg("编辑成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public Grid dataGrid(PsContactInfo record, PageFilter ph) {
		Grid grid = new Grid();
		grid.setRows(psService.dataGrid(record, ph));
		grid.setTotal(psService.count(record, ph));
		return grid;
	}
}
