package light.mvc.service.biz;

import java.util.List;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.biz.MonthPlanMain;
import light.mvc.pageModel.biz.MonthPlanPollutant;

public interface MonthPlanServiceI {
	public void add(MonthPlanMain r);

	public void delete(Integer id);
	
	public Boolean submit(Integer id,String man);

	public void edit(MonthPlanMain r);

	public MonthPlanMain get(Integer id);
	
	public List<MonthPlanMain> dataGrid(MonthPlanMain info,PageFilter ph);

	public Long count(MonthPlanMain demo, PageFilter ph);

	public List<Tree> psTree(String code);

	public List<MonthPlanPollutant> getFactorLimit(Integer id);

	public void editFactor(List<MonthPlanPollutant> record);

	public Boolean grant(Integer id, String man, String s);

	public void cause(Integer id, String cause);

}
