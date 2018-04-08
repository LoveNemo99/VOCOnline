package light.mvc.controller.data;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import light.mvc.controller.base.BaseController;
import light.mvc.pageModel.base.Grid;
import light.mvc.pageModel.base.Json;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.data.StatisticsData;
import light.mvc.pageModel.data.StatisticsDataGas;
import light.mvc.service.demo.StatisticsServiceI;
import light.mvc.utils.ExcelException;
import light.mvc.utils.ExcelUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/report")
public class ReportController extends BaseController{
	
	@Autowired
	private StatisticsServiceI stService;
	
	@RequestMapping("/manager")
	public String manager() {
		return "/data/datahour";
	}
	
	@RequestMapping("/wateryear")
	public String wateryear() {
		return "/data/wateryear";
	}
	@RequestMapping("/watermonth")
	public String watermonth() {
		return "/data/watermonth";
	}
	@RequestMapping("/waterday")
	public String waterday() {
		return "/data/waterday";
	}
	@RequestMapping("/waterhour")
	public String waterhour() {
		return "/data/waterhour";
	}
	@RequestMapping("/waterrealtime")
	public String waterrealtime() {
		return "/data/waterrealtime";
	}
	//====================================================================================================
	@RequestMapping("/gasyear")
	public String gasyear() {
		return "/data/gasyear";
	}
	@RequestMapping("/gasmonth")
	public String gasmonth() {
		return "/data/gasmonth";
	}
	@RequestMapping("/gasday")
	public String gasday() {
		return "/data/gasday";
	}
	@RequestMapping("/gashour")
	public String gashour() {
		return "/data/gashour";
	}
	@RequestMapping("/gasrealtime")
	public String gasrealtime() {
		return "/data/gasrealtime";
	}
	
	@RequestMapping("/dataGridRealTimeWater")
	@ResponseBody
	public List<StatisticsData> dataGridRealTimeWater(StatisticsData data, PageFilter ph) {
		List<StatisticsData> l = stService.dataGridRealTime(data, ph);
		if(l==null)
			l=new ArrayList<StatisticsData>();
		return l;
	}
	
	@RequestMapping("/dataGridRealTimeGas")
	@ResponseBody
	public List<StatisticsDataGas> dataGridRealTimeGas(StatisticsDataGas data, PageFilter ph) {
		List<StatisticsDataGas> l = stService.dataGridRealTimeGas(data, ph);
		if(l==null)
			l=new ArrayList<StatisticsDataGas>();
		return l;
	}
	
	@RequestMapping("/dataGridYearWater")
	@ResponseBody
	public List<StatisticsData> dataGridYearWater(StatisticsData data, PageFilter ph) {
		Grid grid = new Grid();
		List<StatisticsData> l = new ArrayList<StatisticsData>();
		//System.out.println(data.getCompany()+"|"+data.getType()+"|"+data.getCreatedatetimeEnd()+"|"+record.getCreatedatetimeStart());
		//grid.setRows(stService.dataGrid(data, ph, "year"));
		//grid.setTotal(stService.count(data, ph));
		l = stService.dataGrid(data, ph, "year");
		if(l==null)
			l=new ArrayList<StatisticsData>();
		return l;
	}
	
	@RequestMapping("/dataGridMonthWater")
	@ResponseBody
	public List<StatisticsData> dataGridMonthWater(StatisticsData data, PageFilter ph) {
		List<StatisticsData> l = stService.dataGrid(data, ph, "month");
		if(l==null)
			l=new ArrayList<StatisticsData>();
		return l;
	}
	
	@RequestMapping("/dataGridDayWater")
	@ResponseBody
	public List<StatisticsData> dataGridDayWater(StatisticsData data, PageFilter ph) {
		List<StatisticsData> l = stService.dataGrid(data, ph, "day");
		if(l==null)
			l=new ArrayList<StatisticsData>();
		return l;
	}
	
	@RequestMapping("/dataGridHourWater")
	@ResponseBody
	public List<StatisticsData> dataGridHourWater(StatisticsData data, PageFilter ph) {
		List<StatisticsData> l = stService.dataGrid(data, ph, "hour");
		if(l==null)
			l=new ArrayList<StatisticsData>();
		return l;
	}
	
	@RequestMapping("/dataGridYearGas")
	@ResponseBody
	public List<StatisticsDataGas> dataGridYearGas(StatisticsDataGas data, PageFilter ph) {
		List<StatisticsDataGas> l = stService.dataGridGas(data, ph, "year");
		if(l==null)
			l=new ArrayList<StatisticsDataGas>();
		return l;
	}
	
