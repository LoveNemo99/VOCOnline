package light.mvc.pageModel.biz;

import java.io.Serializable;

public class LicencePollutantInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	//private Integer licenceBaseId;
	private String pollutantCode;
	
	private String dayPiStr;
	private Float monthPi;
	
	private Float dayPi;
	private Float yearPi;
    private Float lowerLimit;
    private Float upperLimit;
    
    private Float concentration;
    private String factorName;
    private String licenceNum;
    private Float dayAllow;
    private Float yearAllow;

	public String getDayPiStr() {
		return dayPiStr;
	}

	public void setDayPiStr(String dayPiStr) {
		this.dayPiStr = dayPiStr;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public Float getConcentration() {
		return concentration;
	}

	public void setConcentration(Float concentration) {
		this.concentration = concentration;
	}

	public String getFactorName() {
		return factorName;
	}

	public void setFactorName(String factorName) {
		this.factorName = factorName;
	}

	public String getLicenceNum() {
		return licenceNum;
	}

	public void setLicenceNum(String licenceNum) {
		this.licenceNum = licenceNum;
	}

	public String getPollutantCode() {
		return pollutantCode;
	}

	public void setPollutantCode(String pollutantCode) {
		this.pollutantCode = pollutantCode;
	}

	public Float getMonthPi() {
		return monthPi;
	}

	public void setMonthPi(Float monthPi) {
		this.monthPi = monthPi;
	}

	public Float getDayAllow() {
		return dayAllow;
	}

	public void setDayAllow(Float dayAllow) {
		this.dayAllow = dayAllow;
	}

	public Float getYearAllow() {
		return yearAllow;
	}

	public void setYearAllow(Float yearAllow) {
		this.yearAllow = yearAllow;
	}

	@Override
	public String toString() {
		return "LicencePollutantInfo [id=" + id + ", pollutantCode="
				+ pollutantCode + ", dayPi=" + dayPi + ", dayPiStr=" + dayPiStr
				+ ", monthPi=" + monthPi + ", yearPi=" + yearPi
				+ ", concentration=" + concentration + ", lowerLimit="
				+ lowerLimit + ", upperLimit=" + upperLimit + ", factorName="
				+ factorName + ", licenceNum=" + licenceNum + ", dayAllow="
				+ dayAllow + ", yearAllow=" + yearAllow + "]";
	}

	public Float getDayPi() {
		return dayPi;
	}

	public void setDayPi(Float dayPi) {
		this.dayPi = dayPi;
	}

	public Float getYearPi() {
		return yearPi;
	}

	public void setYearPi(Float yearPi) {
		this.yearPi = yearPi;
	}

	public Float getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(Float lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	public Float getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(Float upperLimit) {
		this.upperLimit = upperLimit;
	}
	
}
