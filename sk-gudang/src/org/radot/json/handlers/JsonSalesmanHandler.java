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
import org.radot.hibernate.entities.SalesmanEntity;
import org.radot.hibernate.entities.UserEntity;
import org.radot.hibernate.persistences.SalesmanPersistence;
import org.radot.json.beans.SalesmanItem;
import org.radot.json.beans.SalesmanResult;
import org.radot.json.beans.SalesmanSelectParam;
import org.radot.json.servlet.JsonServletHandler;
import org.radot.utils.ExportToExcel;
import org.radot.utils.ExportToPdf;

public class JsonSalesmanHandler extends JsonServletHandler<SalesmanSelectParam, SalesmanResult> {

	public JsonSalesmanHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
		//Start Stop
		final HttpSession _session = this.request.getSession();
		try {
			String _c = (String) _session.getAttribute("dotPage");
			if(_c.equalsIgnoreCase("True")){
				_session.setAttribute("dotPage", "False");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		//Start Stop	
		SalesmanItem _item;
		final List<SalesmanItem> _items = new ArrayList<SalesmanItem>();
//		int limit = 10;
//		long offset = Integer.parseInt(this.param.getOffset());
		int page = 0;
		
		/*search*/
		/*search*/
		ArrayList<Criterion> _arrayCrit = new ArrayList<Criterion>();
		String _paramBy = this.param.getByValue();
		String _paramQuery = "%"+this.param.getQueryData()+"%";
		
		Criterion _filterByCode = Restrictions.ilike("code", _paramQuery);
		Criterion _filterByName = Restrictions.ilike("name", _paramQuery);
		if(null==_paramBy)
		{
			_paramBy="";
		}
		if(_paramBy.equalsIgnoreCase("PRINTSALESPENJ")){
			
			
			SalesmanEntity _sales = new SalesmanPersistence().getByCode(this.param.getQueryData());
			if(null == _sales){
				this.result.setCode(8);
				this.result.setMessage("Kode Sales tidak ditemukan");
				return;
			}
			try {
				ExportToPdf.salesPrint(_sales.getRecId().toString(),this.param.getDateTo(), this.param.getDateFrom(), _sales.getCode(), _sales.getName(),this.param.getDiscMin(),this.param.getDiscMax());
			} catch (Exception e) {
			

				this.result.setCode(8);
				e.printStackTrace();
				return;
			}
			this.result.setCode(0);
			this.result.setMessage("penjualan_"+_sales.getCode());
			return;
			
		}
		if(_paramBy.equalsIgnoreCase("PRINTSALESPENJFAKTUR")){
			
			
			SalesmanEntity _sales = new SalesmanPersistence().getByCode(this.param.getQueryData());
			if(null == _sales){
				this.result.setCode(8);
				this.result.setMessage("Kode Sales tidak ditemukan");
				return;
			}
			try {
				UserEntity _user = (UserEntity) _session.getAttribute(SessionKeysConst.CURRENT_USER);
				String _userName = " - ";
				if(null !=_user){
					_userName =_user.getName();
				}
				ExportToPdf.salesPrintFaktur(_sales.getRecId().toString(),this.param.getDateTo(), this.param.getDateFrom(), _sales.getCode(), _sales.getName(), _userName,this.param.getDiscMin(),this.param.getDiscMax());
			} catch (Exception e) {
			

				this.result.setCode(8);
				e.printStackTrace();
				return;
			}
			this.result.setCode(0);
			this.result.setMessage("penjualan_"+_sales.getCode()+"_faktur");
			return;
			
		}
		//EXCELL
		if(_paramBy.equalsIgnoreCase("PRINTSALESPENJFAKTUREXCEL")){
			
			
			SalesmanEntity _sales = new SalesmanPersistence().getByCode(this.param.getQueryData());
			if(null == _sales){
				this.result.setCode(8);
				this.result.setMessage("Kode Sales tidak ditemukan");
				return;
			}
			String a ="";
			try {
				UserEntity _user = (UserEntity) _session.getAttribute(SessionKeysConst.CURRENT_USER);
				String _userName = " - ";
				if(null !=_user){
					_userName =_user.getName();
				}
				 a = ExportToExcel.salesPrintFaktur(_sales.getRecId().toString(),this.param.getDateTo(), this.param.getDateFrom(), _sales.getCode(), _sales.getName(), _userName,this.param.getDiscMin(),this.param.getDiscMax());
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
		if(_paramBy.equalsIgnoreCase("code")){
			_arrayCrit.add(_filterByCode);
		}
		if(_paramBy.equalsIgnoreCase("name")){
			_arrayCrit.add(_filterByName);
		}
		
		}
		
		
		SalesmanPersistence _w =  new SalesmanPersistence();
		
		List<Order> _listOrder = new ArrayList<Order>();
		Number _count = new SalesmanPersistence().getCountBySalesman(_paramBy, _paramQuery);
		Order _orderByID = Order.desc("recId");
		_listOrder.add(_orderByID);
		final List<SalesmanEntity> _entList = BaseEntity.listDataOffset(SalesmanEntity.class, _arrayCrit,_listOrder,  10, 1l);
//		final List<CustomerEntity> _entList = _w.getByCity(this.param.getCity());
		Long _lPage = 0l;
		if (null != _entList && !_entList.isEmpty()) {
			for (final SalesmanEntity _ent: _entList) {
				_item = new SalesmanItem();
				_item.setId(_ent.getRecId());
				_item.setCode(_ent.getCode());
				_item.setName(_ent.getName());
				_items.add(_item);
				_lPage = _ent.getRecId();
			}
			
		}
		Long _modulo = _count.longValue()%10;
		Long _totalPage = _count.longValue()/10;
		if(_modulo != 0){
			_totalPage = _totalPage + 1;
		}
		_session.setAttribute("itemsSalesman", _items);
		_session.setAttribute("offsetLast", _lPage);
		_session.setAttribute("totalData", Integer.parseInt(_count.toString()));
		_session.setAttribute("orderList",  _listOrder);
		_session.setAttribute("arrayCrit", _arrayCrit);
		//System.out.println(_count.longValue()+"  s");
		
		
		this.result.setPage(_totalPage.toString());
		this.result.setItems(_items);
		_session.setAttribute("dotPage", "True"); //Stop
		new Thread(new JsonSalesmanHandler.BackgroundDataLoader(_session)).start();

	}
	public static class BackgroundDataLoader implements Runnable{
		private String _totData;
		private HttpSession session;
		private Integer intPage;
		private Integer intData;
		private Integer intCurrent;
		private long lastOffset;
		private List<SalesmanItem> items;
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
			
			List<SalesmanItem> _items = new ArrayList<SalesmanItem>();
			_items = (List<SalesmanItem>) this.session.getAttribute("itemsSalesman");
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
				
				HashMap<String, ArrayList<SalesmanItem>> _wew = new HashMap<String, ArrayList<SalesmanItem>>();
					Long _totData = Long.valueOf(this.intData);
					List<SalesmanItem> _items = new ArrayList<SalesmanItem>();
					List<SalesmanEntity> _entList = null;
					int _indata = (int) (_totData%10);
					
					try {
						_entList = BaseEntity.listDataOffset(SalesmanEntity.class, this._critArray,this._listOrder,  null, 1l);
					} catch (Exception e) {
						// TODO: handle exception
					}
				if (null != _entList && !_entList.isEmpty()) {
					SalesmanItem _item;
				int i = 0;
				int _p = -1;
				String _chekLast = "";
					for (final SalesmanEntity _ent: _entList) {
						//System.out.println("------------");
						_chString = session.getAttribute("dotPage").toString();
						if(_chString.equalsIgnoreCase("False")){
							//System.out.println("STOP");
							Thread.currentThread().isInterrupted();
							break;
						}
						i = i+1;
						_item = new SalesmanItem();
						_item.setId(_ent.getRecId());
						_item.setCode(_ent.getCode());
						_item.setName(_ent.getName());
						
						_items.add(_item);
						this.lastOffset = _ent.getRecId();
						if(i == 10 ){
							_p = _p+1;
							i = 0;
							String _page = "page"+_p;
							//System.out.println(_page);
							if(this.intPage - _p == 2){
								_chekLast = "last";
							}
						_wew.put(_page, (ArrayList<SalesmanItem>) _items);
						_items = new ArrayList<SalesmanItem>();
						session.setAttribute("paggingDataSalesman", _wew);
						}
						if(_chekLast.equalsIgnoreCase("last") && _indata != 0 && _indata == i){
							_p = _p+1;
							i = 0;
							String _page = "page"+_p;
							//System.out.println(_page);
							if(this.intPage - _p == 2){
								_chekLast = "last";
							}
						_wew.put(_page, (ArrayList<SalesmanItem>) _items);
						_items = new ArrayList<SalesmanItem>();
						session.setAttribute("paggingDataSalesman", _wew);
						}
					}
				}
		}
	}
}
