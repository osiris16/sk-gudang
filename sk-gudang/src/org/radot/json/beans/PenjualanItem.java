package org.radot.json.beans;

import java.math.BigDecimal;
import java.util.Date;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author Iman
 *
 */
public class PenjualanItem {

	@Expose
	private Long id;
	@Expose
	private Date orderDate;
	@Expose
	private String orderNumb;
	@Expose
	private String fakturNumb;
	@Expose
	private Long custId;
	@Expose
	private String custCode;
	@Expose
	private String custName;
	@Expose
	private String custCity;
	@Expose
	private String custAddress;
	@Expose
	private String custAddress2;
	@Expose
	private Long salesId;
	@Expose
	private String salesCode;
	@Expose
	private String salesName;
	@Expose
	private String keterangan;
	@Expose
	private BigDecimal totDisc;
	@Expose
	private BigDecimal totJualBruto;
	@Expose
	private BigDecimal totJualNetto;
	@Expose
	private BigDecimal totJualNettoBeforePpn;
	@Expose
	private BigDecimal ppn;
	@Expose
	private BigDecimal totPpn;
	@Expose
	private String terkirim;
	@Expose
	private BigDecimal hargaJualBrutoCtn;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
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
	public Long getCustId() {
		return custId;
	}
	public void setCustId(Long custId) {
		this.custId = custId;
	}
	public String getCustCode() {
		return custCode;
	}
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustCity() {
		return custCity;
	}
	public void setCustCity(String custCity) {
		this.custCity = custCity;
	}
	public String getCustAddress() {
		return custAddress;
	}
	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}
	public String getCustAddress2() {
		return custAddress2;
	}
	public void setCustAddress2(String custAddress2) {
		this.custAddress2 = custAddress2;
	}
	public Long getSalesId() {
		return salesId;
	}
	public void setSalesId(Long salesId) {
		this.salesId = salesId;
	}
	public String getSalesCode() {
		return salesCode;
	}
	public void setSalesCode(String salesCode) {
		this.salesCode = salesCode;
	}
	public String getSalesName() {
		return salesName;
	}
	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}
	public String getKeterangan() {
		return keterangan;
	}
	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}
	public BigDecimal getTotDisc() {
		return totDisc;
	}
	public void setTotDisc(BigDecimal totDisc) {
		this.totDisc = totDisc;
	}
	public BigDecimal getTotJualBruto() {
		return totJualBruto;
	}
	public void setTotJualBruto(BigDecimal totJualBruto) {
		this.totJualBruto = totJualBruto;
	}
	public BigDecimal getTotJualNetto() {
		return totJualNetto;
	}
	public void setTotJualNetto(BigDecimal totJualNetto) {
		this.totJualNetto = totJualNetto;
	}
	
	public BigDecimal getTotJualNettoBeforePpn() {
		return totJualNettoBeforePpn;
	}
	public void setTotJualNettoBeforePpn(BigDecimal totJualNettoBeforePpn) {
		this.totJualNettoBeforePpn = totJualNettoBeforePpn;
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
	public String getTerkirim() {
		return terkirim;
	}
	public void setTerkirim(String terkirim) {
		this.terkirim = terkirim;
	}
	public BigDecimal getHargaJualBrutoCtn() {
		return hargaJualBrutoCtn;
	}
	public void setHargaJualBrutoCtn(BigDecimal hargaJualBrutoCtn) {
		this.hargaJualBrutoCtn = hargaJualBrutoCtn;
	}
	
	
}
