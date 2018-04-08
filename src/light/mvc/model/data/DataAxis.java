package light.mvc.model.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
@MappedSuperclass
public class DataAxis implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer xAxisId;
	private String pollutant;
	private Float avg, cou, max, min;
    @Column(name = "avg")
    public Float getAvg() {
        return avg;
    }
    @Column(name = "cou")
    public Float getCou() {
        return cou;
    }
    @Column(name = "max")
    public Float getMax() {
        return max;
    }
    @Column(name = "min")
    public Float getMin() {
        return min;
    }
    public void setAvg(Float avg) {
        this.avg = avg;
    }
    public void setCou(Float cou) {
        this.cou = cou;
    }
    public void setMax(Float max) {
        this.max = max;
    }
    public void setMin(Float min) {
        this.min = min;
    }
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
}
