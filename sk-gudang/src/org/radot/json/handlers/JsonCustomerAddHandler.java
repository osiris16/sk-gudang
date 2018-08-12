package org.radot.json.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.radot.consts.SessionKeysConst;
import org.radot.hibernate.entities.CustomerEntity;
import org.radot.hibernate.entities.HistoryEntity;
import org.radot.hibernate.entities.UserEntity;
import org.radot.hibernate.persistences.CustomerPersistence;
import org.radot.json.beans.CustomerInputParam;
import org.radot.json.beans.CustomerItem;
import org.radot.json.beans.CustomerResult;
import org.radot.json.servlet.JsonServletHandler;

import com.google.gson.Gson;

public class JsonCustomerAddHandler extends JsonServletHandler<CustomerInputParam, CustomerResult> {

	public JsonCustomerAddHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
		final HttpSession _session = this.request.getSession();
		String _valA = "";
		
		CustomerEntity _validasiCustomer = new CustomerPersistence().getByCode(this.param.getCode());
		if(_validasiCustomer != null){
			this.result.setCode(1);
			this.result.setMessage("Kode sudah terdaftar");
			 _valA = "true";
		}
		
		_validasiCustomer = new CustomerPersistence().getByName(this.param.getName());
		
		if(_validasiCustomer != null){
			this.result.setCode(1);
			this.result.setMessage("Nama sudah terdaftar");
			 _valA = "true";
		}
		
		if(!_valA.equalsIgnoreCase("true")){
			final CustomerEntity _ent = new CustomerEntity();
			String _code = this.param.getCode();
			_code = _code.replace("'", "");
			String _name = this.param.getName();
			_name = _name.replace("'", "");
			String _addres1 = this.param.getAddress1();
			_addres1 = _addres1.replace("'", "");
			String _addres2 = this.param.getAddress2();
			_addres2 = _addres2.replace("'", "");
			
			
			_ent.setCode(_code);
			_ent.setName(_name);
			_ent.setAddress1(_addres1);
			_ent.setAddress2(_addres2);
			_ent.setContact(this.param.getContact());
			_ent.setCity(this.param.getCity());
			_ent.setFax(this.param.getFax());
			_ent.setPhone(this.param.getPhone());
			_ent.setTax(this.param.getTax());
			_ent.save();
		
			
			final CustomerItem _item = new CustomerItem();
			_item.setName(_ent.getName());
			_item.setAddress1(_ent.getAddress1());
			_item.setAddress2(_ent.getAddress2());
			_item.setCity(_ent.getCity());
			_item.setCode(_ent.getCode());
			_item.setContact(_ent.getContact());
			_item.setFax(_ent.getFax());
			_item.setId(_ent.getRecId());
			_item.setPhone(_ent.getPhone());
			_item.setTax(_ent.getTax());
			
			final List<CustomerItem> _items = new ArrayList<CustomerItem>();
			_items.add(_item);
			
			HashMap<String, Object> _mapsHistory = new HashMap<String, Object>();
			
			_mapsHistory.put("params", this.param);
			_mapsHistory.put("response", _items);
			
			String _mapsStringJSon = new Gson().toJson(_mapsHistory);
			HistoryEntity _hisEntity = new HistoryEntity();
			_hisEntity.setDataHistory(_mapsStringJSon.replace("\\", ""));
			_hisEntity.setType("Customer");
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
			;
		}
	
	}

}


