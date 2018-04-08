package light.mvc.service.base;

import java.util.List;

import light.mvc.pageModel.base.EmissionDestination;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;

public interface EmissionDestinationServiceI {
	public List<Tree> tree();

	public void add(EmissionDestination r);

	public void delete(Integer id);

	public void edit(EmissionDestination r);

	public EmissionDestination get(Integer id);
	
	public List<EmissionDestination> dataGrid(EmissionDestination info,PageFilter ph);

	public Long count(EmissionDestination demo, PageFilter ph);
}
