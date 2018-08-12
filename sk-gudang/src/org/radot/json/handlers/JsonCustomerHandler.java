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
import org.radot.consts.SessionKeysConst;
import org.radot.hibernate.entities.BaseEntity;
import org.radot.hibernate.entities.CustomerEntity;
import org.radot.hibernate.entities.SalesmanEntity;
import org.radot.hibernate.entities.UserEntity;
import org.radot.hibernate.entities.VendorEntity;
import org.radot.hibernate.persistences.CustomerPersistence;
import org.radot.hibernate.persistences.SalesmanPersistence;
import org.radot.json.beans.CustomerItem;
import org.radot.json.beans.CustomerResult;
import org.radot.json.beans.CustomerSelectParam;
import org.radot.json.beans.VendorItem;
import org.radot.json.servlet.JsonServletHandler;
import org.radot.utils.ExportToExcel;
import org.radot.utils.ExportToPdf;

public class JsonCustomerHandler extends JsonServletHandler<CustomerSelectParam, CustomerResult> {

	public JsonCustomerHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
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
		CustomerItem _item;
		final List<CustomerItem> _items = new ArrayList<CustomerItem>();
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
		if(null==_paramBy)
		{
			_paramBy="";
		}
		if(_paramBy.equalsIgnoreCase("PRINTCUSTOMERPENJ")){
			
			
			CustomerEntity _cust = new CustomerPersistence().getByCode(this.param.getQueryData());
			if(null == _cust){
				this.result.setCode(8);
				this.result.setMessage("Kode Customer tidak ditemukan");
				return;
			}
			try {
//				UserEntity _user = (UserEntity) _session.getAttribute(SessionKeysConst.CURRENT_USER);
//				String _userName = " - ";
//				if(null !=_user){
//					_userName =_user.getName();
//				}
				ExportToPdf.custPrint(_cust.getRecId().toString(),this.param.getDateTo(), this.param.getDateFrom(), _cust.getCode(), _cust.getName(),this.param.getDiscMin(),this.param.getDiscMax());
			} catch (Exception e) {
			

				this.result.setCode(8);
				e.printStackTrace();
				return;
			}
			this.result.setCode(0);
			this.result.setMessage("customer_penjualan_"+_cust.getCode());
			return;
			
		}
		if(_paramBy.equalsIgnoreCase("PRINTCUSTOMERPENJFAKTUR")){
			
			
			CustomerEntity _cust = new CustomerPersistence().getByCode(this.param.getQueryData());
			if(null == _cust){
				this.result.setCode(8);
				this.result.setMessage("Kode Customer tidak ditemukan");
				return;
			}
			try {
				UserEntity _user = (UserEntity) _session.getAttribute(SessionKeysConst.CURRENT_USER);
				String _userName = " - ";
				if(null !=_user){
					_userName =_user.getName();
				}
				ExportToPdf.custPrintFaktur(_cust.getRecId().toString(),this.param.getDateTo(), this.param.getDateFrom(), _cust.getCode(), _cust.getName(), _userName,this.param.getDiscMin(),this.param.getDiscMax());
			} catch (Exception e) {
			

				this.result.setCode(8);
				e.printStackTrace();
				return;
			}
			this.result.setCode(0);
			this.result.setMessage("customer_"+_cust.getCode()+"_faktur");
			return;
			
		}
		if(_paramBy.equalsIgnoreCase("PRINTCUSTOMERPENJFAKTUREXCEL")){
			
			
			CustomerEntity _cust = new CustomerPersistence().getByCode(this.param.getQueryData());
			if(null == _cust){
				this.result.setCode(8);
				this.result.setMessage("Kode Customer tidak ditemukan");
				return;
			}
			String a ="";
			try {
				UserEntity _user = (UserEntity) _session.getAttribute(SessionKeysConst.CURRENT_USER);
				String _userName = " - ";
				if(null !=_user){
					_userName =_user.getName();
				}
				a = ExportToExcel.custPrintFaktur(_cust.getRecId().toString(),this.param.getDateTo(), this.param.getDateFrom(), _cust.getCode(), _cust.getName(), _userName,this.param.getDiscMin(),this.param.getDiscMax());
			} catch (Exception e) {
			

				this.result.setCode(8);
				e.printStackTrace();
				return;
			}
			this.result.setCode(0);
			this.result.setMessage(a);
			return;
			
		}
		
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
		
