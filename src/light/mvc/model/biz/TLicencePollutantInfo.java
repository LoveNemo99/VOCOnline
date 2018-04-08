package light.mvc.model.biz;

import java.io.Serializable;
import javax.persistence.*;
@Entity
@Table(name = "biz_licence_pollutant_info")
public class TLicencePollutantInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String licenceNum;
	private String pollutantCode;
	private Float dayPi;
	private Float monthPi;
	private Float yearPi;
	private Float concentration;
    private Float lowerLimit;
    private Float upperLimit;
    private Float dayAllow;
    private Float yearAllow;

    @Column(name = "upper_limit")
	public Float getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(Float upperLimit) {
		this.upperLimit = upperLimit;
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

	@Column(name = "licence_num")
	public String getLicenceNum() {
		return licenceNum;
	}

	public void setLicenceNum(String licenceNum) {
		this.licenceNum = licenceNum;
	}

	@Column(name = "day_pi")
	public Float getDayPi() {
		return dayPi;
	}

	public void setDayPi(Float dayPi) {
		this.dayPi = dayPi;
	}

	@Column(name = "year_pi")
	public Float getYearPi() {
		return yearPi;
	}

	public void setYearPi(Float yearPi) {
		this.yearPi = yearPi;
	}

	@Column(name = "concentration")
	public Float getConcentration() {
		return concentration;
	}

	public void setConcentration(Float concentration) {
		this.concentration = concentration;
	}

	@Column(name = "lower_limit")
	public Float getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(Float lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	@Column(name = "pollutant_code")
	public String getPollutantCode() {
		return pollutantCode;
	}

	public void setPollutantCode(String pollutantCode) {
		this.pollutantCode = pollutantCode;
	}

	@Column(name = "month_pi")
	public Float getMonthPi() {
		return monthPi;
	}

	public void setMonthPi(Float monthPi) {
		this.monthPi = monthPi;
	}

	@Column(name = "day_allow")
	public Float getDayAllow() {
		return dayAllow;
	}

	public void setDayAllow(Float dayAllow) {
		this.dayAllow = dayAllow;
	}

	@Column(name = "year_allow")
	public Float getYearAllow() {
		return yearAllow;
	}

	public void setYearAllow(Float yearAllow) {
		this.yearAllow = yearAllow;
	}
	
}
