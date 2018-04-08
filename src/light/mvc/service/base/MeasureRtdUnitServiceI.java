package light.mvc.service.base;

import java.util.List;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.ScMeasureRtdUnit;
import light.mvc.pageModel.base.Tree;

public interface MeasureRtdUnitServiceI {
	public List<Tree> tree();

	public void add(ScMeasureRtdUnit r);

	public void delete(Integer id);

	public void edit(ScMeasureRtdUnit r);

	public ScMeasureRtdUnit get(Integer id);
	
	public List<ScMeasureRtdUnit> dataGrid(ScMeasureRtdUnit info,PageFilter ph);

	public Long count(ScMeasureRtdUnit demo, PageFilter ph);
}
