package org.radot.json.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.radot.json.beans.PageProductGroupParam;
import org.radot.json.beans.ProductGroupItem;
import org.radot.json.beans.ProductGroupResult;
import org.radot.json.servlet.JsonServletHandler;

public class JsonPageProductGroupHandler extends JsonServletHandler<PageProductGroupParam, ProductGroupResult> {

	public JsonPageProductGroupHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}
	
	@Override()
	public void process() throws Throwable {
		
		ProductGroupItem _item;
		final List<ProductGroupItem> _items = new ArrayList<ProductGroupItem>();
//		int limit = 10;
		String page = this.param.getPage();
		final HttpSession _session = this.request.getSession();
		@SuppressWarnings("unchecked")
		HashMap<String, ArrayList<ProductGroupItem>> _c = (HashMap<String, ArrayList<ProductGroupItem>>) _session.getAttribute("paggingDataProductGroup");
		//System.out.println(_c.size()+ " data ");
		ArrayList<ProductGroupItem> _group = _c.get(page);
		
		for (final ProductGroupItem _ent: _group) {
			System.out.println(_ent.getName());
		}
		if (null != _group && !_group.isEmpty()) {
			for (final ProductGroupItem _grp: _group) {
				_item = new ProductGroupItem();
				_item.setId(_grp.getId());
				if(_grp.getName() != null){
				_item.setName(_grp.getName());
				}
				_items.add(_item);
				
			}
		}
		this.result.setItems(_items);

	}
	
	

}
