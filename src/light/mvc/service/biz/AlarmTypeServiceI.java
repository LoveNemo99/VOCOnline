package light.mvc.service.biz;

import java.util.List;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.sys.AlarmType;

public interface AlarmTypeServiceI {
	public List<Tree> tree();

	public void add(AlarmType r);

	public void delete(Integer id);

	public void edit(AlarmType r);

	public AlarmType get(Integer id);
	
	public List<AlarmType> dataGrid(AlarmType info,PageFilter ph);

	public Long count(AlarmType demo, PageFilter ph);
}
