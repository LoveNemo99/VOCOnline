package light.mvc.service.demo;

import java.util.List;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.data.ConditionData;

public interface ConditionDataServiceI {

	public List<ConditionData> RealtimeCondition();

	public List<String> getConditionName();

}
