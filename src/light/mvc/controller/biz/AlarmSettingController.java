package light.mvc.controller.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import light.mvc.controller.base.BaseController;
import light.mvc.pageModel.base.Grid;
import light.mvc.pageModel.base.Json;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.biz.AlarmSetting;
import light.mvc.service.biz.AlarmSettingServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/alarmsetting")
public class AlarmSettingController extends BaseController{

	@Autowired
	private AlarmSettingServiceI settingService;
	
	@RequestMapping("/manager")
	public String manager() {
		return "/base/alarmsetting";
	}
	
	@RequestMapping("/factortree")
	@ResponseBody
	public List<Tree> factorTree() {
		return settingService.factorTree();
	}
	
	@RequestMapping("/addPage")
	public String addPage() {
		return "/base/alarmsettingAdd";
	}
	
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		AlarmSetting r = settingService.get(id);
		
		request.setAttribute("pageObj", r);
		return "/base/alarmsettingEdit";
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Json add(AlarmSetting record) {
		Json j = new Json();
		
		System.out.println(record);
		try {
			settingService.add(record);
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
			settingService.delete(id);
			j.setMsg("删除成功！");
			j.setSuccess(true);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(AlarmSetting record) {
		Json j = new Json();
		try {
			settingService.edit(record);
			j.setSuccess(true);
			j.setMsg("编辑成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@RequestMapping("/dataGrid")
	@ResponseBody
	public Grid dataGrid(AlarmSetting record, PageFilter ph) {
		Grid grid = new Grid();
		grid.setRows(settingService.dataGrid(record, ph));
		grid.setTotal(settingService.count(record, ph));
		return grid;
	}
	
}
