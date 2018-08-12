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
import org.radot.hibernate.entities.ReturPenjualanEntity;
import org.radot.hibernate.persistences.ReturPenjualanPersistence;
import org.radot.json.beans.ReturPenjualanItem;
import org.radot.json.beans.ReturPenjualanResult;
import org.radot.json.beans.ReturPenjualanSelectParam;
import org.radot.json.servlet.JsonServletHandler;

public class JsonReturPenjualanHandler extends JsonServletHandler<ReturPenjualanSelectParam, ReturPenjualanResult> {

	public JsonReturPenjualanHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
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
		ReturPenjualanItem _item;
		final List<ReturPenjualanItem> _items = new ArrayList<ReturPenjualanItem>();
//		int limit = 10;
//		long offset = Integer.parseInt(this.param.getOffset());
		int page = 0;
		
		/*search*/
		ArrayList<Criterion> _arrayCrit = new ArrayList<Criterion>();
		String _paramBy = this.param.getByValueRetur();
		String _paramQuery = "%"+this.param.getQueryDataRetur()+"%";
		
		
		Criterion _filterByNoRetur = Restrictions.ilike("noRetur", _paramQuery);
		
		
		if (_paramBy !=null){
		
		if(_paramBy.equalsIgnoreCase("noRetur")){
			_arrayCrit.add(_filterByNoRetur);
		
		}
		}
		
		ReturPenjualanPersistence _w =  new ReturPenjualanPersistence();
		
