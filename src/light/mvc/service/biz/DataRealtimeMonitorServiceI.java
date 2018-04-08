package light.mvc.service.biz;

import java.util.List;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.biz.DataRealtimeMonitor;
import light.mvc.utils.echarts.Option;

public interface DataRealtimeMonitorServiceI {

	public List<DataRealtimeMonitor> dataGrid(DataRealtimeMonitor record, PageFilter ph);

	public Long count(DataRealtimeMonitor record, PageFilter ph);

	public DataRealtimeMonitor get(Long id);

	public List<Tree> psTree();

	Option getLineChartData(String psName, String piName, String piCode,
			String factorCode, String factorName, String timeType);
}
