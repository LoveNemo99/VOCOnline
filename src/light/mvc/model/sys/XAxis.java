package light.mvc.model.sys;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
@MappedSuperclass
public class XAxis implements Serializable{
	Integer id;
	String MN;
	Date datatime;
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="MN")
	public String getMN() {
		return MN;
	}

	public void setMN(String mN) {
		MN = mN;
	}


	@Column(name="datatime")
	public Date getDatatime() {
		return datatime;
	}

	public void setDatatime(Date datatime) {
		this.datatime = datatime;
	}
	
}
