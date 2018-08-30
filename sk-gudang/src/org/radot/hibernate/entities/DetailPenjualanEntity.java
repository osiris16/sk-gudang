package org.radot.hibernate.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity(name="Detail_jual")
@Table(name="sk_detail_jual")
public class DetailPenjualanEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5639233679320035225L;

	@JoinColumn(name="penjualan_fk")
	@OneToOne()
	private PenjualanEntity penjualanEnt;

	@JoinColumn(name="stock_fk")
	@OneToOne()
	private StockEntity stockEnt;
	
	@Column(name="kode_order_jual")
	private  String kodeOrderJual;
	
	@Column(name="TotQtyJ_Ctn")
	private BigDecimal totQtyJualCtn;
	
	@Column(name="TotQtyJ_Pcs")
	private BigDecimal totQtyJualPcs;
	
	@Column(name="hj_Ctn")
	private BigDecimal hjCtn;
	
	@Column(name="tot_jual_bruto_idr")
	private BigDecimal totJualBrutoIdr;
	
	@Column(name="disc_persen")
	private BigDecimal discPersen;

	@Column(name="tot_jual_netto_idr")
	private BigDecimal totJualNettoIdr;
	
	@Column(name="keterangan")
	private String Keterangan;

	@Column(name="TypeGrosiRetail")
	private String typeGrosiRetail;
	
	
	public PenjualanEntity getPenjualanEnt() {
		return penjualanEnt;
	}

	public void setPenjualanEnt(PenjualanEntity penjualanEnt) {
		this.penjualanEnt = penjualanEnt;
	}

	public StockEntity getStockEnt() {
		return stockEnt;
	}

	public void setStockEnt(StockEntity stockEnt) {
		this.stockEnt = stockEnt;
	}

	public String getKodeOrderJual() {
		return kodeOrderJual;
	}

	public void setKodeOrderJual(String kodeOrderJual) {
		this.kodeOrderJual = kodeOrderJual;
	}

	public BigDecimal getTotQtyJualCtn() {
		return totQtyJualCtn;
	}

	public void setTotQtyJualCtn(BigDecimal totQtyJualCtn) {
		this.totQtyJualCtn = totQtyJualCtn;
	}

	public BigDecimal getTotQtyJualPcs() {
		return totQtyJualPcs;
	}

	public void setTotQtyJualPcs(BigDecimal totQtyJualPcs) {
		this.totQtyJualPcs = totQtyJualPcs;
	}

	public BigDecimal getHjCtn() {
		return hjCtn;
	}

	public void setHjCtn(BigDecimal hjCtn) {
		this.hjCtn = hjCtn;
	}

	public BigDecimal getTotJualBrutoIdr() {
		return totJualBrutoIdr;
	}

	public void setTotJualBrutoIdr(BigDecimal totJualBrutoIdr) {
		this.totJualBrutoIdr = totJualBrutoIdr;
	}

	public BigDecimal getDiscPersen() {
		return discPersen;
	}

	public void setDiscPersen(BigDecimal discPersen) {
		this.discPersen = discPersen;
	}

	public BigDecimal getTotJualNettoIdr() {
		return totJualNettoIdr;
	}

	public void setTotJualNettoIdr(BigDecimal totJualNettoIdr) {
		this.totJualNettoIdr = totJualNettoIdr;
	}

	public String getKeterangan() {
		return Keterangan;
	}

	public void setKeterangan(String keterangan) {
		Keterangan = keterangan;
	}

	public String getTypeGrosiRetail() {
		return typeGrosiRetail;
	}

	public void setTypeGrosiRetail(String typeGrosiRetail) {
		this.typeGrosiRetail = typeGrosiRetail;
	}

	
	
}
