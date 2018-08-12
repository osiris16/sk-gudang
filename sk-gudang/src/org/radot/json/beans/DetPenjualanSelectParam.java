package org.radot.json.beans;

import org.radot.json.servlet.JsonParam;

import com.google.gson.annotations.Expose;

public class DetPenjualanSelectParam extends JsonParam {
	
	@Expose
	private String queryDataOrder;
	@Expose
	private String byValueOrder;
	@Expose
	private String fakturChek;
	public String getQueryDataOrder() {
		return queryDataOrder;
	}
	public void setQueryDataOrder(String queryDataOrder) {
		this.queryDataOrder = queryDataOrder;
	}
	public String getByValueOrder() {
		return byValueOrder;
	}
	public void setByValueOrder(String byValueOrder) {
		this.byValueOrder = byValueOrder;
	}
	public String getFakturChek() {
		return fakturChek;
	}
	public void setFakturChek(String fakturChek) {
		this.fakturChek = fakturChek;
	}
}