	@RequestMapping("/dataGridMonthGas")
	@ResponseBody
	public List<StatisticsDataGas> dataGridMonthWater(StatisticsDataGas data, PageFilter ph) {
		List<StatisticsDataGas> l = stService.dataGridGas(data, ph, "month");
		if(l==null)
			l=new ArrayList<StatisticsDataGas>();
		return l;
	}
	
	@RequestMapping("/dataGridDayGas")
	@ResponseBody
	public List<StatisticsDataGas> dataGridDayWater(StatisticsDataGas data, PageFilter ph) {
		List<StatisticsDataGas> l = stService.dataGridGas(data, ph, "day");
		if(l==null)
			l=new ArrayList<StatisticsDataGas>();
		return l;
	}
	
	@RequestMapping("/dataGridHourGas")
	@ResponseBody
	public List<StatisticsDataGas> dataGridHourWater(StatisticsDataGas data, PageFilter ph) {
		List<StatisticsDataGas> l = stService.dataGridGas(data, ph, "hour");
		if(l==null)
			l=new ArrayList<StatisticsDataGas>();
		return l;
	}
	
	@RequestMapping("/dataGridWater")
	@ResponseBody
	public Grid dataGrid(String type) {
		System.out.println("type:"+type);
		if(type == null){
			type = "B01";
		}
		Grid grid = new Grid();
		grid.setRows(stService.dataGrid(type));
		return grid;
	}
	
	@RequestMapping("/chartWater")
	@ResponseBody
	public List<StatisticsData> chart(String type) {
		System.out.println("chartWater type:"+type);
		return stService.dataGrid(type);
	}
	
	@RequestMapping("/exportyearwater")
	@ResponseBody
	public Json exportYearWater(StatisticsData data,HttpServletRequest request,  
	        HttpServletResponse response) throws UnsupportedEncodingException{
		return exp("年数据-废水","year",data,request,response);
	}
	@RequestMapping("/exportmonthwater")
	@ResponseBody
	public Json exportMonthWater(StatisticsData data,HttpServletRequest request,  
	        HttpServletResponse response) throws UnsupportedEncodingException{
		return exp("月数据-废水","month",data,request,response);
	}
	@RequestMapping("/exportdaywater")
	@ResponseBody
	public Json exportDayWater(StatisticsData data,HttpServletRequest request,  
	        HttpServletResponse response) throws UnsupportedEncodingException{
		return exp("日数据-废水","day",data,request,response);
	}
	@RequestMapping("/exporthourwater")
	@ResponseBody
	public Json exportHourWater(StatisticsData data,HttpServletRequest request,  
	        HttpServletResponse response) throws UnsupportedEncodingException{
		return exp("小时数据-废水","hour",data,request,response);
	}
	
	@RequestMapping("/exportrealtimewater")
	@ResponseBody
	public Json exportRealTimeWater(StatisticsData data,HttpServletRequest request,  
	        HttpServletResponse response) throws UnsupportedEncodingException{
		return exp("实时数据-废水","realtime",data,request,response);
	}
	
