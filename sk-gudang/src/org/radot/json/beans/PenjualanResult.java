package org.radot.json.beans;

import java.util.List;

import org.radot.json.servlet.JsonResult;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author Iman
 *
 */
public class PenjualanResult extends JsonResult {
	
	@Expose()
	private List<PenjualanItem> items;
	
	@Expose
	public String page;

	
	
	public List<PenjualanItem> getItems() {
		return items;
	}

	public void setItems(List<PenjualanItem> items) {
		this.items = items;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
}
