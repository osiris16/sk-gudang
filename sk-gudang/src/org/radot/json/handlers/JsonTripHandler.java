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
import org.radot.hibernate.entities.DetailPembelianEntity;
import org.radot.hibernate.entities.DetailPenjualanEntity;
import org.radot.hibernate.entities.TripEntity;
import org.radot.hibernate.persistences.TripPersistence;
import org.radot.json.beans.TripItem;
import org.radot.json.beans.TripResult;
import org.radot.json.beans.TripSelectParam;
import org.radot.json.servlet.JsonServletHandler;
import org.radot.utils.ExportToExcel;
import org.radot.utils.Md5;

public class JsonTripHandler extends JsonServletHandler<TripSelectParam, TripResult> {

	public JsonTripHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
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
		TripItem _item;
		String retPage = "";
		final List<TripItem> _items = new ArrayList<TripItem>();
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
		
		Criterion _start = Restrictions.gt("trip_date", _s);
		Criterion _end = Restrictions.lt("trip_date", _e);
		Criterion _trip = Restrictions.ilike("trip_numb", "%"+this.param.getTripNumb()+"%");
		String _tripNUmb = this.param.getTripNumb();
		if(!_tripNUmb.equalsIgnoreCase("")){
			_arrayCrit.add(_trip);
		}
		Criterion _between = Restrictions.and(_start, _end);
		_arrayCrit.add(_between);
		_arrayCrit.add(_trip);
//		QUERY
//		Criterion _code = Restrictions.ilike("code", this.param.getId());
//		_arrayCrit.add(_code);
//		final List<DetailPembelianEntity> _entst = BaseEntity.listDataOffset(DetailPembelianEntity.class, _arrayCrit,null,  null, null);
//		final List<TripEntity> _entst = BaseEntity.list(TripEntity.class, null, null);
		TripPersistence _w =  new TripPersistence();
		
		
		List<Order> _listOrder = new ArrayList<Order>();
		String _actType = this.param.getActiontype();
		
		
		Order _orderByID = Order.desc("recId");
		_listOrder.add(_orderByID);
		try {
			if(_actType.equalsIgnoreCase("DOWNLOADEXCEL")){
				final List<TripEntity> _list = BaseEntity.listDataOffset(TripEntity.class, _arrayCrit,_listOrder,  null, null);
				SimpleDateFormat _forPrint = new SimpleDateFormat("MMddyyyy");
				String _mes = ExportToExcel.printTrip(_list,_forPrint.format(_startCal.getTime()),_forPrint.format(_endCal.getTime()));
				this.result.setMessage(_mes);
				return;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		final List<TripEntity> _entList = BaseEntity.listDataOffset(TripEntity.class, _arrayCrit,_listOrder,  10, null);
		
//		final List<VendorEntity> _entList = _w.getByCity(this.param.getCity());
		Long _lPage = 0l;
		Long _sizePage = 0l;
		if (null != _entList && !_entList.isEmpty()) {
			for (final TripEntity _ent: _entList) {
				_item = new TripItem();
				_item.setId(_ent.getRecId());
				_item.setTrip_date(_ent.getTrip_date());
				_item.setTrip_numb(_ent.getTrip_numb());
				_item.setTrip_dateNote(_ent.getTrip_dateNote());
				_item.setTrip_noteNumber(_ent.getTrip_noteNumber());
				
				_item.setTotCost(_ent.getTotCost());
				_item.setTotDisc(_ent.getTotDisc());
				BigDecimal _oneHundred = new BigDecimal(100);
				BigDecimal _totCost = _ent.getTotCost();
				BigDecimal _totDisc = _ent.getTotDisc();
				BigDecimal _totCostHelp = _totCost.divide(_oneHundred,100, RoundingMode.HALF_UP);
				BigDecimal _totDiscHelp = _totDisc.divide(_oneHundred,100, RoundingMode.HALF_UP);
				
				
				Criterion _tripNumb = Restrictions.eq("tripEnt", _ent);
				ArrayList<Criterion> _arrayCritForDetail = new ArrayList<Criterion>();
				_arrayCritForDetail.add(_tripNumb);
				final List<DetailPembelianEntity> _detBeliEnt = BaseEntity.listDataOffset(DetailPembelianEntity.class, _arrayCritForDetail,null,  null, null);
				if(_detBeliEnt != null){
					BigDecimal _TotBeliBrutoIdr = Md5.getTotalBrutoBeliIdr(_detBeliEnt);
					 
					BigDecimal _TotBeliBrutoVta = Md5.getTotalBrutoBeliVta(_detBeliEnt);
					
					
				BigDecimal _BeforeCost = _TotBeliBrutoVta.subtract(_TotBeliBrutoVta.multiply(_totDiscHelp));
				BigDecimal _TotBeliNettoVta = _BeforeCost.add(_BeforeCost.multiply(_totCostHelp));
				
				_item.setTotBeliNettoVta(_TotBeliNettoVta);
				
				
				BigDecimal _BeforeCostIdr = _TotBeliBrutoIdr.subtract(_TotBeliBrutoIdr.multiply(_totDiscHelp));
				BigDecimal _TotBeliNettoIdr = _BeforeCostIdr.add(_BeforeCostIdr.multiply(_totCostHelp));
				_item.setTotBeliNettoIdr(_TotBeliNettoIdr);
				
				_item.setTotBeliBrutoVta(_TotBeliBrutoVta);
				_item.setTotBeliBrutoIdr(_TotBeliBrutoIdr);
				_item.setTotCostHelp(_totCostHelp);
				_item.setTotDiscHelp(_totDiscHelp);
				_item.setTotBeliCtn(_ent.getTotCarton());
				
				if(_ent.getVendorEnt() != null){
				_item.setVendId(_ent.getVendorEnt().getRecId());
				}
				if(_ent.getVendorEnt() != null){
				_item.setTrip_vendName(_ent.getVendorEnt().getName());
				}
				if(_ent.getVendorEnt() != null){
				_item.setTrip_vendCountry(_ent.getVendorEnt().getCountry());
				}
				if(_ent.getVendorEnt() != null){
				_item.setTrip_vta(_ent.getVendorEnt().getVta());
				}
				_item.setCurrencyIDR(_ent.getCurrencyIDR());
				_item.setDateReceive(_ent.getDateReceive());
						_items.add(_item);
						if (_items.size() == 10){
							break;
						}
					}
					
				}}
		
		Number _num  = new TripPersistence().getCountByTrip(_between);
		_sizePage = _sizePage +_num.longValue();
//		System.out.println("Hasil cari "+ _num.longValue() +" trip Rec Id" +_ent.getRecId());
			//System.out.println(_sizePage + " total data");
		if(_sizePage%10 != 0){
			retPage =String.valueOf( _sizePage/10+1);
		}else{
			retPage =String.valueOf( _sizePage/10);
		}
		this.result.setItems(_items);
		this.result.setPage(retPage);
		//System.out.println(_items.size() + " jumlah data itemss");
		_session.setAttribute("itemsTrip", _items);
		_session.setAttribute("offsetLast", _lPage);
		_session.setAttribute("totalData",  Integer.parseInt(String.valueOf(_sizePage)));
		_session.setAttribute("orderList",  _listOrder);
		_session.setAttribute("arrayCrit", _arrayCrit);
//		_session.setAttribute("pageData1", (ArrayList<DetPembelianItem>) _items);
//		System.out.println(_entst.size()+"  s");
//		int _totPageMod = 0;
//		_totPageMod = _entst.size()/10;
//		if(_entst.size()%10 != 0){
//			_totPageMod = _entst.size()/10 +1;
//		}
//	
//		this.result.setPage(String.valueOf(_totPageMod));
		_session.setAttribute("dotPage", "True");
		new Thread(new JsonTripHandler.BackgroundDataLoader(_session)).start();
	}
	public static class BackgroundDataLoader implements Runnable{
		private String _totData;
		private HttpSession session;
		private Integer intPage;
		private Integer intData;
		private Integer intCurrent;
		private ArrayList<TripItem> _firstPage;
		private long lastOffset;
		private List<TripItem> items;
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
			
			this.intPage = this.intData/10;
			this._firstPage = (ArrayList<TripItem>) session.getAttribute("pageData1");
			List<TripItem> _items = new ArrayList<TripItem>();
			_items = (List<TripItem>) this.session.getAttribute("itemsTrip");
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
			List<TripItem> _items = new ArrayList<TripItem>();
			HashMap<String, ArrayList<TripItem>> _wew = new HashMap<String, ArrayList<TripItem>>();
			final List<TripEntity> _entList = BaseEntity.listDataOffset(TripEntity.class, this._critArray,this._listOrder,  null, null);
			int _p = -1;
			int i = 0;
			int _perP = 0;
			String _chekLast = "";
			Long _totData = Long.valueOf(this.intData);
			int _indata = (int) (_totData%10);
				if (null != _entList && !_entList.isEmpty()) {
					TripItem _item;
					for (final TripEntity _ent: _entList) {
						
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
						_item = new TripItem();
						_item.setId(_ent.getRecId());
						_item.setTrip_date(_ent.getTrip_date());
						_item.setTrip_numb(_ent.getTrip_numb());
						_item.setTrip_dateNote(_ent.getTrip_dateNote());
						_item.setTrip_noteNumber(_ent.getTrip_noteNumber());
						//_item.setTotBeliBrutoVta(_ent.getTotPembelianBrutoVta());
						//_item.setTotBeliBrutoIdr(_ent.getTotPembelianBrutoIdr());
						_item.setTotCost(_ent.getTotCost());
						_item.setTotDisc(_ent.getTotDisc());
						
						BigDecimal _oneHundred = new BigDecimal(100);
						BigDecimal _totCost = _ent.getTotCost();
						BigDecimal _totDisc = _ent.getTotDisc();
						BigDecimal _totCostHelp = _totCost.divide(_oneHundred,100, RoundingMode.HALF_UP);
						BigDecimal _totDiscHelp = _totDisc.divide(_oneHundred,100, RoundingMode.HALF_UP);
						
						Criterion _tripNumb = Restrictions.eq("tripEnt", _ent);
						ArrayList<Criterion> _arrayCritForDetail = new ArrayList<Criterion>();
						_arrayCritForDetail.add(_tripNumb);
						final List<DetailPembelianEntity> _detBeliEnt = BaseEntity.listDataOffset(DetailPembelianEntity.class, _arrayCritForDetail,null,  null, null);
						if(_detBeliEnt != null){
							BigDecimal _TotBeliBrutoIdr = Md5.getTotalBrutoBeliIdr(_detBeliEnt);
							 
							BigDecimal _TotBeliBrutoVta = Md5.getTotalBrutoBeliVta(_detBeliEnt);
							
						
						BigDecimal _BeforeCost = _TotBeliBrutoVta.subtract(_TotBeliBrutoVta.multiply(_totDiscHelp));
						BigDecimal _TotBeliNettoVta = _BeforeCost.add(_BeforeCost.multiply(_totCostHelp));
						
						_item.setTotBeliNettoVta(_TotBeliNettoVta);
						
						
						BigDecimal _BeforeCostIdr = _TotBeliBrutoIdr.subtract(_TotBeliBrutoIdr.multiply(_totDiscHelp));
						BigDecimal _TotBeliNettoIdr = _BeforeCostIdr.add(_BeforeCostIdr.multiply(_totCostHelp));
						_item.setTotBeliNettoIdr(_TotBeliNettoIdr);
						
						_item.setTotBeliBrutoVta(_TotBeliBrutoVta);
						_item.setTotBeliBrutoIdr(_TotBeliBrutoIdr);
						_item.setTotCostHelp(_totCostHelp);
						_item.setTotDiscHelp(_totDiscHelp);
						_item.setTotBeliCtn(_ent.getTotCarton());
						if(_ent.getVendorEnt() != null){
						_item.setVendId(_ent.getVendorEnt().getRecId());
						}
						if(_ent.getVendorEnt() != null){
						_item.setTrip_vendName(_ent.getVendorEnt().getName());
						}
						if(_ent.getVendorEnt() != null){
						_item.setTrip_vendCountry(_ent.getVendorEnt().getCountry());
						}
						if(_ent.getVendorEnt() != null){
						_item.setTrip_vta(_ent.getVendorEnt().getVta());
						}
						_item.setCurrencyIDR(_ent.getCurrencyIDR());
						_item.setDateReceive(_ent.getDateReceive());
						
						_items.add(_item);
						
						}
						_perP = _perP+1;
						if (_perP == 10){
							String _page = "";
							i = 0;
							_p = _p+1;
							  _page = "page"+_p;
							  if(this.intPage - _p == 1){
									_chekLast = "last";
								}
						_wew.put(_page, (ArrayList<TripItem>) _items);
						_items = new ArrayList<TripItem>();
						session.setAttribute("paggingDataTrip", _wew);
						_perP = 0;
							
							
							}
						if(_chekLast.equalsIgnoreCase("last") && _indata != 0 && _indata == i){
							_p = _p+1;
							i = 0;
							String _page = "page"+_p;
							if(this.intPage - _p == 2){
								_chekLast = "last";
							}
						_wew.put(_page, (ArrayList<TripItem>) _items);
						_items = new ArrayList<TripItem>();
						session.setAttribute("paggingDataVendor", _wew);
						}
						}
						
					}
				}
				
			}
			
	}
