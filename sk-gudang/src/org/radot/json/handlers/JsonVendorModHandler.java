package org.radot.json.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.radot.hibernate.entities.VendorEntity;
import org.radot.hibernate.persistences.VendorPersistence;
import org.radot.json.beans.VendorInputParam;
import org.radot.json.beans.VendorItem;
import org.radot.json.beans.VendorResult;
import org.radot.json.servlet.JsonServletHandler;

public class JsonVendorModHandler extends JsonServletHandler<VendorInputParam, VendorResult> {

	public JsonVendorModHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		
		super(request, response);
	}
	
	@Override()
	public void process() throws Throwable {
		String _valA = "";
		
		VendorEntity _ent = new VendorPersistence().getByRecId(this.param.getId());
		if(_ent != null){
			if (!_ent.getName().equalsIgnoreCase(this.param.getName())){
				VendorEntity _checkVend = new VendorPersistence().getByName(this.param.getName());
				if (_checkVend != null){
					this.result.setCode(1);
					this.result.setMessage("Nama sudah terdaftar");
					return;
				}
				else {
					_ent.setName(this.param.getName());
				}
			}
			
			 _valA = "true";
		}
		
		
		/*_ent.setRecId(this.param.getId());*/
		
		_ent.setAddress1(this.param.getAddress1());
		_ent.setAddress2(this.param.getAddress2());
		_ent.setCargo(this.param.getCargo());
		_ent.setCity(this.param.getCity());
		_ent.setCountry(this.param.getCountry());
		_ent.setVta(this.param.getVta());
		_ent.setEmail(this.param.getEmail());
		_ent.setFax(this.param.getFax());
		_ent.setPhone(this.param.getPhone());
		_ent.setTax(this.param.getTax());
		_ent.modify();
		
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
