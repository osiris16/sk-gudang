package org.radot.json.handlers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.radot.hibernate.entities.DetailPenjualanEntity;
import org.radot.hibernate.entities.PenjualanEntity;
import org.radot.hibernate.entities.ProductEntity;
import org.radot.hibernate.entities.StockEntity;
import org.radot.hibernate.entities.TripEntity;
import org.radot.hibernate.persistences.DetPenjualanPersistence;
import org.radot.hibernate.persistences.PenjualanPersistence;
import org.radot.hibernate.persistences.StockPersistence;
import org.radot.hibernate.persistences.TripPersistence;
import org.radot.json.beans.DetPembelianItem;
import org.radot.json.beans.DetPenjualanInputParam;
import org.radot.json.beans.DetPenjualanItem;
import org.radot.json.beans.DetPenjualanResult;
import org.radot.json.beans.StockItem;
import org.radot.json.servlet.JsonServletHandler;

public class JsonDetPenjualanModHandler extends JsonServletHandler<DetPenjualanInputParam, DetPenjualanResult> {

	public JsonDetPenjualanModHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
		
		final HttpSession _session = this.request.getSession();
		
		
			final PenjualanEntity penjEnt = new PenjualanPersistence().getByRecId(Long.parseLong(this.param.getPenjualanEnt()));
			final StockEntity stockEnt = new StockPersistence().getByRecId(Long.parseLong(this.param.getStockEnt()));
			
			final DetailPenjualanEntity _ent = new DetPenjualanPersistence().getByRecId(this.param.getId());
			
