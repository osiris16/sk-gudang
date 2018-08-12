package org.radot.hibernate.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name="ProductGroup")
@Table(name="sk_prod_grp")
public class ProductGroupEntity extends BaseEntity {

	private static final long serialVersionUID = -6306099759633249183L;
	/**
	 * 
	 */
	

	@Column(name="prod_grp_name", length=128, unique=true)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
