package org.radot.hibernate.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name="Return")
@Table(name="sk_returorderJual")
public class ReturPenjualanEntity extends BaseEntity {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -55125569431989921L;
	
	@Column(name="no_retur")
	private String noRetur;
	
	@Column(name="date_retur")
	private Date dateRetur;
	
	@JoinColumn(name="order_fk")
	@OneToOne()
	private DetailPenjualanEntity detPenjualanEnt;
	
	@Column(name="retur_qty_ctn")
	private BigDecimal returQtyCtn;
	
	@Column(name="totNetto_retur_idr")
	private BigDecimal totNettoReturIdr;
	
	@Column(name="hargaBruto_Ctn")
	private BigDecimal hargaBrutoCtn;
	
	@Column(name="penerima")
	private String penerima;
	
	@Column(name="keterangan")
	private String keterangan;

	public String getNoRetur() {
		return noRetur;
	}

	public void setNoRetur(String noRetur) {
		this.noRetur = noRetur;
	}

	public Date getDateRetur() {
		return dateRetur;
	}

	public void setDateRetur(Date dateRetur) {
		this.dateRetur = dateRetur;
	}

	public DetailPenjualanEntity getDetPenjualanEnt() {
		return detPenjualanEnt;
	}

	public void setDetPenjualanEnt(DetailPenjualanEntity detPenjualanEnt) {
		this.detPenjualanEnt = detPenjualanEnt;
	}

	public BigDecimal getReturQtyCtn() {
		return returQtyCtn;
	}

	public void setReturQtyCtn(BigDecimal returQtyCtn) {
		this.returQtyCtn = returQtyCtn;
	}

	public BigDecimal getTotNettoReturIdr() {
		return totNettoReturIdr;
	}

	public void setTotNettoReturIdr(BigDecimal totNettoReturIdr) {
		this.totNettoReturIdr = totNettoReturIdr;
	}

	public BigDecimal getHargaBrutoCtn() {
		return hargaBrutoCtn;
	}

	public void setHargaBrutoCtn(BigDecimal hargaBrutoCtn) {
		this.hargaBrutoCtn = hargaBrutoCtn;
	}

	public String getPenerima() {
		return penerima;
	}

	public void setPenerima(String penerima) {
		this.penerima = penerima;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	
	
}
