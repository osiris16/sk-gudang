package org.radot.json.beans;

import java.math.BigDecimal;

import org.radot.json.servlet.JsonParam;

import com.google.gson.annotations.Expose;

public class ProductInputParam extends JsonParam {
	
	@Expose
	private String code;
	@Expose
	private String name;
	@Expose
	private String merk;
	@Expose
	private String madeIn;
	@Expose
	private String partNumb;
	@Expose
	private String standartNo;
	@Expose
	private String barcode;
	@Expose
	private String productgroupEnt;
	@Expose
	private String image;
	@Expose
	private BigDecimal stokCtn;
	@Expose
	private BigDecimal stokCtnRetail;
	@Expose
	private BigDecimal stokCtnDeptStore;
	@Expose
	private BigDecimal isiCtn;
	@Expose
	private String satIsiCtn;
	@Expose
	private BigDecimal isiPcs;
	@Expose
	private BigDecimal totStockPcs;
	@Expose
	private BigDecimal hargaJualStdCtn;

	
	public BigDecimal getStokCtnRetail() {
		return stokCtnRetail;
	}
	public void setStokCtnRetail(BigDecimal stokCtnRetail) {
		this.stokCtnRetail = stokCtnRetail;
	}
	public BigDecimal getStokCtnDeptStore() {
		return stokCtnDeptStore;
	}
	public void setStokCtnDeptStore(BigDecimal stokCtnDeptStore) {
		this.stokCtnDeptStore = stokCtnDeptStore;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMerk() {
		return merk;
	}
	public void setMerk(String merk) {
		this.merk = merk;
	}
	public String getMadeIn() {
		return madeIn;
	}
	public void setMadeIn(String madeIn) {
		this.madeIn = madeIn;
	}
	public String getPartNumb() {
		return partNumb;
	}
	public void setPartNumb(String partNumb) {
		this.partNumb = partNumb;
	}
	public String getStandartNo() {
		return standartNo;
	}
	public void setStandartNo(String standartNo) {
		this.standartNo = standartNo;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getProductgroupEnt() {
		return productgroupEnt;
	}
	public void setProductgroupEnt(String productgroupEnt) {
		this.productgroupEnt = productgroupEnt;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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
	public BigDecimal getTotStockPcs() {
		return totStockPcs;
	}
	public void setTotStockPcs(BigDecimal totStockPcs) {
		this.totStockPcs = totStockPcs;
	}
	public BigDecimal getStokCtn() {
		return stokCtn;
	}
	public void setStokCtn(BigDecimal stokCtn) {
		this.stokCtn = stokCtn;
	}
	public BigDecimal getHargaJualStdCtn() {
		return hargaJualStdCtn;
	}
	public void setHargaJualStdCtn(BigDecimal hargaJualStdCtn) {
		this.hargaJualStdCtn = hargaJualStdCtn;
	}
	
	
	
}
