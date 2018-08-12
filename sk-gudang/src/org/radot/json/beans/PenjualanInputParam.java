package org.radot.json.beans;

import java.math.BigDecimal;
import java.util.Date;

import org.radot.json.servlet.JsonParam;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author Iman
 *
 */
public class PenjualanInputParam extends JsonParam{

	
	@Expose
	private Long orderDate;
	@Expose
	private String orderNumb;
	@Expose
	private String fakturNumb;
	@Expose
	private String customerEnt;
	@Expose
	private String salesmanEnt;
	@Expose
	private BigDecimal discDefault;
	@Expose
	private BigDecimal brutoDefault;
	@Expose
	private BigDecimal nettoDefault;
	@Expose
	private BigDecimal ppn;
	@Expose
	private BigDecimal totPpn;
	@Expose
	private BigDecimal totDisc;
	@Expose
	private String terkirim;
	@Expose
	private String keterangan;
	
	
	
	
	public Long getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Long orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderNumb() {
		return orderNumb;
	}
	public void setOrderNumb(String orderNumb) {
		this.orderNumb = orderNumb;
	}
	public String getFakturNumb() {
		return fakturNumb;
	}
	public void setFakturNumb(String fakturNumb) {
		this.fakturNumb = fakturNumb;
	}
	public String getCustomerEnt() {
		return customerEnt;
	}
	public void setCustomerEnt(String customerEnt) {
		this.customerEnt = customerEnt;
	}
	public String getSalesmanEnt() {
		return salesmanEnt;
	}
	public void setSalesmanEnt(String salesmanEnt) {
		this.salesmanEnt = salesmanEnt;
	}
	public BigDecimal getDiscDefault() {
		return discDefault;
	}
	public void setDiscDefault(BigDecimal discDefault) {
		this.discDefault = discDefault;
	}
	public BigDecimal getBrutoDefault() {
		return brutoDefault;
	}
	public void setBrutoDefault(BigDecimal brutoDefault) {
		this.brutoDefault = brutoDefault;
	}
	public BigDecimal getNettoDefault() {
		return nettoDefault;
	}
	public void setNettoDefault(BigDecimal nettoDefault) {
		this.nettoDefault = nettoDefault;
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
	public BigDecimal getTotDisc() {
		return totDisc;
	}
	public void setTotDisc(BigDecimal totDisc) {
		this.totDisc = totDisc;
	}
	public String getTerkirim() {
		return terkirim;
	}
	public void setTerkirim(String terkirim) {
		this.terkirim = terkirim;
	}
	public String getKeterangan() {
		return keterangan;
	}
	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}
	
}

	

