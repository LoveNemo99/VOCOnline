
package light.mvc.model.biz;

import java.io.Serializable;

import javax.persistence.*;
@Entity
@Table(name = "biz_sms_setting_and_receive")
public class TSmsSettingAndReceive implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int alarmSettingId;
	private int alarmSmsReceiveManId;

	public void setId(int id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public int getId() {
		return id;
	}

	@Column(name = "alarm_setting_id")
	public int getAlarmSettingId() {
		return alarmSettingId;
	}

	public void setAlarmSettingId(int alarmSettingId) {
		this.alarmSettingId = alarmSettingId;
	}

	@Column(name = "alarm_sms_receive_man_id")
	public int getAlarmSmsReceiveManId() {
		return alarmSmsReceiveManId;
	}

	public void setAlarmSmsReceiveManId(int alarmSmsReceiveManId) {
		this.alarmSmsReceiveManId = alarmSmsReceiveManId;
	}

	public TSmsSettingAndReceive() {
		super();
	}

	public TSmsSettingAndReceive(int id, int alarmSettingId,
			int alarmSmsReceiveManId) {
		super();
		this.id = id;
		this.alarmSettingId = alarmSettingId;
		this.alarmSmsReceiveManId = alarmSmsReceiveManId;
	}

	@Override
	public String toString() {
		return "TSmsSettingAndReceive [id=" + id + ", alarmSettingId="
				+ alarmSettingId + ", alarmSmsReceiveManId="
				+ alarmSmsReceiveManId + "]";
	}

}
