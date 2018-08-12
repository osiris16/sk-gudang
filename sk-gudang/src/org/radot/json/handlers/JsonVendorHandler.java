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
import org.radot.hibernate.entities.VendorEntity;
import org.radot.hibernate.persistences.VendorPersistence;
import org.radot.json.beans.VendorItem;
import org.radot.json.beans.VendorResult;
import org.radot.json.beans.VendorSelectParam;
import org.radot.json.servlet.JsonServletHandler;

public class JsonVendorHandler extends JsonServletHandler<VendorSelectParam, VendorResult> {

	public JsonVendorHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
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
		VendorItem _item;
		final List<VendorItem> _items = new ArrayList<VendorItem>();
//		int limit = 10;
//		long offset = Integer.parseInt(this.param.getOffset());
		int page = 0;
		
		/*search*/
		ArrayList<Criterion> _arrayCrit = new ArrayList<Criterion>();
		String _paramBy = this.param.getByValue();
		String _paramQuery = "%"+this.param.getQueryData()+"%";
		
		Criterion _filterByName = Restrictions.ilike("name", _paramQuery);
		Criterion _filterByCountry = Restrictions.ilike("country", _paramQuery);
		Criterion _filterByCity = Restrictions.ilike("city", _paramQuery);
		Criterion _filterByVta = Restrictions.ilike("vta", _paramQuery);
		
		if (_paramBy !=null){
		if(_paramBy.equalsIgnoreCase("name")){
			_arrayCrit.add(_filterByName);
		}
		if(_paramBy.equalsIgnoreCase("country")){
			_arrayCrit.add(_filterByCountry);
		}
		
		if(_paramBy.equalsIgnoreCase("city")){
			_arrayCrit.add(_filterByCity);
		
		}
		if(_paramBy.equalsIgnoreCase("vta")){
			_arrayCrit.add(_filterByVta);
		
		}
		}
		
		VendorPersistence _w =  new VendorPersistence();
		
		List<Order> _listOrder = new ArrayList<Order>();
		//final List<VendorEntity> _entst = BaseEntity.listDataOffset(VendorEntity.class, _arrayCrit,null,  null, null);
		Number _count = new VendorPersistence().getCountByVendor(_paramBy, _paramQuery);
		Order _orderByID = Order.desc("recId");
		_listOrder.add(_orderByID);
		final List<VendorEntity> _entList = BaseEntity.listDataOffset(VendorEntity.class, _arrayCrit,_listOrder,  10, 1l);
//		final List<VendorEntity> _entList = _w.getByCity(this.param.getCity());
		Long _lPage = 0l;
		if (null != _entList && !_entList.isEmpty()) {
			for (final VendorEntity _ent: _entList) {
				_item = new VendorItem();
				_item.setId(_ent.getRecId());
				_item.setName(_ent.getName());
				_item.setAddress1(_ent.getAddress1());
				_item.setAddress2(_ent.getAddress2());
				_item.setCargo(_ent.getCargo());
				_item.setCity(_ent.getCity());
				_item.setCountry(_ent.getCountry());
				_item.setVta(_ent.getVta());
				_item.setEmail(_ent.getEmail());
				_item.setFax(_ent.getFax());
				_item.setPhone(_ent.getPhone());
				_item.setTax(_ent.getTax());
				
				_items.add(_item);
				_lPage = _ent.getRecId();
			}
			
		}
		
