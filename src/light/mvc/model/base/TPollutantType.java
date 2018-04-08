package light.mvc.model.base;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "sc_pollutant_type")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TPollutantType implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String code;

	public TPollutantType() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TPollutantType(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Column(name = "code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "TPollutantType [id=" + id + ", name=" + name + ", code=" + code
				+ "]";
	}

	

}
