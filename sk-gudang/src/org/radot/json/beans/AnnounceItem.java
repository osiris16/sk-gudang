package org.radot.json.beans;

import java.util.Date;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author Iman
 *
 */
public class AnnounceItem{

	@Expose()
	private Long id;
	
	@Expose()
	private Date date;

	@Expose()
	private String destination;

	@Expose()
	private String content;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
