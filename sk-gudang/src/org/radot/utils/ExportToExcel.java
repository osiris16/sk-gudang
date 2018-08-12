package org.radot.utils;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.radot.hibernate.entities.DetailPembelianEntity;
import org.radot.hibernate.entities.DetailPenjualanEntity;
import org.radot.hibernate.entities.PenjualanEntity;
import org.radot.hibernate.entities.TripEntity;
import org.radot.hibernate.persistences.DetPembelianPersistence;
import org.radot.hibernate.persistences.DetPenjualanPersistence;

import com.mysql.jdbc.Connection;


public class ExportToExcel {

	public static String salesPrintFaktur(String _by, String dateTo,String dateFrom, String salesID, String salesName,String _userName,String discMin,String discMax) throws Exception{

		ResultSet rs = null;
		Statement st = null;
		 String _a=	System.getProperty("os.name");
		 String myDriver = "org.gjt.mm.mysql.Driver";
	      String myUrl = "jdbc:mysql://localhost/sk-gudang";
	      Class.forName(myDriver);
	      Connection conn = null;
	      if(_a.startsWith("Windows")){
	    	  conn = (Connection) DriverManager.getConnection(myUrl, "root", "");
	      }else{
	    	  conn = (Connection) DriverManager.getConnection(myUrl, "radot", "p@ssw0rd");
	      }
	      
	      String query = "SELECT p.tanggal_order ,p.no_order ,c.cust_name ,c.cust_city ,s.salesman_code ,p.tot_penjualan_nett_idr, dj.disc_persen as disc, p.ppn as ppnJual  ,sum(dj.tot_jual_bruto_idr) as brutoidr ,p.tot_disc_penjualan ,avg(dj.disc_persen) as avgdiscpersen FROM sk_penjualan p join sk_salesman s on p.salesman_code_fk = s.rec_id join sk_customers c on p.customer_code_fk = c.rec_id join sk_detail_jual dj on p.rec_id = dj.penjualan_fk";
   		 query = query+" WHERE salesman_code_fk = "+_by+" and tanggal_order between '"+dateFrom+"' and '"+dateTo+"' and dj.disc_persen between '"+discMin+"' and '"+discMax+"'  group by dj.penjualan_fk ORDER BY tanggal_order DESC ";
	      st = conn.createStatement();
	      rs = st.executeQuery(query);
	      HSSFWorkbook _wb = new HSSFWorkbook();
	      	
			HSSFSheet _sheet = _wb.createSheet(salesName+"_"+salesID);
			int _row = 3;
		    HSSFRow row = _sheet.createRow((short)_row);
		    //HEADER
		    HSSFRichTextString _s = new HSSFRichTextString("No\nUrut");
		    row.createCell((short) 0).setCellValue(_s);
		    _s = new HSSFRichTextString("Tanggal");
		    row.createCell((short) 1).setCellValue(_s);
		    _s = new HSSFRichTextString("No. Bukti");
		    row.createCell((short) 2).setCellValue(_s);
		    _s = new HSSFRichTextString("Salesman");
		    row.createCell((short) 3).setCellValue(_s);
		    _s = new HSSFRichTextString("Nama Customer");
		    row.createCell((short) 4).setCellValue(_s);
		    _s = new HSSFRichTextString("Kota");
		    row.createCell((short) 5).setCellValue(_s);
		    _s = new HSSFRichTextString("Bruto (Rp)");
		    row.createCell((short) 6).setCellValue(_s);
		    _s = new HSSFRichTextString("Netto (Rp)");
		    row.createCell((short) 7).setCellValue(_s);
		    _s = new HSSFRichTextString(" Rp ---");
		    row.createCell((short) 8).setCellValue(_s);
		    _s = new HSSFRichTextString("Disc --");
		    row.createCell((short) 9).setCellValue(_s);
		     
		    
		    //END HEADER
		    row = _sheet.createRow((short)0);
		     _s = new HSSFRichTextString("LAPORAN PENJUALAN SALES PERIODE "+dateFrom+" s/d "+dateTo);
		     row.createCell((short) 0).setCellValue(_s);
		     Region _re = new Region();
		     _re.setColumnFrom((short)0);
		     _re.setColumnTo((short)9);
		     _sheet.addMergedRegion(_re);
		     SimpleDateFormat dt2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		     row = _sheet.createRow((short)1);
		     _s = new HSSFRichTextString("PRINTED BY : " + _userName.toUpperCase() +" "+dt2.format(new Date()));
		     row.createCell((short) 0).setCellValue(_s);
		     _re.setRowFrom((short)1);
		     _re.setRowTo((short)1);
		     _sheet.addMergedRegion(_re);
		    int _tot = 0;
		    SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
		    BigDecimal _totBrut = BigDecimal.ZERO;
		      BigDecimal _totNett =  BigDecimal.ZERO;
		      BigDecimal _totTotDisc = BigDecimal.ZERO;
		      BigDecimal _totTotDiscPersen = BigDecimal.ZERO;
	      while (rs.next())
	      {		_tot = _tot+1;
	    	  	_row = _row+1;
	    	  row = _sheet.createRow((short)_row);
	    	  _s = new HSSFRichTextString(String.valueOf(_tot)); 
	    	  row.createCell((short) 0).setCellValue(_s);
	    	  
	    	  _s = new HSSFRichTextString(dt1.format(rs.getDate("tanggal_order")));
			  row.createCell((short) 1).setCellValue(_s);
			  
			  _s = new HSSFRichTextString(rs.getString("no_order"));
			  row.createCell((short) 2).setCellValue(_s);
			
			  _s = new HSSFRichTextString(rs.getString("salesman_code"));
			  row.createCell((short) 3).setCellValue(_s);
			  
			  _s = new HSSFRichTextString(rs.getString("cust_name"));
			  row.createCell((short) 4).setCellValue(_s);
			
			  _s = new HSSFRichTextString(rs.getString("cust_city"));
			  row.createCell((short) 5).setCellValue(_s);
			 
			  BigDecimal _bruto = rs.getBigDecimal("brutoidr");
				//BigDecimal _nett = rs.getBigDecimal("tot_penjualan_nett_idr");
				BigDecimal _discA = rs.getBigDecimal("disc");
				BigDecimal _cepe = new BigDecimal(100);
				BigDecimal _disc = _discA.divide(_cepe,100,RoundingMode.HALF_UP);
				BigDecimal _totDisc = _bruto.multiply(_disc);
				BigDecimal _brutoAfterDiscIdr = _bruto.subtract(_totDisc);
				BigDecimal _ppnA = rs.getBigDecimal("ppnJual");
				BigDecimal _ppn = _ppnA.divide(_cepe,100,RoundingMode.HALF_UP);
				BigDecimal _totPpn = _brutoAfterDiscIdr.multiply(_ppn);
				BigDecimal _nett = _brutoAfterDiscIdr.add(_totPpn);
				//BigDecimal _totDisc = rs.getBigDecimal("tot_disc_penjualan");
				BigDecimal _totDispersen = rs.getBigDecimal("avgdiscpersen");
				_totBrut = _totBrut.add(_bruto);
				_totNett = _totNett.add(_nett);
				_totTotDisc = _totTotDisc.add(_totDisc);
				_totTotDiscPersen = _totTotDiscPersen.add(_totDispersen);
			  _s = new HSSFRichTextString(ExportToPdf.convertRp(_bruto));
			  row.createCell((short) 6).setCellValue(_s);
			  
			  _s = new HSSFRichTextString(ExportToPdf.convertRp(_nett));
			  row.createCell((short) 7).setCellValue(_s);
			  
			  _s = new HSSFRichTextString(ExportToPdf.convertRp(_totDisc));
			  row.createCell((short) 8).setCellValue(_s);
			  
			  _s = new HSSFRichTextString(_totDispersen.divide(BigDecimal.ONE, 2, RoundingMode.HALF_UP).toString()+" %");
			  row.createCell((short) 9).setCellValue(_s);
	      }
	     
	      _wb.getSheetAt(0).autoSizeColumn((short) 0);
	      _wb.getSheetAt(0).autoSizeColumn((short) 1);
	      _wb.getSheetAt(0).autoSizeColumn((short) 2);
	      _wb.getSheetAt(0).autoSizeColumn((short) 3);
	      _wb.getSheetAt(0).autoSizeColumn((short) 4);
	      _wb.getSheetAt(0).autoSizeColumn((short) 5);
	      _wb.getSheetAt(0).autoSizeColumn((short) 6);
	      _wb.getSheetAt(0).autoSizeColumn((short) 7);
	      _wb.getSheetAt(0).autoSizeColumn((short) 8);
	      _wb.getSheetAt(0).autoSizeColumn((short) 9);
	      row = _sheet.createRow((short)_row+1);
	      _s = new HSSFRichTextString("Total");
	      row.createCell((short) 5).setCellValue(_s);
	      _s = new HSSFRichTextString(ExportToPdf.convertRp(_totBrut));
	      row.createCell((short) 6).setCellValue(_s);
	      _s = new HSSFRichTextString(ExportToPdf.convertRp(_totNett));
	      row.createCell((short) 7).setCellValue(_s);
	      _s = new HSSFRichTextString(ExportToPdf.convertRp(_totTotDisc));
	      row.createCell((short) 8).setCellValue(_s);
	      BigDecimal _avgTotDiscPerser = _totTotDiscPersen.divide(BigDecimal.valueOf(Long.valueOf(_tot)), 2,RoundingMode.HALF_UP);
	      _s = new HSSFRichTextString(_avgTotDiscPerser.toString()+" %");
	      row.createCell((short) 9).setCellValue(_s);
	      FileOutputStream fileOut = null;
	      if(_a.startsWith("Windows")){
	    	  fileOut = new FileOutputStream("D:/fileImgSk/SALESPENJFAKTUR_"+salesID+".xls");
	      }else{
	    	  fileOut = new FileOutputStream("/fileImgSk/SALESPENJFAKTUR_"+salesID+".xls");
	      }
	     
	      _wb.write(fileOut);
		    fileOut.close();
		return "SALESPENJFAKTUR_"+salesID+".xls";
	}
	
