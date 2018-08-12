package org.radot.hibernate.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity(name="Salesman")
@Table(name="sk_salesman")
public class SalesmanEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5472364707309131344L;

	@Column(name="salesman_code", length=16, unique=true, nullable=false, updatable=false)
	private String code;

	@Column(name="salesman_name", length=128)
	private String name;

	@Lob
	@Column(name="addr1")
	private String address1;

	@Lob
	@Column(name="addr2")
	private String address2;

	@Column(name="phone", length=35)
	private String phone;

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
