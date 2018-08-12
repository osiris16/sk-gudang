package org.radot.json.handlers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.radot.hibernate.entities.DetailPenjualanEntity;
import org.radot.hibernate.entities.PenjualanEntity;
import org.radot.hibernate.entities.SalesmanEntity;
import org.radot.hibernate.persistences.CustomerPersistence;
import org.radot.hibernate.persistences.DetPenjualanPersistence;
import org.radot.hibernate.persistences.PenjualanPersistence;
import org.radot.hibernate.persistences.SalesmanPersistence;
import org.radot.json.beans.PenjualanItem;
import org.radot.json.beans.PenjualanResult;
import org.radot.json.beans.PenjualanSelectParam;
import org.radot.json.servlet.JsonServletHandler;
import org.radot.utils.Md5;

public class JsonPenjualanHandler extends JsonServletHandler<PenjualanSelectParam, PenjualanResult> {

	public JsonPenjualanHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
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
		PenjualanItem _item;
		final List<PenjualanItem> _items = new ArrayList<PenjualanItem>();
//		int limit = 10;
//		long offset = Integer.parseInt(this.param.getOffset());
		int page = 0;
		
		
		/*search*/
		ArrayList<Criterion> _arrayCrit = new ArrayList<Criterion>();
		 String _paramBy = this.param.getByValuePenj();
		 String _paramQuery = "%"+this.param.getQueryDataPenj()+"%";
		 
		Criterion _filterByOrderNumb = Restrictions.ilike("orderNumb", _paramQuery);
		Criterion _filterByFakturNumb = Restrictions.ilike("fakturNumb", _paramQuery);
		Criterion _filterByCustName = Restrictions.ilike("name","%"+ _paramQuery+"%");
		
		if(_paramBy.equalsIgnoreCase("custName")){
			
			List<CustomerEntity> _listCutomer = new ArrayList<CustomerEntity>();
			ArrayList<Criterion> _cariNama = new ArrayList<Criterion>();
			_cariNama.add(_filterByCustName);
			_listCutomer = BaseEntity.listDataOffset(CustomerEntity.class, _cariNama, null,null,null);
			if(_listCutomer.size() == 0){
				result.setCode(766);
				result.setMessage("Nama customer tidak ditemukan");
				return;
			}
			Criterion _a = null;
			for(CustomerEntity cust:_listCutomer){
				if(null == _a){
					_a = Restrictions.eq("customerEnt", cust);
				}else{
					_a = Restrictions.or(_a, Restrictions.eq("customerEnt", cust));
				}
				
				
			}
			_arrayCrit.add(_a);
		}
		if(_paramBy.equalsIgnoreCase("orderNumb")){
			_arrayCrit.add(_filterByOrderNumb);
		}
		
		if(_paramBy.equalsIgnoreCase("fakturNumb")){
			_arrayCrit.add(_filterByFakturNumb);
		}
		String _from = this.param.getDateFrom();
		String _to = this.param.getDateTo();
		String expectedPattern = "MM/dd/yyyy";
	    SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
	    Date _datFrom = formatter.parse(_from);
	    Date _datTo = formatter.parse(_to);
		Number _count = new PenjualanPersistence().getCountByPenjualan(_paramBy,_paramQuery,_datFrom, _datTo);
		if(_paramBy.equalsIgnoreCase("customerId")){
			CustomerEntity _CustomerEntity = new CustomerPersistence().getByRecId(Long.valueOf(this.param.getQueryDataPenj()));
			if(null != _CustomerEntity){
				Criterion _byCustomer = Restrictions.eq("customerEnt", _CustomerEntity);
				_arrayCrit.add(_byCustomer);
				_count = new PenjualanPersistence().getCountByCust(_CustomerEntity,_datFrom, _datTo);
			}
		}
		if(_paramBy.equalsIgnoreCase("salesId")){
			SalesmanEntity _SalesmanEntity = new SalesmanPersistence().getByRecId(Long.valueOf(this.param.getQueryDataPenj()));
			if(null != _SalesmanEntity){
				Criterion _bySales = Restrictions.eq("salesmanEnt", _SalesmanEntity);
				_arrayCrit.add(_bySales);
				_count = new PenjualanPersistence().getCountBySales(_SalesmanEntity,_datFrom, _datTo);
				//System.out.println("count nya dapat" + _count);
			}
		}
	    
		
		
