package org.radot.json.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.radot.hibernate.entities.SalesmanEntity;
import org.radot.hibernate.persistences.SalesmanPersistence;
import org.radot.json.beans.SalesmanInputParam;
import org.radot.json.beans.SalesmanItem;
import org.radot.json.beans.SalesmanResult;
import org.radot.json.servlet.JsonServletHandler;

public class JsonSalesmanModHandler extends JsonServletHandler<SalesmanInputParam, SalesmanResult> {

	public JsonSalesmanModHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		
		super(request, response);
	}
	
	@Override()
	public void process() throws Throwable {
		String _valA = "";
		
		SalesmanEntity _ent = new SalesmanPersistence().getByRecId(this.param.getId());
		if(_ent != null){
			if (!_ent.getName().equalsIgnoreCase(this.param.getName())){
				SalesmanEntity _checkSalesman = new SalesmanPersistence().getByName(this.param.getName());
				if (_checkSalesman != null){
					this.result.setCode(1);
					this.result.setMessage("Nama sudah terdaftar");
					return;
				}
				else {
					_ent.setName(this.param.getName());
				}
			}
			if (!_ent.getCode().equalsIgnoreCase(this.param.getCode())){
				SalesmanEntity _checkSalesman = new SalesmanPersistence().getByCode(this.param.getCode());
				if (_checkSalesman != null){
					this.result.setCode(1);
					this.result.setMessage("Kode sudah terdaftar");
					return;
				}
				else {
					_ent.setCode(this.param.getCode());
				}
			}
			
			 _valA = "true";
		}
		
		
		/*_ent.setRecId(this.param.getId());*/
		
		
		_ent.modify();
		
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
