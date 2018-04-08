package light.mvc.pageModel.base;

import java.io.Serializable;

public class EmissionDestination implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String code;

	public EmissionDestination() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "EmissionDestination [id=" + id + ", name=" + name + ", code=" + code
				+ "]";
	}

}
