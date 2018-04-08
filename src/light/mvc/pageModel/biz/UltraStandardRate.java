package light.mvc.pageModel.biz;

import java.io.Serializable;
import java.util.Date;

public class UltraStandardRate implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String psCode;
	private String portCode;
	private Integer monitorTimes;
	private Integer overTimes;
	private Date time;
	private Float rate;
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
	public Integer getMonitorTimes() {
		return monitorTimes;
	}
	public void setMonitorTimes(Integer monitorTimes) {
		this.monitorTimes = monitorTimes;
	}
	public Integer getOverTimes() {
		return overTimes;
	}
	public void setOverTimes(Integer overTimes) {
		this.overTimes = overTimes;
	}
	public Float getRate() {
		return rate;
	}
	public void setRate(Float rate) {
		this.rate = rate;
	}
	
}
