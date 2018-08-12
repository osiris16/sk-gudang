package org.radot.json.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.radot.hibernate.entities.SalesmanEntity;
import org.radot.json.beans.SalesmanInputParam;
import org.radot.json.beans.SalesmanItem;
import org.radot.json.beans.SalesmanResult;
import org.radot.json.servlet.JsonServletHandler;

public class JsonSalesmanDelHandler extends JsonServletHandler<SalesmanInputParam, SalesmanResult> {

	public JsonSalesmanDelHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
		
		final SalesmanEntity _ent = new SalesmanEntity();
		_ent.setRecId(this.param.getId());
		_ent.erase();
		
		final SalesmanItem _item = new SalesmanItem();
		_item.setId(_ent.getRecId());
		_item.setCode(_ent.getCode());
		_item.setName(_ent.getName());
		
		final List<SalesmanItem> _items = new ArrayList<SalesmanItem>();
		_items.add(_item);
		
		this.result.setItems(_items);
		this.result.setCode(0);
		this.result.setMessage("Sukses");
	}

}
