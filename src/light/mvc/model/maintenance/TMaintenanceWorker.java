package light.mvc.model.maintenance;

import java.io.Serializable;
import javax.persistence.*;

/**
 * maintenance_worker:
 */
@Entity
@Table(name = "maintenance_worker")
public class TMaintenanceWorker implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * id_:
	 */
	private int id;

	/**
	 * code_:
	 */
	private String code;

	/**
	 * name_:
	 */
	private String name;

	/**
	 * sex_:
	 */
	private String sex;

	/**
	 * education_:
	 */
	private String education;

	/**
	 * major_:
	 */
	private String major;

	/**
	 * company_:
	 */
	private String company;

	/**
	 * tel_:
	 */
	private String tel;

	/**
	 * employee_type_:
	 */
	private String employeeType;

	/**
	 * team_name_:
	 */
	private String teamName;

	/**
	 * remarks_:
	 */
	private String remarks;

	public TMaintenanceWorker() {
		super();
	}

	public TMaintenanceWorker(int id, String code, String name, String sex,
			String education, String major, String company, String tel,
			String employeeType, String teamName, String remarks) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.sex = sex;
		this.education = education;
		this.major = major;
		this.company = company;
		this.tel = tel;
		this.employeeType = employeeType;
		this.teamName = teamName;
		this.remarks = remarks;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_", nullable = false)
	public int getId() {
		return id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "code_", length = 20)
	public String getCode() {
		return code;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "name_", length = 20)
	public String getName() {
		return name;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "sex_", length = 10)
	public String getSex() {
		return sex;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	@Column(name = "education_", length = 20)
	public String getEducation() {
		return education;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Column(name = "major_", length = 20)
	public String getMajor() {
		return major;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "company_", length = 20)
	public String getCompany() {
		return company;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "tel_", length = 20)
	public String getTel() {
		return tel;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	@Column(name = "employee_type_", length = 20)
	public String getEmployeeType() {
		return employeeType;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	@Column(name = "team_name_", length = 20)
	public String getTeamName() {
		return teamName;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "remarks_", length = 20)
	public String getRemarks() {
		return remarks;
	}

	public String toString() {
		return "MaintenanceWorker [id=" + id + ",code=" + code + ",name="
				+ name + ",sex=" + sex + ",education=" + education + ",major="
				+ major + ",company=" + company + ",tel=" + tel
				+ ",employeeType=" + employeeType + ",teamName=" + teamName
				+ ",remarks=" + remarks + "]";
	}

}
