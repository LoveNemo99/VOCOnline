package light.mvc.service.base;

import java.util.List;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.PsContactInfo;
import light.mvc.pageModel.base.Tree;

public interface PsContactInfoServiceI {
	//public List<Tree> tree();

	public void add(PsContactInfo r);

	public void delete(Integer id);

	public void edit(PsContactInfo r);

	public PsContactInfo get(Integer id);
	
	public List<PsContactInfo> dataGrid(PsContactInfo info,PageFilter ph);

	public Long count(PsContactInfo demo, PageFilter ph);

	public List<Tree> psTree();
}
