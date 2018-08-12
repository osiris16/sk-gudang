package org.radot.json.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.radot.consts.SessionKeysConst;
import org.radot.hibernate.entities.HistoryEntity;
import org.radot.hibernate.entities.UserEntity;
import org.radot.hibernate.entities.VendorEntity;
import org.radot.hibernate.persistences.VendorPersistence;
import org.radot.json.beans.VendorInputParam;
import org.radot.json.beans.VendorItem;
import org.radot.json.beans.VendorResult;
import org.radot.json.servlet.JsonServletHandler;

import com.google.gson.Gson;

public class JsonVendorAddHandler extends JsonServletHandler<VendorInputParam, VendorResult> {

	public JsonVendorAddHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
		String _valA = "";
		VendorEntity _validasiVendor = new VendorPersistence().getByName(this.param.getName());
		if(_validasiVendor != null){
			this.result.setCode(1);
			this.result.setMessage("Nama sudah terdaftar");
			 _valA = "true";
		}
		
		HashMap<String, Object> _mapsHistory = new HashMap<String, Object>();
		
		if(!_valA.equalsIgnoreCase("true")){
			final VendorEntity _ent = new VendorEntity();
			_ent.setName(this.param.getName());
			_ent.setAddress1(this.param.getAddress1());
			_ent.setAddress2(this.param.getAddress2());
			_ent.setCargo(this.param.getCargo());
			_ent.setCity(this.param.getCity());
			_ent.setEmail(this.param.getEmail());
			_ent.setFax(this.param.getFax());
			_ent.setPhone(this.param.getPhone());
			_ent.setTax(this.param.getTax());
			_ent.setCountry(this.param.getCountry());
			_ent.setVta(this.param.getVta());
			_ent.save();
		
			
			final VendorItem _item = new VendorItem();
			_item.setId(_ent.getRecId());
			_item.setName(_ent.getName());
			_item.setAddress1(_ent.getAddress1());
			_item.setAddress2(_ent.getAddress2());
			_item.setCargo(_ent.getCargo());
			_item.setCity(_ent.getCity());
			_item.setCountry(_ent.getCountry());
			_item.setVta(_ent.getVta());
			_item.setEmail(_ent.getEmail());
			_item.setFax(_ent.getFax());
			_item.setPhone(_ent.getPhone());
			_item.setTax(_ent.getTax());
			
			final List<VendorItem> _items = new ArrayList<VendorItem>();
			_items.add(_item);
			
			
			//History 
			
			final HttpSession _session = this.request.getSession();
			
//			String _gsonParam = new Gson().toJson(this.param);
//			String _gsonResponse = new Gson().toJson(_items);
			
			_mapsHistory.put("params", this.param);
			_mapsHistory.put("response", _items);
			
			
			String _mapsStringJSon = new Gson().toJson(_mapsHistory);
			HistoryEntity _hisEntity = new HistoryEntity();
			_hisEntity.setDataHistory(_mapsStringJSon.replace("\\", ""));
			_hisEntity.setType("Vendor");
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
			this.result.setMessage("Sukses");
			
		}
	
	}

}
