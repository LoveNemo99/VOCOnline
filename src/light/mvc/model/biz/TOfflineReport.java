package light.mvc.model.biz;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "biz_offline_report")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TOfflineReport implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String psCode;
	private String portCode;
	private Integer offlineTimes;
	private Float offlineDuration;
	private Date time;
	private String currentState;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "ps_code")
	public String getPsCode() {
		return psCode;
	}

	public void setPsCode(String psCode) {
		this.psCode = psCode;
	}
	
	@Column(name = "port_code")
	public String getPortCode() {
		return portCode;
	}

	public void setPortCode(String portCode) {
		this.portCode = portCode;
	}

	@Column(name = "offline_times")
	public Integer getOfflineTimes() {
		return offlineTimes;
	}

	public void setOfflineTimes(Integer offlineTimes) {
		this.offlineTimes = offlineTimes;
	}

	@Column(name = "offline_duration")
	public Float getOfflineDuration() {
		return offlineDuration;
	}

	public void setOfflineDuration(Float offlineDuration) {
		this.offlineDuration = offlineDuration;
	}

	@Column(name = "time")
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Column(name = "current_state")
	public String getCurrentState() {
		return currentState;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

}
