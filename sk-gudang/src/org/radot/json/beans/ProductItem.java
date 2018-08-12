package org.radot.json.beans;

import java.math.BigDecimal;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author Iman
 *
 */
public class ProductItem {
	@Expose
	private Long id;
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
	private String StandartNo;
	@Expose
	private String Barcode;
	@Expose
	private String group;
	@Expose
	private String image;
	@Expose
	private BigDecimal isiCtn;
	@Expose
	private String satIsiCtn;
	@Expose
	private BigDecimal isiPcs;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
		return StandartNo;
	}
	public void setStandartNo(String standartNo) {
		StandartNo = standartNo;
	}
	public String getBarcode() {
		return Barcode;
	}
	public void setBarcode(String barcode) {
		Barcode = barcode;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
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
	
}
