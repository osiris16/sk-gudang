package org.radot.json.beans;

import java.util.List;

import org.radot.json.servlet.JsonResult;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author Iman
 *
 */
public class TripResult extends JsonResult {
	
	@Expose()
	private List<TripItem> items;

	public List<TripItem> getItems() {
		return items;
	}

	public void setItems(List<TripItem> items) {
		this.items = items;
	}

	@Expose
	public String page;

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
}
