package org.radot.json.handlers;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.radot.consts.SessionKeysConst;
import org.radot.hibernate.entities.UserEntity;
import org.radot.json.beans.DetPenjualanResult;
import org.radot.json.beans.PageUserParam;
import org.radot.json.beans.PenjualanSelectParam;
import org.radot.json.beans.UserItem;
import org.radot.json.beans.UserResult;
import org.radot.json.servlet.JsonServletHandler;
import org.radot.utils.ExportToPdf;

import com.mysql.jdbc.Connection;

public class JsonReportingDate extends JsonServletHandler<PenjualanSelectParam, DetPenjualanResult> {

	public JsonReportingDate(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}
	
	@SuppressWarnings("null")
	@Override()
	public void process() throws Throwable {
		
		UserItem _item;
		final List<UserItem> _items = new ArrayList<UserItem>();
//		int limit = 10;
		String acttype = this.param.getActiontype();
		String valPenj = this.param.getDateFrom();
		String _name = this.param.getByValuePenj();
		
		final HttpSession _session = this.request.getSession();
		
		
		SimpleDateFormat _dt = new SimpleDateFormat("MM - yyyy");
		Date _date = _dt.parse(valPenj);
		Calendar _cal = new GregorianCalendar();
		_cal.setTime(_date);
		_cal.add(Calendar.MONTH, 1);
		Date _fixDate = new Date(_cal.getTimeInMillis());
		SimpleDateFormat _dt2 = new SimpleDateFormat("yyyy-MM-dd");
		String k = _dt2.format(_fixDate)+ " 00:00:00";
		String j[] = valPenj.split(" - ");
		String mess = PrintReportDate(_name.replace("User : ", ""), k, j[0], j[1], _name.replace("User : ", ""));
		this.result.setCode(0);
		this.result.setMessage(mess);

	}
	
