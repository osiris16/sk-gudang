package org.radot.json.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.radot.hibernate.entities.PenjualanEntity;
import org.radot.hibernate.persistences.PenjualanPersistence;
import org.radot.json.beans.PenjualanInputParam;
import org.radot.json.beans.PenjualanItem;
import org.radot.json.beans.PenjualanResult;
import org.radot.json.servlet.JsonServletHandler;

public class JsonTerkirimHandler extends JsonServletHandler<PenjualanInputParam, PenjualanResult> {

	public JsonTerkirimHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		
		super(request, response);
	}
	
	@Override()
	public void process() throws Throwable {
		
		
		PenjualanEntity _ent = new PenjualanPersistence().getByRecId(this.param.getId());
		
		String _status = _ent.getTerkirim();
		
		if (_status==null){
			_ent.setTerkirim("Belum Terkirim");
			this.result.setCode(0);
			this.result.setMessage("Belum Terkirim");
			_ent.modify();
		}
		
		else if (_status.equalsIgnoreCase("Belum Terkirim"))
		{
			_ent.setTerkirim("Terkirim");
			this.result.setCode(0);
			this.result.setMessage("Terkirim");
			_ent.modify();
		}
		
		else if (_status.equalsIgnoreCase("Terkirim"))
		{
			_ent.setTerkirim("Belum Terkirim");
			this.result.setCode(0);
			this.result.setMessage("Belum Terkirim");
			_ent.modify();
		}
		
		
		
		final PenjualanItem _item = new PenjualanItem();
		_item.setOrderNumb(_ent.getOrderNumb());
		_item.setTerkirim(_ent.getTerkirim());
		
		
		
		final List<PenjualanItem> _items = new ArrayList<PenjualanItem>();
		_items.add(_item);
		
		this.result.setItems(_items);
		this.result.setCode(0);
		this.result.setMessage(_status);
	}

}
