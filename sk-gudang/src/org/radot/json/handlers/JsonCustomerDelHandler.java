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
import org.radot.json.beans.CustomerInputParam;
import org.radot.json.beans.CustomerItem;
import org.radot.json.beans.CustomerResult;
import org.radot.json.servlet.JsonServletHandler;

import com.google.gson.Gson;

public class JsonCustomerDelHandler extends JsonServletHandler<CustomerInputParam, CustomerResult> {

	public JsonCustomerDelHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
		final HttpSession _session = this.request.getSession();
			final CustomerEntity _ent = new CustomerEntity();
			_ent.setRecId(this.param.getId());
			_ent.setCode(this.param.getCode());
			_ent.setName(this.param.getName());
			
			HashMap<String, Object> _mapsHistory = new HashMap<String, Object>();
			
			_mapsHistory.put("params", this.param);
			_mapsHistory.put("response", _ent);
			
			String _mapsStringJSon = new Gson().toJson(_mapsHistory);
			HistoryEntity _hisEntity = new HistoryEntity();
			_hisEntity.setDataHistory(_mapsStringJSon.replace("\\", ""));
			_hisEntity.setType("Customer");
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
			
			final CustomerItem _item = new CustomerItem();
			_item.setId(_ent.getRecId());
			_item.setCode(_ent.getCode());
			_item.setName(_ent.getName());
			_item.setAddress1(_ent.getAddress1());
			_item.setAddress2(_ent.getAddress2());
			_item.setCity(_ent.getCity());
			_item.setPhone(_ent.getPhone());
			_item.setContact(_ent.getContact());
			_item.setFax(_ent.getFax());
			_item.setTax(_ent.getTax());
			
			final List<CustomerItem> _items = new ArrayList<CustomerItem>();
			_items.add(_item);
			
			
			
			this.result.setItems(_items);
			this.result.setCode(0);
			this.result.setMessage("Sukses");
		}

	}
