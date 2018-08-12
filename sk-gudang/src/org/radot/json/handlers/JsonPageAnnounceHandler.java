package org.radot.json.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.radot.json.beans.AnnounceItem;
import org.radot.json.beans.AnnounceResult;
import org.radot.json.beans.PageAnnounceParam;
import org.radot.json.servlet.JsonServletHandler;

public class JsonPageAnnounceHandler extends JsonServletHandler<PageAnnounceParam, AnnounceResult> {

	public JsonPageAnnounceHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}
	
	@Override()
	public void process() throws Throwable {
		
		AnnounceItem _item;
		final List<AnnounceItem> _items = new ArrayList<AnnounceItem>();
//		int limit = 10;
		String page = this.param.getPage();
		final HttpSession _session = this.request.getSession();
		@SuppressWarnings("unchecked")
		HashMap<String, ArrayList<AnnounceItem>> _c = (HashMap<String, ArrayList<AnnounceItem>>) _session.getAttribute("paggingDataAnnounce");
		//System.out.println(_c.size()+ " data ");
		ArrayList<AnnounceItem> _announce = _c.get(page);
		
		if (null != _announce && !_announce.isEmpty()) {
			for (final AnnounceItem _anc: _announce) {
				_item = new AnnounceItem();
				_item.setId(_anc.getId());
				_item.setDate(_anc.getDate());
				_item.setDestination(_anc.getDestination());
				_item.setContent(_anc.getContent());
				
				
				
				_items.add(_item);
			}
		}
		this.result.setItems(_items);

	}
	
	

}
