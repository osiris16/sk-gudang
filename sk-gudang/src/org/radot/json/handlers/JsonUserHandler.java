package org.radot.json.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.radot.hibernate.entities.BaseEntity;
import org.radot.hibernate.entities.UserEntity;
import org.radot.hibernate.persistences.UserPersistence;
import org.radot.json.beans.UserItem;
import org.radot.json.beans.UserResult;
import org.radot.json.servlet.JsonParam;
import org.radot.json.servlet.JsonServletHandler;

public class JsonUserHandler extends JsonServletHandler<JsonParam, UserResult> {

	public JsonUserHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
		
		UserItem _item;
		final List<UserItem> _items = new ArrayList<UserItem>();
//		int limit = 10;
//		long offset = Integer.parseInt(this.param.getOffset());
		int page = 0;
		final List<UserEntity> _entst = BaseEntity.list(UserEntity.class, null, null);
		UserPersistence _w =  new UserPersistence();
		final HttpSession _session = this.request.getSession();
		
		final List<UserEntity> _entList = BaseEntity.listDataOffset(UserEntity.class, null,null,  10, 1l);
		Long _lPage = 0l;
		if (null != _entList && !_entList.isEmpty()) {
			for (final UserEntity _ent: _entList) {
				_item = new UserItem();
				_item.setId(_ent.getRecId());
				_item.setName(_ent.getName());
				_item.setType(_ent.getType());
				_items.add(_item);
				_lPage = _ent.getRecId();
			}
			
		}
	
		_session.setAttribute("itemsSalesman", _items);
		_session.setAttribute("offsetLast", _lPage);
		_session.setAttribute("totalData",  _entst.size());
	
		_session.setAttribute("pageData1", (ArrayList<UserItem>) _items);
		//System.out.println(_entst.size()+"  s");
		int _totPageMod = 0;
		_totPageMod = _entst.size()/10;
		if(_entst.size()%10 != 0){
			_totPageMod = _entst.size()/10 +1;
		}
	
		this.result.setPage(String.valueOf(_totPageMod));
		this.result.setItems(_items);
		new Thread(new JsonUserHandler.BackgroundDataLoader(_session)).start();

	}
	public static class BackgroundDataLoader implements Runnable{
		private String _totData;
		private HttpSession session;
		private Integer intPage;
		private Integer intData;
		private Integer intCurrent;
		private ArrayList<UserItem> _firstPage;
		private long lastOffset;
		private List<UserItem> items;
		
		@SuppressWarnings("unchecked")
		public  BackgroundDataLoader(final HttpSession _session) {
			this.session = _session;
			//System.out.println("sss " + session.getAttribute("totalData"));
			this.intData = (Integer) session.getAttribute("totalData");
			this.intPage = this.intData/10;
			this._firstPage = (ArrayList<UserItem>) session.getAttribute("pageData1");
			List<UserItem> _items = new ArrayList<UserItem>();
			_items = (List<UserItem>) this.session.getAttribute("itemsUser");
		//	System.out.println("sss " + this.intData);
			this.lastOffset = (Long) this.session.getAttribute("offsetLast");
		}
		@Override
		public void run() {
			//System.out.println(" ssl "+ session.getAttribute("totalData"));
			HashMap<String, ArrayList<UserItem>> _wew = new HashMap<String, ArrayList<UserItem>>();
			_wew.put("page0", (ArrayList<UserItem>) this._firstPage);
			session.setAttribute("paggingData", _wew);
			for (int i = 0; i < this.intPage; i++) {
				final List<UserItem> _items = new ArrayList<UserItem>();
				List<UserEntity> _entList = null;
				try {
					_entList = BaseEntity.listDataOffset(UserEntity.class, null,null,  10, this.lastOffset);
				} catch (Exception e) {
					// TODO: handle exception
				}
				if (null != _entList && !_entList.isEmpty()) {
					UserItem _item;
					for (final UserEntity _ent: _entList) {
						_item = new UserItem();
						_item.setId(_ent.getRecId());
						_item.setName(_ent.getName());
						_item.setType(_ent.getType());
						_items.add(_item);
						this.lastOffset = _ent.getRecId();
					}
				}
				int _p = i+1;
				String _page = "page"+_p;
			//System.out.println(_page);
			_wew.put(_page, (ArrayList<UserItem>) _items);
			session.setAttribute("paggingData", _wew);
			}
		}
		
	}
}
