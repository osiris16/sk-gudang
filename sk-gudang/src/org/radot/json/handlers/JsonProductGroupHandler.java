package org.radot.json.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.radot.hibernate.entities.BaseEntity;
import org.radot.hibernate.entities.ProductGroupEntity;
import org.radot.hibernate.persistences.ProductGroupPersistence;
import org.radot.json.beans.ProductGroupInputParam;
import org.radot.json.beans.ProductGroupItem;
import org.radot.json.beans.ProductGroupResult;
import org.radot.json.servlet.JsonServletHandler;

public class JsonProductGroupHandler extends JsonServletHandler<ProductGroupInputParam, ProductGroupResult> {

	public JsonProductGroupHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
		final HttpSession _session = this.request.getSession();
		try {
			String _c = (String) _session.getAttribute("dotPage");
			if(_c.equalsIgnoreCase("True")){
				_session.setAttribute("dotPage", "False");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		ProductGroupItem _item;
		final List<ProductGroupItem> _items = new ArrayList<ProductGroupItem>();
//		int limit = 10;
//		long offset = Integer.parseInt(this.param.getOffset());
		int page = 0;
		
		/*search*/
		 ArrayList<Criterion> _arrayCrit = new ArrayList<Criterion>();
		 String _paramName = "%"+this.param.getName()+"%";
		
		Criterion _filterByName = Restrictions.ilike("name", _paramName);
		
		_arrayCrit.add(_filterByName);
		
		
		
		ProductGroupPersistence _w =  new ProductGroupPersistence();
		
		List<Order> _listOrder = new ArrayList<Order>();
		final List<ProductGroupEntity> _entst = BaseEntity.listDataOffset(ProductGroupEntity.class, _arrayCrit,null,  null, null);
		Order _orderByID = Order.asc("recId");
		_listOrder.add(_orderByID);
		final List<ProductGroupEntity> _entList = BaseEntity.listDataOffset(ProductGroupEntity.class, _arrayCrit,_listOrder,  11, 1l);
//		final List<VendorEntity> _entList = _w.getByCity(this.param.getCity());
		Long _lPage = 0l;
		if (null != _entList && !_entList.isEmpty()) {
			for (final ProductGroupEntity _ent: _entList) {
				_item = new ProductGroupItem();
				_item.setId(_ent.getRecId());
				_item.setName(_ent.getName());
				
				_items.add(_item);
				_lPage = _ent.getRecId();
			}
			
		}
	
		_session.setAttribute("itemsProductGroup", _items);
		_session.setAttribute("offsetLast", _lPage);
		_session.setAttribute("totalData",  _entst.size());
		_session.setAttribute("orderList",  _listOrder);
		_session.setAttribute("arrayCrit", _arrayCrit);
	
		_session.setAttribute("pageData1", (ArrayList<ProductGroupItem>) _items);
		//System.out.println(_entst.size()+"  s");
		int _totPageMod = 0;
		_totPageMod = _entst.size()/10;
		if(_entst.size()%10 != 0){
			_totPageMod = _entst.size()/10 +1;
		}
	
		this.result.setPage(String.valueOf(_totPageMod));
		this.result.setItems(_items);
		_session.setAttribute("dotPage", "True"); //Stop
		new Thread(new JsonProductGroupHandler.BackgroundDataLoader(_session)).start();

	}
	public static class BackgroundDataLoader implements Runnable{
		private String _totData;
		private HttpSession session;
		private Integer intPage;
		private Integer intData;
		private Integer intCurrent;
		private ArrayList<ProductGroupItem> _firstPage;
		private long lastOffset;
		private List<ProductGroupItem> items;
		private List<Criterion> _critArray;
		private List<Order> _listOrder;
		
		@SuppressWarnings("unchecked")
		public  BackgroundDataLoader(final HttpSession _session) {
			this.session = _session;
			this._critArray = (List<Criterion>) _session.getAttribute("arrayCrit");
			this._listOrder = (List<Order>) _session.getAttribute("orderList");
			//System.out.println("sss " + session.getAttribute("totalData"));
			this.intData = (Integer) session.getAttribute("totalData");
			this.intPage = this.intData/10;
			this._firstPage = (ArrayList<ProductGroupItem>) session.getAttribute("pageData1");
			List<ProductGroupItem> _items = new ArrayList<ProductGroupItem>();
			_items = (List<ProductGroupItem>) this.session.getAttribute("itemsProductGroup");
			//System.out.println("sss " + this.intData);
			this.lastOffset = (Long) this.session.getAttribute("offsetLast");
		}
		@Override
		public void run() {
		//sTOP PAGING	
		String _chString= "";
		_chString = session.getAttribute("dotPage").toString();
			if(_chString.equalsIgnoreCase("False")){
				//System.out.println("STOP");
				Thread.currentThread().isInterrupted();
			}
			//System.out.println(" ssl "+ session.getAttribute("totalData"));
			HashMap<String, ArrayList<ProductGroupItem>> _wew = new HashMap<String, ArrayList<ProductGroupItem>>();
			_wew.put("page0", (ArrayList<ProductGroupItem>) this._firstPage);
			session.setAttribute("paggingDataProductGroup", _wew);
			for (int i = 0; i < this.intPage; i++) {
				final List<ProductGroupItem> _items = new ArrayList<ProductGroupItem>();
				List<ProductGroupEntity> _entList = null;
				try {
					_entList = BaseEntity.listDataOffset(ProductGroupEntity.class, this._critArray,this._listOrder,  11, this.lastOffset);
				} catch (Exception e) {
					// TODO: handle exception
				}
				if (null != _entList && !_entList.isEmpty()) {
					ProductGroupItem _item;
					for (final ProductGroupEntity _ent: _entList) {
						//System.out.println("------------");
						_chString = session.getAttribute("dotPage").toString();
						if(_chString.equalsIgnoreCase("False")){
							//System.out.println("STOP");
							Thread.currentThread().isInterrupted();
							break;
						}
						_item = new ProductGroupItem();
						_item.setId(_ent.getRecId());
						_item.setName(_ent.getName());
						_items.add(_item);
						
						this.lastOffset = _ent.getRecId();
					}
				}
				int _p = i+1;
				String _page = "page"+_p;
			//System.out.println(_page);
			_wew.put(_page, (ArrayList<ProductGroupItem>) _items);
			session.setAttribute("paggingDataProductGroup", _wew);
			}
		}
		
	}
}

