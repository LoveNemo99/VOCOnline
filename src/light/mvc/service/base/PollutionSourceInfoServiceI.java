package light.mvc.service.base;

import java.util.List;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.PsPollutionSourceInfo;
import light.mvc.pageModel.base.Tree;

public interface PollutionSourceInfoServiceI {
	public List<Tree> tree();

	public void add(PsPollutionSourceInfo r);

	public void delete(Integer id);

	public void edit(PsPollutionSourceInfo r);

	public PsPollutionSourceInfo get(Integer id);
	
	public List<PsPollutionSourceInfo> dataGrid(PsPollutionSourceInfo info,PageFilter ph);

	public Long count(PsPollutionSourceInfo demo, PageFilter ph);

	public List<Tree> inCategoryTree();

	public List<Tree> sizeTree();

	public List<Tree> enRegistrationTree();

	public List<Tree> enCategoryTree();

	public List<Tree> affiliationTree();

	public List<Tree> divisionTree();

	public List<Tree> basinTree();
}
