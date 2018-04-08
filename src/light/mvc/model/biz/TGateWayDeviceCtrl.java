package light.mvc.model.biz;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="gateway_device_ctrl")
public class TGateWayDeviceCtrl implements Serializable {
	private static final long serialVersionUID = 1720318347823383539L;
	private Integer id;
	private String mn,result,remark,qn,qnRtn,exeRtn;
	private String ctrlCode;
	private String psName,psCode;
	private Date executeTime;
	private Double param;
	private String cardNum,cardType;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="mn")
	public String getMn() {
		return mn;
	}
	public void setMn(String mn) {
		this.mn = mn;
	}
	@Column(name="result")
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Column(name="remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name="qn")
	public String getQn() {
		return qn;
	}
	public void setQn(String qn) {
		this.qn = qn;
	}
	@Column(name="qn_rtn")
	public String getQnRtn() {
		return qnRtn;
	}
	public void setQnRtn(String qnRtn) {
		this.qnRtn = qnRtn;
	}
	@Column(name="exe_rtn")
	public String getExeRtn() {
		return exeRtn;
	}
	public void setExeRtn(String exeRtn) {
		this.exeRtn = exeRtn;
	}
	@Column(name="ctrl_code")
	public String getCtrlCode() {
		return ctrlCode;
	}
	public void setCtrlCode(String ctrlCode) {
		this.ctrlCode = ctrlCode;
	}
	@Column(name="ps_name")
	public String getPsName() {
		return psName;
	}
	public void setPsName(String psName) {
		this.psName = psName;
	}
	@Column(name="ps_code")
	public String getPsCode() {
		return psCode;
	}
	public void setPsCode(String psCode) {
		this.psCode = psCode;
	}
	@Column(name="execute_time")
	public Date getExecuteTime() {
		return executeTime;
	}
	public void setExecuteTime(Date executeTime) {
		this.executeTime = executeTime;
	}
	@Column(name="param")
	public Double getParam() {
		return param;
	}
	public void setParam(Double param) {
		this.param = param;
	}
	@Column(name="card_num")
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	@Column(name="card_type")
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	
}
