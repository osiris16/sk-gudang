package org.radot.json.beans;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author Iman
 *
 */
public class CustomerItem {

	@Expose
	private Long id;
	
	@Expose
	private String code;
	@Expose
	private String name;
	@Expose
	private String address1;
	@Expose
	private String address2;
	@Expose
	private String city;
	@Expose
	private String contact;
	@Expose
	private String phone;
	@Expose
	private String fax;
	@Expose
	private String tax;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
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
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
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
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	
	
	
}
