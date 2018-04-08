package light.mvc.pageModel.base;

import java.io.Serializable;

public class PsContactInfo implements Serializable {

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

	public PsContactInfo() {
		super();
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPsCode() {
		return psCode;
	}

	public void setPsCode(String psCode) {
		this.psCode = psCode;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	@Override
	public String toString() {
		return "PsContactInfo [id=" + id + ", psCode=" + psCode 
				+ ", name=" + name + ", officePhone=" + officePhone
				+ ", fax=" + fax + ", mobilePhone=" + mobilePhone + ", email="
				+ email + ", postalCode=" + postalCode + ", postalAddress="
				+ postalAddress + "]";
	}

}
