package org.radot.json.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.radot.consts.SessionKeysConst;
import org.radot.hibernate.entities.HistoryEntity;
import org.radot.hibernate.entities.ProductGroupEntity;
import org.radot.hibernate.entities.UserEntity;
import org.radot.hibernate.persistences.ProductGroupPersistence;
import org.radot.json.beans.ProductGroupInputParam;
import org.radot.json.beans.ProductGroupItem;
import org.radot.json.beans.ProductGroupResult;
import org.radot.json.servlet.JsonServletHandler;

import com.google.gson.Gson;

public class JsonProductGroupAddHandler extends JsonServletHandler<ProductGroupInputParam, ProductGroupResult> {

	public JsonProductGroupAddHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
		String _valA = "";
		final HttpSession _session = this.request.getSession();
		ProductGroupEntity _validasiProductGroup = new ProductGroupPersistence().getByName(this.param.getName());
		if(_validasiProductGroup != null){
			this.result.setCode(0);
			this.result.setMessage("Jenis sudah terdaftar");
			 _valA = "true";
		}
		
		
		if(!_valA.equalsIgnoreCase("true")){
			final ProductGroupEntity _ent = new ProductGroupEntity();
			
			_ent.setName(this.param.getName());
			_ent.save();
		
			
			final ProductGroupItem _item = new ProductGroupItem();
			_item.setId(_ent.getRecId());
			
			_item.setName(_ent.getName());
			
			final List<ProductGroupItem> _items = new ArrayList<ProductGroupItem>();
			_items.add(_item);
			
			HashMap<String, Object> _mapsHistory = new HashMap<String, Object>();
			
			_mapsHistory.put("params", this.param);
			_mapsHistory.put("response", _items);
			
			String _mapsStringJSon = new Gson().toJson(_mapsHistory);
			HistoryEntity _hisEntity = new HistoryEntity();
			_hisEntity.setDataHistory(_mapsStringJSon.replace("\\", ""));
			_hisEntity.setType("ProductGroup");
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


/*package org.radot.json.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.radot.hibernate.entities.ProductGroupEntity;
import org.radot.json.beans.ProductGroupInputParam;
import org.radot.json.beans.ProductGroupItem;
import org.radot.json.beans.ProductGroupResult;
import org.radot.json.servlet.JsonServletHandler;

public class JsonProductGroupAddHandler extends JsonServletHandler<ProductGroupInputParam, ProductGroupResult> {

	public JsonProductGroupAddHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
		
		final ProductGroupEntity _ent = new ProductGroupEntity();
		_ent.setName(this.param.getName());
		_ent.save();
		
		final ProductGroupItem _item = new ProductGroupItem();
		_item.setId(_ent.getRecId());
		_item.setName(_ent.getName());
		
		final List<ProductGroupItem> _items = new ArrayList<ProductGroupItem>();
		_items.add(_item);
		
		this.result.setItems(_items);
		this.result.setCode(0);
		this.result.setMessage("Sukses");
	}

}

*/