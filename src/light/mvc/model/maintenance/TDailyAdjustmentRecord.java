package light.mvc.model.maintenance;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * daily_adjustment_record:
 */
@Entity
@Table(name = "daily_adjustment_record")
public class TDailyAdjustmentRecord implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * id_:
	 */
	private int id;

	/**
	 * time_:
	 */
	private Date time;

	/**
	 * pollution_source_name_:
	 */
	private String pollutionSourceName;

	/**
	 * adjusting_device_:
	 */
	private String adjustingDevice;

	/**
	 * result_:
	 */
	private String result;

	/**
	 * maintenance_worker_name_:
	 */
	private String maintenanceWorkerName;

	/**
	 * remark_:
	 */
	private String remark;

	public TDailyAdjustmentRecord() {
		super();
	}

	public TDailyAdjustmentRecord(int id, Date time, String pollutionSourceName,
			String adjustingDevice, String result,
			String maintenanceWorkerName, String remark) {
		super();
		this.id = id;
		this.time = time;
		this.pollutionSourceName = pollutionSourceName;
		this.adjustingDevice = adjustingDevice;
		this.result = result;
		this.maintenanceWorkerName = maintenanceWorkerName;
		this.remark = remark;
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

	public void setTime(Date time) {
		this.time = time;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time_")
	public Date getTime() {
		return time;
	}

	public void setPollutionSourceName(String pollutionSourceName) {
		this.pollutionSourceName = pollutionSourceName;
	}

	@Column(name = "pollution_source_name_", length = 25)
	public String getPollutionSourceName() {
		return pollutionSourceName;
	}

	public void setAdjustingDevice(String adjustingDevice) {
		this.adjustingDevice = adjustingDevice;
	}

	@Column(name = "adjusting_device_", length = 100)
	public String getAdjustingDevice() {
		return adjustingDevice;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Column(name = "result_", length = 100)
	public String getResult() {
		return result;
	}

	public void setMaintenanceWorkerName(String maintenanceWorkerName) {
		this.maintenanceWorkerName = maintenanceWorkerName;
	}

	@Column(name = "maintenance_worker_name_", length = 20)
	public String getMaintenanceWorkerName() {
		return maintenanceWorkerName;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "remark_", length = 100)
	public String getRemark() {
		return remark;
	}

	public String toString() {
		return "DailyAdjustmentRecord [id=" + id + ",time=" + time
				+ ",pollutionSourceName=" + pollutionSourceName
				+ ",adjustingDevice=" + adjustingDevice + ",result=" + result
				+ ",maintenanceWorkerName=" + maintenanceWorkerName
				+ ",remark=" + remark + "]";
	}

}