		List<Order> _listOrder = new ArrayList<Order>();
		//final List<VendorEntity> _entst = BaseEntity.listDataOffset(VendorEntity.class, _arrayCrit,null,  null, null);
		Number _count = new ReturPenjualanPersistence().getCountByReturPenjualan(_paramBy, _paramQuery);
		Order _orderByID = Order.desc("recId");
		_listOrder.add(_orderByID);
		final List<ReturPenjualanEntity> _entList = BaseEntity.listDataOffset(ReturPenjualanEntity.class, _arrayCrit,_listOrder,  10, 1l);
//		final List<VendorEntity> _entList = _w.getByCity(this.param.getCity());
		Long _lPage = 0l;
		if (null != _entList && !_entList.isEmpty()) {
			for (final ReturPenjualanEntity _ent: _entList) {
				_item = new ReturPenjualanItem();
				
				if(_ent != null){
					_item.setId(_ent.getRecId());
					try {
						_item.setNoRetur(_ent.getNoRetur());
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					try {
						_item.setDateRetur(_ent.getDateRetur());
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					try {
						_item.setOrderNumb(_ent.getDetPenjualanEnt().getPenjualanEnt().getOrderNumb());
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					try {
						_item.setAlasanRetur(_ent.getKeterangan());
					} catch (Exception e) {
						e.printStackTrace();
					}
					_item.setProductCode(_ent.getDetPenjualanEnt().getStockEnt().getProductEnt().getCode());
					try {
						_item.setProductName(_ent.getDetPenjualanEnt().getStockEnt().getProductEnt().getName());
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						_item.setCustomerName(_ent.getDetPenjualanEnt().getPenjualanEnt().getCustomerEnt().getName());
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						_item.setSalesmanCode(_ent.getDetPenjualanEnt().getPenjualanEnt().getSalesmanEnt().getCode());
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						_item.setTotQtyReturCtn(_ent.getReturQtyCtn());
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						_item.setNilaiNettoRetur(_ent.getTotNettoReturIdr());
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						_item.setHargaJualCtnBruto(_ent.getHargaBrutoCtn());
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						_item.setPenerimaRetur(_ent.getPenerima());
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						_item.setAlasanRetur(_ent.getKeterangan());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				_items.add(_item);
				_lPage = _ent.getRecId();
			}
			
		}
		
		Long _modulo = _count.longValue()%10;
		Long _totalPage = _count.longValue()/10;
		if(_modulo != 0){
			_totalPage = _totalPage + 1;
		}
		_session.setAttribute("itemsReturPenjualan", _items);
		_session.setAttribute("offsetLast", _lPage);
		_session.setAttribute("totalData", Integer.parseInt(_count.toString()));
		_session.setAttribute("orderList",  _listOrder);
		_session.setAttribute("arrayCrit", _arrayCrit);
		//System.out.println(_count.longValue()+"  s");
		
		
		this.result.setPage(_totalPage.toString());
		this.result.setItems(_items);
		_session.setAttribute("dotPage", "True"); //Stop
		new Thread(new JsonReturPenjualanHandler.BackgroundDataLoader(_session)).start();

	}
	public static class BackgroundDataLoader implements Runnable{
		private String _totData;
		private HttpSession session;
		private Integer intPage;
		private Integer intData;
		private Integer intCurrent;
		private long lastOffset;
		private List<ReturPenjualanItem> items;
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
			
			List<ReturPenjualanItem> _items = new ArrayList<ReturPenjualanItem>();
			_items = (List<ReturPenjualanItem>) this.session.getAttribute("itemsReturPenjualan");
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
			HashMap<String, ArrayList<ReturPenjualanItem>> _wew = new HashMap<String, ArrayList<ReturPenjualanItem>>();
				Long _totData = Long.valueOf(this.intData);
				List<ReturPenjualanItem> _items = new ArrayList<ReturPenjualanItem>();
				List<ReturPenjualanEntity> _entList = null;
				int _indata = (int) (_totData%10);
				
				try {
					_entList = BaseEntity.listDataOffset(ReturPenjualanEntity.class, this._critArray,this._listOrder,  null, 1l);
				} catch (Exception e) {
					// TODO: handle exception
				}
				if (null != _entList && !_entList.isEmpty()) {
					ReturPenjualanItem _item;
					int i = 0;
					int _p = -1;
					String _chekLast = "";
					for (final ReturPenjualanEntity _ent: _entList) {
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
						_item = new ReturPenjualanItem();
						if(_ent != null){
							_item.setId(_ent.getRecId());
							try {
								_item.setNoRetur(_ent.getNoRetur());
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								_item.setDateRetur(_ent.getDateRetur());
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								_item.setOrderNumb(_ent.getDetPenjualanEnt().getPenjualanEnt().getOrderNumb());
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								_item.setAlasanRetur(_ent.getKeterangan());
							} catch (Exception e) {
								e.printStackTrace();
							}
							_item.setProductCode(_ent.getDetPenjualanEnt().getStockEnt().getProductEnt().getCode());
							try {
								_item.setProductName(_ent.getDetPenjualanEnt().getStockEnt().getProductEnt().getName());
								
							} catch (Exception e) {
								e.printStackTrace();
							}
							try {
								_item.setCustomerName(_ent.getDetPenjualanEnt().getPenjualanEnt().getCustomerEnt().getName());
								
							} catch (Exception e) {
								e.printStackTrace();
							}
							try {
								_item.setSalesmanCode(_ent.getDetPenjualanEnt().getPenjualanEnt().getSalesmanEnt().getCode());
								
							} catch (Exception e) {
								e.printStackTrace();
							}
							try {
								_item.setTotQtyReturCtn(_ent.getReturQtyCtn());
								
							} catch (Exception e) {
								e.printStackTrace();
							}
							try {
								_item.setNilaiNettoRetur(_ent.getTotNettoReturIdr());
								
							} catch (Exception e) {
								e.printStackTrace();
							}
							try {
								_item.setHargaJualCtnBruto(_ent.getHargaBrutoCtn());
								
							} catch (Exception e) {
								e.printStackTrace();
							}
							try {
								_item.setPenerimaRetur(_ent.getPenerima());
							} catch (Exception e) {
								e.printStackTrace();
							}
							try {
								_item.setAlasanRetur(_ent.getKeterangan());
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							
							}
							
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
						_wew.put(_page, (ArrayList<ReturPenjualanItem>) _items);
						_items = new ArrayList<ReturPenjualanItem>();
						session.setAttribute("paggingDataReturPenjualan", _wew);
						}
						if(_chekLast.equalsIgnoreCase("last") && _indata != 0 && _indata == i){
							_p = _p+1;
							i = 0;
							String _page = "page"+_p;
							//System.out.println(_page);
							if(this.intPage - _p == 2){
								_chekLast = "last";
							}
						_wew.put(_page, (ArrayList<ReturPenjualanItem>) _items);
						_items = new ArrayList<ReturPenjualanItem>();
						session.setAttribute("paggingDataReturPenjualan", _wew);
						}
					}
				}
		}
	}
}