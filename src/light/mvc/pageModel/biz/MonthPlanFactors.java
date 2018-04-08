package light.mvc.pageModel.biz;

import java.util.List;

public class MonthPlanFactors{
	private List<MonthPlanPollutant> list;

	public List<MonthPlanPollutant> getList() {
		return list;
	}

	public void setList(List<MonthPlanPollutant> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "MonthPlanFactors [list=" + list + "]";
	}

}
