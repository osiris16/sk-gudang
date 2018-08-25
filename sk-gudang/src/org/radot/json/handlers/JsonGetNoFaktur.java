package org.radot.json.handlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.radot.hibernate.entities.PenjualanEntity;
import org.radot.hibernate.persistences.PenjualanPersistence;
import org.radot.json.beans.AnnounceInputParam;
import org.radot.json.beans.AnnounceResult;
import org.radot.json.servlet.JsonServletHandler;
import org.radot.utils.AutoGenerateID;
import org.radot.utils.ExportToPdfFaturPenjualan;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class JsonGetNoFaktur extends JsonServletHandler<AnnounceInputParam, AnnounceResult> {

	public JsonGetNoFaktur(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
		if(null != this.param.getContent()){
			
			String content = this.param.getContent();
			String j = this.param.getOffset();
			JsonParser jsonParser = new JsonParser();
			JsonArray element = (JsonArray) jsonParser.parse(content);
			String destination = this.param.getDestination();
			JsonArray elemDestination = (JsonArray) jsonParser.parse(destination);
			String _retMess= "";
			Boolean _checkType = false;
			try {
				String _fakt = elemDestination.get(0).toString().replace("\"", "");
				System.out.println("fakturnya oi : "+_fakt);
				System.out.println("fakturnya oi : "+this.param.getRealfaktur());
				if(!this.param.getRealfaktur().trim().equalsIgnoreCase(_fakt.trim())) {
					PenjualanEntity _entPenjualanEntity = new PenjualanPersistence().getByFakturNumb(_fakt.trim());
					if(null != _entPenjualanEntity) {
						this.result.setMessage("nomor faktur telah digunakan");
						return;
					}
					
					_entPenjualanEntity = new PenjualanPersistence().getByFakturNumb(this.param.getRealfaktur().trim());
					_entPenjualanEntity.setFakturNumb(_fakt.trim());
					_entPenjualanEntity.modify();
					System.out.println("masuks");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			if(null != this.param.getTypePdf()){
				_checkType = true;
			}
			_retMess = ExportToPdfFaturPenjualan.print(element, elemDestination, _checkType);
			this.result.setCode(2);
			/*if(j==null||j|="dotmatrix"){
				_retMess = ExportToPdfFaturPenjualan.print(element, elemDestination);
				this.result.setCode(2);
				
			}else{
				_retMess = ExportToPdfFaturPenjualan.printRawText(element, elemDestination);
				this.result.setCode(3);
			}*/
			
			this.result.setMessage(_retMess);
			
		}else{
			String fakturNumb = AutoGenerateID.exFaktur("");
			/*	DetailPenjualanEntity _detPent = new DetPenjualanPersistence().getByRecId(this.param.getId());*/
				PenjualanEntity _entPenjualanEntity = new PenjualanPersistence().getByRecId(this.param.getId());
				_entPenjualanEntity.setFakturNumb(fakturNumb);
				_entPenjualanEntity.modify();
				this.result.setMessage(fakturNumb);
				this.result.setCode(0);
		}
		
	}

}
