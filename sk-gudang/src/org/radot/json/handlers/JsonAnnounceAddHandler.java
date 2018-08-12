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

public class JsonAnnounceAddHandler extends JsonServletHandler<AnnounceInputParam, AnnounceResult> {

	public JsonAnnounceAddHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
		
		final AnnounceEntity _ent = new AnnounceEntity();
		_ent.setContent(this.param.getContent());
		_ent.setDestination(this.param.getDestination());
		
		Date _date = new Date();
		_date.setTime(this.param.getDate());
		_ent.setDate(_date);
		_ent.save();
		
		final AnnounceItem _item = new AnnounceItem();
		_item.setDate(_ent.getDate());
		_item.setContent(_ent.getContent());
		
		
		_item.setDestination(_ent.getDestination());
		
		final List<AnnounceItem> _items = new ArrayList<AnnounceItem>();
		_items.add(_item);
		
		this.result.setItems(_items);
		this.result.setCode(0);
		this.result.setMessage("Sukses");
	}

}
