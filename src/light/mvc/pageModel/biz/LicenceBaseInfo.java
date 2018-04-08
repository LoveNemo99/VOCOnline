package light.mvc.pageModel.biz;

import java.io.Serializable;
import java.util.Date;

public class LicenceBaseInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String psCode;
	private String piCode;
	private String licenceNum;
	private Date startDate;
	private Date endDate;
	private String startDateStr;
	private String endDateStr;
    private String department;
    private String qn;
    private Boolean isSynchronize;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public String getLicenceNum() {
		return licenceNum;
	}

	public void setLicenceNum(String licenceNum) {
		this.licenceNum = licenceNum;
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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getQn() {
		return qn;
	}

	public void setQn(String qn) {
		this.qn = qn;
	}

	public Boolean getIsSynchronize() {
		return isSynchronize;
	}

	public void setIsSynchronize(Boolean isSynchronize) {
		this.isSynchronize = isSynchronize;
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

	@Override
	public String toString() {
		return "LicenceBaseInfo [id=" + id + ", psCode=" + psCode + ", piCode="
				+ piCode + ", licenceNum=" + licenceNum + ", startDate="
				+ startDate + ", endDate=" + endDate + ", startDateStr="
				+ startDateStr + ", endDateStr=" + endDateStr + ", department="
				+ department + ", qn=" + qn + ", isSynchronize="
				+ isSynchronize + "]";
	}
	
}
