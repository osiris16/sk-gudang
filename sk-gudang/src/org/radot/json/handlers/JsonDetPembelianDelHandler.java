package org.radot.json.handlers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.StyledEditorKit.BoldAction;

import org.radot.consts.SessionKeysConst;
import org.radot.hibernate.entities.DetailPembelianEntity;
import org.radot.hibernate.entities.HistoryEntity;
import org.radot.hibernate.entities.ProductEntity;
import org.radot.hibernate.entities.StockEntity;
import org.radot.hibernate.entities.TripEntity;
import org.radot.hibernate.entities.UserEntity;
import org.radot.hibernate.persistences.DetPembelianPersistence;
import org.radot.hibernate.persistences.StockPersistence;
import org.radot.hibernate.persistences.TripPersistence;
import org.radot.json.beans.DetPembelianInputParam;
import org.radot.json.beans.DetPembelianItem;
import org.radot.json.beans.DetPembelianResult;
import org.radot.json.beans.StockItem;
import org.radot.json.servlet.JsonServletHandler;

import com.google.gson.Gson;

public class JsonDetPembelianDelHandler extends JsonServletHandler<DetPembelianInputParam, DetPembelianResult> {

	public JsonDetPembelianDelHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
		final HttpSession _session = this.request.getSession();
			final StockEntity stockEnt = new StockPersistence().getByRecId(Long.parseLong(this.param.getStockEnt()));
			final TripEntity tripEnt = new TripPersistence().getByRecId(Long.parseLong(this.param.getTripEnt()));
			
			final DetailPembelianEntity _ent = new DetPembelianPersistence().getByRecId(this.param.getId());
//			_ent.setIsDeleted(true);
			if(stockEnt != null){
				_ent.setStockEnt(stockEnt);
				}
			if(tripEnt != null){
				_ent.setTripEnt(tripEnt);
			}
			
			
			
			
			
			StockEntity _stockEnt = new StockPersistence().getByRecId(Long.parseLong(this.param.getStockEnt()));
			
			if(_stockEnt != null){
			ProductEntity _prodEnt = _stockEnt.getProductEnt();
			_prodEnt.setHJstdCtn(_ent.getHargaJ_CtnOld());
			_prodEnt.modify();
			
			BigDecimal _qtyBeli = _ent.getQtyBeliCtn();
			BigDecimal _stockNow = _stockEnt.getStokCtn();
			BigDecimal _totUpdatNow = _stockNow.subtract(_qtyBeli) ; 
			_stockEnt.setStokCtn(_totUpdatNow);
			_stockEnt.setTripNumStok(_ent.getTripNumSeqStockHist());
			
			HashMap<String, Object> _mapsHistory = new HashMap<String, Object>();
			_mapsHistory.put("params", this.param);
			_mapsHistory.put("response", _stockEnt);
			
			String _mapsStringJSon = new Gson().toJson(_mapsHistory);
			HistoryEntity _hisEntity = new HistoryEntity();
			_hisEntity.setDataHistory(_mapsStringJSon.replace("\\", ""));
			_hisEntity.setType("Stock");
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
			if(null !=_ent.getTripNumSeqStockHist()){
				_stockEnt.modify();
				this.result.setCode(0);
				this.result.setMessage("Sukses");
			}else{
				this.result.setCode(7);
				this.result.setMessage("Gagal, data tidak dapat di hapus");
				return;
			}
			
			
			TripEntity _tripEnt = new TripPersistence().getByRecId(Long.parseLong(this.param.getTripEnt()));
			if(_tripEnt != null){
				
				BigDecimal _totCartonBefore = _tripEnt.getTotCarton();
				BigDecimal _totBeli = _ent.getQtyBeliCtn();
				BigDecimal _totCartonNow = _totCartonBefore.subtract(_totBeli);
				_tripEnt.setTotCarton(_totCartonNow); //carton
				
				BigDecimal _totBeliBrutoIdrNow = _tripEnt.getTotPembelianBrutoIdr();
				BigDecimal _totBeliBrutoIdrBefore = _ent.getTotHargaBrutB_Idr();
				BigDecimal _totBeliBrutoIdrUpdate = _totBeliBrutoIdrNow.subtract(_totBeliBrutoIdrBefore);
				_tripEnt.setTotPembelianBrutoIdr(_totBeliBrutoIdrUpdate); //brutoIdr
				
				BigDecimal _totBeliBrutoVtaNow = _tripEnt.getTotPembelianBrutoVta();
				BigDecimal _totBeliBrutoVtaBefore = _ent.getTotHargaBrutB_Vta();
				BigDecimal _totBeliBrutoVtaUpdate = _totBeliBrutoVtaNow.subtract(_totBeliBrutoVtaBefore);
				_tripEnt.setTotPembelianBrutoVta(_totBeliBrutoVtaUpdate); //brutoVta
				
				BigDecimal _totBeliNettoIdrNow = _tripEnt.getTotPembelianNettoIdr();
				BigDecimal _totBeliNettoIdrBefore = _ent.getTotHargaNettB_Idr();
				BigDecimal _totBeliNettoIdrUpdate = _totBeliNettoIdrNow.subtract(_totBeliNettoIdrBefore);
				_tripEnt.setTotPembelianNettoIdr(_totBeliNettoIdrUpdate); //nettoIdr
				
				BigDecimal _totBeliNettoVtaNow = _tripEnt.getTotPembelianNettoVta();
				BigDecimal _totBeliNettoVtaBefore = _ent.getTotHargaNettB_Vta();
				BigDecimal _totBeliNettoVtaUpdate = _totBeliNettoVtaNow.subtract(_totBeliNettoVtaBefore);
				_tripEnt.setTotPembelianNettoVta(_totBeliNettoVtaUpdate); //nettoVta
				
				
				
				_tripEnt.modify();
				
			}
//			_ent.modify();
			_ent.erase();
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
			_stokItem.setStokCtn(_stokItem.getStokCtn());
			}
			
			
			final List<DetPembelianItem> _items = new ArrayList<DetPembelianItem>();
			
			_items.add(_item);
			
			
			HashMap<String, Object> _mapsHistory = new HashMap<String, Object>();
			_mapsHistory.put("params", this.param);
			_mapsHistory.put("response", _stockEnt);
			
			String _mapsStringJSon = new Gson().toJson(_mapsHistory);
			HistoryEntity _hisEntity = new HistoryEntity();
			_hisEntity.setDataHistory(_mapsStringJSon.replace("\\", ""));
			_hisEntity.setType("DetPembelian");
			_hisEntity.setActionType("Delete");
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
			
		}

	}
