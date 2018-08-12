package org.radot.json.handlers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.radot.hibernate.entities.ProductGroupEntity;
import org.radot.hibernate.entities.TripEntity;
import org.radot.hibernate.entities.VendorEntity;
import org.radot.hibernate.persistences.ProductGroupPersistence;
import org.radot.hibernate.persistences.TripPersistence;
import org.radot.hibernate.persistences.VendorPersistence;
import org.radot.json.beans.TripInputParam;
import org.radot.json.beans.TripItem;
import org.radot.json.beans.TripResult;
import org.radot.json.servlet.JsonServletHandler;

public class JsonTripModHandler extends JsonServletHandler<TripInputParam, TripResult> {

	public JsonTripModHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}


	@Override()
	public void process() throws Throwable {
		String _valA = "";
		TripEntity _ent = new TripPersistence().getByRecId(this.param.getId());
		if(_ent != null){
			if (!_ent.getTrip_numb().equalsIgnoreCase(this.param.getTrip_numb())){
				TripEntity _checkTrip = new TripPersistence().getByTrip_numb(this.param.getTrip_numb());
				if (_checkTrip != null){
					this.result.setCode(1);
					this.result.setMessage("Nomor Trip sudah terpakai");
					return;
				}
				else {
					_ent.setTrip_numb(this.param.getTrip_numb());
				}
			}
			
			
			 _valA = "true";
		}
		
			//Date _dateNote = new Date();
			//_dateNote.setTime(this.param.getTrip_dateNote());
			
			Date _dateTrip = new Date();
			_dateTrip.setTime(this.param.getTrip_date());
			
			Date _dateReceive = new Date();
			_dateReceive.setTime(this.param.getDateReceive());
			
			VendorEntity vendorEnt = new VendorPersistence().getByRecId(Long.valueOf(this.param.getVendorEnt()));
			
			_ent.setTrip_date(_dateTrip);
			_ent.setTrip_numb(this.param.getTrip_numb());
			//_ent.setTrip_dateNote(_dateNote);
			//_ent.setTrip_noteNumber(this.param.getTrip_noteNumber());
			_ent.setDateReceive(_dateReceive);
			_ent.setTotCost(this.param.getCost());
			_ent.setTotDisc(this.param.getDisc());
			_ent.setVendorEnt(vendorEnt);
			
			_ent.setCurrencyIDR(this.param.getCurrencyIDR());
			_ent.modify();
		
			
			final TripItem _item = new TripItem();
			_item.setTrip_date(_ent.getTrip_date());
			_item.setTrip_numb(_ent.getTrip_numb());
			_item.setTrip_dateNote(_ent.getTrip_dateNote());
			_item.setTrip_noteNumber(_ent.getTrip_noteNumber());
			_ent.setVendorEnt(_ent.getVendorEnt());
			_item.setCurrencyIDR(_ent.getCurrencyIDR());
			_item.setDateReceive(_ent.getDateReceive());
			_item.setTrip_vendName(_ent.getVendorEnt().getName());
			final List<TripItem> _items = new ArrayList<TripItem>();
			_items.add(_item);
			
			this.result.setItems(_items);
			this.result.setCode(0);
			this.result.setMessage("Data Berhasil diubah");
		}
	
	}

