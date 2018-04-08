package light.mvc.model.biz;

import java.io.Serializable;

import javax.persistence.*;
@Entity
@Table(name = "biz_alarm_contact")
public class TAlarmSmsReceiveMan implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int alarmId;
	private String name;
	private String tel;

	public void setId(int id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public int getId() {
		return id;
	}

	@Column(name = "alarm_id")
	public int getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(int alarmId) {
		this.alarmId = alarmId;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "tel")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public TAlarmSmsReceiveMan() {
		super();
	}

	public TAlarmSmsReceiveMan(int id, int alarmId, String name, String tel) {
		super();
		this.id = id;
		this.alarmId = alarmId;
		this.name = name;
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "TAlarmSmsReceiveMan [id=" + id + ", alarmId=" + alarmId
				+ ", name=" + name + ", tel=" + tel + "]";
	}

}
