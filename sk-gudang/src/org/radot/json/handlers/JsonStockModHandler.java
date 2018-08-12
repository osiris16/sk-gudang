/*package org.radot.json.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.radot.hibernate.entities.VendorEntity;
import org.radot.hibernate.persistences.VendorPersistence;
import org.radot.json.beans.VendorInputParam;
import org.radot.json.beans.VendorItem;
import org.radot.json.beans.VendorResult;
import org.radot.json.servlet.JsonServletHandler;

public class JsonVendorModHandler extends JsonServletHandler<VendorInputParam, VendorResult> {

	public JsonVendorModHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		
		super(request, response);
	}
	
	@Override()
	public void process() throws Throwable {
		String _valA = "";
		
		VendorEntity _ent = new VendorPersistence().getByRecId(this.param.getId());
		if(_ent != null){
			if (!_ent.getName().equalsIgnoreCase(this.param.getName())){
				VendorEntity _checkVend = new VendorPersistence().getByName(this.param.getName());
				if (_checkVend != null){
					this.result.setCode(1);
					this.result.setMessage("Nama sudah terdaftar");
					return;
				}
				else {
					_ent.setName(this.param.getName());
				}
			}
			
			 _valA = "true";
		}
		
		
		_ent.setRecId(this.param.getId());
		
		_ent.setAddress1(this.param.getAddress1());
		_ent.setAddress2(this.param.getAddress2());
		_ent.setCargo(this.param.getCargo());
		_ent.setCity(this.param.getCity());
		_ent.setCountry(this.param.getCountry());
		_ent.setVta(this.param.getVta());
		_ent.setEmail(this.param.getEmail());
		_ent.setFax(this.param.getFax());
		_ent.setPhone(this.param.getPhone());
		_ent.setTax(this.param.getTax());
		_ent.modify();
		
		final VendorItem _item = new VendorItem();
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

		final List<VendorItem> _items = new ArrayList<VendorItem>();
		_items.add(_item);
		
		this.result.setItems(_items);
		this.result.setCode(0);
		this.result.setMessage("Sukses");
	}

}
*/
package org.radot.json.handlers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.radot.hibernate.entities.ProductEntity;
import org.radot.hibernate.entities.ProductGroupEntity;
import org.radot.hibernate.entities.StockEntity;
import org.radot.hibernate.persistences.ProductGroupPersistence;
import org.radot.hibernate.persistences.ProductPersistence;
import org.radot.hibernate.persistences.StockPersistence;
import org.radot.json.beans.ProductInputParam;
import org.radot.json.beans.ProductItem;
import org.radot.json.beans.ProductResult;
import org.radot.json.beans.StockItem;
import org.radot.json.servlet.JsonServletHandler;
import org.radot.utils.UploadImgUtils;

public class JsonStockModHandler extends JsonServletHandler<ProductInputParam, ProductResult> {

	public JsonStockModHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
		
		ProductEntity _ent = new ProductPersistence().getByRecId(this.param.getId());
		final HttpSession _session = this.request.getSession();
		String _imgName = "";
		try {
			_imgName =  _session.getAttribute(UploadImgUtils.UPLOADED_IMAGE_NAME).toString();
			_session.setAttribute(UploadImgUtils.UPLOADED_IMAGE_NAME, "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String _valA = "";
		if(_ent != null){
			
			if (!_ent.getCode().equalsIgnoreCase(this.param.getCode())){
				ProductEntity _checkProd = new ProductPersistence().getByCode(this.param.getCode());
				if (_checkProd != null){
					this.result.setCode(1);
					this.result.setMessage("Kode sudah terdaftar");
					return;
				}
				else {
					_ent.setCode(this.param.getCode());
				}
			}
			
			 _valA = "true";
		}
		
		 ProductGroupEntity productgroupEnt = null;
		 try{
			 productgroupEnt = new ProductGroupPersistence().getByRecId(Long.parseLong(this.param.getProductgroupEnt()));
		 }catch(Exception e){
			 System.out.println("group ent null");
		 }
		_ent.setCode(this.param.getCode());
		_ent.setName(this.param.getName());
		_ent.setBarcode(this.param.getBarcode());
		_ent.setStandartNo(this.param.getStandartNo());
		_ent.setPartNumb(this.param.getPartNumb());
		_ent.setMadeIn(this.param.getMadeIn());
		_ent.setMerk(this.param.getMerk());
		_ent.setSatIsiCtn(this.param.getSatIsiCtn());
		BigDecimal _isiPcs = this.param.getIsiPcs();
		BigDecimal _default= new BigDecimal(1);
		//BigDecimal _zero= BigDecimal.ZERO;;
		BigDecimal _isiCtn = this.param.getIsiCtn();
		
		
		if (null==_isiCtn||_isiCtn.equals(BigDecimal.ZERO)){
			_ent.setIsiCtn(_default);
		}
		else
		{
			_ent.setIsiCtn(_isiCtn);
		}
		if (null==_isiPcs||_isiPcs.equals(BigDecimal.ZERO)){
			_ent.setIsiPcs(_default);
		}
		else
		{
			_ent.setIsiPcs(_isiPcs);
		}
		
		if(!_imgName.equalsIgnoreCase("")){
			_ent.setImage(_imgName);
		}
//		_ent.modify();
		if(productgroupEnt != null){
			_ent.setProductGroupEnt(productgroupEnt);
		}
		else{
			
		}
		_ent.modify();
		
		StockEntity _stockEnt = new StockPersistence().getByProdEnt(_ent);
		if(_stockEnt != null){
			
			_stockEnt.setStokCtn(this.param.getStokCtn());
			_stockEnt.setStokCtnRetail(this.param.getStokCtnRetail());
			_stockEnt.setStokCtn_grosir(this.param.getStokCtnDeptStore());
			_stockEnt.setProductEnt(_ent);
			_stockEnt.modify();
		}
		
		final ProductItem _item = new ProductItem();
		_item.setCode(_ent.getCode());
		_item.setName(_ent.getName());
		_item.setBarcode(_ent.getBarcode());
		_item.setStandartNo(_ent.getStandartNo());
		_item.setPartNumb(_ent.getPartNumb());
		_item.setMadeIn(_ent.getMadeIn());
		_item.setMerk(_ent.getMerk());
		_item.setIsiCtn(_ent.getIsiCtn());
		_item.setSatIsiCtn(_ent.getSatIsiCtn());
		_item.setIsiPcs(_ent.getIsiPcs());
		if(productgroupEnt != null){
		_ent.setProductGroupEnt(_ent.getProductGroupEnt());
		}
		
		
		StockItem _stokItem = new StockItem ();
		if(_stokItem != null){
		_stokItem.setTotStokPcs(_stokItem.getTotStokPcs());
		}
		
		
		final List<ProductItem> _items = new ArrayList<ProductItem>();
		_items.add(_item);
		
		this.result.setItems(_items);
		this.result.setCode(0);
		this.result.setMessage("Sukses");
	}

}
