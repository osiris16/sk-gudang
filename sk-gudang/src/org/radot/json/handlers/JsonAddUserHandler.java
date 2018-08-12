package org.radot.json.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.radot.hibernate.entities.UserEntity;
import org.radot.json.beans.UserAddParam;
import org.radot.json.beans.UserItem;
import org.radot.json.beans.UserResult;
import org.radot.json.servlet.JsonServletHandler;

public class JsonAddUserHandler extends JsonServletHandler<UserAddParam, UserResult> {

	public JsonAddUserHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
		
		final UserEntity _ent = new UserEntity();
		_ent.setName(this.param.getName());
		
		_ent.setPassword("5f4dcc3b5aa765d61d8327deb882cf99");
		_ent.setType(this.param.getType());
		_ent.save();
		
		final UserItem _item = new UserItem();
		_item.setId(_ent.getRecId());
		_item.setName(_ent.getName());
		//_item.setPassword(_ent.getPassword());
		_item.setType(_ent.getType());
		
		final List<UserItem> _items = new ArrayList<UserItem>();
		_items.add(_item);
		
		this.result.setItems(_items);
		this.result.setCode(0);
		this.result.setMessage("Sukses");
		
	}

}
