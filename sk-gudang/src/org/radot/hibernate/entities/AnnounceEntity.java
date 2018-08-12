package org.radot.hibernate.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name="Announce")
@Table(name="sk_announce")
public class AnnounceEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7364842649271914664L;

	@Column(name="anc_date")
	private Date date;
	
	@Column(name="anc_dest")
	private String destination;

	@Column(name="anc_cont")
	private String content;

	

	

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
