package org.radot.json.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.radot.json.beans.PageReturPenjualanParam;
import org.radot.json.beans.ReturPenjualanItem;
import org.radot.json.beans.ReturPenjualanResult;
import org.radot.json.servlet.JsonServletHandler;

public class JsonPageReturPenjualanHandler extends JsonServletHandler<PageReturPenjualanParam, ReturPenjualanResult> {

	public JsonPageReturPenjualanHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}
	
	@Override()
	public void process() throws Throwable {
		
		ReturPenjualanItem _item;
		final List<ReturPenjualanItem> _items = new ArrayList<ReturPenjualanItem>();
//		int limit = 10;
		String page = this.param.getPage();
		final HttpSession _session = this.request.getSession();
		@SuppressWarnings("unchecked")
		HashMap<String, ArrayList<ReturPenjualanItem>> _c = (HashMap<String, ArrayList<ReturPenjualanItem>>) _session.getAttribute("paggingDataReturPenjualan");
		//System.out.println(_c.size()+ " data ");
		ArrayList<ReturPenjualanItem> _returpenjualan = _c.get(page);
		
		if (null != _returpenjualan && !_returpenjualan.isEmpty()) {
			for (final ReturPenjualanItem _returj: _returpenjualan) {
				_item = new ReturPenjualanItem();
				
				if(_returj != null){
					
				_item.setId(_returj.getId());
				if(_returj.getNoRetur() != null){
				_item.setNoRetur(_returj.getNoRetur());
				}
				if(_returj.getNoRetur() != null){
				_item.setDateRetur(_returj.getDateRetur());
				}
				if(_returj.getOrderNumb() != null){
				_item.setOrderNumb(_returj.getOrderNumb());
				}
				if(_returj.getProductCode() != null){
				_item.setProductCode(_returj.getProductCode());
				}
				if(_returj.getProductName() != null){
				_item.setProductName(_returj.getProductName());
				}
				if(_returj.getCustomerName() != null){
				_item.setCustomerName(_returj.getCustomerName());
				}
				if(_returj.getTotQtyReturCtn() != null){
				_item.setTotQtyReturCtn(_returj.getTotQtyReturCtn());
				}
				if(_returj.getNilaiNettoRetur() != null){
				_item.setNilaiNettoRetur(_returj.getNilaiNettoRetur());
				}
				if(_returj.getHargaJualCtnBruto() != null){
				_item.setHargaJualCtnBruto(_returj.getHargaJualCtnBruto());
				}
				if(_returj.getPenerimaRetur() != null){
				_item.setPenerimaRetur(_returj.getPenerimaRetur());
				}
				if(_returj.getAlasanRetur() != null){
				_item.setAlasanRetur(_returj.getAlasanRetur());
				}
				if(_returj.getSalesmanCode() != null){
				_item.setSalesmanCode(_returj.getSalesmanCode());
				}
				}
				
				_items.add(_item);
			}
		}
		this.result.setItems(_items);

	}
	
	

}
