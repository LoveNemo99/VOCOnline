
package light.mvc.model.biz;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
@Entity
@Table(name = "biz_routing_inspection")
public class TRoutingInspection implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String psCode;
	private Date createTime;
	private Date modifyTime;
	private String createMan;
	private String modifyMan;
	private String valveState;
	private String networkState;
	private String systemState;
	private String otherState;
	private String yearMonth;
	private String problemDescribe;

	public void setId(Integer id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public Integer getId() {
		return id;
	}

	@Column(name = "ps_code")
	public String getPsCode() {
		return psCode;
	}

	public void setPsCode(String psCode) {
		this.psCode = psCode;
	}

	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "modify_time")
	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Column(name = "create_man")
	public String getCreateMan() {
		return createMan;
	}

	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}

	@Column(name = "modify_man")
	public String getModifyMan() {
		return modifyMan;
	}

	public void setModifyMan(String modifyMan) {
		this.modifyMan = modifyMan;
	}

	@Column(name = "valve_state")
	public String getValveState() {
		return valveState;
	}

	public void setValveState(String valveState) {
		this.valveState = valveState;
	}

	@Column(name = "network_state")
	public String getNetworkState() {
		return networkState;
	}

	public void setNetworkState(String networkState) {
		this.networkState = networkState;
	}

	@Column(name = "system_state")
	public String getSystemState() {
		return systemState;
	}

	public void setSystemState(String systemState) {
		this.systemState = systemState;
	}

	@Column(name = "other_state")
	public String getOtherState() {
		return otherState;
	}

	public void setOtherState(String otherState) {
		this.otherState = otherState;
	}

	@Column(name = "month")
	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}

	@Column(name = "problem_describe")
	public String getProblemDescribe() {
		return problemDescribe;
	}

	public void setProblemDescribe(String problemDescribe) {
		this.problemDescribe = problemDescribe;
	}

	@Override
	public String toString() {
		return "TRoutingInspection [id=" + id + ", psCode=" + psCode
				+ ", createTime=" + createTime + ", modifyTime=" + modifyTime
				+ ", createMan=" + createMan + ", modifyMan=" + modifyMan
				+ ", valveState=" + valveState + ", networkState="
				+ networkState + ", systemState=" + systemState
				+ ", otherState=" + otherState + ", yearMonth=" + yearMonth
				+ ", problemDescribe=" + problemDescribe + "]";
	}
	
}
