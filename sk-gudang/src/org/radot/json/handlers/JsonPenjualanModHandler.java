package org.radot.json.handlers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.radot.hibernate.entities.CustomerEntity;
import org.radot.hibernate.entities.PenjualanEntity;
import org.radot.hibernate.entities.SalesmanEntity;
import org.radot.hibernate.persistences.CustomerPersistence;
import org.radot.hibernate.persistences.PenjualanPersistence;
import org.radot.hibernate.persistences.SalesmanPersistence;
import org.radot.json.beans.PenjualanInputParam;
import org.radot.json.beans.PenjualanItem;
import org.radot.json.beans.PenjualanResult;
import org.radot.json.servlet.JsonServletHandler;

public class JsonPenjualanModHandler extends JsonServletHandler<PenjualanInputParam, PenjualanResult> {

	public JsonPenjualanModHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		
		super(request, response);
	}
	
	@Override()
	public void process() throws Throwable {
		String _valA = "";
		
		PenjualanEntity _ent = new PenjualanPersistence().getByRecId(this.param.getId());
		if(_ent != null){
			if (!_ent.getOrderNumb().equalsIgnoreCase(this.param.getOrderNumb())){
				PenjualanEntity _checkPenj = new PenjualanPersistence().getByOrderNumb(this.param.getOrderNumb());
				if (_checkPenj != null){
					this.result.setCode(1);
					this.result.setMessage("Nomor Order sudah terdaftar");
					return;
				}
				else {
					_ent.setOrderNumb(this.param.getOrderNumb());
				}
			}
			
			 _valA = "true";
		}
		
		
		/*_ent.setRecId(this.param.getId());*/
		final CustomerEntity customerEnt = new CustomerPersistence().getByRecId(Long.parseLong(this.param.getCustomerEnt()));
		final SalesmanEntity salesmanEnt = new SalesmanPersistence().getByRecId(Long.parseLong(this.param.getSalesmanEnt()));
		
		Date _orderDate = new Date();
		_orderDate.setTime(this.param.getOrderDate());
		
		_ent.setOrderDate(_orderDate);
		_ent.setOrderNumb(this.param.getOrderNumb());
		//_ent.setFakturNumb(this.param.getFakturNumb());
		_ent.setPpn(this.param.getPpn());
		_ent.setTotPpn(this.param.getTotPpn());
		_ent.setTotDiscPenjualan(this.param.getTotDisc());
		_ent.setTotPenjualanBrutoIdr(this.param.getBrutoDefault());
		_ent.setTotPenjualanNettIdr(this.param.getNettoDefault());
		_ent.setKeterangan(this.param.getKeterangan());
		if(salesmanEnt !=null){
		_ent.setSalesmanEnt(salesmanEnt);
		}
		if(customerEnt !=null){
		_ent.setCustomerEnt(customerEnt);
		}
		
		_ent.modify();
		
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
		
		
		final List<PenjualanItem> _items = new ArrayList<PenjualanItem>();
		_items.add(_item);
		
		this.result.setItems(_items);
		this.result.setCode(0);
		this.result.setMessage("Sukses");
	}

}
