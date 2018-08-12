package org.radot.json.handlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.radot.hibernate.entities.StockEntity;
import org.radot.json.beans.ProductInputParam;
import org.radot.json.beans.ProductResult;
import org.radot.json.servlet.JsonServletHandler;

public class JsonProductDelHandler extends JsonServletHandler<ProductInputParam, ProductResult> {

	public JsonProductDelHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
		
		final StockEntity _ent = new StockEntity();
		_ent.setRecId(this.param.getId());
		_ent.erase();
		
		this.result.setCode(0);
		this.result.setMessage("Data Berhasil Dihapus");
	}

}