		Long _modulo = _count.longValue()%10;
		Long _totalPage = _count.longValue()/10;
		if(_modulo != 0){
			_totalPage = _totalPage + 1;
		}
		_session.setAttribute("itemsVendor", _items);
		_session.setAttribute("offsetLast", _lPage);
		_session.setAttribute("totalData", Integer.parseInt(_count.toString()));
		_session.setAttribute("orderList",  _listOrder);
		_session.setAttribute("arrayCrit", _arrayCrit);
		//System.out.println(_count.longValue()+"  s");
		
		
		this.result.setPage(_totalPage.toString());
		this.result.setItems(_items);
		_session.setAttribute("dotPage", "True");
		new Thread(new JsonVendorHandler.BackgroundDataLoader(_session)).start();

	}
	public static class BackgroundDataLoader implements Runnable{
		private String _totData;
		private HttpSession session;
		private Integer intPage;
		private Integer intData;
		private Integer intCurrent;
		private long lastOffset;
		private List<VendorItem> items;
		private List<Criterion> _critArray;
		private List<Order> _listOrder;
		
		@SuppressWarnings("unchecked")
		public  BackgroundDataLoader(final HttpSession _session) {
			this.session = _session;
			this._critArray = (List<Criterion>) _session.getAttribute("arrayCrit");
			this._listOrder = (List<Order>) _session.getAttribute("orderList");
			//System.out.println("sss " + session.getAttribute("totalData"));
			this.intData = (Integer) session.getAttribute("totalData");
			if(this.intData%10 != 0){
				this.intPage = this.intData/10+1;
			}else{
				this.intPage = this.intData/10;
			}
			
			List<VendorItem> _items = new ArrayList<VendorItem>();
			_items = (List<VendorItem>) this.session.getAttribute("itemsVendor");
			//System.out.println("sss " + this.intData);
			this.lastOffset = (Long) this.session.getAttribute("offsetLast");
			
		}
		
		@Override
		public void run() {
			String _chString= "";
			_chString = session.getAttribute("dotPage").toString();
			if(_chString.equalsIgnoreCase("False")){
				//System.out.println("STOP");
				Thread.currentThread().isInterrupted();
			}
			HashMap<String, ArrayList<VendorItem>> _wew = new HashMap<String, ArrayList<VendorItem>>();
				Long _totData = Long.valueOf(this.intData);
				List<VendorItem> _items = new ArrayList<VendorItem>();
				List<VendorEntity> _entList = null;
				int _indata = (int) (_totData%10);
				
				try {
					_entList = BaseEntity.listDataOffset(VendorEntity.class, this._critArray,this._listOrder,  null, 1l);
				} catch (Exception e) {
					// TODO: handle exception
				}
				if (null != _entList && !_entList.isEmpty()) {
					VendorItem _item;
					int i = 0;
					int _p = -1;
					String _chekLast = "";
					for (final VendorEntity _ent: _entList) {
						//System.out.println("------------");
						_chString = session.getAttribute("dotPage").toString();
						if(_chString.equalsIgnoreCase("False")){
							//System.out.println("STOP");
							Thread.currentThread().isInterrupted();
							break;
						}
						i = i+1;
						_item = new VendorItem();
						_item.setId(_ent.getRecId());
						_item.setName(_ent.getName());
						_item.setAddress1(_ent.getAddress1());
						_item.setAddress2(_ent.getAddress2());
						_item.setCargo(_ent.getCargo());
						_item.setCity(_ent.getCity());
						_item.setCountry(_ent.getCountry());
						_item.setVta(_ent.getVta());
						_item.setEmail(_ent.getEmail());
						_item.setFax(_ent.getFax());
						_item.setPhone(_ent.getPhone());
						_item.setTax(_ent.getTax());
						
						_items.add(_item);
						
						this.lastOffset = _ent.getRecId();
						if(i == 10 ){
							_p = _p+1;
							i = 0;
							String _page = "page"+_p;
						//	System.out.println(_page);
							if(this.intPage - _p == 2){
								_chekLast = "last";
							}
						_wew.put(_page, (ArrayList<VendorItem>) _items);
						_items = new ArrayList<VendorItem>();
						session.setAttribute("paggingDataVendor", _wew);
						}
						if(_chekLast.equalsIgnoreCase("last") && _indata != 0 && _indata == i){
							_p = _p+1;
							i = 0;
							String _page = "page"+_p;
							//System.out.println(_page);
							if(this.intPage - _p == 2){
								_chekLast = "last";
							}
						_wew.put(_page, (ArrayList<VendorItem>) _items);
						_items = new ArrayList<VendorItem>();
						session.setAttribute("paggingDataVendor", _wew);
						}
					}
				}
		}
	}
}