package org.radot.json.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.radot.hibernate.entities.BaseEntity;
import org.radot.hibernate.entities.ProductEntity;
import org.radot.hibernate.persistences.ProductPersistence;
import org.radot.json.beans.ProductItem;
import org.radot.json.beans.ProductResult;
import org.radot.json.servlet.JsonParam;
import org.radot.json.servlet.JsonServletHandler;

public class JsonProductHandler extends JsonServletHandler<JsonParam, ProductResult> {

	public JsonProductHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}
	
	@Override()
	public void process() throws Throwable {
		
		ProductItem _item;
		final List<ProductItem> _items = new ArrayList<ProductItem>();
//		int limit = 10;
//		long offset = Integer.parseInt(this.param.getOffset());
		int page = 0;
		final List<ProductEntity> _entst = BaseEntity.list(ProductEntity.class, null, null);
		ProductPersistence _w =  new ProductPersistence();
		final HttpSession _session = this.request.getSession();
		
		final List<ProductEntity> _entList = BaseEntity.listDataOffset(ProductEntity.class, null,null,  10, 1l);
		Long _lPage = 0l;
		if (null != _entList && !_entList.isEmpty()) {
			for (final ProductEntity _ent: _entList) {
				_item = new ProductItem();
				_item.setCode(_ent.getCode());
				_item.setName(_ent.getName());
				_item.setMerk(_ent.getMerk());
				_item.setMadeIn(_ent.getMadeIn());
				_item.setGroup(_ent.getProductGroupEnt().getName());
				_item.setPartNumb(_ent.getPartNumb());
				
				_items.add(_item);
				_lPage = _ent.getRecId();
			}
			
		}
	
		_session.setAttribute("itemsProduct", _items);
		_session.setAttribute("offsetLast", _lPage);
		_session.setAttribute("totalData",  _entst.size());
	
		_session.setAttribute("pageData1", (ArrayList<ProductItem>) _items);
		this.result.setPage(String.valueOf(_entst.size()/10));
		this.result.setItems(_items);
		new Thread(new JsonProductHandler.BackgroundDataLoader(_session)).start();

	}
	public static class BackgroundDataLoader implements Runnable{
		private String _totData;
		private HttpSession session;
		private Integer intPage;
		private Integer intData;
		private Integer intCurrent;
		private ArrayList<ProductItem> _firstPage;
		private long lastOffset;
		private List<ProductItem> items;
		
		@SuppressWarnings("unchecked")
		public  BackgroundDataLoader(final HttpSession _session) {
			this.session = _session;
			//System.out.println("sss " + session.getAttribute("totalData"));
			this.intData = (Integer) session.getAttribute("totalData");
			this.intPage = this.intData/10;
			this._firstPage = (ArrayList<ProductItem>) session.getAttribute("pageData1");
			List<ProductItem> _items = new ArrayList<ProductItem>();
			_items = (List<ProductItem>) this.session.getAttribute("itemsProduct");
			//System.out.println("sss " + this.intData);
			this.lastOffset = (Long) this.session.getAttribute("offsetLast");
		}
		@Override
		public void run() {
			//System.out.println(" ssl "+ session.getAttribute("totalData"));
			HashMap<String, ArrayList<ProductItem>> _wew = new HashMap<String, ArrayList<ProductItem>>();
			_wew.put("page0", (ArrayList<ProductItem>) this._firstPage);
			session.setAttribute("paggingData", _wew);
			for (int i = 0; i < this.intPage; i++) {
				final List<ProductItem> _items = new ArrayList<ProductItem>();
				List<ProductEntity> _entList = null;
				try {
					_entList = BaseEntity.listDataOffset(ProductEntity.class, null,null,  10, this.lastOffset);
				} catch (Exception e) {
					// TODO: handle exception
				}
				if (null != _entList && !_entList.isEmpty()) {
					ProductItem _item;
					for (final ProductEntity _ent: _entList) {
						_item = new ProductItem();
						_item.setCode(_ent.getCode());
						_item.setName(_ent.getName());
						_item.setMerk(_ent.getMerk());
						_item.setMadeIn(_ent.getMadeIn());
						
						_item.setGroup(_ent.getProductGroupEnt().getName());
						_item.setPartNumb(_ent.getPartNumb());
						_items.add(_item);
						this.lastOffset = _ent.getRecId();
					}
				}
				int _p = i+1;
				String _page = "page"+_p;
			//System.out.println(_page);
			_wew.put(_page, (ArrayList<ProductItem>) _items);
			session.setAttribute("paggingData", _wew);
			}
		}
		
	}

	
	

}
