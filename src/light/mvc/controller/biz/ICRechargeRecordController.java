package light.mvc.controller.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import light.mvc.controller.base.BaseController;
import light.mvc.pageModel.base.Grid;
import light.mvc.pageModel.base.Json;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.biz.AlarmMode;
import light.mvc.pageModel.biz.ICAccountInfo;
import light.mvc.pageModel.biz.ICCardInfo;
import light.mvc.pageModel.biz.ICRechargeRecord;
import light.mvc.service.biz.AlarmModeServiceI;
import light.mvc.service.biz.ICAccountInfoServiceI;
import light.mvc.service.biz.ICCardInfoServiceI;
import light.mvc.service.biz.ICRechargeRecordServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/icrecharge")
public class ICRechargeRecordController extends BaseController {

	@Autowired
	private ICRechargeRecordServiceI pService;
	
	@RequestMapping("/manager")
	public String manager() {
		return "/base/icrecharge";
	}

//	@RequestMapping("/tree")
//	@ResponseBody
//	public List<Tree> pInfo(AlarmMode demo, PageFilter ph) {
//		return pService.tree();
//	}
//
//	@RequestMapping("/addPage")
//	public String addPage() {
//		return "/base/alarmmodeAdd";
//	}
//
//	@RequestMapping("/add")
//	@ResponseBody
//	public Json add(AlarmMode demo) {
//		Json j = new Json();
//		try {
//			pService.add(demo);
//			j.setSuccess(true);
//			j.setMsg("添加成功！");
//		} catch (Exception e) {
//			j.setMsg(e.getMessage());
//		}
//		return j;
//	}
//
//	@RequestMapping("/delete")
//	@ResponseBody
//	public Json delete(Integer id) {
//		Json j = new Json();
//		try {
//			pService.delete(id);
//			j.setMsg("删除成功！");
//			j.setSuccess(true);
//		} catch (Exception e) {
//			j.setMsg(e.getMessage());
//		}
//		return j;
//	}
//
//	@RequestMapping("/get")
//	@ResponseBody
//	public AlarmMode get(Integer id) {
//		return pService.get(id);
//	}
//
//	@RequestMapping("/editPage")
//	public String editPage(HttpServletRequest request, Integer id) {
//		AlarmMode r = pService.get(id);
//		request.setAttribute("pageObj", r);
//		return "/base/alarmmodeEdit";
//	}
//
//	@RequestMapping("/edit")
//	@ResponseBody
//	public Json edit(AlarmMode demo) {
//		Json j = new Json();
//		try {
//			pService.edit(demo);
//			j.setSuccess(true);
//			j.setMsg("编辑成功！");
//		} catch (Exception e) {
//			j.setMsg(e.getMessage());
//		}
//		return j;
//	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public Grid dataGrid(ICRechargeRecord record, PageFilter ph) {
		Grid grid = new Grid();
		grid.setRows(pService.dataGrid(record, ph));
		grid.setTotal(pService.count(record, ph));
		return grid;
	}
}
