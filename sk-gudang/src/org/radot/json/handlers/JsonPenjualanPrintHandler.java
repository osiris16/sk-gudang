package org.radot.json.handlers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.radot.hibernate.entities.BaseEntity;
import org.radot.hibernate.entities.PenjualanEntity;
import org.radot.hibernate.persistences.PenjualanPersistence;
import org.radot.json.beans.PenjualanItem;
import org.radot.json.beans.PenjualanResult;
import org.radot.json.beans.PenjualanSelectParam;
import org.radot.json.servlet.JsonServletHandler;
import org.radot.utils.ExportToExcel;

public class JsonPenjualanPrintHandler extends JsonServletHandler<PenjualanSelectParam, PenjualanResult> {

	public JsonPenjualanPrintHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
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
		PenjualanItem _item;
		String retPage = "";
		final List<PenjualanItem> _items = new ArrayList<PenjualanItem>();
//		int limit = 10;
//		long offset = Integer.parseInt(this.param.getOffset());
		int page = 0;
		 String _paramFrom = this.param.getDateFrom();
		 String _paramTo = this.param.getDateTo();
		 ArrayList<Criterion> _arrayCrit = new ArrayList<Criterion>();
	
	 	Calendar _startCal = new GregorianCalendar();
	 	String _starDate = _paramFrom;
	 	String _startArray[] = _starDate.split("/");
	 	int _sInt = Integer.valueOf(_startArray[0]);
	 	_startCal.set(GregorianCalendar.YEAR, Integer.valueOf(_startArray[2]));
	 	_startCal.set(GregorianCalendar.MONTH, _sInt-1);
	 	_startCal.set(GregorianCalendar.DATE, Integer.valueOf(_startArray[1]));
	 	_startCal.set(GregorianCalendar.HOUR_OF_DAY, 0);
	 	SimpleDateFormat _f = new SimpleDateFormat("MM/dd/yyyy hhhh");
	 	
	 	
	 	Calendar _endCal = new GregorianCalendar();
	 	String _endDate = _paramTo;
	 	String _endArray[] = _endDate.split("/");
	 	int _eInt = Integer.valueOf(_endArray[0]);
	 	_endCal.set(GregorianCalendar.YEAR, Integer.valueOf(_endArray[2]));
	 	_endCal.set(GregorianCalendar.MONTH, _eInt - 1);
	 	_endCal.set(GregorianCalendar.DATE, Integer.valueOf(_endArray[1]));
	 	_endCal.set(GregorianCalendar.HOUR_OF_DAY, 23);
	 	
		Date _s = new Date();
		Date _e = new Date();
		_s.setTime(_startCal.getTimeInMillis());
		_e.setTime(_endCal.getTimeInMillis());
		//System.out.println("Start date "+_f.format(_startCal.getTime()));
		//System.out.println("end date "+_f.format(_endCal.getTime()));
		
