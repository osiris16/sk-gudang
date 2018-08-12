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
import org.radot.hibernate.entities.DetailPembelianEntity;
import org.radot.hibernate.entities.HistoryEntity;
import org.radot.hibernate.entities.ProductEntity;
import org.radot.hibernate.entities.StockEntity;
import org.radot.hibernate.entities.TripEntity;
import org.radot.hibernate.entities.UserEntity;
import org.radot.hibernate.persistences.StockPersistence;
import org.radot.hibernate.persistences.TripPersistence;
import org.radot.json.beans.DetPembelianInputParam;
import org.radot.json.beans.DetPembelianItem;
import org.radot.json.beans.DetPembelianResult;
import org.radot.json.beans.StockItem;
import org.radot.json.servlet.JsonServletHandler;
import org.radot.utils.AutoGenerateID;

import com.google.gson.Gson;

public class JsonDetPembelianAddHandler extends JsonServletHandler<DetPembelianInputParam, DetPembelianResult> {

	public JsonDetPembelianAddHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
		
		final HttpSession _session = this.request.getSession();
		
		
			final TripEntity tripEnt = new TripPersistence().getByRecId(Long.parseLong(this.param.getTripEnt()));
			final StockEntity stockEnt = new StockPersistence().getByRecId(Long.parseLong(this.param.getStockEnt()));
			
			final DetailPembelianEntity _ent = new DetailPembelianEntity();
			if(stockEnt != null){
				_ent.setStockEnt(stockEnt);
				
				}
			if(tripEnt != null){
				_ent.setTripEnt(tripEnt);
				}
			
			BigDecimal _isiPcs = _ent.getStockEnt().getProductEnt().getIsiPcs();
			BigDecimal _isiCtn = _ent.getStockEnt().getProductEnt().getIsiCtn();
			BigDecimal _kurs = _ent.getTripEnt().getCurrencyIDR();
			BigDecimal _hargaBpcsVta = this.param.getHargaBPcsVta();
			BigDecimal _hargaBctnVta = _hargaBpcsVta.multiply(_isiCtn).multiply(_isiPcs);
			BigDecimal _hargaBctnIdr = _hargaBctnVta.multiply(_kurs);
			
			_ent.setHargaBeliCtnVta(_hargaBctnVta); // harga beli per carton VTA
			_ent.setHargaBeliCtnIdr(_hargaBctnIdr);// harga beli per carton IDr
			
			
			
			Date _dateTrip = new Date();
			_dateTrip.setTime(this.param.getTripDate());
			
			_ent.setDisc(this.param.getDisc()); // Disc Persen
			_ent.setCost(this.param.getCost()); // Cost Persen
			_ent.setHargaJ_CtnOld(_ent.getStockEnt().getProductEnt().getHJstdCtn());
			BigDecimal One_Hundred = new BigDecimal(100);
			
			BigDecimal _hbPcsVta = (this.param.getHargaBPcsVta());
			
			BigDecimal _cos = _ent.getTripEnt().getTotCost();
			BigDecimal _cost = _cos.divide(One_Hundred,100, RoundingMode.HALF_UP);
			
			
			
			BigDecimal _totBeliCtn = (this.param.getQtyBeliCtn()); 
			//BigDecimal _totBeliPcs = _totIsi.multiply(_totBeliCtn);
			
			BigDecimal _totBrutoHBVta = _hbPcsVta.multiply(_isiPcs).multiply(_isiCtn).multiply(_totBeliCtn);
			BigDecimal _totCost = _totBrutoHBVta.multiply(_cost); //cost
			BigDecimal _totBrutoHBIdr = _totBrutoHBVta.multiply(_kurs);
			BigDecimal _dis = _ent.getTripEnt().getTotDisc();
			BigDecimal _disc = _dis.divide(One_Hundred,100, RoundingMode.HALF_UP);
			BigDecimal _totDisc = _totBrutoHBVta.multiply(_disc);
			
			
			BigDecimal _totFinalNettoVta = _totBrutoHBVta.add(_totCost).subtract(_totDisc);
			BigDecimal _totFinalNettoIdr = _totFinalNettoVta.multiply(_kurs);
			
