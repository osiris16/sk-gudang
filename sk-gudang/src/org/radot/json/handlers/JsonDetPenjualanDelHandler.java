package org.radot.json.handlers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.radot.hibernate.entities.DetailPenjualanEntity;
import org.radot.hibernate.entities.PenjualanEntity;
import org.radot.hibernate.entities.StockEntity;
import org.radot.hibernate.persistences.DetPenjualanPersistence;
import org.radot.hibernate.persistences.PenjualanPersistence;
import org.radot.hibernate.persistences.StockPersistence;
import org.radot.json.beans.DetPenjualanInputParam;
import org.radot.json.beans.DetPenjualanItem;
import org.radot.json.beans.DetPenjualanResult;
import org.radot.json.beans.StockItem;
import org.radot.json.servlet.JsonServletHandler;

public class JsonDetPenjualanDelHandler extends JsonServletHandler<DetPenjualanInputParam, DetPenjualanResult> {

	public JsonDetPenjualanDelHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
			
			final StockEntity stockEnt = new StockPersistence().getByRecId(Long.parseLong(this.param.getStockEnt()));
			final PenjualanEntity penjEnt = new PenjualanPersistence().getByRecId(Long.parseLong(this.param.getPenjualanEnt()));
			
			final DetailPenjualanEntity _ent = new DetPenjualanPersistence().getByRecId(this.param.getId());
			
			if(stockEnt != null){
				_ent.setStockEnt(stockEnt);
			}
			
			if(penjEnt != null){
				_ent.setPenjualanEnt(penjEnt);
			}
			BigDecimal _cepe = new BigDecimal(100);
			BigDecimal _discA = _ent.getDiscPersen();
			BigDecimal _disc = _discA.divide(_cepe);
			
			
			
			BigDecimal _hargaJualBruto = _ent.getTotJualBrutoIdr();
			BigDecimal _discOrder = _hargaJualBruto.multiply(_disc);
			BigDecimal _hargaJualNetto = _ent.getTotJualNettoIdr();
			
			PenjualanEntity _penjEnt = new PenjualanPersistence().getByRecId(Long.parseLong(this.param.getPenjualanEnt()));
			
			if (_penjEnt != null){
				//disc
			BigDecimal _discBefore = _penjEnt.getTotDiscPenjualan();
			BigDecimal _discNow = _discBefore.subtract(_discOrder);
			
			_penjEnt.setTotDiscPenjualan(_discNow);
			
			//ppn
			BigDecimal _ppnA = _penjEnt.getPpn();
			BigDecimal _ppn = _ppnA.divide(_cepe);
			BigDecimal _brutoA = _ent.getTotJualBrutoIdr();
			BigDecimal _discBrutoA = _ent.getDiscPersen();
			BigDecimal _discBrutoB = _discBrutoA.divide(_cepe);
			
			BigDecimal _totDiscBrutoB = _brutoA.multiply(_discBrutoB);
			
			BigDecimal _totBrutoA = _brutoA.subtract(_totDiscBrutoB);
			
			BigDecimal _totPpn= _totBrutoA.multiply(_ppn);
			
			BigDecimal _totPpnBefore = _penjEnt.getTotPpn();
			BigDecimal _totPpnNow = _totPpnBefore.subtract(_totPpn);
			
			_penjEnt.setTotPpn(_totPpnNow);
			
			_penjEnt.setTotDiscPenjualan(_discNow);
			
			BigDecimal _totJualBruto = _penjEnt.getTotPenjualanBrutoIdr();
			BigDecimal _jualBrutoNow = _totJualBruto.subtract(_hargaJualBruto);
			
			_penjEnt.setTotPenjualanBrutoIdr(_jualBrutoNow);
			
			BigDecimal _totJualNetto = _penjEnt.getTotPenjualanNettIdr();
			BigDecimal _jualNettoNow = _totJualNetto.subtract(_hargaJualNetto);
			//test
			//_penjEnt.setTotPenjualanNettIdr(_jualNettoNow);
			
			_penjEnt.modify();
			}
			
			StockEntity _stockEnt = new StockPersistence().getByRecId(Long.parseLong(this.param.getStockEnt()));
			if(_stockEnt != null){
			BigDecimal _qtyBeli = _ent.getTotQtyJualCtn();
			
			BigDecimal _stockNow = _stockEnt.getStokCtn();
			BigDecimal _totUpdatNow = _stockNow.add(_qtyBeli) ; 
			_stockEnt.setStokCtn(_totUpdatNow);
			
			try {
				if(_ent.getTypeGrosiRetail().equalsIgnoreCase("retail")) {
					BigDecimal _stockCtnRetailOld = _ent.getStockEnt().getStokCtnRetail();
					BigDecimal _stockRetailNow = _stockCtnRetailOld.add(_qtyBeli);
					_stockEnt.setStokCtnRetail(_stockRetailNow);
				}else {
					BigDecimal _stockCtnGrosirlOld = _ent.getStockEnt().getStokCtn_grosir();
					BigDecimal _stockGrosirNow = _stockCtnGrosirlOld.add(_qtyBeli);
					_stockEnt.setStokCtn_grosir(_stockGrosirNow);
				}
				
			} catch (Exception e) {
				
			}
			
			_stockEnt.modify();
				
			_ent.erase();
			}
			
			final DetPenjualanItem _item = new DetPenjualanItem();
			if(_item != null){
			_item.setOrderNumb(_ent.getPenjualanEnt().getOrderNumb());
			_item.setFakturNumb(_ent.getPenjualanEnt().getFakturNumb());
			
			}
			if(_ent.getStockEnt() != null){
			_item.setProductCode(_ent.getStockEnt().getProductEnt().getCode());
			
			_item.setProductName(_ent.getStockEnt().getProductEnt().getName());
			}
			
			_item.setHargaJualCtn(_ent.getHjCtn());
			_item.setDisc(_ent.getDiscPersen());
			_item.setTotJualBrutoIdr(_ent.getTotJualBrutoIdr());
			_item.setTotJualNettoIdr(_ent.getTotJualNettoIdr());
			_item.setTotQtyJualCtn(_ent.getTotQtyJualCtn());
			
			
			
			
			StockItem _stokItem = new StockItem ();
			if(_stokItem != null){
			_stokItem.setTotStokPcs(_stokItem.getTotStokPcs());
			}
			
			
			final List<DetPenjualanItem> _items = new ArrayList<DetPenjualanItem>();
			
			_items.add(_item);
			
			this.result.setItems(_items);
			this.result.setCode(0);
			this.result.setMessage("Sukses");
		}

	}
