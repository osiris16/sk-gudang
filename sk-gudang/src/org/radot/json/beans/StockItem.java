package org.radot.json.beans;

import java.math.BigDecimal;
import java.util.Date;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author Iman
 *
 */
public class StockItem {
	@Expose
	private Long stockId;
	@Expose
	private Long prodId;
	@Expose
	private Long prodGroupId;
	@Expose
	private String productCode;
	@Expose
	private String productName;
	@Expose
	private String productMerk;
	@Expose
	private String productGroup;
	@Expose
	private BigDecimal stokCtn;
	@Expose
	private BigDecimal stokIsiCtn;
	@Expose
	private BigDecimal stokIsiCtnRetail;
	@Expose
	private BigDecimal stokIsiCtnDeptStore;
	@Expose
	private BigDecimal isiCtn;
	@Expose
	private String satIsiCtn;
	@Expose
	private BigDecimal isiPcs;
	@Expose
	private BigDecimal totStokPcs;
	@Expose
	private String productMadeIn;
	@Expose
	private String productPartNumb;
	@Expose
	private String productStandartNo;
	@Expose
	private String productBarcode;
	@Expose
	private String productImage;
	@Expose
	private BigDecimal hargaJualIsiCtn;
	@Expose
	private BigDecimal hargaJualCtn;
	@Expose
	private BigDecimal hargaJualPcs;
	@Expose
	private String tripNumbStok;
	@Expose
	private Date tripDateStok;
	
	
	
	
	
	public BigDecimal getStokIsiCtnRetail() {
		return stokIsiCtnRetail;
	}
	public void setStokIsiCtnRetail(BigDecimal stokIsiCtnRetail) {
		this.stokIsiCtnRetail = stokIsiCtnRetail;
	}
	public BigDecimal getStokIsiCtnDeptStore() {
		return stokIsiCtnDeptStore;
	}
	public void setStokIsiCtnDeptStore(BigDecimal stokIsiCtnDeptStore) {
		this.stokIsiCtnDeptStore = stokIsiCtnDeptStore;
	}
	public Long getStockId() {
		return stockId;
	}
	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}
	public Long getProdId() {
		return prodId;
	}
	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}
	public Long getProdGroupId() {
		return prodGroupId;
	}
	public void setProdGroupId(Long prodGroupId) {
		this.prodGroupId = prodGroupId;
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
	public String getProductMerk() {
		return productMerk;
	}
	public void setProductMerk(String productMerk) {
		this.productMerk = productMerk;
	}
	public String getProductGroup() {
		return productGroup;
	}
	public void setProductGroup(String productGroup) {
		this.productGroup = productGroup;
	}
	public BigDecimal getStokCtn() {
		return stokCtn;
	}
	public void setStokCtn(BigDecimal stokCtn) {
		this.stokCtn = stokCtn;
	}
	public BigDecimal getStokIsiCtn() {
		return stokIsiCtn;
	}
	public void setStokIsiCtn(BigDecimal stokIsiCtn) {
		this.stokIsiCtn = stokIsiCtn;
	}
	public BigDecimal getIsiCtn() {
		return isiCtn;
	}
	public void setIsiCtn(BigDecimal isiCtn) {
		this.isiCtn = isiCtn;
	}
	public String getSatIsiCtn() {
		return satIsiCtn;
	}
	public void setSatIsiCtn(String satIsiCtn) {
		this.satIsiCtn = satIsiCtn;
	}
	public BigDecimal getIsiPcs() {
		return isiPcs;
	}
	public void setIsiPcs(BigDecimal isiPcs) {
		this.isiPcs = isiPcs;
	}
	public BigDecimal getTotStokPcs() {
		return totStokPcs;
	}
	public void setTotStokPcs(BigDecimal totStokPcs) {
		this.totStokPcs = totStokPcs;
	}
	public String getProductMadeIn() {
		return productMadeIn;
	}
	public void setProductMadeIn(String productMadeIn) {
		this.productMadeIn = productMadeIn;
	}
	public String getProductPartNumb() {
		return productPartNumb;
	}
	public void setProductPartNumb(String productPartNumb) {
		this.productPartNumb = productPartNumb;
	}
	public String getProductStandartNo() {
		return productStandartNo;
	}
	public void setProductStandartNo(String productStandartNo) {
		this.productStandartNo = productStandartNo;
	}
	public String getProductBarcode() {
		return productBarcode;
	}
	public void setProductBarcode(String productBarcode) {
		this.productBarcode = productBarcode;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public BigDecimal getHargaJualIsiCtn() {
		return hargaJualIsiCtn;
	}
	public void setHargaJualIsiCtn(BigDecimal hargaJualIsiCtn) {
		this.hargaJualIsiCtn = hargaJualIsiCtn;
	}
	public BigDecimal getHargaJualCtn() {
		return hargaJualCtn;
	}
	public void setHargaJualCtn(BigDecimal hargaJualCtn) {
		this.hargaJualCtn = hargaJualCtn;
	}
	public BigDecimal getHargaJualPcs() {
		return hargaJualPcs;
	}
	public void setHargaJualPcs(BigDecimal hargaJualPcs) {
		this.hargaJualPcs = hargaJualPcs;
	}
	public String getTripNumbStok() {
		return tripNumbStok;
	}
	public void setTripNumbStok(String tripNumbStok) {
		this.tripNumbStok = tripNumbStok;
	}
	public Date getTripDateStok() {
		return tripDateStok;
	}
	public void setTripDateStok(Date tripDateStok) {
		this.tripDateStok = tripDateStok;
	}
	
	
	
	
	
	
}
