package org.radot.json.beans;

import java.math.BigDecimal;
import java.util.Date;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author Iman
 *
 */
public class DetPenjualanItem {
	
	@Expose
	private Long id;
	@Expose
	private Long prodId;
	@Expose
	private Long stockId;
	@Expose
	private Long penjualanId;
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
	private BigDecimal totQtyJualCtn;
	@Expose
	private BigDecimal disc;
	@Expose
	private BigDecimal ppn;
	@Expose
	private BigDecimal hargaJualPcs;
	@Expose
	private BigDecimal hargaJualPcsDisc;
	@Expose
	private BigDecimal hargaJualPcsNetto;
	@Expose
	private BigDecimal totJualBrutoIdr;
	@Expose
	private BigDecimal totJualNettoIdr;
	@Expose
	private BigDecimal totJualNettoIdrBeforePpn;
	@Expose
	private BigDecimal hargaJualCtn;
	@Expose
	private BigDecimal hargaJualCtnStd;
	@Expose
	private BigDecimal hargaJualNettoCtn;
	@Expose
	private BigDecimal hargaJualBrutoCtn;
	@Expose
	private BigDecimal hargaJualIsiCtn;
	@Expose
	private BigDecimal totPenjualanFaktur;
	@Expose
	private Long datePenjualan;
	@Expose
	private Long datePenjualanKirim;
	@Expose
	private BigDecimal sisaStokEdit;
	@Expose
	private BigDecimal isiPcs;
	@Expose
	private BigDecimal isiCtn;
	@Expose
	private String keterangan;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProdId() {
		return prodId;
	}
	public void setProdId(Long prodId) {
		this.prodId = prodId;
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
	public BigDecimal getPpn() {
		return ppn;
	}
	public void setPpn(BigDecimal ppn) {
		this.ppn = ppn;
	}
	public BigDecimal getHargaJualPcs() {
		return hargaJualPcs;
	}
	public void setHargaJualPcs(BigDecimal hargaJualPcs) {
		this.hargaJualPcs = hargaJualPcs;
	}
	public BigDecimal getHargaJualPcsDisc() {
		return hargaJualPcsDisc;
	}
	public void setHargaJualPcsDisc(BigDecimal hargaJualPcsDisc) {
		this.hargaJualPcsDisc = hargaJualPcsDisc;
	}
	public BigDecimal getHargaJualPcsNetto() {
		return hargaJualPcsNetto;
	}
	public void setHargaJualPcsNetto(BigDecimal hargaJualPcsNetto) {
		this.hargaJualPcsNetto = hargaJualPcsNetto;
	}
	public BigDecimal getTotJualBrutoIdr() {
		return totJualBrutoIdr;
	}
	public void setTotJualBrutoIdr(BigDecimal totJualBrutoIdr) {
		this.totJualBrutoIdr = totJualBrutoIdr;
	}
	public BigDecimal getTotJualNettoIdr() {
		return totJualNettoIdr;
	}
	public void setTotJualNettoIdr(BigDecimal totJualNettoIdr) {
		this.totJualNettoIdr = totJualNettoIdr;
	}
	public BigDecimal getTotJualNettoIdrBeforePpn() {
		return totJualNettoIdrBeforePpn;
	}
	public void setTotJualNettoIdrBeforePpn(BigDecimal totJualNettoIdrBeforePpn) {
		this.totJualNettoIdrBeforePpn = totJualNettoIdrBeforePpn;
	}
	public BigDecimal getHargaJualCtn() {
		return hargaJualCtn;
	}
	public void setHargaJualCtn(BigDecimal hargaJualCtn) {
		this.hargaJualCtn = hargaJualCtn;
	}
	public BigDecimal getHargaJualCtnStd() {
		return hargaJualCtnStd;
	}
	public void setHargaJualCtnStd(BigDecimal hargaJualCtnStd) {
		this.hargaJualCtnStd = hargaJualCtnStd;
	}
	public BigDecimal getHargaJualNettoCtn() {
		return hargaJualNettoCtn;
	}
	public void setHargaJualNettoCtn(BigDecimal hargaJualNettoCtn) {
		this.hargaJualNettoCtn = hargaJualNettoCtn;
	}
	public BigDecimal getHargaJualBrutoCtn() {
		return hargaJualBrutoCtn;
	}
	public void setHargaJualBrutoCtn(BigDecimal hargaJualBrutoCtn) {
		this.hargaJualBrutoCtn = hargaJualBrutoCtn;
	}
	public BigDecimal getHargaJualIsiCtn() {
		return hargaJualIsiCtn;
	}
	public void setHargaJualIsiCtn(BigDecimal hargaJualIsiCtn) {
		this.hargaJualIsiCtn = hargaJualIsiCtn;
	}
	public BigDecimal getTotPenjualanFaktur() {
		return totPenjualanFaktur;
	}
	public void setTotPenjualanFaktur(BigDecimal totPenjualanFaktur) {
		this.totPenjualanFaktur = totPenjualanFaktur;
	}
	public Long getDatePenjualan() {
		return datePenjualan;
	}
	public void setDatePenjualan(Long datePenjualan) {
		this.datePenjualan = datePenjualan;
	}
	public Long getDatePenjualanKirim() {
		return datePenjualanKirim;
	}
	public void setDatePenjualanKirim(Long datePenjualanKirim) {
		this.datePenjualanKirim = datePenjualanKirim;
	}
	public BigDecimal getSisaStokEdit() {
		return sisaStokEdit;
	}
	public void setSisaStokEdit(BigDecimal sisaStokEdit) {
		this.sisaStokEdit = sisaStokEdit;
	}
	public BigDecimal getIsiPcs() {
		return isiPcs;
	}
	public void setIsiPcs(BigDecimal isiPcs) {
		this.isiPcs = isiPcs;
	}
	public BigDecimal getIsiCtn() {
		return isiCtn;
	}
	public void setIsiCtn(BigDecimal isiCtn) {
		this.isiCtn = isiCtn;
	}
	public String getKeterangan() {
		return keterangan;
	}
	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}
	
	
	
	
	
}
