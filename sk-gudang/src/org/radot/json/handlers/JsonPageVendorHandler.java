package org.radot.json.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.radot.json.beans.PageVendorParam;
import org.radot.json.beans.VendorItem;
import org.radot.json.beans.VendorResult;
import org.radot.json.servlet.JsonServletHandler;

public class JsonPageVendorHandler extends JsonServletHandler<PageVendorParam, VendorResult> {

	public JsonPageVendorHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}
	
	@Override()
	public void process() throws Throwable {
		
		VendorItem _item;
		final List<VendorItem> _items = new ArrayList<VendorItem>();
//		int limit = 10;
		String page = this.param.getPage();
		final HttpSession _session = this.request.getSession();
		@SuppressWarnings("unchecked")
		HashMap<String, ArrayList<VendorItem>> _c = (HashMap<String, ArrayList<VendorItem>>) _session.getAttribute("paggingDataVendor");
		//System.out.println(_c.size()+ " data ");
		ArrayList<VendorItem> _vendor = _c.get(page);
		for (final VendorItem _ent: _vendor) {
			System.out.println(_ent.getName());
		}
		if (null != _vendor && !_vendor.isEmpty()) {
			for (final VendorItem _vend: _vendor) {
				_item = new VendorItem();
				_item.setId(_vend.getId());
				_item.setName(_vend.getName());
				_item.setAddress1(_vend.getAddress1());
				if(_vend.getAddress2() != null){
					_item.setAddress2(_vend.getAddress2());
				}
				if(_vend.getCargo() != null){
					_item.setCargo(_vend.getCargo());
				}
				if(_vend.getCity() != null){
				_item.setCity(_vend.getCity());
				}
				if(_vend.getCountry() != null){
				_item.setCountry(_vend.getCountry());
				}
				if(_vend.getVta() != null){
				_item.setVta(_vend.getVta());
				}
				
				if(_vend.getFax() != null){
					_item.setFax(_vend.getFax());
				}
				_item.setEmail(_vend.getEmail());
				if(_vend.getFax() != null){
					_item.setFax(_vend.getFax());
				}
				if(_vend.getPhone() != null){
					_item.setPhone(_vend.getPhone());
				}
				if(_vend.getTax() != null){
					_item.setTax(_vend.getTax());
				}
				
				
				_items.add(_item);
			}
		}
		this.result.setItems(_items);

	}
	
	

}
