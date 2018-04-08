package light.mvc.pageModel.base;

import java.io.Serializable;

public class ScMeasureRtdUnit implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String symbol;

	public ScMeasureRtdUnit() {
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

	@Override
	public String toString() {
		return "TScMeasureUnit [id=" + id + ", name=" + name + ", symbol="
				+ symbol + "]";
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}


}
