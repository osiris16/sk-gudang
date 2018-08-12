package org.radot.json.beans;

import java.util.Date;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author Iman
 *
 */
public class HistoryItem {

	@Expose()
	private Long id;
	
	@Expose()
	private String name;

	@Expose()
	private String type;

	@Expose()
	private String actionType;
	
	@Expose()
	private Date tanggal;

	@Expose()
	private String data_history;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public Date getTanggal() {
		return tanggal;
	}

	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}

	public String getData_history() {
		return data_history;
	}

	public void setData_history(String data_history) {
		this.data_history = data_history;
	}
	
	
}
