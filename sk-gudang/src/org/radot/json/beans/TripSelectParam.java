package org.radot.json.beans;

import org.radot.json.servlet.JsonParam;

import com.google.gson.annotations.Expose;

public class TripSelectParam extends JsonParam {
	

	
//	@Expose
//	private BigDecimal harga;
	@Expose
	private String dateFrom;
	@Expose
	private String dateTo;
	@Expose
	private String tripNumb;
	@Expose
	private String actiontype;
	
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
	public String getTripNumb() {
		return tripNumb;
	}
	public void setTripNumb(String tripNumb) {
		this.tripNumb = tripNumb;
	}
	public String getActiontype() {
		return actiontype;
	}
	public void setActiontype(String actiontype) {
		this.actiontype = actiontype;
	}
	
	
	
}
