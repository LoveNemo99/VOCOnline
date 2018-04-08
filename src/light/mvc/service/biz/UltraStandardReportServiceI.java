package light.mvc.service.biz;

import java.util.List;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.biz.UltraStandardReport;

public interface UltraStandardReportServiceI {

	public List<UltraStandardReport> dataGrid(UltraStandardReport record, PageFilter ph);

	public Long count(UltraStandardReport record, PageFilter ph);

	public void add(UltraStandardReport record);

	public void delete(Integer id);

	public void edit(UltraStandardReport record);

	public UltraStandardReport get(Integer id);

	public List<UltraStandardReport> today(UltraStandardReport demo, PageFilter ph);

	List<Tree> portTree(String psCode);

	List<Tree> psTree();

	List<Tree> factorTree();
}
