package light.mvc.controller.demo;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import light.mvc.controller.base.BaseController;
import light.mvc.pageModel.base.Grid;
import light.mvc.pageModel.base.Json;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.data.StatisticsData;
import light.mvc.service.demo.ReportDataServiceI;
import light.mvc.utils.ExcelException;
import light.mvc.utils.ExcelUtil;
import light.mvc.utils.echarts.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
@RequestMapping("/reportData")
public class ReportDataController extends BaseController{
	
	@Autowired
	private ReportDataServiceI rdService;
	
	@RequestMapping("/manager")
	public String manager() {
		return "/admin/statistics";
	}
	
	@RequestMapping("/datayear")
	public String wateryear() {
		return "/data/datayear";
	}
	@RequestMapping("/datamonth")
	public String watermonth() {
		return "/data/datamonth";
	}
	@RequestMapping("/dataday")
	public String waterday() {
		return "/data/dataday";
	}
	@RequestMapping("/datahour")
	public String waterhour() {
		return "/data/datahour";
	}

	@RequestMapping("/datayeargas")
	public String wateryeargas() {
		return "/data/datayeargas";
	}
	@RequestMapping("/datamonthgas")
	public String watermonthgas() {
		return "/data/datamonthgas";
	}
	@RequestMapping("/datadaygas")
	public String waterdaygas() {
		return "/data/datadaygas";
	}
	@RequestMapping("/datahourgas")
	public String waterhourgas() {
		return "/data/datahourgas";
	}
	//====================================================================================================
	
	
	@RequestMapping("/dataGridYear")
	@ResponseBody
	public List<StatisticsData> dataGridYear(StatisticsData data, PageFilter ph) {
		List<StatisticsData> l = new ArrayList<StatisticsData>();
		l = rdService.dataGrid(data, ph, "year","废气");
		if(l==null)
			l=new ArrayList<StatisticsData>();
		return l;
	}
	
	@RequestMapping("/dataGridMonth")
	@ResponseBody
	public List<StatisticsData> dataGridMonth(StatisticsData data, PageFilter ph) {
		List<StatisticsData> l = rdService.dataGrid(data, ph, "month","废气");
		if(l==null)
			l=new ArrayList<StatisticsData>();
		return l;
	}
	
	@RequestMapping("/dataGridDay")
	@ResponseBody
	public List<StatisticsData> dataGridDay(StatisticsData data, PageFilter ph) {
		List<StatisticsData> l = rdService.dataGrid(data, ph, "day","废气");
		if(l==null)
			l=new ArrayList<StatisticsData>();
		return l;
	}
	
	@RequestMapping("/dataGridHour")
	@ResponseBody
	public List<StatisticsData> dataGridHour(StatisticsData data, PageFilter ph) {
		
		List<StatisticsData> l = rdService.dataGrid(data, ph, "hour","废气");
		System.out.println(l);
		if(l==null)
			l=new ArrayList<StatisticsData>();
		return l;
	}
	
	@RequestMapping("/dataGridYearGas")
	@ResponseBody
	public List<StatisticsData> dataGridYearGas(StatisticsData data, PageFilter ph) {
		List<StatisticsData> l = new ArrayList<StatisticsData>();
		l = rdService.dataGrid(data, ph, "year","废气");
		if(l==null)
			l=new ArrayList<StatisticsData>();
		return l;
	}
	
	@RequestMapping("/dataGridMonthGas")
	@ResponseBody
	public List<StatisticsData> dataGridMonthGas(StatisticsData data, PageFilter ph) {
		List<StatisticsData> l = rdService.dataGrid(data, ph, "month","废气");
		if(l==null)
			l=new ArrayList<StatisticsData>();
		return l;
	}
	
	@RequestMapping("/dataGridDayGas")
	@ResponseBody
	public List<StatisticsData> dataGridDayGas(StatisticsData data, PageFilter ph) {
		List<StatisticsData> l = rdService.dataGrid(data, ph, "day","废气");
		if(l==null)
			l=new ArrayList<StatisticsData>();
		return l;
	}
	
	@RequestMapping("/dataGridHourGas")
	@ResponseBody
	public List<StatisticsData> dataGridHourGas(StatisticsData data, PageFilter ph) {
		//System.out.println(data);
		List<StatisticsData> l = rdService.dataGrid(data, ph, "hour","废气");
		if(l==null)
			l=new ArrayList<StatisticsData>();
		return l;
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
	
	public Json exp(String sheetName,String type,StatisticsData data,HttpServletRequest request,  
	        HttpServletResponse response){
		LinkedHashMap<String, String> fieldMap = null;
		PageFilter ph = new PageFilter();
		List list = null;
		{
			list = rdService.dataGrid(data, ph, type,"");
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
	@RequestMapping("/testpage")
	public String chart() {
//		return "/data/test";
		return "/admin/info";
	}
	@RequestMapping("/test")
	@ResponseBody
	public Json removecauses() throws Exception {
		Gson g = new Gson();
		Json j = new Json();
		Option op = rdService.selectRemoveCauses();
		j.setObj(g.toJson(op));
		j.setSuccess(true);
	    return j;
	}
	//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	@RequestMapping("/gaschart")
	public String gasChart() {
		return "/data/gaschart";
	}
	
	@RequestMapping("/chartdata")
	@ResponseBody
	public Json chartData(String timeType,String factorType) throws Exception {
		Gson g = new Gson();
		Json j = new Json();
		String pollutantType = "废气";//污染物类型：污水 废气
		//String factorType = "废气";//废气  二氧化硫  氮氧化物
		//String timeType = "hour";//时间类型：日月年
		Option op = rdService.getChartData(pollutantType,factorType,timeType);
		j.setObj(g.toJson(op));
		j.setSuccess(true);
	    return j;
	}
	
	@RequestMapping("/piedata")
	@ResponseBody
	public Json pieData() throws Exception {
		Gson g = new Gson();
		Json j = new Json();
		Option op = rdService.getInfoBoardOnlineRate2();
		j.setObj(g.toJson(op));
		j.setSuccess(true);
	    return j;
	}
	
	@RequestMapping("/bardata")
	@ResponseBody
	public Json barData(String code) throws Exception {
		Gson g = new Gson();
		Json j = new Json();
		Option op = rdService.getInfoBoardLargeAmountPs("code");
		j.setObj(g.toJson(op));
		j.setSuccess(true);
	    return j;
	}
	
	@RequestMapping("/bardata2")
	@ResponseBody
	public Json barData2(String code){
		Gson g = new Gson();
		Json j = new Json();
		Option op = rdService.getInfoBoardLargeAmountPs2(code);
		j.setObj(g.toJson(op));
		j.setSuccess(true);
	    return j;
	}
	
	@RequestMapping("/linedata")
	@ResponseBody
	public Json lineData() throws Exception {
		Gson g = new Gson();
		Json j = new Json();
		Option op = rdService.getInfoBoardTrend();
		j.setObj(g.toJson(op));
		j.setSuccess(true);
	    return j;
	}
	
	@RequestMapping("/linedata2")
	@ResponseBody
	public Json lineData2(String psName,String piCode) throws Exception {
		Gson g = new Gson();
		Json j = new Json();
		Option op = rdService.getLineChartData(psName,piCode,"24h");
		j.setObj(g.toJson(op));
		j.setSuccess(true);
	    return j;
	}
}
