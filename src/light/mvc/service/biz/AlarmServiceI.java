package light.mvc.service.biz;

import java.util.List;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.biz.AlarmRecord;

public interface AlarmServiceI {

	public List<AlarmRecord> dataGrid(AlarmRecord record, PageFilter ph);

	public Long count(AlarmRecord record, PageFilter ph);

	public void add(AlarmRecord record);

	public void delete(Integer id);

	public void edit(AlarmRecord record);

	public AlarmRecord get(Long id);
	
	public List<Tree> tree();

	public List<AlarmRecord> today(AlarmRecord demo, PageFilter ph);
}
