package light.mvc.service.biz;

import java.util.List;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.biz.RoutingInspection;

public interface RoutingInspectionServiceI {
	public List<Tree> psTree();
	
	public List<Tree> unInspectPsTree();

	public void add(RoutingInspection r);

	public void delete(Integer id);

	public void edit(RoutingInspection r);

	public RoutingInspection get(Integer id);
	
	public List<RoutingInspection> dataGrid(RoutingInspection info,PageFilter ph);

	public Long count(RoutingInspection demo, PageFilter ph);

	public List<Tree> stateTree();
}
