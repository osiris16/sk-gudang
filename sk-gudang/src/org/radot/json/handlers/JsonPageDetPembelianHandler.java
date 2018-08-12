package org.radot.json.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.radot.json.beans.DetPembelianItem;
import org.radot.json.beans.DetPembelianResult;
import org.radot.json.beans.PageDetPembelianParam;
import org.radot.json.servlet.JsonServletHandler;

public class JsonPageDetPembelianHandler extends JsonServletHandler<PageDetPembelianParam, DetPembelianResult> {

	public JsonPageDetPembelianHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}
	
	@SuppressWarnings("unchecked")
	@Override()
	public void process() throws Throwable {
		
		DetPembelianItem _item;
		final List<DetPembelianItem> _items = new ArrayList<DetPembelianItem>();
//		int limit = 10;
		String page = this.param.getPage();
		final HttpSession _session = this.request.getSession();
		HashMap<String, ArrayList<DetPembelianItem>> _c = (HashMap<String, ArrayList<DetPembelianItem>>) _session.getAttribute("paggingDataDetPembelian");
		//System.out.println(_c.size()+ " data ");
		ArrayList<DetPembelianItem> _detpembelian = _c.get(page);
		if (null != _detpembelian && !_detpembelian.isEmpty()) {
			for (final DetPembelianItem _dpemb: _detpembelian) {
				_item = new DetPembelianItem();
				_item.setId(_dpemb.getId());
				if(_dpemb.getTripDate() != null){
				_item.setTripDate(_dpemb.getTripDate());
				}
				if(_dpemb.getTripNumber() != null){
				_item.setTripNumber(_dpemb.getTripNumber());
				}
				if(_dpemb.getTripNumbStok() != null){
					_item.setTripNumbStok(_dpemb.getTripNumbStok());
					}
				if(_dpemb.getProductCode() != null){
					_item.setProductCode(_dpemb.getProductCode());
					}
				if(_dpemb.getProductName() != null){
					_item.setProductName(_dpemb.getProductName());
					}
				if(_dpemb.getProductImage() != null){
					_item.setProductImage(_dpemb.getProductImage());
					}
				if(_dpemb.getIsiCtn() != null){
					_item.setIsiCtn(_dpemb.getIsiCtn());
					}
				if(_dpemb.getSatIsiCtn() != null){
					_item.setSatIsiCtn(_dpemb.getSatIsiCtn());
					}
				if(_dpemb.getIsiPcs() != null){
					_item.setIsiPcs(_dpemb.getIsiPcs());
					}
				if(_dpemb.getIdStock() != null){
					_item.setIdStock(_dpemb.getIdStock());
					}
				if(_dpemb.getIdTrip() != null){
					_item.setIdTrip(_dpemb.getIdTrip());
					}
				if(_dpemb.getTotQtyBeliPcs() != null){
					_item.setTotQtyBeliPcs(_dpemb.getTotQtyBeliPcs());
					}
				if(_dpemb.getTotQtyBeliCtn() != null){
					_item.setTotQtyBeliCtn(_dpemb.getTotQtyBeliCtn());
					}
				if(_dpemb.getTotStokPcs() != null){
					_item.setTotStokPcs(_dpemb.getTotStokPcs());
					}
				if(_dpemb.getStokCtn() != null){
					_item.setStokCtn(_dpemb.getStokCtn());
					}
				if(_dpemb.getVendVta() != null){
				_item.setVendVta(_dpemb.getVendVta());
					}
				if(_dpemb.getTripKurs() != null){
					_item.setTripKurs(_dpemb.getTripKurs());
					}
				if(_dpemb.getDisc() != null){
					_item.setDisc(_dpemb.getDisc());
					}
				if(_dpemb.getHargaJualIsiCtn() != null){
					_item.setHargaJualIsiCtn(_dpemb.getHargaJualIsiCtn());
					}
				if(_dpemb.getHargaJualCtn() != null){
					_item.setHargaJualCtn(_dpemb.getHargaJualCtn());
					}
				if(_dpemb.getHargaJualPcsVta() != null){
					_item.setHargaJualPcsVta(_dpemb.getHargaJualPcsVta());
					}
				if(_dpemb.getHargaJualCtnVta() != null){
					_item.setHargaJualCtnVta(_dpemb.getHargaJualCtnVta());
					}
				if(_dpemb.getTotHargaBrutBeliIdr() != null){
					_item.setTotHargaBrutBeliIdr(_dpemb.getTotHargaBrutBeliIdr());
					}
				if(_dpemb.getTotHargaBrutBeliVta() != null){
					_item.setTotHargaBrutBeliVta(_dpemb.getTotHargaBrutBeliVta());
					}
				if(_dpemb.getTotHargaNettBeliVta() != null){
					_item.setTotHargaNettBeliVta(_dpemb.getTotHargaNettBeliVta());
					}
				if(_dpemb.getTotHargaBrutBeliIdr() != null){
					_item.setTotHargaNettBeliIdr(_dpemb.getTotHargaNettBeliIdr());
					}
				
				if(_dpemb.getHargaJualPcs() != null){
					_item.setHargaJualPcs(_dpemb.getHargaJualPcs());
					}
				if(_dpemb.getTotHargaBrutBeliPcsIdr() != null){
					_item.setTotHargaBrutBeliPcsIdr(_dpemb.getTotHargaBrutBeliPcsIdr());
					}
				if(_dpemb.getTotHargaBrutBeliPcsVta() != null){
					_item.setTotHargaBrutBeliPcsVta(_dpemb.getTotHargaBrutBeliPcsVta());
					}
				if(_dpemb.getTotHargaNettBeliPcsVta() != null){
					_item.setTotHargaNettBeliPcsVta(_dpemb.getTotHargaNettBeliPcsVta());
					}
				if(_dpemb.getTotHargaNettBeliPcsIdr() != null){
					_item.setTotHargaNettBeliPcsIdr(_dpemb.getTotHargaNettBeliPcsIdr());
					}
				if(_dpemb.getCost() != null){
					_item.setCost(_dpemb.getCost());
					}
				if(_dpemb.getTripNumbStok() !=null){
					_item.setTripNumbStok(_dpemb.getTripNumbStok());
				}
				
				
				_items.add(_item);
			}
			
		}
		this.result.setItems(_items);

	}
	
	

}
