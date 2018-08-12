package org.radot.json.handlers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.radot.hibernate.entities.AnnounceEntity;
import org.radot.json.beans.AnnounceInputParam;
import org.radot.json.beans.AnnounceItem;
import org.radot.json.beans.AnnounceResult;
import org.radot.json.servlet.JsonServletHandler;

public class JsonAnnounceModHandler extends JsonServletHandler<AnnounceInputParam, AnnounceResult> {

	public JsonAnnounceModHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
		
		final AnnounceEntity _ent = new AnnounceEntity();
		_ent.setRecId(this.param.getId());
		_ent.setContent(this.param.getContent());
		_ent.setDestination(this.param.getDestination());
		_ent.setDate(new Date(this.param.getDate()));
		_ent.modify();
		
		final AnnounceItem _item = new AnnounceItem();
		_item.setContent(_ent.getContent());
		_item.setDate(_ent.getDate());
		_item.setDestination(_ent.getDestination());
		
		final List<AnnounceItem> _items = new ArrayList<AnnounceItem>();
		_items.add(_item);
		
		this.result.setItems(_items);
		this.result.setCode(0);
	}

}
