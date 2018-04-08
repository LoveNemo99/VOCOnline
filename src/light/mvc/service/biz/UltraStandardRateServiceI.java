package light.mvc.service.biz;

import java.util.List;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.biz.UltraStandardRate;

public interface UltraStandardRateServiceI {

	public List<UltraStandardRate> dataGrid(UltraStandardRate record, PageFilter ph);

	public Long count(UltraStandardRate record, PageFilter ph);

	public void add(UltraStandardRate record);

	public void delete(Integer id);

	public void edit(UltraStandardRate record);

	public UltraStandardRate get(Integer id);

	public List<UltraStandardRate> today(UltraStandardRate demo, PageFilter ph);

	List<Tree> portTree(String psCode);

	List<Tree> psTree();

	List<Tree> factorTree();
}
