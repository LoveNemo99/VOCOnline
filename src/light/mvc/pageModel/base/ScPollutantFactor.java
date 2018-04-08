package light.mvc.pageModel.base;

import java.io.Serializable;

public class ScPollutantFactor implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String code;
	private String name;
	private String measurementUnitCode;
	private String measurementRtdUnitCode;
	private String categoryCode;
	private Float lowerLimit;
	private Float upperLimit;
	private String state;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getMeasurementUnitCode() {
		return measurementUnitCode;
	}
	public void setMeasurementUnitCode(String measurementUnitCode) {
		this.measurementUnitCode = measurementUnitCode;
	}
	public String getMeasurementRtdUnitCode() {
		return measurementRtdUnitCode;
	}
	public void setMeasurementRtdUnitCode(String measurementRtdUnitCode) {
		this.measurementRtdUnitCode = measurementRtdUnitCode;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "ScPollutantFactor [id=" + id + ", code=" + code + ", name="
				+ name + ", measurementUnitCode=" + measurementUnitCode
				+ ", measurementRtdUnitCode=" + measurementRtdUnitCode
				+ ", categoryCode=" + categoryCode + ", lowerLimit="
				+ lowerLimit + ", upperLimit=" + upperLimit + ", state="
				+ state + "]";
	}

}