		Criterion _start = Restrictions.gt("orderDate", _s);
		Criterion _end = Restrictions.lt("orderDate", _e);
		Criterion _penjualan = Restrictions.ilike("orderNumb", "%"+this.param.getOrderNumb()+"%");
		String _orderNumb = this.param.getOrderNumb();
		if(!_orderNumb.equalsIgnoreCase("")){
			_arrayCrit.add(_penjualan);
		}
		Criterion _between = Restrictions.and(_start, _end);
		_arrayCrit.add(_between);
		_arrayCrit.add(_penjualan);
//		QUERY
//		Criterion _code = Restrictions.ilike("code", this.param.getId());
//		_arrayCrit.add(_code);
//		final List<DetailPembelianEntity> _entst = BaseEntity.listDataOffset(DetailPembelianEntity.class, _arrayCrit,null,  null, null);
//		final List<TripEntity> _entst = BaseEntity.list(TripEntity.class, null, null);
		PenjualanPersistence _w =  new PenjualanPersistence();
		
		
		List<Order> _listOrder = new ArrayList<Order>();
		String _actType = this.param.getActiontype();
		
		
		Order _orderByID = Order.desc("recId");
		_listOrder.add(_orderByID);
		try {
			if(_actType.equalsIgnoreCase("DOWNLOADEXCEL")){
				final List<PenjualanEntity> _list = BaseEntity.listDataOffset(PenjualanEntity.class, _arrayCrit,_listOrder,  null, null);
				SimpleDateFormat _forPrint = new SimpleDateFormat("MMddyyyy");
				String _mes = ExportToExcel.printPenjualan(_list,_forPrint.format(_startCal.getTime()),_forPrint.format(_endCal.getTime()));
				this.result.setMessage(_mes);
				return;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		final List<PenjualanEntity> _entList = BaseEntity.listDataOffset(PenjualanEntity.class, _arrayCrit,_listOrder,  50, 1l);
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
				if(_ent.getTotDiscPenjualan() != null){
				_item.setTotDisc(_ent.getTotDiscPenjualan());
				}
				if(_ent.getTotPenjualanBrutoIdr() != null){
				_item.setTotJualBruto(_ent.getTotPenjualanBrutoIdr());
				}
				//Test PPN
				if(_ent !=null){
					BigDecimal _cepe = new BigDecimal(100);
					BigDecimal _totJualBruto = _ent.getTotPenjualanBrutoIdr();
					
					BigDecimal _totDisc = _ent.getTotDiscPenjualan();
					BigDecimal _totJualBrutoAfterDisc = _totJualBruto.subtract(_totDisc);
					BigDecimal _totPpnA = _ent.getPpn().divide(_cepe,100, RoundingMode.HALF_UP);
					
					BigDecimal _totPpn = _totPpnA.multiply(_totJualBrutoAfterDisc);
					_item.setTotPpn(_totPpn);
					
					
					BigDecimal _totJualNetto = _totJualBrutoAfterDisc.add(_totPpn);
					
					_item.setTotJualNettoBeforePpn(_totJualBrutoAfterDisc);
					_item.setTotJualNetto(_totJualNetto);
						
				}
				/*if(_ent != null){
				BigDecimal _totJualNettoAfter = _ent.getTotPenjualanNettIdr();
				BigDecimal _totPpnBefore = _ent.getTotPpn();
				
				BigDecimal _totJualNettoBefore = _totJualNettoAfter.subtract(_totPpnBefore);
				
				
					_item.setTotJualNettoBeforePpn(_totJualNettoBefore);
				}*/
				
				if(_ent.getTerkirim() != null){
					_item.setTerkirim(_ent.getTerkirim());
					}
				/*if(_ent.getTotPenjualanNettIdr() != null){
				_item.setTotJualNetto(_ent.getTotPenjualanNettIdr());
				}*/
				if(_ent.getPpn() != null){
					_item.setPpn(_ent.getPpn());
				}
				//Test PPN
				/*if(_ent.getTotPpn() != null){
					_item.setTotPpn(_ent.getTotPpn());
					}*/
				if(_ent.getKeterangan() != null){
					_item.setKeterangan(_ent.getKeterangan());
					}
				
				_items.add(_item);
				_lPage = _ent.getRecId();
			}
			
		}
	
		_session.setAttribute("dotPage", "True"); //Stop
		new Thread(new JsonPenjualanPrintHandler.BackgroundDataLoader(_session)).start();

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
				this.intPage = this.intData/50+1;
			}else{
				this.intPage = this.intData/50;
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
				int _indata = (int) (_totData%50);
				
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
								if(_ent.getTotDiscPenjualan() != null){
								_item.setTotDisc(_ent.getTotDiscPenjualan());
									}
								if(_ent.getTotPenjualanBrutoIdr() != null){
								_item.setTotJualBruto(_ent.getTotPenjualanBrutoIdr());
									}
								/*if(_ent.getTotPenjualanNettIdr() != null){
								_item.setTotJualNetto(_ent.getTotPenjualanNettIdr());
									}*/
								if(_ent.getPpn() != null){
								_item.setPpn(_ent.getPpn());
									}
								//Test PPN
								/*if(_ent.getTotPpn() != null){
									_item.setTotPpn(_ent.getTotPpn());
									}*/
								if(_ent.getKeterangan() != null){
									_item.setKeterangan(_ent.getKeterangan());
									}
								//Test PPN
								if(_ent !=null){
									BigDecimal _cepe = new BigDecimal(100);
									BigDecimal _totJualBruto = _ent.getTotPenjualanBrutoIdr();
									
									BigDecimal _totDisc = _ent.getTotDiscPenjualan();
									BigDecimal _totJualBrutoAfterDisc = _totJualBruto.subtract(_totDisc);
									BigDecimal _totPpnA = _ent.getPpn().divide(_cepe,100, RoundingMode.HALF_UP);
									
									BigDecimal _totPpn = _totPpnA.multiply(_totJualBrutoAfterDisc);
									_item.setTotPpn(_totPpn);
									
									
									BigDecimal _totJualNetto = _totJualBrutoAfterDisc.add(_totPpn);
									
									_item.setTotJualNettoBeforePpn(_totJualBrutoAfterDisc);
									_item.setTotJualNetto(_totJualNetto);
								}
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
						if(i == 50 ){
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

