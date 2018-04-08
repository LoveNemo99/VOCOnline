package light.mvc.model.data;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "working_condition")
public class WorkingCondition implements Serializable{
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private BigInteger mainId;
	private String conditionName;
	private String condition;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	@Column(name="condition_name")
	public String getConditionName() {
		return conditionName;
	}
	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}
	@Column(name="condition")
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
}
