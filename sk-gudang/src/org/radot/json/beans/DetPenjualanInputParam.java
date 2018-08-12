package org.radot.json.beans;

import java.math.BigDecimal;

import org.radot.json.servlet.JsonParam;

import com.google.gson.annotations.Expose;

public class DetPenjualanInputParam extends JsonParam {
	
	@Expose
	private String prodEnt;
	@Expose
	private String stockEnt;
	@Expose
	private String penjualanEnt;
	@Expose
	private Long customerId;
	@Expose
	private Long salesmanId;
	@Expose
	private String customerCode;
	@Expose
	private String customerName;
	@Expose
	private String salesmanCode;
	@Expose
	private String salesmanName;
	@Expose
	private String orderNumb;
	@Expose
	private String fakturNumb;
	@Expose
	private String productCode;
	@Expose
	private String productName;
	@Expose
	private BigDecimal totStockPcs;
	@Expose
	private BigDecimal isiCtn;
	@Expose
	private BigDecimal totQtyJualPcs;
	@Expose
	private BigDecimal totQtyJualCtn;
	@Expose
	private BigDecimal disc;
	@Expose
	private BigDecimal hargaJualStdIsiCtn;
	@Expose
	private BigDecimal hargaJualPcs;
	@Expose
	private BigDecimal hargaJualCtn;
	@Expose
	private BigDecimal totJual;
	@Expose
	private BigDecimal ppn;
	@Expose
	private BigDecimal totPpn;
	@Expose
	private String keterangan;
	
	public String getProdEnt() {
		return prodEnt;
	}
	public void setProdEnt(String prodEnt) {
		this.prodEnt = prodEnt;
	}
	public String getStockEnt() {
		return stockEnt;
	}
	public void setStockEnt(String stockEnt) {
		this.stockEnt = stockEnt;
	}
	public String getPenjualanEnt() {
		return penjualanEnt;
	}
	public void setPenjualanEnt(String penjualanEnt) {
		this.penjualanEnt = penjualanEnt;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getSalesmanId() {
		return salesmanId;
	}
	public void setSalesmanId(Long salesmanId) {
		this.salesmanId = salesmanId;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getSalesmanCode() {
		return salesmanCode;
	}
	public void setSalesmanCode(String salesmanCode) {
		this.salesmanCode = salesmanCode;
	}
	public String getSalesmanName() {
		return salesmanName;
	}
	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
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
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigDecimal getTotStockPcs() {
		return totStockPcs;
	}
	public void setTotStockPcs(BigDecimal totStockPcs) {
		this.totStockPcs = totStockPcs;
	}
	public BigDecimal getIsiCtn() {
		return isiCtn;
	}
	public void setIsiCtn(BigDecimal isiCtn) {
		this.isiCtn = isiCtn;
	}
	public BigDecimal getTotQtyJualPcs() {
		return totQtyJualPcs;
	}
	public void setTotQtyJualPcs(BigDecimal totQtyJualPcs) {
		this.totQtyJualPcs = totQtyJualPcs;
	}
	
	public BigDecimal getTotQtyJualCtn() {
		return totQtyJualCtn;
	}
	public void setTotQtyJualCtn(BigDecimal totQtyJualCtn) {
		this.totQtyJualCtn = totQtyJualCtn;
	}
	public BigDecimal getDisc() {
		return disc;
	}
	public void setDisc(BigDecimal disc) {
		this.disc = disc;
	}
	public BigDecimal getHargaJualStdIsiCtn() {
		return hargaJualStdIsiCtn;
	}
	public void setHargaJualStdIsiCtn(BigDecimal hargaJualStdIsiCtn) {
		this.hargaJualStdIsiCtn = hargaJualStdIsiCtn;
	}
	public BigDecimal getHargaJualPcs() {
		return hargaJualPcs;
	}
	public void setHargaJualPcs(BigDecimal hargaJualPcs) {
		this.hargaJualPcs = hargaJualPcs;
	}
	
	public BigDecimal getHargaJualCtn() {
		return hargaJualCtn;
	}
	public void setHargaJualCtn(BigDecimal hargaJualCtn) {
		this.hargaJualCtn = hargaJualCtn;
	}
	public BigDecimal getTotJual() {
		return totJual;
	}
	public void setTotJual(BigDecimal totJual) {
		this.totJual = totJual;
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
	public String getKeterangan() {
		return keterangan;
	}
	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}
	
	
	
	
	
	
	
	
	
	
	
}
