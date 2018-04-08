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
@Table(name="gateway_sample_take")
public class TGateWaySampleTake implements Serializable {
	private static final long serialVersionUID = 1720318347823383539L;
	private Integer id,pollutantSourceId;
	private String MN,result,remark,QN,QNRtn,exeRtn;
	private String ctrlCode;
	private String pollutantSourceName,pollutantSourceAlias;
	private Date executionTime;
	private Double param;
	private String pollutantCode;
	private Integer bottleId;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	@Column(name="ps_id")
	public Integer getPollutantSourceId() {
		return pollutantSourceId;
	}
	@Column(name="mn")
	public String getMN() {
		return MN;
	}
	@Column(name="result")
	public String getResult() {
		return result;
	}
	@Column(name="ctrl_code")
	public String getCtrlCode() {
		return ctrlCode;
	}
	@Column(name="remark")
	public String getRemark() {
		return remark;
	}
	@Column(name="qn")
	public String getQN() {
		return QN;
	}
	@Column(name="qn_rtn")
	public String getQNRtn() {
		return QNRtn;
	}
	@Column(name="exe_rtn")
	public String getExeRtn() {
		return exeRtn;
	}
	@Column(name="execute_time")
	public Date getExecutionTime() {
		return executionTime;
	}
	@Column(name="param")
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

	@Column(name="ps_name")
	public String getPollutantSourceName() {
		return pollutantSourceName;
	}
	public void setPollutantSourceName(String pollutantSourceName) {
		this.pollutantSourceName = pollutantSourceName;
	}
	@Column(name="ps_alias")
	public String getPollutantSourceAlias() {
		return pollutantSourceAlias;
	}
	public void setPollutantSourceAlias(String pollutantSourceAlias) {
		this.pollutantSourceAlias = pollutantSourceAlias;
	}
	@Column(name="pollutant_code")
	public String getPollutantCode() {
		return pollutantCode;
	}
	public void setPollutantCode(String pollutantCode) {
		this.pollutantCode = pollutantCode;
	}
	@Column(name="bottle_id")
	public Integer getBottleId() {
		return bottleId;
	}
	public void setBottleId(Integer bottleId) {
		this.bottleId = bottleId;
	}
}
