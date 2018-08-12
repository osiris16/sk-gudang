package org.radot.json.handlers;

import java.math.BigDecimal;
import java.math.BigInteger;
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
import org.radot.hibernate.entities.ProductEntity;
import org.radot.hibernate.entities.ProductGroupEntity;
import org.radot.hibernate.entities.StockEntity;
import org.radot.hibernate.persistences.DetPembelianPersistence;
import org.radot.hibernate.persistences.ProductGroupPersistence;
import org.radot.hibernate.persistences.ProductPersistence;
import org.radot.hibernate.persistences.StockPersistence;
import org.radot.json.beans.StockItem;
import org.radot.json.beans.StockResult;
import org.radot.json.beans.StockSelectParam;
import org.radot.json.servlet.JsonServletHandler;
import org.radot.utils.ExportToPdf;

public class JsonStockHandler extends JsonServletHandler<StockSelectParam, StockResult> {

	public JsonStockHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
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
		StockItem _item;
		final List<StockItem> _items = new ArrayList<StockItem>();
		int page = 0;
		
		/*search*/
		 ArrayList<Criterion> _arrayCrit = new ArrayList<Criterion>();
		 String _paramBy = this.param.getByValue();
		 String _paramQuery = "%"+this.param.getQueryData()+"%";
		
		 if(_paramBy.equalsIgnoreCase("PRINTSTOCK")){
			ExportToPdf.stokCtn(this.param.getQueryData());
			this.result.setCode(0);
			this.result.setMessage("print success");
			return;
		 }
		 if(_paramBy.equalsIgnoreCase("DOWNLOADEXCEL")){
				ExportToPdf.stockCTnexcel(this.param.getQueryData());
				this.result.setCode(0);
				this.result.setMessage("download success");
				return;
	     }
		 
		Criterion _filterByTripNumb = Restrictions.ilike("tripNumStok", _paramQuery);
		Criterion _filterByCode = Restrictions.ilike("code", _paramQuery);
		Criterion _filterByName = Restrictions.ilike("name", _paramQuery);
		Criterion _stok = Restrictions.eq("stokCtn", this.param.get_qtyStock());
		
		List<Order> _listOrder = new ArrayList<Order>();
		
//		if(_paramBy.equalsIgnoreCase("tripNumStok")){
//			_arrayCrit.add(_filterByTripNumb);
//		}
		
		
		
		if(_paramBy.equalsIgnoreCase("code")){
			_arrayCrit.add(_filterByCode);
		}
		if(_paramBy.equalsIgnoreCase("name")){
			_arrayCrit.add(_filterByName);
		}
		
		List<ProductEntity> _entList = null;
		List<StockEntity> _entStockList = null;
		
		List<DetailPembelianEntity> _detPembList = null;
		if(_paramBy.equalsIgnoreCase("tripNum")){
			_arrayCrit.add(_filterByTripNumb);
			Order _orderByID = Order.asc("recId");
			_listOrder.add(_orderByID);
			_entStockList = BaseEntity.listDataOffset(StockEntity.class, _arrayCrit, _listOrder, 10, 1l);
		}else if(_paramBy.equalsIgnoreCase("stokCtn")){
			_arrayCrit.add(_stok);
			//System.out.println("tets");
			Order _orderByID = Order.desc("recId");
			//Order _orderByID = Order.desc("modifiedOn");
			_listOrder.add(_orderByID);
			_entStockList = BaseEntity.listDataOffset(StockEntity.class, _arrayCrit, _listOrder, 10, 1l);
		}else if(_paramBy.equalsIgnoreCase("tripNumSeq")){
			Criterion _stockSeq = Restrictions.ilike("tripNumSeqStock", _paramQuery);
			Order _orderByID = Order.asc("recId");
			_listOrder.add(_orderByID);
			_arrayCrit.add(_stockSeq);
			_detPembList = BaseEntity.listDataOffset(DetailPembelianEntity.class, _arrayCrit, _listOrder, 10, 1l);
		}else{
			Order _orderByID = Order.desc("recId");
			_listOrder.add(_orderByID);
			_entList = BaseEntity.listDataOffset(ProductEntity.class, _arrayCrit,_listOrder,  10, 1l);
		}
		StockPersistence _w =  new StockPersistence();
		_session.setAttribute("queryByBy", _paramBy);
		
//		final List<ProductEntity> _entst = BaseEntity.listDataOffset(ProductEntity.class, _arrayCrit,null,  null, null);
		Number _count = 0;
		if(_paramBy.equalsIgnoreCase("tripNum")){
			_count = new StockPersistence().getCountByTripNum(_paramBy, _paramQuery);
		}else if(_paramBy.equalsIgnoreCase("stokCtn")){
			_count = new StockPersistence().getCountByTotStok(_paramBy, this.param.get_qtyStock());
		}else if(_paramBy.equalsIgnoreCase("tripNumSeq")){
			_count = new DetPembelianPersistence().getCountByTripSeq(_paramQuery);
		}
		else{
			_count = new ProductPersistence().getCountByProduct(_paramBy, _paramQuery);
		}
		
