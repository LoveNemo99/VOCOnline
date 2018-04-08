package light.mvc.model.base;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "ps_pi_port")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TPsPiPort implements Serializable {

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

	public TPsPiPort() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "port_code")
	public String getPortCode() {
		return portCode;
	}

	public void setPortCode(String portCode) {
		this.portCode = portCode;
	}

	@Column(name = "port_name")
	public String getPortName() {
		return portName;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}

	@Column(name = "mn")
	public String getMn() {
		return mn;
	}

	public void setMn(String mn) {
		this.mn = mn;
	}

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "is_supplement")
	public Boolean getIsSupplement() {
		return isSupplement;
	}

	public void setIsSupplement(Boolean isSupplement) {
		this.isSupplement = isSupplement;
	}

	@Column(name = "is_timing")
	public Boolean getIsTiming() {
		return isTiming;
	}

	public void setIsTiming(Boolean isTiming) {
		this.isTiming = isTiming;
	}

	@Column(name = "ps_code")
	public String getPsCode() {
		return psCode;
	}

	public void setPsCode(String psCode) {
		this.psCode = psCode;
	}

	@Column(name = "pollutant_type_code")
	public String getPollutantTypeCode() {
		return pollutantTypeCode;
	}

	public void setPollutantTypeCode(String pollutantTypeCode) {
		this.pollutantTypeCode = pollutantTypeCode;
	}

	@Column(name = "ip")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