	// PRINT TRIP
	public static String printTrip(List<TripEntity> _entList,String _dari,String _sampai) throws Exception{
		HSSFWorkbook _wb = new HSSFWorkbook();
		HSSFSheet _sheet = _wb.createSheet("Trip");
		int _row = 2;
	    HSSFRow row = _sheet.createRow((short)_row);
	  //HEADER
	    HSSFRichTextString _s = new HSSFRichTextString("No\nUrut");
	    row.createCell((short) 0).setCellValue(_s);
	    _s = new HSSFRichTextString("Kode Barang");
	    row.createCell((short) 1).setCellValue(_s);
	    _s = new HSSFRichTextString("Nama Barang");
	    row.createCell((short) 2).setCellValue(_s);
	    _s = new HSSFRichTextString("Qty Karton");
	    row.createCell((short) 3).setCellValue(_s);
	    
	    _s = new HSSFRichTextString("Jumlah Isi Qty");
	    row.createCell((short) 4).setCellValue(_s);
	    _s = new HSSFRichTextString("Harga Beli Satuan ($)");
	    row.createCell((short) 5).setCellValue(_s);
	    _s = new HSSFRichTextString("Jumlah Harga Bruto ($)");
	    row.createCell((short) 6).setCellValue(_s);
	    _s = new HSSFRichTextString("Discount 0.00%");
	    row.createCell((short) 7).setCellValue(_s);
	    _s = new HSSFRichTextString("Jumlah Harga Netto ($)");
	    row.createCell((short) 8).setCellValue(_s);
	    _s = new HSSFRichTextString("Kurs Beli");
	    row.createCell((short) 9).setCellValue(_s);
	    _s = new HSSFRichTextString("Jumlah Harga Netto (Rp)");
	    row.createCell((short) 10).setCellValue(_s);
	    _s = new HSSFRichTextString("Harga Beli Satuan (Rp)");
	    row.createCell((short) 11).setCellValue(_s);
	    row = _sheet.createRow((short)0);
	     _s = new HSSFRichTextString("LAPORAN TRIP PERIODE "+_dari+" s/d "+_sampai);
	     row.createCell((short) 0).setCellValue(_s);
	     Region _re = new Region();
	     _re.setColumnFrom((short)0);
	     _re.setColumnTo((short)9);
	     _sheet.addMergedRegion(_re);
	     SimpleDateFormat dt2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	     row = _sheet.createRow((short)1);
	     _s = new HSSFRichTextString("PRINTED BY : " + " "+dt2.format(new Date()));
	     row.createCell((short) 0).setCellValue(_s);
	     _re.setRowFrom((short)1);
	     _re.setRowTo((short)1);
	     _sheet.addMergedRegion(_re);
	   
	    //END HEADER
		int _tot = 0;
		BigDecimal _totQty = BigDecimal.ZERO;
		BigDecimal _totNettoVta = BigDecimal.ZERO;
		BigDecimal _totNettoIdr = BigDecimal.ZERO;
		for (final TripEntity _ent: _entList) {
			
    	  	List<DetailPembelianEntity> _detBeli = new DetPembelianPersistence().getByTripList(_ent);
	    	  	for (final DetailPembelianEntity _entBeli: _detBeli){
	    	  		_tot = _tot+1;
	        	  	_row = _row+1;
	    	  		 row = _sheet.createRow((short)_row);
	   	    	  _s = new HSSFRichTextString(String.valueOf(_tot)); 
	   	    	  row.createCell((short) 0).setCellValue(_s);
	   	    	  //kode barang
	   	    	  _s = new HSSFRichTextString(String.valueOf(_entBeli.getStockEnt().getProductEnt().getCode())); 
	   	    	  row.createCell((short) 1).setCellValue(_s);
	   	    	  //nama barang
	   	    	  _s = new HSSFRichTextString(String.valueOf(_entBeli.getStockEnt().getProductEnt().getName())); 
	 	    	  row.createCell((short) 2).setCellValue(_s);
	 	    	  //QTY Karton
	 	    	 BigDecimal _qtyCtn = _entBeli.getQtyBeliCtn();
	 	    	  _s = new HSSFRichTextString(String.valueOf(_qtyCtn)); 
	 	    	  row.createCell((short) 3).setCellValue(_s);
	 	    	  
	 	    	  _totQty = _totQty.add(_qtyCtn);
		    	  //jumlah isi qty Pcs
	 	    	  BigDecimal _totQtyPcs = _entBeli.getQtyBeliCtn().multiply(_entBeli.getStockEnt().getProductEnt().getIsiPcs().multiply(_entBeli.getStockEnt().getProductEnt().getIsiCtn()));
		    	  _s = new HSSFRichTextString(String.valueOf(_totQtyPcs)); 
		    	  row.createCell((short) 4).setCellValue(_s);
		    	  //harga beli satuan $
		    	  _s = new HSSFRichTextString(String.valueOf(_entBeli.getHargaBeliCtnVta())); 
		    	  row.createCell((short) 5).setCellValue(_s);
		    	  //jumlah harga bruto $
		    	  _s = new HSSFRichTextString(String.valueOf(_entBeli.getTotHargaBrutB_Vta())); 
		    	  row.createCell((short) 6).setCellValue(_s);
		    	  //Discount
		    	  _s = new HSSFRichTextString(String.valueOf(_entBeli.getDisc())); 
		    	  row.createCell((short) 7).setCellValue(_s);
		    	  //Jumlah harga netto $
		    	  BigDecimal _hrgNettoVta = _entBeli.getTotHargaNettB_Vta();
		    	  _s = new HSSFRichTextString(String.valueOf(_hrgNettoVta)); 
		    	  row.createCell((short) 8).setCellValue(_s);
		    	  _totNettoVta = _totNettoVta.add(_hrgNettoVta);
		    	  //Kurs Beli
		    	  _s = new HSSFRichTextString(String.valueOf(_ent.getCurrencyIDR())); 
		    	  row.createCell((short) 9).setCellValue(_s);
		    	  //Jumlah harga netto IDR
		    	  BigDecimal _hrgNettoIdr = _entBeli.getTotHargaNettB_Idr();
		    	  _s = new HSSFRichTextString(String.valueOf(_hrgNettoIdr)); 
		    	  row.createCell((short) 10).setCellValue(_s);
		    	  _totNettoIdr = _totNettoIdr.add(_hrgNettoIdr);
		    	  //Jumlah Harga Beli Satuan IDR
		    	 
		    	  _s = new HSSFRichTextString(String.valueOf(_entBeli.getHargaBeliCtnIdr())); 
		    	  row.createCell((short) 11).setCellValue(_s);
		    	  
	    	  	}
			}
		//TOTAL ALL
		_row = _row+1;
		row = _sheet.createRow((short)_row);
		_s = new HSSFRichTextString("TOTAL"); 
  	  	row.createCell((short) 2).setCellValue(_s);
  	  	_s = new HSSFRichTextString(String.valueOf(_totQty)); 
	  	row.createCell((short) 3).setCellValue(_s);
  	  	_s = new HSSFRichTextString(String.valueOf(_totNettoVta)); 
	  	row.createCell((short) 8).setCellValue(_s);
	  	_s = new HSSFRichTextString(ExportToPdf.convertRp(_totNettoIdr)); 
	  	row.createCell((short) 10).setCellValue(_s);
	  	//END TOTAL
		  _wb.getSheetAt(0).autoSizeColumn((short) 0);
	      _wb.getSheetAt(0).autoSizeColumn((short) 1);
	      _wb.getSheetAt(0).autoSizeColumn((short) 2);
	      _wb.getSheetAt(0).autoSizeColumn((short) 3);
	      _wb.getSheetAt(0).autoSizeColumn((short) 4);
	      _wb.getSheetAt(0).autoSizeColumn((short) 5);
	      _wb.getSheetAt(0).autoSizeColumn((short) 6);
	      _wb.getSheetAt(0).autoSizeColumn((short) 7);
	      _wb.getSheetAt(0).autoSizeColumn((short) 8);
	      _wb.getSheetAt(0).autoSizeColumn((short) 9);
	      _wb.getSheetAt(0).autoSizeColumn((short) 10);
	      _wb.getSheetAt(0).autoSizeColumn((short) 11);
	      String _a=	System.getProperty("os.name");
	      FileOutputStream fileOut = null;
	      if(_a.startsWith("Windows")){
	    	  fileOut = new FileOutputStream("D:/fileImgSk/Trip_"+_dari+"_"+_sampai+".xls");
	      }else{
	    	  fileOut = new FileOutputStream("/fileImgSk/Trip_"+_dari+"_"+_sampai+".xls");
	      }
	     
	      _wb.write(fileOut);
		    fileOut.close();
		
		return "Trip_"+_dari+"_"+_sampai+".xls";
	}
	// PRINT PENJUALAN EXCEL
	public static String printPenjualan(List<PenjualanEntity> _entList,String _dari,String _sampai) throws Exception{
		HSSFWorkbook _wb = new HSSFWorkbook();
		HSSFSheet _sheet = _wb.createSheet("Penjualan");
		int _row = 2;
	    HSSFRow row = _sheet.createRow((short)_row);
	  //HEADER
	    HSSFRichTextString _s = new HSSFRichTextString("No\nUrut");
	    row.createCell((short) 0).setCellValue(_s);
	    _s = new HSSFRichTextString("No Trip");
	    row.createCell((short) 1).setCellValue(_s);
	    _s = new HSSFRichTextString("Kode Barang");
	    row.createCell((short) 2).setCellValue(_s);
	    _s = new HSSFRichTextString("No Pabrik");
	    row.createCell((short) 3).setCellValue(_s);
	    
	    _s = new HSSFRichTextString("Tanggal Order");
	    row.createCell((short) 4).setCellValue(_s);
	    _s = new HSSFRichTextString("Jumlah CTN");
	    row.createCell((short) 5).setCellValue(_s);
	    _s = new HSSFRichTextString("Nama Barang");
	    row.createCell((short) 6).setCellValue(_s);
	    _s = new HSSFRichTextString("Nomor Order");
	    row.createCell((short) 7).setCellValue(_s);
	    _s = new HSSFRichTextString("Nomor Faktur");
	    row.createCell((short) 8).setCellValue(_s);
	    _s = new HSSFRichTextString("Nama Customer");
	    row.createCell((short) 9).setCellValue(_s);
	    _s = new HSSFRichTextString("Harga Satuan / CTN");
	    row.createCell((short) 10).setCellValue(_s);
	    _s = new HSSFRichTextString("Total Harga Netto");
	    row.createCell((short) 11).setCellValue(_s);
	    row = _sheet.createRow((short)0);
	     _s = new HSSFRichTextString("LAPORAN Penjualan PERIODE "+_dari+" s/d "+_sampai);
	     row.createCell((short) 0).setCellValue(_s);
	     Region _re = new Region();
	     _re.setColumnFrom((short)0);
	     _re.setColumnTo((short)9);
	     _sheet.addMergedRegion(_re);
	     SimpleDateFormat dt2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	     row = _sheet.createRow((short)1);
	     _s = new HSSFRichTextString("PRINTED BY : " + " "+dt2.format(new Date()));
	     row.createCell((short) 0).setCellValue(_s);
	     _re.setRowFrom((short)1);
	     _re.setRowTo((short)1);
	     _sheet.addMergedRegion(_re);
	   
	    //END HEADER
		int _tot = 0;
		BigDecimal _totQty = BigDecimal.ZERO;
		BigDecimal _totNettoIdr = BigDecimal.ZERO;
		for (final PenjualanEntity _ent: _entList) {
			
    	  	List<DetailPenjualanEntity> _detJual = new DetPenjualanPersistence().getByPenjualanList(_ent);
	    	  	for (final DetailPenjualanEntity _entJual: _detJual){
	    	  		_tot = _tot+1;
	        	  	_row = _row+1;
	    	  		 row = _sheet.createRow((short)_row);
	   	    	  _s = new HSSFRichTextString(String.valueOf(_tot)); 
	   	    	  row.createCell((short) 0).setCellValue(_s);
	   	    	  //Nomor Trip
	   	    	  _s = new HSSFRichTextString(String.valueOf(_entJual.getStockEnt().getTripNumStok())); 
	   	    	  row.createCell((short) 1).setCellValue(_s);
	   	    	  //Kode barang
	   	    	  _s = new HSSFRichTextString(String.valueOf(_entJual.getStockEnt().getProductEnt().getCode())); 
	 	    	  row.createCell((short) 2).setCellValue(_s);
	 	    	  //No Pabrik
	 	    	  _s = new HSSFRichTextString(String.valueOf(_entJual.getStockEnt().getProductEnt().getPartNumb())); 
	 	    	  row.createCell((short) 3).setCellValue(_s);
	 	    	  //Tanggal Order
	 	    	  _s = new HSSFRichTextString(String.valueOf(_ent.getOrderDate())); 
	 	    	  row.createCell((short) 4).setCellValue(_s);
		    	  //Jumlah Karton
	 	    	 BigDecimal _qtyCtn = _entJual.getTotQtyJualCtn();
		    	  _s = new HSSFRichTextString(String.valueOf(_qtyCtn)); 
		    	  row.createCell((short) 5).setCellValue(_s);
		    	  _totQty = _totQty.add(_qtyCtn);
		    	  //Nama Barang
		    	  _s = new HSSFRichTextString(String.valueOf(_entJual.getStockEnt().getProductEnt().getName())); 
		    	  row.createCell((short) 6).setCellValue(_s);
		    	//Nomor Faktur
		    	  _s = new HSSFRichTextString(String.valueOf(_ent.getOrderNumb())); 
		    	  row.createCell((short) 7).setCellValue(_s);
		    	  //Nomor Faktur
		    	  _s = new HSSFRichTextString(String.valueOf(_ent.getFakturNumb())); 
		    	  row.createCell((short) 8).setCellValue(_s);
		    	  //Nama Customer
		    	  _s = new HSSFRichTextString(String.valueOf(_ent.getCustomerEnt().getName())); 
		    	  row.createCell((short) 9).setCellValue(_s);
		    	  //Harga Satuan
		    	  _s = new HSSFRichTextString(String.valueOf(_entJual.getHjCtn())); 
		    	  row.createCell((short) 10).setCellValue(_s);
		    	  //Total Harga Netto
		    	  //BigDecimal _nettoIdr = _entJual.getTotJualNettoIdr();
		    	  BigDecimal _cepe = new BigDecimal(100);
					BigDecimal _bruto = _entJual.getTotJualBrutoIdr();
					
					BigDecimal _discPers = _entJual.getDiscPersen().divide(_cepe,3,RoundingMode.HALF_UP);
					BigDecimal _totDisc = _bruto.multiply(_discPers);
					BigDecimal _brutoAfterDisc = _bruto.subtract(_totDisc);
					
					BigDecimal _ppnPers = _ent.getPpn().divide(_cepe,3,RoundingMode.HALF_UP);
					BigDecimal _totPpn = _brutoAfterDisc.multiply(_ppnPers);
					BigDecimal _nettoIdr = _brutoAfterDisc.add(_totPpn);
		    	  
		    	  _s = new HSSFRichTextString(String.valueOf(_nettoIdr).trim()); 
		    	  row.createCell((short) 11).setCellValue(_s);
		    	  _totNettoIdr = _totNettoIdr.add(_nettoIdr);
	    	  	}
			}
		//TOTAL ALL
		_row = _row+1;
		row = _sheet.createRow((short)_row);
		_s = new HSSFRichTextString("TOTAL"); 
  	  	row.createCell((short) 2).setCellValue(_s);
  	  	_s = new HSSFRichTextString(String.valueOf(_totQty)); 
	  	row.createCell((short) 5).setCellValue(_s);
  	  	_s = new HSSFRichTextString(String.valueOf(_totNettoIdr)); 
	  	row.createCell((short) 11).setCellValue(_s);
	  	
	  	//END TOTAL
		  _wb.getSheetAt(0).autoSizeColumn((short) 0);
	      _wb.getSheetAt(0).autoSizeColumn((short) 1);
	      _wb.getSheetAt(0).autoSizeColumn((short) 2);
	      _wb.getSheetAt(0).autoSizeColumn((short) 3);
	      _wb.getSheetAt(0).autoSizeColumn((short) 4);
	      _wb.getSheetAt(0).autoSizeColumn((short) 5);
	      _wb.getSheetAt(0).autoSizeColumn((short) 6);
	      _wb.getSheetAt(0).autoSizeColumn((short) 7);
	      _wb.getSheetAt(0).autoSizeColumn((short) 8);
	      _wb.getSheetAt(0).autoSizeColumn((short) 9);
	      _wb.getSheetAt(0).autoSizeColumn((short) 10);
	      _wb.getSheetAt(0).autoSizeColumn((short) 11);
	      _wb.getSheetAt(0).autoSizeColumn((short) 12);
	      String _a=	System.getProperty("os.name");
	      FileOutputStream fileOut = null;
	      if(_a.startsWith("Windows")){
	    	  fileOut = new FileOutputStream("D:/fileImgSk/Penjualan_"+_dari+"_"+_sampai+".xls");
	      }else{
	    	  fileOut = new FileOutputStream("/fileImgSk/Penjualan_"+_dari+"_"+_sampai+".xls");
	      }
	     
	      _wb.write(fileOut);
		    fileOut.close();
		
		return "Penjualan_"+_dari+"_"+_sampai+".xls";
	}
	
	
	public static String custPrintFaktur(String _by, String dateTo,String dateFrom, String custcode, String custName,String _userName,String discMin,String discMax) throws Exception{

		ResultSet rs = null;
		Statement st = null;
		 String _a=	System.getProperty("os.name");
		 String myDriver = "org.gjt.mm.mysql.Driver";
	      String myUrl = "jdbc:mysql://localhost/sk-gudang";
	      Class.forName(myDriver);
	      Connection conn = null;
	      if(_a.startsWith("Windows")){
	    	  conn = (Connection) DriverManager.getConnection(myUrl, "root", "");
	      }else{
	    	  conn = (Connection) DriverManager.getConnection(myUrl, "radot", "p@ssw0rd");
	      }
	      
	      String query = "SELECT p.tanggal_order ,p.no_order ,c.cust_name ,c.cust_city ,s.salesman_code ,p.tot_penjualan_nett_idr, dj.disc_persen as disc, p.ppn as ppnJual ,p.tot_penjualan_bruto_idr ,p.tot_disc_penjualan ,avg(dj.disc_persen) as avgdiscpersen FROM sk_penjualan p join sk_salesman s on p.salesman_code_fk = s.rec_id join sk_customers c on p.customer_code_fk = c.rec_id join sk_detail_jual dj on p.rec_id = dj.penjualan_fk";
   		 query = query+" WHERE customer_code_fk = "+_by+" and tanggal_order between '"+dateFrom+"' and '"+dateTo+"' and dj.disc_persen between '"+discMin+"' and '"+discMax+"' group by dj.penjualan_fk ORDER BY tanggal_order DESC ";
	      st = conn.createStatement();
	      rs = st.executeQuery(query);
	      HSSFWorkbook _wb = new HSSFWorkbook();
	      	
			HSSFSheet _sheet = _wb.createSheet(custcode);
			
			int _row = 2;
		    HSSFRow row = _sheet.createRow((short)_row);
		    //HEADER
		    HSSFRichTextString _s = new HSSFRichTextString("No\nUrut");
		    row.createCell((short) 0).setCellValue(_s);
		    _s = new HSSFRichTextString("Tanggal");
		    row.createCell((short) 1).setCellValue(_s);
		    _s = new HSSFRichTextString("No. Bukti");
		    row.createCell((short) 2).setCellValue(_s);
		    _s = new HSSFRichTextString("Salesman");
		    row.createCell((short) 3).setCellValue(_s);
		    _s = new HSSFRichTextString("Nama Customer");
		    row.createCell((short) 4).setCellValue(_s);
		    _s = new HSSFRichTextString("Kota");
		    row.createCell((short) 5).setCellValue(_s);
		    _s = new HSSFRichTextString("Bruto (Rp)");
		    row.createCell((short) 6).setCellValue(_s);
		    _s = new HSSFRichTextString("Netto (Rp)");
		    row.createCell((short) 7).setCellValue(_s);
		    _s = new HSSFRichTextString(" Rp ---");
		    row.createCell((short) 8).setCellValue(_s);
		    _s = new HSSFRichTextString("Disc --");
		    row.createCell((short) 9).setCellValue(_s);
		    //"LAPORAN PENJUALAN CUSTOMER PERIODE "+dateFrom+" s/d "+dateTo
		    //END HEADER
		    row = _sheet.createRow((short)0);
		     _s = new HSSFRichTextString("LAPORAN PENJUALAN CUSTOMER PERIODE "+dateFrom+" s/d "+dateTo);
		     row.createCell((short) 0).setCellValue(_s);
		     Region _re = new Region();
		     _re.setColumnFrom((short)0);
		     _re.setColumnTo((short)9);
		     _sheet.addMergedRegion(_re);
		     SimpleDateFormat dt2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		     row = _sheet.createRow((short)1);
		     _s = new HSSFRichTextString("PRINTED BY : " + _userName.toUpperCase() +" "+dt2.format(new Date()));
		     row.createCell((short) 0).setCellValue(_s);
		     _re.setRowFrom((short)1);
		     _re.setRowTo((short)1);
		     _sheet.addMergedRegion(_re);
		    int _tot = 0;
		    SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
		    BigDecimal _totBrut = BigDecimal.ZERO;
		      BigDecimal _totNett =  BigDecimal.ZERO;
		      BigDecimal _totTotDisc = BigDecimal.ZERO;
		      BigDecimal _totTotDiscPersen = BigDecimal.ZERO;
	      while (rs.next())
	      {		_tot = _tot+1;
	    	  	_row = _row+1;
	    	  row = _sheet.createRow((short)_row);
	    	  _s = new HSSFRichTextString(String.valueOf(_tot)); 
	    	  row.createCell((short) 0).setCellValue(_s);
	    	  
	    	  _s = new HSSFRichTextString(dt1.format(rs.getDate("tanggal_order")));
			  row.createCell((short) 1).setCellValue(_s);
			  
			  _s = new HSSFRichTextString(rs.getString("no_order"));
			  row.createCell((short) 2).setCellValue(_s);
			
			  _s = new HSSFRichTextString(rs.getString("salesman_code"));
			  row.createCell((short) 3).setCellValue(_s);
			  
			  _s = new HSSFRichTextString(rs.getString("cust_name"));
			  row.createCell((short) 4).setCellValue(_s);
			
			  _s = new HSSFRichTextString(rs.getString("cust_city"));
			  row.createCell((short) 5).setCellValue(_s);
			 
			  BigDecimal _bruto = rs.getBigDecimal("tot_penjualan_bruto_idr");
				//BigDecimal _nett = rs.getBigDecimal("tot_penjualan_nett_idr");
				BigDecimal _discA = rs.getBigDecimal("disc");
				BigDecimal _cepe = new BigDecimal(100);
				BigDecimal _disc = _discA.divide(_cepe,100,RoundingMode.HALF_UP);
				BigDecimal _totDisc = _bruto.multiply(_disc);
				BigDecimal _brutoAfterDiscIdr = _bruto.subtract(_totDisc);
				BigDecimal _ppnA = rs.getBigDecimal("ppnJual");
				BigDecimal _ppn = _ppnA.divide(_cepe,100,RoundingMode.HALF_UP);
				BigDecimal _totPpn = _brutoAfterDiscIdr.multiply(_ppn);
				BigDecimal _nett = _brutoAfterDiscIdr.add(_totPpn);
				//BigDecimal _totDisc = rs.getBigDecimal("tot_disc_penjualan");
				BigDecimal _totDispersen = rs.getBigDecimal("avgdiscpersen");
				_totBrut = _totBrut.add(_bruto);
				_totNett = _totNett.add(_nett);
				_totTotDisc = _totTotDisc.add(_totDisc);
				_totTotDiscPersen = _totTotDiscPersen.add(_totDispersen);
			  _s = new HSSFRichTextString(ExportToPdf.convertRp(_bruto));
			  row.createCell((short) 6).setCellValue(_s);
			  
			  _s = new HSSFRichTextString(ExportToPdf.convertRp(_nett));
			  row.createCell((short) 7).setCellValue(_s);
			  
			  _s = new HSSFRichTextString(ExportToPdf.convertRp(_totDisc));
			  row.createCell((short) 8).setCellValue(_s);
			  
			  _s = new HSSFRichTextString(_totDispersen.divide(BigDecimal.ONE, 2, RoundingMode.HALF_UP).toString()+" %");
			  row.createCell((short) 9).setCellValue(_s);
	      }
	     
	      _wb.getSheetAt(0).autoSizeColumn((short) 0);
	      _wb.getSheetAt(0).autoSizeColumn((short) 1);
	      _wb.getSheetAt(0).autoSizeColumn((short) 2);
	      _wb.getSheetAt(0).autoSizeColumn((short) 3);
	      _wb.getSheetAt(0).autoSizeColumn((short) 4);
	      _wb.getSheetAt(0).autoSizeColumn((short) 5);
	      _wb.getSheetAt(0).autoSizeColumn((short) 6);
	      _wb.getSheetAt(0).autoSizeColumn((short) 7);
	      _wb.getSheetAt(0).autoSizeColumn((short) 8);
	      _wb.getSheetAt(0).autoSizeColumn((short) 9);
	      row = _sheet.createRow((short)_row+1);
	      _s = new HSSFRichTextString("Total");
	      row.createCell((short) 5).setCellValue(_s);
	      _s = new HSSFRichTextString(ExportToPdf.convertRp(_totBrut));
	      row.createCell((short) 6).setCellValue(_s);
	      
	      _s = new HSSFRichTextString(ExportToPdf.convertRp(_totNett));
	      row.createCell((short) 7).setCellValue(_s);
	      _s = new HSSFRichTextString(ExportToPdf.convertRp(_totTotDisc));
	      row.createCell((short) 8).setCellValue(_s);
	      BigDecimal _avgTotDiscPerser = BigDecimal.ZERO;
	      if(_totTotDiscPersen != BigDecimal.ZERO){
	    	  _avgTotDiscPerser = _totTotDiscPersen.divide(BigDecimal.valueOf(Long.valueOf(_tot)), 2,RoundingMode.HALF_UP);
	      }
	      _s = new HSSFRichTextString(_avgTotDiscPerser.toString()+" %");
	      row.createCell((short) 9).setCellValue(_s);
	      FileOutputStream fileOut = null;
	      if(_a.startsWith("Windows")){
	    	  fileOut = new FileOutputStream("D:/fileImgSk/CUSTPENJFAKTUR_"+custcode+".xls");
	      }else{
	    	  fileOut = new FileOutputStream("/fileImgSk/CUSTPENJFAKTUR_"+custcode+".xls");
	      }
	     
	      _wb.write(fileOut);
		    fileOut.close();
		return "CUSTPENJFAKTUR_"+custcode+".xls";
	}
	
	
	/*public static void main(String[] args)throws Exception {
		ExportToExcel.custPrintFaktur("7", "2100/01/01", "2016/01/01", "006", "NUSA", "asdasd", "0", "100");
	}*/
}
