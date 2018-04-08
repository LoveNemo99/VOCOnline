package light.mvc.model.base;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

/**
 * ps_info_realtime:
 */
@Entity
@Table(name = "ps_info_realtime")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TPsInfoRealtime implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String code;
	private String filterCode;
	private String pollutionSourceName;
	private String pollutionSourceAlias;
	private Integer administrativeDivisionCode;
	private Integer registrationTypeCode;
	private Integer enterpriseTypeCode;
	private Integer enterpriseScaleCode;
	private Integer subordinateRelationshipCode;
	private Integer industryCategoryCode;
	private String emissionType;
	private String statisticsIndustry;
	private String pollutionSourceImage;
	private Integer basinCode;
	private String concernDegree;
	private String pollutionSourceAddress;
	private Double centerLongitude;
	private Double centerLatitude;
	private String environmentalAgencyName;
	private String environmentalProtectionPerson;
	private Integer profEnviProtCount;
	private String legalRepresentativeCode;
	private String legalRepresentative;
	private Date productionDate;
	private String accountBank;
	private String accountNumber;
	private String enterpriseWebsite;
	private String solidHandleType;
	private Boolean isSewageTreatmentFactory;
	private Boolean isStopped;
	private Integer psBaseId;
	private Integer psPiId;
	private String mn;
	private Date datatime;
	private Boolean isOnline;
	private String liquidLevel;
	private String valve;
	private String bump;
	private String power;
	private String gate;

	@Column(name = "is_stopped_")
	public Boolean getIsStopped() {
		return isStopped;
	}

	public void setIsStopped(Boolean isStopped) {
		this.isStopped = isStopped;
	}

	public TPsInfoRealtime() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_")
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "code_", length = 12)
	public String getCode() {
		return code;
	}

	public void setFilterCode(String filterCode) {
		this.filterCode = filterCode;
	}

	@Column(name = "filter_code_", length = 50)
	public String getFilterCode() {
		return filterCode;
	}

	public void setPollutionSourceName(String pollutionSourceName) {
		this.pollutionSourceName = pollutionSourceName;
	}

	@Column(name = "pollution_source_name_", length = 200)
	public String getPollutionSourceName() {
		return pollutionSourceName;
	}

	public void setPollutionSourceAlias(String pollutionSourceAlias) {
		this.pollutionSourceAlias = pollutionSourceAlias;
	}

	@Column(name = "pollution_source_alias_", length = 50)
	public String getPollutionSourceAlias() {
		return pollutionSourceAlias;
	}

	public void setAdministrativeDivisionCode(Integer administrativeDivisionCode) {
		this.administrativeDivisionCode = administrativeDivisionCode;
	}

	@Column(name = "administrative_division_code_")
	public Integer getAdministrativeDivisionCode() {
		return administrativeDivisionCode;
	}

	public void setRegistrationTypeCode(Integer registrationTypeCode) {
		this.registrationTypeCode = registrationTypeCode;
	}

	@Column(name = "registration_type_code_")
	public Integer getRegistrationTypeCode() {
		return registrationTypeCode;
	}

	public void setEnterpriseTypeCode(Integer enterpriseTypeCode) {
		this.enterpriseTypeCode = enterpriseTypeCode;
	}

	@Column(name = "enterprise_type_code_")
	public Integer getEnterpriseTypeCode() {
		return enterpriseTypeCode;
	}

	public void setEnterpriseScaleCode(Integer enterpriseScaleCode) {
		this.enterpriseScaleCode = enterpriseScaleCode;
	}

	@Column(name = "enterprise_scale_code_")
	public Integer getEnterpriseScaleCode() {
		return enterpriseScaleCode;
	}

	public void setSubordinateRelationshipCode(
			Integer subordinateRelationshipCode) {
		this.subordinateRelationshipCode = subordinateRelationshipCode;
	}

	@Column(name = "subordinate_relationship_code_")
	public Integer getSubordinateRelationshipCode() {
		return subordinateRelationshipCode;
	}

	public void setIndustryCategoryCode(Integer industryCategoryCode) {
		this.industryCategoryCode = industryCategoryCode;
	}

	@Column(name = "industry_category_code_")
	public Integer getIndustryCategoryCode() {
		return industryCategoryCode;
	}

	public void setEmissionType(String emissionType) {
		this.emissionType = emissionType;
	}

	@Column(name = "emission_type_", length = 50)
	public String getEmissionType() {
		return emissionType;
	}

	public void setStatisticsIndustry(String statisticsIndustry) {
		this.statisticsIndustry = statisticsIndustry;
	}

	@Column(name = "statistics_industry_", length = 50)
	public String getStatisticsIndustry() {
		return statisticsIndustry;
	}

	public void setPollutionSourceImage(String pollutionSourceImage) {
		this.pollutionSourceImage = pollutionSourceImage;
	}

	@Column(name = "pollution_source_image_", length = 200)
	public String getPollutionSourceImage() {
		return pollutionSourceImage;
	}

	public void setBasinCode(Integer basinCode) {
		this.basinCode = basinCode;
	}

	@Column(name = "basin_code_", length = 10)
	public Integer getBasinCode() {
		return basinCode;
	}

	public void setConcernDegree(String concernDegree) {
		this.concernDegree = concernDegree;
	}

	@Column(name = "concern_degree_", length = 5)
	public String getConcernDegree() {
		return concernDegree;
	}

	public void setPollutionSourceAddress(String pollutionSourceAddress) {
		this.pollutionSourceAddress = pollutionSourceAddress;
	}

	@Column(name = "pollution_source_address_", length = 30)
	public String getPollutionSourceAddress() {
		return pollutionSourceAddress;
	}

	public void setCenterLongitude(Double centerLongitude) {
		this.centerLongitude = centerLongitude;
	}

	@Column(name = "center_longitude_")
	public Double getCenterLongitude() {
		return centerLongitude;
	}

	public void setCenterLatitude(Double centerLatitude) {
		this.centerLatitude = centerLatitude;
	}

	@Column(name = "center_latitude_")
	public Double getCenterLatitude() {
		return centerLatitude;
	}

	public void setEnvironmentalAgencyName(String environmentalAgencyName) {
		this.environmentalAgencyName = environmentalAgencyName;
	}

	@Column(name = "environmental_agency_name_", length = 50)
	public String getEnvironmentalAgencyName() {
		return environmentalAgencyName;
	}

	public void setEnvironmentalProtectionPerson(
			String environmentalProtectionPerson) {
		this.environmentalProtectionPerson = environmentalProtectionPerson;
	}

	@Column(name = "environmental_protection_person_", length = 30)
	public String getEnvironmentalProtectionPerson() {
		return environmentalProtectionPerson;
	}

	public void setProfEnviProtCount(Integer profEnviProtCount) {
		this.profEnviProtCount = profEnviProtCount;
	}

	@Column(name = "prof_envi_prot_count_")
	public Integer getProfEnviProtCount() {
		return profEnviProtCount;
	}

	public void setLegalRepresentativeCode(String legalRepresentativeCode) {
		this.legalRepresentativeCode = legalRepresentativeCode;
	}

	@Column(name = "legal_representative_code_", length = 50)
	public String getLegalRepresentativeCode() {
		return legalRepresentativeCode;
	}

	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}

	@Column(name = "legal_representative_", length = 30)
	public String getLegalRepresentative() {
		return legalRepresentative;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "production_date_")
	public Date getProductionDate() {
		return productionDate;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}

	@Column(name = "account_bank_", length = 200)
	public String getAccountBank() {
		return accountBank;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Column(name = "account_number_", length = 50)
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setEnterpriseWebsite(String enterpriseWebsite) {
		this.enterpriseWebsite = enterpriseWebsite;
	}

	@Column(name = "enterprise_website_", length = 50)
	public String getEnterpriseWebsite() {
		return enterpriseWebsite;
	}

	public void setSolidHandleType(String solidHandleType) {
		this.solidHandleType = solidHandleType;
	}

	@Column(name = "solid_handle_type_", length = 50)
	public String getSolidHandleType() {
		return solidHandleType;
	}

	public void setIsSewageTreatmentFactory(Boolean isSewageTreatmentFactory) {
		this.isSewageTreatmentFactory = isSewageTreatmentFactory;
	}

	@Column(name = "is_sewage_treatment_factory_")
	public Boolean isIsSewageTreatmentFactory() {
		return isSewageTreatmentFactory;
	}

	@Column(name = "ps_base_id")
	public Integer getPsBaseId() {
		return psBaseId;
	}

	public void setPsBaseId(Integer psBaseId) {
		this.psBaseId = psBaseId;
	}

	@Column(name = "ps_pi_id")
	public Integer getPsPiId() {
		return psPiId;
	}

	public void setPsPiId(Integer psPiId) {
		this.psPiId = psPiId;
	}

	@Column(name = "mn")
	public String getMn() {
		return mn;
	}

	public void setMn(String mn) {
		this.mn = mn;
	}

	@Column(name = "datatime")
	public Date getDatatime() {
		return datatime;
	}

	public void setDatatime(Date datatime) {
		this.datatime = datatime;
	}

	@Column(name = "is_online")
	public Boolean getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(Boolean isOnline) {
		this.isOnline = isOnline;
	}

	@Column(name = "liquid_level")
	public String getLiquidLevel() {
		return liquidLevel;
	}

	public void setLiquidLevel(String liquidLevel) {
		this.liquidLevel = liquidLevel;
	}

	@Column(name = "valve")
	public String getValve() {
		return valve;
	}

	public void setValve(String valve) {
		this.valve = valve;
	}

	@Column(name = "bump")
	public String getBump() {
		return bump;
	}

	public void setBump(String bump) {
		this.bump = bump;
	}

	@Column(name = "power")
	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	@Column(name = "gate")
	public String getGate() {
		return gate;
	}

	public void setGate(String gate) {
		this.gate = gate;
	}

	public Boolean getIsSewageTreatmentFactory() {
		return isSewageTreatmentFactory;
	}

	@Override
	public String toString() {
		return "TPsInfoRealtime [id=" + id + ", code=" + code + ", filterCode="
				+ filterCode + ", pollutionSourceName=" + pollutionSourceName
				+ ", pollutionSourceAlias=" + pollutionSourceAlias
				+ ", administrativeDivisionCode=" + administrativeDivisionCode
				+ ", registrationTypeCode=" + registrationTypeCode
				+ ", enterpriseTypeCode=" + enterpriseTypeCode
				+ ", enterpriseScaleCode=" + enterpriseScaleCode
				+ ", subordinateRelationshipCode="
				+ subordinateRelationshipCode + ", industryCategoryCode="
				+ industryCategoryCode + ", emissionType=" + emissionType
				+ ", statisticsIndustry=" + statisticsIndustry
				+ ", pollutionSourceImage=" + pollutionSourceImage
				+ ", basinCode=" + basinCode + ", concernDegree="
				+ concernDegree + ", pollutionSourceAddress="
				+ pollutionSourceAddress + ", centerLongitude="
				+ centerLongitude + ", centerLatitude=" + centerLatitude
				+ ", environmentalAgencyName=" + environmentalAgencyName
				+ ", environmentalProtectionPerson="
				+ environmentalProtectionPerson + ", profEnviProtCount="
				+ profEnviProtCount + ", legalRepresentativeCode="
				+ legalRepresentativeCode + ", legalRepresentative="
				+ legalRepresentative + ", productionDate=" + productionDate
				+ ", accountBank=" + accountBank + ", accountNumber="
				+ accountNumber + ", enterpriseWebsite=" + enterpriseWebsite
				+ ", solidHandleType=" + solidHandleType
				+ ", isSewageTreatmentFactory=" + isSewageTreatmentFactory
				+ ", isStopped=" + isStopped + ", psBaseId=" + psBaseId
				+ ", psPiId=" + psPiId + ", mn=" + mn + ", datatime="
				+ datatime + ", isOnline=" + isOnline + ", liquidLevel="
				+ liquidLevel + ", valve=" + valve + ", bump=" + bump
				+ ", power=" + power + ", gate=" + gate + "]";
	}

}
