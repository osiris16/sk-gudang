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
import org.radot.hibernate.entities.DetailPenjualanEntity;
import org.radot.hibernate.entities.PenjualanEntity;
import org.radot.hibernate.persistences.DetPenjualanPersistence;
import org.radot.hibernate.persistences.PenjualanPersistence;
import org.radot.json.beans.DetPenjualanItem;
import org.radot.json.beans.DetPenjualanResult;
import org.radot.json.beans.DetPenjualanSelectParam;
import org.radot.json.servlet.JsonServletHandler;

public class JsonDetPenjualanHandler extends JsonServletHandler<DetPenjualanSelectParam, DetPenjualanResult> {

	public JsonDetPenjualanHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
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
		DetPenjualanItem _item;
		final List<DetPenjualanItem> _items = new ArrayList<DetPenjualanItem>();
//		int limit = 10;
//		long offset = Integer.parseInt(this.param.getOffset());
		int page = 0;
		
		ArrayList<Criterion> _arrayCrit = new ArrayList<Criterion>();
		 String _paramBy = this.param.getByValueOrder();
		 String _paramQuery = "%"+this.param.getQueryDataOrder()+"%";
		 String _fakturCheck = this.param.getFakturChek();
		 if(null == _fakturCheck){
			 _fakturCheck = "";
		 }
		Criterion _filterByOrderNumb = Restrictions.ilike("orderNumb", _paramQuery);
		Criterion _filterByFakturNumb = Restrictions.ilike("fakturNumb", _paramQuery);
		
		if(_paramBy.equalsIgnoreCase("orderNumb")){
			_arrayCrit.add(_filterByOrderNumb);
		}
		
		if(_paramBy.equalsIgnoreCase("fakturNumb")){
			_arrayCrit.add(_filterByFakturNumb);
		
		}
//		final List<DetailPembelianEntity> _entst = BaseEntity.listDataOffset(DetailPembelianEntity.class, _arrayCrit,null,  null, null);
//		final List<TripEntity> _entst = BaseEntity.list(TripEntity.class, null, null);
		PenjualanPersistence _w =  new PenjualanPersistence();
		
		
		List<Order> _listOrder = new ArrayList<Order>();
		
