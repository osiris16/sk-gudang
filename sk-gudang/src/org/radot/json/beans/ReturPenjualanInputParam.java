package org.radot.json.beans;

import java.math.BigDecimal;

import org.radot.json.servlet.JsonParam;

import com.google.gson.annotations.Expose;

public class ReturPenjualanInputParam extends JsonParam {
	
	
	@Expose
	private String noRetur;
	@Expose
	private Long dateRetur;
	@Expose
	private String prodEnt;
	@Expose
	private String detPenjualanEnt;
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
	private BigDecimal totQtyJualCtn;
	@Expose
	private BigDecimal disc;
	@Expose
	private BigDecimal hargaJualCtn;
	@Expose
	private BigDecimal nilaiNettoRetur;
	@Expose
	private BigDecimal nilaiBrutoRetur;
	@Expose
	private BigDecimal hargaBrutoCtn;
	@Expose
	private BigDecimal totQtyReturCtn;
	@Expose
	private BigDecimal totBrutoFaktur;
	@Expose
	private BigDecimal totNettoFaktur;
	@Expose
	private BigDecimal totPpnFaktur;
	@Expose
	private BigDecimal totDiscFaktur;
	@Expose
	private String penerima;
	@Expose
	private String alasanRetur;
	public String getNoRetur() {
		return noRetur;
	}
	public void setNoRetur(String noRetur) {
		this.noRetur = noRetur;
	}
	public Long getDateRetur() {
		return dateRetur;
	}
	public void setDateRetur(Long dateRetur) {
		this.dateRetur = dateRetur;
	}
	public String getProdEnt() {
		return prodEnt;
	}
	public void setProdEnt(String prodEnt) {
		this.prodEnt = prodEnt;
	}
	public String getDetPenjualanEnt() {
		return detPenjualanEnt;
	}
	public void setDetPenjualanEnt(String detPenjualanEnt) {
		this.detPenjualanEnt = detPenjualanEnt;
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
	public BigDecimal getHargaJualCtn() {
		return hargaJualCtn;
	}
	public void setHargaJualCtn(BigDecimal hargaJualCtn) {
		this.hargaJualCtn = hargaJualCtn;
	}
	public BigDecimal getNilaiNettoRetur() {
		return nilaiNettoRetur;
	}
	public void setNilaiNettoRetur(BigDecimal nilaiNettoRetur) {
		this.nilaiNettoRetur = nilaiNettoRetur;
	}
	public BigDecimal getNilaiBrutoRetur() {
		return nilaiBrutoRetur;
	}
	public void setNilaiBrutoRetur(BigDecimal nilaiBrutoRetur) {
		this.nilaiBrutoRetur = nilaiBrutoRetur;
	}
	public BigDecimal getHargaBrutoCtn() {
		return hargaBrutoCtn;
	}
	public void setHargaBrutoCtn(BigDecimal hargaBrutoCtn) {
		this.hargaBrutoCtn = hargaBrutoCtn;
	}
	public BigDecimal getTotQtyReturCtn() {
		return totQtyReturCtn;
	}
	public void setTotQtyReturCtn(BigDecimal totQtyReturCtn) {
		this.totQtyReturCtn = totQtyReturCtn;
	}
	public BigDecimal getTotBrutoFaktur() {
		return totBrutoFaktur;
	}
	public void setTotBrutoFaktur(BigDecimal totBrutoFaktur) {
		this.totBrutoFaktur = totBrutoFaktur;
	}
	public BigDecimal getTotNettoFaktur() {
		return totNettoFaktur;
	}
	public void setTotNettoFaktur(BigDecimal totNettoFaktur) {
		this.totNettoFaktur = totNettoFaktur;
	}
	public BigDecimal getTotPpnFaktur() {
		return totPpnFaktur;
	}
	public void setTotPpnFaktur(BigDecimal totPpnFaktur) {
		this.totPpnFaktur = totPpnFaktur;
	}
	public BigDecimal getTotDiscFaktur() {
		return totDiscFaktur;
	}
	public void setTotDiscFaktur(BigDecimal totDiscFaktur) {
		this.totDiscFaktur = totDiscFaktur;
	}
	public String getPenerima() {
		return penerima;
	}
	public void setPenerima(String penerima) {
		this.penerima = penerima;
	}
	public String getAlasanRetur() {
		return alasanRetur;
	}
	public void setAlasanRetur(String alasanRetur) {
		this.alasanRetur = alasanRetur;
	}
	
	
	
}
