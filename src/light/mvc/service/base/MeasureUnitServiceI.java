package light.mvc.service.base;

import java.util.List;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.ScMeasureUnit;
import light.mvc.pageModel.base.Tree;

public interface MeasureUnitServiceI {
	public List<Tree> tree();

	public void add(ScMeasureUnit r);

	public void delete(Integer id);

	public void edit(ScMeasureUnit r);

	public ScMeasureUnit get(Integer id);
	
	public List<ScMeasureUnit> dataGrid(ScMeasureUnit info,PageFilter ph);

	public Long count(ScMeasureUnit demo, PageFilter ph);
}
