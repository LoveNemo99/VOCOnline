package light.mvc.controller.base;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import light.mvc.controller.base.BaseController;
import light.mvc.pageModel.base.Grid;
import light.mvc.pageModel.base.Json;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.ScPollutantFactor;
import light.mvc.pageModel.base.Tree;
import light.mvc.service.base.ScPollutantFactorServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/pollutantfactor")
public class ScPollutantFactorController extends BaseController{

	@Autowired
	private ScPollutantFactorServiceI factorService;
	
//	@Autowired
//	private PollutionSourceInfoServiceI psService;
	
	@RequestMapping("/manager")
	public String manager() {
		return "/base/factor";
	}
	@RequestMapping("/choosetree")
	@ResponseBody
	public List<Tree> chooseTree() {
		List<Tree> lt = new ArrayList<Tree>();
		
		Tree tree = new Tree();
		tree.setId("1");
		tree.setText("是");
		tree.setValue("是");
		lt.add(tree);
		tree = new Tree();
		tree.setId("2");
		tree.setText("否");
		tree.setValue("否");
		lt.add(tree);
		
		return lt;
	}
	
	@RequestMapping("/usetree")
	@ResponseBody
	public List<Tree> useTree() {
		List<Tree> lt = new ArrayList<Tree>();
		
		Tree tree = new Tree();
		tree.setText("启用");
		tree.setValue("启用");
		lt.add(tree);
		tree = new Tree();
		tree.setText("停用");
		tree.setValue("停用");
		lt.add(tree);
		
		return lt;
	}
	
	@RequestMapping("/factortree")
	@ResponseBody
	public List<Tree> factorTree() {
		return factorService.tree();
	}
	
	@RequestMapping("/unit")
	@ResponseBody
	public List<Tree> unitTree() {
		return factorService.unitTree();
	}
	
	@RequestMapping("/pollutanttype")
	@ResponseBody
	public List<Tree> pollutantTree() {
		return factorService.pollutantTypeTree();
	}
	
	@RequestMapping("/addPage")
	public String addPage() {
		return "/base/factorAdd";
	}
	
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		ScPollutantFactor r = factorService.get(id);
		request.setAttribute("pageObj", r);
		return "/base/factorEdit";
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Json add(ScPollutantFactor record) {
		Json j = new Json();
		System.out.println(record.toString());
		try {
			factorService.add(record);
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
			factorService.delete(id);
			j.setMsg("删除成功！");
			j.setSuccess(true);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(ScPollutantFactor record) {
		Json j = new Json();
		System.out.println(record.toString());
		try {
			factorService.edit(record);
			j.setSuccess(true);
			j.setMsg("编辑成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@RequestMapping("/dataGrid")
	@ResponseBody
	public Grid dataGrid(ScPollutantFactor record, PageFilter ph) {
		Grid grid = new Grid();
		//List<ScPollutantFactor> l = factorService.dataGrid(record, ph);
		grid.setRows(factorService.dataGrid(record, ph));
		grid.setTotal(factorService.count(record, ph));
		return grid;
	}
	
}
