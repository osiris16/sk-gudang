package org.radot.json.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.radot.hibernate.entities.ProductEntity;
import org.radot.hibernate.entities.StockEntity;
import org.radot.hibernate.persistences.StockPersistence;
import org.radot.json.beans.ProductInputParam;
import org.radot.json.beans.StockItem;
import org.radot.json.beans.StockResult;
import org.radot.json.servlet.JsonServletHandler;

public class JsonStockDelHandler extends JsonServletHandler<ProductInputParam, StockResult> {

	public JsonStockDelHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
		
		final StockEntity _ent = new StockPersistence().getByRecId(this.param.getId());
		_ent.setRecId(this.param.getId());
		
		ProductEntity _entProd = _ent.getProductEnt();
		
		_ent.erase();
		_entProd.erase();
		
		final StockItem _item = new StockItem ();
		_item.setTotStokPcs(_item.getTotStokPcs());
		final List<StockItem> _items = new ArrayList<StockItem>();
		_items.add(_item);
		
		this.result.setItems(_items);
		this.result.setCode(0);
		this.result.setMessage("Data Berhasil Dihapus");
	}

}
