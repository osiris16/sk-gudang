package org.radot.json.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.radot.hibernate.entities.UserEntity;
import org.radot.hibernate.persistences.UserPersistence;
import org.radot.json.beans.UserAddParam;
import org.radot.json.beans.UserItem;
import org.radot.json.beans.UserResult;
import org.radot.json.servlet.JsonServletHandler;

public class JsonResetPasswordHandler extends JsonServletHandler<UserAddParam, UserResult> {

	public JsonResetPasswordHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
		
		final UserEntity _ent = new UserPersistence().getByRecId(this.param.getId());
		_ent.setPassword(this.param.getPassword());
		
		_ent.modify();
		
		final UserItem _item = new UserItem();
		_item.setId(_ent.getRecId());
		_item.setName(_ent.getName());
		_item.setPassword(_ent.getPassword());
		_item.setType(_ent.getType());
		
		final List<UserItem> _items = new ArrayList<UserItem>();
		_items.add(_item);
		
		this.result.setItems(_items);
		this.result.setCode(0);
		this.result.setMessage("Sukses");
		
	}

}
