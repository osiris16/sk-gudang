package org.radot.json.beans;

import java.math.BigDecimal;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author Iman
 *
 */
public class DetPembelianItem {
	
	@Expose
	private Long id;
	@Expose
	private Long idStock;
	@Expose
	private Long idTrip;
	@Expose
	private String productImage;
	@Expose
	private String productCode;
	@Expose
	private String productName;
	@Expose
	private String vendName;
	@Expose
	private String vendCountry;
	@Expose
	private String vendVta;
	@Expose
	private BigDecimal tripKurs;
	@Expose
	private java.util.Date tripDate;
	@Expose
	private String tripNumber;
	@Expose
	private String tripNumbStok;
	@Expose
	private BigDecimal stokCtn;
	@Expose
	private BigDecimal isiCtn;
	@Expose
	private String satIsiCtn;
	@Expose
	private BigDecimal isiPcs;
	@Expose
	private BigDecimal totStokPcs;
	@Expose
	private BigDecimal totQtyBeliCtn;
	@Expose
	private BigDecimal totQtyBeliPcs;
	@Expose
	private BigDecimal disc;
	@Expose
	private BigDecimal cost;
	@Expose
	private BigDecimal hargaJualCtn;
	@Expose
	private BigDecimal hargaJualIsiCtn;
	@Expose
	private BigDecimal hargaJualPcs;
	@Expose
	private BigDecimal hargaJualPcsVta;
	@Expose
	private BigDecimal hargaJualCtnVta;
	@Expose
	private BigDecimal hargaBeliCtnVta;
	@Expose
	private BigDecimal hargaBeliPcsVtaNetto;
	@Expose
	private BigDecimal hargaBeliCtnVtaNetto;
	@Expose
	private BigDecimal hargaBeliCtnIdrNetto;
	@Expose
	private BigDecimal hargaBeliPcsIdrNetto;
	@Expose
	private BigDecimal totHargaBrutBeliIdr;
	@Expose
	private BigDecimal totHargaBrutBeliVta;
	@Expose
	private BigDecimal totHargaNettBeliIdr;
	@Expose
	private BigDecimal totHargaNettBeliVta;
	@Expose
	private BigDecimal totHargaBrutBeliPcsIdr;
	@Expose
	private BigDecimal totHargaBrutBeliPcsVta;
	@Expose
	private BigDecimal totHargaNettBeliPcsIdr;
	@Expose
	private BigDecimal totHargaNettBeliPcsVta;
	@Expose
	private BigDecimal profitIdr;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdStock() {
		return idStock;
	}
	public void setIdStock(Long idStock) {
		this.idStock = idStock;
	}
	public Long getIdTrip() {
		return idTrip;
	}
	public void setIdTrip(Long idTrip) {
		this.idTrip = idTrip;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
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
	public String getVendName() {
		return vendName;
	}
	public void setVendName(String vendName) {
		this.vendName = vendName;
	}
	public String getVendCountry() {
		return vendCountry;
	}
	public void setVendCountry(String vendCountry) {
		this.vendCountry = vendCountry;
	}
	public String getVendVta() {
		return vendVta;
	}
	public void setVendVta(String vendVta) {
		this.vendVta = vendVta;
	}
	public BigDecimal getTripKurs() {
		return tripKurs;
	}
	public void setTripKurs(BigDecimal tripKurs) {
		this.tripKurs = tripKurs;
	}
	public java.util.Date getTripDate() {
		return tripDate;
	}
	public void setTripDate(java.util.Date tripDate) {
		this.tripDate = tripDate;
	}
	public String getTripNumber() {
		return tripNumber;
	}
	public void setTripNumber(String tripNumber) {
		this.tripNumber = tripNumber;
	}
	public String getTripNumbStok() {
		return tripNumbStok;
	}
	public void setTripNumbStok(String tripNumbStok) {
		this.tripNumbStok = tripNumbStok;
	}
	public BigDecimal getStokCtn() {
		return stokCtn;
	}
	public void setStokCtn(BigDecimal stokCtn) {
		this.stokCtn = stokCtn;
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
	public BigDecimal getTotQtyBeliCtn() {
		return totQtyBeliCtn;
	}
	public void setTotQtyBeliCtn(BigDecimal totQtyBeliCtn) {
		this.totQtyBeliCtn = totQtyBeliCtn;
	}
	public BigDecimal getTotQtyBeliPcs() {
		return totQtyBeliPcs;
	}
	public void setTotQtyBeliPcs(BigDecimal totQtyBeliPcs) {
		this.totQtyBeliPcs = totQtyBeliPcs;
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
	public BigDecimal getHargaJualCtn() {
		return hargaJualCtn;
	}
	public void setHargaJualCtn(BigDecimal hargaJualCtn) {
		this.hargaJualCtn = hargaJualCtn;
	}
	public BigDecimal getHargaJualIsiCtn() {
		return hargaJualIsiCtn;
	}
	public void setHargaJualIsiCtn(BigDecimal hargaJualIsiCtn) {
		this.hargaJualIsiCtn = hargaJualIsiCtn;
	}
	public BigDecimal getHargaJualPcs() {
		return hargaJualPcs;
	}
	public void setHargaJualPcs(BigDecimal hargaJualPcs) {
		this.hargaJualPcs = hargaJualPcs;
	}
	public BigDecimal getHargaJualPcsVta() {
		return hargaJualPcsVta;
	}
	public void setHargaJualPcsVta(BigDecimal hargaJualPcsVta) {
		this.hargaJualPcsVta = hargaJualPcsVta;
	}
	public BigDecimal getHargaJualCtnVta() {
		return hargaJualCtnVta;
	}
	public void setHargaJualCtnVta(BigDecimal hargaJualCtnVta) {
		this.hargaJualCtnVta = hargaJualCtnVta;
	}
	public BigDecimal getHargaBeliCtnVta() {
		return hargaBeliCtnVta;
	}
	public void setHargaBeliCtnVta(BigDecimal hargaBeliCtnVta) {
		this.hargaBeliCtnVta = hargaBeliCtnVta;
	}
	public BigDecimal getHargaBeliPcsVtaNetto() {
		return hargaBeliPcsVtaNetto;
	}
	public void setHargaBeliPcsVtaNetto(BigDecimal hargaBeliPcsVtaNetto) {
		this.hargaBeliPcsVtaNetto = hargaBeliPcsVtaNetto;
	}
	public BigDecimal getHargaBeliCtnVtaNetto() {
		return hargaBeliCtnVtaNetto;
	}
	public void setHargaBeliCtnVtaNetto(BigDecimal hargaBeliCtnVtaNetto) {
		this.hargaBeliCtnVtaNetto = hargaBeliCtnVtaNetto;
	}
	public BigDecimal getHargaBeliCtnIdrNetto() {
		return hargaBeliCtnIdrNetto;
	}
	public void setHargaBeliCtnIdrNetto(BigDecimal hargaBeliCtnIdrNetto) {
		this.hargaBeliCtnIdrNetto = hargaBeliCtnIdrNetto;
	}
	public BigDecimal getHargaBeliPcsIdrNetto() {
		return hargaBeliPcsIdrNetto;
	}
	public void setHargaBeliPcsIdrNetto(BigDecimal hargaBeliPcsIdrNetto) {
		this.hargaBeliPcsIdrNetto = hargaBeliPcsIdrNetto;
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
	public BigDecimal getTotHargaBrutBeliPcsIdr() {
		return totHargaBrutBeliPcsIdr;
	}
	public void setTotHargaBrutBeliPcsIdr(BigDecimal totHargaBrutBeliPcsIdr) {
		this.totHargaBrutBeliPcsIdr = totHargaBrutBeliPcsIdr;
	}
	public BigDecimal getTotHargaBrutBeliPcsVta() {
		return totHargaBrutBeliPcsVta;
	}
	public void setTotHargaBrutBeliPcsVta(BigDecimal totHargaBrutBeliPcsVta) {
		this.totHargaBrutBeliPcsVta = totHargaBrutBeliPcsVta;
	}
	public BigDecimal getTotHargaNettBeliPcsIdr() {
		return totHargaNettBeliPcsIdr;
	}
	public void setTotHargaNettBeliPcsIdr(BigDecimal totHargaNettBeliPcsIdr) {
		this.totHargaNettBeliPcsIdr = totHargaNettBeliPcsIdr;
	}
	public BigDecimal getTotHargaNettBeliPcsVta() {
		return totHargaNettBeliPcsVta;
	}
	public void setTotHargaNettBeliPcsVta(BigDecimal totHargaNettBeliPcsVta) {
		this.totHargaNettBeliPcsVta = totHargaNettBeliPcsVta;
	}
	public BigDecimal getProfitIdr() {
		return profitIdr;
	}
	public void setProfitIdr(BigDecimal profitIdr) {
		this.profitIdr = profitIdr;
	}
	
	
	
	
}
