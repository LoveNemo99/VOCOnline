package light.mvc.service.biz;

import java.util.List;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.biz.ICCardInfo;

public interface ICCardInfoServiceI {
	public List<Tree> tree();

	public void add(ICCardInfo r);

	public void delete(Integer id);

	public void edit(ICCardInfo r);

	public ICCardInfo get(Integer id);
	
	public List<ICCardInfo> dataGrid(ICCardInfo info,PageFilter ph);

	public Long count(ICCardInfo demo, PageFilter ph);

	public List<Tree> psTree();
	
}
