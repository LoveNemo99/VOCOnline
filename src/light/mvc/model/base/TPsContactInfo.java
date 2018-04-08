package light.mvc.model.base;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "ps_contact_info")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TPsContactInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String psCode;
	private String name;
	private String officePhone;
	private String fax;
	private String mobilePhone;
	private String email;
	private String postalCode;
	private String postalAddress;

	public TPsContactInfo() {
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

	@Column(name = "ps_code")
	public String getPsCode() {
		return psCode;
	}

	public void setPsCode(String psCode) {
		this.psCode = psCode;
	}

	@Column(name = "office_phone")
	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	@Column(name = "fax")
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "mobile_phone")
	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "postal_code")
	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Column(name = "postal_address")
	public String getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	@Override
	public String toString() {
		return "TPsContactInfo [id=" + id + ", psCode=" + psCode + ", name="
				+ name + ", officePhone=" + officePhone + ", fax=" + fax
				+ ", mobilePhone=" + mobilePhone + ", email=" + email
				+ ", postalCode=" + postalCode + ", postalAddress="
				+ postalAddress + "]";
	}

}