		Order _orderByID = Order.desc("orderNumb");
		_listOrder.add(_orderByID);
		final List<PenjualanEntity> _entList = BaseEntity.listDataOffset(PenjualanEntity.class, _arrayCrit,_listOrder,  null, null);
//		final List<VendorEntity> _entList = _w.getByCity(this.param.getCity());
		Long _lPage = 0l;
		Long _sizePage = 0l;
		String _stopLoop = "";
		if (null != _entList && !_entList.isEmpty()) {
			for (final PenjualanEntity _ent: _entList) {
				if (_items.size() == 50){
					//System.out.println("masuk sini");
					break;
				}
				
				final List<DetailPenjualanEntity> _penj = new DetPenjualanPersistence().getByPenjualanList(_ent);
				if(null != _penj){
				
					for(final DetailPenjualanEntity _penjEnt : _penj){
						_item = new DetPenjualanItem();
						_item.setId(_penjEnt.getRecId());
						try {
							_item.setOrderNumb(_ent.getOrderNumb());
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							_item.setFakturNumb(_ent.getFakturNumb());
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							_item.setStockId(_penjEnt.getStockEnt().getRecId());
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							
							BigDecimal _sisaStokEdit = _penjEnt.getStockEnt().getStokCtn().add(_penjEnt.getTotQtyJualCtn());
							_item.setSisaStokEdit(_sisaStokEdit);
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							_item.setPenjualanId(_penjEnt.getPenjualanEnt().getRecId());
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							_item.setProdId(_penjEnt.getStockEnt().getProductEnt().getRecId());
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						try {
							_item.setProductCode(_penjEnt.getStockEnt().getProductEnt().getCode());
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						try {
							_item.setProductName(_penjEnt.getStockEnt().getProductEnt().getName());
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						try {
							_item.setCustomerId(_penjEnt.getPenjualanEnt().getCustomerEnt().getRecId());
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						try {
							_item.setCustomerCode(_penjEnt.getPenjualanEnt().getCustomerEnt().getCode());
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						try {
							_item.setCustomerName(_penjEnt.getPenjualanEnt().getCustomerEnt().getName());
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							_item.setCustomerAddr(_penjEnt.getPenjualanEnt().getCustomerEnt().getAddress1());
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							_item.setCustomerCity(_penjEnt.getPenjualanEnt().getCustomerEnt().getCity());
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							_item.setSalesmanId(_penjEnt.getPenjualanEnt().getSalesmanEnt().getRecId());
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						try {
							_item.setSalesmanCode(_penjEnt.getPenjualanEnt().getSalesmanEnt().getCode());
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						try {
							_item.setSalesmanName(_penjEnt.getPenjualanEnt().getSalesmanEnt().getName());
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						try {
							_item.setTotQtyJualCtn(_penjEnt.getTotQtyJualCtn());
							_item.setStokIsiCtnDeptStore(_penjEnt.getStockEnt().getStokCtn_grosir());
							_item.setStokIsiCtnRetail(_penjEnt.getStockEnt().getStokCtnRetail());
						} catch (Exception e) {
								e.printStackTrace();
						}
						try {
							BigDecimal _cepe = new BigDecimal(100);
							BigDecimal _bruto = _penjEnt.getTotJualBrutoIdr();
							BigDecimal _discPers = _penjEnt.getDiscPersen().divide(_cepe,100,RoundingMode.HALF_UP);
							BigDecimal _totDisc = _bruto.multiply(_discPers);
							BigDecimal _brutoAfterDisc = _bruto.subtract(_totDisc);
							
							BigDecimal _ppnPers = _ent.getPpn().divide(_cepe,100,RoundingMode.HALF_UP);
							BigDecimal _totPpn = _brutoAfterDisc.multiply(_ppnPers);
							BigDecimal _totJualNettoIdr = _brutoAfterDisc.add(_totPpn);
							
							
							
						BigDecimal _QtyBeliCtn = _penjEnt.getTotQtyJualCtn();
						BigDecimal _HargaJualNettoCtn = _totJualNettoIdr.divide(_QtyBeliCtn,2,RoundingMode.HALF_UP);
						
						_item.setHargaJualNettoCtn(_HargaJualNettoCtn); //harga jual Ctn
						_item.setHargaJualCtn(_penjEnt.getHjCtn()); //harga jual Ctn
						_item.setHargaJualCtnStd(_penjEnt.getStockEnt().getProductEnt().getHJstdCtn());
						} catch (Exception e) {
						}
						try {
						BigDecimal _totJualBrutoIdr = _penjEnt.getTotJualBrutoIdr();
						BigDecimal _QtyBeliCtn = _penjEnt.getTotQtyJualCtn();
						BigDecimal _HargaJualBrutoCtn = _totJualBrutoIdr.divide(_QtyBeliCtn,2,RoundingMode.HALF_UP);
									
						_item.setHargaJualBrutoCtn(_HargaJualBrutoCtn); //harga jual Ctn
							
						} catch (Exception e) {
						}
						
						try {
						BigDecimal _cepe = new BigDecimal(100);
						BigDecimal _ppn = _penjEnt.getPenjualanEnt().getPpn();
						BigDecimal _ppnA = _ppn.divide(_cepe,100, RoundingMode.HALF_UP);
						
						BigDecimal _hargaJualCtn = _penjEnt.getHjCtn();
						BigDecimal _isiPcs = _penjEnt.getStockEnt().getProductEnt().getIsiPcs();
						BigDecimal _isiCtn = _penjEnt.getStockEnt().getProductEnt().getIsiCtn();
						BigDecimal _hargaJualCtn2 = _hargaJualCtn.divide(_isiCtn,2, RoundingMode.HALF_UP);
						BigDecimal _hargaJualPcs = _hargaJualCtn2.divide(_isiPcs,2, RoundingMode.HALF_UP);
						BigDecimal _discPersen = _penjEnt.getDiscPersen();
						
						BigDecimal _disc = _discPersen.divide(_cepe,100, RoundingMode.HALF_UP);
						BigDecimal _totDisc = _hargaJualPcs.multiply(_disc);
						BigDecimal _hargaJualPcsDisc = _hargaJualPcs.subtract(_totDisc); //harga jual pcs diskon belum ppn
						BigDecimal _hargaJualPcsNet = _hargaJualPcsDisc.multiply(_ppnA);
						BigDecimal _hargaJualPcsNetto = _hargaJualPcsDisc.add(_hargaJualPcsNet);
						
						_item.setHargaJualPcs(_hargaJualPcs); //harga pcs bruto
						_item.setHargaJualPcsDisc(_hargaJualPcsDisc); // harga pcs potong disc
						_item.setHargaJualPcsNetto(_hargaJualPcsNetto); //harga pcs potong disc tambah ppn
						
						} catch (Exception e) {
						}
						
						try {
							
							BigDecimal _totJualCtn = _penjEnt.getTotQtyJualCtn();
							BigDecimal _isiPcs = _penjEnt.getStockEnt().getProductEnt().getIsiCtn().multiply(_penjEnt.getStockEnt().getProductEnt().getIsiPcs());
							BigDecimal _a = _totJualCtn.multiply(_isiPcs);
							if (_penjEnt.getTotQtyJualPcs()==null){
								_item.setTotQtyJualPcs(_a);
							}
							else{
							_item.setTotQtyJualPcs(_penjEnt.getTotQtyJualPcs());
							}
						
						} catch (Exception e) {
							// TODO: handle exception
						}
						try {
							_item.setDisc(_penjEnt.getDiscPersen()); // disc persen
						
						} catch (Exception e) {
							// TODO: handle exception
						}
						try {
							_item.setPpn(_penjEnt.getPenjualanEnt().getPpn()); // ppn persen
						
						} catch (Exception e) {
							// TODO: handle exception
						}
						try {
							_item.setTotJualBrutoIdr(_penjEnt.getTotJualBrutoIdr());
							
						} catch (Exception e) {
							// TODO: handle exception
						}
						try {
							BigDecimal _cepe = new BigDecimal(100);
							BigDecimal _bruto = _penjEnt.getTotJualBrutoIdr();
							BigDecimal _discPers = _penjEnt.getDiscPersen().divide(_cepe,100,RoundingMode.HALF_UP);
							BigDecimal _totDisc = _bruto.multiply(_discPers);
							BigDecimal _brutoAfterDisc = _bruto.subtract(_totDisc);
							
							BigDecimal _ppnPers = _ent.getPpn().divide(_cepe,100,RoundingMode.HALF_UP);
							BigDecimal _totPpn = _brutoAfterDisc.multiply(_ppnPers);
							BigDecimal _totJualNettoIdr = _brutoAfterDisc.add(_totPpn);
							
							_item.setTotJualNettoIdr(_totJualNettoIdr);
							
						} catch (Exception e) {
							// TODO: handle exception
						}
						
						try {
							BigDecimal _totJualBrutoIdr = _penjEnt.getTotJualBrutoIdr();
							BigDecimal _cepe = new BigDecimal(100);
							BigDecimal _disc = _penjEnt.getDiscPersen().divide(_cepe,100, RoundingMode.HALF_UP);
							BigDecimal _totDisc = _totJualBrutoIdr.multiply(_disc);
							BigDecimal _totJualNettoIdrBeforePpn = _totJualBrutoIdr.subtract(_totDisc);
							
							_item.setTotJualNettoIdrBeforePpn(_totJualNettoIdrBeforePpn);
							
						} catch (Exception e) {
							// TODO: handle exception
						}
						try {
							_item.setTotPenjualanFaktur(_penjEnt.getPenjualanEnt().getTotPenjualanNettIdr());
							
						} catch (Exception e) {
							// TODO: handle exception
						}
						
						try {
							_item.setIsiCtn(_penjEnt.getStockEnt().getProductEnt().getIsiCtn());
							
						} catch (Exception e) {
							// TODO: handle exception
						}
						try {
							_item.setIsiPcs(_penjEnt.getStockEnt().getProductEnt().getIsiPcs());
							
						} catch (Exception e) {
							// TODO: handle exception
						}
						try {
							_item.setKeterangan(_penjEnt.getKeterangan());
							
						} catch (Exception e) {
							// TODO: handle exception
						}
						
						
						_items.add(_item);
						if (_items.size() == 50 && _fakturCheck.equalsIgnoreCase("")){
							break;
						}
					}
					
				}
			}
			for (final PenjualanEntity _ent: _entList) {
				Number _num  = new DetPenjualanPersistence().getCountByDetPenj(_ent);
				_sizePage = _sizePage +_num.longValue();
				if(_sizePage >= 50 && _stopLoop.equalsIgnoreCase("")){
					_stopLoop = "stop";
					this.result.setItems(_items);
					Long kk = _sizePage%50;
					if(kk !=0){
						kk = _sizePage/50;
						kk = kk+1;
					}else{
						kk = _sizePage/50;
					}
					//System.out.println(kk);
					this.result.setPage(String.valueOf(kk));
				}
				//System.out.println("Hasil cari hh "+ _num.longValue() +"  Rec Id " +_ent.getRecId());
			}
			//System.out.println(_sizePage + " total Page");
		}
		if(_sizePage < 50 && _stopLoop.equalsIgnoreCase("")){
			this.result.setItems(_items);
			Long kk = _sizePage%50;
			if(kk !=0){
				kk = _sizePage/50;
				kk = kk+1;
			}else{
				kk = _sizePage/50;
			}
			//System.out.println(kk);
			this.result.setPage(String.valueOf(kk));
		}
		
		//System.out.println(_items.size() + " jumlah data itemss");
		_session.setAttribute("itemsDetPenjualan", _items);
		_session.setAttribute("offsetLast", _lPage);
		_session.setAttribute("totalData",  Integer.parseInt(String.valueOf(_sizePage/50)));
		_session.setAttribute("allData", Integer.parseInt(String.valueOf(_sizePage)));
		_session.setAttribute("orderList",  _listOrder);
		_session.setAttribute("arrayCrit", _arrayCrit);

		_session.setAttribute("dotPage", "True"); //Stop
		//System.out.println(_session.getAttribute("dotPage"));
		new Thread(new JsonDetPenjualanHandler.BackgroundDataLoader(_session)).start();
	}
	public static class BackgroundDataLoader implements Runnable{
		private String _totData;
		private HttpSession session;
		private Integer intPage;
		private Integer intData;
		private Integer allData;
		private Integer intCurrent;
		private ArrayList<DetPenjualanItem> _firstPage;
		private long lastOffset;
		private List<DetPenjualanItem> items;
		private List<Criterion> _critArray;
		private List<Order> _listOrder;
		
		@SuppressWarnings("unchecked")
		public  BackgroundDataLoader(final HttpSession _session) {
			//System.out.println("ss");
			this.session = _session;
			this.allData = (Integer) session.getAttribute("allData");
			this._critArray = (List<Criterion>) _session.getAttribute("arrayCrit");
			this._listOrder = (List<Order>) _session.getAttribute("orderList");
			//System.out.println("sss " + session.getAttribute("totalData"));
			this.intData = (Integer) session.getAttribute("totalData");
			this.intPage = this.intData/50;
			this._firstPage = (ArrayList<DetPenjualanItem>) session.getAttribute("pageData1");
			List<DetPenjualanItem> _items = new ArrayList<DetPenjualanItem>();
			_items = (List<DetPenjualanItem>) this.session.getAttribute("ItemsDetPenjualan");
			//System.out.println("sss " + this.intData);
			this.lastOffset = (Long) this.session.getAttribute("offsetLast");
		}
		@Override
		public void run() {
			//sTOP PAGING	
			//System.out.println("sss");
			String _chString= "";
			_chString = session.getAttribute("dotPage").toString();
			//System.out.println(_chString +"  s");
			if(_chString.equalsIgnoreCase("False")){
				System.out.println("STOP");
				Thread.currentThread().isInterrupted();
			}
			//sTOP PAGGING END
			List<DetPenjualanItem> _items = new ArrayList<DetPenjualanItem>();
			HashMap<String, ArrayList<DetPenjualanItem>> _wew = new HashMap<String, ArrayList<DetPenjualanItem>>();
			final List<PenjualanEntity> _entList = BaseEntity.listDataOffset(PenjualanEntity.class, this._critArray,this._listOrder,  null, null);
			int _p = -1;
			int _perP = 0;
			int _pp = 0;
			if (null != _entList && !_entList.isEmpty()) {
				for (final PenjualanEntity _ent: _entList) {
					final List<DetailPenjualanEntity> _penj = new DetPenjualanPersistence().getByPenjualanList(_ent);
					if(null != _penj){
						
						for(final DetailPenjualanEntity _penjEnt : _penj){
							//sTOP PAGGING
							//System.out.println("-----ss-------");
							_chString = session.getAttribute("dotPage").toString();
							if(_chString.equalsIgnoreCase("False")){
								//System.out.println("STOP");
								Thread.currentThread().isInterrupted();
								break;
							}
							//sTOP PAGGING
							DetPenjualanItem _item = new DetPenjualanItem();
							_item.setId(_penjEnt.getRecId());
							try {
								_item.setOrderNumb(_ent.getOrderNumb());
							} catch (Exception e) {
								e.printStackTrace();
							}
							try {
								_item.setFakturNumb(_ent.getFakturNumb());
							} catch (Exception e) {
								e.printStackTrace();
							}
							try {
								_item.setStockId(_penjEnt.getStockEnt().getRecId());
							} catch (Exception e) {
								e.printStackTrace();
							}
							try {
								
								BigDecimal _sisaStokEdit = _penjEnt.getStockEnt().getStokCtn().add(_penjEnt.getTotQtyJualCtn());
								_item.setSisaStokEdit(_sisaStokEdit);
							} catch (Exception e) {
								e.printStackTrace();
							}
							try {
								_item.setPenjualanId(_penjEnt.getPenjualanEnt().getRecId());
							} catch (Exception e) {
								e.printStackTrace();
							}
							try {
								_item.setProdId(_penjEnt.getStockEnt().getProductEnt().getRecId());
							} catch (Exception e) {
								e.printStackTrace();
							}
							try {
								_item.setProductCode(_penjEnt.getStockEnt().getProductEnt().getCode());
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								_item.setProductName(_penjEnt.getStockEnt().getProductEnt().getName());
							} catch (Exception e) {
								e.printStackTrace();
							}
							try {
								_item.setCustomerId(_penjEnt.getPenjualanEnt().getCustomerEnt().getRecId());
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								_item.setCustomerCode(_penjEnt.getPenjualanEnt().getCustomerEnt().getCode());
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								_item.setCustomerName(_penjEnt.getPenjualanEnt().getCustomerEnt().getName());
							} catch (Exception e) {
								e.printStackTrace();
							}
							try {
								_item.setCustomerAddr(_penjEnt.getPenjualanEnt().getCustomerEnt().getAddress1());
							} catch (Exception e) {
								e.printStackTrace();
							}
							try {
								_item.setCustomerCity(_penjEnt.getPenjualanEnt().getCustomerEnt().getCity());
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								_item.setSalesmanId(_penjEnt.getPenjualanEnt().getSalesmanEnt().getRecId());
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								_item.setSalesmanCode(_penjEnt.getPenjualanEnt().getSalesmanEnt().getCode());
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								_item.setSalesmanName(_penjEnt.getPenjualanEnt().getSalesmanEnt().getName());
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								
								BigDecimal _totJualCtn = _penjEnt.getTotQtyJualCtn();
								BigDecimal _isiPcs = _penjEnt.getStockEnt().getProductEnt().getIsiCtn().multiply(_penjEnt.getStockEnt().getProductEnt().getIsiPcs());
								BigDecimal _a = _totJualCtn.multiply(_isiPcs);
								if (_penjEnt.getTotQtyJualPcs()==null){
									_item.setTotQtyJualPcs(_a);
								}
								else{
								_item.setTotQtyJualPcs(_penjEnt.getTotQtyJualPcs());
								}
							
							} catch (Exception e) {
								// TODO: handle exception
							}
								
								
								try {
									BigDecimal _cepe = new BigDecimal(100);
									BigDecimal _ppn = _penjEnt.getPenjualanEnt().getPpn();
									BigDecimal _ppnA = _ppn.divide(_cepe,100, RoundingMode.HALF_UP);
									
									BigDecimal _hargaJualCtn = _penjEnt.getHjCtn();
									BigDecimal _isiPcs = _penjEnt.getStockEnt().getProductEnt().getIsiPcs();
									BigDecimal _isiCtn = _penjEnt.getStockEnt().getProductEnt().getIsiCtn();
									BigDecimal _hargaJualCtn2 = _hargaJualCtn.divide(_isiCtn,2, RoundingMode.HALF_UP);
									BigDecimal _hargaJualPcs = _hargaJualCtn2.divide(_isiPcs,2, RoundingMode.HALF_UP);
									BigDecimal _discPersen = _penjEnt.getDiscPersen();
									
									BigDecimal _disc = _discPersen.divide(_cepe,100, RoundingMode.HALF_UP);
									BigDecimal _totDisc = _hargaJualPcs.multiply(_disc);
									BigDecimal _hargaJualPcsDisc = _hargaJualPcs.subtract(_totDisc); //harga jual pcs diskon belum ppn
									BigDecimal _hargaJualPcsNet = _hargaJualPcsDisc.multiply(_ppnA);
									BigDecimal _hargaJualPcsNetto = _hargaJualPcsDisc.add(_hargaJualPcsNet);
									
									//BigDecimal _totQtyJualPcs = _penjEnt.getTotQtyJualCtn().multiply(_isiCtn).multiply(_isiPcs);
									
									
									_item.setHargaJualPcs(_hargaJualPcs); //harga pcs bruto
									_item.setHargaJualPcsDisc(_hargaJualPcsDisc); // harga pcs potong disc
									_item.setHargaJualPcsNetto(_hargaJualPcsNetto); //harga pcs potong disc tambah ppn
								
								} catch (Exception e) {
								}
								try {
									_item.setTotQtyJualPcs(_penjEnt.getTotQtyJualPcs());
								
								} catch (Exception e) {
									// TODO: handle exception
								}
							try {
								BigDecimal _cepe = new BigDecimal(100);
								BigDecimal _bruto = _penjEnt.getTotJualBrutoIdr();
								BigDecimal _discPers = _penjEnt.getDiscPersen().divide(_cepe,100,RoundingMode.HALF_UP);
								BigDecimal _totDisc = _bruto.multiply(_discPers);
								BigDecimal _brutoAfterDisc = _bruto.subtract(_totDisc);
								
								BigDecimal _ppnPers = _ent.getPpn().divide(_cepe,100,RoundingMode.HALF_UP);
								BigDecimal _totPpn = _brutoAfterDisc.multiply(_ppnPers);
								BigDecimal _totJualNettoIdr = _brutoAfterDisc.add(_totPpn);
								
								BigDecimal _QtyBeliCtn = _penjEnt.getTotQtyJualCtn();
								BigDecimal _HargaJualNettoCtn = _totJualNettoIdr.divide(_QtyBeliCtn,2,RoundingMode.HALF_UP);
									
								_item.setHargaJualNettoCtn(_HargaJualNettoCtn); //harga jual Ctn
								_item.setHargaJualCtn(_penjEnt.getHjCtn()); //harga jual Ctn
								_item.setHargaJualCtnStd(_penjEnt.getStockEnt().getProductEnt().getHJstdCtn());
								} catch (Exception e) {
								}
								
							try {
								BigDecimal _totJualBrutoIdr = _penjEnt.getTotJualBrutoIdr();
								BigDecimal _QtyBeliCtn = _penjEnt.getTotQtyJualCtn();
								BigDecimal _HargaJualBrutoCtn = _totJualBrutoIdr.divide(_QtyBeliCtn,2,RoundingMode.HALF_UP);
										
								_item.setHargaJualBrutoCtn(_HargaJualBrutoCtn); //harga jual Ctn
								
								} catch (Exception e) {
								}	
								
							try {
								_item.setDisc(_penjEnt.getDiscPersen());
							
							} catch (Exception e) {
								// TODO: handle exception
							}
							try {
								_item.setPpn(_penjEnt.getPenjualanEnt().getPpn());
							
							} catch (Exception e) {
								// TODO: handle exception
							}
							try {
								_item.setTotJualBrutoIdr(_penjEnt.getTotJualBrutoIdr());
								
							} catch (Exception e) {
								// TODO: handle exception
							}
							
							try {
								BigDecimal _cepe = new BigDecimal(100);
								BigDecimal _bruto = _penjEnt.getTotJualBrutoIdr();
								BigDecimal _discPers = _penjEnt.getDiscPersen().divide(_cepe,100,RoundingMode.HALF_UP);
								BigDecimal _totDisc = _bruto.multiply(_discPers);
								BigDecimal _brutoAfterDisc = _bruto.subtract(_totDisc);
								
								BigDecimal _ppnPers = _ent.getPpn().divide(_cepe,100,RoundingMode.HALF_UP);
								BigDecimal _totPpn = _brutoAfterDisc.multiply(_ppnPers);
								BigDecimal _totJualNettoIdr = _brutoAfterDisc.add(_totPpn);
								
								_item.setTotJualNettoIdr(_totJualNettoIdr);
								//_item.setTotJualNettoIdr(_penjEnt.getTotJualNettoIdr());
								
							} catch (Exception e) {
								// TODO: handle exception
							}
							try {
								BigDecimal _totJualBrutoIdr = _penjEnt.getTotJualBrutoIdr();
								BigDecimal _cepe = new BigDecimal(100);
								BigDecimal _disc = _penjEnt.getDiscPersen().divide(_cepe,100, RoundingMode.HALF_UP);
								BigDecimal _totDisc = _totJualBrutoIdr.multiply(_disc);
								BigDecimal _totJualNettoIdrBeforePpn = _totJualBrutoIdr.subtract(_totDisc);
								
								_item.setTotJualNettoIdrBeforePpn(_totJualNettoIdrBeforePpn);
								
							} catch (Exception e) {
								// TODO: handle exception
							}
							try {
								_item.setTotPenjualanFaktur(_penjEnt.getPenjualanEnt().getTotPenjualanNettIdr());
								
							} catch (Exception e) {
								// TODO: handle exception
							}
							try {
								_item.setIsiCtn(_penjEnt.getStockEnt().getProductEnt().getIsiCtn());
								
							} catch (Exception e) {
								// TODO: handle exception
							}
							try {
								_item.setIsiPcs(_penjEnt.getStockEnt().getProductEnt().getIsiPcs());
								
							} catch (Exception e) {
								// TODO: handle exception
							}
							
							try {
								if(null != _penjEnt.getKeterangan()){
								_item.setKeterangan(_penjEnt.getKeterangan());
								}
							} catch (Exception e) {
								// TODO: handle exception
							}
							
							_items.add(_item);
							_perP = _perP+1;
							_pp = _pp+1;
							if (_perP == 50 || _pp == this.allData){
								String _page = "";
								
								_p = _p+1;
								  _page = "page"+_p;
							_wew.put(_page, (ArrayList<DetPenjualanItem>) _items);
							_items = new ArrayList<DetPenjualanItem>();
							session.setAttribute("paggingDataDetPenjualan", _wew);
							_perP = 0;
							
							}
						}
						
					}
				}
				
			}
			
		}
		
	}
}



