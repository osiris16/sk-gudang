package org.radot.json.handlers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.radot.consts.SessionKeysConst;
import org.radot.hibernate.entities.CustomerEntity;
import org.radot.hibernate.entities.HistoryEntity;
import org.radot.hibernate.entities.PenjualanEntity;
import org.radot.hibernate.entities.SalesmanEntity;
import org.radot.hibernate.entities.UserEntity;
import org.radot.hibernate.persistences.CustomerPersistence;
import org.radot.hibernate.persistences.PenjualanPersistence;
import org.radot.hibernate.persistences.SalesmanPersistence;
import org.radot.json.beans.PenjualanInputParam;
import org.radot.json.beans.PenjualanItem;
import org.radot.json.beans.PenjualanResult;
import org.radot.json.servlet.JsonServletHandler;
import org.radot.utils.AutoGenerateID;

import com.google.gson.Gson;

public class JsonPenjualanAddHandler extends JsonServletHandler<PenjualanInputParam, PenjualanResult> {

	public JsonPenjualanAddHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
		String _valA = "";
		final HttpSession _session = this.request.getSession();
		PenjualanEntity _validasiPenjualan = new PenjualanPersistence().getByOrderNumb(this.param.getOrderNumb());
		if(_validasiPenjualan != null){
			this.result.setCode(0);
			this.result.setMessage("Nomor order sudah terdaftar");
			 _valA = "true";
		}
		
		
		if(!_valA.equalsIgnoreCase("true")){
			final CustomerEntity customerEnt = new CustomerPersistence().getByRecId(Long.parseLong(this.param.getCustomerEnt()));
			final SalesmanEntity salesmanEnt = new SalesmanPersistence().getByRecId(Long.parseLong(this.param.getSalesmanEnt()));
			final PenjualanEntity _ent = new PenjualanEntity();
			Date _orderDate = new Date();
			_orderDate.setTime(this.param.getOrderDate());
			
			_ent.setOrderDate(_orderDate);
			
			
			String _idOrder = AutoGenerateID.ex("");
			
			_ent.setFakturNumb(this.param.getFakturNumb());
			_ent.setOrderNumb(_idOrder);
			_ent.setPpn(this.param.getPpn());
			_ent.setKeterangan(this.param.getKeterangan());
			if(salesmanEnt !=null){
			_ent.setSalesmanEnt(salesmanEnt);
			}
			if(customerEnt !=null){
			_ent.setCustomerEnt(customerEnt);
			}
			
			BigDecimal _default = new BigDecimal(0);
			_ent.setTotDiscPenjualan(_default);
			_ent.setTotPenjualanBrutoIdr(_default);
			_ent.setTotPenjualanNettIdr(_default);
			_ent.setTotPpn(_default);
			
			_ent.setTerkirim("Belum Terkirim");
			_ent.setKeterangan(this.param.getKeterangan());
			try {
				_ent.save();
			} catch (Exception e) {
				AutoGenerateID.exPenjualanSave(_idOrder, _ent);
			}
			
		
			
			final PenjualanItem _item = new PenjualanItem();
			_item.setOrderNumb(_ent.getOrderNumb());
			
			_item.setOrderDate(_ent.getOrderDate());
			_item.setFakturNumb(_ent.getFakturNumb());
			if(customerEnt != null){
			_ent.setCustomerEnt(_ent.getCustomerEnt());
			}
			if(salesmanEnt != null){
			_ent.setSalesmanEnt(_ent.getSalesmanEnt());
			}
			_item.setKeterangan(_ent.getKeterangan());
			final List<PenjualanItem> _items = new ArrayList<PenjualanItem>();
			_items.add(_item);
			
			
			HashMap<String, Object> _mapsHistory = new HashMap<String, Object>();
			
			_mapsHistory.put("params", this.param);
			_mapsHistory.put("response", _items);
			
			String _mapsStringJSon = new Gson().toJson(_mapsHistory);
			HistoryEntity _hisEntity = new HistoryEntity();
			_hisEntity.setDataHistory(_mapsStringJSon.replace("\\", ""));
			_hisEntity.setType("Penjualan");
			_hisEntity.setActionType("Add");
			UserEntity _user = null;
			try {
				_user = (UserEntity) _session.getAttribute(SessionKeysConst.CURRENT_USER);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(null != _user){
				_hisEntity.setCreatedBy(_user);
			}
			_hisEntity.save();
			
			this.result.setItems(_items);
			this.result.setCode(0);
			this.result.setMessage("Sukses");
		}
	
	}

}

