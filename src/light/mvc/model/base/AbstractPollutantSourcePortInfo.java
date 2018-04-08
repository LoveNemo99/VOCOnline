package light.mvc.model.base;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
@MappedSuperclass
public abstract class AbstractPollutantSourcePortInfo {
	String code,pollutionSourceName,administrativeDivisionName,filterCode,name,pollutionSourceCode,psAlias;
	Integer pollutionSourceId,administrativeDivisionCode,industryCategoryCode;
	Boolean sewageTreatmentFactory,isOnline;
	@Id
	@Column(name="code_")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(name="pollution_source_name_")
	public String getPollutionSourceName() {
		return pollutionSourceName;
	}
	public void setPollutionSourceName(String pollutionSourceName) {
		this.pollutionSourceName = pollutionSourceName;
	}
	@Column(name="administrative_division_name_")
	public String getAdministrativeDivisionName() {
		return administrativeDivisionName;
	}
	public void setAdministrativeDivisionName(String administrativeDivisionName) {
		this.administrativeDivisionName = administrativeDivisionName;
	}
	@Column(name="filter_code_")
	public String getFilterCode() {
		return filterCode;
	}
	public void setFilterCode(String filterCode) {
		this.filterCode = filterCode;
	}
	@Column(name="name_")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="pollution_source_id_")
	public Integer getPollutionSourceId() {
		return pollutionSourceId;
	}
	public void setPollutionSourceId(Integer pollutionSourceId) {
		this.pollutionSourceId = pollutionSourceId;
	}
	@Column(name="administrative_division_code_")
	public Integer getAdministrativeDivisionCode() {
		return administrativeDivisionCode;
	}
	public void setAdministrativeDivisionCode(Integer administrativeDivisionCode) {
		this.administrativeDivisionCode = administrativeDivisionCode;
	}
	@Column(name="industry_category_code_")
	public Integer getIndustryCategoryCode() {
		return industryCategoryCode;
	}
	public void setIndustryCategoryCode(Integer industryCategoryCode) {
		this.industryCategoryCode = industryCategoryCode;
	}
	@Column(name="is_sewage_treatment_factory_")
	public Boolean getSewageTreatmentFactory() {
		return sewageTreatmentFactory;
	}
	public void setSewageTreatmentFactory(Boolean sewageTreatmentFactory) {
		this.sewageTreatmentFactory = sewageTreatmentFactory;
	}
	@Column(name="pollution_source_code_")
	public String getPollutionSourceCode() {
		return pollutionSourceCode;
	}
	public void setPollutionSourceCode(String pollutionSourceCode) {
		this.pollutionSourceCode = pollutionSourceCode;
	}
	@Column(name="pollution_source_alias_")
	public String getPsAlias() {
		return psAlias;
	}
	public void setPsAlias(String psAlias) {
		this.psAlias = psAlias;
	}
	@Column(name="is_online")
	public Boolean getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(Boolean isOnline) {
		this.isOnline = isOnline;
	}
	

}