		if (_count.toString() == "0"){
			this.result.setMessage("data tidak ditemukan");
			this.result.setCode(80);
		}
//		final List<VendorEntity> _entList = _w.getByCity(this.param.getCity());
		Long _lPage = 0l;
		if (null != _entList && !_entList.isEmpty()) {
			for (final ProductEntity _ent: _entList) {
				_item = new StockItem();
				
				_item.setProdId(_ent.getRecId());
				_item.setProductCode(_ent.getCode());
				_item.setProductName(_ent.getName());
				_item.setProductMadeIn(_ent.getMadeIn());
				_item.setProductMerk(_ent.getMerk());
				_item.setProductBarcode(_ent.getBarcode());
				_item.setProductStandartNo(_ent.getStandartNo());
				_item.setProductPartNumb(_ent.getPartNumb());
				_item.setProductImage(_ent.getImage());
				_item.setIsiCtn(_ent.getIsiCtn());
				_item.setSatIsiCtn(_ent.getSatIsiCtn());
				_item.setIsiPcs(_ent.getIsiPcs());
				_item.setHargaJualCtn(_ent.getHJstdCtn());
				
			
					BigDecimal _hargaJualCtn = _ent.getHJstdCtn();
					BigDecimal _isiPcs = _ent.getIsiPcs();
					BigDecimal _isiCtn =_ent.getIsiCtn();
					BigDecimal _hargaJualCtn2 = _hargaJualCtn.divide(_isiCtn,2,RoundingMode.HALF_UP);
					BigDecimal _hargaJualPcs = _hargaJualCtn2.divide(_isiPcs,2,RoundingMode.HALF_UP);
					_item.setHargaJualPcs(_hargaJualPcs);
					
				
				StockEntity _stockEnt = new StockPersistence().getByProdEnt(_ent);
				if(_stockEnt != null){
					_item.setStockId(_stockEnt.getRecId());
					_item.setStokCtn(_stockEnt.getStokCtn());
					_item.setStokIsiCtnRetail(BigDecimal.ZERO);
					_item.setStokIsiCtnDeptStore(BigDecimal.ZERO);
					if(null !=_stockEnt.getStokCtnRetail()){
						_item.setStokIsiCtnRetail(_stockEnt.getStokCtnRetail());
					}
					if(null != _stockEnt.getStokCtn_grosir()) {
						_item.setStokIsiCtnDeptStore(_stockEnt.getStokCtn_grosir());
					}
					_item.setTripNumbStok(_stockEnt.getTripNumStok());
					_item.setTripDateStok(_stockEnt.getTripDateStok());
					
					
					BigDecimal _stokCtn = _stockEnt.getStokCtn();
			
					BigDecimal _stokIsiCtn = _stokCtn.multiply(_isiCtn);
					BigDecimal _stokPcs = _stokIsiCtn.multiply(_isiPcs);
					
					_item.setTotStokPcs(_stokPcs);
				}
			
				ProductGroupEntity _pgEnt =_ent.getProductGroupEnt();
					if(_pgEnt !=null){
					_item.setProdGroupId(_pgEnt.getRecId());
					_item.setProductGroup(_pgEnt.getName());
					}
					/*String _a = "";
					_a = _ent.getProductGroupEnt().getName();
					_item.setProductGroup(_a);*/
					
				_items.add(_item);
				_lPage = _ent.getRecId();
			}
		}else if (null != _detPembList && !_detPembList.isEmpty() || _paramBy.equalsIgnoreCase("tripNumSeq")) {
			for (final DetailPembelianEntity _ent: _detPembList) {
				_item = new StockItem();
				_item.setProdId(_ent.getStockEnt().getProductEnt().getRecId());
				_item.setProductCode(_ent.getStockEnt().getProductEnt().getCode());
				_item.setProductName(_ent.getStockEnt().getProductEnt().getName());
				_item.setProductMadeIn(_ent.getStockEnt().getProductEnt().getMadeIn());
				_item.setProductMerk(_ent.getStockEnt().getProductEnt().getMerk());
				_item.setProductBarcode(_ent.getStockEnt().getProductEnt().getBarcode());
				_item.setProductStandartNo(_ent.getStockEnt().getProductEnt().getStandartNo());
				_item.setProductPartNumb(_ent.getStockEnt().getProductEnt().getPartNumb());
				_item.setProductImage(_ent.getStockEnt().getProductEnt().getImage());
				_item.setIsiCtn(_ent.getStockEnt().getProductEnt().getIsiCtn());
				_item.setSatIsiCtn(_ent.getStockEnt().getProductEnt().getSatIsiCtn());
				_item.setIsiPcs(_ent.getStockEnt().getProductEnt().getIsiPcs());
				_item.setHargaJualCtn(_ent.getStockEnt().getProductEnt().getHJstdCtn());
				
			
					BigDecimal _hargaJualCtn = _ent.getStockEnt().getProductEnt().getHJstdCtn();
					BigDecimal _isiPcs = _ent.getStockEnt().getProductEnt().getIsiPcs();
					BigDecimal _isiCtn =_ent.getStockEnt().getProductEnt().getIsiCtn();
					BigDecimal _hargaJualCtn2 = _hargaJualCtn.divide(_isiCtn,2,RoundingMode.HALF_UP);
					BigDecimal _hargaJualPcs = _hargaJualCtn2.divide(_isiPcs,2,RoundingMode.HALF_UP);
					_item.setHargaJualPcs(_hargaJualPcs);
					
				
				
				StockEntity _stockEnt =_ent.getStockEnt();
				if(_stockEnt != null){
					_item.setStockId(_stockEnt.getRecId());
					_item.setStokCtn(_stockEnt.getStokCtn());
					_item.setTripNumbStok(_stockEnt.getTripNumStok());
					_item.setTripDateStok(_stockEnt.getTripDateStok());
					//_item.setTotStokPcs(_stockEnt.getTotStokPcs());
					
					BigDecimal _stokCtn = _stockEnt.getStokCtn();
					//BigDecimal _isiPcs = _stockEnt.getProductEnt().getIsiPcs();
					//BigDecimal _isiCtn = _stockEnt.getProductEnt().getIsiCtn();
					BigDecimal _stokIsiCtn = _stokCtn.multiply(_isiCtn);
					BigDecimal _stokPcs = _stokIsiCtn.multiply(_isiPcs);
					
					_item.setTotStokPcs(_stokPcs);
					
					
				}
				
				ProductGroupEntity _pgEnt =_ent.getStockEnt().getProductEnt().getProductGroupEnt();
				if(_pgEnt != null){
					_item.setProdGroupId(_pgEnt.getRecId());
					_item.setProductGroup(_pgEnt.getName());
				}
				/*	String _a = "";
				
					_a = _ent.getStockEnt().getProductEnt().getProductGroupEnt().getName();
					if(_a!=null){
					_item.setProductGroup(_a);
					}*/
				
				_items.add(_item);
				_lPage = _ent.getRecId();
			}
		}
		else{

			for (final StockEntity _ent: _entStockList) {
				_item = new StockItem();
				_item.setProdId(_ent.getProductEnt().getRecId());
				_item.setProductCode(_ent.getProductEnt().getCode());
				_item.setProductName(_ent.getProductEnt().getName());
				_item.setProductMadeIn(_ent.getProductEnt().getMadeIn());
				_item.setProductMerk(_ent.getProductEnt().getMerk());
				_item.setProductBarcode(_ent.getProductEnt().getBarcode());
				_item.setProductStandartNo(_ent.getProductEnt().getStandartNo());
				_item.setProductPartNumb(_ent.getProductEnt().getPartNumb());
				_item.setProductImage(_ent.getProductEnt().getImage());
				_item.setIsiCtn(_ent.getProductEnt().getIsiCtn());
				_item.setSatIsiCtn(_ent.getProductEnt().getSatIsiCtn());
				_item.setIsiPcs(_ent.getProductEnt().getIsiPcs());
				_item.setHargaJualCtn(_ent.getProductEnt().getHJstdCtn());
				
				
					BigDecimal _hargaJualCtn = _ent.getProductEnt().getHJstdCtn();
					BigDecimal _isiPcs = _ent.getProductEnt().getIsiPcs();
					BigDecimal _isiCtn =_ent.getProductEnt().getIsiCtn();
					BigDecimal _hargaJualCtn2 = _hargaJualCtn.divide(_isiCtn,2,RoundingMode.HALF_UP);
					BigDecimal _hargaJualPcs = _hargaJualCtn2.divide(_isiPcs,2,RoundingMode.HALF_UP);
					_item.setHargaJualPcs(_hargaJualPcs);
					
				
				
				StockEntity _stockEnt =_ent;
				if(_stockEnt != null){
					
					_item.setStockId(_stockEnt.getRecId());
					_item.setStokCtn(_stockEnt.getStokCtn());
					_item.setTripNumbStok(_stockEnt.getTripNumStok());
					_item.setTripDateStok(_stockEnt.getTripDateStok());
					//_item.setTotStokPcs(_stockEnt.getTotStokPcs());
					
					BigDecimal _stokCtn = _stockEnt.getStokCtn();
					//BigDecimal _isiPcs = _stockEnt.getProductEnt().getIsiPcs();
					//BigDecimal _isiCtn = _stockEnt.getProductEnt().getIsiCtn();
					BigDecimal _stokIsiCtn = _stokCtn.multiply(_isiCtn);
					BigDecimal _stokPcs = _stokIsiCtn.multiply(_isiPcs);
					
					_item.setTotStokPcs(_stokPcs);
					
				}
				ProductGroupEntity _pgEnt =_ent.getProductEnt().getProductGroupEnt();
				if(_pgEnt!=null){	
				_item.setProdGroupId(_pgEnt.getRecId());
				_item.setProductGroup(_pgEnt.getName());
				}
					/*String _a = "";
					_a = _ent.getProductEnt().getProductGroupEnt().getName();
					if(_a!=null){
					_item.setProductGroup(_a);
					}*/
				
				_items.add(_item);
				_lPage = _ent.getRecId();
			}
		}
		Long _modulo = _count.longValue()%10;
		Long _totalPage = _count.longValue()/10;
		if(_modulo != 0){
			_totalPage = _totalPage + 1;
		}
		_session.setAttribute("itemsStock", _items);
		_session.setAttribute("offsetLast", _lPage);
		_session.setAttribute("totalData", Integer.parseInt(_count.toString()));
		_session.setAttribute("orderList",  _listOrder);
		_session.setAttribute("arrayCrit", _arrayCrit);
//		System.out.println(_count.longValue()+"  s");
		
