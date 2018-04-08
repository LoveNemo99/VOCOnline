package light.mvc.service.base;

import java.util.List;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.ScPollutantFactor;
import light.mvc.pageModel.base.Tree;

public interface ScPollutantFactorServiceI {
	public List<Tree> tree();

	public void add(ScPollutantFactor r);

	public void delete(Integer id);

	public void edit(ScPollutantFactor r);

	public ScPollutantFactor get(Integer id);
	
	public List<ScPollutantFactor> dataGrid(ScPollutantFactor info,PageFilter ph);

	public Long count(ScPollutantFactor demo, PageFilter ph);

	public List<Tree> unitTree();

	public List<Tree> pollutantTypeTree();
}
