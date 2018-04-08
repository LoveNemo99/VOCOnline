package light.mvc.service.demo;

import java.util.List;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.maintenance.MaintenanceWorker;

public interface MaintenanceServiceI {

	void add(MaintenanceWorker r);

	void delete(Integer id);

	void edit(MaintenanceWorker r);

	MaintenanceWorker get(Integer id);

	List<MaintenanceWorker> dataGridWorker(MaintenanceWorker demo, PageFilter ph);

	Long count(MaintenanceWorker demo, PageFilter ph);
	
}