			_ent.setTotHargaBrutB_Vta(_totBrutoHBVta);
			_ent.setTotHargaBrutB_Idr(_totBrutoHBIdr);
			
			_ent.setTotHargaNettB_Vta(_totFinalNettoVta);
			_ent.setTotHargaNettB_Idr(_totFinalNettoIdr);
			

			_ent.setQtyBeliCtn(_totBeliCtn);
			
			
			final ProductEntity _prodEnt = stockEnt.getProductEnt();
			
			
			BigDecimal _HJPCS = this.param.getHargaJPcs();
			BigDecimal _HJCTN = _isiPcs.multiply(_isiCtn).multiply(_HJPCS);
			_prodEnt.setHJstdCtn(_HJCTN); //harga jual CTN
			
				HashMap<String, Object> _mapsHistory = new HashMap<String, Object>();
				_mapsHistory.put("params", this.param);
				_mapsHistory.put("response", _prodEnt);
			
				String _mapsStringJSon = new Gson().toJson(_mapsHistory);
				HistoryEntity _hisEntity = new HistoryEntity();
				_hisEntity.setDataHistory(_mapsStringJSon.replace("\\", ""));
				_hisEntity.setType("Product");
				_hisEntity.setActionType("Edit");
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
			
				_prodEnt.modify();
			
			
			StockEntity _stockEnt = new StockPersistence().getByRecId(Long.parseLong(this.param.getStockEnt()));
			//ProductEntity _prodEnt = new ProductPersistence().getByRecId(Long.parseLong(this.param.getProdEnt()));
			
