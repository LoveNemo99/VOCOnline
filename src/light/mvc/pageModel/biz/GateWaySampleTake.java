package light.mvc.pageModel.biz;

import java.io.Serializable;
import java.util.Date;

public class GateWaySampleTake implements Serializable {
	private static final long serialVersionUID = 1720318347823383539L;
	private Integer id,pollutantSourceId;
	private String MN,result,remark,QN,QNRtn,exeRtn;
	private String ctrlCode;
	private String pollutantSourceName,pollutantSourceAlias;
	private Date executionTime;
	private Double param;
	private String pollutantCode;
	private Integer bottleId;
	private Date startTime;
	private Date endTime;
	private String pwd;

	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Integer getId() {
		return id;
	}
	public Integer getPollutantSourceId() {
		return pollutantSourceId;
	}
	public String getMN() {
		return MN;
	}
	public String getResult() {
		return result;
	}
	public String getCtrlCode() {
		return ctrlCode;
	}
	public String getRemark() {
		return remark;
	}
	public String getQN() {
		return QN;
	}
	public String getQNRtn() {
		return QNRtn;
	}
	public String getExeRtn() {
		return exeRtn;
	}
	public Date getExecutionTime() {
		return executionTime;
	}
	public Double getParam() {
		return param;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setPollutantSourceId(Integer pollutantSourceId) {
		this.pollutantSourceId = pollutantSourceId;
	}
	public void setMN(String mN) {
		MN = mN;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public void setCtrlCode(String ctrlCode) {
		this.ctrlCode = ctrlCode;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setQN(String qN) {
		QN = qN;
	}
	public void setQNRtn(String qNRtn) {
		QNRtn = qNRtn;
	}
	public void setExeRtn(String exeRtn) {
		this.exeRtn = exeRtn;
	}
	public void setExecutionTime(Date executionTime) {
		this.executionTime = executionTime;
	}
	public void setParam(Double param) {
		this.param = param;
	}

	public String getPollutantSourceName() {
		return pollutantSourceName;
	}
	public void setPollutantSourceName(String pollutantSourceName) {
		this.pollutantSourceName = pollutantSourceName;
	}
	public String getPollutantSourceAlias() {
		return pollutantSourceAlias;
	}
	public void setPollutantSourceAlias(String pollutantSourceAlias) {
		this.pollutantSourceAlias = pollutantSourceAlias;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getPollutantCode() {
		return pollutantCode;
	}
	public void setPollutantCode(String pollutantCode) {
		this.pollutantCode = pollutantCode;
	}
	public Integer getBottleId() {
		return bottleId;
	}
	public void setBottleId(Integer bottleId) {
		this.bottleId = bottleId;
	}
	@Override
	public String toString() {
		return "GateWaySampleTake [id=" + id + ", pollutantSourceId="
				+ pollutantSourceId + ", MN=" + MN + ", result=" + result
				+ ", remark=" + remark + ", QN=" + QN + ", QNRtn=" + QNRtn
				+ ", exeRtn=" + exeRtn + ", ctrlCode=" + ctrlCode
				+ ", pollutantSourceName=" + pollutantSourceName
				+ ", pollutantSourceAlias=" + pollutantSourceAlias
				+ ", executionTime=" + executionTime + ", param=" + param
				+ ", pollutantCode=" + pollutantCode + ", bottleId=" + bottleId
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", pwd=" + pwd + "]";
	}
	
}
