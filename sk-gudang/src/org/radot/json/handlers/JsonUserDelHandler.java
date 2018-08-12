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
import org.radot.json.beans.TripItem;
import org.radot.json.beans.UserAddParam;
import org.radot.json.beans.UserItem;
import org.radot.json.servlet.JsonResult;
import org.radot.json.servlet.JsonServletHandler;

import com.google.gson.Gson;

public class JsonUserDelHandler extends JsonServletHandler<UserAddParam, JsonResult> {

	public JsonUserDelHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
		
		final HttpSession _session = this.request.getSession();
		final UserEntity _ent = new UserEntity();
		_ent.setRecId(this.param.getId());
		_ent.setName(this.param.getName());
		
		HashMap<String, Object> _mapsHistory = new HashMap<String, Object>();
		_mapsHistory.put("params", this.param);
		_mapsHistory.put("response", _ent);
		
		String _mapsStringJSon = new Gson().toJson(_mapsHistory);
		HistoryEntity _hisEntity = new HistoryEntity();
		_hisEntity.setDataHistory(_mapsStringJSon.replace("\\", ""));
		_hisEntity.setType("User");
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
		
		final UserItem _item = new UserItem();
		_item.setId(_ent.getRecId());
		_item.setName(_ent.getName());
		

		final List<UserItem> _items = new ArrayList<UserItem>();
		_items.add(_item);
		
		this.result.setCode(0);
		this.result.setMessage("Sukses");
	}

}
