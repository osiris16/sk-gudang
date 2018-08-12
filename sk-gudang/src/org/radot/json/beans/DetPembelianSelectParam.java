package org.radot.json.beans;

import org.radot.json.servlet.JsonParam;

import com.google.gson.annotations.Expose;

public class DetPembelianSelectParam extends JsonParam {
	
	@Expose
	private String dateFrom;
	@Expose
	private String dateTo;
	@Expose
	private String queryDataBeli;
	@Expose
	private String byValueBeli;
	
	
	public String getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	public String getDateTo() {
		return dateTo;
	}
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
	public String getQueryDataBeli() {
		return queryDataBeli;
	}
	public void setQueryDataBeli(String queryDataBeli) {
		this.queryDataBeli = queryDataBeli;
	}
	public String getByValueBeli() {
		return byValueBeli;
	}
	public void setByValueBeli(String byValueBeli) {
		this.byValueBeli = byValueBeli;
	}
	
	
}
