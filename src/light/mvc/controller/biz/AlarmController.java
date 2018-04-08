package light.mvc.controller.biz;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import light.mvc.controller.base.BaseController;
import light.mvc.pageModel.base.Grid;
import light.mvc.pageModel.base.Json;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.biz.AlarmRecord;
import light.mvc.service.base.PollutionSourceInfoServiceI;
import light.mvc.service.biz.AlarmServiceI;
import light.mvc.utils.ExcelException;
import light.mvc.utils.ExcelUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/alarm")
public class AlarmController extends BaseController{

	@Autowired
	private AlarmServiceI alarmService;
	
	@Autowired
	private PollutionSourceInfoServiceI psService;
	
	@RequestMapping("/manager")
	public String manager() {
		return "/admin/alarm";
	}
	//企业下拉框
	@RequestMapping("/pstree")
	@ResponseBody
	public List<Tree> psTree() {
		return psService.tree();
	}
	//报警类型下拉框
	@RequestMapping("/alarmtypetree")
	@ResponseBody
	public List<Tree> alarmTypeTree() {
		return alarmService.tree();
	}
	
	@RequestMapping("/dataGrid")
	@ResponseBody
	public Grid dataGrid(AlarmRecord record, PageFilter ph) {
		Grid grid = new Grid();
		grid.setRows(alarmService.dataGrid(record, ph));
		grid.setTotal(alarmService.count(record, ph));
		return grid;
	}
	@RequestMapping("/today")
	@ResponseBody
	public Grid today(AlarmRecord record, PageFilter ph) {
		Grid grid = new Grid();
		grid.setRows(alarmService.today(record, ph));
		//grid.setTotal(alarmService.count(record, ph));
		return grid;
	}

	//导出报警记录报表
	@RequestMapping("/export")
	@ResponseBody
	public Json export(AlarmRecord record,HttpServletRequest request,  
	        HttpServletResponse response) throws UnsupportedEncodingException{
		LinkedHashMap<String, String> fieldMap = getLeadToFiledPublicQuestionBank();
		String sheetName = "报警记录";
		PageFilter ph = new PageFilter();
		ph.setOrder("desc");
		List list = alarmService.dataGrid(record, ph);
		if (list == null || list.size() == 0) {  
            System.out.println("报警记录为空");
        }else {  
            try {
				ExcelUtil.listToExcel(list, fieldMap, sheetName, response);
			} catch (ExcelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
            System.out.println("导出成功~~~~");  
        }  
		Json j = new Json();
		try {
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	public LinkedHashMap<String, String> getLeadToFiledPublicQuestionBank() {  		  
	    LinkedHashMap<String, String> superClassMap = new LinkedHashMap<String, String>();  
	    superClassMap.put("company", "企业");  
	    superClassMap.put("text", "报警内容");  
	    superClassMap.put("type", "报警类型");  
	    superClassMap.put("time", "报警时间");  
	    superClassMap.put("smsName", "短信接收人");  
	  
	    return superClassMap;  
	}
	
}
