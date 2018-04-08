package light.mvc.pageModel.base;

import java.io.Serializable;

public class PsPiPort implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String psCode;
	private String pollutantTypeCode;
	private String portCode;
	private String portName;
	private String mn;
	private String ip;
	private String remark;
	private Boolean isSupplement;
	private Boolean isTiming;

	public PsPiPort() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPortCode() {
		return portCode;
	}

	public void setPortCode(String portCode) {
		this.portCode = portCode;
	}

	public String getPortName() {
		return portName;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}

	public String getMn() {
		return mn;
	}

	public void setMn(String mn) {
		this.mn = mn;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getIsSupplement() {
		return isSupplement;
	}

	public void setIsSupplement(Boolean isSupplement) {
		this.isSupplement = isSupplement;
	}


	public Boolean getIsTiming() {
		return isTiming;
	}

	public void setIsTiming(Boolean isTiming) {
		this.isTiming = isTiming;
	}

	public String getPsCode() {
		return psCode;
	}

	public void setPsCode(String psCode) {
		this.psCode = psCode;
	}

	public String getPollutantTypeCode() {
		return pollutantTypeCode;
	}

	public void setPollutantTypeCode(String pollutantTypeCode) {
		this.pollutantTypeCode = pollutantTypeCode;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "PsPiPort [id=" + id + ", psCode=" + psCode
				+ ", pollutantTypeCode=" + pollutantTypeCode + ", portCode="
				+ portCode + ", portName=" + portName + ", mn=" + mn
				+ ", remark=" + remark + ", isSupplement=" + isSupplement
				+ ", isTiming=" + isTiming + "]";
	}

}
