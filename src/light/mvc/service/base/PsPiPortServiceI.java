package light.mvc.service.base;

import java.util.List;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.PsPiPort;
import light.mvc.pageModel.base.Tree;

public interface PsPiPortServiceI {
	public List<Tree> tree();

	public void add(PsPiPort r);

	public void delete(Integer id);

	public void edit(PsPiPort r);

	public PsPiPort get(Integer id);
	
	public List<PsPiPort> dataGrid(PsPiPort info,PageFilter ph);

	public Long count(PsPiPort demo, PageFilter ph);

	public List<Tree> psTree();

	public List<Tree> pollutantTypeTree();
}
