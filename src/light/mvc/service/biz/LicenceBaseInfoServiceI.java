package light.mvc.service.biz;

import java.util.List;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.biz.LicenceBaseInfo;
import light.mvc.pageModel.biz.LicencePollutantInfo;

public interface LicenceBaseInfoServiceI {
	public void add(LicenceBaseInfo r);

	public void delete(Integer id);

	public void edit(LicenceBaseInfo r);

	public LicenceBaseInfo get(Integer id);
	
	public List<LicenceBaseInfo> dataGrid(LicenceBaseInfo info,PageFilter ph);

	public Long count(LicenceBaseInfo demo, PageFilter ph);

	public List<Tree> psTree();

	public List<LicencePollutantInfo> getFactorLimit(Integer id);

	public void editFactor(List<LicencePollutantInfo> record);
}
