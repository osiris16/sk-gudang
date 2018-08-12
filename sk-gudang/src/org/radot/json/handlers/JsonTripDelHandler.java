package org.radot.json.handlers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.radot.consts.SessionKeysConst;
import org.radot.hibernate.entities.HistoryEntity;
import org.radot.hibernate.entities.TripEntity;
import org.radot.hibernate.entities.UserEntity;
import org.radot.json.beans.TripInputParam;
import org.radot.json.beans.TripItem;
import org.radot.json.beans.TripResult;
import org.radot.json.servlet.JsonServletHandler;

import com.google.gson.Gson;

public class JsonTripDelHandler extends JsonServletHandler<TripInputParam, TripResult> {

	public JsonTripDelHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
		final HttpSession _session = this.request.getSession();
		final TripEntity _ent = new TripEntity();
		_ent.setRecId(this.param.getId());
		
		HashMap<String, Object> _mapsHistory = new HashMap<String, Object>();
		_mapsHistory.put("params", this.param);
		_mapsHistory.put("response", _ent);
		
		String _mapsStringJSon = new Gson().toJson(_mapsHistory);
		HistoryEntity _hisEntity = new HistoryEntity();
		_hisEntity.setDataHistory(_mapsStringJSon.replace("\\", ""));
		_hisEntity.setType("Trip");
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
		
		_ent.erase();
		
		final TripItem _item = new TripItem();
		_item.setTrip_date(_ent.getTrip_date());
		_item.setTrip_numb(_ent.getTrip_numb());
		_item.setTrip_dateNote(_ent.getTrip_dateNote());
		_item.setTrip_noteNumber(_ent.getTrip_noteNumber());
		_item.setVendorEnt(_ent.getVendorEnt());
		_item.setCurrencyIDR(_ent.getCurrencyIDR());
		
		_item.setDateReceive(_ent.getDateReceive());

		final List<TripItem> _items = new ArrayList<TripItem>();
		_items.add(_item);
		
		this.result.setItems(_items);
		this.result.setCode(0);
		this.result.setMessage("Sukses");
	}

}
