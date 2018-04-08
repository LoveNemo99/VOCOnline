package light.mvc.service.biz;

import java.util.List;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.biz.TotalCalculateAnalysis;
import light.mvc.utils.echarts.Option;

public interface TotalCalculateAnalysisServiceI {

	List<Tree> portTree(String psCode);

	List<Tree> psTree();

	List<TotalCalculateAnalysis> dataGrid(TotalCalculateAnalysis demo,
			PageFilter ph);

	Option getTotalAnalysis(Integer psId);

	Option getTotalAnalysisCopy(Integer psId, String psCode, String psName,
			String factorCode, String factorName);
}