	public Json exp(String sheetName,String type,StatisticsData data,HttpServletRequest request,  
	        HttpServletResponse response){
		LinkedHashMap<String, String> fieldMap = null;
		PageFilter ph = new PageFilter();
		List list = null;
		if(type.equals("realtime")){
			list = stService.dataGridRealTime(data, ph);
			fieldMap = getLeadToFiledPublicQuestionBankRealTime();
		}
		else{
			list = stService.dataGrid(data, ph, type);
			fieldMap = getLeadToFiledPublicQuestionBank();
		}
		if (list == null || list.size() == 0) {  
            System.out.println("报警记录为空");
        }else {  
            try {
				ExcelUtil.listToExcel(list, fieldMap, sheetName, response);
			} catch (ExcelException e) {
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
	    superClassMap.put("code", "MM");
	    superClassMap.put("pollutionSourceName", "企业");
	    superClassMap.put("time", "数据时间");
	    superClassMap.put("couB01", "废水");
	    superClassMap.put("avg101", "总磷");
	    superClassMap.put("avg060", "氨氮");
	    superClassMap.put("avg011", "COD");
	    superClassMap.put("avg463", "电导率");
	  
	    return superClassMap;  
	}
	
	public LinkedHashMap<String, String> getLeadToFiledPublicQuestionBankRealTime() {  		  
	    LinkedHashMap<String, String> superClassMap = new LinkedHashMap<String, String>();  
	    superClassMap.put("code", "MM");
	    superClassMap.put("isOnline", "状态");
	    superClassMap.put("pollutionSourceName", "企业");
	    superClassMap.put("time", "数据时间");
	    superClassMap.put("avgB01", "废水");
	    superClassMap.put("avg101", "总磷");
	    superClassMap.put("avg060", "氨氮");
	    superClassMap.put("avg011", "COD");
	    superClassMap.put("avg463", "电导率");
	    superClassMap.put("avg001", "PH");
	    return superClassMap;  
	}
	//==================================================================================================
	@RequestMapping("/exportyeargas")
	@ResponseBody
	public Json exportYearWater(StatisticsDataGas data,HttpServletRequest request,  
	        HttpServletResponse response) throws UnsupportedEncodingException{
		return expGas("年数据-废气","year",data,request,response);
	}
	@RequestMapping("/exportmonthgas")
	@ResponseBody
	public Json exportMonthWater(StatisticsDataGas data,HttpServletRequest request,  
	        HttpServletResponse response) throws UnsupportedEncodingException{
		return expGas("月数据-废气","month",data,request,response);
	}
	@RequestMapping("/exportdaygas")
	@ResponseBody
	public Json exportDayWater(StatisticsDataGas data,HttpServletRequest request,  
	        HttpServletResponse response) throws UnsupportedEncodingException{
		return expGas("日数据-废气","day",data,request,response);
	}
	@RequestMapping("/exporthourgas")
	@ResponseBody
	public Json exportHourWater(StatisticsDataGas data,HttpServletRequest request,  
	        HttpServletResponse response) throws UnsupportedEncodingException{
		return expGas("小时数据-废气","hour",data,request,response);
	}
	@RequestMapping("/exportrealtimegas")
	@ResponseBody
	public Json exportRealTimeGas(StatisticsDataGas data,HttpServletRequest request,  
	        HttpServletResponse response) throws UnsupportedEncodingException{
		return expGas("实时数据-废气","realtime",data,request,response);
	}
	
	public Json expGas(String sheetName,String type,StatisticsDataGas data,HttpServletRequest request,  
	        HttpServletResponse response){
		LinkedHashMap<String, String> fieldMap = null;
		PageFilter ph = new PageFilter();
		List list = null;
		if(type.equals("realtime")){
			list = stService.dataGridRealTimeGas(data, ph);
			fieldMap = getLeadToFiledPublicQuestionBankRealTimeGas();
		}
		else{
			list = stService.dataGridGas(data, ph, type);
			fieldMap = getLeadToFiledPublicQuestionBankGas();
		}
		if (list == null || list.size() == 0) {  
            System.out.println("报警记录为空");
        }else {  
            try {
				ExcelUtil.listToExcel(list, fieldMap, sheetName, response);
			} catch (ExcelException e) {
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
	
	public LinkedHashMap<String, String> getLeadToFiledPublicQuestionBankRealTimeGas() {  		  
	    LinkedHashMap<String, String> superClassMap = new LinkedHashMap<String, String>();  
	    superClassMap.put("code", "MM");
	    superClassMap.put("isOnline", "状态");
	    superClassMap.put("pollutionSourceName", "企业");
	    superClassMap.put("time", "数据时间");
	    superClassMap.put("avgB02", "废气");
	    superClassMap.put("avgS01", "氧气含量");
	    superClassMap.put("avg03", "氮氧化物");
	    superClassMap.put("avg02", "二氧化硫");
	    superClassMap.put("avg01", "烟尘");
	    return superClassMap;  
	}
	
	public LinkedHashMap<String, String> getLeadToFiledPublicQuestionBankGas() {  		  
	    LinkedHashMap<String, String> superClassMap = new LinkedHashMap<String, String>();  
	    superClassMap.put("code", "MM");
	    superClassMap.put("pollutionSourceName", "企业");
	    superClassMap.put("time", "数据时间");
	    superClassMap.put("couB02", "废气");
	    superClassMap.put("avgS01", "氧气含量");
	    superClassMap.put("avg03", "氮氧化物");
	    superClassMap.put("avg02", "二氧化硫");
	    superClassMap.put("avg01", "烟尘");
	    return superClassMap;
	}
	
	@RequestMapping("/gastree")
	@ResponseBody
	public List<Tree> psTree() {
		return stService.gasTree();
	}
}