			if(_stockEnt != null){
				
			BigDecimal _qtyBeliCtn = this.param.getQtyBeliCtn();	
			BigDecimal _stockNow = _stockEnt.getStokCtn();
			BigDecimal _stockUpdatNow = _stockNow.add(_qtyBeliCtn) ; 
			_stockEnt.setStokCtn(_stockUpdatNow);
			BigDecimal _qtyBeliCtnRd = this.param.getQtyBeliCtn();	
			if(this.param.getRetailORDept().equalsIgnoreCase("retail")) {
				BigDecimal _stockNowRd = _stockEnt.getStokCtnRetail();
				BigDecimal _stockUpdatNowRd = _stockNowRd.add(_qtyBeliCtnRd) ; 
				
				_stockEnt.setStokCtnRetail(_stockUpdatNowRd);
			}else {
				BigDecimal _stockNowRd = _stockEnt.getStokCtn_grosir();
				BigDecimal _stockUpdatNowRd = _stockNowRd.add(_qtyBeliCtnRd) ; 
				_stockEnt.setStokCtn_grosir(_stockUpdatNowRd);
			}
			
			//_stockEnt.setTripNumStok(AutoGenerateID.keySeqTrip(this.param.getTripNumb()));
			_stockEnt.setTripDateStok(_dateTrip);
			//ini adalah nomor trip yang lama 
			String _a = _stockEnt.getTripNumStok();
			String _b = AutoGenerateID.keySeqTrip(this.param.getTripNumb());
			_ent.setTripNumSeqStock(_b);
			_ent.setTripNumSeqStockHist(_a);
			
			_stockEnt.setTripNumStok(_b);
			
			
			
			_mapsHistory.put("params", this.param);
			_mapsHistory.put("response", _stockEnt);
			
			_hisEntity.setDataHistory(_mapsStringJSon.replace("\\", ""));
			_hisEntity.setType("Stock");
			_hisEntity.setActionType("Edit");
			
			try {
				_user = (UserEntity) _session.getAttribute(SessionKeysConst.CURRENT_USER);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(null != _user){
				_hisEntity.setCreatedBy(_user);
			}
			_hisEntity.save();
			
			_stockEnt.modify();
			
			TripEntity _tripEnt = new TripPersistence().getByRecId(Long.parseLong(this.param.getTripEnt()));
			if(_tripEnt != null){
			BigDecimal _ctnBefore = _tripEnt.getTotCarton();
			BigDecimal _ctnAdd = (this.param.getQtyBeliCtn());
			BigDecimal _ctnNow = _ctnAdd.add(_ctnBefore);
			
			_tripEnt.setTotCarton(_ctnNow); // Add Carton
			
			BigDecimal _pembelianBrutoIdrBefore = _tripEnt.getTotPembelianBrutoIdr();
			BigDecimal _pembelianBrutoIdrUpdate = _pembelianBrutoIdrBefore.add(_totBrutoHBIdr);
			_tripEnt.setTotPembelianBrutoIdr(_pembelianBrutoIdrUpdate);
			
			BigDecimal _pembelianBrutoVtaBefore = _tripEnt.getTotPembelianBrutoVta();
			BigDecimal _pembelianBrutoVtaUpdate = _pembelianBrutoVtaBefore.add(_totBrutoHBVta);
			_tripEnt.setTotPembelianBrutoVta(_pembelianBrutoVtaUpdate);
			
			BigDecimal _pembelianNettoIdrBefore = _tripEnt.getTotPembelianNettoIdr();
			BigDecimal _pembelianNettoIdrUpdate = _pembelianNettoIdrBefore.add(_totFinalNettoIdr);
			_tripEnt.setTotPembelianNettoIdr(_pembelianNettoIdrUpdate);
			
			BigDecimal _pembelianNettoVtaBefore = _tripEnt.getTotPembelianNettoVta();
			BigDecimal _pembelianNettoVtaUpdate = _pembelianNettoVtaBefore.add(_totFinalNettoVta);
			_tripEnt.setTotPembelianNettoVta(_pembelianNettoVtaUpdate);
			_mapsHistory.put("params", this.param);
			_mapsHistory.put("response", _tripEnt);
			
			_hisEntity.setDataHistory(_mapsStringJSon.replace("\\", ""));
			_hisEntity.setType("Trip");
			_hisEntity.setActionType("Edit");
			
			try {
				_user = (UserEntity) _session.getAttribute(SessionKeysConst.CURRENT_USER);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(null != _user){
				_hisEntity.setCreatedBy(_user);
			}
			_hisEntity.save();
			
			_tripEnt.modify(); // trip modify
			}
			
			BigDecimal _hargaPcsIdr = this.param.getHargaJPcs();
			BigDecimal _hargaCtnIdr = _hargaPcsIdr.multiply(_isiPcs).multiply(_isiCtn);
			_ent.setHargaJ_CtnNew(_hargaCtnIdr);//harga jual CTN
			
			
				_ent.save();
			}
			
			
			
			
			final DetPembelianItem _item = new DetPembelianItem();
			if(_item != null){
				
			_item.setTripDate(_ent.getTripEnt().getTrip_date());
			_item.setTripNumber(_ent.getTripEnt().getTrip_numb());
			_item.setTripKurs(_ent.getTripEnt().getCurrencyIDR());
			_item.setVendVta(_ent.getTripEnt().getVendorEnt().getVta());
			}
			if(_ent.getStockEnt() != null){
			_item.setProductCode(_ent.getStockEnt().getProductEnt().getCode());
			
			_item.setProductName(_ent.getStockEnt().getProductEnt().getName());
			}
			
			_item.setDisc(_ent.getDisc());
			_item.setCost(_ent.getCost());
			_item.setTotHargaBrutBeliIdr(_ent.getTotHargaBrutB_Idr());
			_item.setTotHargaBrutBeliVta(_ent.getTotHargaBrutB_Vta());
			_item.setTotHargaNettBeliIdr(_ent.getTotHargaNettB_Idr());
			_item.setTotHargaNettBeliVta(_ent.getTotHargaNettB_Vta());
			_item.setHargaJualCtn(_ent.getHargaJ_CtnNew());
			_item.setTotQtyBeliCtn(_ent.getQtyBeliCtn());
			
			
			StockItem _stokItem = new StockItem ();
			if(_stokItem != null){
			_stokItem.setTotStokPcs(_stokItem.getTotStokPcs());
			}
			
			
			final List<DetPembelianItem> _items = new ArrayList<DetPembelianItem>();
			
			_items.add(_item);
			
			
			
			_mapsHistory.put("params", this.param);
			_mapsHistory.put("response", _items);
			
			_hisEntity.setDataHistory(_mapsStringJSon.replace("\\", ""));
			_hisEntity.setType("DetPembelian");
			_hisEntity.setActionType("Add");
			
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
			this.result.setMessage("Sukses");
		}
	
	}


