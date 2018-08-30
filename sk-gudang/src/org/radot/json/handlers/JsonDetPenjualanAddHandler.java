package org.radot.json.handlers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.radot.consts.SessionKeysConst;
import org.radot.hibernate.entities.DetailPenjualanEntity;
import org.radot.hibernate.entities.HistoryEntity;
import org.radot.hibernate.entities.PenjualanEntity;
import org.radot.hibernate.entities.StockEntity;
import org.radot.hibernate.entities.UserEntity;
import org.radot.hibernate.persistences.PenjualanPersistence;
import org.radot.hibernate.persistences.StockPersistence;
import org.radot.json.beans.DetPenjualanInputParam;
import org.radot.json.beans.DetPenjualanItem;
import org.radot.json.beans.DetPenjualanResult;
import org.radot.json.beans.StockItem;
import org.radot.json.servlet.JsonServletHandler;

import com.google.gson.Gson;

public class JsonDetPenjualanAddHandler extends JsonServletHandler<DetPenjualanInputParam, DetPenjualanResult> {

	public JsonDetPenjualanAddHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
		
		final HttpSession _session = this.request.getSession();
		
		

			final StockEntity stockEnt = new StockPersistence().getByRecId(Long.parseLong(this.param.getStockEnt()));
			final PenjualanEntity penjualanEnt = new PenjualanPersistence().getByRecId(Long.parseLong(this.param.getPenjualanEnt()));
		
			final DetailPenjualanEntity _ent = new DetailPenjualanEntity();
			_ent.setTypeGrosiRetail(this.param.getDeptOrRetail());
			BigDecimal _qtyJual= (this.param.getTotQtyJualCtn()); //qty jual per ctn
			BigDecimal _qtyJualPcs= (this.param.getTotQtyJualPcs());
			BigDecimal _hargaJualPcs = this.param.getHargaJualPcs();
			BigDecimal _isiCtn = stockEnt.getProductEnt().getIsiCtn();
			BigDecimal _isiPcs = stockEnt.getProductEnt().getIsiPcs();
			BigDecimal _hargaJualCtn = _hargaJualPcs.multiply(_isiCtn).multiply(_isiPcs);
			BigDecimal _totHargaJualBruto = _hargaJualPcs.multiply(_qtyJualPcs);
			
			//BigDecimal _isiPcs= stockEnt.getProductEnt().getIsiPcs();
			
			
			//BigDecimal _totHargaJualBruto = _hargaJualCtn.multiply(_qtyJual) ; Sebelum hitung pcs
			
			
			BigDecimal _cepe = new BigDecimal(100);
			
			BigDecimal _discA = (this.param.getDisc());
			BigDecimal _disc = _discA.divide(_cepe,100, RoundingMode.HALF_UP);
			BigDecimal _totDisc = _totHargaJualBruto.multiply(_disc);
			BigDecimal _totHargaJualNetto = _totHargaJualBruto.subtract(_totDisc);
			
			BigDecimal _ppnA = (this.param.getPpn());
			BigDecimal _ppn = _ppnA.divide(_cepe,100, RoundingMode.HALF_UP);
			BigDecimal _totPpn = _totHargaJualNetto.multiply(_ppn);
			BigDecimal _totHargaJualNettoFinal = _totHargaJualNetto.add(_totPpn);
			
			
			_ent.setTotJualBrutoIdr (_totHargaJualBruto);
			_ent.setDiscPersen(this.param.getDisc());
			_ent.setTotJualNettoIdr(_totHargaJualNettoFinal);
			_ent.setHjCtn(_hargaJualCtn);
			_ent.setTotQtyJualCtn(_qtyJual);
			_ent.setTotQtyJualPcs(_qtyJualPcs);
			_ent.setKeterangan(this.param.getKeterangan());
			
			if(stockEnt != null){
			_ent.setStockEnt(stockEnt);
			}
			
			
			if(penjualanEnt != null){
				_ent.setPenjualanEnt(penjualanEnt);
			}
			
			
			
			StockEntity _stockEnt = new StockPersistence().getByRecId(Long.parseLong(this.param.getStockEnt()));
			if(_stockEnt != null){
				
			BigDecimal _qtyBeli = this.param.getTotQtyJualCtn();
			BigDecimal _stockNow = _stockEnt.getStokCtn();
			BigDecimal _totUpdatNow = _stockNow.subtract(_qtyBeli) ; 
			
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
			PenjualanEntity _penjEnt = new PenjualanPersistence().getByRecId(Long.parseLong(this.param.getPenjualanEnt()));
			if (_penjEnt != null){
				
				BigDecimal _totDiscBefore = _penjEnt.getTotDiscPenjualan();
				BigDecimal _totDiscNow = _totDiscBefore.add(_totDisc);
				_penjEnt.setTotDiscPenjualan(_totDiscNow);

				BigDecimal _totJualBrutoBefore = _penjEnt.getTotPenjualanBrutoIdr();
				BigDecimal _totJualBrutoNow = _totJualBrutoBefore.add(_totHargaJualBruto);
				_penjEnt.setTotPenjualanBrutoIdr(_totJualBrutoNow);
				
				BigDecimal _totJualNettoBefore = _penjEnt.getTotPenjualanNettIdr();
				BigDecimal _totJualNettoNow = _totJualNettoBefore.add(_totHargaJualNettoFinal);
				_penjEnt.setTotPenjualanNettIdr(_totJualNettoNow);
				
				
				BigDecimal _totPpnPenjualan = _penjEnt.getTotPpn();
				BigDecimal _totPpnPenjualanFinal = _totPpnPenjualan.add(_totPpn);
				_penjEnt.setTotPpn(_totPpnPenjualanFinal);
				_penjEnt.modify();
			}
			_ent.save();
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
			
			
			HashMap<String, Object> _mapsHistory = new HashMap<String, Object>();
			_mapsHistory.put("params", this.param);
			_mapsHistory.put("response", _items);
			
			String _mapsStringJSon = new Gson().toJson(_mapsHistory);
			HistoryEntity _hisEntity = new HistoryEntity();
			_hisEntity.setDataHistory(_mapsStringJSon.replace("\\", ""));
			_hisEntity.setType("DetPenjualan");
			_hisEntity.setActionType("Add");
			UserEntity _user = null;
			try {
				_user = (UserEntity) _session.getAttribute(SessionKeysConst.CURRENT_USER);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(null != _user){
				_hisEntity.setCreatedBy(_user);
			}
			_hisEntity.save();
			
			
			
			this.result.setItems(_items);
			this.result.setCode(0);
			this.result.setMessage("Data Berhasil Disimpan");
		}
	
	}


