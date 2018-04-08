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
import light.mvc.pageModel.biz.DataRealtimeMonitor;
import light.mvc.service.biz.DataRealtimeMonitorServiceI;
import light.mvc.utils.ExcelException;
import light.mvc.utils.ExcelUtil;
import light.mvc.utils.echarts.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
@RequestMapping("/realtimemonitor")
public class DataRealtimeMonitorController extends BaseController{

	@Autowired
	private DataRealtimeMonitorServiceI monitorService;
	
	@RequestMapping("/manager")
	public String manager() {
		return "/data/waterdatamonitor";
	}
	//企业下拉框
	@RequestMapping("/pstree")
	@ResponseBody
	public List<Tree> psTree() {
		return monitorService.psTree();
	}
	
	@RequestMapping("/dataGrid")
	@ResponseBody
	public Grid dataGrid(DataRealtimeMonitor record, PageFilter ph) {
		Grid grid = new Grid();
		grid.setRows(monitorService.dataGrid(record, ph));
		grid.setTotal(monitorService.count(record, ph));
		return grid;
	}
	
	@RequestMapping("/line")
	@ResponseBody
	public Json line(String psName,String piName,String piCode,String factorName,String factorCode,String timeType) {
		System.out.println(psName+" "+piName+" "+piCode+" "+factorName+" "+factorCode+" "+timeType);
		Option option = monitorService.getLineChartData(psName, piName, piCode, factorCode, factorName, timeType);
		Gson g = new Gson();
		Json j = new Json();
		System.out.println(g.toJson(option));
		j.setObj(g.toJson(option));
		j.setSuccess(true);
	    return j;
	}

	//导出报警记录报表
	@RequestMapping("/export")
	@ResponseBody
	public Json export(DataRealtimeMonitor record,HttpServletRequest request,  
	        HttpServletResponse response) throws UnsupportedEncodingException{
		LinkedHashMap<String, String> fieldMap = getLeadToFiledPublicQuestionBank();
		String sheetName = "实时监控";
		PageFilter ph = new PageFilter();
		ph.setOrder("desc");
		List list = monitorService.dataGrid(record, ph);
		if (list == null || list.size() == 0) {  
            System.out.println("实时监控为空");
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
