
package light.mvc.pageModel.biz;

import java.io.Serializable;
public class AlarmMode implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String alarmModeName;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public String getAlarmModeName() {
		return alarmModeName;
	}

	public void setAlarmModeName(String alarmModeName) {
		this.alarmModeName = alarmModeName;
	}

	@Override
	public String toString() {
		return "AlarmMode [id=" + id + ", alarmModeName=" + alarmModeName + "]";
	}


}
