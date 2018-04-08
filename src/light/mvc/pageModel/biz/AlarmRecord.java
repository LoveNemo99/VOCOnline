package light.mvc.pageModel.biz;

import java.util.Date;

public class AlarmRecord implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String psName;
	private String text;
	private String type;
	private Date time;
	private String alarmMan;
	private String tel;
	private String remark;
	private Boolean isSend;
	private Date creatDateTimeStart;
	private Date creatDateTimeEnd;
	private String portName;
	private String state;
	private String operation;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPsName() {
		return psName;
	}
	public void setPsName(String psName) {
		this.psName = psName;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getAlarmMan() {
		return alarmMan;
	}
	public void setAlarmMan(String alarmMan) {
		this.alarmMan = alarmMan;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Boolean getIsSend() {
		return isSend;
	}
	public void setIsSend(Boolean isSend) {
		this.isSend = isSend;
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
	
	public String getPortName() {
		return portName;
	}
	public void setPortName(String portName) {
		this.portName = portName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	@Override
	public String toString() {
		return "AlarmRecord [id=" + id + ", psName=" + psName + ", text="
				+ text + ", type=" + type + ", time=" + time + ", alarmMan="
				+ alarmMan + ", tel=" + tel + ", remark=" + remark
				+ ", isSend=" + isSend + ", creatDateTimeStart="
				+ creatDateTimeStart + ", creatDateTimeEnd=" + creatDateTimeEnd
				+ "]";
	}
	
}