			if(_ent != null){
			
			/*Modify Start*/
			BigDecimal _qtyBeliOld = _ent.getTotQtyJualCtn();
			BigDecimal _stockCtnOld = _ent.getStockEnt().getStokCtn();
			BigDecimal _stockNow = _stockCtnOld.add(_qtyBeliOld);
			_ent.getStockEnt().setStokCtn(_stockNow);
			
			_ent.setTypeGrosiRetail(this.param.getDeptOrRetail());
			
			try {
				if(_ent.getTypeGrosiRetail().equalsIgnoreCase("retail")) {
					BigDecimal _stockCtnRetailOld = _ent.getStockEnt().getStokCtnRetail();
					BigDecimal _stockRetailNow = _stockCtnRetailOld.add(_qtyBeliOld);
					_ent.getStockEnt().setStokCtnRetail(_stockRetailNow);
				}else {
					BigDecimal _stockCtnGrosirlOld = _ent.getStockEnt().getStokCtn_grosir();
					BigDecimal _stockGrosirNow = _stockCtnGrosirlOld.add(_qtyBeliOld);
					_ent.getStockEnt().setStokCtnRetail(_stockGrosirNow);
				}
				
			} catch (Exception e) {
				
			}
			_ent.getStockEnt().modify();
			
			BigDecimal _penjualanBrutoIdrBefore = _ent.getPenjualanEnt().getTotPenjualanBrutoIdr();
			BigDecimal _penjualanBrutoIdrUpdate = _penjualanBrutoIdrBefore.subtract(_ent.getTotJualBrutoIdr());
			
			BigDecimal _penjualanNettoIdrBefore = _ent.getPenjualanEnt().getTotPenjualanNettIdr();
			BigDecimal _penjualanNettoIdrUpdate = _penjualanNettoIdrBefore.subtract(_ent.getTotJualNettoIdr());
			
			BigDecimal _persen = new BigDecimal(100);
			BigDecimal _bruto = _ent.getTotJualBrutoIdr();
			BigDecimal _disc = _ent.getDiscPersen().divide(_persen,2,RoundingMode.HALF_UP);
			BigDecimal _totDisc = _bruto.multiply(_disc);
			BigDecimal _totDiscNoww = _ent.getPenjualanEnt().getTotDiscPenjualan();
			BigDecimal _discUpdate = _totDiscNoww.subtract(_totDisc); // disc edit
			
			BigDecimal _totJualNettoBeforePpn = _ent.getTotJualBrutoIdr().subtract(_totDisc);
			BigDecimal _ppn = _ent.getPenjualanEnt().getPpn().divide(_persen,2,RoundingMode.HALF_UP);
			BigDecimal _totPpn = _ppn.multiply(_totJualNettoBeforePpn);
			BigDecimal _ppnUpdate = _ent.getPenjualanEnt().getTotPpn().subtract(_totPpn);
			
			
			_ent.getPenjualanEnt().setTotDiscPenjualan(_discUpdate);
			_ent.getPenjualanEnt().modify();
			_ent.getPenjualanEnt().setTotPpn(_ppnUpdate);
			_ent.getPenjualanEnt().modify();
			_ent.getPenjualanEnt().setTotPenjualanBrutoIdr(_penjualanBrutoIdrUpdate);
			_ent.getPenjualanEnt().modify();
			//Test
			//_ent.getPenjualanEnt().setTotPenjualanNettIdr(_penjualanNettoIdrUpdate);
			_ent.getPenjualanEnt().modify();
			
			/*Modify End*/	
			
			
			if(stockEnt != null){
				_ent.setStockEnt(stockEnt);
				}
			if(penjEnt != null){
				_ent.setPenjualanEnt(penjEnt);
				}
			
			BigDecimal _qtyJual= (this.param.getTotQtyJualCtn()); //qty jual per ctn
			BigDecimal _qtyJualPcs= (this.param.getTotQtyJualPcs());
			BigDecimal _hargaJualPcs = this.param.getHargaJualPcs();
			BigDecimal _isiCtn = stockEnt.getProductEnt().getIsiCtn();
			BigDecimal _isiPcs = stockEnt.getProductEnt().getIsiPcs();
			BigDecimal _hargaJualCtn = _hargaJualPcs.multiply(_isiCtn).multiply(_isiPcs);
			BigDecimal _totHargaJualBruto = _hargaJualPcs.multiply(_qtyJualPcs);
			//BigDecimal _totHargaJualBruto = _hargaJualCtn.multiply(_qtyJual) ; 
			
			
			BigDecimal _cepe = new BigDecimal(100);
			
			BigDecimal _discA = (this.param.getDisc());
			BigDecimal _disc2 = _discA.divide(_cepe,100, RoundingMode.HALF_UP);
			BigDecimal _totDisc2 = _totHargaJualBruto.multiply(_disc2);
			BigDecimal _totHargaJualNetto = _totHargaJualBruto.subtract(_totDisc2);
			
			BigDecimal _ppnA = (this.param.getPpn());
			BigDecimal _ppn2 = _ppnA.divide(_cepe,100, RoundingMode.HALF_UP);
			BigDecimal _totPpn2 = _totHargaJualNetto.multiply(_ppn2);
			BigDecimal _totHargaJualNettoFinal = _totHargaJualNetto.add(_totPpn2);
			
			
			_ent.setTotJualBrutoIdr (_totHargaJualBruto);
			_ent.setDiscPersen(this.param.getDisc());
			_ent.setTotJualNettoIdr(_totHargaJualNettoFinal);
			_ent.setHjCtn(_hargaJualCtn);
			_ent.setTotQtyJualCtn(_qtyJual);
			
			
			PenjualanEntity _penjEnt = new PenjualanPersistence().getByRecId(Long.parseLong(this.param.getPenjualanEnt()));
			if (_penjEnt != null){
				
				BigDecimal _totDiscBefore = _penjEnt.getTotDiscPenjualan();
				BigDecimal _totDiscNow = _totDiscBefore.add(_totDisc2);
				_penjEnt.setTotDiscPenjualan(_totDiscNow);

				BigDecimal _totJualBrutoBefore = _penjEnt.getTotPenjualanBrutoIdr();
				BigDecimal _totJualBrutoNow = _totJualBrutoBefore.add(_totHargaJualBruto);
				_penjEnt.setTotPenjualanBrutoIdr(_totJualBrutoNow);
				
				BigDecimal _totJualNettoBefore = _penjEnt.getTotPenjualanNettIdr();
				BigDecimal _totJualNettoNow = _totJualNettoBefore.add(_totHargaJualNettoFinal);
				_penjEnt.setTotPenjualanNettIdr(_totJualNettoNow);
				
				
				BigDecimal _totPpnPenjualan = _penjEnt.getTotPpn();
				BigDecimal _totPpnPenjualanFinal = _totPpnPenjualan.add(_totPpn2);
				_penjEnt.setTotPpn(_totPpnPenjualanFinal);
				
				_penjEnt.modify();
			}
			
			StockEntity _stockEnt = new StockPersistence().getByRecId(Long.parseLong(this.param.getStockEnt()));
			if(_stockEnt != null){
				
			BigDecimal _qtyBeli = this.param.getTotQtyJualCtn();
			BigDecimal _totUpdatNow = _stockEnt.getStokCtn().subtract(_qtyBeli) ; 
			
			if(this.param.getDeptOrRetail().equalsIgnoreCase("retail")) {
				BigDecimal _stockRetailNow = _stockEnt.getStokCtnRetail();
				BigDecimal _totUpdatRetailNow = _stockRetailNow.subtract(_qtyBeli) ; 
				if(_totUpdatRetailNow.compareTo(BigDecimal.ZERO)<0){
					this.result.setCode(1);
					this.result.setMessage("Stok Retail Kurang");
					return;
				}
				_stockEnt.setStokCtnRetail(_totUpdatRetailNow);
			}else {
				BigDecimal _stockGrosirNow = _stockEnt.getStokCtn_grosir();
				BigDecimal _totUpdatGrosirNow = _stockGrosirNow.subtract(_qtyBeli) ; 
				if(_totUpdatGrosirNow.compareTo(BigDecimal.ZERO)<0){
					this.result.setCode(1);
					this.result.setMessage("Stok Grosir Kurang");
					return;
				}
				_stockEnt.setStokCtn_grosir(_totUpdatGrosirNow);
			}
			
			if(_totUpdatNow.compareTo(BigDecimal.ZERO)<0){
				
				this.result.setCode(1);
				this.result.setMessage("Stok Kurang");
				return;
			}
			else
			{
			_stockEnt.setStokCtn(_totUpdatNow);
			_stockEnt.modify();
			}
			}
			_ent.setTotQtyJualPcs(this.param.getTotQtyJualPcs());
			_ent.setKeterangan(this.param.getKeterangan());
				_ent.modify();
			}
			
			
			
			
			final DetPenjualanItem _item = new DetPenjualanItem();
			if(_ent.getPenjualanEnt() != null){
				_item.setOrderNumb(_ent.getPenjualanEnt().getOrderNumb());
				
				_item.setFakturNumb(_ent.getPenjualanEnt().getFakturNumb());
				}
			
			_item.setTotQtyJualCtn(_ent.getTotQtyJualCtn());
			_item.setTotJualBrutoIdr(_ent.getTotJualBrutoIdr());
			_item.setTotJualNettoIdr(_ent.getTotJualNettoIdr());
			_item.setDisc(_ent.getDiscPersen());
			
			if(_ent.getStockEnt() != null){
			_item.setProductCode(_ent.getStockEnt().getProductEnt().getCode());
			
			_item.setProductName(_ent.getStockEnt().getProductEnt().getName());
			}
			
			
			StockItem _stokItem = new StockItem ();
			if(_stokItem != null){
			_stokItem.setTotStokPcs(_stokItem.getTotStokPcs());
			}
			
			
			final List<DetPenjualanItem> _items = new ArrayList<DetPenjualanItem>();
			
			_items.add(_item);
			
			
			
			this.result.setItems(_items);
			this.result.setCode(0);
			this.result.setMessage("Sukses");
		}}
	
	


