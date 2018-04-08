package light.mvc.model.sys;

import java.io.Serializable;
import javax.persistence.*;

/**
 * alarm_type:
 */
@Entity
@Table(name = "biz_alarm_outlier_data_type")
public class TAlarmType implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String type;
	private String code;

	@Column(name = "code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public TAlarmType() {
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

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "type_name", length = 20)
	public String getType() {
		return type;
	}

}
