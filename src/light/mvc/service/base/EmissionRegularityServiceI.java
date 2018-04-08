package light.mvc.service.base;

import java.util.List;

import light.mvc.pageModel.base.EmissionRegularity;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;

public interface EmissionRegularityServiceI {
	public List<Tree> tree();

	public void add(EmissionRegularity r);

	public void delete(Integer id);

	public void edit(EmissionRegularity r);

	public EmissionRegularity get(Integer id);
	
	public List<EmissionRegularity> dataGrid(EmissionRegularity info,PageFilter ph);

	public Long count(EmissionRegularity demo, PageFilter ph);
}
