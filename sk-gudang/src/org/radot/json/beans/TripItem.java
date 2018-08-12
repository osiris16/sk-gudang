package org.radot.json.beans;

import java.math.BigDecimal;
import java.util.Date;

import org.radot.hibernate.entities.VendorEntity;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author Iman
 *
 */
public class TripItem {

	@Expose()
	private Long id;
	@Expose()
	private Long vendId;
	@Expose()
	private VendorEntity vendorEnt;
	
	@Expose()
	private String trip_numb;
	
	@Expose()
	private Date trip_date;
	
	@Expose()
	private Date trip_dateNote;
	
	@Expose()
	private String trip_noteNumber;
	
	@Expose()
	private String trip_vendName;
	
	@Expose()
	private String trip_vendCountry;

	@Expose()
	private String trip_vta;

	@Expose()
	private BigDecimal currencyIDR;
	
	@Expose()
	private Date dateReceive;
	
	@Expose()
	private BigDecimal totBeliCtn;
	
	@Expose()
	private BigDecimal totBeliBrutoVta;
	
	@Expose()
	private BigDecimal totBeliBrutoIdr;
	
	@Expose()
	private BigDecimal totBeliNettoVta;
	
	@Expose()
	private BigDecimal totBeliNettoIdr;
	
	@Expose()
	private BigDecimal totDisc;
	
	@Expose()
	private BigDecimal totCost;
	
	@Expose()
	private BigDecimal totDiscHelp;
	
	@Expose()
	private BigDecimal totCostHelp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVendId() {
		return vendId;
	}

	public void setVendId(Long vendId) {
		this.vendId = vendId;
	}

	public VendorEntity getVendorEnt() {
		return vendorEnt;
	}

	public void setVendorEnt(VendorEntity vendorEnt) {
		this.vendorEnt = vendorEnt;
	}

	public String getTrip_numb() {
		return trip_numb;
	}

	public void setTrip_numb(String trip_numb) {
		this.trip_numb = trip_numb;
	}

	public Date getTrip_date() {
		return trip_date;
	}

	public void setTrip_date(Date trip_date) {
		this.trip_date = trip_date;
	}

	public Date getTrip_dateNote() {
		return trip_dateNote;
	}

	public void setTrip_dateNote(Date trip_dateNote) {
		this.trip_dateNote = trip_dateNote;
	}

	public String getTrip_noteNumber() {
		return trip_noteNumber;
	}

	public void setTrip_noteNumber(String trip_noteNumber) {
		this.trip_noteNumber = trip_noteNumber;
	}

	public String getTrip_vendName() {
		return trip_vendName;
	}

	public void setTrip_vendName(String trip_vendName) {
		this.trip_vendName = trip_vendName;
	}

	public String getTrip_vendCountry() {
		return trip_vendCountry;
	}

	public void setTrip_vendCountry(String trip_vendCountry) {
		this.trip_vendCountry = trip_vendCountry;
	}

	public String getTrip_vta() {
		return trip_vta;
	}

	public void setTrip_vta(String trip_vta) {
		this.trip_vta = trip_vta;
	}

	public BigDecimal getCurrencyIDR() {
		return currencyIDR;
	}

	public void setCurrencyIDR(BigDecimal currencyIDR) {
		this.currencyIDR = currencyIDR;
	}

	public Date getDateReceive() {
		return dateReceive;
	}

	public void setDateReceive(Date dateReceive) {
		this.dateReceive = dateReceive;
	}

	public BigDecimal getTotBeliCtn() {
		return totBeliCtn;
	}

	public void setTotBeliCtn(BigDecimal totBeliCtn) {
		this.totBeliCtn = totBeliCtn;
	}

	public BigDecimal getTotBeliBrutoVta() {
		return totBeliBrutoVta;
	}

	public void setTotBeliBrutoVta(BigDecimal totBeliBrutoVta) {
		this.totBeliBrutoVta = totBeliBrutoVta;
	}

	public BigDecimal getTotBeliBrutoIdr() {
		return totBeliBrutoIdr;
	}

	public void setTotBeliBrutoIdr(BigDecimal totBeliBrutoIdr) {
		this.totBeliBrutoIdr = totBeliBrutoIdr;
	}

	public BigDecimal getTotBeliNettoVta() {
		return totBeliNettoVta;
	}

	public void setTotBeliNettoVta(BigDecimal totBeliNettoVta) {
		this.totBeliNettoVta = totBeliNettoVta;
	}

	public BigDecimal getTotBeliNettoIdr() {
		return totBeliNettoIdr;
	}

	public void setTotBeliNettoIdr(BigDecimal totBeliNettoIdr) {
		this.totBeliNettoIdr = totBeliNettoIdr;
	}

	public BigDecimal getTotDisc() {
		return totDisc;
	}

	public void setTotDisc(BigDecimal totDisc) {
		this.totDisc = totDisc;
	}

	public BigDecimal getTotCost() {
		return totCost;
	}

	public void setTotCost(BigDecimal totCost) {
		this.totCost = totCost;
	}

	public BigDecimal getTotDiscHelp() {
		return totDiscHelp;
	}

	public void setTotDiscHelp(BigDecimal totDiscHelp) {
		this.totDiscHelp = totDiscHelp;
	}

	public BigDecimal getTotCostHelp() {
		return totCostHelp;
	}

	public void setTotCostHelp(BigDecimal totCostHelp) {
		this.totCostHelp = totCostHelp;
	}
	
	
	
	
}
