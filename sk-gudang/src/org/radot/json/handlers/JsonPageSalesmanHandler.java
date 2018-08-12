package org.radot.json.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.radot.json.beans.PageSalesmanParam;
import org.radot.json.beans.SalesmanItem;
import org.radot.json.beans.SalesmanResult;
import org.radot.json.servlet.JsonServletHandler;

public class JsonPageSalesmanHandler extends JsonServletHandler<PageSalesmanParam, SalesmanResult> {

	public JsonPageSalesmanHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}
	
	@Override()
	public void process() throws Throwable {
		
		SalesmanItem _item;
		final List<SalesmanItem> _items = new ArrayList<SalesmanItem>();
//		int limit = 10;
		String page = this.param.getPage();
		final HttpSession _session = this.request.getSession();
		@SuppressWarnings("unchecked")
		HashMap<String, ArrayList<SalesmanItem>> _c = (HashMap<String, ArrayList<SalesmanItem>>) _session.getAttribute("paggingDataSalesman");
		//System.out.println(_c.size()+ " data ");
		ArrayList<SalesmanItem> _salesman = _c.get(page);
		for (final SalesmanItem _ent: _salesman) {
			System.out.println(_ent.getName());
		}
		if (null != _salesman && !_salesman.isEmpty()) {
			for (final SalesmanItem _slsm: _salesman) {
				_item = new SalesmanItem();
				if(_slsm.getId() != null){
				_item.setId(_slsm.getId());
				}
				if(_slsm.getCode() != null){
				_item.setCode(_slsm.getCode());
				}
				if(_slsm.getName() != null){
				_item.setName(_slsm.getName());
				}
				
				_items.add(_item);
			}
		}
		this.result.setItems(_items);

	}
	
	

}


/*package org.radot.json.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.radot.json.beans.PageSalesmanParam;
import org.radot.json.beans.SalesmanItem;
import org.radot.json.beans.SalesmanResult;
import org.radot.json.servlet.JsonServletHandler;

public class JsonPageSalesmanHandler extends JsonServletHandler<PageSalesmanParam, SalesmanResult> {

	public JsonPageSalesmanHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}
	
	@Override()
	public void process() throws Throwable {
		
		SalesmanItem _item;
		final List<SalesmanItem> _items = new ArrayList<SalesmanItem>();
//		int limit = 10;
		String page = this.param.getPage();
		final HttpSession _session = this.request.getSession();
		@SuppressWarnings("unchecked")
		HashMap<String, ArrayList<SalesmanItem>> _c = (HashMap<String, ArrayList<SalesmanItem>>) _session.getAttribute("paggingData");
		System.out.println(_c.size()+ " data ");
		ArrayList<SalesmanItem> _salesman = _c.get(page);
		for (final SalesmanItem _ent: _salesman) {
			System.out.println(_ent.getName());
		}
		if (null != _salesman && !_salesman.isEmpty()) {
			for (final SalesmanItem _slsm: _salesman) {
				_item = new SalesmanItem();
				if(_slsm.getCode() != null){
				_item.setCode(_slsm.getCode());
				}
				if(_slsm.getId() != null){
				_item.setId(_slsm.getId());
				}
				if(_slsm.getName() != null){
				_item.setName(_slsm.getName());
				}
				_items.add(_item);
				
			}
		}
		this.result.setItems(_items);

	}
}
*/