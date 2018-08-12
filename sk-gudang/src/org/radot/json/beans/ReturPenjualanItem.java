package org.radot.json.beans;

import java.math.BigDecimal;
import java.util.Date;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author Iman
 *
 */
public class ReturPenjualanItem {
	
	@Expose
	private Long id;
	@Expose
	private String noRetur;
	@Expose
	private Date dateRetur;
	@Expose
	private Long stockId;
	@Expose
	private Long penjualanId;
	@Expose
	private Long orderId;
	@Expose
	private Long customerId;
	@Expose
	private Long salesmanId;
	@Expose
	private String customerCode;
	@Expose
	private String customerName;
	@Expose
	private String customerAddr;
	@Expose
	private String customerCity;
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
	private BigDecimal totQtyJualPcs;
	@Expose
	private BigDecimal disc;
	@Expose
	private BigDecimal hargaJualPcs;
	@Expose
	private BigDecimal nilaiReturPcs;
	@Expose
	private BigDecimal totQtyReturPcs;
	@Expose
	private BigDecimal hargaJualCtn;
	@Expose
	private BigDecimal hargaJualCtnBruto;
	@Expose
	private BigDecimal nilaiNettoRetur;
	@Expose
	private BigDecimal nilaiBrutoRetur;
	@Expose
	private BigDecimal totQtyReturCtn;
	@Expose
	private String penerimaRetur;
	@Expose
	private String alasanRetur;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNoRetur() {
		return noRetur;
	}
	public void setNoRetur(String noRetur) {
		this.noRetur = noRetur;
	}
	public Date getDateRetur() {
		return dateRetur;
	}
	public void setDateRetur(Date dateRetur) {
		this.dateRetur = dateRetur;
	}
	public Long getStockId() {
		return stockId;
	}
	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}
	public Long getPenjualanId() {
		return penjualanId;
	}
	public void setPenjualanId(Long penjualanId) {
		this.penjualanId = penjualanId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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
	public String getCustomerAddr() {
		return customerAddr;
	}
	public void setCustomerAddr(String customerAddr) {
		this.customerAddr = customerAddr;
	}
	public String getCustomerCity() {
		return customerCity;
	}
	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
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
	public BigDecimal getTotQtyJualPcs() {
		return totQtyJualPcs;
	}
	public void setTotQtyJualPcs(BigDecimal totQtyJualPcs) {
		this.totQtyJualPcs = totQtyJualPcs;
	}
	public BigDecimal getDisc() {
		return disc;
	}
	public void setDisc(BigDecimal disc) {
		this.disc = disc;
	}
	public BigDecimal getHargaJualPcs() {
		return hargaJualPcs;
	}
	public void setHargaJualPcs(BigDecimal hargaJualPcs) {
		this.hargaJualPcs = hargaJualPcs;
	}
	public BigDecimal getNilaiReturPcs() {
		return nilaiReturPcs;
	}
	public void setNilaiReturPcs(BigDecimal nilaiReturPcs) {
		this.nilaiReturPcs = nilaiReturPcs;
	}
	public BigDecimal getTotQtyReturPcs() {
		return totQtyReturPcs;
	}
	public void setTotQtyReturPcs(BigDecimal totQtyReturPcs) {
		this.totQtyReturPcs = totQtyReturPcs;
	}
	public BigDecimal getHargaJualCtn() {
		return hargaJualCtn;
	}
	public void setHargaJualCtn(BigDecimal hargaJualCtn) {
		this.hargaJualCtn = hargaJualCtn;
	}
	public BigDecimal getHargaJualCtnBruto() {
		return hargaJualCtnBruto;
	}
	public void setHargaJualCtnBruto(BigDecimal hargaJualCtnBruto) {
		this.hargaJualCtnBruto = hargaJualCtnBruto;
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
	public BigDecimal getTotQtyReturCtn() {
		return totQtyReturCtn;
	}
	public void setTotQtyReturCtn(BigDecimal totQtyReturCtn) {
		this.totQtyReturCtn = totQtyReturCtn;
	}
	public String getPenerimaRetur() {
		return penerimaRetur;
	}
	public void setPenerimaRetur(String penerimaRetur) {
		this.penerimaRetur = penerimaRetur;
	}
	public String getAlasanRetur() {
		return alasanRetur;
	}
	public void setAlasanRetur(String alasanRetur) {
		this.alasanRetur = alasanRetur;
	}
	
	
	
}
