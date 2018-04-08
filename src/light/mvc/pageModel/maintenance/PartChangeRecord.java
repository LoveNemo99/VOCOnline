package light.mvc.pageModel.maintenance;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * part_change_record:
 */
@Entity
@Table(name = "part_change_record")
public class PartChangeRecord implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * id_:
	 */
	private int id;

	/**
	 * part_name_:
	 */
	private String partName;

	/**
	 * time_:
	 */
	private Date time;

	/**
	 * company_:
	 */
	private String company;

	/**
	 * maintenance_worker_:
	 */
	private String maintenanceWorker;

	/**
	 * is_return_warehouse_:
	 */
	private boolean isReturnWarehouse;

	/**
	 * remarks:
	 */
	private String remarks;

	public PartChangeRecord() {
		super();
	}

	public PartChangeRecord(int id, String partName, Date time, String company,
			String maintenanceWorker, boolean isReturnWarehouse, String remarks) {
		super();
		this.id = id;
		this.partName = partName;
		this.time = time;
		this.company = company;
		this.maintenanceWorker = maintenanceWorker;
		this.isReturnWarehouse = isReturnWarehouse;
		this.remarks = remarks;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_", nullable = false)
	public int getId() {
		return id;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	@Column(name = "part_name_", length = 50)
	public String getPartName() {
		return partName;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time_")
	public Date getTime() {
		return time;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "company_", length = 30)
	public String getCompany() {
		return company;
	}

	public void setMaintenanceWorker(String maintenanceWorker) {
		this.maintenanceWorker = maintenanceWorker;
	}

	@Column(name = "maintenance_worker_", length = 20)
	public String getMaintenanceWorker() {
		return maintenanceWorker;
	}

	public void setIsReturnWarehouse(boolean isReturnWarehouse) {
		this.isReturnWarehouse = isReturnWarehouse;
	}

	@Column(name = "is_return_warehouse_")
	public boolean isIsReturnWarehouse() {
		return isReturnWarehouse;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "remarks", length = 100)
	public String getRemarks() {
		return remarks;
	}

	public String toString() {
		return "PartChangeRecord [id=" + id + ",partName=" + partName
				+ ",time=" + time + ",company=" + company
				+ ",maintenanceWorker=" + maintenanceWorker
				+ ",isReturnWarehouse=" + isReturnWarehouse + ",remarks="
				+ remarks + "]";
	}

}