		CustomerPersistence _w =  new CustomerPersistence();
		
		List<Order> _listOrder = new ArrayList<Order>();
		//final List<CustomerEntity> _entst = BaseEntity.listDataOffset(CustomerEntity.class, _arrayCrit,null,  null, null);
		Number _count = new CustomerPersistence().getCountByCustomer(_paramBy, _paramQuery);
		Order _orderByID = Order.desc("recId");
		_listOrder.add(_orderByID);
		final List<CustomerEntity> _entList = BaseEntity.listDataOffset(CustomerEntity.class, _arrayCrit,_listOrder,  10, 1l);
//		final List<CustomerEntity> _entList = _w.getByCity(this.param.getCity());
		Long _lPage = 0l;
		if (null != _entList && !_entList.isEmpty()) {
			for (final CustomerEntity _ent: _entList) {
				_item = new CustomerItem();
				_item.setAddress1(_ent.getAddress1());
				_item.setAddress2(_ent.getAddress2());
				_item.setCity(_ent.getCity());
				_item.setCode(_ent.getCode());
				_item.setContact(_ent.getContact());
				_item.setFax(_ent.getFax());
				_item.setId(_ent.getRecId());
				_item.setName(_ent.getName());
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
		_session.setAttribute("itemsCustomer", _items);
		_session.setAttribute("offsetLast", _lPage);
		_session.setAttribute("totalData", Integer.parseInt(_count.toString()));
		_session.setAttribute("orderList",  _listOrder);
		_session.setAttribute("arrayCrit", _arrayCrit);
		System.out.println(_count.longValue()+"  s");
		
		
		this.result.setPage(_totalPage.toString());
		this.result.setItems(_items);
		_session.setAttribute("dotPage", "True");
		new Thread(new JsonCustomerHandler.BackgroundDataLoader(_session)).start();

	}
	public static class BackgroundDataLoader implements Runnable{
		private String _totData;
		private HttpSession session;
		private Integer intPage;
		private Integer intData;
		private Integer intCurrent;
		private long lastOffset;
		private List<CustomerItem> items;
		private List<Criterion> _critArray;
		private List<Order> _listOrder;
		
		@SuppressWarnings("unchecked")
		public  BackgroundDataLoader(final HttpSession _session) {
			this.session = _session;
			this._critArray = (List<Criterion>) _session.getAttribute("arrayCrit");
			this._listOrder = (List<Order>) _session.getAttribute("orderList");
			System.out.println("sss " + session.getAttribute("totalData"));
			this.intData = (Integer) session.getAttribute("totalData");
			if(this.intData%10 != 0){
				this.intPage = this.intData/10+1;
			}else{
				this.intPage = this.intData/10;
			}
			
			List<CustomerItem> _items = new ArrayList<CustomerItem>();
			_items = (List<CustomerItem>) this.session.getAttribute("itemsCustomer");
			System.out.println("sss " + this.intData);
			this.lastOffset = (Long) this.session.getAttribute("offsetLast");
			
		}
		
		@Override
		public void run() {
			String _chString= "";
			_chString = session.getAttribute("dotPage").toString();
			if(_chString.equalsIgnoreCase("False")){
				System.out.println("STOP");
				Thread.currentThread().isInterrupted();
			}
			HashMap<String, ArrayList<CustomerItem>> _wew = new HashMap<String, ArrayList<CustomerItem>>();
				Long _totData = Long.valueOf(this.intData);
				List<CustomerItem> _items = new ArrayList<CustomerItem>();
				List<CustomerEntity> _entList = null;
				int _indata = (int) (_totData%10);
				
				try {
					_entList = BaseEntity.listDataOffset(CustomerEntity.class, this._critArray,this._listOrder,  null, 1l);
				} catch (Exception e) {
					// TODO: handle exception
				}
				if (null != _entList && !_entList.isEmpty()) {
					CustomerItem _item;
					int i = 0;
					int _p = -1;
					String _chekLast = "";
					for (final CustomerEntity _ent: _entList) {
						System.out.println("------------");
						_chString = session.getAttribute("dotPage").toString();
						if(_chString.equalsIgnoreCase("False")){
							System.out.println("STOP");
							Thread.currentThread().isInterrupted();
							break;
						}
						i = i+1;
						_item = new CustomerItem();
						_item.setAddress1(_ent.getAddress1());
						_item.setAddress2(_ent.getAddress2());
						_item.setCity(_ent.getCity());
						_item.setCode(_ent.getCode());
						_item.setContact(_ent.getContact());
						_item.setFax(_ent.getFax());
						_item.setId(_ent.getRecId());
						_item.setName(_ent.getName());
						_item.setPhone(_ent.getPhone());
						_item.setTax(_ent.getTax());
						_items.add(_item);
						
						this.lastOffset = _ent.getRecId();
						if(i == 10 ){
							_p = _p+1;
							i = 0;
							String _page = "page"+_p;
							System.out.println(_page);
							if(this.intPage - _p == 2){
								_chekLast = "last";
							}
						_wew.put(_page, (ArrayList<CustomerItem>) _items);
						_items = new ArrayList<CustomerItem>();
						session.setAttribute("paggingDataCustomer", _wew);
						}
						if(_chekLast.equalsIgnoreCase("last") && _indata != 0 && _indata == i){
							_p = _p+1;
							i = 0;
							String _page = "page"+_p;
							System.out.println(_page);
							if(this.intPage - _p == 2){
								_chekLast = "last";
							}
						_wew.put(_page, (ArrayList<CustomerItem>) _items);
						_items = new ArrayList<CustomerItem>();
						session.setAttribute("paggingDataCustomer", _wew);
						}
					}
				}
		}
	}
}

/*package org.radot.json.handlers;

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
import org.radot.hibernate.entities.CustomerEntity;
import org.radot.hibernate.persistences.CustomerPersistence;
import org.radot.json.beans.CustomerItem;
import org.radot.json.beans.CustomerResult;
import org.radot.json.beans.CustomerSelectParam;
import org.radot.json.servlet.JsonServletHandler;

public class JsonCustomerHandler extends JsonServletHandler<CustomerSelectParam, CustomerResult> {

	public JsonCustomerHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
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
		CustomerItem _item;
		final List<CustomerItem> _items = new ArrayList<CustomerItem>();
//		int limit = 10;
//		long offset = Integer.parseInt(this.param.getOffset());
		int page = 0;
		
		search
		
		ArrayList<Criterion> _arrayCrit = new ArrayList<Criterion>();
		String _paramBy = this.param.getByValue();
		String _paramQuery = "%"+this.param.getQueryData()+"%";
		
		Criterion _filterByName = Restrictions.ilike("name", _paramQuery);
		Criterion _filterByCity = Restrictions.ilike("city", _paramQuery);
		
		
		if (_paramBy !=null){
		if(_paramBy.equalsIgnoreCase("name")){
			_arrayCrit.add(_filterByName);
		}
		if(_paramBy.equalsIgnoreCase("city")){
			_arrayCrit.add(_filterByCity);
		}
		
		CustomerPersistence _w =  new CustomerPersistence();
		
		List<Order> _listOrder = new ArrayList<Order>();
		Number _count = new CustomerPersistence().getCountByCustomer(_paramBy,_paramQuery);
		Order _orderByID = Order.desc("recId");
		_listOrder.add(_orderByID);
		final List<CustomerEntity> _entList = BaseEntity.listDataOffset(CustomerEntity.class, _arrayCrit,_listOrder,  10, 1l);
//		final List<CustomerEntity> _entList = _w.getByCity(this.param.getCity());
		Long _lPage = 0l;
		if (null != _entList && !_entList.isEmpty()) {
			for (final CustomerEntity _ent: _entList) {
				_item = new CustomerItem();
				_item.setAddress1(_ent.getAddress1());
				_item.setAddress2(_ent.getAddress2());
				_item.setCity(_ent.getCity());
				_item.setCode(_ent.getCode());
				_item.setContact(_ent.getContact());
				_item.setFax(_ent.getFax());
				_item.setId(_ent.getRecId());
				_item.setName(_ent.getName());
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
		System.out.println(_count.longValue()+"  s");
		
		
		this.result.setPage(_totalPage.toString());
		this.result.setItems(_items);
		_session.setAttribute("dotPage", "True"); //Stop
		new Thread(new JsonCustomerHandler.BackgroundDataLoader(_session)).start();

	}
	public static class BackgroundDataLoader implements Runnable{
		private String _totData;
		private HttpSession session;
		private Integer intPage;
		private Integer intData;
		private Integer intCurrent;
		private long lastOffset;
		private List<CustomerItem> items;
		private List<Criterion> _critArray;
		private List<Order> _listOrder;
		
		@SuppressWarnings("unchecked")
		public  BackgroundDataLoader(final HttpSession _session) {
			this.session = _session;
			this._critArray = (List<Criterion>) _session.getAttribute("arrayCrit");
			this._listOrder = (List<Order>) _session.getAttribute("orderList");
			System.out.println("sss " + session.getAttribute("totalData"));
			this.intData = (Integer) session.getAttribute("totalData");
			if(this.intData%10 != 0){
				this.intPage = this.intData/10+1;
			}else{
				this.intPage = this.intData/10;
			}
			
			List<CustomerItem> _items = new ArrayList<CustomerItem>();
			_items = (List<CustomerItem>) this.session.getAttribute("itemsCustomer");
			System.out.println("sss " + this.intData);
			this.lastOffset = (Long) this.session.getAttribute("offsetLast");
			
		}
		
		@Override
		public void run() {
			//sTOP PAGING	
			String _chString= "";
			_chString = session.getAttribute("dotPage").toString();
			if(_chString.equalsIgnoreCase("False")){
				System.out.println("STOP");
				Thread.currentThread().isInterrupted();
			}
			//sTOP PAGGING END
			
			HashMap<String, ArrayList<CustomerItem>> _wew = new HashMap<String, ArrayList<CustomerItem>>();
				Long _totData = Long.valueOf(this.intData);
				List<CustomerItem> _items = new ArrayList<CustomerItem>();
				List<CustomerEntity> _entList = null;
				int _indata = (int) (_totData%10);
				
				try {
					_entList = BaseEntity.listDataOffset(CustomerEntity.class, this._critArray,this._listOrder,  null, 1l);
				} catch (Exception e) {
					// TODO: handle exception
				}
				if (null != _entList && !_entList.isEmpty()) {
					CustomerItem _item;
					int i = 0;
					int _p = -1;
					String _chekLast = "";
					for (final CustomerEntity _ent: _entList) {
						//sTOP PAGGING
						System.out.println("------------");
						_chString = session.getAttribute("dotPage").toString();
						if(_chString.equalsIgnoreCase("False")){
							System.out.println("STOP");
							Thread.currentThread().isInterrupted();
							break;
						}
						i = i+1;
						_item = new CustomerItem();
						_item.setAddress1(_ent.getAddress1());
						_item.setAddress2(_ent.getAddress2());
						_item.setCity(_ent.getCity());
						_item.setCode(_ent.getCode());
						_item.setContact(_ent.getContact());
						_item.setFax(_ent.getFax());
						_item.setId(_ent.getRecId());
						_item.setName(_ent.getName());
						_item.setPhone(_ent.getPhone());
						_item.setTax(_ent.getTax());
						_items.add(_item);
						this.lastOffset = _ent.getRecId();
						if(i == 10 ){
							_p = _p+1;
							i = 0;
							String _page = "page"+_p;
							System.out.println(_page);
							if(this.intPage - _p == 2){
								_chekLast = "last";
							}
						_wew.put(_page, (ArrayList<CustomerItem>) _items);
						_items = new ArrayList<CustomerItem>();
						session.setAttribute("paggingDataCustomer", _wew);
						}
						if(_chekLast.equalsIgnoreCase("last") && _indata != 0 && _indata == i){
							_p = _p+1;
							i = 0;
							String _page = "page"+_p;
							System.out.println(_page);
							if(this.intPage - _p == 2){
								_chekLast = "last";
							}
						_wew.put(_page, (ArrayList<CustomerItem>) _items);
						_items = new ArrayList<CustomerItem>();
						session.setAttribute("paggingDataCustomer", _wew);
						}
					}
				}
		}
	}
}
*/