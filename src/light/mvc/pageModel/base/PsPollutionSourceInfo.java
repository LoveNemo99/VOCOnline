package light.mvc.pageModel.base;

import java.io.Serializable;

public class PsPollutionSourceInfo implements Serializable {
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPsName() {
		return psName;
	}
	public void setPsName(String psName) {
		this.psName = psName;
	}
	
	public String getBasinCode() {
		return basinCode;
	}
	public void setBasinCode(String basinCode) {
		this.basinCode = basinCode;
	}
	public String getPsAddress() {
		return psAddress;
	}
	public void setPsAddress(String psAddress) {
		this.psAddress = psAddress;
	}
	public Double getCenterLongitude() {
		return centerLongitude;
	}
	public void setCenterLongitude(Double centerLongitude) {
		this.centerLongitude = centerLongitude;
	}
	public Double getCenterLatitude() {
		return centerLatitude;
	}
	public void setCenterLatitude(Double centerLatitude) {
		this.centerLatitude = centerLatitude;
	}
	public String getLegalRepresentative() {
		return legalRepresentative;
	}
	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}
	public Boolean getIsStopped() {
		return isStopped;
	}
	public void setIsStopped(Boolean isStopped) {
		this.isStopped = isStopped;
	}
	public String getAdministrativeDivisionCode() {
		return administrativeDivisionCode;
	}
	public void setAdministrativeDivisionCode(String administrativeDivisionCode) {
		this.administrativeDivisionCode = administrativeDivisionCode;
	}
	public String getRegistrationCode() {
		return registrationCode;
	}
	public void setRegistrationCode(String registrationCode) {
		this.registrationCode = registrationCode;
	}
	public String getEnterpriseCategoryCode() {
		return enterpriseCategoryCode;
	}
	public void setEnterpriseCategoryCode(String enterpriseCategoryCode) {
		this.enterpriseCategoryCode = enterpriseCategoryCode;
	}
	public String getEnterpriseSizeCode() {
		return enterpriseSizeCode;
	}
	public void setEnterpriseSizeCode(String enterpriseSizeCode) {
		this.enterpriseSizeCode = enterpriseSizeCode;
	}
	public String getEnterpriseAffiliationCode() {
		return enterpriseAffiliationCode;
	}
	public void setEnterpriseAffiliationCode(String enterpriseAffiliationCode) {
		this.enterpriseAffiliationCode = enterpriseAffiliationCode;
	}
	public String getIndustryCategoryCode() {
		return industryCategoryCode;
	}
	public void setIndustryCategoryCode(String industryCategoryCode) {
		this.industryCategoryCode = industryCategoryCode;
	}
	
	public String getEnvironmentMan() {
		return environmentMan;
	}
	public void setEnvironmentMan(String environmentMan) {
		this.environmentMan = environmentMan;
	}
	public String getEnvironmentTel() {
		return environmentTel;
	}
	public void setEnvironmentTel(String environmentTel) {
		this.environmentTel = environmentTel;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	@Override
	public String toString() {
		return "PsPollutionSourceInfo [id=" + id + ", code=" + code
				+ ", psName=" + psName + ", administrativeDivisionCode="
				+ administrativeDivisionCode + ", registrationCode="
				+ registrationCode + ", enterpriseCategoryCode="
				+ enterpriseCategoryCode + ", enterpriseSizeCode="
				+ enterpriseSizeCode + ", enterpriseAffiliationCode="
				+ enterpriseAffiliationCode + ", industryCategoryCode="
				+ industryCategoryCode + ", basinCode=" + basinCode
				+ ", psAddress=" + psAddress + ", centerLongitude="
				+ centerLongitude + ", centerLatitude=" + centerLatitude
				+ ", legalRepresentative=" + legalRepresentative
				+ ", isStopped=" + isStopped + "]";
	}
}
