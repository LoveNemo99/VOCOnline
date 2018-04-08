package light.mvc.controller.base;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import light.mvc.controller.base.BaseController;
import light.mvc.pageModel.base.Grid;
import light.mvc.pageModel.base.Json;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.PsPollutionSourceInfo;
import light.mvc.pageModel.base.Tree;
import light.mvc.service.base.PollutionSourceInfoServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/info")
public class PollutionSourceInfoController extends BaseController {

	@Autowired
	private PollutionSourceInfoServiceI psService;
	
	@RequestMapping("/manager")
	public String manager() {
		return "/base/psbaseinfo";
	}

	@RequestMapping("/pstree")
	@ResponseBody
	public List<Tree> psInfo(PsPollutionSourceInfo demo, PageFilter ph) {
		return psService.tree();
	}
	@RequestMapping("/affiliationTree")//隶属关系
	@ResponseBody
	public List<Tree> affiliationInfo(PsPollutionSourceInfo demo, PageFilter ph) {
		return psService.affiliationTree();
	}
	@RequestMapping("/basinTree")//流域
	@ResponseBody
	public List<Tree> basinInfo(PsPollutionSourceInfo demo, PageFilter ph) {
		return psService.basinTree();
	}
	@RequestMapping("/divisionTree")//行政区划
	@ResponseBody
	public List<Tree> divisionInfo(PsPollutionSourceInfo demo, PageFilter ph) {
		return psService.divisionTree();
	}
	@RequestMapping("/enCategoryTree")//企业类型
	@ResponseBody
	public List<Tree> enCategoryInfo(PsPollutionSourceInfo demo, PageFilter ph) {
		return psService.enCategoryTree();
	}
	@RequestMapping("/enRegistrationTree")//注册类型
	@ResponseBody
	public List<Tree> enRegistrationInfo(PsPollutionSourceInfo demo, PageFilter ph) {
		return psService.enRegistrationTree();
	}
	@RequestMapping("/inCategoryTree")//行业类别
	@ResponseBody
	public List<Tree> inCategoryInfo(PsPollutionSourceInfo demo, PageFilter ph) {
		return psService.inCategoryTree();
	}
	@RequestMapping("/sizeTree")//企业规模
	@ResponseBody
	public List<Tree> sizeInfo(PsPollutionSourceInfo demo, PageFilter ph) {
		return psService.sizeTree();
	}

	@RequestMapping("/addPage")
	public String addPage() {
		return "/base/psbaseinfoAdd";
	}

	@RequestMapping("/add")
	@ResponseBody
	public Json add(PsPollutionSourceInfo demo) {
		Json j = new Json();
		System.out.println(demo.toString());
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
	public PsPollutionSourceInfo get(Integer id) {
		return psService.get(id);
	}

	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		PsPollutionSourceInfo r = psService.get(id);
		request.setAttribute("pageObj", r);
		return "/base/psbaseinfoEdit";
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(PsPollutionSourceInfo demo) {
		Json j = new Json();
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
	public Grid dataGrid(PsPollutionSourceInfo record, PageFilter ph) {
		Grid grid = new Grid();
		grid.setRows(psService.dataGrid(record, ph));
		grid.setTotal(psService.count(record, ph));
		return grid;
	}
}
