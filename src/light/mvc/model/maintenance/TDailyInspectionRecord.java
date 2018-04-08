package light.mvc.model.maintenance;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * daily_inspection_record:
 */
@Entity
@Table(name = "daily_inspection_record")
public class TDailyInspectionRecord implements Serializable {

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
	 * inspection_content_:
	 */
	private String inspectionContent;

	/**
	 * maintenance_worker_name_:
	 */
	private String maintenanceWorkerName;

	/**
	 * problem_:
	 */
	private String problem;

	/**
	 * remark_:
	 */
	private String remark;

	public TDailyInspectionRecord() {
		super();
	}

	public TDailyInspectionRecord(int id, Date time, String pollutionSourceName,
			String inspectionContent, String maintenanceWorkerName,
			String problem, String remark) {
		super();
		this.id = id;
		this.time = time;
		this.pollutionSourceName = pollutionSourceName;
		this.inspectionContent = inspectionContent;
		this.maintenanceWorkerName = maintenanceWorkerName;
		this.problem = problem;
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

	public void setInspectionContent(String inspectionContent) {
		this.inspectionContent = inspectionContent;
	}

	@Column(name = "inspection_content_", length = 100)
	public String getInspectionContent() {
		return inspectionContent;
	}

	public void setMaintenanceWorkerName(String maintenanceWorkerName) {
		this.maintenanceWorkerName = maintenanceWorkerName;
	}

	@Column(name = "maintenance_worker_name_", length = 20)
	public String getMaintenanceWorkerName() {
		return maintenanceWorkerName;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	@Column(name = "problem_", length = 100)
	public String getProblem() {
		return problem;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "remark_", length = 100)
	public String getRemark() {
		return remark;
	}

	public String toString() {
		return "DailyInspectionRecord [id=" + id + ",time=" + time
				+ ",pollutionSourceName=" + pollutionSourceName
				+ ",inspectionContent=" + inspectionContent
				+ ",maintenanceWorkerName=" + maintenanceWorkerName
				+ ",problem=" + problem + ",remark=" + remark + "]";
	}

}
