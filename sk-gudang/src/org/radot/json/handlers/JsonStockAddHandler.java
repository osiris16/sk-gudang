package org.radot.json.handlers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.radot.consts.SessionKeysConst;
import org.radot.hibernate.entities.HistoryEntity;
import org.radot.hibernate.entities.ProductEntity;
import org.radot.hibernate.entities.ProductGroupEntity;
import org.radot.hibernate.entities.StockEntity;
import org.radot.hibernate.entities.UserEntity;
import org.radot.hibernate.persistences.ProductGroupPersistence;
import org.radot.hibernate.persistences.ProductPersistence;
import org.radot.json.beans.ProductInputParam;
import org.radot.json.beans.ProductItem;
import org.radot.json.beans.ProductResult;
import org.radot.json.beans.StockItem;
import org.radot.json.servlet.JsonServletHandler;
import org.radot.utils.UploadImgUtils;

import com.google.gson.Gson;

public class JsonStockAddHandler extends JsonServletHandler<ProductInputParam, ProductResult> {

	public JsonStockAddHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
		String _valA = "";
		final HttpSession _session = this.request.getSession();
		String _imgName = "";
		try {
			//System.out.println(_session.getAttribute(UploadImgUtils.UPLOADED_IMAGE_NAME) + " img nya");
			_imgName =  _session.getAttribute(UploadImgUtils.UPLOADED_IMAGE_NAME).toString();
			_session.setAttribute(UploadImgUtils.UPLOADED_IMAGE_NAME,"");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		ProductEntity _validasiProduct = new ProductPersistence().getByCode(this.param.getCode());
		if(_validasiProduct != null){
			this.result.setCode(1);
			this.result.setMessage("Kode sudah terdaftar");
			 _valA = "true";
		}
		
		if(!_valA.equalsIgnoreCase("true")){
			final ProductGroupEntity productgroupEnt = new ProductGroupPersistence().getByRecId(Long.parseLong(this.param.getProductgroupEnt()));
			
			final ProductEntity _ent = new ProductEntity();
			_ent.setCode(this.param.getCode());
			_ent.setName(this.param.getName());
			_ent.setBarcode(this.param.getBarcode());
			_ent.setStandartNo(this.param.getStandartNo());
			_ent.setPartNumb(this.param.getPartNumb());
			_ent.setMadeIn(this.param.getMadeIn());
			_ent.setMerk(this.param.getMerk());
			_ent.setSatIsiCtn(this.param.getSatIsiCtn());
			_ent.setHJstdCtn(this.param.getHargaJualStdCtn());
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
			
			BigDecimal _defaultHarga = new BigDecimal(0);
			_ent.setHJstdCtn(_defaultHarga);
			
			if(!_imgName.equalsIgnoreCase("")){
				_ent.setImage(_imgName);
			}
			
			if(productgroupEnt != null){
				_ent.setProductGroupEnt(productgroupEnt);
			}
			
			_ent.save();
			
			StockEntity _stockEnt = new StockEntity();
			//BigDecimal _totCtn = this.param.getStokCtn();
			//BigDecimal _isiCtn = this.param.getIsiCtn();
			//BigDecimal _isiPcs = this.param.getIsiPcs();
			//BigDecimal _totStokIsiCtn = _totCtn.multiply(_isiCtn);
			//BigDecimal _totStokPcs = _totStokIsiCtn.multiply(_isiPcs);
			
			_stockEnt.setStokCtn(this.param.getStokCtn());
			_stockEnt.setStokCtnRetail(this.param.getStokCtnRetail());
			_stockEnt.setStokCtn_grosir(this.param.getStokCtnDeptStore());
			//_stockEnt.setTotStokPcs(_totStokPcs);
			_stockEnt.setProductEnt(_ent);
			_stockEnt.save();
			
			
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
			_stokItem.setStokCtn(_stokItem.getStokCtn());
			}
			
			
			final List<ProductItem> _items = new ArrayList<ProductItem>();
			
			_items.add(_item);
			
			
			HashMap<String, Object> _mapsHistory = new HashMap<String, Object>();
			_mapsHistory.put("params", this.param);
			_mapsHistory.put("response", _items);
			
			String _mapsStringJSon = new Gson().toJson(_mapsHistory);
			HistoryEntity _hisEntity = new HistoryEntity();
			_hisEntity.setDataHistory(_mapsStringJSon.replace("\\", ""));
			_hisEntity.setType("StockProduct");
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