	    Criterion _critDateFrom = Restrictions.gt("orderDate", _datFrom);
	    Criterion _critDateTo = Restrictions.lt("orderDate", _datTo);
	    Criterion _beetWeen = Restrictions.and(_critDateFrom, _critDateTo);
	    
	    _arrayCrit.add(_beetWeen);
	    
		List<Order> _listOrder = new ArrayList<Order>();
		
		Order _orderByID = Order.desc("recId");
		_listOrder.add(_orderByID);
		
	DetPenjualanPersistence _w= new DetPenjualanPersistence();
		
		final List<PenjualanEntity> _entList = BaseEntity.listDataOffset(PenjualanEntity.class, _arrayCrit,_listOrder,  100, 1l);
//		final List<CustomerEntity> _entList = _w.getByCity(this.param.getCity());
		Long _lPage = 0l;
		if (null != _entList && !_entList.isEmpty()) {
			for (final PenjualanEntity _ent: _entList) {
				_item = new PenjualanItem();
				if(_ent.getRecId() != null){
					_item.setId(_ent.getRecId());
					}
					if(_ent.getOrderDate() != null){
					_item.setOrderDate(_ent.getOrderDate());
					}
					if(_ent.getOrderNumb() != null){
					_item.setOrderNumb(_ent.getOrderNumb());
					}
					if(_ent.getFakturNumb() != null){
					_item.setFakturNumb(_ent.getFakturNumb());
					}
				if(_ent.getCustomerEnt() != null){
				_item.setCustId(_ent.getCustomerEnt().getRecId());
				}
				if(_ent.getCustomerEnt() != null){
				_item.setCustCode(_ent.getCustomerEnt().getCode());
				}
				if(_ent.getCustomerEnt() != null){
				_item.setCustName(_ent.getCustomerEnt().getName());
				}
				if(_ent.getCustomerEnt() != null){
				_item.setCustAddress(_ent.getCustomerEnt().getAddress1());
				}
				if(_ent.getCustomerEnt() != null){
				_item.setCustAddress2(_ent.getCustomerEnt().getAddress2());
				}
				if(_ent.getCustomerEnt() != null){
				_item.setCustCity(_ent.getCustomerEnt().getCity());
				}
				if(_ent.getSalesmanEnt() != null){
				_item.setSalesId(_ent.getSalesmanEnt().getRecId());
				}
				if(_ent.getSalesmanEnt() != null){
				_item.setSalesCode(_ent.getSalesmanEnt().getCode());
				}
				if(_ent.getSalesmanEnt() != null){
				_item.setSalesName(_ent.getSalesmanEnt().getName());
				}
				if(_ent.getKeterangan() != null){
				_item.setKeterangan(_ent.getKeterangan());
				}
				/*if(_ent.getTotDiscPenjualan() != null){
				_item.setTotDisc(_ent.getTotDiscPenjualan());
				}*/
				
				Criterion _orderNumb = Restrictions.eq("penjualanEnt", _ent);
				ArrayList<Criterion> _arrayCritForDetail = new ArrayList<Criterion>();
				_arrayCritForDetail.add(_orderNumb);
				//final DetailPenjualanEntity _detJualEnt = new DetPenjualanPersistence().getByPenjEnt(_ent);
				final List<DetailPenjualanEntity> _detJualEnt = BaseEntity.listDataOffset(DetailPenjualanEntity.class, _arrayCritForDetail,null,  null, null);
				if(_detJualEnt != null){
					BigDecimal _totJualBruto = Md5.getTotalBruto(_detJualEnt);
					
					 _item.setTotJualBruto(_totJualBruto);
					 
					 BigDecimal _totJualNettoDetail = Md5.getTotalNetto(_detJualEnt);
						
					 BigDecimal _totDisc = _totJualBruto.subtract(_totJualNettoDetail);
					 _item.setTotDisc(_totDisc);
				//Test PPN
				if(_ent !=null){
					BigDecimal _cepe = new BigDecimal(100);
					//BigDecimal _totJualBruto = _ent.getTotPenjualanBrutoIdr();
					
					//BigDecimal _totDisc = _ent.getTotDiscPenjualan();
					BigDecimal _totJualBrutoAfterDisc = _totJualBruto.subtract(_totDisc);
					BigDecimal _totPpnA = _ent.getPpn().divide(_cepe,100, RoundingMode.HALF_UP);
					BigDecimal _totPpn = _totPpnA.multiply(_totJualBrutoAfterDisc);
					BigDecimal _totJualNetto = _totJualBrutoAfterDisc.add(_totPpn);
					
					
					_item.setTotPpn(_totPpn);
					_item.setTotJualNettoBeforePpn(_totJualBrutoAfterDisc);
					_item.setTotJualNetto(_totJualNetto);
						
				}}
				
				
				if(_ent.getTerkirim() != null){
					_item.setTerkirim(_ent.getTerkirim());
					}
				
				if(_ent.getPpn() != null){
					_item.setPpn(_ent.getPpn());
				}
				
				if(_ent.getKeterangan() != null){
					_item.setKeterangan(_ent.getKeterangan());
					}
				
				_items.add(_item);
				_lPage = _ent.getRecId();
			}
			
		}
	
