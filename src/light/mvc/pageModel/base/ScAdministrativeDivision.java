package light.mvc.pageModel.base;

import java.io.Serializable;

public class ScAdministrativeDivision implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String code;
	private String name;
	private String parentCode;
	private String jianpin;

	public ScAdministrativeDivision() {
		super();
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public void setJianpin(String jianpin) {
		this.jianpin = jianpin;
	}

	public String getJianpin() {
		return jianpin;
	}

	@Override
	public String toString() {
		return "ScAdministrativeDivision [id=" + id + ", code=" + code
				+ ", name=" + name + ", parentCode=" + parentCode
				+ ", jianpin=" + jianpin + "]";
	}
	
}
