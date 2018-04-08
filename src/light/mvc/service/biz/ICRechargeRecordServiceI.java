package light.mvc.service.biz;

import java.util.List;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.biz.ICRechargeRecord;

public interface ICRechargeRecordServiceI {
	public List<Tree> tree();

	public void add(ICRechargeRecord r);

	public void delete(Integer id);

	public void edit(ICRechargeRecord r);

	public ICRechargeRecord get(Integer id);
	
	public List<ICRechargeRecord> dataGrid(ICRechargeRecord info,PageFilter ph);

	public Long count(ICRechargeRecord demo, PageFilter ph);

	public List<Tree> psTree();
	
}
