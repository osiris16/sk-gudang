package org.radot.json.handlers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.radot.hibernate.entities.DetailPenjualanEntity;
import org.radot.hibernate.entities.ReturPenjualanEntity;
import org.radot.hibernate.persistences.DetPenjualanPersistence;
import org.radot.hibernate.persistences.ReturPenjualanPersistence;
import org.radot.json.beans.ReturPenjualanInputParam;
import org.radot.json.beans.ReturPenjualanItem;
import org.radot.json.beans.ReturPenjualanResult;
import org.radot.json.beans.StockItem;
import org.radot.json.servlet.JsonServletHandler;

public class JsonReturPenjualanDelHandler extends JsonServletHandler<ReturPenjualanInputParam, ReturPenjualanResult> {

	public JsonReturPenjualanDelHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
			
			final DetailPenjualanEntity detpenjualanEnt = new DetPenjualanPersistence().getByRecId(Long.parseLong(this.param.getDetPenjualanEnt()));
			final ReturPenjualanEntity _ent = new ReturPenjualanPersistence().getByRecId(this.param.getId());
			
			
			if(detpenjualanEnt != null){
				_ent.setDetPenjualanEnt(detpenjualanEnt);
			}
			
			
			DetailPenjualanEntity _detpenjualanEnt = new DetPenjualanPersistence().getByRecId(Long.parseLong(this.param.getDetPenjualanEnt()));
			if(_detpenjualanEnt != null){
				
			
			BigDecimal _totUpdatNow = _detpenjualanEnt.getStockEnt().getStokCtn().subtract(_ent.getReturQtyCtn()) ; 
			_detpenjualanEnt.getStockEnt().setStokCtn(_totUpdatNow);
			_detpenjualanEnt.getStockEnt().modify();
			
			BigDecimal _totUpdatJualNow = _detpenjualanEnt.getTotQtyJualCtn().add(_ent.getReturQtyCtn()) ; 
			_detpenjualanEnt.setTotQtyJualCtn(_totUpdatJualNow);
			
			BigDecimal _cepe = new BigDecimal(100);
			BigDecimal _brutoFaktur = _detpenjualanEnt.getPenjualanEnt().getTotPenjualanBrutoIdr();
			BigDecimal _nettoFaktur = _detpenjualanEnt.getPenjualanEnt().getTotPenjualanNettIdr();
			BigDecimal _nettoOrder = _detpenjualanEnt.getTotJualNettoIdr();
			BigDecimal _brutoOrder = _detpenjualanEnt.getTotJualBrutoIdr();
			BigDecimal _nilaiNettoRetur = _ent.getTotNettoReturIdr();
			BigDecimal _nettoOrderUpdate = _nettoOrder.add(_nilaiNettoRetur);
			BigDecimal _nettoFakturUpdate = _nettoFaktur.add(_nettoOrderUpdate); //Order Netto
			
			BigDecimal _nilaiBrutoRetur = _ent.getHargaBrutoCtn().multiply(_ent.getReturQtyCtn());
			BigDecimal _brutoOrderUpdate = _brutoOrder.add(_nilaiBrutoRetur);
			BigDecimal _brutoFakturUpdate = _brutoFaktur.add(_brutoOrderUpdate); //Order Netto
			
			 
			BigDecimal _disc = _detpenjualanEnt.getDiscPersen().divide(_cepe,100, RoundingMode.HALF_UP);
			BigDecimal _discNow = _nilaiBrutoRetur.multiply(_disc);
			BigDecimal _discUpdate = _discNow.add(_detpenjualanEnt.getPenjualanEnt().getTotDiscPenjualan());
			BigDecimal _brutoAfterDisc = _nilaiBrutoRetur.subtract(_discNow);//bruto setelah diskon
			BigDecimal _ppn = _detpenjualanEnt.getPenjualanEnt().getPpn().divide(_cepe,100, RoundingMode.HALF_UP);
			BigDecimal _ppnNow = _brutoAfterDisc.multiply(_ppn);
			BigDecimal _ppnUpdate = _ppnNow.add(_detpenjualanEnt.getPenjualanEnt().getTotPpn());
			
			
			_detpenjualanEnt.setTotJualNettoIdr(_nettoOrderUpdate);
			_detpenjualanEnt.setTotJualBrutoIdr(_brutoOrderUpdate);
			_detpenjualanEnt.getPenjualanEnt().setTotPpn(_ppnUpdate);
			_detpenjualanEnt.getPenjualanEnt().setTotDiscPenjualan(_discUpdate);
			_detpenjualanEnt.getPenjualanEnt().setTotPenjualanBrutoIdr(_brutoFakturUpdate);
			_detpenjualanEnt.getPenjualanEnt().setTotPenjualanNettIdr(_nettoFakturUpdate);
			
			_detpenjualanEnt.getPenjualanEnt().modify();
			_detpenjualanEnt.modify();
			
			
			_ent.erase();
			}
			
			final ReturPenjualanItem _item = new ReturPenjualanItem();
			
			_item.setNoRetur(_ent.getNoRetur());
			_item.setDateRetur(_ent.getDateRetur());
			_item.setTotQtyReturCtn(_ent.getReturQtyCtn());
			_item.setNilaiNettoRetur(_ent.getTotNettoReturIdr());
			_item.setPenerimaRetur(_ent.getPenerima());
			_item.setAlasanRetur(_ent.getKeterangan());
			
			if(_ent.getDetPenjualanEnt().getStockEnt() != null){
			_item.setProductCode(_ent.getDetPenjualanEnt().getStockEnt().getProductEnt().getCode());
			
			_item.setProductName(_ent.getDetPenjualanEnt().getStockEnt().getProductEnt().getName());
			
			}
			
			
			StockItem _stokItem = new StockItem ();
			if(_stokItem != null){
			_stokItem.setStokCtn(_stokItem.getStokCtn());
			}
			
			
			final List<ReturPenjualanItem> _items = new ArrayList<ReturPenjualanItem>();
			
			_items.add(_item);
			
			this.result.setItems(_items);
			this.result.setCode(0);
			this.result.setMessage("Sukses");
		}

	}
