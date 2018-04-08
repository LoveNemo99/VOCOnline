package light.mvc.controller.demo;

import java.util.ArrayList;
import java.util.List;

import light.mvc.controller.base.BaseController;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.data.ConditionData;
import light.mvc.pageModel.data.StatisticsData;
import light.mvc.service.demo.ConditionDataServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/condition")
public class WorkingConditionController extends BaseController{
	
	@Autowired
	private ConditionDataServiceI rdService;
	
	@RequestMapping("/manager")
	public String manager() {
		return "/data/condition";
	}
	
	@RequestMapping("/titles")
	@ResponseBody
	public List<String> titles(StatisticsData data, PageFilter ph) {
		List<String> l = new ArrayList<String>();
		l = rdService.getConditionName();
		if(l==null)
			l=new ArrayList<String>();
		return l;
	}
	
	@RequestMapping("/dataGrid")
	@ResponseBody
	public List<ConditionData> dataGrid(StatisticsData data, PageFilter ph) {
		List<ConditionData> l = rdService.RealtimeCondition();
		if(l==null)
			l=new ArrayList<ConditionData>();
		//System.out.println("l"+l);
		return l;
	}
}
