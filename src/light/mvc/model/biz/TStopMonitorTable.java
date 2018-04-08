
package light.mvc.model.biz;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "biz_stop_monitor_table")
public class TStopMonitorTable implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int psBaseId;
	private int psPiId;
	private Date stopMonth;
	private int stopMonitorSettingId;
	private String alarmContent;

	public void setId(int id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public int getId() {
		return id;
	}

	@Column(name = "ps_base_id")
	public int getPsBaseId() {
		return psBaseId;
	}

	public void setPsBaseId(int psBaseId) {
		this.psBaseId = psBaseId;
	}

	@Column(name = "ps_pi_id")
	public int getPsPiId() {
		return psPiId;
	}

	public void setPsPiId(int psPiId) {
		this.psPiId = psPiId;
	}

	@Column(name = "stop_month")
	public Date getStopMonth() {
		return stopMonth;
	}

	public void setStopMonth(Date stopMonth) {
		this.stopMonth = stopMonth;
	}

	@Column(name = "stop_monitor_setting_id")
	public int getStopMonitorSettingId() {
		return stopMonitorSettingId;
	}

	public void setStopMonitorSettingId(int stopMonitorSettingId) {
		this.stopMonitorSettingId = stopMonitorSettingId;
	}

	@Column(name = "alarm_content")
	public String getAlarmContent() {
		return alarmContent;
	}

	public void setAlarmContent(String alarmContent) {
		this.alarmContent = alarmContent;
	}

	public TStopMonitorTable() {
		super();
	}

	public TStopMonitorTable(int id, int psBaseId, int psPiId, Date stopMonth,
			int stopMonitorSettingId, String alarmContent) {
		super();
		this.id = id;
		this.psBaseId = psBaseId;
		this.psPiId = psPiId;
		this.stopMonth = stopMonth;
		this.stopMonitorSettingId = stopMonitorSettingId;
		this.alarmContent = alarmContent;
	}

	@Override
	public String toString() {
		return "TStopMonitorTable [id=" + id + ", psBaseId=" + psBaseId
				+ ", psPiId=" + psPiId + ", stopMonth=" + stopMonth
				+ ", stopMonitorSettingId=" + stopMonitorSettingId
				+ ", alarmContent=" + alarmContent + "]";
	}

}
