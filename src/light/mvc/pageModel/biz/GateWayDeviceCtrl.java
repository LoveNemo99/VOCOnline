package light.mvc.pageModel.biz;

import java.io.Serializable;
import java.util.Date;

public class GateWayDeviceCtrl implements Serializable {
	private static final long serialVersionUID = 1720318347823383539L;
	private Integer id;
	private String mn,result,remark,qn,qnRtn,exeRtn;
	private String ctrlCode;
	private String psName,psCode;
	private Date executeTime;
	private Double param;
	private String cardNum,cardType;
	private Date startTime;
	private Date endTime;
	private String pwd;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMn() {
		return mn;
	}
	public void setMn(String mn) {
		this.mn = mn;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getQn() {
		return qn;
	}
	public void setQn(String qn) {
		this.qn = qn;
	}
	public String getQnRtn() {
		return qnRtn;
	}
	public void setQnRtn(String qnRtn) {
		this.qnRtn = qnRtn;
	}
	public String getExeRtn() {
		return exeRtn;
	}
	public void setExeRtn(String exeRtn) {
		this.exeRtn = exeRtn;
	}
	public String getCtrlCode() {
		return ctrlCode;
	}
	public void setCtrlCode(String ctrlCode) {
		this.ctrlCode = ctrlCode;
	}
	public String getPsName() {
		return psName;
	}
	public void setPsName(String psName) {
		this.psName = psName;
	}
	public String getPsCode() {
		return psCode;
	}
	public void setPsCode(String psCode) {
		this.psCode = psCode;
	}
	public Date getExecuteTime() {
		return executeTime;
	}
	public void setExecuteTime(Date executeTime) {
		this.executeTime = executeTime;
	}
	public Double getParam() {
		return param;
	}
	public void setParam(Double param) {
		this.param = param;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
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
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Override
	public String toString() {
		return "GateWayDeviceCtrl [id=" + id + ", mn=" + mn + ", result="
				+ result + ", remark=" + remark + ", qn=" + qn + ", qnRtn="
				+ qnRtn + ", exeRtn=" + exeRtn + ", ctrlCode=" + ctrlCode
				+ ", psName=" + psName + ", psCode=" + psCode
				+ ", executeTime=" + executeTime + ", param=" + param
				+ ", cardNum=" + cardNum + ", cardType=" + cardType
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", pwd=" + pwd + "]";
	}

}
