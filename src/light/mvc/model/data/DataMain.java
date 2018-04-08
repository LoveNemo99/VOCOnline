package light.mvc.model.data;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
@MappedSuperclass
public class DataMain implements Serializable{
	private BigInteger id;
	private String psCode;
	private String piCode;
	private Date monitorTime;
	private String mn;
	private Boolean isSynchronize;
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	@Column(name="monitor_time")
	public Date getMonitorTime() {
		return monitorTime;
	}

	public void setMonitorTime(Date monitorTime) {
		this.monitorTime = monitorTime;
	}

	@Column(name="mn")
	public String getMn() {
		return mn;
	}

	public void setMn(String mn) {
		this.mn = mn;
	}

	@Column(name="ps_code")
	public String getPsCode() {
		return psCode;
	}

	public void setPsCode(String psCode) {
		this.psCode = psCode;
	}

	@Column(name="pi_code")
	public String getPiCode() {
		return piCode;
	}

	public void setPiCode(String piCode) {
		this.piCode = piCode;
	}

	@Column(name="is_synchronize")
	public Boolean getIsSynchronize() {
		return isSynchronize;
	}

	public void setIsSynchronize(Boolean isSynchronize) {
		this.isSynchronize = isSynchronize;
	}

	@Override
	public String toString() {
		return "DataMain [id=" + id + ", psCode=" + psCode + ", piCode="
				+ piCode + ", monitorTime=" + monitorTime + ", mn=" + mn
				+ ", isSynchronize=" + isSynchronize + "]";
	}
	
}
