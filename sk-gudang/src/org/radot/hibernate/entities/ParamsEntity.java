package org.radot.hibernate.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name="params")
@Table(name="sk_params")
public class ParamsEntity extends BaseEntity {

	/**
	 * 
	 */

	private static final long serialVersionUID = -6897251049955540869L;
	
	@Column(name="params_key")
	private String paramsKey;
	@Column(name="params_desc")
	private String paramsDesc;
	
	public String getParamsKey() {
		return paramsKey;
	}
	public void setParamsKey(String paramsKey) {
		this.paramsKey = paramsKey;
	}
	public String getParamsDesc() {
		return paramsDesc;
	}
	public void setParamsDesc(String paramsDesc) {
		this.paramsDesc = paramsDesc;
	}
	
}
