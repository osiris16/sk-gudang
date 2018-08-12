package org.radot.json.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.radot.hibernate.entities.ProductGroupEntity;
import org.radot.json.beans.ProductGroupInputParam;
import org.radot.json.beans.ProductGroupItem;
import org.radot.json.beans.ProductGroupResult;
import org.radot.json.servlet.JsonServletHandler;

public class JsonProductGroupDelHandler extends JsonServletHandler<ProductGroupInputParam, ProductGroupResult> {

	public JsonProductGroupDelHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
		
		final ProductGroupEntity _ent = new ProductGroupEntity();
		_ent.setRecId(this.param.getId());
		_ent.erase();
		
		final ProductGroupItem _item = new ProductGroupItem();
		_item.setId(_ent.getRecId());
		_item.setName(_ent.getName());

		final List<ProductGroupItem> _items = new ArrayList<ProductGroupItem>();
		_items.add(_item);
		
		this.result.setItems(_items);
		this.result.setCode(0);
		this.result.setMessage("Data Berhasil Dihapus");
	}

}
