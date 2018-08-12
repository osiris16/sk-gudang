package org.radot.json.handlers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.radot.consts.SessionKeysConst;
import org.radot.hibernate.entities.HistoryEntity;
import org.radot.hibernate.entities.TripEntity;
import org.radot.hibernate.entities.UserEntity;
import org.radot.hibernate.entities.VendorEntity;
import org.radot.hibernate.persistences.TripPersistence;
import org.radot.hibernate.persistences.VendorPersistence;
import org.radot.json.beans.TripInputParam;
import org.radot.json.beans.TripItem;
import org.radot.json.beans.TripResult;
import org.radot.json.servlet.JsonServletHandler;

import com.google.gson.Gson;

public class JsonTripAddHandler extends JsonServletHandler<TripInputParam, TripResult> {

	public JsonTripAddHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
		String _valA = "";
		final HttpSession _session = this.request.getSession();
		TripEntity _validasiTrip = new TripPersistence().getByTrip_numb(this.param.getTrip_numb());
		if(_validasiTrip != null){
			this.result.setCode(1);
			this.result.setMessage("Nomor Trip sudah terdaftar");
			 _valA = "true";
		}
		
		_validasiTrip = new TripPersistence().getByTrip_noteNumber(this.param.getTrip_noteNumber());
		
		if(_validasiTrip != null){
			this.result.setCode(1);
			this.result.setMessage("Nomor Nota sudah terdaftar");
			 _valA = "true";
		}
		
		if(!_valA.equalsIgnoreCase("true")){
			final VendorEntity vendorEnt = new VendorPersistence().getByRecId(Long.parseLong(this.param.getVendorEnt()));
			final TripEntity _ent = new TripEntity();
			
			
			Date _dateTrip = new Date();
			_dateTrip.setTime(this.param.getTrip_date());
			
			Date _dateReceive = new Date();
			_dateReceive.setTime(this.param.getDateReceive());
			
			_ent.setTrip_date(_dateTrip);
			_ent.setTrip_numb(this.param.getTrip_numb());
			
			BigDecimal _disc = (this.param.getDisc());
			
			
			if(_disc == null){
				BigDecimal _totDisc = new BigDecimal(0);
				_ent.setTotDisc(_totDisc);
			}
			else {
				
			_ent.setTotDisc(this.param.getDisc());
			}
			
			//costStart
			BigDecimal _curr = (this.param.getCurrencyIDR());
			
			if(_curr == null){
				BigDecimal _totCurr = new BigDecimal(0);
				_ent.setCurrencyIDR(_totCurr);
			}
			else {
				
			_ent.setCurrencyIDR(this.param.getCurrencyIDR());
			}
			//costEnd
			
			//costStart
			BigDecimal _cost = (this.param.getCost());
			
			if(_cost == null){
				BigDecimal _totCost = new BigDecimal(0);
				_ent.setTotCost(_totCost);
			}
			else {
				
			_ent.setTotCost(this.param.getCost());
			}
			//costEnd
			
			_ent.setTrip_noteNumber(this.param.getTrip_noteNumber());
			_ent.setDateReceive(_dateReceive);
			
			if(vendorEnt != null){
				_ent.setVendorEnt(vendorEnt);
			}
			
			// Null validate
			BigDecimal _default = new BigDecimal(0);
			
			_ent.setTotCarton(_default);
			_ent.setTotPembelianBrutoIdr(_default);
			_ent.setTotPembelianBrutoVta(_default);
			_ent.setTotPembelianNettoIdr(_default);
			_ent.setTotPembelianNettoVta(_default);
			
			
			_ent.save();
		
			
			final TripItem _item = new TripItem();
			_item.setTrip_date(_ent.getTrip_date());
			_item.setTrip_numb(_ent.getTrip_numb());
			_item.setTrip_dateNote(_ent.getTrip_dateNote());
			_item.setTrip_noteNumber(_ent.getTrip_noteNumber());
			_ent.setVendorEnt(_ent.getVendorEnt());
			_item.setCurrencyIDR(_ent.getCurrencyIDR());
			_item.setDateReceive(_ent.getDateReceive());
			
			final List<TripItem> _items = new ArrayList<TripItem>();
			HashMap<String, Object> _mapsHistory = new HashMap<String, Object>();
			_mapsHistory.put("params", this.param);
			_mapsHistory.put("response", _item);
			
			String _mapsStringJSon = new Gson().toJson(_mapsHistory);
			HistoryEntity _hisEntity = new HistoryEntity();
			_hisEntity.setDataHistory(_mapsStringJSon.replace("\\", ""));
			_hisEntity.setType("Trip");
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
			
			_items.add(_item);
			
			this.result.setItems(_items);
			this.result.setCode(0);
			this.result.setMessage("Sukses");
		}
	
	}

}