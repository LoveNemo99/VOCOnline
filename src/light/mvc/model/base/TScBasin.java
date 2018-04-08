package light.mvc.model.base;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "sc_basin")
public class TScBasin implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String basinCode;
	private String trunkName;
	private String basinName;

	public TScBasin() {
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

	@Column(name = "basin_code")
	public String getBasinCode() {
		return basinCode;
	}


	public void setBasinCode(String basinCode) {
		this.basinCode = basinCode;
	}

	@Column(name = "trunk_name")
	public String getTrunkName() {
		return trunkName;
	}


	public void setTrunkName(String trunkName) {
		this.trunkName = trunkName;
	}

	@Column(name = "basin_name")
	public String getBasinName() {
		return basinName;
	}


	public void setBasinName(String basinName) {
		this.basinName = basinName;
	}

}
