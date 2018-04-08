package light.mvc.pageModel.biz;

import java.io.Serializable;

public class MonthPlanPollutant implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer mainId;
    private String code;
	private Float allow;
	private String factorName;

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMainId() {
		return mainId;
	}

	public void setMainId(Integer mainId) {
		this.mainId = mainId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Float getAllow() {
		return allow;
	}

	public void setAllow(Float allow) {
		this.allow = allow;
	}

	public String getFactorName() {
		return factorName;
	}

	public void setFactorName(String factorName) {
		this.factorName = factorName;
	}

	@Override
	public String toString() {
		return "MonthPlanPollutant [id=" + id + ", mainId=" + mainId
				+ ", code=" + code + ", allow=" + allow + ", factorName="
				+ factorName + "]";
	}

	
}
