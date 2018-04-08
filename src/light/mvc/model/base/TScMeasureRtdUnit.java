package light.mvc.model.base;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "sc_measure_rtd_unit")
public class TScMeasureRtdUnit implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String symbol;

	public TScMeasureRtdUnit() {
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

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	@Column(name = "symbol")
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return "TScMeasureRtdUnit [id=" + id + ", name=" + name + ", symbol="
				+ symbol + "]";
	}


}
