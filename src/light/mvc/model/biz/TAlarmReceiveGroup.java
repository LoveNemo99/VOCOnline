
package light.mvc.model.biz;

import java.io.Serializable;

import javax.persistence.*;
@Entity
@Table(name = "biz_alarm_receive_group")
public class TAlarmReceiveGroup implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String alarmReceiveGroupName;

	public void setId(int id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public int getId() {
		return id;
	}

	public TAlarmReceiveGroup() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TAlarmReceiveGroup(int id, String alarmReceiveGroupName) {
		super();
		this.id = id;
		this.alarmReceiveGroupName = alarmReceiveGroupName;
	}

	@Column(name = "alarm_receive_group_name")
	public String getAlarmReceiveGroupName() {
		return alarmReceiveGroupName;
	}

	public void setAlarmReceiveGroupName(String alarmReceiveGroupName) {
		this.alarmReceiveGroupName = alarmReceiveGroupName;
	}

	@Override
	public String toString() {
		return "TAlarmReceiveGroup [id=" + id + ", alarmReceiveGroupName="
				+ alarmReceiveGroupName + "]";
	}


}
