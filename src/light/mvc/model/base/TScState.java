package light.mvc.model.base;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "sc_state")
public class TScState implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String type;
	private String state;

	public TScState() {
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

	@Column(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "state")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
