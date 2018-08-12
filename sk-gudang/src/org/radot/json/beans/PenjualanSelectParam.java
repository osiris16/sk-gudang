package org.radot.json.beans;

import org.radot.json.servlet.JsonParam;

import com.google.gson.annotations.Expose;

public class PenjualanSelectParam extends JsonParam {
	
	@Expose
	private String dateFrom;
	@Expose
	private String dateTo;
	@Expose
	private String queryDataPenj;
	@Expose
	private String byValuePenj;
	@Expose
	private String orderNumb;
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
	public String getQueryDataPenj() {
		return queryDataPenj;
	}
	public void setQueryDataPenj(String queryDataPenj) {
		this.queryDataPenj = queryDataPenj;
	}
	public String getByValuePenj() {
		return byValuePenj;
	}
	public void setByValuePenj(String byValuePenj) {
		this.byValuePenj = byValuePenj;
	}
	public String getOrderNumb() {
		return orderNumb;
	}
	public void setOrderNumb(String orderNumb) {
		this.orderNumb = orderNumb;
	}
	public String getActiontype() {
		return actiontype;
	}
	public void setActiontype(String actiontype) {
		this.actiontype = actiontype;
	}
	
	
}
