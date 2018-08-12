package org.radot.json.servlet;

import com.google.gson.annotations.Expose;

public class JsonParam {
	
	@Expose
	private Long id;
	
	@Expose
	private String limit;
	
	@Expose
	private String offset;


	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}

	
}
