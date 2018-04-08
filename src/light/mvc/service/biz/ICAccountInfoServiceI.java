package light.mvc.service.biz;

import java.util.List;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.biz.ICAccountInfo;

public interface ICAccountInfoServiceI {
	public List<Tree> tree();

	public void add(ICAccountInfo r);

	public void delete(Integer id);

	public void edit(ICAccountInfo r);

	public ICAccountInfo get(Integer id);
	
	public List<ICAccountInfo> dataGrid(ICAccountInfo info,PageFilter ph);

	public Long count(ICAccountInfo demo, PageFilter ph);

	public List<Tree> psTree();
	
}
