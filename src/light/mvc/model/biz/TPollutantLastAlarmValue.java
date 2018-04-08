
package light.mvc.model.biz;

import java.io.Serializable;

import javax.persistence.*;
@Entity
@Table(name = "biz_pollutant_last_alarm_value")
public class TPollutantLastAlarmValue implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int psBaseId;
	private int psPiId;
	private int pollutantCodeId;
	private Float lastAlarmValue;

	public void setId(int id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public int getId() {
		return id;
	}

	@Column(name = "ps_base_id")
	public int getPsBaseId() {
		return psBaseId;
	}

	public void setPsBaseId(int psBaseId) {
		this.psBaseId = psBaseId;
	}

	@Column(name = "ps_pi_id")
	public int getPsPiId() {
		return psPiId;
	}

	public void setPsPiId(int psPiId) {
		this.psPiId = psPiId;
	}

	@Column(name = "pollutant_code_id")
	public int getPollutantCodeId() {
		return pollutantCodeId;
	}

	public void setPollutantCodeId(int pollutantCodeId) {
		this.pollutantCodeId = pollutantCodeId;
	}

	@Column(name = "last_alarm_value")
	public Float getLastAlarmValue() {
		return lastAlarmValue;
	}

	public void setLastAlarmValue(Float lastAlarmValue) {
		this.lastAlarmValue = lastAlarmValue;
	}

	public TPollutantLastAlarmValue() {
		super();
	}

	public TPollutantLastAlarmValue(int id, int psBaseId, int psPiId,
			int pollutantCodeId, Float lastAlarmValue) {
		super();
		this.id = id;
		this.psBaseId = psBaseId;
		this.psPiId = psPiId;
		this.pollutantCodeId = pollutantCodeId;
		this.lastAlarmValue = lastAlarmValue;
	}

	@Override
	public String toString() {
		return "TPollutantLastAlarmValue [id=" + id + ", psBaseId=" + psBaseId
				+ ", psPiId=" + psPiId + ", pollutantCodeId=" + pollutantCodeId
				+ ", lastAlarmValue=" + lastAlarmValue + "]";
	}

}
