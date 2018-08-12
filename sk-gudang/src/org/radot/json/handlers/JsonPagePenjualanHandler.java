package org.radot.json.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.radot.json.beans.PagePenjualanParam;
import org.radot.json.beans.PenjualanItem;
import org.radot.json.beans.PenjualanResult;
import org.radot.json.servlet.JsonServletHandler;

public class JsonPagePenjualanHandler extends JsonServletHandler<PagePenjualanParam, PenjualanResult> {

	public JsonPagePenjualanHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}
	
	@Override()
	public void process() throws Throwable {
		
		PenjualanItem _item;
		final List<PenjualanItem> _items = new ArrayList<PenjualanItem>();
//		int limit = 10;
		String page = this.param.getPage();
		final HttpSession _session = this.request.getSession();
		@SuppressWarnings("unchecked")
		HashMap<String, ArrayList<PenjualanItem>> _c = (HashMap<String, ArrayList<PenjualanItem>>) _session.getAttribute("paggingDataPenjualan");
		//System.out.println(_c.size()+ " data ");
		ArrayList<PenjualanItem> _penjualan = _c.get(page);
		
		if (null != _penjualan && !_penjualan.isEmpty()) {
			for (final PenjualanItem _penj: _penjualan) {
				_item = new PenjualanItem();
				if(_penj.getId() != null){
					_item.setId(_penj.getId());
					}
					if(_penj.getOrderDate() != null){
					_item.setOrderDate(_penj.getOrderDate());
					}
					if(_penj.getOrderNumb() != null){
					_item.setOrderNumb(_penj.getOrderNumb());
					}
					if(_penj.getFakturNumb() != null){
					_item.setFakturNumb(_penj.getFakturNumb());
					}
					
					if(_penj.getId() != null){
					_item.setCustId(_penj.getId());
					}
					
					if(_penj.getCustCode() != null){
					_item.setCustCode(_penj.getCustCode());
					}
					
					if(_penj.getCustName() != null){
					_item.setCustName(_penj.getCustName());
					}
					
					if(_penj.getCustAddress() != null){
					_item.setCustAddress(_penj.getCustAddress());
					}
					
					if(_penj.getCustAddress2() != null){
					_item.setCustAddress2(_penj.getCustAddress2());
					}
					
					if(_penj.getCustCity() != null){
					_item.setCustCity(_penj.getCustCity());
					}
					
					if(_penj.getId() != null){
						_item.setSalesId(_penj.getId());
						}
					if(_penj.getSalesCode() != null){
					_item.setSalesCode(_penj.getSalesCode());
					}
					
					if(_penj.getSalesName() != null){
					_item.setSalesName(_penj.getSalesName());
					}
					if(_penj.getKeterangan() != null){
					_item.setKeterangan(_penj.getKeterangan());
					}
					if(_penj.getTotDisc() != null){
						_item.setTotDisc(_penj.getTotDisc());
						}
					if(_penj.getTotJualBruto() != null){
						_item.setTotJualBruto(_penj.getTotJualBruto());
						}
					if(_penj.getTotJualNetto() != null){
						_item.setTotJualNetto(_penj.getTotJualNetto());
						}
					if(_penj.getPpn() != null){
						_item.setPpn(_penj.getPpn());
						}
					if(_penj.getTotPpn() != null){
						_item.setTotPpn(_penj.getTotPpn());
						}
					if(_penj.getTotJualNettoBeforePpn() != null){
					_item.setTotJualNettoBeforePpn(_penj.getTotJualNettoBeforePpn());
						}
					if(_penj.getTerkirim() != null){
						_item.setTerkirim(_penj.getTerkirim());
							}
					if(_penj.getKeterangan() != null){
						_item.setKeterangan(_penj.getKeterangan());
							}
				_items.add(_item);
				
			}
		}
		this.result.setItems(_items);

	}

}

