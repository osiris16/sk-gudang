package org.radot.json.handlers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.time.DateUtils;
import org.apache.taglibs.standard.tag.common.fmt.FormatDateSupport;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.radot.hibernate.entities.BaseEntity;
import org.radot.hibernate.entities.DetailPembelianEntity;
import org.radot.hibernate.entities.HistoryEntity;
import org.radot.hibernate.entities.StockEntity;
import org.radot.hibernate.entities.TripEntity;
import org.radot.hibernate.entities.VendorEntity;
import org.radot.hibernate.persistences.StockPersistence;
import org.radot.hibernate.persistences.TripPersistence;
import org.radot.hibernate.persistences.VendorPersistence;
import org.radot.json.beans.DetPembelianItem;
import org.radot.json.beans.DetPembelianResult;
import org.radot.json.beans.HistoryItem;
import org.radot.json.beans.HistoryResult;
import org.radot.json.beans.HistorySelectParam;
import org.radot.json.beans.VendorItem;
import org.radot.json.beans.VendorResult;
import org.radot.json.beans.VendorSelectParam;
import org.radot.json.servlet.JsonServletHandler;

public class JsonHistoryDetPembelianHandler extends JsonServletHandler<HistorySelectParam, DetPembelianResult> {

	public JsonHistoryDetPembelianHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
		
		DetPembelianItem _item;
		final List<DetPembelianItem> _items = new ArrayList<DetPembelianItem>();
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
		Criterion _filterAct = Restrictions.eq("StockEnt", _stockEnt);
		_arrayCrit.add(_filterAct);
		final HttpSession _session = this.request.getSession();
		List<Order> _listOrder = new ArrayList<Order>();
		Order _orderByID = Order.desc("recId");
		_listOrder.add(_orderByID);
		final List<DetailPembelianEntity> _entList = BaseEntity.listDataOffset(DetailPembelianEntity.class, _arrayCrit,_listOrder,  1000, 1l);
		Long _lPage = 0l;
		if (null != _entList && !_entList.isEmpty()) {
			for (final DetailPembelianEntity _ent: _entList) {
				_item = new DetPembelianItem();
				
				BigDecimal _isiCtn = _ent.getStockEnt().getProductEnt().getIsiCtn();
				BigDecimal _isiPCS = _ent.getStockEnt().getProductEnt().getIsiPcs();
				BigDecimal _totQtyBeliPcs = _ent.getQtyBeliCtn().multiply(_isiCtn).multiply(_isiPCS);
				try {
					_item.setTripNumber(_ent.getTripEnt().getTrip_numb());
				}catch (Exception e) {
				e.printStackTrace();
				}
				try {
					_item.setTripDate(_ent.getTripEnt().getTrip_date());	
				}catch (Exception e) {
				e.printStackTrace();
				}
				try {
					_item.setTotQtyBeliCtn(_ent.getQtyBeliCtn());
				}catch (Exception e) {
				e.printStackTrace();
				}
				try {
					_item.setTotQtyBeliPcs(_totQtyBeliPcs);	
				}catch (Exception e) {
				e.printStackTrace();
				}
				try {
					_item.setVendVta(_ent.getTripEnt().getVendorEnt().getVta());
				}catch (Exception e) {
				e.printStackTrace();
				}
				try {
					_item.setTotHargaNettBeliIdr(_ent.getTotHargaNettB_Vta().divide(_isiCtn, 2, RoundingMode.HALF_UP).divide(_isiPCS,2 , RoundingMode.HALF_UP));	
				}catch (Exception e) {
				e.printStackTrace();
				}	
				
				
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
			if(this.intData%1000 != 0){
				this.intPage = this.intData/1000+1;
			}else{
				this.intPage = this.intData/1000;
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
				int _indata = (int) (_totData%1000);
				
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
						if(i == 1000 ){
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