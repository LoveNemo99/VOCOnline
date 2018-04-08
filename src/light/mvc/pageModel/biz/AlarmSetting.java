package light.mvc.pageModel.biz;

import java.io.Serializable;

public class AlarmSetting implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String factorName;
	private String factorCode;
	private String concentrationLimitValue;
	private String concentrationEarlyWarningValue;
	private String piLimitValue;
	private String piEarlyWarningValue;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFactorName() {
		return factorName;
	}
	public void setFactorName(String factorName) {
		this.factorName = factorName;
	}
	public String getFactorCode() {
		return factorCode;
	}
	public void setFactorCode(String factorCode) {
		this.factorCode = factorCode;
	}
	public String getConcentrationLimitValue() {
		return concentrationLimitValue;
	}
	public void setConcentrationLimitValue(String concentrationLimitValue) {
		this.concentrationLimitValue = concentrationLimitValue;
	}
	public String getConcentrationEarlyWarningValue() {
		return concentrationEarlyWarningValue;
	}
	public void setConcentrationEarlyWarningValue(
			String concentrationEarlyWarningValue) {
		this.concentrationEarlyWarningValue = concentrationEarlyWarningValue;
	}
	public String getPiLimitValue() {
		return piLimitValue;
	}
	public void setPiLimitValue(String piLimitValue) {
		this.piLimitValue = piLimitValue;
	}
	public String getPiEarlyWarningValue() {
		return piEarlyWarningValue;
	}
	public void setPiEarlyWarningValue(String piEarlyWarningValue) {
		this.piEarlyWarningValue = piEarlyWarningValue;
	}
	@Override
	public String toString() {
		return "AlarmSetting [id=" + id + ", factorName=" + factorName
				+ ", factorCode=" + factorCode + ", concentrationLimitValue="
				+ concentrationLimitValue + ", concentrationEarlyWarningValue="
				+ concentrationEarlyWarningValue + ", piLimitValue="
				+ piLimitValue + ", piEarlyWarningValue=" + piEarlyWarningValue
				+ "]";
	}

}
