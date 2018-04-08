package light.mvc.service.demo;

import java.util.List;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.data.StatisticsData;
import light.mvc.utils.echarts.Option;

public interface ReportDataServiceI {

	//public List<StatisticsData> dataGridByForm(StatisticsData data, PageFilter ph,String tType);

	//public List<StatisticsData> dataGrid(StatisticsData data, PageFilter ph,String tType);

	public List<StatisticsData> dataGrid(StatisticsData data, PageFilter ph,
			String tType, String pollutantType);

	public List<StatisticsData> dataGridByForm(StatisticsData data, PageFilter ph,
			String tType, String pollutantType);

	public Option selectRemoveCauses() throws Exception;

	public Option getChartData(String pollutantType, String factorType,
			String timeType);

	Option getInfoBoardOnlineRate();

	Option getInfoBoardOnlineRate2();

	Option getInfoBoardLargeAmountPs(String code);

	Option getInfoBoardTrend();

	Option getInfoBoardLargeAmountPs2(String code);

	Option getLineChartData(String psName, String piCode, String timeType);

}
