package light.mvc.model.base;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "sc_pollutant_factor")
public class TScPollutantFactor implements Serializable {

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

	public TScPollutantFactor() {
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

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "code")
	public String getCode() {
		return code;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}
	
	@Column(name = "lower_limit")
	public Float getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(Float lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	@Column(name = "upper_limit")
	public Float getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(Float upperLimit) {
		this.upperLimit = upperLimit;
	}

	@Column(name = "measurement_unit_code")
	public String getMeasurementUnitCode() {
		return measurementUnitCode;
	}

	public void setMeasurementUnitCode(String measurementUnitCode) {
		this.measurementUnitCode = measurementUnitCode;
	}

	@Column(name = "measurement_rtd_unit_code")
	public String getMeasurementRtdUnitCode() {
		return measurementRtdUnitCode;
	}

	public void setMeasurementRtdUnitCode(String measurementRtdUnitCode) {
		this.measurementRtdUnitCode = measurementRtdUnitCode;
	}

	@Column(name = "category_code")
	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	@Column(name = "state")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "TScPollutantFactor [id=" + id + ", code=" + code + ", name="
				+ name + ", measurementUnitCode=" + measurementUnitCode
				+ ", measurementRtdUnitCode=" + measurementRtdUnitCode
				+ ", categoryCode=" + categoryCode + ", lowerLimit="
				+ lowerLimit + ", upperLimit=" + upperLimit + "]";
	}
	
}
