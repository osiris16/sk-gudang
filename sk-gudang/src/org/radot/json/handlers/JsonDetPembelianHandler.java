package org.radot.json.handlers;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import org.radot.hibernate.entities.DetailPembelianEntity;
import org.radot.hibernate.entities.TripEntity;
import org.radot.hibernate.persistences.DetPembelianPersistence;
import org.radot.hibernate.persistences.TripPersistence;
import org.radot.json.beans.DetPembelianItem;
import org.radot.json.beans.DetPembelianResult;
import org.radot.json.beans.DetPembelianSelectParam;
import org.radot.json.servlet.JsonServletHandler;
import org.radot.utils.ExportToPdf;

public class JsonDetPembelianHandler extends JsonServletHandler<DetPembelianSelectParam, DetPembelianResult> {

	
	public JsonDetPembelianHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
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
		DetPembelianItem _item;
		final List<DetPembelianItem> _items = new ArrayList<DetPembelianItem>();
//		int limit = 10;
//		long offset = Integer.parseInt(this.param.getOffset());
		int page = 0;
		
		ArrayList<Criterion> _arrayCrit = new ArrayList<Criterion>();
		 String _paramBy = this.param.getByValueBeli();
		// String _paramQuery = "%"+this.param.getQueryDataBeli()+"%";
		 /*String _fakturCheck = this.param.getFakturChek();
		 if(null == _fakturCheck){
			 _fakturCheck = "";
		 }*/
		Criterion _filterByTripNumb = Restrictions.eq("trip_numb", this.param.getQueryDataBeli());
		//Criterion _filterByFakturNumb = Restrictions.ilike("fakturNumb", _paramQuery);
		
		if(_paramBy.equalsIgnoreCase("trip_numb")){
			_arrayCrit.add(_filterByTripNumb);
		}
		if(_paramBy.equalsIgnoreCase("trip_numbPrint")){
			_arrayCrit.add(_filterByTripNumb);
		}
		/*if(_paramBy.equalsIgnoreCase("fakturNumb")){
			_arrayCrit.add(_filterByFakturNumb);
		
		}*/
//		final List<DetailPembelianEntity> _entst = BaseEntity.listDataOffset(DetailPembelianEntity.class, _arrayCrit,null,  null, null);
//		final List<TripEntity> _entst = BaseEntity.list(TripEntity.class, null, null);
		TripPersistence _w =  new TripPersistence();
		
		
		List<Order> _listOrder = new ArrayList<Order>();
		
		Order _orderByID = Order.desc("trip_numb");
		_listOrder.add(_orderByID);
		final List<TripEntity> _entList = BaseEntity.listDataOffset(TripEntity.class, _arrayCrit,_listOrder,  null, null);
//		final List<VendorEntity> _entList = _w.getByCity(this.param.getCity());
		Long _lPage = 0l;
		Long _sizePage = 0l;
		String _stopLoop = "";
		
		if(_paramBy.equalsIgnoreCase("trip_numbPrint")){
			ExportToPdf.tripId(this.param.getQueryDataBeli());
			this.result.setMessage(this.param.getQueryDataBeli());
			return;
		}
		
