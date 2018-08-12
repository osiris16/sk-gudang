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
import org.radot.hibernate.entities.StockEntity;
import org.radot.hibernate.entities.VendorEntity;
import org.radot.hibernate.persistences.StockPersistence;
import org.radot.json.beans.DetPenjualanItem;
import org.radot.json.beans.DetPenjualanResult;
import org.radot.json.beans.HistorySelectParam;
import org.radot.json.beans.VendorItem;
import org.radot.json.servlet.JsonServletHandler;

public class JsonHistoryDetPenjualanHandler extends JsonServletHandler<HistorySelectParam, DetPenjualanResult> {

	public JsonHistoryDetPenjualanHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
		
		DetPenjualanItem _item;
		final List<DetPenjualanItem> _items = new ArrayList<DetPenjualanItem>();
//		int limit = 10;
//		long offset = Integer.parseInt(this.param.getOffset());
		int page = 0;
		
		/*search*/
		ArrayList<Criterion> _arrayCrit = new ArrayList<Criterion>();
		String _paramBy = this.param.getByValue();
		String _paramQuery = "%"+this.param.getQueryData()+"%";
		
		String _action = this.param.getByValue(); //contoh
		String _cat = this.param.getByValue(); //contoh
		
		
		
		
		StockEntity _stockEnt = new StockPersistence().getByRecId(Long.valueOf(this.param.getByValue()));
		Criterion _filterAct = Restrictions.eq("stockEnt", _stockEnt);
		_arrayCrit.add(_filterAct);
		final HttpSession _session = this.request.getSession();
		List<Order> _listOrder = new ArrayList<Order>();
		Order _orderByID = Order.desc("recId");
		_listOrder.add(_orderByID);
		final List<DetailPenjualanEntity> _entList = BaseEntity.listDataOffset(DetailPenjualanEntity.class, _arrayCrit,_listOrder,  50, 1l);
		Long _lPage = 0l;
		if (null != _entList && !_entList.isEmpty()) {
			for (final DetailPenjualanEntity _ent: _entList) {
				_item = new DetPenjualanItem();
				
				BigDecimal _isiCtn = _ent.getStockEnt().getProductEnt().getIsiCtn();
				BigDecimal _isiPCS = _ent.getStockEnt().getProductEnt().getIsiPcs();
				BigDecimal _totQtyJualCtn = _ent.getTotQtyJualCtn();
				BigDecimal _hargaCtn = _ent.getHjCtn();
				BigDecimal _hargajualCtn2 = _hargaCtn.divide(_isiCtn, 2, RoundingMode.HALF_UP).divide(_isiPCS, 2, RoundingMode.HALF_UP);
				try {
					_item.setOrderNumb(_ent.getPenjualanEnt().getOrderNumb());
				}catch (Exception e) {
				e.printStackTrace();
				}
					
				try {
					_item.setDatePenjualan(_ent.getPenjualanEnt().getOrderDate().getTime());
				}catch (Exception e) {
				e.printStackTrace();
				}
				
				try {
					if(null != _ent.getPenjualanEnt().getOrderDateKirim()){
					_item.setDatePenjualanKirim(_ent.getPenjualanEnt().getOrderDateKirim().getTime());
				}
				}catch (Exception e) {
					e.printStackTrace();
					
				}
				try {
				if(null != _ent.getPenjualanEnt().getCustomerEnt()){
					_item.setCustomerName(_ent.getPenjualanEnt().getCustomerEnt().getName());
				}
				}catch (Exception e) {
				e.printStackTrace();
				}
				_item.setHargaJualPcs(_hargajualCtn2);
				_item.setHargaJualCtn(_hargaCtn);
				
				_item.setTotQtyJualCtn(_totQtyJualCtn);
				
				_items.add(_item);
				_lPage = _ent.getRecId();
			}
			
		}
		this.result.setItems(_items);
//		new Thread(new JsonHistoryHandler.BackgroundDataLoader(_session)).start();

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
			if(this.intData%50 != 0){
				this.intPage = this.intData/50+1;
			}else{
				this.intPage = this.intData/50;
			}
			
			List<VendorItem> _items = new ArrayList<VendorItem>();
			_items = (List<VendorItem>) this.session.getAttribute("itemsVendor");
			//System.out.println("sss " + this.intData);
			this.lastOffset = (Long) this.session.getAttribute("offsetLast");
			
		}
		
		@Override
		public void run() {
			
			HashMap<String, ArrayList<VendorItem>> _wew = new HashMap<String, ArrayList<VendorItem>>();
				Long _totData = Long.valueOf(this.intData);
				List<VendorItem> _items = new ArrayList<VendorItem>();
				List<VendorEntity> _entList = null;
				int _indata = (int) (_totData%50);
				
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
						if(i == 50 ){
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