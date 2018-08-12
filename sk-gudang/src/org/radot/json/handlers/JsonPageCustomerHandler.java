package org.radot.json.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.radot.json.beans.CustomerItem;
import org.radot.json.beans.CustomerResult;
import org.radot.json.beans.PageCustomerParam;
import org.radot.json.servlet.JsonServletHandler;

public class JsonPageCustomerHandler extends JsonServletHandler<PageCustomerParam, CustomerResult> {

	public JsonPageCustomerHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}
	
	@Override()
	public void process() throws Throwable {
		
		CustomerItem _item;
		final List<CustomerItem> _items = new ArrayList<CustomerItem>();
//		int limit = 10;
		String page = this.param.getPage();
		final HttpSession _session = this.request.getSession();
		@SuppressWarnings("unchecked")
		HashMap<String, ArrayList<CustomerItem>> _c = (HashMap<String, ArrayList<CustomerItem>>) _session.getAttribute("paggingDataCustomer");
		//System.out.println(_c.size()+ " data ");
		ArrayList<CustomerItem> _customer = _c.get(page);
		for (final CustomerItem _ent: _customer) {
			System.out.println(_ent.getName());
		}
		if (null != _customer && !_customer.isEmpty()) {
			for (final CustomerItem _cust: _customer) {
				_item = new CustomerItem();
				_item.setId(_cust.getId());
				if(_cust.getAddress1() != null){
				_item.setAddress1(_cust.getAddress1());
				}
				if(_cust.getAddress2() != null){
				_item.setAddress2(_cust.getAddress2());
				}
				if(_cust.getCity() != null){
				_item.setCity(_cust.getCity());
				}
				if(_cust.getCode() != null){
				_item.setCode(_cust.getCode());
				}
				if(_cust.getContact() != null){
				_item.setContact(_cust.getContact());
				
				}if(_cust.getFax() != null){
				_item.setFax(_cust.getFax());
				}
				if(_cust.getName() != null){
				_item.setName(_cust.getName());
				}
				if(_cust.getPhone() != null){
				_item.setPhone(_cust.getPhone());
				}
				if(_cust.getTax() != null){
				_item.setTax(_cust.getTax());
				}
				
				_items.add(_item);
			}
		}
		this.result.setItems(_items);

	}
	
	

}
