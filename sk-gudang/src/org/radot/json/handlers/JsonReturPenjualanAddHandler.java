package org.radot.json.handlers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.radot.consts.SessionKeysConst;
import org.radot.hibernate.entities.DetailPenjualanEntity;
import org.radot.hibernate.entities.HistoryEntity;
import org.radot.hibernate.entities.ReturPenjualanEntity;
import org.radot.hibernate.entities.UserEntity;
import org.radot.hibernate.persistences.DetPenjualanPersistence;
import org.radot.json.beans.ReturPenjualanInputParam;
import org.radot.json.beans.ReturPenjualanItem;
import org.radot.json.beans.ReturPenjualanResult;
import org.radot.json.beans.StockItem;
import org.radot.json.servlet.JsonServletHandler;

import com.google.gson.Gson;

public class JsonReturPenjualanAddHandler extends JsonServletHandler<ReturPenjualanInputParam, ReturPenjualanResult> {

	public JsonReturPenjualanAddHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
		
		final HttpSession _session = this.request.getSession();
		
		
			final DetailPenjualanEntity detpenjualanEnt = new DetPenjualanPersistence().getByRecId(Long.parseLong(this.param.getDetPenjualanEnt()));
			final ReturPenjualanEntity _ent = new ReturPenjualanEntity();
			
			_ent.setNoRetur(this.param.getNoRetur());
			Date _dateRetur = new Date();
			_dateRetur.setTime(this.param.getDateRetur());
			_ent.setDateRetur(_dateRetur);
			
			BigDecimal _totNettoReturIdr = this.param.getNilaiNettoRetur();
			BigDecimal _nol= new BigDecimal(0);
			if(_totNettoReturIdr==null){
				_ent.setTotNettoReturIdr(_nol);
			}
			else{
				_ent.setTotNettoReturIdr(_totNettoReturIdr);
			}
			
			BigDecimal _hargabrutoCtn = this.param.getHargaBrutoCtn();
			if(_hargabrutoCtn==null){
				_ent.setHargaBrutoCtn(_nol);
			}
			else{
				_ent.setHargaBrutoCtn(_hargabrutoCtn);
			}
			
			BigDecimal _qtyReturCtn = this.param.getTotQtyReturCtn();
			if(_qtyReturCtn==null){
				_ent.setReturQtyCtn(_nol);
			}
			else{
				_ent.setReturQtyCtn(_qtyReturCtn);
			}	
			
			_ent.setPenerima(this.param.getPenerima());
			_ent.setKeterangan(this.param.getAlasanRetur());
			
			
			
			
			if(detpenjualanEnt != null){
			_ent.setDetPenjualanEnt(detpenjualanEnt);
			}
			
			
			
