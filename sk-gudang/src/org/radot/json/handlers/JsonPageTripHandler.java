package org.radot.json.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.radot.json.beans.PageTripParam;
import org.radot.json.beans.TripItem;
import org.radot.json.beans.TripResult;
import org.radot.json.servlet.JsonServletHandler;

public class JsonPageTripHandler extends JsonServletHandler<PageTripParam, TripResult> {

	public JsonPageTripHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}
	
	@Override()
	public void process() throws Throwable {
		
		TripItem _item;
		final List<TripItem> _items = new ArrayList<TripItem>();
//		int limit = 10;
		String page = this.param.getPage();
		final HttpSession _session = this.request.getSession();
		HashMap<String, ArrayList<TripItem>> _c = (HashMap<String, ArrayList<TripItem>>) _session.getAttribute("paggingDataTrip");
		//System.out.println(_c.size()+ " data ");
		ArrayList<TripItem> _trip = _c.get(page);
		if (null != _trip && !_trip.isEmpty()) {
			for (final TripItem _trp: _trip) {
				
				_item = new TripItem();
			
				if(_trp.getTrip_date() != null){
				_item.setTrip_date(_trp.getTrip_date());
				}
				if(_trp.getTrip_numb() != null){
				_item.setTrip_numb(_trp.getTrip_numb());
				}
				if(_trp.getTrip_dateNote() != null){
				_item.setTrip_dateNote(_trp.getTrip_dateNote());
				}
				if(_trp.getTrip_noteNumber() != null){
				_item.setTrip_noteNumber(_trp.getTrip_noteNumber());
				}
				if(_trp.getVendId() != null){
				_item.setVendId(_trp.getVendId());
				}
				if(_trp.getTrip_vendName() != null){
				_item.setTrip_vendName(_trp.getTrip_vendName());
				}
				if(_trp.getTrip_vendCountry() != null){
				_item.setTrip_vendCountry(_trp.getTrip_vendCountry());
				}
				if(_trp.getTrip_vta() != null){
				_item.setTrip_vta(_trp.getTrip_vta());
				}
				if(_trp.getCurrencyIDR() != null){
				_item.setCurrencyIDR(_trp.getCurrencyIDR());
				}
				if(_trp.getDateReceive() != null){
				_item.setDateReceive(_trp.getDateReceive());
				}
				if(_trp.getTotBeliBrutoIdr() != null){
				_item.setTotBeliBrutoIdr(_trp.getTotBeliBrutoIdr());
				}
				if(_trp.getTotBeliBrutoVta() != null){
				_item.setTotBeliBrutoVta(_trp.getTotBeliBrutoVta());
				}
				if(_trp.getTotBeliNettoIdr() != null){
				_item.setTotBeliNettoIdr(_trp.getTotBeliNettoIdr());
				}
				if(_trp.getTotBeliNettoVta() != null){
				_item.setTotBeliNettoVta(_trp.getTotBeliNettoVta());
				}
				if(_trp.getTotBeliCtn() != null){
				_item.setTotBeliCtn(_trp.getTotBeliCtn());
				}
				if(_trp.getTotCost() != null){
				_item.setTotCost(_trp.getTotCost());
				}
				if(_trp.getTotDisc() != null){
				_item.setTotDisc(_trp.getTotDisc());
				}
				if(_trp.getTotCostHelp() != null){
				_item.setTotCostHelp(_trp.getTotCostHelp());
				}
				if(_trp.getTotDiscHelp() != null){
				_item.setTotDiscHelp(_trp.getTotDiscHelp());
				}
				_item.setId(_trp.getId());
				_items.add(_item);
				
			}
		}
		this.result.setItems(_items);

	}
	
	

}
