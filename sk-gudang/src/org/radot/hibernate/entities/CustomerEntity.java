package org.radot.hibernate.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity(name="Customer")
@Table(name="sk_customers")
public class CustomerEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2931649254105439716L;

	@Column(name="cust_id")
	private String custId;
	
	@Column(name="cust_code")
	private String code;

	@Column(name="cust_name", length=128)
	private String name;

	@Lob
	@Column(name="cust_addr1")
	private String address1;

	@Lob
	@Column(name="cust_addr2")
	private String address2;

	@Column(name="cust_city", length=100)
	private String city;

	@Column(name="cust_contact", length=32)
	private String contact;

	@Column(name="cust_phone", length=32)
	private String phone;

	@Column(name="cust_fax", length=32)
	private String fax;

	@Column(name="cust_tax", length=32)
	private String tax;

	
	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
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
