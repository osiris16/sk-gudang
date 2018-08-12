package org.radot.json.beans;

import org.radot.json.servlet.JsonParam;

import com.google.gson.annotations.Expose;

public class SalesmanSelectParam extends JsonParam {
	
	@Expose
	private String queryData;
	@Expose
	private String byValue;
	@Expose
	private String dateTo;
	@Expose
	private String dateFrom;
	@Expose
	private String discMin;
	@Expose
	private String discMax;
	
	public String getDiscMin() {
		return discMin;
	}
	public void setDiscMin(String discMin) {
		this.discMin = discMin;
	}
	public String getDiscMax() {
		return discMax;
	}
	public void setDiscMax(String discMax) {
		this.discMax = discMax;
	}
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
	public String getDateTo() {
		return dateTo;
	}
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
	public String getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	
	
	
}
