package light.mvc.model.biz;

import java.io.Serializable;

import javax.persistence.*;
@Entity
@Table(name = "biz_alarm_contact_ps_pi")
public class TAlarmContactPsPi implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int alarmContactId;
	private int psPiId;

	public void setId(int id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public int getId() {
		return id;
	}

	@Column(name = "alarm_contact_id")
	public int getAlarmContactId() {
		return alarmContactId;
	}

	public void setAlarmContactId(int alarmContactId) {
		this.alarmContactId = alarmContactId;
	}

	@Column(name = "ps_pi_id")
	public int getPsPiId() {
		return psPiId;
	}

	public void setPsPiId(int psPiId) {
		this.psPiId = psPiId;
	}

	public TAlarmContactPsPi() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TAlarmContactPsPi(int id, int alarmContactId, int psPiId) {
		super();
		this.id = id;
		this.alarmContactId = alarmContactId;
		this.psPiId = psPiId;
	}

	@Override
	public String toString() {
		return "TAlarmContactPsPi [id=" + id + ", alarmContactId="
				+ alarmContactId + ", psPiId=" + psPiId + "]";
	}

}
