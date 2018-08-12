package org.radot.hibernate.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity(name="Vendor")
@Table(name="sk_vendor")
public class VendorEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8097208643527479109L;
	
	@Column(name="vnd_id")
	private String vendId;
	
	@Column(name="vnd_name", length=100, nullable=false)
	private String name;
	
	@Lob
	@Column(name="vnd_addr1")
	private String address1;
	
	@Lob
	@Column(name="vnd_addr2")
	private String address2;
	

	@Column(name="vnd_city", length=100)
	private String city;

	@Column(name="vnd_phone", length=32)
	private String phone;
	
	@Column(name="vnd_fax", length=32)
	private String fax;

	@Column(name="vnd_email", length=254)
	private String email;

	@Column(name="vnd_tax", length=32)
	private String tax;

	@Column(name="vnd_cargo", length=100)
	private String cargo;

	@Column(name="vnd_country", length=100)
	private String country;
	
	@Column(name="vnd_vta", length=100)
	private String vta;

	
	public String getVendId() {
		return vendId;
	}

	public void setVendId(String vendId) {
		this.vendId = vendId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getVta() {
		return vta;
	}

	public void setVta(String vta) {
		this.vta = vta;
	}

	
}
