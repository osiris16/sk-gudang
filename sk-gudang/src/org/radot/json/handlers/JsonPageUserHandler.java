package org.radot.json.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.radot.json.beans.PageUserParam;
import org.radot.json.beans.UserItem;
import org.radot.json.beans.UserResult;
import org.radot.json.servlet.JsonServletHandler;

public class JsonPageUserHandler extends JsonServletHandler<PageUserParam, UserResult> {

	public JsonPageUserHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}
	
	@Override()
	public void process() throws Throwable {
		
		UserItem _item;
		final List<UserItem> _items = new ArrayList<UserItem>();
//		int limit = 10;
		String page = this.param.getPage();
		final HttpSession _session = this.request.getSession();
		@SuppressWarnings("unchecked")
		HashMap<String, ArrayList<UserItem>> _c = (HashMap<String, ArrayList<UserItem>>) _session.getAttribute("paggingData");
		//System.out.println(_c.size()+ " data ");
		ArrayList<UserItem> _user = _c.get(page);
		for (final UserItem _ent: _user) {
			System.out.println(_ent.getName());
		}
		if (null != _user && !_user.isEmpty()) {
			for (final UserItem _usr: _user) {
				_item = new UserItem();
				_item.setId(_usr.getId());
				_item.setName(_usr.getName());
				_item.setType(_usr.getType());
				_items.add(_item);
			}
		}
		this.result.setItems(_items);

	}
	
	

}
