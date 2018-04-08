package light.mvc.service.biz;

import java.util.List;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.biz.AlarmSetting;

public interface AlarmSettingServiceI {
	//public List<Tree> tree();

	public void add(AlarmSetting r);

	public void delete(Integer id);

	public void edit(AlarmSetting r);

	public AlarmSetting get(Integer id);
	
	public List<AlarmSetting> dataGrid(AlarmSetting info,PageFilter ph);

	public Long count(AlarmSetting demo, PageFilter ph);

	public List<Tree> factorTree();
}