	public static String PrintReportDate(String _by, String dateTo, String bulan, String tahun,String _userName) throws Exception{

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
	      
//	      String query = "SELECT s.rec_id, s.trip_num_stok, pr.prod_code,pr.hjStd_Ctn, pr.isi_ctn, pr.isi_pcs, pr.prod_partNumb, pr.prod_name,s.stok_ctn, SUM(dtb.qty_beli_ctn) as totalBeli, SUM(dtj.TotQtyJ_Ctn) as totalJual, (s.stok_ctn + SUM(dtb.qty_beli_ctn) - SUM(dtj.TotQtyJ_Ctn)) as hasilnya FROM sk_stock s LEFT join  sk_detail_beli dtb on s.rec_id = dtb.Stock_fk LEFT JOIN  sk_detail_jual dtj on s.rec_id = dtj.stock_fk LEFT JOIN sk_trip t on dtb.Trip_fk = t.rec_id LEFT JOIN sk_penjualan p on dtj.penjualan_fk = p.rec_id LEFT JOIN SK_PRODUCT pr on s.product_fk = pr.rec_id";
//   		  query = query+ " WHERE t.trip_date BETWEEN '"+dateTo+"' AND now() and p.tanggal_order BETWEEN '"+dateTo+"' AND now() GROUP BY s.rec_id";
//	      query = query+ " HAVING s.stok_ctn + SUM(dtb.qty_beli_ctn) - SUM(dtj.TotQtyJ_Ctn) > 0";
	      String query =  "select hargaBeli_CtnIdr, hasilnya , fix.rec_id, trip_num_stok, stok_ctn, product_fk, pr.prod_code,pr.hjStd_Ctn, pr.isi_ctn, pr.isi_pcs, pr.prod_partNumb, pr.prod_name from"+
	      				  " (select hargaBeli_CtnIdr,  (totalBeli-totalJual) as hasilnya , rec_id, trip_num_stok, stok_ctn, product_fk from ("+
			    		  " select hargaBeli_CtnIdr,  COALESCE(totJual,0) as totalJual , COALESCE(totBeli,0) as totalBeli, st.rec_id, st.trip_num_stok, st.stok_ctn, st.product_fk from sk_stock st left join"+ 
			    		  " (select  sum(dj.TotQtyJ_Ctn) as totJual, dj.rec_id , dj.stock_fk from sk_penjualan p"+
			    		  " left join sk_detail_jual dj on p.rec_id = dj.penjualan_fk"+
			    		  " where p.tanggal_order BETWEEN '2013-11-30' and 'DATETOJUAL' and dj.rec_id is not null  group by dj.stock_fk) as dtJualSel" + 
			    		  " on st.rec_id = dtJualSel.stock_fk left join" +  
			    		  " (SELECT hargaBeli_CtnIdr,SUM(dtb.qty_beli_ctn) as totBeli, dtb.stock_fk, dtb.rec_id FROM sk_trip t"+
			    		  " join sk_detail_beli dtb on t.rec_id = dtb.trip_fk WHERE t.trip_date BETWEEN '2013-11-30' and 'DATETOBELI'  group by dtb.stock_fk) as dtBeliSel"+
			    		  " on st.rec_id = dtBeliSel.stock_fk" +
			    		  " where product_fk is not null"+
			    		  " ) as hasilsel) as fix join sk_product pr on fix.product_fk = pr.rec_id"+
			    		  " where hasilnya > 0";
	      
	      query = query.replace("DATETOJUAL", dateTo);
	      query = query.replace("DATETOBELI", dateTo);
	      
	      //System.out.println(query);
   		  st = conn.createStatement();
	      rs = st.executeQuery(query);
	      HSSFWorkbook _wb = new HSSFWorkbook();
	      	
			HSSFSheet _sheet = _wb.createSheet(tahun+"_"+bulan);
			int _row = 3;
		    HSSFRow row = _sheet.createRow((short)_row);
		    //HEADER
		    HSSFRichTextString _s = new HSSFRichTextString("No\nUrut");
		    row.createCell((short) 0).setCellValue(_s);
		    
		    _s = new HSSFRichTextString("Kode Barang ");
		    row.createCell((short) 1).setCellValue(_s);
		    
		    _s = new HSSFRichTextString("No Pabrik");
		    row.createCell((short) 2).setCellValue(_s);
		    
		    _s = new HSSFRichTextString("Nama Barang");
		    row.createCell((short) 3).setCellValue(_s);
		    
		    _s = new HSSFRichTextString("Harga Beli(Idr/PCS)");
		    row.createCell((short) 4).setCellValue(_s);
		    
		    _s = new HSSFRichTextString("Harga Jual(PCS)");
		    row.createCell((short) 5).setCellValue(_s);
		    
		    _s = new HSSFRichTextString("No Trip");
		    row.createCell((short) 6).setCellValue(_s);
		    
		    _s = new HSSFRichTextString("Sisa stock CTN");
		    row.createCell((short) 7).setCellValue(_s);
		    
		    _s = new HSSFRichTextString("Sisa stock PCS");
		    row.createCell((short) 8).setCellValue(_s);
		    //END HEADER
		    row = _sheet.createRow((short)0);
		     _s = new HSSFRichTextString("Sisa Stock "+bulan +" "+tahun);
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
		    HSSFSheet _sheet2 = _wb.createSheet(tahun+"_"+bulan+"_32000");
	      while (rs.next())
	      {		_tot = _tot+1;
	    	  	_row = _row+1;
	    	  	if(_row > 32000){
	    	  		System.out.println(_row); 
	    	  		//_row = _row-32000;
	    	  		int _intRo2 = _row;
	    	  		_intRo2 = _intRo2-32000;
	    	  		row = _sheet2.createRow((short)_intRo2);
	    	  	}else{
	    	  		row = _sheet.createRow((short)_row);
	    	  	}
	    	  
	    	  _s = new HSSFRichTextString(String.valueOf(_tot)); 
	    	  row.createCell((short) 0).setCellValue(_s);
	    	  
	    	  _s = new HSSFRichTextString(rs.getString("prod_code"));
			  row.createCell((short) 1).setCellValue(_s);
			  
			  _s = new HSSFRichTextString(rs.getString("prod_partNumb"));
			  row.createCell((short) 2).setCellValue(_s);
			
			  _s = new HSSFRichTextString(rs.getString("prod_name"));
			  row.createCell((short) 3).setCellValue(_s);
			  //format rupiah
			  DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
		      DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
		      formatRp.setCurrencySymbol("Rp ");
		        formatRp.setMonetaryDecimalSeparator('.');
		        formatRp.setGroupingSeparator(',');
		        kursIndonesia.setDecimalFormatSymbols(formatRp);
		      // end rupiah
			  //harga BELI
			  
			  BigDecimal _hargaPCSBeli = null;
				String _cBeli = "";
				try {
					
					_hargaPCSBeli  = rs.getBigDecimal("hargaBeli_CtnIdr").divide(rs.getBigDecimal("isi_pcs"),2,RoundingMode.HALF_UP).divide(rs.getBigDecimal("isi_ctn"),2,RoundingMode.HALF_UP);
					

			     
					_cBeli = kursIndonesia.format(_hargaPCSBeli.doubleValue());
			    } catch (Exception e) {
					e.printStackTrace();
					_cBeli =  "0";
				}
			  
			  _s = new HSSFRichTextString(_cBeli);
			  row.createCell((short) 4).setCellValue(_s);
			//end Harga BELI
			  
			  
			  
			  //harga JUAL
			  
			  BigDecimal _hargaPCS = null;
				String _c = "";
				try {
					
					_hargaPCS  = rs.getBigDecimal("hjStd_Ctn").divide(rs.getBigDecimal("isi_pcs"),2,RoundingMode.HALF_UP).divide(rs.getBigDecimal("isi_ctn"),2,RoundingMode.HALF_UP);
					

			        
			        _c = kursIndonesia.format(_hargaPCS.doubleValue());
			    } catch (Exception e) {
					e.printStackTrace();
					_c =  "0";
				}
			  
			  _s = new HSSFRichTextString(_c);
			  row.createCell((short) 5).setCellValue(_s);
			//end Harga Jual
			  _s = new HSSFRichTextString(rs.getString("trip_num_stok"));
			  row.createCell((short) 6).setCellValue(_s);
			 
			  BigDecimal _totStokPcs = BigDecimal.ZERO;
			  int _intStockPcs = 0;
				try {
					BigDecimal _isiPcs = rs.getBigDecimal("isi_pcs");
					BigDecimal _isiCtn = rs.getBigDecimal("isi_ctn");
					BigDecimal _stokCtn = rs.getBigDecimal("hasilnya");
					 _totStokPcs = _stokCtn.multiply(_isiCtn).multiply(_isiPcs);
					 _intStockPcs = _totStokPcs.intValue();
				} catch (Exception e) {
					e.printStackTrace();
				}
			  _s = new HSSFRichTextString(rs.getString("hasilnya"));
			  row.createCell((short) 7).setCellValue(_s);
			  
			  _s = new HSSFRichTextString(String.valueOf(_intStockPcs));
			  row.createCell((short) 8).setCellValue(_s);
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
	     
	    
	      FileOutputStream fileOut = null;
	      final String nameFile = "SISASTOCK_"+bulan+".xls";
	      if(_a.startsWith("Windows")){
	    	  fileOut = new FileOutputStream("D:/fileImgSk/"+nameFile);
	      }else{
	    	  fileOut = new FileOutputStream("/fileImgSk/"+nameFile);
	      }
	     
	      _wb.write(fileOut);
		    fileOut.close();
		  //  System.out.println(nameFile);
		return nameFile;
	}

}
