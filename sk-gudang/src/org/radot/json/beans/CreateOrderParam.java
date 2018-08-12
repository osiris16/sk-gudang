package org.radot.json.beans;

import org.radot.json.servlet.JsonParam;

import com.google.gson.annotations.Expose;



public class CreateOrderParam extends JsonParam {
	
	@Expose
	private String customerCode;
	
	@Expose
	private String salesCode;

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getSalesCode() {
		return salesCode;
	}

	public void setSalesCode(String salesCode) {
		this.salesCode = salesCode;
	}

}
