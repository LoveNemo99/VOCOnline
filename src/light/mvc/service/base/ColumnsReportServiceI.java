package light.mvc.service.base;

import java.util.List;

import light.mvc.pageModel.base.ColumnsReport;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;

public interface ColumnsReportServiceI {
	//public List<Tree> tree();

	//public void add(ColumnsReport r);

	public void delete(Integer id);

	public void edit(ColumnsReport r,String type);

	public ColumnsReport get(Integer id);
	
	//public List<ColumnsReport> dataGrid(ColumnsReport info,PageFilter ph);

	public Long count(ColumnsReport demo, PageFilter ph);

	public List<Tree> unitTree();

	public List<Tree> factorTree();

	public List<ColumnsReport> dataGrid(ColumnsReport info, PageFilter ph, String type);

	public void add(ColumnsReport r, String type);
}
