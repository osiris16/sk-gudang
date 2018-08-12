package org.radot.json.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.radot.hibernate.entities.CustomerEntity;
import org.radot.hibernate.persistences.CustomerPersistence;
import org.radot.json.beans.CustomerInputParam;
import org.radot.json.beans.CustomerItem;
import org.radot.json.beans.CustomerResult;
import org.radot.json.servlet.JsonServletHandler;

public class JsonCustomerModHandler extends JsonServletHandler<CustomerInputParam, CustomerResult> {

	public JsonCustomerModHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		
		super(request, response);
	}
	
	@Override()
	public void process() throws Throwable {
		String _valA = "";
		
		CustomerEntity _ent = new CustomerPersistence().getByRecId(this.param.getId());
		if(_ent != null){
			if (!_ent.getName().equalsIgnoreCase(this.param.getName())){
				CustomerEntity _checkCustName = new CustomerPersistence().getByName(this.param.getName());
				if (_checkCustName != null){
					this.result.setCode(1);
					this.result.setMessage("Nama sudah terdaftar");
					return;
				}
				else {
					String _name = this.param.getName();
					_name = _name.replace("'", "");
					_ent.setName(_name);
				}
				_valA = "true";
			}
			
			if (!_ent.getCode().equalsIgnoreCase(this.param.getCode())){
				CustomerEntity _checkCustCode = new CustomerPersistence().getByCode(this.param.getCode());
				if (_checkCustCode != null){
					this.result.setCode(2);
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
		
		_ent.setAddress1(this.param.getAddress1());
		_ent.setAddress2(this.param.getAddress2());
		_ent.setCity(this.param.getCity());
		_ent.setContact(this.param.getContact());
		_ent.setPhone(this.param.getPhone());
		_ent.setFax(this.param.getFax());
		_ent.setTax(this.param.getTax());
		_ent.modify();
		
		final CustomerItem _item = new CustomerItem();
		_item.setId(_ent.getRecId());
		_item.setCode(_ent.getCode());
		_item.setName(_ent.getName());
		_item.setAddress1(_ent.getAddress1());
		_item.setAddress2(_ent.getAddress2());
		_item.setCity(_ent.getCity());
		_item.setContact(_ent.getContact());
		_item.setPhone(_ent.getPhone());
		_item.setFax(_ent.getFax());
		_item.setTax(_ent.getTax());

		final List<CustomerItem> _items = new ArrayList<CustomerItem>();
		_items.add(_item);
		
		this.result.setItems(_items);
		this.result.setCode(0);
		this.result.setMessage("Sukses");
	}

}
