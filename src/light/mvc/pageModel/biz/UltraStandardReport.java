package light.mvc.pageModel.biz;

import java.io.Serializable;
import java.util.Date;

public class UltraStandardReport implements Serializable {
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
	private Date creatDateTimeStart;
	private Date creatDateTimeEnd;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPsCode() {
		return psCode;
	}
	public void setPsCode(String psCode) {
		this.psCode = psCode;
	}
	public String getPortCode() {
		return portCode;
	}
	public void setPortCode(String portCode) {
		this.portCode = portCode;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Date getCreatDateTimeStart() {
		return creatDateTimeStart;
	}
	public void setCreatDateTimeStart(Date creatDateTimeStart) {
		this.creatDateTimeStart = creatDateTimeStart;
	}
	public Date getCreatDateTimeEnd() {
		return creatDateTimeEnd;
	}
	public void setCreatDateTimeEnd(Date creatDateTimeEnd) {
		this.creatDateTimeEnd = creatDateTimeEnd;
	}
	public String getFactorCode() {
		return factorCode;
	}
	public void setFactorCode(String factorCode) {
		this.factorCode = factorCode;
	}
	public String getStandardValue() {
		return standardValue;
	}
	public void setStandardValue(String standardValue) {
		this.standardValue = standardValue;
	}
	public String getMonitorValue() {
		return monitorValue;
	}
	public void setMonitorValue(String monitorValue) {
		this.monitorValue = monitorValue;
	}
	public String getUltraStandardTimes() {
		return ultraStandardTimes;
	}
	public void setUltraStandardTimes(String ultraStandardTimes) {
		this.ultraStandardTimes = ultraStandardTimes;
	}
	public String getUltraStandardHours() {
		return ultraStandardHours;
	}
	public void setUltraStandardHours(String ultraStandardHours) {
		this.ultraStandardHours = ultraStandardHours;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
}
