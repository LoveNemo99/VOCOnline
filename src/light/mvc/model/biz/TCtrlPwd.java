
package light.mvc.model.biz;

import java.io.Serializable;

import javax.persistence.*;
@Entity
@Table(name = "biz_ctrl_pwd")
public class TCtrlPwd implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String psCode;
	private String piCode;
	private String password;
	private String passwordType;

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "password_type")
	public String getPasswordType() {
		return passwordType;
	}

	public void setPasswordType(String passwordType) {
		this.passwordType = passwordType;
	}
	
	public TCtrlPwd() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "ps_code")
	public String getPsCode() {
		return psCode;
	}

	public void setPsCode(String psCode) {
		this.psCode = psCode;
	}

	@Column(name = "pi_code")
	public String getPiCode() {
		return piCode;
	}

	public void setPiCode(String piCode) {
		this.piCode = piCode;
	}
	
}
