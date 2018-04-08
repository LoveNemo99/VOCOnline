package light.mvc.model.sys;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "biz_alarm_contact")
public class TAlarmContact implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String psCode;
	private String man;
	private String tel;
	private String level;

	public TAlarmContact() {
		super();
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public Integer getId() {
		return id;
	}

	@Column(name = "tel")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "ps_code")
	public String getPsCode() {
		return psCode;
	}

	public void setPsCode(String psCode) {
		this.psCode = psCode;
	}

	@Column(name = "man")
	public String getMan() {
		return man;
	}

	public void setMan(String man) {
		this.man = man;
	}

	@Column(name = "level")
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
}
