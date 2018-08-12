package org.radot.json.beans;

import java.math.BigDecimal;
import java.util.Date;

import org.radot.json.servlet.JsonParam;

import com.google.gson.annotations.Expose;

public class DetPembelianInputParam extends JsonParam {
	
	@Expose
	private String tripEnt;
	@Expose
	private String tripNumb;
	@Expose
	private Long tripDate;
	@Expose
	private String stockEnt;
	@Expose
	private String prodEnt;
	@Expose
	private BigDecimal stockAwal;
	@Expose
	private BigDecimal totStockPcs;
	@Expose
	private BigDecimal cost;
	@Expose
	private BigDecimal disc;
	@Expose
	private BigDecimal totDisc;
	@Expose
	private BigDecimal totCost;
	@Expose
	private BigDecimal hargaBPcsIdr;
	@Expose
	private BigDecimal hargaBPcsVta;
	@Expose
	private BigDecimal hargaJPcs;
	@Expose
	private BigDecimal qtyBeliPcs;
	@Expose
	private BigDecimal qtyBeliCtn;
	@Expose
	private BigDecimal totHargaBrutBeliIdr;
	@Expose
	private BigDecimal totHargaBrutBeliVta;
	@Expose
	private BigDecimal totHargaNettBeliIdr;
	@Expose
	private BigDecimal totHargaNettBeliVta;
	@Expose 
	private String RetailORDept;
	
	public String getRetailORDept() {
		return RetailORDept;
	}
	public void setRetailORDept(String retailORDept) {
		RetailORDept = retailORDept;
	}
	public String getTripEnt() {
		return tripEnt;
	}
	public void setTripEnt(String tripEnt) {
		this.tripEnt = tripEnt;
	}
	public String getTripNumb() {
		return tripNumb;
	}
	public void setTripNumb(String tripNumb) {
		this.tripNumb = tripNumb;
	}
	public Long getTripDate() {
		return tripDate;
	}
	public void setTripDate(Long tripDate) {
		this.tripDate = tripDate;
	}
	public String getStockEnt() {
		return stockEnt;
	}
	public void setStockEnt(String stockEnt) {
		this.stockEnt = stockEnt;
	}
	public String getProdEnt() {
		return prodEnt;
	}
	public void setProdEnt(String prodEnt) {
		this.prodEnt = prodEnt;
	}
	public BigDecimal getStockAwal() {
		return stockAwal;
	}
	public void setStockAwal(BigDecimal stockAwal) {
		this.stockAwal = stockAwal;
	}
	public BigDecimal getTotStockPcs() {
		return totStockPcs;
	}
	public void setTotStockPcs(BigDecimal totStockPcs) {
		this.totStockPcs = totStockPcs;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	public BigDecimal getDisc() {
		return disc;
	}
	public void setDisc(BigDecimal disc) {
		this.disc = disc;
	}
	public BigDecimal getTotDisc() {
		return totDisc;
	}
	public void setTotDisc(BigDecimal totDisc) {
		this.totDisc = totDisc;
	}
	public BigDecimal getTotCost() {
		return totCost;
	}
	public void setTotCost(BigDecimal totCost) {
		this.totCost = totCost;
	}
	public BigDecimal getHargaBPcsIdr() {
		return hargaBPcsIdr;
	}
	public void setHargaBPcsIdr(BigDecimal hargaBPcsIdr) {
		this.hargaBPcsIdr = hargaBPcsIdr;
	}
	public BigDecimal getHargaBPcsVta() {
		return hargaBPcsVta;
	}
	public void setHargaBPcsVta(BigDecimal hargaBPcsVta) {
		this.hargaBPcsVta = hargaBPcsVta;
	}
	public BigDecimal getHargaJPcs() {
		return hargaJPcs;
	}
	public void setHargaJPcs(BigDecimal hargaJPcs) {
		this.hargaJPcs = hargaJPcs;
	}
	public BigDecimal getQtyBeliPcs() {
		return qtyBeliPcs;
	}
	public void setQtyBeliPcs(BigDecimal qtyBeliPcs) {
		this.qtyBeliPcs = qtyBeliPcs;
	}
	public BigDecimal getQtyBeliCtn() {
		return qtyBeliCtn;
	}
	public void setQtyBeliCtn(BigDecimal qtyBeliCtn) {
		this.qtyBeliCtn = qtyBeliCtn;
	}
	public BigDecimal getTotHargaBrutBeliIdr() {
		return totHargaBrutBeliIdr;
	}
	public void setTotHargaBrutBeliIdr(BigDecimal totHargaBrutBeliIdr) {
		this.totHargaBrutBeliIdr = totHargaBrutBeliIdr;
	}
	public BigDecimal getTotHargaBrutBeliVta() {
		return totHargaBrutBeliVta;
	}
	public void setTotHargaBrutBeliVta(BigDecimal totHargaBrutBeliVta) {
		this.totHargaBrutBeliVta = totHargaBrutBeliVta;
	}
	public BigDecimal getTotHargaNettBeliIdr() {
		return totHargaNettBeliIdr;
	}
	public void setTotHargaNettBeliIdr(BigDecimal totHargaNettBeliIdr) {
		this.totHargaNettBeliIdr = totHargaNettBeliIdr;
	}
	public BigDecimal getTotHargaNettBeliVta() {
		return totHargaNettBeliVta;
	}
	public void setTotHargaNettBeliVta(BigDecimal totHargaNettBeliVta) {
		this.totHargaNettBeliVta = totHargaNettBeliVta;
	}
	
	
}