		if (null != _entList && !_entList.isEmpty()) {
			for (final TripEntity _ent: _entList) {
				if (_items.size() == 10){
					//System.out.println("masuk sini");
					break;
				}
				
				final List<DetailPembelianEntity> _pemb = new DetPembelianPersistence().getByTripList(_ent);
				if(null != _pemb){
				
					for(final DetailPembelianEntity _pemEnt : _pemb){
						_item = new DetPembelianItem();
						_item.setId(_pemEnt.getRecId());
						try {
							_item.setTripDate(_ent.getTrip_date());
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							_item.setTripNumber(_ent.getTrip_numb());
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							_item.setTripNumbStok(_pemEnt.getTripNumSeqStock());
							} catch (Exception e) {
								e.printStackTrace();
							}
						try {
							_item.setProductCode(_pemEnt.getStockEnt().getProductEnt().getCode());
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						try {
							_item.setProductName(_pemEnt.getStockEnt().getProductEnt().getName());
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							_item.setIdStock(_pemEnt.getStockEnt().getRecId());
							}catch (Exception e) {
								e.printStackTrace();
							}
						try {
							_item.setIdTrip(_pemEnt.getTripEnt().getRecId());
							}catch (Exception e) {
								e.printStackTrace();
							}
						try {
							_item.setStokCtn(_pemEnt.getStockEnt().getStokCtn());
							}catch (Exception e) {
							e.printStackTrace();
							}
							try {
								BigDecimal _isiPcs = _pemEnt.getStockEnt().getProductEnt().getIsiPcs();
								BigDecimal _isiCtn = _pemEnt.getStockEnt().getProductEnt().getIsiCtn();
								BigDecimal _stokCtn = _pemEnt.getStockEnt().getStokCtn();
								BigDecimal _stokPcs = _stokCtn.multiply(_isiCtn).multiply(_isiPcs);
								_item.setTotStokPcs(_stokPcs);
								}catch (Exception e) {
									e.printStackTrace();
								}
						
						try {
							_item.setProductImage(_pemEnt.getStockEnt().getProductEnt().getImage());
						} catch (Exception e) {
							// TODO: handle exception
						}
						try {
							_item.setIsiCtn(_pemEnt.getStockEnt().getProductEnt().getIsiCtn());
							}catch (Exception e) {
								e.printStackTrace();
							}
						try {
							_item.setSatIsiCtn(_pemEnt.getStockEnt().getProductEnt().getSatIsiCtn());
							}catch (Exception e) {
								e.printStackTrace();
							}
						try {
							_item.setIsiPcs(_pemEnt.getStockEnt().getProductEnt().getIsiPcs());
							}catch (Exception e) {
								e.printStackTrace();
							}
						try {
							_item.setTotQtyBeliCtn(_pemEnt.getQtyBeliCtn());
						} catch (Exception e) {
							// TODO: handle exception
						}
						try {
							BigDecimal _isiCtn = _pemEnt.getStockEnt().getProductEnt().getIsiCtn();
							BigDecimal _isiPcs = _pemEnt.getStockEnt().getProductEnt().getIsiPcs();
							BigDecimal _qtyBeliCtn =_pemEnt.getQtyBeliCtn();
							BigDecimal _qtyBeliCtn2 = _qtyBeliCtn.multiply(_isiCtn);
							BigDecimal _qtyBeliPcs = _qtyBeliCtn2.multiply(_isiPcs);
							_item.setTotQtyBeliPcs(_qtyBeliPcs);
						} catch (Exception e) {
							// TODO: handle exception
						}
						try {
							BigDecimal _isiCtn = _pemEnt.getStockEnt().getProductEnt().getIsiCtn();
							BigDecimal _isiPcs = _pemEnt.getStockEnt().getProductEnt().getIsiPcs();
							BigDecimal _qtyBeli = _pemEnt.getQtyBeliCtn();
							BigDecimal _qtyBeliPcs = _qtyBeli.multiply(_isiCtn).multiply(_isiPcs);
							BigDecimal _TotHargaBrutBeliPcsIdr = _pemEnt.getTotHargaBrutB_Idr().divide(_qtyBeliPcs,2,RoundingMode.HALF_UP);
							BigDecimal _TotHargaBrutBeliPcsVta = _pemEnt.getTotHargaBrutB_Vta().divide(_qtyBeliPcs,2,RoundingMode.HALF_UP);
							BigDecimal _TotHargaNettBeliPcsIdr = _pemEnt.getTotHargaNettB_Idr().divide(_qtyBeliPcs,2,RoundingMode.HALF_UP);
							BigDecimal _TotHargaNettBeliPcsVta = _pemEnt.getTotHargaNettB_Vta().divide(_qtyBeliPcs,2,RoundingMode.HALF_UP);
							BigDecimal _HargaJualPcs = _pemEnt.getHargaJ_CtnNew().divide(_isiCtn,2,RoundingMode.HALF_UP).divide(_isiPcs,2,RoundingMode.HALF_UP);
							BigDecimal _kurs = _pemEnt.getTripEnt().getCurrencyIDR();
							BigDecimal _HargaJualPcsVta=_HargaJualPcs.divide(_kurs,2,RoundingMode.HALF_UP);
							BigDecimal _HargaJualCtnVta=_pemEnt.getHargaJ_CtnNew().divide(_kurs,2,RoundingMode.HALF_UP);
							
							_item.setTotHargaBrutBeliPcsIdr(_TotHargaBrutBeliPcsIdr);
							_item.setTotHargaBrutBeliPcsVta(_TotHargaBrutBeliPcsVta);
							_item.setTotHargaNettBeliPcsIdr(_TotHargaNettBeliPcsIdr);
							_item.setTotHargaNettBeliPcsVta(_TotHargaNettBeliPcsVta);
							_item.setHargaJualPcs(_HargaJualPcs);
							_item.setHargaJualPcsVta(_HargaJualPcsVta);
							_item.setHargaJualCtnVta(_HargaJualCtnVta);
							
							
						} catch (Exception e) {
							// TODO: handle exception
						}
						try {
							_item.setTotHargaBrutBeliIdr(_pemEnt.getTotHargaBrutB_Idr());
						} catch (Exception e) {
							// TODO: handle exception
						}
						try {
							_item.setTotHargaBrutBeliVta(_pemEnt.getTotHargaBrutB_Vta());
						} catch (Exception e) {
							// TODO: handle exception
						}
						
						try {
							_item.setHargaBeliCtnVta(_pemEnt.getHargaBeliCtnVta());
						} catch (Exception e) {
							// TODO: handle exception
						}
						try{
							
							BigDecimal _oneHundred = new BigDecimal(100);
							BigDecimal _totCost = _ent.getTotCost();
							BigDecimal _totDisc = _ent.getTotDisc();
							BigDecimal _totCostHelp = _totCost.divide(_oneHundred,100, RoundingMode.HALF_UP);
							BigDecimal _totDiscHelp = _totDisc.divide(_oneHundred,100, RoundingMode.HALF_UP);
							
							BigDecimal _totHargaBrutoBeliVta = _pemEnt.getTotHargaBrutB_Vta();
							BigDecimal _totHargaNettBeliVtaBeforeCost = _totHargaBrutoBeliVta.subtract(_totHargaBrutoBeliVta.multiply(_totDiscHelp));
							BigDecimal _totHargaNettBeliVta = _totHargaNettBeliVtaBeforeCost.add(_totHargaNettBeliVtaBeforeCost.multiply(_totCostHelp));
							
							BigDecimal _totHargaBrutoBeliIdr = _pemEnt.getTotHargaBrutB_Idr();
							BigDecimal _totHargaNettBeliIdrBeforeCost = _totHargaBrutoBeliIdr.subtract(_totHargaBrutoBeliIdr.multiply(_totDiscHelp));
							BigDecimal _totHargaNettBeliIdr = _totHargaNettBeliIdrBeforeCost.add(_totHargaNettBeliIdrBeforeCost.multiply(_totCostHelp));
							
							_item.setTotHargaNettBeliVta(_totHargaNettBeliVta);
							_item.setTotHargaNettBeliIdr(_totHargaNettBeliIdr);
							
							BigDecimal _hargaCtnVta = _pemEnt.getHargaBeliCtnVta();
							BigDecimal _hargaCtnVtaNettoBeforeCost = _hargaCtnVta.subtract((_hargaCtnVta.multiply(_totDiscHelp)));
							BigDecimal _hargaCtnVtaNetto = _hargaCtnVtaNettoBeforeCost.add(_hargaCtnVtaNettoBeforeCost.multiply(_totCostHelp));
							_item.setHargaBeliCtnVtaNetto(_hargaCtnVtaNetto);
							
							BigDecimal _isiCtn = _pemEnt.getStockEnt().getProductEnt().getIsiCtn();
							BigDecimal _isiPcs = _pemEnt.getStockEnt().getProductEnt().getIsiPcs();
							BigDecimal _hargaBeliPcsVtaNetto = (_hargaCtnVtaNetto.divide(_isiCtn,100, RoundingMode.HALF_UP)).divide(_isiPcs,100, RoundingMode.HALF_UP);
							_item.setHargaBeliPcsVtaNetto(_hargaBeliPcsVtaNetto);
							
							BigDecimal _kurs = _ent.getCurrencyIDR();
							BigDecimal _hargaBeliPcsIdrNetto = _hargaBeliPcsVtaNetto.multiply(_kurs);
							_item.setHargaBeliPcsIdrNetto(_hargaBeliPcsIdrNetto);
							
							BigDecimal _HargaJualPcs = _pemEnt.getHargaJ_CtnNew().divide(_isiCtn,2,RoundingMode.HALF_UP).divide(_isiPcs,2,RoundingMode.HALF_UP);
							BigDecimal _profitIDR = _HargaJualPcs.subtract(_hargaBeliPcsIdrNetto);
							_item.setProfitIdr(_profitIDR);
						}
					 catch (Exception e) {
						// TODO: handle exception
					}
								
						
						/*try {
							_item.setTotHargaNettBeliIdr(_pemEnt.getTotHargaNettB_Idr());
						} catch (Exception e) {
							// TODO: handle exception
						}
						try {
							_item.setTotHargaNettBeliVta(_pemEnt.getTotHargaNettB_Vta());
						} catch (Exception e) {
							// TODO: handle exception
						}*/
						 
						try {
							_item.setHargaJualCtn(_pemEnt.getHargaJ_CtnNew());
						} catch (Exception e) {
							// TODO: handle exception
						}
						try {
							_item.setCost(_pemEnt.getTripEnt().getTotCost());
						} catch (Exception e) {
							// TODO: handle exception
						}
						try {
							_item.setDisc(_pemEnt.getTripEnt().getTotDisc());
						} catch (Exception e) {
							// TODO: handle exception
						}
						try {
							_item.setVendVta(_pemEnt.getTripEnt().getVendorEnt().getVta());
						} catch (Exception e) {
							// TODO: handle exception
						}
						try {
							_item.setTripKurs(_ent.getCurrencyIDR());
						
						} catch (Exception e) {
							// TODO: handle exception
						}
						/*DetailPembelianEntity _detPembelian = new DetPembelianPersistence().getByTripEnt(_ent);
						if(null != _detPembelian){
							_item.setTotHargaNettBeliVta(_detPembelian.getTotHargaNettB_Vta());
							_item.setTotHargaNettBeliIdr(_detPembelian.getTotHargaNettB_Idr());
						}*/
						_items.add(_item);
						
						if (_items.size() == 10){
							if(!_paramBy.equalsIgnoreCase("trip_numbPrint")){
								break;
							}
							
						}
					}
					
				}
			}
			for (final TripEntity _ent: _entList) {
				Number _num  = new DetPembelianPersistence().getCountByPembe2(_ent);
				_sizePage = _sizePage +_num.longValue();
				if(_sizePage >= 10 && _stopLoop.equalsIgnoreCase("")){
					_stopLoop = "stop";
					this.result.setItems(_items);
					Long kk = _sizePage%10;
					if(kk !=0){
						kk = _sizePage/10;
						kk = kk+1;
					}else{
						kk = _sizePage/10;
					}
					//System.out.println(kk);
					this.result.setPage(String.valueOf(kk));
				}
				//System.out.println("Hasil cari hh "+ _num.longValue() +"  Rec Id " +_ent.getRecId());
			}
			//System.out.println(_sizePage + " total Page");
		}
		if(_sizePage < 10 && _stopLoop.equalsIgnoreCase("")){
			this.result.setItems(_items);
			Long kk = _sizePage%10;
			if(kk !=0){
				kk = _sizePage/10;
				kk = kk+1;
			}else{
				kk = _sizePage/10;
			}
			//System.out.println(kk);
			this.result.setPage(String.valueOf(kk));
		}
		
		_session.setAttribute("itemsDetPembelian", _items);
		_session.setAttribute("offsetLast", _lPage);
		_session.setAttribute("totalData",  Integer.parseInt(String.valueOf(_sizePage/10)));
		_session.setAttribute("allData", Integer.parseInt(String.valueOf(_sizePage)));
		_session.setAttribute("orderList",  _listOrder);
		_session.setAttribute("arrayCrit", _arrayCrit);

		_session.setAttribute("dotPage", "True"); //Stop
		//System.out.println(_session.getAttribute("dotPage"));
		if(!_paramBy.equalsIgnoreCase("trip_numbPrint")){
			
			new Thread(new JsonDetPembelianHandler.BackgroundDataLoader(_session)).start();
		}
		
	}
	public static class BackgroundDataLoader implements Runnable{
		private String _totData;
		private HttpSession session;
		private Integer intPage;
		private Integer intData;
		private Integer allData;
		private Integer intCurrent;
		private ArrayList<DetPembelianItem> _firstPage;
		private long lastOffset;
		private List<DetPembelianItem> items;
		private List<Criterion> _critArray;
		private List<Order> _listOrder;
		
