package light.mvc.service.biz;

import java.util.List;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.biz.AlarmMode;

public interface AlarmModeServiceI {
	public List<Tree> tree();

	public void add(AlarmMode r);

	public void delete(Integer id);

	public void edit(AlarmMode r);

	public AlarmMode get(Integer id);
	
	public List<AlarmMode> dataGrid(AlarmMode info,PageFilter ph);

	public Long count(AlarmMode demo, PageFilter ph);
}
