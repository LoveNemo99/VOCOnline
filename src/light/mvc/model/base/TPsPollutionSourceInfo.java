package light.mvc.model.base;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * ps_pollution_source_info:
 */
@Entity
@Table(name = "ps_pollution_source_info")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TPsPollutionSourceInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String code;
	private String psName;
	private String administrativeDivisionCode;
	private String registrationCode;
	private String enterpriseCategoryCode;
	private String enterpriseSizeCode;
	private String enterpriseAffiliationCode;
	private String industryCategoryCode;
	private String basinCode;
	private String psAddress;
	private Double centerLongitude;
	private Double centerLatitude;
	private String legalRepresentative;
	private Boolean isStopped;
	private String environmentMan;
	private String environmentTel;
	private String alias;

	@Column(name = "is_stopped")
	public Boolean getIsStopped() {
		return isStopped;
	}

	public void setIsStopped(Boolean isStopped) {
		this.isStopped = isStopped;
	}

	public TPsPollutionSourceInfo() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "code", length = 12)
	public String getCode() {
		return code;
	}

	public void setAdministrativeDivisionCode(String administrativeDivisionCode) {
		this.administrativeDivisionCode = administrativeDivisionCode;
	}

	@Column(name = "administrative_division_code")
	public String getAdministrativeDivisionCode() {
		return administrativeDivisionCode;
	}

	public void setIndustryCategoryCode(String industryCategoryCode) {
		this.industryCategoryCode = industryCategoryCode;
	}

	@Column(name = "industry_category_code")
	public String getIndustryCategoryCode() {
		return industryCategoryCode;
	}

	
	public void setBasinCode(String basinCode) {
		this.basinCode = basinCode;
	}

	@Column(name = "basin_code", length = 10)
	public String getBasinCode() {
		return basinCode;
	}

	public void setCenterLongitude(Double centerLongitude) {
		this.centerLongitude = centerLongitude;
	}

	@Column(name = "center_longitude")
	public Double getCenterLongitude() {
		return centerLongitude;
	}

	public void setCenterLatitude(Double centerLatitude) {
		this.centerLatitude = centerLatitude;
	}

	@Column(name = "center_latitude")
	public Double getCenterLatitude() {
		return centerLatitude;
	}

	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}

	@Column(name = "legal_representative")
	public String getLegalRepresentative() {
		return legalRepresentative;
	}

	@Column(name = "ps_name")
	public String getPsName() {
		return psName;
	}

	public void setPsName(String psName) {
		this.psName = psName;
	}

	@Column(name = "enterprise_registration_code")
	public String getRegistrationCode() {
		return registrationCode;
	}

	public void setRegistrationCode(String registrationCode) {
		this.registrationCode = registrationCode;
	}

	@Column(name = "enterprise_category_code")
	public String getEnterpriseCategoryCode() {
		return enterpriseCategoryCode;
	}

	public void setEnterpriseCategoryCode(String enterpriseCategoryCode) {
		this.enterpriseCategoryCode = enterpriseCategoryCode;
	}

	@Column(name = "enterprise_size_code")
	public String getEnterpriseSizeCode() {
		return enterpriseSizeCode;
	}

	public void setEnterpriseSizeCode(String enterpriseSizeCode) {
		this.enterpriseSizeCode = enterpriseSizeCode;
	}

	@Column(name = "enterprise_affiliation_code")
	public String getEnterpriseAffiliationCode() {
		return enterpriseAffiliationCode;
	}

	public void setEnterpriseAffiliationCode(String enterpriseAffiliationCode) {
		this.enterpriseAffiliationCode = enterpriseAffiliationCode;
	}

	@Column(name = "ps_address")
	public String getPsAddress() {
		return psAddress;
	}

	public void setPsAddress(String psAddress) {
		this.psAddress = psAddress;
	}

	@Column(name = "environment_man")
	public String getEnvironmentMan() {
		return environmentMan;
	}

	public void setEnvironmentMan(String environmentMan) {
		this.environmentMan = environmentMan;
	}

	@Column(name = "environment_tel")
	public String getEnvironmentTel() {
		return environmentTel;
	}

	public void setEnvironmentTel(String environmentTel) {
		this.environmentTel = environmentTel;
	}

	@Column(name = "alias")
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

}
