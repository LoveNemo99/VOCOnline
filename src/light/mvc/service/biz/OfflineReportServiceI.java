package light.mvc.service.biz;

import java.util.List;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.biz.OfflineReport;

public interface OfflineReportServiceI {

	public List<OfflineReport> dataGrid(OfflineReport record, PageFilter ph);

	public Long count(OfflineReport record, PageFilter ph);

	public void add(OfflineReport record);

	public void delete(Integer id);

	public void edit(OfflineReport record);

	public OfflineReport get(Long id);

	public List<OfflineReport> today(OfflineReport demo, PageFilter ph);

	List<Tree> portTree(String psCode);

	List<Tree> psTree();
}
