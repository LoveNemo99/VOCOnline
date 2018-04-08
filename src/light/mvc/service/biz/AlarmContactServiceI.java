package light.mvc.service.biz;

import java.util.List;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.sys.AlarmContact;

public interface AlarmContactServiceI {
	public List<Tree> tree();

	public void add(AlarmContact r);

	public void delete(Integer id);

	public void edit(AlarmContact r);

	public AlarmContact get(Integer id);
	
	public List<AlarmContact> dataGrid(AlarmContact info,PageFilter ph);

	public Long count(AlarmContact demo, PageFilter ph);

	public List<Tree> psTree();
}
