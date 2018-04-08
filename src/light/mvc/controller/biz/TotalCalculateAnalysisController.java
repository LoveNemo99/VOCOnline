package light.mvc.controller.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import light.mvc.controller.base.BaseController;
import light.mvc.pageModel.base.Grid;
import light.mvc.pageModel.base.Json;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.biz.TotalCalculateAnalysis;
import light.mvc.pageModel.sys.AlarmType;
import light.mvc.service.base.PollutionSourceInfoServiceI;
import light.mvc.service.biz.TotalCalculateAnalysisServiceI;
import light.mvc.utils.echarts.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
@RequestMapping("/totalanalysis")
public class TotalCalculateAnalysisController extends BaseController{

	@Autowired
	private TotalCalculateAnalysisServiceI analysisService;
	
	@Autowired
	private PollutionSourceInfoServiceI psService;
	
	@RequestMapping("/manager")
	public String manager() {
		return "/admin/totalanalysis";
	}
	
	@RequestMapping("/analysisPage")
	public String analysisPage(HttpServletRequest request, Integer id) {
		request.setAttribute("pageObj", id);
		return "/admin/analysisPage";
	}
	
	@RequestMapping("/analysis")
	@ResponseBody
	public Json analysis(Integer id) {
		Option option = analysisService.getTotalAnalysis(id);
		Gson g = new Gson();
		Json j = new Json();
		j.setObj(g.toJson(option));
		j.setSuccess(true);
	    return j;
	}
	
	@RequestMapping("/analysis2")
	@ResponseBody
	public Json analysis2(Integer id,String psCode, String psName,String factorCode, String factorName) {
		Option option = analysisService.getTotalAnalysisCopy(id, psCode, psName, factorCode, factorName);
		Gson g = new Gson();
		Json j = new Json();
		j.setObj(g.toJson(option));
		j.setSuccess(true);
	    return j;
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
		return analysisService.portTree(psCode);
	}
	
	@RequestMapping("/dataGrid")
	@ResponseBody
	public Grid dataGrid(TotalCalculateAnalysis record, PageFilter ph) {
		Grid grid = new Grid();
		grid.setRows(analysisService.dataGrid(record, ph));
//		grid.setTotal(offlineService.count(record, ph));
		return grid;
	}
	
}
