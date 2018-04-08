package light.mvc.model.data;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
@MappedSuperclass
public class Data implements Serializable{
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private BigInteger mainId;
	private String pollutantCode;
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
	@Column(name="id")
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	@Column(name="main_id")
	public BigInteger getMainId() {
		return mainId;
	}
	public void setMainId(BigInteger mainId) {
		this.mainId = mainId;
	}
	
	@Column(name="pollutant_code")
	public String getPollutantCode() {
		return pollutantCode;
	}
	public void setPollutantCode(String pollutantCode) {
		this.pollutantCode = pollutantCode;
	}
	
	public Data() {
		super();
	}
	@Override
	public String toString() {
		return "Data [id=" + id + ", mainId=" + mainId + ", pollutantCode="
				+ pollutantCode + ", avg=" + avg + ", cou=" + cou + ", max="
				+ max + ", min=" + min + "]";
	}
	
}
