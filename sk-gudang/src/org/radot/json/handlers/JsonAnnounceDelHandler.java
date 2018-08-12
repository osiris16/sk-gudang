package org.radot.json.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.radot.hibernate.entities.AnnounceEntity;
import org.radot.json.beans.AnnounceInputParam;
import org.radot.json.beans.AnnounceItem;
import org.radot.json.beans.AnnounceResult;
import org.radot.json.servlet.JsonServletHandler;

public class JsonAnnounceDelHandler extends JsonServletHandler<AnnounceInputParam, AnnounceResult> {

	public JsonAnnounceDelHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
		
		final AnnounceEntity _ent = new AnnounceEntity();
		_ent.setRecId(this.param.getId());
		
		
		_ent.erase();
		
		final AnnounceItem _item = new AnnounceItem();
		_item.setDate(_ent.getDate());
		_item.setDestination(_ent.getDestination());
		_item.setContent(_ent.getContent());
		

		final List<AnnounceItem> _items = new ArrayList<AnnounceItem>();
		_items.add(_item);
		
		this.result.setItems(_items);
		this.result.setCode(0);
		this.result.setMessage("Sukses");
	}

}
