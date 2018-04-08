package light.mvc.model.biz;

import java.io.Serializable;
import javax.persistence.*;
@Entity
@Table(name = "biz_month_plan")
public class TMonthPlan implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer mainId;
	private String code;
	private Float allow;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "main_id")
	public Integer getMainId() {
		return mainId;
	}

	public void setMainId(Integer mainId) {
		this.mainId = mainId;
	}

	@Column(name = "code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "allow")
	public Float getAllow() {
		return allow;
	}

	public void setAllow(Float allow) {
		this.allow = allow;
	}

	@Override
	public String toString() {
		return "TMonthPlan [id=" + id + ", mainId=" + mainId + ", code=" + code
				+ ", allow=" + allow + "]";
	}
	
}
