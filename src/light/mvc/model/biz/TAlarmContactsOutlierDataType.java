package light.mvc.model.biz;

import java.io.Serializable;

import javax.persistence.*;
@Entity
@Table(name = "biz_alarm_contacts_outlier_data_type")
public class TAlarmContactsOutlierDataType implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int alarmManId;
	private int alarmOutlierDataTypeId;

	public void setId(int id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public int getId() {
		return id;
	}

	public TAlarmContactsOutlierDataType() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Column(name = "alarm_man_id")
	public int getAlarmManId() {
		return alarmManId;
	}

	public void setAlarmManId(int alarmManId) {
		this.alarmManId = alarmManId;
	}

	@Column(name = "alarm_outlier_data_type_id")
	public int getAlarmOutlierDataTypeId() {
		return alarmOutlierDataTypeId;
	}

	public void setAlarmOutlierDataTypeId(int alarmOutlierDataTypeId) {
		this.alarmOutlierDataTypeId = alarmOutlierDataTypeId;
	}

	public TAlarmContactsOutlierDataType(int id, int alarmManId,
			int alarmOutlierDataTypeId) {
		super();
		this.id = id;
		this.alarmManId = alarmManId;
		this.alarmOutlierDataTypeId = alarmOutlierDataTypeId;
	}

	@Override
	public String toString() {
		return "TAlarmContactsOutlierDataType [id=" + id + ", alarmManId="
				+ alarmManId + ", alarmOutlierDataTypeId="
				+ alarmOutlierDataTypeId + "]";
	}


}