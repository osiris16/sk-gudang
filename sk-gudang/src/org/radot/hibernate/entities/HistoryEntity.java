package org.radot.hibernate.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name="history")
@Table(name="sk_history")
public class HistoryEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 341331741997007690L;

	/**
	 * 
	 */
	

	
	
	@Column(name="type", length=32)
	private String type;
	
	@Column(name="action_type", length=32)
	private String actionType;
	
	@Column(name="data_history", length=4000)
	private String dataHistory;
	

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDataHistory() {
		return dataHistory;
	}

	public void setDataHistory(String dataHistory) {
		this.dataHistory = dataHistory;
	}
	
	
	
}
