package org.radot.json.beans;

import java.math.BigDecimal;

import org.radot.json.servlet.JsonParam;

import com.google.gson.annotations.Expose;

public class StockSelectParam extends JsonParam {
	
	@Expose
	private String queryData;
	@Expose
	private String byValue;
	@Expose
	private String stockEnt;
	@Expose
	private BigDecimal _qtyStock;
	@Expose
	private String dateFrom;
	@Expose
	private String dateTo;
	@Expose
	private String tripNumb;
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
	public String getStockEnt() {
		return stockEnt;
	}
	public void setStockEnt(String stockEnt) {
		this.stockEnt = stockEnt;
	}
	public BigDecimal get_qtyStock() {
		return _qtyStock;
	}
	public void set_qtyStock(BigDecimal _qtyStock) {
		this._qtyStock = _qtyStock;
	}
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
	
	
	
}
