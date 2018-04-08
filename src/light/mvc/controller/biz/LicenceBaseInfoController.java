package light.mvc.controller.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import light.mvc.controller.base.BaseController;
import light.mvc.pageModel.base.Grid;
import light.mvc.pageModel.base.Json;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.biz.LicenceBaseInfo;
import light.mvc.pageModel.biz.LicenceFactors;
import light.mvc.pageModel.biz.LicencePollutantInfo;
import light.mvc.service.biz.LicenceBaseInfoServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;


@Controller
@RequestMapping("/licenceinfo")
public class LicenceBaseInfoController extends BaseController{

	@Autowired
	private LicenceBaseInfoServiceI licenceService;
	
	@RequestMapping("/manager")
	public String manager() {
		return "/base/licenceinfo";
	}
	
	@RequestMapping("/tree")
	@ResponseBody
	public List<Tree> tree() {
		return licenceService.psTree();
	}
	
	@RequestMapping("/addPage")
	public String addPage() {
		return "/base/licenceinfoAdd";
	}
	
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		LicenceBaseInfo r = licenceService.get(id);
		
		request.setAttribute("pageObj", r);
		return "/base/licenceinfoEdit";
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Json add(LicenceBaseInfo record) {
		Json j = new Json();
		
		System.out.println(record);
		try {
			licenceService.add(record);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Json deleteType(Integer id) {
		Json j = new Json();
		try {
			System.out.println("id:-------"+id.toString());
			licenceService.delete(id);
			j.setMsg("删除成功！");
			j.setSuccess(true);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(LicenceBaseInfo record) {
		Json j = new Json();
		System.out.println(record);
		try {
			licenceService.edit(record);
			j.setSuccess(true);
			j.setMsg("编辑成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@RequestMapping("/dataGrid")
	@ResponseBody
	public Grid dataGrid(LicenceBaseInfo record, PageFilter ph) {
		Grid grid = new Grid();
		grid.setRows(licenceService.dataGrid(record, ph));
		grid.setTotal(licenceService.count(record, ph));
		return grid;
	}
	
	@RequestMapping("/factorPage")
	public String factorInfo(HttpServletRequest request, Integer id){
		Gson gson = new  Gson();
		//List<LicencePollutantInfo> l = licenceService.getFactorLimit(id);
		request.setAttribute("factors",id);
		
		return "/base/licencefactorEdit";
	}
	
	@RequestMapping("/editFactor1")
	@ResponseBody
	public List<LicencePollutantInfo> factorIn(Integer id){
		List<LicencePollutantInfo> l = licenceService.getFactorLimit(id);
		//System.out.println("editFactor1:"+l);
		return l;
	}
	@RequestMapping("/editFactor")
	@ResponseBody
	public Json editFactor(LicenceFactors records) {
		Json j = new Json();
		//System.out.println("records"+records);
		try {
			licenceService.editFactor(records.getList());
			j.setSuccess(true);
			j.setMsg("编辑成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
}
