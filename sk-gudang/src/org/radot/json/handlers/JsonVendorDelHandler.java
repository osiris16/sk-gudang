package org.radot.json.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.radot.hibernate.entities.VendorEntity;
import org.radot.json.beans.VendorInputParam;
import org.radot.json.beans.VendorItem;
import org.radot.json.beans.VendorResult;
import org.radot.json.servlet.JsonServletHandler;

public class JsonVendorDelHandler extends JsonServletHandler<VendorInputParam, VendorResult> {

	public JsonVendorDelHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
		
		final VendorEntity _ent = new VendorEntity();
		_ent.setRecId(this.param.getId());
		_ent.erase();
		
		final VendorItem _item = new VendorItem();
		_item.setId(_ent.getRecId());
		_item.setName(_ent.getName());
		_item.setAddress1(_ent.getAddress1());
		_item.setAddress2(_ent.getAddress2());
		_item.setCargo(_ent.getCargo());
		_item.setCity(_ent.getCity());
		_item.setCountry(_ent.getCountry());
		_item.setVta(_ent.getVta());
		_item.setEmail(_ent.getEmail());
		_item.setFax(_ent.getFax());
		_item.setPhone(_ent.getPhone());
		_item.setTax(_ent.getTax());

		final List<VendorItem> _items = new ArrayList<VendorItem>();
		_items.add(_item);
		
		this.result.setItems(_items);
		this.result.setCode(0);
		this.result.setMessage("Sukses");
	}

}
