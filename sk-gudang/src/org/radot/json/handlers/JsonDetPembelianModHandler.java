package org.radot.json.handlers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.radot.hibernate.entities.DetailPembelianEntity;
import org.radot.hibernate.entities.ProductEntity;
import org.radot.hibernate.entities.StockEntity;
import org.radot.hibernate.entities.TripEntity;
import org.radot.hibernate.persistences.DetPembelianPersistence;
import org.radot.hibernate.persistences.StockPersistence;
import org.radot.hibernate.persistences.TripPersistence;
import org.radot.json.beans.DetPembelianInputParam;
import org.radot.json.beans.DetPembelianItem;
import org.radot.json.beans.DetPembelianResult;
import org.radot.json.beans.StockItem;
import org.radot.json.servlet.JsonServletHandler;
import org.radot.utils.AutoGenerateID;

public class JsonDetPembelianModHandler extends JsonServletHandler<DetPembelianInputParam, DetPembelianResult> {
	public JsonDetPembelianModHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
		
		final HttpSession _session = this.request.getSession();
		
		
			final TripEntity tripEnt = new TripPersistence().getByRecId(Long.parseLong(this.param.getTripEnt()));
			final StockEntity stockEnt = new StockPersistence().getByRecId(Long.parseLong(this.param.getStockEnt()));
			
			final DetailPembelianEntity _ent = new DetPembelianPersistence().getByRecId(this.param.getId());
			
