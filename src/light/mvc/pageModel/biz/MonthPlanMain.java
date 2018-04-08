package light.mvc.pageModel.biz;

import java.io.Serializable;
import java.util.Date;

public class MonthPlanMain implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String psCode;
	private String piCode;
	private String month;
    private Boolean isSynchronize;
    private String applyMan;
    private Date applyTime;
    private String approveMan;
    private Date approveTime;
    private String approve;
    private String submit;
    private String cause;
    private String code;
	private Float allow;
	private String startDateStr;
	private String endDateStr;
	private Date startDate;
	private Date endDate;

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getIsSynchronize() {
		return isSynchronize;
	}

	public void setIsSynchronize(Boolean isSynchronize) {
		this.isSynchronize = isSynchronize;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Float getAllow() {
		return allow;
	}

	public void setAllow(Float allow) {
		this.allow = allow;
	}

	public String getPsCode() {
		return psCode;
	}

	public void setPsCode(String psCode) {
		this.psCode = psCode;
	}

	public String getPiCode() {
		return piCode;
	}

	public void setPiCode(String piCode) {
		this.piCode = piCode;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getApplyMan() {
		return applyMan;
	}

	public void setApplyMan(String applyMan) {
		this.applyMan = applyMan;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public String getApproveMan() {
		return approveMan;
	}

	public void setApproveMan(String approveMan) {
		this.approveMan = approveMan;
	}

	public Date getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}

	public String getApprove() {
		return approve;
	}

	public void setApprove(String approve) {
		this.approve = approve;
	}

	public String getSubmit() {
		return submit;
	}

	public void setSubmit(String submit) {
		this.submit = submit;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getStartDateStr() {
		return startDateStr;
	}

	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}

	public String getEndDateStr() {
		return endDateStr;
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "MonthPlan [id=" + id + ", psCode=" + psCode + ", piCode="
				+ piCode + ", month=" + month + ", isSynchronize="
				+ isSynchronize + ", applyMan=" + applyMan + ", applyTime="
				+ applyTime + ", approveMan=" + approveMan + ", approveTime="
				+ approveTime + ", approve=" + approve + ", submit=" + submit
				+ ", cause=" + cause + ", code=" + code + ", allow=" + allow
				+ ", startDateStr=" + startDateStr + ", endDateStr="
				+ endDateStr + ", startDate=" + startDate + ", endDate="
				+ endDate + "]";
	}
	
}
