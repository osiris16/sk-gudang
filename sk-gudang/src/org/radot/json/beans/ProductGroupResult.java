package org.radot.json.beans;

import java.util.List;

import org.radot.json.servlet.JsonResult;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author Iman
 *
 */
public class ProductGroupResult extends JsonResult {
	
	@Expose()
	private List<ProductGroupItem> items;

	public List<ProductGroupItem> getItems() {
		return items;
	}

	public void setItems(List<ProductGroupItem> items) {
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
