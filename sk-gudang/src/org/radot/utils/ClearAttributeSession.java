package org.radot.utils;

import javax.servlet.http.HttpSession;


public class ClearAttributeSession {
	public static String clear(final HttpSession session) {
		final HttpSession _session = session;
		_session.setAttribute("dotPage", "False");
		_session.removeAttribute("paggingData");
		_session.removeAttribute("paggingDataVendor");
		_session.removeAttribute("paggingDataTrip");
		_session.removeAttribute("paggingDataStock");
		_session.removeAttribute("paggingDataProductGroup");
		_session.removeAttribute("paggingDataDetPembelian");
		_session.removeAttribute("paggingDataSalesman");
		_session.removeAttribute("paggingDataCustomer");
		_session.removeAttribute("paggingDataPenjualan");
		_session.removeAttribute("paggingDataDetPenjualan");
		_session.removeAttribute("paggingDataReturPenjualan");
		
		
		
		return "";
	}
	/*public static void main(String[] args) {
		List<TripEntity> _entList = new ArrayList<TripEntity>();
		List<Order> _orderList = new ArrayList<Order>();
		_orderList.add(Order.desc("recId"));
		_entList = BaseEntity.listDataOffset(TripEntity.class, null,_orderList,  11, 1l);
		System.out.println("sadas");
	}*/
}
