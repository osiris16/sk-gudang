package org.radot.json.beans;

import org.radot.json.servlet.JsonParam;

import com.google.gson.annotations.Expose;

public class AnnounceSelectParam extends JsonParam {
	
	@Expose
	private String queryData;
	@Expose
	private String byValue;
	public String getQueryData() {
		return queryData;
	}
	public void setQueryData(String queryData) {
		this.queryData = queryData;
	}
	public String getByValue() {
		return byValue;
	}
	public void setByValue(String byValue) {
		this.byValue = byValue;
	}

	
	
}
