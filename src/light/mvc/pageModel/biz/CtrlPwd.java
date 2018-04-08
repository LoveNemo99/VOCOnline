
package light.mvc.pageModel.biz;

import java.io.Serializable;
public class CtrlPwd implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int psBaseId;
	private int psPiId;
	private String password;
	private String passwordType;
	private String oldPwd;
	private String newPwd;

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPsBaseId() {
		return psBaseId;
	}

	public void setPsBaseId(int psBaseId) {
		this.psBaseId = psBaseId;
	}

	public int getPsPiId() {
		return psPiId;
	}

	public void setPsPiId(int psPiId) {
		this.psPiId = psPiId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordType() {
		return passwordType;
	}

	public void setPasswordType(String passwordType) {
		this.passwordType = passwordType;
	}

	public int getId() {
		return id;
	}

	public CtrlPwd() {
		super();
	}

	public CtrlPwd(int id, int psBaseId, int psPiId, String password,
			String passwordType) {
		super();
		this.id = id;
		this.psBaseId = psBaseId;
		this.psPiId = psPiId;
		this.password = password;
		this.passwordType = passwordType;
	}

	@Override
	public String toString() {
		return "TCtrlPwd [id=" + id + ", psBaseId=" + psBaseId + ", psPiId="
				+ psPiId + ", password=" + password + ", passwordType="
				+ passwordType + "]";
	}
	
}