			DetailPenjualanEntity _detpenjualanEnt = new DetPenjualanPersistence().getByRecId(Long.parseLong(this.param.getDetPenjualanEnt()));
			if(_detpenjualanEnt != null){
				
			BigDecimal _qtyRetur = this.param.getTotQtyReturCtn();
			BigDecimal _stockNow = _detpenjualanEnt.getStockEnt().getStokCtn();
			BigDecimal _totUpdatNow = _stockNow.add(_qtyRetur) ; 
			_detpenjualanEnt.getStockEnt().setStokCtn(_totUpdatNow);
			_detpenjualanEnt.getStockEnt().modify();
			
			//BigDecimal _nilaiReturNetto = this.param.getNilaiReturPcs();
			
			
			BigDecimal _cepe = new BigDecimal(100);
			BigDecimal _qtyJual = _detpenjualanEnt.getTotQtyJualCtn();
			BigDecimal _totUpdatJualNow = _qtyJual.subtract(_qtyRetur) ;
			if (_totUpdatJualNow.compareTo(BigDecimal.ZERO) < 0)
			{
				this.result.setCode(1);
				this.result.setMessage("Kuantiti Retur Melebihi Jual");
				return;
				
				
			}
			else
					{
				_detpenjualanEnt.setTotQtyJualCtn(_totUpdatJualNow); 
					}
				
			BigDecimal _nettoDetailJ = _detpenjualanEnt.getTotJualNettoIdr();
			BigDecimal _brutoFaktur = _detpenjualanEnt.getPenjualanEnt().getTotPenjualanBrutoIdr();
			BigDecimal _nettoFaktur = _detpenjualanEnt.getPenjualanEnt().getTotPenjualanNettIdr();
			
			BigDecimal _hargaCtnBruto = this.param.getHargaBrutoCtn();
			
			BigDecimal _disc = _detpenjualanEnt.getDiscPersen();
			BigDecimal _discDec = _disc.divide(_cepe,100, RoundingMode.HALF_UP);
			BigDecimal _totDisc = _hargaCtnBruto.multiply(_discDec);
			BigDecimal _hargaCtnBrutoDisc =_hargaCtnBruto.subtract(_totDisc); //harga pcs bruto di disc
			
			BigDecimal _ppn = _detpenjualanEnt.getPenjualanEnt().getPpn();
			BigDecimal _ppnDec = _ppn.divide(_cepe,100, RoundingMode.HALF_UP);
			BigDecimal _totPpn = _hargaCtnBrutoDisc.multiply(_ppnDec); //
			
			BigDecimal _hargaPcsNettoFinal = _hargaCtnBrutoDisc.add(_totPpn);
			
			BigDecimal _totBrutoDetailJual = _hargaCtnBruto.multiply(_qtyRetur);
			BigDecimal _totBrutoDetailJualUpdate = _detpenjualanEnt.getTotJualBrutoIdr().subtract(_totBrutoDetailJual);
			
			BigDecimal _totNettoDetailJual = _hargaPcsNettoFinal.multiply(_qtyRetur);
			BigDecimal _totNettoDetailJualUpdate = _nettoDetailJ.subtract(_totNettoDetailJual);
			
			
			
			BigDecimal _penjualanBrutoNow = _brutoFaktur.subtract(_totBrutoDetailJual); //bruto penjualan update
			BigDecimal _totDiscUpdate = _penjualanBrutoNow.multiply(_discDec);
			BigDecimal _penjualanBrutoNowDisc = _penjualanBrutoNow.subtract(_totDiscUpdate);
			BigDecimal _totPpnUpdate = _penjualanBrutoNowDisc.multiply(_ppnDec);
			
			BigDecimal _penjualanNettoNow = _nettoFaktur.subtract(_totNettoDetailJual);
			
			_detpenjualanEnt.setTotJualBrutoIdr(_totBrutoDetailJualUpdate);
			_detpenjualanEnt.setTotJualNettoIdr(_totNettoDetailJualUpdate);
			_detpenjualanEnt.getPenjualanEnt().setTotDiscPenjualan(_totDiscUpdate);
			_detpenjualanEnt.getPenjualanEnt().setTotPpn(_totPpnUpdate);
			_detpenjualanEnt.getPenjualanEnt().setTotPenjualanBrutoIdr(_penjualanBrutoNow);
			_detpenjualanEnt.getPenjualanEnt().setTotPenjualanNettIdr(_penjualanNettoNow);
			
			_detpenjualanEnt.modify();
			
			_detpenjualanEnt.getPenjualanEnt().modify();	
			
			_ent.save();
			
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
			_stokItem.setTotStokPcs(_stokItem.getTotStokPcs());
			}
			
			
			final List<ReturPenjualanItem> _items = new ArrayList<ReturPenjualanItem>();
			
			_items.add(_item);
			
			
			HashMap<String, Object> _mapsHistory = new HashMap<String, Object>();
			_mapsHistory.put("params", this.param);
			_mapsHistory.put("response", _items);
			
			String _mapsStringJSon = new Gson().toJson(_mapsHistory);
			HistoryEntity _hisEntity = new HistoryEntity();
			_hisEntity.setDataHistory(_mapsStringJSon.replace("\\", ""));
			_hisEntity.setType("ReturPenjualan");
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


