package org.radot.json.beans;

import java.math.BigDecimal;

import org.radot.json.servlet.JsonParam;

import com.google.gson.annotations.Expose;

public class TripInputParam extends JsonParam {
	
	@Expose
	private String trip_numb;
	@Expose
	private Long trip_date;
	@Expose
	private String vendorEnt;
	@Expose
	private BigDecimal currencyIDR;
	@Expose
	private Long trip_dateNote;
	@Expose
	private String trip_noteNumber;
	@Expose
	private long dateReceive;
	@Expose
	private BigDecimal disc;
	@Expose
	private BigDecimal cost;
	public String getTrip_numb() {
		return trip_numb;
	}
	public void setTrip_numb(String trip_numb) {
		this.trip_numb = trip_numb;
	}
	public Long getTrip_date() {
		return trip_date;
	}
	public void setTrip_date(Long trip_date) {
		this.trip_date = trip_date;
	}
	public String getVendorEnt() {
		return vendorEnt;
	}
	public void setVendorEnt(String vendorEnt) {
		this.vendorEnt = vendorEnt;
	}
	public BigDecimal getCurrencyIDR() {
		return currencyIDR;
	}
	public void setCurrencyIDR(BigDecimal currencyIDR) {
		this.currencyIDR = currencyIDR;
	}
	public Long getTrip_dateNote() {
		return trip_dateNote;
	}
	public void setTrip_dateNote(Long trip_dateNote) {
		this.trip_dateNote = trip_dateNote;
	}
	public String getTrip_noteNumber() {
		return trip_noteNumber;
	}
	public void setTrip_noteNumber(String trip_noteNumber) {
		this.trip_noteNumber = trip_noteNumber;
	}
	public long getDateReceive() {
		return dateReceive;
	}
	public void setDateReceive(long dateReceive) {
		this.dateReceive = dateReceive;
	}
	public BigDecimal getDisc() {
		return disc;
	}
	public void setDisc(BigDecimal disc) {
		this.disc = disc;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	
	
}
