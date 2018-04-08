package light.mvc.pageModel.biz;

import java.io.Serializable;
import java.util.Date;

public class OfflineReport implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String psCode;
	private String portCode;
	private Integer offlineTimes;
	private Float offlineDuration;
	private Date time;
	private String currentState;
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
	public Integer getOfflineTimes() {
		return offlineTimes;
	}
	public void setOfflineTimes(Integer offlineTimes) {
		this.offlineTimes = offlineTimes;
	}
	public Float getOfflineDuration() {
		return offlineDuration;
	}
	public void setOfflineDuration(Float offlineDuration) {
		this.offlineDuration = offlineDuration;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getCurrentState() {
		return currentState;
	}
	public void setCurrentState(String currentState) {
		this.currentState = currentState;
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
	
}
