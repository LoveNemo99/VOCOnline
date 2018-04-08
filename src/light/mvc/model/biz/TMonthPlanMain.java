package light.mvc.model.biz;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
@Entity
@Table(name = "biz_month_plan_main")
public class TMonthPlanMain implements Serializable {

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

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "is_synchronize")
	public Boolean getIsSynchronize() {
		return isSynchronize;
	}

	public void setIsSynchronize(Boolean isSynchronize) {
		this.isSynchronize = isSynchronize;
	}

	@Column(name = "ps_code")
	public String getPsCode() {
		return psCode;
	}

	public void setPsCode(String psCode) {
		this.psCode = psCode;
	}

	@Column(name = "pi_code")
	public String getPiCode() {
		return piCode;
	}

	public void setPiCode(String piCode) {
		this.piCode = piCode;
	}

	@Column(name = "month")
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	@Column(name = "apply_man")
	public String getApplyMan() {
		return applyMan;
	}

	public void setApplyMan(String applyMan) {
		this.applyMan = applyMan;
	}

	@Column(name = "apply_time")
	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	@Column(name = "approve_man")
	public String getApproveMan() {
		return approveMan;
	}

	public void setApproveMan(String approveMan) {
		this.approveMan = approveMan;
	}

	@Column(name = "approve_time")
	public Date getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}

	@Column(name = "approve")
	public String getApprove() {
		return approve;
	}

	public void setApprove(String approve) {
		this.approve = approve;
	}

	@Column(name = "submit")
	public String getSubmit() {
		return submit;
	}

	public void setSubmit(String submit) {
		this.submit = submit;
	}

	@Column(name = "cause")
	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	@Override
	public String toString() {
		return "TMonthPlanMain [id=" + id + ", psCode=" + psCode + ", piCode="
				+ piCode + ", month=" + month + ", isSynchronize="
				+ isSynchronize + ", applyMan=" + applyMan + ", applyTime="
				+ applyTime + ", approveMan=" + approveMan + ", approveTime="
				+ approveTime + ", approve=" + approve + ", submit=" + submit
				+ ", cause=" + cause + "]";
	}
	
}
