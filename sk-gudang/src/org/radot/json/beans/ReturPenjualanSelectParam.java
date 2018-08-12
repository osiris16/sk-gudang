package org.radot.json.beans;

import org.radot.json.servlet.JsonParam;

import com.google.gson.annotations.Expose;

public class ReturPenjualanSelectParam extends JsonParam {
	
	@Expose
	private String queryDataRetur;
	@Expose
	private String byValueRetur;
	
	public String getQueryDataRetur() {
		return queryDataRetur;
	}
	public void setQueryDataRetur(String queryDataRetur) {
		this.queryDataRetur = queryDataRetur;
	}
	public String getByValueRetur() {
		return byValueRetur;
	}
	public void setByValueRetur(String byValueRetur) {
		this.byValueRetur = byValueRetur;
	}
	
	
	
}
