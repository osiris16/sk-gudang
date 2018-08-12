package org.radot.json.beans;

import java.math.BigDecimal;

import org.radot.json.servlet.JsonParam;

import com.google.gson.annotations.Expose;

public class StockInputParam extends JsonParam {
	
	@Expose
	private Long id;
	@Expose
	private BigDecimal stokCtn;
	@Expose
	private BigDecimal productIsiCtn;
	@Expose
	private String productSatIsiCtn;
	@Expose
	private BigDecimal productIsiPcs;
	@Expose
	private BigDecimal totStokPcs;
	@Expose
	private String productEnt;
	@Expose
	private String productCode;
	@Expose
	private String productName;
	@Expose
	private String productMerk;
	@Expose
	private String productGroup;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getStokCtn() {
		return stokCtn;
	}
	public void setStokCtn(BigDecimal stokCtn) {
		this.stokCtn = stokCtn;
	}
	public BigDecimal getProductIsiCtn() {
		return productIsiCtn;
	}
	public void setProductIsiCtn(BigDecimal productIsiCtn) {
		this.productIsiCtn = productIsiCtn;
	}
	public String getProductSatIsiCtn() {
		return productSatIsiCtn;
	}
	public void setProductSatIsiCtn(String productSatIsiCtn) {
		this.productSatIsiCtn = productSatIsiCtn;
	}
	public BigDecimal getProductIsiPcs() {
		return productIsiPcs;
	}
	public void setProductIsiPcs(BigDecimal productIsiPcs) {
		this.productIsiPcs = productIsiPcs;
	}
	public BigDecimal getTotStokPcs() {
		return totStokPcs;
	}
	public void setTotStokPcs(BigDecimal totStokPcs) {
		this.totStokPcs = totStokPcs;
	}
	public String getProductEnt() {
		return productEnt;
	}
	public void setProductEnt(String productEnt) {
		this.productEnt = productEnt;
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
	
	
	
}
