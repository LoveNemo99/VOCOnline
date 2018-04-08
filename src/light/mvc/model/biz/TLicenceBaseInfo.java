package light.mvc.model.biz;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
@Entity
@Table(name = "biz_licence_base_info")
public class TLicenceBaseInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String psCode;
	private String piCode;
	private String licenceNum;
	private Date startDate;
	private Date endDate;
    private String department;
    private String qn;
    private Boolean isSynchronize;

	public void setId(Integer id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public Integer getId() {
		return id;
	}

	@Column(name = "licence_num")
	public String getLicenceNum() {
		return licenceNum;
	}

	public void setLicenceNum(String licenceNum) {
		this.licenceNum = licenceNum;
	}

	@Column(name = "start_date")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "end_date")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "department")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Column(name = "qn")
	public String getQn() {
		return qn;
	}

	public void setQn(String qn) {
		this.qn = qn;
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

	@Override
	public String toString() {
		return "TLicenceBaseInfo [id=" + id + ", psCode=" + psCode
				+ ", piCode=" + piCode + ", licenceNum=" + licenceNum
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", department=" + department + ", qn=" + qn
				+ ", isSynchronize=" + isSynchronize + "]";
	}
	
}
