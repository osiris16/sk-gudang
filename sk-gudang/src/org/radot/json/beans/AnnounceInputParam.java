package org.radot.json.beans;

import org.radot.json.servlet.JsonParam;

import com.google.gson.annotations.Expose;

public class AnnounceInputParam extends JsonParam {
	
	
	
	@Expose
	private Long date;
	
	@Expose
	private String destination;

	@Expose
	private String content;
	@Expose
	private String typePdf;
	@Expose
	private String realfaktur;


	
	

	

	
	public String getRealfaktur() {
		return realfaktur;
	}

	public void setRealfaktur(String realfaktur) {
		this.realfaktur = realfaktur;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
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

	public String getTypePdf() {
		return typePdf;
	}

	public void setTypePdf(String typePdf) {
		this.typePdf = typePdf;
	}
	
}
