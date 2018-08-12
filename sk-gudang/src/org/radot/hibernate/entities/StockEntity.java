package org.radot.hibernate.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name="Stock")
@Table(name="sk_stock")
public class StockEntity extends BaseEntity {

	/**
	 * 
	 */

	private static final long serialVersionUID = -6897251049955540869L;

	@JoinColumn(name="product_fk")
	@OneToOne()
	private ProductEntity productEnt;
	
	@Column(name="stok_ctn")
	private BigDecimal stokCtn;
	
	@Column(name="stok_ctn_retail")
	private BigDecimal stokCtnRetail;
	
	@Column(name="stok_ctn_deptStore")
	private BigDecimal stokCtn_grosir;
	
	@Column(name="trip_num_stok")
	private String tripNumStok;
	
	@Column(name="trip_date_stok")
	private Date tripDateStok;

	public ProductEntity getProductEnt() {
		return productEnt;
	}

	public void setProductEnt(ProductEntity productEnt) {
		this.productEnt = productEnt;
	}

	public BigDecimal getStokCtn() {
		return stokCtn;
	}

	public void setStokCtn(BigDecimal stokCtn) {
		this.stokCtn = stokCtn;
	}

	public BigDecimal getStokCtnRetail() {
		return stokCtnRetail;
	}

	public void setStokCtnRetail(BigDecimal stokCtnRetail) {
		this.stokCtnRetail = stokCtnRetail;
	}

	public BigDecimal getStokCtn_grosir() {
		return stokCtn_grosir;
	}

	public void setStokCtn_grosir(BigDecimal stokCtn_grosir) {
		this.stokCtn_grosir = stokCtn_grosir;
	}

	public String getTripNumStok() {
		return tripNumStok;
	}

	public void setTripNumStok(String tripNumStok) {
		this.tripNumStok = tripNumStok;
	}

	public Date getTripDateStok() {
		return tripDateStok;
	}

	public void setTripDateStok(Date tripDateStok) {
		this.tripDateStok = tripDateStok;
	}

	
	

	
}
