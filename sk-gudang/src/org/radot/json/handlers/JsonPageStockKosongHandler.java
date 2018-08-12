package org.radot.json.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.radot.json.beans.PageStockParam;
import org.radot.json.beans.StockItem;
import org.radot.json.beans.StockResult;
import org.radot.json.servlet.JsonServletHandler;

public class JsonPageStockKosongHandler extends JsonServletHandler<PageStockParam, StockResult> {

	public JsonPageStockKosongHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}
	
	@Override()
	public void process() throws Throwable {
		
		StockItem _item;
		final List<StockItem> _items = new ArrayList<StockItem>();
//		int limit = 10;
		String page = this.param.getPage();
		final HttpSession _session = this.request.getSession();
		@SuppressWarnings("unchecked")
		HashMap<String, ArrayList<StockItem>> _c = (HashMap<String, ArrayList<StockItem>>) _session.getAttribute("paggingDataStockKosong");
		//System.out.println(_c.size()+ " data ");
		ArrayList<StockItem> _stock = _c.get(page);
		for (final StockItem _ent: _stock) {
			System.out.println(_ent.getProductName());
		}
		if (null != _stock && !_stock.isEmpty()) {
			for (final StockItem _prod: _stock) {
				_item = new StockItem();
				_item.setStockId(_prod.getStockId());
				_item.setProdId(_prod.getProdId());
				_item.setProdGroupId(_prod.getProdGroupId());
				_item.setProductCode(_prod.getProductCode());
				_item.setProductName(_prod.getProductName());
				_item.setStokCtn(_prod.getStokCtn());
				_item.setTripNumbStok(_prod.getTripNumbStok());
				_item.setTripDateStok(_prod.getTripDateStok());
				
				if(_prod.getProductMerk() != null){
					_item.setProductMerk(_prod.getProductMerk());
				}
				if(_prod.getProductMadeIn() != null){
					_item.setProductMadeIn(_prod.getProductMadeIn());
				}
				
				if(_prod.getProductBarcode() != null){
					_item.setProductBarcode(_prod.getProductBarcode());
				}
				
				if(_prod.getProductStandartNo() != null){
					_item.setProductStandartNo(_prod.getProductStandartNo());
				}
				
				if(_prod.getProductPartNumb() != null){
					_item.setProductPartNumb(_prod.getProductPartNumb());
				}
				if(_prod.getProductPartNumb() != null){
					_item.setProductPartNumb(_prod.getProductPartNumb());
				}
				
				if(_prod.getStokCtn()!=null){
					_item.setStokCtn(_prod.getStokCtn());
				}
				
				if(_prod.getStokIsiCtn()!=null){
					_item.setStokIsiCtn(_prod.getStokIsiCtn());
				}
				
				if(_prod.getIsiCtn() != null){
					_item.setIsiCtn(_prod.getIsiCtn());
				}
				
				if(_prod.getSatIsiCtn() != null){
					_item.setSatIsiCtn(_prod.getSatIsiCtn());
				}
				
				if(_prod.getIsiPcs() != null){
					_item.setIsiPcs(_prod.getIsiPcs());
				}
				
				if(_prod.getTotStokPcs() != null){
					_item.setTotStokPcs(_prod.getTotStokPcs());
				}
				if(_prod.getProductGroup() != null){
					_item.setProductGroup(_prod.getProductGroup());
				}
				if(_prod.getProductImage() != null){
					_item.setProductImage(_prod.getProductImage());
				}
				if(_prod.getHargaJualIsiCtn() != null){
					_item.setHargaJualIsiCtn(_prod.getHargaJualIsiCtn());
				}
				if(_prod.getHargaJualCtn() != null){
					_item.setHargaJualCtn(_prod.getHargaJualCtn());
				}
				_items.add(_item);
			}
		}
		this.result.setItems(_items);

	}
	
	

}
