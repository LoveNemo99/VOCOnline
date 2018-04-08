package light.mvc.model.sys;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "biz_alarm_outlier_data_type")
public class TAlarmOutlierDataType implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String typeName;
	private String managerGradeCode;
	private String code;
	private Boolean isSendPs;
	private Boolean isSendMaintence;
	private Boolean isSendManage;
	private Boolean isSendCenter;
	private Boolean isSendOther;
	
	public TAlarmOutlierDataType() {
		super();
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public Integer getId() {
		return id;
	}

	@Column(name = "type_name")
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Column(name = "manager_grade_code")
	public String getManagerGradeCode() {
		return managerGradeCode;
	}

	public void setManagerGradeCode(String managerGradeCode) {
		this.managerGradeCode = managerGradeCode;
	}

	@Column(name = "code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "is_send_ps")
	public Boolean getIsSendPs() {
		return isSendPs;
	}

	public void setIsSendPs(Boolean isSendPs) {
		this.isSendPs = isSendPs;
	}

	@Column(name = "is_send_maintence")
	public Boolean getIsSendMaintence() {
		return isSendMaintence;
	}

	public void setIsSendMaintence(Boolean isSendMaintence) {
		this.isSendMaintence = isSendMaintence;
	}

	@Column(name = "is_send_manage")
	public Boolean getIsSendManage() {
		return isSendManage;
	}

	public void setIsSendManage(Boolean isSendManage) {
		this.isSendManage = isSendManage;
	}

	@Column(name = "is_send_center")
	public Boolean getIsSendCenter() {
		return isSendCenter;
	}

	public void setIsSendCenter(Boolean isSendCenter) {
		this.isSendCenter = isSendCenter;
	}

	@Column(name = "is_send_other")
	public Boolean getIsSendOther() {
		return isSendOther;
	}

	public void setIsSendOther(Boolean isSendOther) {
		this.isSendOther = isSendOther;
	}

	public TAlarmOutlierDataType(Integer id, String typeName,
			String managerGradeCode, String code, Boolean isSendPs,
			Boolean isSendMaintence, Boolean isSendManage,
			Boolean isSendCenter, Boolean isSendOther) {
		super();
		this.id = id;
		this.typeName = typeName;
		this.managerGradeCode = managerGradeCode;
		this.code = code;
		this.isSendPs = isSendPs;
		this.isSendMaintence = isSendMaintence;
		this.isSendManage = isSendManage;
		this.isSendCenter = isSendCenter;
		this.isSendOther = isSendOther;
	}

	@Override
	public String toString() {
		return "TAlarmOutlierDataType [id=" + id + ", typeName=" + typeName
				+ ", managerGradeCode=" + managerGradeCode + ", code=" + code
				+ ", isSendPs=" + isSendPs + ", isSendMaintence="
				+ isSendMaintence + ", isSendManage=" + isSendManage
				+ ", isSendCenter=" + isSendCenter + ", isSendOther="
				+ isSendOther + "]";
	}

}