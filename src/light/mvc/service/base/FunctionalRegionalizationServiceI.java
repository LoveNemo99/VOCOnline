package light.mvc.service.base;

import java.util.List;

import light.mvc.pageModel.base.FunctionalRegionalization;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;

public interface FunctionalRegionalizationServiceI {
	public List<Tree> tree();

	public void add(FunctionalRegionalization r);

	public void delete(Integer id);

	public void edit(FunctionalRegionalization r);

	public FunctionalRegionalization get(Integer id);
	
	public List<FunctionalRegionalization> dataGrid(FunctionalRegionalization info,PageFilter ph);

	public Long count(FunctionalRegionalization demo, PageFilter ph);
}
