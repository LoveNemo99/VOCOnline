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
@Table(name = "biz_ultra_standard_rate")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TUltraStandardRate implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String psCode;
	private String portCode;
	private Integer monitorTimes;
	private Integer overTimes;
	private Date time;
	
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

	@Column(name = "time")
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Column(name = "monitor_times")
	public Integer getMonitorTimes() {
		return monitorTimes;
	}

	public void setMonitorTimes(Integer monitorTimes) {
		this.monitorTimes = monitorTimes;
	}

	@Column(name = "over_times")
	public Integer getOverTimes() {
		return overTimes;
	}

	public void setOverTimes(Integer overTimes) {
		this.overTimes = overTimes;
	}

}
