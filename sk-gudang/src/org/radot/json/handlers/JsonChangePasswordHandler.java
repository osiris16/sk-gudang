package org.radot.json.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.radot.consts.SessionKeysConst;
import org.radot.hibernate.entities.UserEntity;
import org.radot.hibernate.entities.VendorEntity;
import org.radot.hibernate.persistences.UserPersistence;
import org.radot.json.beans.TripItem;
import org.radot.json.beans.UserAddParam;
import org.radot.json.beans.UserItem;
import org.radot.json.beans.UserResult;
import org.radot.json.servlet.JsonServletHandler;
import org.radot.utils.Md5;

public class JsonChangePasswordHandler extends JsonServletHandler<UserAddParam, UserResult> {

	public JsonChangePasswordHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		
		super(request, response);
	}
	
	@Override()
	public void process() throws Throwable {
		String _valA = "";
		final HttpSession _session = this.request.getSession();
		final UserEntity _ent = (UserEntity) _session.getAttribute(SessionKeysConst.CURRENT_USER);
		String _oldPass = this.param.getOldPass();
		String _oldPassDb = _ent.getPassword();
		String _newPass = this.param.getNewPass();
		
		
		String _oldPassDec = Md5.decript(_oldPass);
		String _newPassDec = Md5.decript(_newPass);
		
		
		
		if(_oldPassDec.equalsIgnoreCase(_oldPassDb)){
			
				_ent.setPassword(_newPassDec);
				_ent.modify();
			
			}
			
		else{
			this.result.setCode(1);
			this.result.setMessage("Password Lama Salah");
			return;
			
		}
		
		
		final UserItem _item = new UserItem();
		_item.setId(_ent.getRecId());
		_item.setName(_ent.getName());
		
		final List<UserItem> _items = new ArrayList<UserItem>();
		_items.add(_item);
		this.result.setCode(0);
		this.result.setMessage("Sukses");
		
	}

}
