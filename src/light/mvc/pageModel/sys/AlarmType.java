package light.mvc.pageModel.sys;

import java.io.Serializable;

public class AlarmType implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String type;
	private String code;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "AlarmType [id=" + id + ", type=" + type + ", code=" + code
				+ "]";
	}

}
