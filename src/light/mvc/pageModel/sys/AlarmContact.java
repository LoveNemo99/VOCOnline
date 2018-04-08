package light.mvc.pageModel.sys;

import java.io.Serializable;

public class AlarmContact implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String psCode;
	private String man;
	private String tel;
	private String level;

	public AlarmContact() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPsCode() {
		return psCode;
	}

	public void setPsCode(String psCode) {
		this.psCode = psCode;
	}

	public String getMan() {
		return man;
	}

	public void setMan(String man) {
		this.man = man;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "AlarmContact [id=" + id + ", psCode=" + psCode + ", man=" + man
				+ ", tel=" + tel + ", level=" + level + "]";
	}

}
