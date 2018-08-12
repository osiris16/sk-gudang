package org.radot.hibernate.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name="Trip")
@Table(name="sk_trip")
public class TripEntity extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -259026046286438926L;

	
	@Column(name="kode_trip")
	private String kodeTrip;
	
	@Column(name="trip_date")
	private Date trip_date;
	
	@Column(name="trip_numb", length=100, nullable=false, unique=true)
	private String trip_numb;
	
	@Column(name="trip_date_note")
	private Date trip_dateNote;
	
	@Column(name="trip_noteNumber")
	private String trip_noteNumber;

	@Column(name="trip_currsIDR")
	private BigDecimal currencyIDR;
	
	@Column(name="trip_dateReceive")
	private Date dateReceive;
	
	@JoinColumn(name="vnd_code_fk")
	@OneToOne()
	private VendorEntity vendorEnt;

	@Column(name="tot_pembelianBruto_Vta")
	private BigDecimal totPembelianBrutoVta;
	
	@Column(name="tot_pembelianBruto_Idr")
	private BigDecimal totPembelianBrutoIdr;
	
	@Column(name="tot_pembelianNetto_Idr")
	private BigDecimal totPembelianNettoIdr;
	
	@Column(name="tot_pembelianNetto_Vta")
	private BigDecimal totPembelianNettoVta;
	
	@Column(name="tot_cost")
	private BigDecimal totCost;
	
	@Column(name="tot_disc")
	private BigDecimal totDisc;
	
	@Column(name="tot_carton")
	private BigDecimal totCarton;

	public String getKodeTrip() {
		return kodeTrip;
	}

	public void setKodeTrip(String kodeTrip) {
		this.kodeTrip = kodeTrip;
	}

	public Date getTrip_date() {
		return trip_date;
	}

	public void setTrip_date(Date trip_date) {
		this.trip_date = trip_date;
	}

	public String getTrip_numb() {
		return trip_numb;
	}

	public void setTrip_numb(String trip_numb) {
		this.trip_numb = trip_numb;
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

	public VendorEntity getVendorEnt() {
		return vendorEnt;
	}

	public void setVendorEnt(VendorEntity vendorEnt) {
		this.vendorEnt = vendorEnt;
	}

	public BigDecimal getTotPembelianBrutoVta() {
		return totPembelianBrutoVta;
	}

	public void setTotPembelianBrutoVta(BigDecimal totPembelianBrutoVta) {
		this.totPembelianBrutoVta = totPembelianBrutoVta;
	}

	public BigDecimal getTotPembelianBrutoIdr() {
		return totPembelianBrutoIdr;
	}

	public void setTotPembelianBrutoIdr(BigDecimal totPembelianBrutoIdr) {
		this.totPembelianBrutoIdr = totPembelianBrutoIdr;
	}

	public BigDecimal getTotPembelianNettoIdr() {
		return totPembelianNettoIdr;
	}

	public void setTotPembelianNettoIdr(BigDecimal totPembelianNettoIdr) {
		this.totPembelianNettoIdr = totPembelianNettoIdr;
	}

	public BigDecimal getTotPembelianNettoVta() {
		return totPembelianNettoVta;
	}

	public void setTotPembelianNettoVta(BigDecimal totPembelianNettoVta) {
		this.totPembelianNettoVta = totPembelianNettoVta;
	}

	public BigDecimal getTotCost() {
		return totCost;
	}

	public void setTotCost(BigDecimal totCost) {
		this.totCost = totCost;
	}

	public BigDecimal getTotDisc() {
		return totDisc;
	}

	public void setTotDisc(BigDecimal totDisc) {
		this.totDisc = totDisc;
	}

	public BigDecimal getTotCarton() {
		return totCarton;
	}

	public void setTotCarton(BigDecimal totCarton) {
		this.totCarton = totCarton;
	}
}
