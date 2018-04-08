package light.mvc.pageModel.base;

import java.io.Serializable;

public class ScMeasureUnit implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String symbol;
	private String code;

	public ScMeasureUnit() {
		super();
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "ScMeasureUnit [id=" + id + ", name=" + name + ", symbol="
				+ symbol + ", code=" + code + "]";
	}
	
}
