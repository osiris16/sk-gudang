package org.radot.json.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.radot.consts.SessionKeysConst;
import org.radot.hibernate.entities.HistoryEntity;
import org.radot.hibernate.entities.SalesmanEntity;
import org.radot.hibernate.entities.UserEntity;
import org.radot.hibernate.persistences.SalesmanPersistence;
import org.radot.json.beans.SalesmanInputParam;
import org.radot.json.beans.SalesmanItem;
import org.radot.json.beans.SalesmanResult;
import org.radot.json.servlet.JsonServletHandler;

import com.google.gson.Gson;

public class JsonSalesmanAddHandler extends JsonServletHandler<SalesmanInputParam, SalesmanResult> {

	public JsonSalesmanAddHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
		String _valA = "";
		final HttpSession _session = this.request.getSession();
		SalesmanEntity _validasiSalesman = new SalesmanPersistence().getByCode(this.param.getCode());
		if(_validasiSalesman != null){
			this.result.setCode(0);
			this.result.setMessage("Kode sudah terdaftar");
			 _valA = "true";
		}
		
		_validasiSalesman = new SalesmanPersistence().getByName(this.param.getName());
		
		if(_validasiSalesman != null){
			this.result.setCode(0);
			this.result.setMessage("Nama sudah terdaftar");
			 _valA = "true";
		}
		
		if(!_valA.equalsIgnoreCase("true")){
			final SalesmanEntity _ent = new SalesmanEntity();
			_ent.setCode(this.param.getCode());
			_ent.setName(this.param.getName());
			_ent.save();
		
			
			final SalesmanItem _item = new SalesmanItem();
			_item.setId(_ent.getRecId());
			_item.setCode(_ent.getCode());
			_item.setName(_ent.getName());
			
			final List<SalesmanItem> _items = new ArrayList<SalesmanItem>();
			_items.add(_item);
			
			HashMap<String, Object> _mapsHistory = new HashMap<String, Object>();
			
			_mapsHistory.put("params", this.param);
			_mapsHistory.put("response", _items);
			
			String _mapsStringJSon = new Gson().toJson(_mapsHistory);
			HistoryEntity _hisEntity = new HistoryEntity();
			_hisEntity.setDataHistory(_mapsStringJSon.replace("\\", ""));
			_hisEntity.setType("Salesman");
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
