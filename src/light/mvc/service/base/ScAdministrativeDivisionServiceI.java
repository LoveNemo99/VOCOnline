package light.mvc.service.base;

import java.util.List;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.ScAdministrativeDivision;
import light.mvc.pageModel.base.Tree;

public interface ScAdministrativeDivisionServiceI {
	public List<Tree> tree();

	public void add(ScAdministrativeDivision r);

	public void delete(Integer id);

	public void edit(ScAdministrativeDivision r);

	public ScAdministrativeDivision get(Integer id);
	
	public List<ScAdministrativeDivision> dataGrid(ScAdministrativeDivision info,PageFilter ph);

	public Long count(ScAdministrativeDivision demo, PageFilter ph);
}
