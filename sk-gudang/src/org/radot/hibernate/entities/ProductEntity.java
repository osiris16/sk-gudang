package org.radot.hibernate.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name="Product")
@Table(name="sk_product")
public class ProductEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7249876829637306471L;

	@Column(name="prod_id")
	private String prodId;
	
	@Column(name="prod_code", length=32)
	private String code;
	
	@Column(name="prod_name", length=128)
	private String name;
	
	@Column(name="prod_made_In", length=128)
	private String madeIn;
	
	@Column(name="prod_merk", length=128)
	private String merk;
	
	@Column(name="prod_partNumb", length=128)
	private String partNumb;
	
	@Column(name="prod_standartNo", length=128)
	private String standartNo;
	
	@Column(name="prod_barcode", length=128)
	private String barcode;
	
	@JoinColumn(name="prod_grp_fk")
	@OneToOne()
	private ProductGroupEntity productGroupEnt;
	
	@Lob()
	@Column(name="prod_img")
	private String image;
	
	@Column(name="isi_ctn")
	private BigDecimal isiCtn;
	
	@Column(name="sat_isi_ctn")
	private String satIsiCtn;
	
	@Column(name="isi_pcs")
	private BigDecimal isiPcs;
	
	@Column(name="hjStd_Ctn")
	private BigDecimal hJstdCtn;

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
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

	public String getMadeIn() {
		return madeIn;
	}

	public void setMadeIn(String madeIn) {
		this.madeIn = madeIn;
	}

	public String getMerk() {
		return merk;
	}

	public void setMerk(String merk) {
		this.merk = merk;
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

	public ProductGroupEntity getProductGroupEnt() {
		return productGroupEnt;
	}

	public void setProductGroupEnt(ProductGroupEntity productGroupEnt) {
		this.productGroupEnt = productGroupEnt;
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

	public BigDecimal getHJstdCtn() {
		return hJstdCtn;
	}

	public void setHJstdCtn(BigDecimal hJstdCtn) {
		this.hJstdCtn = hJstdCtn;
	}

	
	

	
}
