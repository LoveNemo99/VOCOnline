package light.mvc.model.base;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "sc_pollutant_type")
public class TScPollutantType implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String code;
	private String name;

	public TScPollutantType() {
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

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "code")
	public String getCode() {
		return code;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "TScPollutantType [id=" + id + ", code=" + code + ", name="
				+ name + "]";
	}
	
}
