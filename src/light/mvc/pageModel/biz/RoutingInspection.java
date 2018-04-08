
package light.mvc.pageModel.biz;

import java.io.Serializable;
import java.util.Date;
public class RoutingInspection implements Serializable {

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getCreateMan() {
		return createMan;
	}

	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}

	public String getModifyMan() {
		return modifyMan;
	}

	public void setModifyMan(String modifyMan) {
		this.modifyMan = modifyMan;
	}

	public String getValveState() {
		return valveState;
	}

	public void setValveState(String valveState) {
		this.valveState = valveState;
	}

	public String getNetworkState() {
		return networkState;
	}

	public void setNetworkState(String networkState) {
		this.networkState = networkState;
	}

	public String getSystemState() {
		return systemState;
	}

	public void setSystemState(String systemState) {
		this.systemState = systemState;
	}

	public String getOtherState() {
		return otherState;
	}

	public void setOtherState(String otherState) {
		this.otherState = otherState;
	}

	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}

	public String getProblemDescribe() {
		return problemDescribe;
	}

	public void setProblemDescribe(String problemDescribe) {
		this.problemDescribe = problemDescribe;
	}

	@Override
	public String toString() {
		return "RoutingInspection [id=" + id + ", psCode=" + psCode
				+ ", createTime=" + createTime + ", modifyTime=" + modifyTime
				+ ", createMan=" + createMan + ", modifyMan=" + modifyMan
				+ ", valveState=" + valveState + ", networkState="
				+ networkState + ", systemState=" + systemState
				+ ", otherState=" + otherState + ", yearMonth=" + yearMonth
				+ ", problemDescribe=" + problemDescribe + "]";
	}
	
}
