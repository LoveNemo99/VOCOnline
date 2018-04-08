package light.mvc.model.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="data_real_time")
public class RealTimeDataAxis implements Serializable{
	private static final long serialVersionUID = 1L;
	Float rtd;
	private Integer xAxisId;
	private String pollutant;
	
	@Id
	@Column(name="pollutant")
	public String getPollutant() {
		return pollutant;
	}
	public void setPollutant(String pollutant) {
		this.pollutant = pollutant;
	}

	@Id
	@Column(name="ID")
	public Integer getxAxisId() {
		return xAxisId;
	}
	public void setxAxisId(Integer xAxisId) {
		this.xAxisId = xAxisId;
	}

	@Column(name="rtd")
	public Float getRtd() {
		return rtd;
	}

	public void setRtd(Float rtd) {
		this.rtd = rtd;
	}

}