		Long _modulo = _count.longValue()%100;
		Long _totalPage = _count.longValue()/100;
		if(_modulo != 0){
			_totalPage = _totalPage + 1;
		}
		_session.setAttribute("itemsPenjualan", _items);
		_session.setAttribute("offsetLast", _lPage);
		_session.setAttribute("totalData", Integer.parseInt(_count.toString()));
		_session.setAttribute("orderList",  _listOrder);
		_session.setAttribute("arrayCrit", _arrayCrit);
		//System.out.println(_count.longValue()+"  s");
		
		
		this.result.setPage(_totalPage.toString());
		this.result.setItems(_items);
		_session.setAttribute("dotPage", "True"); //Stop
		new Thread(new JsonPenjualanHandler.BackgroundDataLoader(_session)).start();

	}
	public static class BackgroundDataLoader implements Runnable{
		private String _totData;
		private HttpSession session;
		private Integer intPage;
		private Integer intData;
		private Integer intCurrent;
		private long lastOffset;
		private List<PenjualanItem> items;
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
				this.intPage = this.intData/100+1;
			}else{
				this.intPage = this.intData/100;
			}
			
			List<PenjualanItem> _items = new ArrayList<PenjualanItem>();
			_items = (List<PenjualanItem>) this.session.getAttribute("itemsPenjualan");
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
			//sTOP PAGGING END
			
			HashMap<String, ArrayList<PenjualanItem>> _wew = new HashMap<String, ArrayList<PenjualanItem>>();
				Long _totData = Long.valueOf(this.intData);
				List<PenjualanItem> _items = new ArrayList<PenjualanItem>();
				List<PenjualanEntity> _entList = null;
				int _indata = (int) (_totData%100);
				
				try {
					_entList = BaseEntity.listDataOffset(PenjualanEntity.class, this._critArray,this._listOrder,  null, 1l);
				} catch (Exception e) {
					// TODO: handle exception
				}
				if (null != _entList && !_entList.isEmpty()) {
					PenjualanItem _item;
					int i = 0;
					int _p = -1;
					String _chekLast = "";
					for (final PenjualanEntity _ent: _entList) {
						//sTOP PAGGING
						//System.out.println("------------");
						_chString = session.getAttribute("dotPage").toString();
						if(_chString.equalsIgnoreCase("False")){
							//System.out.println("STOP");
							Thread.currentThread().isInterrupted();
							break;
						}
						//sTOP PAGGING
						i = i+1;
						_item = new PenjualanItem();
						if(_ent.getRecId() != null){
						_item.setId(_ent.getRecId());
						}
						if(_ent.getOrderDate() != null){
						_item.setOrderDate(_ent.getOrderDate());
						}
						if(_ent.getOrderNumb() != null){
						_item.setOrderNumb(_ent.getOrderNumb());
						}
						if(_ent.getFakturNumb() != null){
						_item.setFakturNumb(_ent.getFakturNumb());
						}
						if(_ent.getCustomerEnt() != null){
							_item.setCustId(_ent.getCustomerEnt().getRecId());
							}
							if(_ent.getCustomerEnt() != null){
							_item.setCustCode(_ent.getCustomerEnt().getCode());
							}
							if(_ent.getCustomerEnt() != null){
							_item.setCustName(_ent.getCustomerEnt().getName());
							}
							if(_ent.getCustomerEnt() != null){
							_item.setCustAddress(_ent.getCustomerEnt().getAddress1());
							}
							if(_ent.getCustomerEnt() != null){
							_item.setCustAddress2(_ent.getCustomerEnt().getAddress2());
							}
							if(_ent.getCustomerEnt() != null){
							_item.setCustCity(_ent.getCustomerEnt().getCity());
							}
							if(_ent.getSalesmanEnt() != null){
							_item.setSalesId(_ent.getSalesmanEnt().getRecId());
							}
							if(_ent.getSalesmanEnt() != null){
							_item.setSalesCode(_ent.getSalesmanEnt().getCode());
							}
							if(_ent.getSalesmanEnt() != null){
							_item.setSalesName(_ent.getSalesmanEnt().getName());
							}
							if(_ent.getKeterangan() != null){
							_item.setKeterangan(_ent.getKeterangan());
							}
							/*if(_ent.getTotDiscPenjualan() != null){
							_item.setTotDisc(_ent.getTotDiscPenjualan());
								}*/
							Criterion _orderNumb = Restrictions.eq("penjualanEnt", _ent);
							ArrayList<Criterion> _arrayCritForDetail = new ArrayList<Criterion>();
							_arrayCritForDetail.add(_orderNumb);
							//final DetailPenjualanEntity _detJualEnt = new DetPenjualanPersistence().getByPenjEnt(_ent);
							final List<DetailPenjualanEntity> _detJualEnt = BaseEntity.listDataOffset(DetailPenjualanEntity.class, _arrayCritForDetail,null,  null, null);
							if(_detJualEnt != null){
								BigDecimal _totJualBruto = Md5.getTotalBruto(_detJualEnt);
								
								 _item.setTotJualBruto(_totJualBruto);
								 
								 BigDecimal _totJualNettoDetail = Md5.getTotalNetto(_detJualEnt);
								 BigDecimal _totDisc = _totJualBruto.subtract(_totJualNettoDetail);
								 _item.setTotDisc(_totDisc);
							
							if(_ent.getPpn() != null){
							_item.setPpn(_ent.getPpn());
								}
							
							if(_ent.getKeterangan() != null){
								_item.setKeterangan(_ent.getKeterangan());
								}
							//Test PPN
							if(_ent !=null){
								BigDecimal _cepe = new BigDecimal(100);
								//BigDecimal _totJualBruto = _ent.getTotPenjualanBrutoIdr();
								
								//BigDecimal _totDisc = _ent.getTotDiscPenjualan();
								BigDecimal _totJualBrutoAfterDisc = _totJualBruto.subtract(_totDisc);
								BigDecimal _totPpnA = _ent.getPpn().divide(_cepe,100, RoundingMode.HALF_UP);
								
								BigDecimal _totPpn = _totPpnA.multiply(_totJualBrutoAfterDisc);
								_item.setTotPpn(_totPpn);
								
								
								BigDecimal _totJualNetto = _totJualBrutoAfterDisc.add(_totPpn);
								
								_item.setTotJualNettoBeforePpn(_totJualBrutoAfterDisc);
								_item.setTotJualNetto(_totJualNetto);
							}}
							/*if(_ent != null){
							BigDecimal _totJualNettoAfter = _ent.getTotPenjualanNettIdr();
							BigDecimal _totPpnBefore = _ent.getTotPpn();
							
							BigDecimal _totJualNettoBefore = _totJualNettoAfter.subtract(_totPpnBefore);
							
							
								_item.setTotJualNettoBeforePpn(_totJualNettoBefore);
							}*/
							if(_ent.getTerkirim() != null){
								
								_item.setTerkirim(_ent.getTerkirim());
								}
								
							/*_item.setTotJualNettoBeforePpn(_totJualNettoBefore);
								}*/
							
						_items.add(_item);
						this.lastOffset = _ent.getRecId();
						if(i == 100 ){
							_p = _p+1;
							i = 0;
							String _page = "page"+_p;
							//System.out.println(_page);
							if(this.intPage - _p == 2){
								_chekLast = "last";
							}
						_wew.put(_page, (ArrayList<PenjualanItem>) _items);
						_items = new ArrayList<PenjualanItem>();
						session.setAttribute("paggingDataPenjualan", _wew);
						}
						if(_chekLast.equalsIgnoreCase("last") && _indata != 0 && _indata == i){
							_p = _p+1;
							i = 0;
							String _page = "page"+_p;
							//System.out.println(_page);
							if(this.intPage - _p == 2){
								_chekLast = "last";
							}
						_wew.put(_page, (ArrayList<PenjualanItem>) _items);
						_items = new ArrayList<PenjualanItem>();
						session.setAttribute("paggingDataPenjualan", _wew);
						}
					}
				}
		}
	}
}