			if(_ent != null){
			
			
			/*Modify Start*/
			_ent.getStockEnt().getProductEnt().setHJstdCtn(_ent.getHargaJ_CtnOld());
			_ent.getStockEnt().getProductEnt().modify();
			
			
			BigDecimal _qtyBeliOld = _ent.getQtyBeliCtn();
			BigDecimal _stockCtnOld = _ent.getStockEnt().getStokCtn();
			BigDecimal _stockNow = _stockCtnOld.subtract(_qtyBeliOld);
			_ent.getStockEnt().setStokCtn(_stockNow);
			//_ent.getStockEnt().setTripNumStok(_ent.getTripNumSeqStockHist());
			_ent.getStockEnt().modify();
			
			
			BigDecimal _ctnBefore = _ent.getTripEnt().getTotCarton();
			BigDecimal _ctnNow = _ctnBefore.subtract(_qtyBeliOld);
			_ent.getTripEnt().setTotCarton(_ctnNow); // Add Carton
			
	
			BigDecimal _pembelianBrutoIdrBefore = _ent.getTripEnt().getTotPembelianBrutoIdr();
			BigDecimal _pembelianBrutoIdrUpdate = _pembelianBrutoIdrBefore.subtract(_ent.getTotHargaBrutB_Idr());
			_ent.getTripEnt().setTotPembelianBrutoIdr(_pembelianBrutoIdrUpdate);
					
			BigDecimal _pembelianBrutoVtaBefore = _ent.getTripEnt().getTotPembelianBrutoVta();
			BigDecimal _pembelianBrutoVtaUpdate = _pembelianBrutoVtaBefore.subtract(_ent.getTotHargaBrutB_Vta());
			_ent.getTripEnt().setTotPembelianBrutoVta(_pembelianBrutoVtaUpdate);
					
			BigDecimal _pembelianNettoIdrBefore = _ent.getTripEnt().getTotPembelianNettoIdr();
			BigDecimal _pembelianNettoIdrUpdate = _pembelianNettoIdrBefore.subtract(_ent.getTotHargaNettB_Idr());
			_ent.getTripEnt().setTotPembelianNettoIdr(_pembelianNettoIdrUpdate);
					
			BigDecimal _pembelianNettoVtaBefore = _ent.getTripEnt().getTotPembelianNettoVta();
			BigDecimal _pembelianNettoVtaUpdate = _pembelianNettoVtaBefore.subtract(_ent.getTotHargaNettB_Vta());
			_ent.getTripEnt().setTotPembelianNettoVta(_pembelianNettoVtaUpdate);
			_ent.getTripEnt().modify(); // trip modify
			 
			/*Modify End*/	
			
			if(stockEnt != null){
				_ent.setStockEnt(stockEnt);
				}
			if(tripEnt != null){
				_ent.setTripEnt(tripEnt);
				}
			
			Date _dateTrip = new Date();
			_dateTrip.setTime(this.param.getTripDate());
			BigDecimal _isiPcs = _ent.getStockEnt().getProductEnt().getIsiPcs();
			BigDecimal _isiCtn = _ent.getStockEnt().getProductEnt().getIsiCtn();
			BigDecimal _kurs = _ent.getTripEnt().getCurrencyIDR();
			BigDecimal _hargaBpcsVta = this.param.getHargaBPcsVta();
			BigDecimal _hargaBctnVta = _hargaBpcsVta.multiply(_isiCtn).multiply(_isiPcs);
			BigDecimal _hargaBctnIdr = _hargaBctnVta.multiply(_kurs);
				
			_ent.setHargaBeliCtnVta(_hargaBctnVta); // harga beli per carton VTA
			_ent.setHargaBeliCtnIdr(_hargaBctnIdr);// harga beli per carton IDr
			_ent.setHargaJ_CtnOld(_ent.getStockEnt().getProductEnt().getHJstdCtn());
			
			BigDecimal One_Hundred = new BigDecimal(100);
			BigDecimal _hbPcsVta = (this.param.getHargaBPcsVta());
			BigDecimal _cos = this.param.getCost();
			BigDecimal _cost = _cos.divide(One_Hundred,100, RoundingMode.HALF_UP);
			BigDecimal _totBeliCtn = (this.param.getQtyBeliCtn()); 
			//BigDecimal _totBeliPcs = _totIsi.multiply(_totBeliCtn);
			BigDecimal _totBrutoHBVta = _hbPcsVta.multiply(_isiPcs).multiply(_isiCtn).multiply(_totBeliCtn);
			BigDecimal _totCost = _totBrutoHBVta.multiply(_cost); //cost
			BigDecimal _totBrutoHBIdr = _totBrutoHBVta.multiply(_kurs);
			BigDecimal _dis = this.param.getDisc();
			BigDecimal _disc = _dis.divide(One_Hundred,100, RoundingMode.HALF_UP);
			BigDecimal _totDisc = _totBrutoHBVta.multiply(_disc);
			BigDecimal _totFinalNettoVta = _totBrutoHBVta.add(_totCost).subtract(_totDisc);
			BigDecimal _totFinalNettoIdr = _totFinalNettoVta.multiply(_kurs);
				
			_ent.setTotHargaBrutB_Vta(_totBrutoHBVta);
			_ent.setTotHargaBrutB_Idr(_totBrutoHBIdr);
				
			_ent.setTotHargaNettB_Vta(_totFinalNettoVta);
			_ent.setTotHargaNettB_Idr(_totFinalNettoIdr);
			_ent.setQtyBeliCtn(_totBeliCtn);	
			
			final ProductEntity _prodEnt = stockEnt.getProductEnt();
			if(_prodEnt != null){
			
			BigDecimal _HJPCS = this.param.getHargaJPcs();
			BigDecimal _HJCTN = _isiPcs.multiply(_isiCtn).multiply(_HJPCS);
			_prodEnt.setHJstdCtn(_HJCTN); //harga jual CTN
			_prodEnt.modify();
			}
			
			final StockEntity _stockEnt = new StockPersistence().getByRecId(Long.parseLong(this.param.getStockEnt()));
				if(_stockEnt != null){
					BigDecimal _totBeliCtnNow = this.param.getQtyBeliCtn();
					BigDecimal _stokNowUpdate = _stockEnt.getStokCtn();
					BigDecimal _stockUpdatNow = _stokNowUpdate.add(_totBeliCtnNow) ; 
					_stockEnt.setStokCtn(_stockUpdatNow);
					_stockEnt.setTripDateStok(_dateTrip);
					
						_stockEnt.modify();
				}
				
				
				
				
			final TripEntity _tripEnt = new TripPersistence().getByRecId(Long.parseLong(this.param.getTripEnt()));
				if(_tripEnt != null){
					
					BigDecimal _ctnBeforeUpdate = _tripEnt.getTotCarton();
					BigDecimal _ctnNowUpdate = _totBeliCtn.add(_ctnBeforeUpdate);
					_tripEnt.setTotCarton(_ctnNowUpdate); // Add Carton
					
					BigDecimal _pembelianBrutoIdrBeforeUpdate = _tripEnt.getTotPembelianBrutoIdr();
					BigDecimal _pembelianBrutoIdrUpdateUpdate = _pembelianBrutoIdrBeforeUpdate.add(_totBrutoHBIdr);
					_tripEnt.setTotPembelianBrutoIdr(_pembelianBrutoIdrUpdateUpdate);
					
					BigDecimal _pembelianBrutoVtaBeforeUpdate = _tripEnt.getTotPembelianBrutoVta();
					BigDecimal _pembelianBrutoVtaUpdateUpdate = _pembelianBrutoVtaBeforeUpdate.add(_totBrutoHBVta);
					_tripEnt.setTotPembelianBrutoVta(_pembelianBrutoVtaUpdateUpdate);
					
					BigDecimal _pembelianNettoIdrBeforeUpdate = _tripEnt.getTotPembelianNettoIdr();
					BigDecimal _pembelianNettoIdrUpdateUpdate = _pembelianNettoIdrBeforeUpdate.add(_totFinalNettoIdr);
					_tripEnt.setTotPembelianNettoIdr(_pembelianNettoIdrUpdateUpdate);
					
					BigDecimal _pembelianNettoVtaBeforeUpdate = _tripEnt.getTotPembelianNettoVta();
					BigDecimal _pembelianNettoVtaUpdateUpdate = _pembelianNettoVtaBeforeUpdate.add(_totFinalNettoVta);
					_tripEnt.setTotPembelianNettoVta(_pembelianNettoVtaUpdateUpdate);
				
					_tripEnt.modify(); // trip modify
						
				}
				
			BigDecimal _hargaPcsIdr = this.param.getHargaJPcs();
			BigDecimal _hargaCtnIdr = _hargaPcsIdr.multiply(_isiPcs).multiply(_isiCtn);
			_ent.setHargaJ_CtnNew(_hargaCtnIdr);//harga jual CTN
			//_ent.setHargaJ_CtnOld(_ent.getStockEnt().getProductEnt().getHJstdCtn());
			
				
				//_ent.setCost(this.param.getTotCost());
				//_ent.setDisc(this.param.getTotDisc());
				_ent.modify();
			}
			
			
			
			
			final DetPembelianItem _item = new DetPembelianItem();
			if(_item != null){
				
			_item.setTripDate(_ent.getTripEnt().getTrip_date());
			_item.setTripNumber(_ent.getTripEnt().getTrip_numb());
			_item.setTripKurs(_ent.getTripEnt().getCurrencyIDR());
			_item.setVendVta(_ent.getTripEnt().getVendorEnt().getVta());
			}
			if(_ent.getStockEnt() != null){
			_item.setProductCode(_ent.getStockEnt().getProductEnt().getCode());
			
			_item.setProductName(_ent.getStockEnt().getProductEnt().getName());
			}
			
			_item.setDisc(_ent.getDisc());
			_item.setCost(_ent.getCost());
			_item.setTotHargaBrutBeliIdr(_ent.getTotHargaBrutB_Idr());
			_item.setTotHargaBrutBeliVta(_ent.getTotHargaBrutB_Vta());
			_item.setTotHargaNettBeliIdr(_ent.getTotHargaNettB_Idr());
			_item.setTotHargaNettBeliVta(_ent.getTotHargaNettB_Vta());
			_item.setHargaJualCtn(_ent.getHargaJ_CtnNew());
			_item.setTotQtyBeliCtn(_ent.getQtyBeliCtn());
			
			
			StockItem _stokItem = new StockItem ();
			if(_stokItem != null){
			_stokItem.setTotStokPcs(_stokItem.getTotStokPcs());
			}
			
			
			final List<DetPembelianItem> _items = new ArrayList<DetPembelianItem>();
			
			_items.add(_item);
			
			
			
			this.result.setItems(_items);
			this.result.setCode(0);
			this.result.setMessage("Sukses");
		}}
	
	


