package light.mvc.model.base;

import java.io.Serializable;
import javax.persistence.*;

/**
 * sc_administrative_division:
 */
@Entity
@Table(name = "sc_administrative_division")
public class TScAdministrativeDivision implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String code;
	private String name;
	private String parentCode;
	private String jianpin;

	public TScAdministrativeDivision() {
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

	@Column(name = "parent_code")
	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public void setJianpin(String jianpin) {
		this.jianpin = jianpin;
	}

	@Column(name = "jianpin")
	public String getJianpin() {
		return jianpin;
	}

	@Override
	public String toString() {
		return "TScAdministrativeDivision [id=" + id + ", code=" + code
				+ ", name=" + name + ", parentCode=" + parentCode
				+ ", jianpin=" + jianpin + "]";
	}

}
