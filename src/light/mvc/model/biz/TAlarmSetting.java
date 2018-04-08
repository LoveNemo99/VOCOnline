package light.mvc.model.biz;

import java.io.Serializable;
import javax.persistence.*;
@Entity
@Table(name = "biz_alarm_setting")
public class TAlarmSetting implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String factorName;
	private String factorCode;
	private String concentrationLimitValue;
	private String concentrationEarlyWarningValue;
	private String piLimitValue;
	private String piEarlyWarningValue;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "factor_name")
	public String getFactorName() {
		return factorName;
	}

	public void setFactorName(String factorName) {
		this.factorName = factorName;
	}

	@Column(name = "factor_code")
	public String getFactorCode() {
		return factorCode;
	}

	public void setFactorCode(String factorCode) {
		this.factorCode = factorCode;
	}

	@Column(name = "concentration_limit_value")
	public String getConcentrationLimitValue() {
		return concentrationLimitValue;
	}

	public void setConcentrationLimitValue(String concentrationLimitValue) {
		this.concentrationLimitValue = concentrationLimitValue;
	}

	@Column(name = "concentration_early_warning_value")
	public String getConcentrationEarlyWarningValue() {
		return concentrationEarlyWarningValue;
	}

	public void setConcentrationEarlyWarningValue(
			String concentrationEarlyWarningValue) {
		this.concentrationEarlyWarningValue = concentrationEarlyWarningValue;
	}

	@Column(name = "pi_limit_value")
	public String getPiLimitValue() {
		return piLimitValue;
	}

	public void setPiLimitValue(String piLimitValue) {
		this.piLimitValue = piLimitValue;
	}

	@Column(name = "pi_early_warning_value")
	public String getPiEarlyWarningValue() {
		return piEarlyWarningValue;
	}

	public void setPiEarlyWarningValue(String piEarlyWarningValue) {
		this.piEarlyWarningValue = piEarlyWarningValue;
	}

}
