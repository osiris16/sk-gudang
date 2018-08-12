package org.radot.hibernate.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name="Detail_beli")
@Table(name="sk_detail_beli")
public class DetailPembelianEntity extends BaseEntity {


	/**
	 * 
	 */
	private static final long serialVersionUID = -9215515866691701660L;

	/**
	 * 
	 */
	
	
	@JoinColumn(name="Stock_fk")
	@OneToOne()
	private StockEntity StockEnt;
	
	@JoinColumn(name="Trip_fk")
	@OneToOne()
	private TripEntity tripEnt;
	
	@Column(name="qty_beli_ctn")
	private BigDecimal qtyBeliCtn;
	
	@Column(name="disc")
	private BigDecimal disc;
	
	@Column(name="cost")
	private BigDecimal cost;
	
	@Column(name="hargaJualCtnNew")
	private BigDecimal hargaJ_CtnNew;
	
	@Column(name="hargaJualCtnOld")
	private BigDecimal hargaJ_CtnOld;
	
	@Column(name="hargaBeli_CtnIdr")
	private BigDecimal hargaBeliCtnIdr;
	
	@Column(name="hargaBeli_CtnVta")
	private BigDecimal hargaBeliCtnVta;
	
	@Column(name="totHargaBrut_B_Idr")
	private BigDecimal totHargaBrutB_Idr;

	@Column(name="totHargaBrut_B_Vta")
	private BigDecimal totHargaBrutB_Vta;
	
	@Column(name="totHargaNett_B_Idr")
	private BigDecimal totHargaNettB_Idr;
	
	@Column(name="totHargaNett_B_Vta")
	private BigDecimal totHargaNettB_Vta;
	
	@Column(name="tripNum_Seq_Stock")
	private String tripNumSeqStock;
	
	@Column(name="tripNum_Seq_Stock_History")
	private String tripNumSeqStockHist;

	public StockEntity getStockEnt() {
		return StockEnt;
	}

	public void setStockEnt(StockEntity stockEnt) {
		StockEnt = stockEnt;
	}

	public TripEntity getTripEnt() {
		return tripEnt;
	}

	public void setTripEnt(TripEntity tripEnt) {
		this.tripEnt = tripEnt;
	}

	public BigDecimal getQtyBeliCtn() {
		return qtyBeliCtn;
	}

	public void setQtyBeliCtn(BigDecimal qtyBeliCtn) {
		this.qtyBeliCtn = qtyBeliCtn;
	}

	public BigDecimal getDisc() {
		return disc;
	}

	public void setDisc(BigDecimal disc) {
		this.disc = disc;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public BigDecimal getHargaJ_CtnNew() {
		return hargaJ_CtnNew;
	}

	public void setHargaJ_CtnNew(BigDecimal hargaJ_CtnNew) {
		this.hargaJ_CtnNew = hargaJ_CtnNew;
	}

	public BigDecimal getHargaJ_CtnOld() {
		return hargaJ_CtnOld;
	}

	public void setHargaJ_CtnOld(BigDecimal hargaJ_CtnOld) {
		this.hargaJ_CtnOld = hargaJ_CtnOld;
	}

	public BigDecimal getHargaBeliCtnIdr() {
		return hargaBeliCtnIdr;
	}

	public void setHargaBeliCtnIdr(BigDecimal hargaBeliCtnIdr) {
		this.hargaBeliCtnIdr = hargaBeliCtnIdr;
	}

	public BigDecimal getHargaBeliCtnVta() {
		return hargaBeliCtnVta;
	}

	public void setHargaBeliCtnVta(BigDecimal hargaBeliCtnVta) {
		this.hargaBeliCtnVta = hargaBeliCtnVta;
	}

	public BigDecimal getTotHargaBrutB_Idr() {
		return totHargaBrutB_Idr;
	}

	public void setTotHargaBrutB_Idr(BigDecimal totHargaBrutB_Idr) {
		this.totHargaBrutB_Idr = totHargaBrutB_Idr;
	}

	public BigDecimal getTotHargaBrutB_Vta() {
		return totHargaBrutB_Vta;
	}

	public void setTotHargaBrutB_Vta(BigDecimal totHargaBrutB_Vta) {
		this.totHargaBrutB_Vta = totHargaBrutB_Vta;
	}

	public BigDecimal getTotHargaNettB_Idr() {
		return totHargaNettB_Idr;
	}

	public void setTotHargaNettB_Idr(BigDecimal totHargaNettB_Idr) {
		this.totHargaNettB_Idr = totHargaNettB_Idr;
	}

	public BigDecimal getTotHargaNettB_Vta() {
		return totHargaNettB_Vta;
	}

	public void setTotHargaNettB_Vta(BigDecimal totHargaNettB_Vta) {
		this.totHargaNettB_Vta = totHargaNettB_Vta;
	}

	public String getTripNumSeqStock() {
		return tripNumSeqStock;
	}

	public void setTripNumSeqStock(String tripNumSeqStock) {
		this.tripNumSeqStock = tripNumSeqStock;
	}

	public String getTripNumSeqStockHist() {
		return tripNumSeqStockHist;
	}

	public void setTripNumSeqStockHist(String tripNumSeqStockHist) {
		this.tripNumSeqStockHist = tripNumSeqStockHist;
	}
	
	
	
}
