
package light.mvc.model.biz;

import java.io.Serializable;

import javax.persistence.*;
@Entity
@Table(name = "biz_alarm_mode")
public class TAlarmMode implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String alarmModeName;

	public void setId(Integer id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public Integer getId() {
		return id;
	}

	public TAlarmMode() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Column(name = "alarm_mode_name")
	public String getAlarmModeName() {
		return alarmModeName;
	}

	public void setAlarmModeName(String alarmModeName) {
		this.alarmModeName = alarmModeName;
	}

	public TAlarmMode(Integer id, String alarmModeName) {
		super();
		this.id = id;
		this.alarmModeName = alarmModeName;
	}

	@Override
	public String toString() {
		return "TAlarmMode [id=" + id + ", alarmModeName=" + alarmModeName
				+ "]";
	}


}
