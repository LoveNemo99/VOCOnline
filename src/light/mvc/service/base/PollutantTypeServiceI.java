package light.mvc.service.base;

import java.util.List;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.PollutantType;
import light.mvc.pageModel.base.Tree;

public interface PollutantTypeServiceI {
	public List<Tree> tree();

	public void add(PollutantType r);

	public void delete(Integer id);

	public void edit(PollutantType r);

	public PollutantType get(Integer id);
	
	public List<PollutantType> dataGrid(PollutantType info,PageFilter ph);

	public Long count(PollutantType demo, PageFilter ph);
}
