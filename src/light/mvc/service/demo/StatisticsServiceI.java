package light.mvc.service.demo;

import java.util.List;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.data.StatisticsData;
import light.mvc.pageModel.data.StatisticsDataGas;

public interface StatisticsServiceI {

	public List<StatisticsData> dataGrid(StatisticsData data, PageFilter ph, String tType);

	public Long count(StatisticsData data, PageFilter ph);

	public List<StatisticsData> dataGrid(String type);

	public List<StatisticsData> dataGridRealTime(StatisticsData data, PageFilter ph);

	public List<StatisticsDataGas> dataGridRealTimeGas(StatisticsDataGas data,PageFilter ph);

	public List<StatisticsDataGas> dataGridGas(StatisticsDataGas data, PageFilter ph,String tType);

	public List<Tree> gasTree();

}