		@SuppressWarnings("unchecked")
		public  BackgroundDataLoader(final HttpSession _session) {
			//System.out.println("ss");
			this.session = _session;
			int _a = (Integer)session.getAttribute("allData");
			this._totData = String.valueOf(_a);
			this.allData = (Integer) session.getAttribute("allData");
			this._critArray = (List<Criterion>) _session.getAttribute("arrayCrit");
			this._listOrder = (List<Order>) _session.getAttribute("orderList");
			//System.out.println("sss " + session.getAttribute("totalData"));
			this.intData = (Integer) session.getAttribute("totalData");
			this.intPage = this.intData/10;
			this._firstPage = (ArrayList<DetPembelianItem>) session.getAttribute("pageData1");
			List<DetPembelianItem> _items = new ArrayList<DetPembelianItem>();
			_items = (List<DetPembelianItem>) this.session.getAttribute("ItemsDetPembelian");
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
			List<DetPembelianItem> _items = new ArrayList<DetPembelianItem>();
			HashMap<String, ArrayList<DetPembelianItem>> _wew = new HashMap<String, ArrayList<DetPembelianItem>>();
			final List<TripEntity> _entList = BaseEntity.listDataOffset(TripEntity.class, this._critArray,this._listOrder,  null, null);
			int _p = -1;
			int _perP = 0;
			int _a = this.intData;
			int countL = Integer.valueOf(this._totData);
			int countk= 0;
			if (null != _entList && !_entList.isEmpty()) {
				for (final TripEntity _ent: _entList) {
					countk = countk+1;
					final List<DetailPembelianEntity> _pemb = new DetPembelianPersistence().getByTripList(_ent);
					if(null != _pemb){
					
						for(final DetailPembelianEntity _pemEnt : _pemb){
							countk = countk+1;
							//System.out.println("------------");
							_chString = session.getAttribute("dotPage").toString();
							if(_chString.equalsIgnoreCase("False")){
								//System.out.println("STOP");
								Thread.currentThread().isInterrupted();
								break;
							}
							DetPembelianItem _item = new DetPembelianItem();
							_item.setId(_pemEnt.getRecId());
							try {
								_item.setTripDate(_ent.getTrip_date());
							} catch (Exception e) {
								e.printStackTrace();
							}
							try {
								_item.setTripNumber(_ent.getTrip_numb());
							} catch (Exception e) {
								e.printStackTrace();
							}
							try {
								_item.setTripNumbStok(_pemEnt.getTripNumSeqStock());
								} catch (Exception e) {
									e.printStackTrace();
								}
							try {
								_item.setProductCode(_pemEnt.getStockEnt().getProductEnt().getCode());
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								_item.setProductName(_pemEnt.getStockEnt().getProductEnt().getName());
							} catch (Exception e) {
								e.printStackTrace();
							}
							try {
								_item.setIdStock(_pemEnt.getStockEnt().getRecId());
								}catch (Exception e) {
									e.printStackTrace();
								}
							try {
								_item.setIdTrip(_pemEnt.getTripEnt().getRecId());
								}catch (Exception e) {
									e.printStackTrace();
								}
							try {
								_item.setStokCtn(_pemEnt.getStockEnt().getStokCtn());
								}catch (Exception e) {
								e.printStackTrace();
								}
								try {
									BigDecimal _isiPcs = _pemEnt.getStockEnt().getProductEnt().getIsiPcs();
									BigDecimal _isiCtn = _pemEnt.getStockEnt().getProductEnt().getIsiCtn();
									BigDecimal _stokCtn = _pemEnt.getStockEnt().getStokCtn();
									BigDecimal _stokPcs = _stokCtn.multiply(_isiCtn).multiply(_isiPcs);
									_item.setTotStokPcs(_stokPcs);
									}catch (Exception e) {
										e.printStackTrace();
									}
							
							try {
								_item.setProductImage(_pemEnt.getStockEnt().getProductEnt().getImage());
							} catch (Exception e) {
								// TODO: handle exception
							}
							try {
								_item.setIsiCtn(_pemEnt.getStockEnt().getProductEnt().getIsiCtn());
								}catch (Exception e) {
									e.printStackTrace();
								}
							try {
								_item.setSatIsiCtn(_pemEnt.getStockEnt().getProductEnt().getSatIsiCtn());
								}catch (Exception e) {
									e.printStackTrace();
								}
							try {
								_item.setIsiPcs(_pemEnt.getStockEnt().getProductEnt().getIsiPcs());
								}catch (Exception e) {
									e.printStackTrace();
								}
							try {
								_item.setTotQtyBeliCtn(_pemEnt.getQtyBeliCtn());
							} catch (Exception e) {
								// TODO: handle exception
							}
							try {
								BigDecimal _isiCtn = _pemEnt.getStockEnt().getProductEnt().getIsiCtn();
								BigDecimal _isiPcs = _pemEnt.getStockEnt().getProductEnt().getIsiPcs();
								BigDecimal _qtyBeliCtn =_pemEnt.getQtyBeliCtn();
								BigDecimal _qtyBeliCtn2 = _qtyBeliCtn.multiply(_isiCtn);
								BigDecimal _qtyBeliPcs = _qtyBeliCtn2.multiply(_isiPcs);
								_item.setTotQtyBeliPcs(_qtyBeliPcs);
							} catch (Exception e) {
								// TODO: handle exception
							}
							try {
								BigDecimal _isiCtn = _pemEnt.getStockEnt().getProductEnt().getIsiCtn();
								BigDecimal _isiPcs = _pemEnt.getStockEnt().getProductEnt().getIsiPcs();
								BigDecimal _qtyBeli = _pemEnt.getQtyBeliCtn();
								BigDecimal _qtyBeliPcs = _qtyBeli.multiply(_isiCtn).multiply(_isiPcs);
								BigDecimal _TotHargaBrutBeliPcsIdr = _pemEnt.getTotHargaBrutB_Idr().divide(_qtyBeliPcs,2,RoundingMode.HALF_UP);
								BigDecimal _TotHargaBrutBeliPcsVta = _pemEnt.getTotHargaBrutB_Vta().divide(_qtyBeliPcs,2,RoundingMode.HALF_UP);
								BigDecimal _TotHargaNettBeliPcsIdr = _pemEnt.getTotHargaNettB_Idr().divide(_qtyBeliPcs,2,RoundingMode.HALF_UP);
								BigDecimal _TotHargaNettBeliPcsVta = _pemEnt.getTotHargaNettB_Vta().divide(_qtyBeliPcs,2,RoundingMode.HALF_UP);
								BigDecimal _HargaJualPcs = _pemEnt.getHargaJ_CtnNew().divide(_isiCtn,2,RoundingMode.HALF_UP).divide(_isiPcs,2,RoundingMode.HALF_UP);
								
								BigDecimal _kurs = _pemEnt.getTripEnt().getCurrencyIDR();
								BigDecimal _HargaJualPcsVta=_HargaJualPcs.divide(_kurs,2,RoundingMode.HALF_UP);
								BigDecimal _HargaJualCtnVta=_pemEnt.getHargaJ_CtnNew().divide(_kurs,2,RoundingMode.HALF_UP);
								
								
								
								_item.setTotHargaBrutBeliPcsIdr(_TotHargaBrutBeliPcsIdr);
								_item.setTotHargaBrutBeliPcsVta(_TotHargaBrutBeliPcsVta);
								_item.setTotHargaNettBeliPcsIdr(_TotHargaNettBeliPcsIdr);
								_item.setTotHargaNettBeliPcsVta(_TotHargaNettBeliPcsVta);
								
								_item.setHargaJualPcs(_HargaJualPcs);
								_item.setHargaJualPcsVta(_HargaJualPcsVta);
								_item.setHargaJualCtnVta(_HargaJualCtnVta);
							} catch (Exception e) {
								// TODO: handle exception
							}
							
							try {
								_item.setTotHargaBrutBeliIdr(_pemEnt.getTotHargaBrutB_Idr());
							} catch (Exception e) {
								// TODO: handle exception
							}
							
							try {
								_item.setTotHargaBrutBeliVta(_pemEnt.getTotHargaBrutB_Vta());
							} catch (Exception e) {
								// TODO: handle exception
							}
							try {
								_item.setHargaBeliCtnVta(_pemEnt.getHargaBeliCtnVta());
							} catch (Exception e) {
								// TODO: handle exception
							}
							try{
								
								BigDecimal _oneHundred = new BigDecimal(100);
								BigDecimal _totCost = _ent.getTotCost();
								BigDecimal _totDisc = _ent.getTotDisc();
								BigDecimal _totCostHelp = _totCost.divide(_oneHundred,100, RoundingMode.HALF_UP);
								BigDecimal _totDiscHelp = _totDisc.divide(_oneHundred,100, RoundingMode.HALF_UP);
								
								BigDecimal _totHargaBrutoBeliVta = _pemEnt.getTotHargaBrutB_Vta();
								BigDecimal _totHargaNettBeliVtaBeforeCost = _totHargaBrutoBeliVta.subtract(_totHargaBrutoBeliVta.multiply(_totDiscHelp));
								BigDecimal _totHargaNettBeliVta = _totHargaNettBeliVtaBeforeCost.add(_totHargaNettBeliVtaBeforeCost.multiply(_totCostHelp));
								
								BigDecimal _totHargaBrutoBeliIdr = _pemEnt.getTotHargaBrutB_Idr();
								BigDecimal _totHargaNettBeliIdrBeforeCost = _totHargaBrutoBeliIdr.subtract(_totHargaBrutoBeliIdr.multiply(_totDiscHelp));
								BigDecimal _totHargaNettBeliIdr = _totHargaNettBeliIdrBeforeCost.add(_totHargaNettBeliIdrBeforeCost.multiply(_totCostHelp));
								
								_item.setTotHargaNettBeliVta(_totHargaNettBeliVta);
								_item.setTotHargaNettBeliIdr(_totHargaNettBeliIdr);
								
								BigDecimal _hargaCtnVta = _pemEnt.getHargaBeliCtnVta();
								BigDecimal _hargaCtnVtaNettoBeforeCost = _hargaCtnVta.subtract((_hargaCtnVta.multiply(_totDiscHelp)));
								BigDecimal _hargaCtnVtaNetto = _hargaCtnVtaNettoBeforeCost.add(_hargaCtnVtaNettoBeforeCost.multiply(_totCostHelp));
								_item.setHargaBeliCtnVtaNetto(_hargaCtnVtaNetto);
								
								BigDecimal _isiCtn = _pemEnt.getStockEnt().getProductEnt().getIsiCtn();
								BigDecimal _isiPcs = _pemEnt.getStockEnt().getProductEnt().getIsiPcs();
								BigDecimal _hargaBeliPcsVtaNetto = (_hargaCtnVtaNetto.divide(_isiCtn,100, RoundingMode.HALF_UP)).divide(_isiPcs,100, RoundingMode.HALF_UP);
								_item.setHargaBeliPcsVtaNetto(_hargaBeliPcsVtaNetto);
								
								BigDecimal _kurs = _ent.getCurrencyIDR();
								BigDecimal _hargaBeliPcsIdrNetto = _hargaBeliPcsVtaNetto.multiply(_kurs);
								_item.setHargaBeliPcsIdrNetto(_hargaBeliPcsIdrNetto);
								
								BigDecimal _HargaJualPcs = _pemEnt.getHargaJ_CtnNew().divide(_isiCtn,2,RoundingMode.HALF_UP).divide(_isiPcs,2,RoundingMode.HALF_UP);
								BigDecimal _profitIDR = _HargaJualPcs.subtract(_hargaBeliPcsIdrNetto);
								_item.setProfitIdr(_profitIDR);
							}
						 catch (Exception e) {
							// TODO: handle exception
						}
									
							
							/*try {
								_item.setTotHargaNettBeliIdr(_pemEnt.getTotHargaNettB_Idr());
							} catch (Exception e) {
								// TODO: handle exception
							}
							try {
								_item.setTotHargaNettBeliVta(_pemEnt.getTotHargaNettB_Vta());
							} catch (Exception e) {
								// TODO: handle exception
							}*/
							 
							
							try {
								_item.setHargaJualCtn(_pemEnt.getHargaJ_CtnNew());
							} catch (Exception e) {
								// TODO: handle exception
							}
							try {
								_item.setCost(_pemEnt.getTripEnt().getTotCost());
							} catch (Exception e) {
								// TODO: handle exception
							}
							try {
								_item.setDisc(_pemEnt.getTripEnt().getTotDisc());
							} catch (Exception e) {
								// TODO: handle exception
							}
							try {
								_item.setVendVta(_pemEnt.getTripEnt().getVendorEnt().getVta());
							} catch (Exception e) {
								// TODO: handle exception
							}
							try {
								_item.setTripKurs(_ent.getCurrencyIDR());
							
							} catch (Exception e) {
								// TODO: handle exception
							}
							/*DetailPembelianEntity _detPembelian = new DetPembelianPersistence().getByTripEnt(_ent);
							if(null != _detPembelian){
								_item.setTotHargaNettBeliVta(_detPembelian.getTotHargaNettB_Vta());
								_item.setTotHargaNettBeliIdr(_detPembelian.getTotHargaNettB_Idr());
							}*/
							_items.add(_item);
							_perP = _perP+1;
							if (_perP == 10){
								String _page = "";
								
								_p = _p+1;
								  _page = "page"+_p;
							_wew.put(_page, (ArrayList<DetPembelianItem>) _items);
							_items = new ArrayList<DetPembelianItem>();
							session.setAttribute("paggingDataDetPembelian", _wew);
							_perP = 0;
							
							}
							//System.out.println(countL+" --- "+countk);
							if(countL<countk){
								String _page = "";
								_p = _p+1;
								_page = "page"+_p;
								_wew.put(_page, (ArrayList<DetPembelianItem>) _items);
								_items = new ArrayList<DetPembelianItem>();
								session.setAttribute("paggingDataDetPembelian", _wew);
							
							}
						}
						
					}
				}
				
			}
			
		}
		
	}
}
