package org.radot.json.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.radot.json.beans.DetPenjualanItem;
import org.radot.json.beans.DetPenjualanResult;
import org.radot.json.beans.PageDetPenjualanParam;
import org.radot.json.servlet.JsonServletHandler;

public class JsonPageDetPenjualanHandler extends JsonServletHandler<PageDetPenjualanParam, DetPenjualanResult> {

	public JsonPageDetPenjualanHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}
	
	@SuppressWarnings("unchecked")
	@Override()
	public void process() throws Throwable {
		
		DetPenjualanItem _item;
		final List<DetPenjualanItem> _items = new ArrayList<DetPenjualanItem>();
//		int limit = 10;
		String page = this.param.getPage();
		final HttpSession _session = this.request.getSession();
		HashMap<String, ArrayList<DetPenjualanItem>> _c = (HashMap<String, ArrayList<DetPenjualanItem>>) _session.getAttribute("paggingDataDetPenjualan");
		//System.out.println(_c.size()+ " data ");
		ArrayList<DetPenjualanItem> _detpenjualan = _c.get(page);
		if (null != _detpenjualan && !_detpenjualan.isEmpty()) {
			for (final DetPenjualanItem _dpenj: _detpenjualan) {
				_item = new DetPenjualanItem();
				_item.setId(_dpenj.getId());
				if(_dpenj.getOrderNumb() != null){
					_item.setOrderNumb(_dpenj.getOrderNumb());
					}
				if(_dpenj.getFakturNumb() != null){
					_item.setFakturNumb(_dpenj.getFakturNumb());
					}
				if(_dpenj.getStockId() != null){
					_item.setStockId(_dpenj.getStockId());
					}
				if(_dpenj.getPenjualanId() != null){
					_item.setPenjualanId(_dpenj.getPenjualanId());
					}
				if(_dpenj.getProdId() != null){
					_item.setProdId(_dpenj.getProdId());
					}
				if(_dpenj.getProductCode() != null){
					_item.setProductCode(_dpenj.getProductCode());
					}
				if(_dpenj.getProductName() != null){
					_item.setProductName(_dpenj.getProductName());
					}
				if(_dpenj.getCustomerId() != null){
					_item.setCustomerId(_dpenj.getCustomerId());
					}
				if(_dpenj.getCustomerCode() != null){
					_item.setCustomerCode(_dpenj.getCustomerCode());
					}
				if(_dpenj.getCustomerName() != null){
					_item.setCustomerName(_dpenj.getCustomerName());
					}
				if(_dpenj.getCustomerAddr() != null){
					_item.setCustomerAddr(_dpenj.getCustomerAddr());
					}
				if(_dpenj.getCustomerCity() != null){
					_item.setCustomerCity(_dpenj.getCustomerCity());
					}
				if(_dpenj.getSalesmanId() != null){
					_item.setSalesmanId(_dpenj.getSalesmanId());
					}
				if(_dpenj.getSalesmanCode() != null){
					_item.setSalesmanCode(_dpenj.getSalesmanCode());
					}
				if(_dpenj.getSalesmanName() != null){
					_item.setSalesmanName(_dpenj.getSalesmanName());
					}
				if(_dpenj.getDisc() != null){
					_item.setDisc(_dpenj.getDisc());
					}
				if(_dpenj.getTotQtyJualPcs() != null){
					_item.setTotQtyJualPcs(_dpenj.getTotQtyJualPcs());
					}
				if(_dpenj.getTotQtyJualCtn() != null){
					_item.setTotQtyJualCtn(_dpenj.getTotQtyJualCtn());
					}
				if(_dpenj.getHargaJualIsiCtn() != null){
					_item.setHargaJualIsiCtn(_dpenj.getHargaJualIsiCtn());
					}
				if(_dpenj.getHargaJualNettoCtn() != null){
					_item.setHargaJualNettoCtn(_dpenj.getHargaJualNettoCtn());
					}
				if(_dpenj.getHargaJualBrutoCtn() != null){
					_item.setHargaJualBrutoCtn(_dpenj.getHargaJualBrutoCtn());
					}
				if(_dpenj.getHargaJualCtn() != null){
					_item.setHargaJualCtn(_dpenj.getHargaJualCtn());
					}
				if(_dpenj.getHargaJualCtnStd() != null){
					_item.setHargaJualCtnStd(_dpenj.getHargaJualCtnStd());
					}
				if(_dpenj.getHargaJualPcs() != null){
					_item.setHargaJualPcs(_dpenj.getHargaJualPcs());
					}
				if(_dpenj.getHargaJualPcsDisc() != null){
					_item.setHargaJualPcsDisc(_dpenj.getHargaJualPcsDisc());
					}
				if(_dpenj.getHargaJualPcsNetto()!=null){
					_item.setHargaJualPcsNetto(_dpenj.getHargaJualPcsNetto());
				}
				if(_dpenj.getDisc() != null){
					_item.setDisc(_dpenj.getDisc());
					}
				if(_dpenj.getPpn() != null){
					_item.setPpn(_dpenj.getPpn());
					}
				if(_dpenj.getTotJualBrutoIdr() != null){
					_item.setTotJualBrutoIdr(_dpenj.getTotJualBrutoIdr());
					}
				if(_dpenj.getTotJualNettoIdr() != null){
					_item.setTotJualNettoIdr(_dpenj.getTotJualNettoIdr());
					}
				if(_dpenj.getTotPenjualanFaktur() != null){
					_item.setTotPenjualanFaktur(_dpenj.getTotPenjualanFaktur());
					}
				if(_dpenj.getTotJualNettoIdrBeforePpn() != null){
					_item.setTotJualNettoIdrBeforePpn(_dpenj.getTotJualNettoIdrBeforePpn());
					}
				if(_dpenj.getSisaStokEdit() != null){
					_item.setSisaStokEdit(_dpenj.getSisaStokEdit());
					}
				if(_dpenj.getIsiCtn() != null){
					_item.setIsiCtn(_dpenj.getIsiCtn());
					}
				if(_dpenj.getIsiPcs() != null){
					_item.setIsiPcs(_dpenj.getIsiPcs());
					}
				if(_dpenj.getKeterangan() != null){
					_item.setKeterangan(_dpenj.getKeterangan());
					}
				
				_items.add(_item);
			}
			
		}
		this.result.setItems(_items);

	}
	
	

}
