package org.radot.hibernate.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name="Penjualan")
@Table(name="sk_penjualan")
public class PenjualanEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7249876829637306471L;

	@JoinColumn(name="salesman_code_fk")
	@OneToOne()
	private SalesmanEntity salesmanEnt;
	
	@JoinColumn(name="customer_code_fk")
	@OneToOne()
	private CustomerEntity customerEnt;
	
	@Column(name="kode_jual")
	private  String kodeJual;
	
	@Column(name="no_order", nullable=false, unique=true)
	private String orderNumb;
	
	@Column(name="terkirim")
	private String terkirim;
	
	@Column(name="tanggal_order")
	private Date orderDate;
	
	@Column(name="tanggal_kirim")
	private Date orderDateKirim;
	
	@Column(name="no_faktur")
	private String fakturNumb;
	
	@Column(name="keterangan")
	private String keterangan;

	@Column(name="tot_penjualan_bruto_Idr")
	private BigDecimal totPenjualanBrutoIdr;
	
	@Column(name="tot_disc_penjualan")
	private BigDecimal totDiscPenjualan;
	
	@Column(name="tot_penjualan_nett_idr")
	private BigDecimal totPenjualanNettIdr;
	
	@Column(name="ppn")
	private BigDecimal ppn;
	
	@Column(name="tot_ppn")
	private BigDecimal totPpn;

	public SalesmanEntity getSalesmanEnt() {
		return salesmanEnt;
	}

	public void setSalesmanEnt(SalesmanEntity salesmanEnt) {
		this.salesmanEnt = salesmanEnt;
	}

	public CustomerEntity getCustomerEnt() {
		return customerEnt;
	}

	public void setCustomerEnt(CustomerEntity customerEnt) {
		this.customerEnt = customerEnt;
	}

	public String getKodeJual() {
		return kodeJual;
	}

	public void setKodeJual(String kodeJual) {
		this.kodeJual = kodeJual;
	}

	public String getOrderNumb() {
		return orderNumb;
	}

	public void setOrderNumb(String orderNumb) {
		this.orderNumb = orderNumb;
	}

	public String getTerkirim() {
		return terkirim;
	}

	public void setTerkirim(String terkirim) {
		this.terkirim = terkirim;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getOrderDateKirim() {
		return orderDateKirim;
	}

	public void setOrderDateKirim(Date orderDateKirim) {
		this.orderDateKirim = orderDateKirim;
	}

	public String getFakturNumb() {
		return fakturNumb;
	}

	public void setFakturNumb(String fakturNumb) {
		this.fakturNumb = fakturNumb;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public BigDecimal getTotPenjualanBrutoIdr() {
		return totPenjualanBrutoIdr;
	}

	public void setTotPenjualanBrutoIdr(BigDecimal totPenjualanBrutoIdr) {
		this.totPenjualanBrutoIdr = totPenjualanBrutoIdr;
	}

	public BigDecimal getTotDiscPenjualan() {
		return totDiscPenjualan;
	}

	public void setTotDiscPenjualan(BigDecimal totDiscPenjualan) {
		this.totDiscPenjualan = totDiscPenjualan;
	}

	public BigDecimal getTotPenjualanNettIdr() {
		return totPenjualanNettIdr;
	}

	public void setTotPenjualanNettIdr(BigDecimal totPenjualanNettIdr) {
		this.totPenjualanNettIdr = totPenjualanNettIdr;
	}

	public BigDecimal getPpn() {
		return ppn;
	}

	public void setPpn(BigDecimal ppn) {
		this.ppn = ppn;
	}

	public BigDecimal getTotPpn() {
		return totPpn;
	}

	public void setTotPpn(BigDecimal totPpn) {
		this.totPpn = totPpn;
	}

	
	
}
