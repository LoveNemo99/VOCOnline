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
import light.mvc.pageModel.biz.OfflineReport;
import light.mvc.service.base.PollutionSourceInfoServiceI;
import light.mvc.service.biz.OfflineReportServiceI;
import light.mvc.utils.ExcelException;
import light.mvc.utils.ExcelUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/offline")
public class OfflineReportController extends BaseController{

	@Autowired
	private OfflineReportServiceI offlineService;
	
	@Autowired
	private PollutionSourceInfoServiceI psService;
	
	@RequestMapping("/manager")
	public String manager() {
		return "/admin/offlinereport";
	}
	//企业下拉框
	@RequestMapping("/pstree")
	@ResponseBody
	public List<Tree> psTree() {
		return psService.tree();
	}
	
	@RequestMapping("/porttree")
	@ResponseBody
	public List<Tree> portTree(String psCode) {
		return offlineService.portTree(psCode);
	}
	
	@RequestMapping("/dataGrid")
	@ResponseBody
	public Grid dataGrid(OfflineReport record, PageFilter ph) {
		Grid grid = new Grid();
		grid.setRows(offlineService.dataGrid(record, ph));
		grid.setTotal(offlineService.count(record, ph));
		return grid;
	}
	@RequestMapping("/today")
	@ResponseBody
	public Grid today(OfflineReport record, PageFilter ph) {
		Grid grid = new Grid();
		grid.setRows(offlineService.today(record, ph));
		//grid.setTotal(alarmService.count(record, ph));
		return grid;
	}

	//导出报表
	@RequestMapping("/export")
	@ResponseBody
	public Json export(OfflineReport record,HttpServletRequest request,  
	        HttpServletResponse response) throws UnsupportedEncodingException{
		LinkedHashMap<String, String> fieldMap = getLeadToFiledPublicQuestionBank();
		String sheetName = "断线记录";
		PageFilter ph = new PageFilter();
		ph.setOrder("desc");
		List list = offlineService.dataGrid(record, ph);
		if (list == null || list.size() == 0) {  
            System.out.println("断线记录为空");
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
	    superClassMap.put("psCode", "企业");  
	    superClassMap.put("portCode", "排口");  
	    superClassMap.put("offlineTimes", "断线次数");  
	    superClassMap.put("offlineDuration", "断线时长");  
	    superClassMap.put("time", "时间");  
	  
	    return superClassMap;  
	}
	
}
