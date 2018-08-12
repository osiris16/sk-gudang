package org.radot.json.beans;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author Iman
 *
 */
public class VendorItem {

	@Expose()
	private Long id;
	
	@Expose()
	private String name;

	@Expose()
	private String country;

	@Expose()
	private String vta;
	
	@Expose()
	private String address1;

	@Expose()
	private String address2;
	
	@Expose()
	private String city;
	
	@Expose()
	private String phone;
	
	@Expose()
	private String fax;
	
	@Expose()
	private String email;
	
	@Expose()
	private String tax;
	
	@Expose()
	private String cargo;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	
}