		this.result.setPage(_totalPage.toString());
		this.result.setItems(_items);
		this.result.setCode(0);
		
		_session.setAttribute("dotPage", "True"); //Stop
		new Thread(new JsonStockHandler.BackgroundDataLoader(_session)).start();

	}
	public static class BackgroundDataLoader implements Runnable{
		private String _totData;
		private String _paramByBy;
		private HttpSession session;
		private Integer intPage;
		private Integer intData;
		private Integer intCurrent;
		private long lastOffset;
		private List<StockItem> items;
		private List<Criterion> _critArray;
		private List<Order> _listOrder;
		
		@SuppressWarnings("unchecked")
		public  BackgroundDataLoader(final HttpSession _session) {
			this.session = _session;
			this._paramByBy = (String) _session.getAttribute("queryByBy");
			this._critArray = (List<Criterion>) _session.getAttribute("arrayCrit");
			this._listOrder = (List<Order>) _session.getAttribute("orderList");
//			System.out.println("sss " + session.getAttribute("totalData"));
			this.intData = (Integer) session.getAttribute("totalData");
			if(this.intData%10 != 0){
				this.intPage = this.intData/10+1;
			}else{
				this.intPage = this.intData/10;
			}
			
			List<StockItem> _items = new ArrayList<StockItem>();
			_items = (List<StockItem>) this.session.getAttribute("itemsStock");
		//	System.out.println("sss " + this.intData);
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
			HashMap<String, ArrayList<StockItem>> _wew = new HashMap<String, ArrayList<StockItem>>();
				Long _totData = Long.valueOf(this.intData);
				List<StockItem> _items = new ArrayList<StockItem>();
				List<ProductEntity> _entList = null;
				List<StockEntity> _entStockList = null;
				List<DetailPembelianEntity>_detPembList = null;
				int _indata = (int) (_totData%10);
				if(this._paramByBy.equalsIgnoreCase("totStokPcs")){
					//System.out.println("masuk nggak ya");
					try {
						_entStockList = BaseEntity.listDataOffset(StockEntity.class, this._critArray,this._listOrder,  null, 1l);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}else if(this._paramByBy.equalsIgnoreCase("tripNumSeq")){
					_detPembList = BaseEntity.listDataOffset(DetailPembelianEntity.class, this._critArray,this._listOrder,  null, 1l);
				}else if(this._paramByBy.equalsIgnoreCase("tripNum")){
					_entStockList = BaseEntity.listDataOffset(StockEntity.class, this._critArray,this._listOrder,  null, 1l);
				}
					else{
					try {
						//System.out.println("masuk sini katak nya");
						_entList = BaseEntity.listDataOffset(ProductEntity.class, this._critArray,this._listOrder,  null, 1l);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				
				if (null != _entList && !_entList.isEmpty()) {
					StockItem _item;
					int i = 0;
					int _p = -1;
					String _chekLast = "";
					for (final ProductEntity _ent: _entList) {
						//sTOP PAGGING
						_chString = session.getAttribute("dotPage").toString();
						if(_chString.equalsIgnoreCase("False")){
						//	System.out.println("STOP");
							Thread.currentThread().isInterrupted();
							break;
						}
						//sTOP PAGGING
						i = i+1;
						_item = new StockItem();
						_item.setProdId(_ent.getRecId());
						_item.setProductCode(_ent.getCode());
						_item.setProductName(_ent.getName());
						_item.setProductMadeIn(_ent.getMadeIn());
						_item.setProductMerk(_ent.getMerk());
						_item.setProductBarcode(_ent.getBarcode());
						_item.setProductStandartNo(_ent.getStandartNo());
						_item.setProductPartNumb(_ent.getPartNumb());
						_item.setProductImage(_ent.getImage());
						_item.setIsiCtn(_ent.getIsiCtn());
						_item.setSatIsiCtn(_ent.getSatIsiCtn());
						_item.setIsiPcs(_ent.getIsiPcs());
						_item.setHargaJualCtn(_ent.getHJstdCtn());
						
						
							BigDecimal _hargaJualCtn = _ent.getHJstdCtn();
							BigDecimal _isiPcs = _ent.getIsiPcs();
							BigDecimal _isiCtn =_ent.getIsiCtn();
							BigDecimal _hargaJualCtn2 = _hargaJualCtn.divide(_isiCtn,2,RoundingMode.HALF_UP);
							BigDecimal _hargaJualPcs = _hargaJualCtn2.divide(_isiPcs,2,RoundingMode.HALF_UP);
							_item.setHargaJualPcs(_hargaJualPcs);
							
						
						StockEntity _stockEnt = new StockPersistence().getByProdEnt(_ent);
						if(_stockEnt != null){
							//System.out.println("masuk kah entity stockEnt " +_stockEnt.getStokCtn());
							_item.setStockId(_stockEnt.getRecId());
							_item.setStokCtn(_stockEnt.getStokCtn());
							_item.setTripNumbStok(_stockEnt.getTripNumStok());
							_item.setTripDateStok(_stockEnt.getTripDateStok());
							//_item.setTotStokPcs(_stockEnt.getTotStokPcs());
							
							BigDecimal _stokCtn = _stockEnt.getStokCtn();
							
							BigDecimal _stokIsiCtn = _stokCtn.multiply(_isiCtn);
							BigDecimal _stokPcs = _stokIsiCtn.multiply(_isiPcs);
							
							_item.setStokIsiCtnRetail(BigDecimal.ZERO);
							_item.setStokIsiCtnDeptStore(BigDecimal.ZERO);
							if(null !=_stockEnt.getStokCtnRetail()){
								_item.setStokIsiCtnRetail(_stockEnt.getStokCtnRetail());
							}
							if(null != _stockEnt.getStokCtn_grosir()) {
								_item.setStokIsiCtnDeptStore(_stockEnt.getStokCtn_grosir());
							}
							
							_item.setTotStokPcs(_stokPcs);
						}
						
						ProductGroupEntity _pgEnt = _ent.getProductGroupEnt();
						if(_pgEnt!=null){
						_item.setProdGroupId(_pgEnt.getRecId());
						_item.setProductGroup(_pgEnt.getName());
						}
						/*String _a = "";
				
						_a = _ent.getProductGroupEnt().getName();
						if(_a!=null){
						_item.setProductGroup(_a);
						}*/
						
						_items.add(_item);
						this.lastOffset = _ent.getRecId();
						if(i == 10 ){
							_p = _p+1;
							i = 0;
							String _page = "page"+_p;
							if(this.intPage - _p == 2){
								_chekLast = "last";
							}
						_wew.put(_page, (ArrayList<StockItem>) _items);
						_items = new ArrayList<StockItem>();
						session.setAttribute("paggingDataStock", _wew);
						}
						if(_chekLast.equalsIgnoreCase("last") && _indata != 0 && _indata == i){
							_p = _p+1;
							i = 0;
							String _page = "page"+_p;
						//	System.out.println(_page);
							if(this.intPage - _p == 2){
								_chekLast = "last";
							}
						_wew.put(_page, (ArrayList<StockItem>) _items);
						_items = new ArrayList<StockItem>();
						session.setAttribute("paggingDataStock", _wew);
						}
					}
				}else if (null != _detPembList && !_detPembList.isEmpty()) {
					StockItem _item;
					int i = 0;
					int _p = -1;
					String _chekLast = "";
					for (final DetailPembelianEntity _ent: _detPembList) {
						//sTOP PAGGING
						_chString = session.getAttribute("dotPage").toString();
						if(_chString.equalsIgnoreCase("False")){
						//	System.out.println("STOP");
							Thread.currentThread().isInterrupted();
							break;
						}
						//sTOP PAGGING
						i = i+1;
						_item = new StockItem();
						_item.setStockId(_ent.getStockEnt().getRecId());
						_item.setProdId(_ent.getStockEnt().getProductEnt().getRecId());
						_item.setProductCode(_ent.getStockEnt().getProductEnt().getCode());
						_item.setProductName(_ent.getStockEnt().getProductEnt().getName());
						_item.setProductMadeIn(_ent.getStockEnt().getProductEnt().getMadeIn());
						_item.setProductMerk(_ent.getStockEnt().getProductEnt().getMerk());
						_item.setProductBarcode(_ent.getStockEnt().getProductEnt().getBarcode());
						_item.setProductStandartNo(_ent.getStockEnt().getProductEnt().getStandartNo());
						_item.setProductPartNumb(_ent.getStockEnt().getProductEnt().getPartNumb());
						_item.setProductImage(_ent.getStockEnt().getProductEnt().getImage());
						_item.setIsiCtn(_ent.getStockEnt().getProductEnt().getIsiCtn());
						_item.setSatIsiCtn(_ent.getStockEnt().getProductEnt().getSatIsiCtn());
						_item.setIsiPcs(_ent.getStockEnt().getProductEnt().getIsiPcs());
						_item.setHargaJualCtn(_ent.getStockEnt().getProductEnt().getHJstdCtn());
						
						
							BigDecimal _hargaJualCtn = _ent.getStockEnt().getProductEnt().getHJstdCtn();
							BigDecimal _isiPcs = _ent.getStockEnt().getProductEnt().getIsiPcs();
							BigDecimal _isiCtn =_ent.getStockEnt().getProductEnt().getIsiCtn();
							BigDecimal _hargaJualCtn2 = _hargaJualCtn.divide(_isiCtn,2,RoundingMode.HALF_UP);
							BigDecimal _hargaJualPcs = _hargaJualCtn2.divide(_isiPcs,2,RoundingMode.HALF_UP);
							_item.setHargaJualPcs(_hargaJualPcs);
							
						
						
						StockEntity _stockEnt = new StockPersistence().getByProdEnt(_ent.getStockEnt().getProductEnt());
						if(_stockEnt != null){
							_item.setStockId(_stockEnt.getRecId());
							_item.setStokCtn(_stockEnt.getStokCtn());
							_item.setTripNumbStok(_stockEnt.getTripNumStok());
							_item.setTripDateStok(_stockEnt.getTripDateStok());
							//_item.setTotStokPcs(_stockEnt.getTotStokPcs());
							
							BigDecimal _stokCtn = _stockEnt.getStokCtn();
							//BigDecimal _isiPcs = _stockEnt.getProductEnt().getIsiPcs();
							//BigDecimal _isiCtn = _stockEnt.getProductEnt().getIsiCtn();
							BigDecimal _stokIsiCtn = _stokCtn.multiply(_isiCtn);
							BigDecimal _stokPcs = _stokIsiCtn.multiply(_isiPcs);
							
							_item.setTotStokPcs(_stokPcs);
						}
						
						ProductGroupEntity _pgEnt = _ent.getStockEnt().getProductEnt().getProductGroupEnt();
							
							if(_pgEnt!=null){
							_item.setProdGroupId(_pgEnt.getRecId());
							_item.setProductGroup(_pgEnt.getName());
							
							}
						/*String _a = "";
						
							_a = _ent.getStockEnt().getProductEnt().getProductGroupEnt().getName();
							if(_a!=null){
							_item.setProductGroup(_a);
							}*/
						_items.add(_item);
						this.lastOffset = _ent.getRecId();
						if(i == 10 ){
							_p = _p+1;
							i = 0;
							String _page = "page"+_p;
							if(this.intPage - _p == 2){
								_chekLast = "last";
							}
						_wew.put(_page, (ArrayList<StockItem>) _items);
						_items = new ArrayList<StockItem>();
						session.setAttribute("paggingDataStock", _wew);
						}
						if(_chekLast.equalsIgnoreCase("last") && _indata != 0 && _indata == i){
							_p = _p+1;
							i = 0;
							String _page = "page"+_p;
							//System.out.println(_page);
							if(this.intPage - _p == 2){
								_chekLast = "last";
							}
						_wew.put(_page, (ArrayList<StockItem>) _items);
						_items = new ArrayList<StockItem>();
						session.setAttribute("paggingDataStock", _wew);
						}
					}
				}else{
					
					StockItem _item;
					int i = 0;
					int _p = -1;
					String _chekLast = "";
					for (final StockEntity _ent: _entStockList) {
						//sTOP PAGGING
					//	System.out.println("------------");
						_chString = session.getAttribute("dotPage").toString();
						if(_chString.equalsIgnoreCase("False")){
						//	System.out.println("STOP");
							Thread.currentThread().isInterrupted();
							break;
						}
						//sTOP PAGGING
						i = i+1;
						_item = new StockItem();
						_item.setStockId(_ent.getRecId());
						_item.setProdId(_ent.getProductEnt().getRecId());
						_item.setProductCode(_ent.getProductEnt().getCode());
						_item.setProductName(_ent.getProductEnt().getName());
						_item.setProductMadeIn(_ent.getProductEnt().getMadeIn());
						_item.setProductMerk(_ent.getProductEnt().getMerk());
						_item.setProductBarcode(_ent.getProductEnt().getBarcode());
						_item.setProductStandartNo(_ent.getProductEnt().getStandartNo());
						_item.setProductPartNumb(_ent.getProductEnt().getPartNumb());
						_item.setProductImage(_ent.getProductEnt().getImage());
						_item.setIsiCtn(_ent.getProductEnt().getIsiCtn());
						_item.setSatIsiCtn(_ent.getProductEnt().getSatIsiCtn());
						_item.setIsiPcs(_ent.getProductEnt().getIsiPcs());
						_item.setHargaJualCtn(_ent.getProductEnt().getHJstdCtn());
						_item.setTripNumbStok(_ent.getTripNumStok());
						_item.setStokCtn(_ent.getStokCtn());
						
						
							BigDecimal _hargaJualCtn = _ent.getProductEnt().getHJstdCtn();
							BigDecimal _isiPcs = _ent.getProductEnt().getIsiPcs();
							BigDecimal _isiCtn =_ent.getProductEnt().getIsiCtn();
							BigDecimal _hargaJualCtn2 = _hargaJualCtn.divide(_isiCtn,2,RoundingMode.HALF_UP);
							BigDecimal _hargaJualPcs = _hargaJualCtn2.divide(_isiPcs,2,RoundingMode.HALF_UP);
							_item.setHargaJualPcs(_hargaJualPcs);
							
						
							ProductGroupEntity _pgEnt= _ent.getProductEnt().getProductGroupEnt();
							if(_pgEnt!=null){
							_item.setProdGroupId(_pgEnt.getRecId());
							_item.setProductGroup(_pgEnt.getName());
							}
						
							/*String _a = "";
							_a = _ent.getProductEnt().getProductGroupEnt().getName();
							if(_a!=null){
							_item.setProductGroup(_a);
							}*/
						_items.add(_item);
						this.lastOffset = _ent.getRecId();
						if(i == 10 ){
							_p = _p+1;
							i = 0;
							String _page = "page"+_p;
							if(this.intPage - _p == 2){
								_chekLast = "last";
							}
						_wew.put(_page, (ArrayList<StockItem>) _items);
						_items = new ArrayList<StockItem>();
						session.setAttribute("paggingDataStock", _wew);
						}
						if(_chekLast.equalsIgnoreCase("last") && _indata != 0 && _indata == i){
							_p = _p+1;
							i = 0;
							String _page = "page"+_p;
						//	System.out.println(_page);
							if(this.intPage - _p == 2){
								_chekLast = "last";
							}
						_wew.put(_page, (ArrayList<StockItem>) _items);
						_items = new ArrayList<StockItem>();
						session.setAttribute("paggingDataStock", _wew);
						}
					}
				}
		}
	}
}

