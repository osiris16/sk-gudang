package org.radot.json.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.radot.hibernate.entities.PenjualanEntity;
import org.radot.json.beans.PenjualanInputParam;
import org.radot.json.beans.PenjualanItem;
import org.radot.json.beans.PenjualanResult;
import org.radot.json.servlet.JsonServletHandler;

public class JsonPenjualanDelHandler extends JsonServletHandler<PenjualanInputParam, PenjualanResult> {

	public JsonPenjualanDelHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
		
		final PenjualanEntity _ent = new PenjualanEntity();
		_ent.setRecId(this.param.getId());
		_ent.erase();
		
		final PenjualanItem _item = new PenjualanItem();
		_item.setOrderNumb(_ent.getOrderNumb());
		_item.setOrderDate(_ent.getOrderDate());
		_item.setFakturNumb(_ent.getFakturNumb());
	
		
		final List<PenjualanItem> _items = new ArrayList<PenjualanItem>();
		_items.add(_item);
		
		this.result.setItems(_items);
		this.result.setCode(0);
		this.result.setMessage("Data Berhasil Dihapus");
	}

}

