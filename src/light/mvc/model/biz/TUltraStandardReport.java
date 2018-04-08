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
@Table(name = "biz_ultra_standard_report")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TUltraStandardReport implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String psCode;
	private String portCode;
	private String factorCode;
	private String standardValue;
	private String monitorValue;
	private String ultraStandardTimes;
	private String ultraStandardHours;
	private String operation;
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

	@Column(name = "factor_code")
	public String getFactorCode() {
		return factorCode;
	}

	public void setFactorCode(String factorCode) {
		this.factorCode = factorCode;
	}

	@Column(name = "standard_value")
	public String getStandardValue() {
		return standardValue;
	}

	public void setStandardValue(String standardValue) {
		this.standardValue = standardValue;
	}

	@Column(name = "monitor_value")
	public String getMonitorValue() {
		return monitorValue;
	}

	public void setMonitorValue(String monitorValue) {
		this.monitorValue = monitorValue;
	}

	@Column(name = "ultra_standard_times")
	public String getUltraStandardTimes() {
		return ultraStandardTimes;
	}

	public void setUltraStandardTimes(String ultraStandardTimes) {
		this.ultraStandardTimes = ultraStandardTimes;
	}

	@Column(name = "ultra_standard_hours")
	public String getUltraStandardHours() {
		return ultraStandardHours;
	}

	public void setUltraStandardHours(String ultraStandardHours) {
		this.ultraStandardHours = ultraStandardHours;
	}

	@Column(name = "operation")
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

}
