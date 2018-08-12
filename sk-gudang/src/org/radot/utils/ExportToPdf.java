package org.radot.utils;

import java.io.FileNotFoundException;
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
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.radot.hibernate.entities.BaseEntity;
import org.radot.hibernate.entities.DetailPembelianEntity;
import org.radot.hibernate.entities.TripEntity;
import org.radot.hibernate.persistences.DetPembelianPersistence;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.jdbc.Connection;

public class ExportToPdf {
	public static String _osName = 	System.getProperty("os.name");
	public static String convertRp (BigDecimal _h){
		String _c;
		DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("");
        formatRp.setMonetaryDecimalSeparator('.');
        formatRp.setGroupingSeparator(',');
        kursIndonesia.setDecimalFormatSymbols(formatRp);
        _c = kursIndonesia.format(_h.doubleValue());
        return _c;
	}
	public static String stockCTnexcel(String id) throws Exception{
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
	      
	      String query = "SELECT * FROM sk_detail_beli_view d join sk_stock s on d.stock_fk = s.rec_id join sk_product p on s.product_fk = p.rec_id  join sk_trip t on d.trip_fk = t.rec_id where s.stok_ctn > 0 group by d.stock_fk order by p."+id+" asc";
	      System.out.println("get query "+query);
    	 // "SELECT * FROM sk_stock s join sk_product p on s.product_fk = p.rec_id join sk_detail_beli d on s.rec_id= s.stock_fk where s.stok_ctn > 0 order by p."+id+" asc";
       
	    	  //"SELECT * FROM sk_stock s join sk_product p on s.product_fk = p.rec_id where s.stok_ctn > 0 order by p."+id+" asc";
	      st = conn.createStatement();
	      rs = st.executeQuery(query);
	      HSSFWorkbook _wb = new HSSFWorkbook();
	      	
			HSSFSheet _sheet = _wb.createSheet("Trip");
			int _row = 2;
		    HSSFRow row = _sheet.createRow((short)_row);
		    //HEADER
		    HSSFRichTextString _s = new HSSFRichTextString("Kode Barang");
		    row.createCell((short) 0).setCellValue(_s);
		    _s = new HSSFRichTextString("Nomor Pabrik");
		    row.createCell((short) 1).setCellValue(_s);
		    _s = new HSSFRichTextString("Nama Barang");
		    row.createCell((short) 2).setCellValue(_s);
		    _s = new HSSFRichTextString("Kurs Beli");
		    row.createCell((short) 3).setCellValue(_s);
		    _s = new HSSFRichTextString("HargaBeli IDR/PCS");
		    row.createCell((short) 4).setCellValue(_s);
		    _s = new HSSFRichTextString("HargaJual /Pcs");
		    row.createCell((short) 5).setCellValue(_s);
		    _s = new HSSFRichTextString("No. Trip");
		    row.createCell((short) 6).setCellValue(_s);
		    _s = new HSSFRichTextString("Sisa Stok (CTN)");
		    row.createCell((short) 7).setCellValue(_s);
		    _s = new HSSFRichTextString("Sisa Stok (PCS)");
		    row.createCell((short) 8).setCellValue(_s);
		    //END HEADER
		    
	      while (rs.next())
	      {
	    	  	_row = _row+1;
	    	  row = _sheet.createRow((short)_row);
	    	  _s = new HSSFRichTextString(rs.getString("prod_code")); // INI MASUKIN DATA YG DI AMBIL DARI DB.
	    	  row.createCell((short) 0).setCellValue(_s);
	    	  _s = new HSSFRichTextString(rs.getString("prod_partNumb"));
			  row.createCell((short) 1).setCellValue(_s);
			  _s = new HSSFRichTextString(rs.getString("prod_name"));
			  row.createCell((short) 2).setCellValue(_s);
			  
			 
			  _s = new HSSFRichTextString(rs.getString("trip_currsIDR"));
			  row.createCell((short) 3).setCellValue(_s);
			  BigDecimal hargaBeli_PcsIdr = BigDecimal.ZERO;
			  
			  BigDecimal _isiCtn = rs.getBigDecimal("isi_ctn");
			  BigDecimal _isiPcs = rs.getBigDecimal("isi_pcs");
			  BigDecimal hargaBeli_CtnIdr = rs.getBigDecimal("hargaBeli_CtnIdr");
			  hargaBeli_PcsIdr = hargaBeli_CtnIdr.divide(_isiCtn,2,RoundingMode.HALF_UP).divide(_isiPcs,2,RoundingMode.HALF_UP);
			  _s = new HSSFRichTextString(hargaBeli_PcsIdr.toString());
			  row.createCell((short) 4).setCellValue(_s);
			  
			  BigDecimal _hargaCtn = rs.getBigDecimal("hjStd_Ctn");
			  BigDecimal _hargaPcs = _hargaCtn.divide(_isiCtn,2,RoundingMode.HALF_UP).divide(_isiPcs,2,RoundingMode.HALF_UP);
			 _s = new HSSFRichTextString(_hargaPcs.toString());
			  row.createCell((short) 5).setCellValue(_s);
			 
			  _s = new HSSFRichTextString(rs.getString("trip_num_stok"));
			  row.createCell((short) 6).setCellValue(_s);
			  
			  BigDecimal _stokCtn = rs.getBigDecimal("stok_ctn");
			  _s = new HSSFRichTextString(_stokCtn.toBigInteger().toString());
			  row.createCell((short) 7).setCellValue(_s);
			 
			  BigDecimal _stokPcs = _stokCtn.multiply(_isiCtn).multiply(_isiPcs);
			  _s = new HSSFRichTextString(_stokPcs.toBigInteger().toString());
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
	      if(_a.startsWith("Windows")){
	    	  fileOut = new FileOutputStream("D:/fileImgSk/workbook.xls");
	      }else{
	    	  fileOut = new FileOutputStream("/fileImgSk/workbook.xls");
	      }
	     
	      _wb.write(fileOut);
		    fileOut.close();
		return "";
	}
	public static String tripId(String _tripId) {
		Document doc = new Document(PageSize.LETTER);
		try {
			Criterion _filterByTripNumb = Restrictions.eq("trip_numb", _tripId);
			ArrayList<Criterion> _arrayCrit = new ArrayList<Criterion>();
			_arrayCrit.add(_filterByTripNumb);
			List<Order> _listOrder = new ArrayList<Order>();
			Order _orderByID = Order.desc("trip_numb");
			_listOrder.add(_orderByID);
			final List<TripEntity> _entList = BaseEntity.listDataOffset(TripEntity.class, _arrayCrit,_listOrder,  null, null);
			System.out.println("trip total size"+_entList.size());
			String _urlPdf = "";
			 String _ak=	System.getProperty("os.name");
		     
		      if(_ak.startsWith("Windows")){
		    	  _urlPdf = "D:/fileImgSk/"+_tripId+".pdf";
		      }else{
		    	  _urlPdf = "/fileImgSk/"+_tripId+".pdf";
		      }
			PdfWriter.getInstance(doc, new FileOutputStream(_urlPdf));
			doc.open();
			doc.setMargins(20, 20, 20, 20);
			
			PdfPTable _table = new PdfPTable(10);
			_table.setWidthPercentage(100);
			_table.setWidths(new float[]{2,2,3,1,1,1,1,2,2,3}); // UNTUK MENGATUR KAN UKURAN CELL
			_table.setTotalWidth(1000f);
			_table.setHeaderRows(1);
			_table.addCell(new Phrase("Nomor\n TRIP-Urut",new Font(FontFamily.TIMES_ROMAN, 10)));
			_table.addCell(new Phrase("Kd. Barang",new Font(FontFamily.TIMES_ROMAN, 10)));
			_table.addCell(new Phrase("Nama Barang",new Font(FontFamily.TIMES_ROMAN, 10)));
			_table.addCell(new Phrase("Qty\n(Pcs)",new Font(FontFamily.TIMES_ROMAN, 10)));
			_table.addCell(new Phrase("Sisa\n(Pcs)",new Font(FontFamily.TIMES_ROMAN, 10)));
			_table.addCell(new Phrase("Disc %",new Font(FontFamily.TIMES_ROMAN, 10)));
			_table.addCell(new Phrase("Cost %",new Font(FontFamily.TIMES_ROMAN, 9)));
			_table.addCell(new Phrase("Valuta",new Font(FontFamily.TIMES_ROMAN, 10)));
			_table.addCell(new Phrase("Juml.Netto\n(VTA)",new Font(FontFamily.TIMES_ROMAN, 10)));
			_table.addCell(new Phrase("Juml.Netto\n(IDR)",new Font(FontFamily.TIMES_ROMAN, 10)));
			int _loop = 0;
			if (null != _entList && !_entList.isEmpty()) {
				System.out.println("masuk list trip");
				for (final TripEntity _ent: _entList) {
					final List<DetailPembelianEntity> _pemb = new DetPembelianPersistence().getByTripList(_ent);
					System.out.println("_pemb size "+ _pemb.size());
					if(null != _pemb){
						for(final DetailPembelianEntity _pemEnt : _pemb){
							_loop = _loop+1;
							String _a = _pemEnt.getTripNumSeqStock();
							PdfPCell _cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
							_cell.setBorderWidthBottom(Rectangle.NO_BORDER);
							_cell.setBorderWidthTop(Rectangle.NO_BORDER);
							_table.addCell(_cell);
							_a = _pemEnt.getStockEnt().getProductEnt().getCode();
							_cell = new PdfPCell(new Phrase(_a, new Font(FontFamily.TIMES_ROMAN, 10)));
							_cell.setBorderWidthBottom(Rectangle.NO_BORDER);
							_cell.setBorderWidthTop(Rectangle.NO_BORDER);
							_table.addCell(_cell);
							_a = _pemEnt.getStockEnt().getProductEnt().getName();
							_cell = new PdfPCell(new Phrase(_pemEnt.getStockEnt().getProductEnt().getName(),new Font(FontFamily.TIMES_ROMAN, 10)));
							_cell.setBorderWidthBottom(Rectangle.NO_BORDER);
							_cell.setBorderWidthTop(Rectangle.NO_BORDER);
							_table.addCell(_cell);
							_a = _pemEnt.getQtyBeliCtn().toBigInteger().toString();
							_cell = new PdfPCell(new Phrase(_pemEnt.getQtyBeliCtn().toBigInteger().toString(),new Font(FontFamily.TIMES_ROMAN, 10)));
							_cell.setBorderWidthBottom(Rectangle.NO_BORDER);
							_cell.setBorderWidthTop(Rectangle.NO_BORDER);
							_table.addCell(_cell);
							_a = _pemEnt.getStockEnt().getStokCtn().toBigInteger().toString();
							_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
							_cell.setBorderWidthBottom(Rectangle.NO_BORDER);
							_cell.setBorderWidthTop(Rectangle.NO_BORDER);
							_table.addCell(_cell);
							_a = _pemEnt.getDisc().toString();
							_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
							_cell.setBorderWidthBottom(Rectangle.NO_BORDER);
							_cell.setBorderWidthTop(Rectangle.NO_BORDER);
							_table.addCell(_cell);
							
							_a = _pemEnt.getTripEnt().getTotCost().toString();
							_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
							_cell.setBorderWidthBottom(Rectangle.NO_BORDER);
							_cell.setBorderWidthTop(Rectangle.NO_BORDER);
							_table.addCell(_cell);
							
							_a = _pemEnt.getTripEnt().getCurrencyIDR().toString();
							_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
							_cell.setBorderWidthBottom(Rectangle.NO_BORDER);
							_cell.setBorderWidthTop(Rectangle.NO_BORDER);
							_table.addCell(_cell);
							
							BigDecimal _cepe = new BigDecimal(100);
							BigDecimal _cost = _pemEnt.getTripEnt().getTotCost();
							BigDecimal _nettVta= _pemEnt.getTotHargaNettB_Vta();
							BigDecimal _costnNettBVta = (_cost.multiply(_nettVta)).divide(_cepe,2,RoundingMode.HALF_UP);
							BigDecimal _finalNettVta = _nettVta.add(_costnNettBVta);
							_a = _finalNettVta.toString();
							_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
							_cell.setBorderWidthBottom(Rectangle.NO_BORDER);
							_cell.setBorderWidthTop(Rectangle.NO_BORDER);
							_table.addCell(_cell);
							
							
							BigDecimal _nettIdr= _pemEnt.getTotHargaNettB_Idr();
							BigDecimal _costnNettBIdr = (_cost.multiply(_nettIdr)).divide(_cepe,2,RoundingMode.HALF_UP);
							BigDecimal _finalNettIdr = _nettIdr.add(_costnNettBIdr);
							
							_a = _finalNettIdr.toString();
							_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
							_cell.setBorderWidthBottom(Rectangle.NO_BORDER);
							_cell.setBorderWidthTop(Rectangle.NO_BORDER);
							_table.addCell(_cell);
							
						}
					}
				}
			}
			if(_loop <10){
				String _a = "";
				for (int i = 0; i <10 - _loop; i++) {
				PdfPCell _cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
				_cell.setBorderWidthBottom(Rectangle.NO_BORDER);
				_cell.setBorderWidthTop(Rectangle.NO_BORDER);
				_table.addCell(_cell);

				_cell = new PdfPCell(new Phrase(_a, new Font(FontFamily.TIMES_ROMAN, 10)));
				_cell.setBorderWidthBottom(Rectangle.NO_BORDER);
				_cell.setBorderWidthTop(Rectangle.NO_BORDER);
				_table.addCell(_cell);

				_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
				_cell.setBorderWidthBottom(Rectangle.NO_BORDER);
				_cell.setBorderWidthTop(Rectangle.NO_BORDER);
				_table.addCell(_cell);

				_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
				_cell.setBorderWidthBottom(Rectangle.NO_BORDER);
				_cell.setBorderWidthTop(Rectangle.NO_BORDER);
				_table.addCell(_cell);

				_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
				_cell.setBorderWidthBottom(Rectangle.NO_BORDER);
				_cell.setBorderWidthTop(Rectangle.NO_BORDER);
				_table.addCell(_cell);

				_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
				_cell.setBorderWidthBottom(Rectangle.NO_BORDER);
				_cell.setBorderWidthTop(Rectangle.NO_BORDER);
				_table.addCell(_cell);

				_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
				_cell.setBorderWidthBottom(Rectangle.NO_BORDER);
				_cell.setBorderWidthTop(Rectangle.NO_BORDER);
				_table.addCell(_cell);

				_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
				_cell.setBorderWidthBottom(Rectangle.NO_BORDER);
				_cell.setBorderWidthTop(Rectangle.NO_BORDER);
				_table.addCell(_cell);

				_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
				_cell.setBorderWidthBottom(Rectangle.NO_BORDER);
				_cell.setBorderWidthTop(Rectangle.NO_BORDER);
				_table.addCell(_cell);

				_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
				_cell.setBorderWidthBottom(Rectangle.NO_BORDER);
				_cell.setBorderWidthTop(Rectangle.NO_BORDER);
				_table.addCell(_cell);
				}
			}
			Paragraph _p = new Paragraph("TRIP PEMBELIAN" , new Font(FontFamily.TIMES_ROMAN, 10));
			_p.setAlignment(Element.ALIGN_CENTER);
			
			
			doc.add(_p);
			doc.add(new Paragraph("\n"));
			
			doc.add(_table);
			doc.close();
		} catch (FileNotFoundException e) {
			System.out.println("masuk");
			e.printStackTrace();
		} catch (DocumentException e) {
			System.out.println("masuk f");
			e.printStackTrace();
		}
		return "";
		
	}
	
	
	public static String stokCtn(String _by){
		{
			Document doc = new Document(PageSize.LETTER);
		
			try {
				ResultSet rs = null;
				Statement st = null;
			      int _tot = 0;
			      String _ak=	System.getProperty("os.name");
			      String _urlPdf = "";
			      if(_ak.startsWith("Windows")){
			    	  _urlPdf = "D:/fileImgSk/Stock_0.pdf";
			      }else{
			    	  _urlPdf = "/fileImgSk/Stock_0.pdf";
			      }
			
				doc.setMargins(5, 5, 5, 5);
				PdfWriter.getInstance(doc, new FileOutputStream(_urlPdf));
				doc.open();
				
				
				PdfPTable _table = new PdfPTable(7);
				_table.setWidthPercentage(100);
				_table.setWidths(new float[]{3,3,4,3,2,2,2}); // UNTUK MENGATUR KAN UKURAN CELL
				_table.setTotalWidth(100f);
				_table.setHeaderRows(1);
				_table.addCell(new Phrase("Kode Barang",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Nomor Pabrik",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Nama Barang",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Harga Jual (PCS)",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("No. Trip",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Sisa Stok CTN",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Sisa Stok PCS",new Font(FontFamily.TIMES_ROMAN, 10)));
				
				try {
					  String myDriver = "org.gjt.mm.mysql.Driver";
				      String myUrl = "jdbc:mysql://localhost/sk-gudang";
				      Class.forName(myDriver);
				      Connection conn = null;
				      System.out.println(ExportToPdf._osName);
				      
				      if(ExportToPdf._osName.startsWith("Windows")){
				    	  conn = (Connection) DriverManager.getConnection(myUrl, "root", "");
				      }else{
				    	  conn = (Connection) DriverManager.getConnection(myUrl, "radot", "p@ssw0rd");
				      }
				      String query = "SELECT * FROM sk_stock s join sk_product p on s.product_fk = p.rec_id where s.stok_ctn > 0 order by p."+_by+" asc";
				      st = conn.createStatement();
				      rs = st.executeQuery(query);
				      while (rs.next())
				      {
				    	  _tot = _tot+1;
						String _a ="";
						PdfPCell _cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
					
						
						_a = rs.getString("prod_code");
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
					
						_cell.setBorderWidthLeft(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						

						_a = rs.getString("prod_partNumb");
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						_a =rs.getString("prod_name");
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						BigDecimal _hargaPCS = null;
						
						String _c = "";
						try {
							
							_hargaPCS  = rs.getBigDecimal("hjStd_Ctn").divide(rs.getBigDecimal("isi_pcs"),2,RoundingMode.HALF_UP).divide(rs.getBigDecimal("isi_ctn"),2,RoundingMode.HALF_UP);
							DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
					        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

					        formatRp.setCurrencySymbol("Rp ");
					        formatRp.setMonetaryDecimalSeparator('.');
					        formatRp.setGroupingSeparator(',');
					        kursIndonesia.setDecimalFormatSymbols(formatRp);
					        _c = kursIndonesia.format(_hargaPCS.doubleValue());
					        } catch (Exception e) {
							e.printStackTrace();
							_c =  "0";
						}
						
						
						_a =_c;
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
					
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						_a = rs.getString("trip_num_stok");
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
					
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);

						BigDecimal _totStokPcs = BigDecimal.ZERO;
						try {
							BigDecimal _isiPcs = rs.getBigDecimal("isi_pcs");
							BigDecimal _isiCtn = rs.getBigDecimal("isi_ctn");
							BigDecimal _stokCtn = rs.getBigDecimal("stok_ctn");
							 _totStokPcs = _stokCtn.multiply(_isiCtn).multiply(_isiPcs);
							
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						BigDecimal _stokCtn=rs.getBigDecimal("stok_ctn");
						_a =_stokCtn.toBigInteger().toString();
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
					
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						_a =_totStokPcs.toBigInteger().toString();
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
				    
				      }	st.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				Paragraph _p = new Paragraph("SISA STOCK" , new Font(FontFamily.TIMES_ROMAN, 10));
				_p.setAlignment(Element.ALIGN_CENTER);
				
				Paragraph _ps = new Paragraph("FAKTUR TRIP Kedua" , new Font(FontFamily.TIMES_ROMAN, 10));
				_ps.setAlignment(Element.ALIGN_CENTER);
				doc.add(_p);
				/*doc.add(new Paragraph("\n"));*/
				
				doc.add(_table);
				doc.add(new Paragraph("total item = " + String.valueOf(_tot)));
				doc.close();
			} catch (FileNotFoundException e) {
				System.out.println("masuk");
				e.printStackTrace();
			} catch (DocumentException e) {
				System.out.println("masuk f");
				e.printStackTrace();
			}
		return "";
	}
	
	}
	public static String salesPrint(String _by, String dateTo,String dateFrom, String salesID, String salesName,String discMin,String discMax){
		{
			Document doc = new Document(PageSize.A1.rotate());
		
			try {
				ResultSet rs = null;
				Statement st = null;
			      int _tot = 0;
				
				 String _ak=	System.getProperty("os.name");
			      String _urlPdf = "";
			      if(_ak.startsWith("Windows")){
			    	  _urlPdf = "D:/fileImgSk/penjualan_"+salesID+".pdf";
			      }else{
			    	  _urlPdf = "/fileImgSk/penjualan_"+salesID+".pdf";
			      }
				doc.setMargins(5, 5, 5, 5);
				PdfWriter.getInstance(doc, new FileOutputStream(_urlPdf));
				doc.open();
				
				
				PdfPTable _table = new PdfPTable(22);
				_table.setWidthPercentage(100);
				_table.setWidths(new float[]{1,2,3,2,1,1,2,2,2,2,1,2,2,2,1,1,1,1,1,1,1,1}); // UNTUK MENGATUR KAN UKURAN CELL
				_table.setTotalWidth(100f);
				_table.setHeaderRows(1);
				_table.addCell(new Phrase("No\nUrut",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Trip",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Nama Stock",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Part No",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Min",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Max",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Awal",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Akhir",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Hrg/Ctn\nStandar",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Qty\nJual",new Font(FontFamily.TIMES_ROMAN, 10)));
				/*_table.addCell(new Phrase("Isi\nCtn",new Font(FontFamily.TIMES_ROMAN, 10)));*/
				_table.addCell(new Phrase("Netto\nBruto",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Hrg/Ctn\nRata2",new Font(FontFamily.TIMES_ROMAN, 10)));
				PdfPCell _cellH = new PdfPCell(new Phrase("HPP",new Font(FontFamily.TIMES_ROMAN, 10)));
				_cellH.setColspan(5);
				_table.addCell(_cellH);
				_table.addCell(new Phrase("Rugi/Laba",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Ongkos\nMasuk %",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("R/L stlh\nOngkos Rp",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Rugi dalam $",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Biaya dan\nongkos2 (Rp)",new Font(FontFamily.TIMES_ROMAN, 10)));
//	buat variable total
				   String _totQtyJual = "";
				   BigDecimal _totQtyJualA = BigDecimal.ZERO;
				   BigDecimal _totNettoBRuto = BigDecimal.ZERO;
				   BigDecimal _totProfitBeforeCost = BigDecimal.ZERO;
				   BigDecimal _totProfitAfterCost = BigDecimal.ZERO;
				   BigDecimal _totHargaRata2 = BigDecimal.ZERO;
				   BigDecimal _totHpp = BigDecimal.ZERO;
				   BigDecimal _totOngkos = BigDecimal.ZERO;
				try {
					  String myDriver = "org.gjt.mm.mysql.Driver";
				      String myUrl = "jdbc:mysql://localhost/sk-gudang";
				      Class.forName(myDriver);
				      
				      Connection conn = null;
				      if(ExportToPdf._osName.startsWith("Windows")){
				    	  conn = (Connection) DriverManager.getConnection(myUrl, "root", "");
				      }else{
				    	  conn = (Connection) DriverManager.getConnection(myUrl, "radot", "p@ssw0rd");
				      }
				      String query = "SELECT pr.prod_name as namaprod ,pr.prod_code as codeprod, dj.disc_persen as disc ,max(dj.disc_persen) as discMax ,min(dj.disc_persen) as discMin ,avg(dj.disc_persen) as rataDisc ,sum(dj.tot_jual_bruto_idr) as brutoidr ,sum(dj.tot_jual_netto_idr) as nettoidr ,sum(dj.TotQtyJ_Ctn) as qtyJual ,pr.isi_ctn as isictn ,min(pj.tanggal_order) as awaltgl ,max(pj.tanggal_order) as akhirtgl ,s.trip_num_stok as tripNumb ,pj.ppn as ppnJual ,s.rec_id as idStock FROM sk_penjualan pj join sk_detail_jual dj on dj.penjualan_fk = pj.rec_id join sk_stock s on dj.stock_fk = s.rec_id join sk_product pr on s.product_fk = pr.rec_id ";
				      		 query = query+" WHERE salesman_code_fk = "+_by+" and tanggal_order between '"+dateFrom+"' and '"+dateTo+"' and dj.disc_persen between '"+discMin+"' and '"+discMax+"' group by pr.prod_code ORDER BY tanggal_order DESC ";
				      System.out.println(query);
				      st = conn.createStatement();
				      rs = st.executeQuery(query);
				      while (rs.next())
				      {
				    	  
				    	 
				    	  _tot = _tot+1;
						String _a ="";
						PdfPCell _cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
					
						SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
						
						_a = String.valueOf(_tot);
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
					
						_cell.setBorderWidthLeft(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						

						_a = rs.getString("tripNumb");
						
						if(null != _a ){
							if(_a.equalsIgnoreCase("Q05-041")){
								System.out.println("_a");
							}
							
						}
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						_a =rs.getString("namaprod");
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						
						
						_a =rs.getString("codeprod");
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						_a = rs.getBigDecimal("discMin").toBigInteger().toString()+"%";
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);

						
						_a =rs.getBigDecimal("discMax").toBigInteger().toString()+"%";
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						_a =dt1.format(rs.getDate("awaltgl"));
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						_a =dt1.format(rs.getDate("akhirtgl"));
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						BigDecimal _rata2 = rs.getBigDecimal("rataDisc");
						BigDecimal _qtyJual = rs.getBigDecimal("qtyJual");
						BigDecimal _brutoIdr = rs.getBigDecimal("brutoidr");
						BigDecimal _hrgStandar = BigDecimal.ZERO;
						try {
						 _hrgStandar = _brutoIdr.divide(_qtyJual,2, RoundingMode.HALF_UP);
						} catch (Exception e) {
							e.printStackTrace();
						}
						_a =convertRp(_hrgStandar);
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						_a =_qtyJual.toBigInteger().toString();
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						BigDecimal _discA = rs.getBigDecimal("disc");
						BigDecimal _cepe = new BigDecimal(100);
						BigDecimal _disc = _discA.divide(_cepe,100,RoundingMode.HALF_UP);
						BigDecimal _totDisc = _brutoIdr.multiply(_disc);
						BigDecimal _brutoAfterDiscIdr = _brutoIdr.subtract(_totDisc);
						BigDecimal _ppnA = rs.getBigDecimal("ppnJual");
						BigDecimal _ppn = _ppnA.divide(_cepe,100,RoundingMode.HALF_UP);
						BigDecimal _totPpn = _brutoAfterDiscIdr.multiply(_ppn);
						BigDecimal _netto = _brutoAfterDiscIdr.add(_totPpn);
						BigDecimal _nettoIdr = _netto;
						BigDecimal _hrgCtnRat = BigDecimal.ZERO;
						try {
							_hrgCtnRat = _nettoIdr.divide(_qtyJual,2, RoundingMode.HALF_UP);
						} catch (Exception e) {
							// TODO: handle exception
						}
						_a =convertRp(_nettoIdr);
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						_a =convertRp(_hrgCtnRat);
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						String _idStock = rs.getString("idStock");
						String _queryBeli = "SELECT t.tot_cost as cost,v.vnd_vta as vta, dbl.totHargaBrut_B_Idr as hargaBeliBruto ,dbl.qty_beli_ctn as qtybeliCTn ,t.trip_currsIdr as currs FROM sk_detail_beli dbl Join sk_trip t on dbl.Trip_fk = t.rec_id Join sk_vendor v on t.vnd_code_fk = v.rec_id";
							    _queryBeli = _queryBeli+" where dbl.Stock_fk = "+_idStock+" ORDER BY dbl.rec_id DESC limit 1";
//							    System.out.println("resBeli = " + _queryBeli);
							  ResultSet rsBeli = null;
							  Statement stBeli = null;
							  stBeli = conn.createStatement();
							  rsBeli = stBeli.executeQuery(_queryBeli);
							  
							 rsBeli.first();
								  
						
								BigDecimal _HrgBeli = BigDecimal.ZERO;
								BigDecimal _qtyBeli = BigDecimal.ZERO;
								BigDecimal _hppBeliIdr = BigDecimal.ZERO;
								BigDecimal _TotHppBeliIdr = BigDecimal.ZERO;
								
								BigDecimal _hPPBeliVta = BigDecimal.ZERO;
								BigDecimal _kurs = BigDecimal.ZERO;
								
								try {
									_HrgBeli = rsBeli.getBigDecimal("hargaBeliBruto");
									_qtyBeli = rsBeli.getBigDecimal("qtybeliCTn");
									_hppBeliIdr = _HrgBeli.divide(_qtyBeli,2,RoundingMode.HALF_UP);
									_TotHppBeliIdr = _hppBeliIdr.multiply(_qtyJual);								} catch (Exception e) {
									System.out.println("idStock "+ _idStock);
									e.printStackTrace();

								}
								try {
									_kurs = rsBeli.getBigDecimal("currs");
									_hPPBeliVta = _hppBeliIdr.divide(_kurs);
								} catch (Exception e) {
									System.out.println("idStock "+ _idStock);
									e.printStackTrace();
								}
						
						_a =convertRp(_hppBeliIdr);
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						_a =convertRp(_TotHppBeliIdr);
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						
						_a =rsBeli.getString("vta");
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						_a =_hPPBeliVta.toString();
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						_a =_kurs.toString();
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						//END HPP
						BigDecimal _labaRugi = _nettoIdr.subtract(_TotHppBeliIdr);
						_a =convertRp(_labaRugi);
						/*BigDecimal _labaRugi = _nettoIdr.subtract(_hppBeliIdr);
						_a =convertRp(_labaRugi);*/
						
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						BigDecimal _cost = rsBeli.getBigDecimal("cost");
						_a =_cost.toString()+" %";
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						BigDecimal _totCost = BigDecimal.ZERO;
						BigDecimal _LRStlhCost = BigDecimal.ZERO;
						BigDecimal _LRvta = BigDecimal.ZERO;
						try {
							_totCost  = _hppBeliIdr.multiply(_qtyJual).multiply(_cost).divide(BigDecimal.valueOf(100));
						} catch (Exception e) {
							System.out.println("get totCost");
							e.printStackTrace();
						}
						try {
							_LRStlhCost = _labaRugi.subtract(_totCost);
						} catch (Exception e) {
							System.out.println("Laba rugi stlh cost");
							e.printStackTrace();
						}
						try {
							
							_LRvta= _labaRugi.divide(_kurs, 2, RoundingMode.HALF_UP);
						} catch (Exception e) {
							System.out.println("Laba Rugi dollar" +_cost.toString()+" / "+_kurs.toString());
							e.printStackTrace();
						}
						_a =convertRp(_LRStlhCost);
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						_a =_LRvta.toString();
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						BigDecimal _biayaAndOngkosRp = BigDecimal.ZERO;
						try {
							_biayaAndOngkosRp = _labaRugi.subtract(_LRStlhCost);
						} catch (Exception e) {
							System.out.println("Biaya dan ongkos 2 (rp)");
							e.printStackTrace();
						}
						_a =convertRp(_biayaAndOngkosRp);
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						//mentotal setiap looping
						
						_totQtyJualA = _totQtyJualA.add(_qtyJual);
						_totQtyJual=_totQtyJualA.toBigInteger().toString();
						_totHargaRata2 = _totHargaRata2.add(_hrgCtnRat);
						_totNettoBRuto = _totNettoBRuto.add(_nettoIdr);
						_totProfitBeforeCost = _totProfitBeforeCost.add(_labaRugi);
						_totProfitAfterCost = _totProfitAfterCost.add(_LRStlhCost);
						_totHpp =_totHpp.add(_hppBeliIdr);
						_totOngkos = _totOngkos.add(_biayaAndOngkosRp);
						
						stBeli.close();
						rsBeli.close();
				    
				      }	
				      // ADD TOTAL
				      PdfPCell _cellTotQtyJual = new PdfPCell(new Phrase(_totQtyJual.toString(),new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(new Phrase("",new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(new Phrase("",new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(new Phrase("",new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(new Phrase("",new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(new Phrase("",new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(new Phrase("",new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(new Phrase("",new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(new Phrase("",new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(new Phrase("",new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(_cellTotQtyJual);
				      _table.addCell(new Phrase(convertRp(_totNettoBRuto),new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(new Phrase(convertRp(_totHargaRata2),new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(new Phrase("",new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(new Phrase(convertRp(_totHpp),new Font(FontFamily.TIMES_ROMAN, 10)));
				    
				      _table.addCell(new Phrase("",new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(new Phrase("",new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(new Phrase("",new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(new Phrase(convertRp(_totProfitBeforeCost),new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(new Phrase("",new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(new Phrase(convertRp(_totProfitAfterCost),new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(new Phrase("",new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(new Phrase(convertRp(_totOngkos),new Font(FontFamily.TIMES_ROMAN, 10)));
				      st.close();
				      conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				Paragraph _p = new Paragraph("Penjualan "+salesName+"\n" , new Font(FontFamily.TIMES_ROMAN, 10));
				_p.setAlignment(Element.ALIGN_CENTER);
				
			
				doc.add(_p);
				/*doc.add(new Paragraph("\n"));*/
				
				doc.add(_table);
				
				doc.add(new Paragraph("total item = " + String.valueOf(_tot)));
				doc.close();
			} catch (FileNotFoundException e) {
				System.out.println("masuk");
				e.printStackTrace();
			} catch (DocumentException e) {
				System.out.println("masuk f");
				e.printStackTrace();
			}
		return "";
	}
	}
	public static String custPrint(String _by, String dateTo,String dateFrom, String salesID, String custName, String discMin, String discMax){
		{
			Document doc = new Document(PageSize.A1.rotate());
		
			try {
				ResultSet rs = null;
				Statement st = null;
			      int _tot = 0;
				
				 String _ak=	System.getProperty("os.name");
			      String _urlPdf = "";
			      if(_ak.startsWith("Windows")){
			    	  _urlPdf = "D:/fileImgSk/customer_penjualan_"+salesID+".pdf";
			      }else{
			    	  _urlPdf = "/fileImgSk/customer_penjualan_"+salesID+".pdf";
			      }
				doc.setMargins(5, 5, 5, 5);
				PdfWriter.getInstance(doc, new FileOutputStream(_urlPdf));
				doc.open();
				
				
				PdfPTable _table = new PdfPTable(22);
				_table.setWidthPercentage(100);
				_table.setWidths(new float[]{1,2,3,2,1,1,2,2,2,2,1,2,2,2,1,1,1,1,1,1,1,1}); // UNTUK MENGATUR KAN UKURAN CELL
				_table.setTotalWidth(100f);
				_table.setHeaderRows(1);
				_table.addCell(new Phrase("No\nUrut",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Trip",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Nama Stock",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Part No",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Min",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Max",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Awal",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Akhir",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Hrg/Ctn\nStandar",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Qty\nJual",new Font(FontFamily.TIMES_ROMAN, 10)));
				/*_table.addCell(new Phrase("Isi\nCtn",new Font(FontFamily.TIMES_ROMAN, 10)));*/
				_table.addCell(new Phrase("Netto\nBruto",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Hrg/Ctn\nRata2",new Font(FontFamily.TIMES_ROMAN, 10)));
				PdfPCell _cellH = new PdfPCell(new Phrase("HPP",new Font(FontFamily.TIMES_ROMAN, 10)));
				_cellH.setColspan(5);
				_table.addCell(_cellH);
				_table.addCell(new Phrase("Rugi/Laba",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Ongkos\nMasuk %",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("R/L stlh\nOngkos Rp",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Rugi dalam $",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Biaya dan\nongkos2 (Rp)",new Font(FontFamily.TIMES_ROMAN, 10)));
//	buat variable total
				   String _totQtyJual = "";
				   BigDecimal _totQtyJualA = BigDecimal.ZERO;
				   BigDecimal _totNettoBRuto = BigDecimal.ZERO;
				   BigDecimal _totProfitBeforeCost = BigDecimal.ZERO;
				   BigDecimal _totProfitAfterCost = BigDecimal.ZERO;
				   BigDecimal _totHpp = BigDecimal.ZERO;
				   BigDecimal _totHargaRata2 = BigDecimal.ZERO;
				   BigDecimal _totOngkos = BigDecimal.ZERO;
				   
				try {
					  String myDriver = "org.gjt.mm.mysql.Driver";
				      String myUrl = "jdbc:mysql://localhost/sk-gudang";
				      Class.forName(myDriver);
				      
				      Connection conn = null;
				      if(ExportToPdf._osName.startsWith("Windows")){
				    	  conn = (Connection) DriverManager.getConnection(myUrl, "root", "");
				      }else{
				    	  conn = (Connection) DriverManager.getConnection(myUrl, "radot", "p@ssw0rd");
				      }
				      String query = "SELECT pr.prod_name as namaprod ,pr.prod_code as codeprod, dj.disc_persen as disc ,max(dj.disc_persen) as discMax ,min(dj.disc_persen) as discMin ,avg(dj.disc_persen) as rataDisc ,sum(dj.tot_jual_bruto_idr) as brutoidr ,sum(dj.tot_jual_netto_idr) as nettoidr ,sum(dj.TotQtyJ_Ctn) as qtyJual ,pr.isi_ctn as isictn ,min(pj.tanggal_order) as awaltgl ,max(pj.tanggal_order) as akhirtgl ,s.trip_num_stok as tripNumb ,pj.ppn as ppnJual ,s.rec_id as idStock ,dbl.rec_id as IdDetailBeli FROM sk_penjualan pj join sk_detail_jual dj on dj.penjualan_fk = pj.rec_id join sk_stock s on dj.stock_fk = s.rec_id join sk_product pr on s.product_fk = pr.rec_id join sk_detail_beli dbl on dbl.Stock_fk = s.rec_id";
				      		 query = query+" WHERE customer_code_fk = "+_by+" and tanggal_order between '"+dateFrom+"' and '"+dateTo+"' and dj.disc_persen between '"+discMin+"' and '"+discMax+"' group by pr.prod_code ORDER BY tanggal_order DESC ";
				      System.out.println(query);
				      st = conn.createStatement();
				      rs = st.executeQuery(query);
				      while (rs.next())
				      {
				    	  
				    	 
				    	  _tot = _tot+1;
						String _a ="";
						PdfPCell _cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
					
						SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
						
						_a = String.valueOf(_tot);
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
					
						_cell.setBorderWidthLeft(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						

						_a = rs.getString("tripNumb");
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						_a =rs.getString("namaprod");
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						
						
						_a =rs.getString("codeprod");
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						_a = rs.getBigDecimal("discMin").toBigInteger().toString()+"%";
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);

						
						_a =rs.getBigDecimal("discMax").toBigInteger().toString()+"%";
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						_a =dt1.format(rs.getDate("awaltgl"));
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						_a =dt1.format(rs.getDate("akhirtgl"));
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						BigDecimal _qtyJual = rs.getBigDecimal("qtyJual");
						BigDecimal _brutoIdr = rs.getBigDecimal("brutoidr");
						BigDecimal _hrgStandar = BigDecimal.ZERO;
						try {
						 _hrgStandar = _brutoIdr.divide(_qtyJual,2,RoundingMode.HALF_UP);
						} catch (Exception e) {
							e.printStackTrace();
						}
						_a =convertRp(_hrgStandar);
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						_a =_qtyJual.toBigInteger().toString();
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						BigDecimal _discA = rs.getBigDecimal("disc");
						BigDecimal _cepe = new BigDecimal(100);
						BigDecimal _disc = _discA.divide(_cepe,100,RoundingMode.HALF_UP);
						BigDecimal _totDisc = _brutoIdr.multiply(_disc);
						BigDecimal _brutoAfterDiscIdr = _brutoIdr.subtract(_totDisc);
						BigDecimal _ppnA = rs.getBigDecimal("ppnJual");
						BigDecimal _ppn = _ppnA.divide(_cepe,100,RoundingMode.HALF_UP);
						BigDecimal _totPpn = _brutoAfterDiscIdr.multiply(_ppn);
						BigDecimal _netto = _brutoAfterDiscIdr.add(_totPpn);
						BigDecimal _nettoIdr = _netto;
						//BigDecimal _nettoIdr = rs.getBigDecimal("nettoidr");
						BigDecimal _hrgCtnRat = BigDecimal.ZERO;
						try {
							_hrgCtnRat = _nettoIdr.divide(_qtyJual);
						} catch (Exception e) {
							// TODO: handle exception
						}
						_a =convertRp(_nettoIdr);
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						_a =convertRp(_hrgCtnRat);
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						String _idStock = rs.getString("idStock");
						String _queryBeli = "SELECT t.tot_cost as cost,v.vnd_vta as vta, dbl.totHargaBrut_B_Idr as hargaBeliBruto ,dbl.qty_beli_ctn as qtybeliCTn ,t.trip_currsIdr as currs FROM sk_detail_beli dbl Join sk_trip t on dbl.Trip_fk = t.rec_id Join sk_vendor v on t.vnd_code_fk = v.rec_id";
							    _queryBeli = _queryBeli+" where dbl.Stock_fk = "+_idStock+" ORDER BY dbl.rec_id DESC limit 1";
//							    System.out.println("resBeli = " + _queryBeli);
							  ResultSet rsBeli = null;
							  Statement stBeli = null;
							  stBeli = conn.createStatement();
							  rsBeli = stBeli.executeQuery(_queryBeli);
							  
							 rsBeli.first();
								  
						
								BigDecimal _HrgBeli = BigDecimal.ZERO;
								BigDecimal _qtyBeli = BigDecimal.ZERO;
								BigDecimal _hppBeliIdr = BigDecimal.ZERO;
								BigDecimal _hPPBeliVta = BigDecimal.ZERO;
								BigDecimal _TotHppBeliIdr = BigDecimal.ZERO;
								BigDecimal _kurs = BigDecimal.ZERO;
								try {
									_HrgBeli = rsBeli.getBigDecimal("hargaBeliBruto");
									_qtyBeli = rsBeli.getBigDecimal("qtybeliCTn");
									_hppBeliIdr = _HrgBeli.divide(_qtyBeli,2,RoundingMode.HALF_UP);
									_TotHppBeliIdr = _hppBeliIdr.multiply(_qtyJual);
								} catch (Exception e) {
									System.out.println("idStock "+ _idStock);
									e.printStackTrace();

								}
								try {
									_kurs = rsBeli.getBigDecimal("currs");
									_hPPBeliVta = _hppBeliIdr.divide(_kurs);
								} catch (Exception e) {
									System.out.println("idStock "+ _idStock);
									e.printStackTrace();
								}
						
						_a =convertRp(_hppBeliIdr);
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						_a =convertRp(_TotHppBeliIdr);
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						
						
						_a =rsBeli.getString("vta");
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						_a =_hPPBeliVta.toString();
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						_a =_kurs.toString();
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						//END HPP
						
						/*BigDecimal _labaRugi = _nettoIdr.subtract(_TotHppBeliIdr);
						_a =convertRp(_labaRugi);*/
						BigDecimal _labaRugi = _nettoIdr.subtract(_TotHppBeliIdr);
						_a =convertRp(_labaRugi);
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						BigDecimal _cost = rsBeli.getBigDecimal("cost");
						
						_a =_cost.toString()+" %";
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						BigDecimal _totCost = BigDecimal.ZERO;
						BigDecimal _LRStlhCost = BigDecimal.ZERO;
						BigDecimal _LRvta = BigDecimal.ZERO;
						try {
							_totCost  = _hppBeliIdr.multiply(_qtyJual).multiply(_cost).divide(BigDecimal.valueOf(100));
						} catch (Exception e) {
							System.out.println("get totCost");
							e.printStackTrace();
						}
						try {
							_LRStlhCost = _labaRugi.subtract(_totCost);
						} catch (Exception e) {
							System.out.println("Laba rugi stlh cost");
							e.printStackTrace();
						}
						try {
							
							_LRvta= _labaRugi.divide(_kurs, 2, RoundingMode.HALF_UP);
						} catch (Exception e) {
							System.out.println("Laba Rugi dollar" +_cost.toString()+" / "+_kurs.toString());
							e.printStackTrace();
						}
						_a =convertRp(_LRStlhCost);
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						_a =_LRvta.toString();
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						BigDecimal _biayaAndOngkosRp = BigDecimal.ZERO;
						try {
							_biayaAndOngkosRp = _labaRugi.subtract(_LRStlhCost);
						} catch (Exception e) {
							System.out.println("Biaya dan ongkos 2 (rp)");
							e.printStackTrace();
						}
						_a =convertRp(_biayaAndOngkosRp);
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						//mentotal setiap looping
						
						_totQtyJualA = _totQtyJualA.add(_qtyJual);
						_totQtyJual=_totQtyJualA.toBigInteger().toString();
						_totHargaRata2 = _totHargaRata2.add(_hrgCtnRat);
						_totNettoBRuto = _totNettoBRuto.add(_nettoIdr);
						_totProfitBeforeCost = _totProfitBeforeCost.add(_labaRugi);
						_totProfitAfterCost = _totProfitAfterCost.add(_LRStlhCost);
						_totHpp = _totHpp.add(_hppBeliIdr);
						
						_totOngkos = _totOngkos.add(_biayaAndOngkosRp);
						
						stBeli.close();
						rsBeli.close();
				    
				      }	
				      // ADD TOTAL
				      PdfPCell _cellTotQtyJual = new PdfPCell(new Phrase(_totQtyJual.toString(),new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(new Phrase("",new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(new Phrase("",new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(new Phrase("",new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(new Phrase("",new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(new Phrase("",new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(new Phrase("",new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(new Phrase("",new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(new Phrase("",new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(new Phrase("",new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(_cellTotQtyJual);
				      _table.addCell(new Phrase(convertRp(_totNettoBRuto),new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(new Phrase(convertRp(_totHargaRata2),new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(new Phrase("",new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(new Phrase(convertRp(_totHpp),new Font(FontFamily.TIMES_ROMAN, 10)));
				      
				      _table.addCell(new Phrase("",new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(new Phrase("",new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(new Phrase("",new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(new Phrase(convertRp(_totProfitBeforeCost),new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(new Phrase("",new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(new Phrase(convertRp(_totProfitAfterCost),new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(new Phrase("",new Font(FontFamily.TIMES_ROMAN, 10)));
				      _table.addCell(new Phrase(convertRp(_totOngkos),new Font(FontFamily.TIMES_ROMAN, 10)));
				      
				      st.close();
				      conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				Paragraph _p = new Paragraph("Penjualan "+custName+"\n" , new Font(FontFamily.TIMES_ROMAN, 10));
				_p.setAlignment(Element.ALIGN_CENTER);
				
			
				doc.add(_p);
				/*doc.add(new Paragraph("\n"));*/
				
				doc.add(_table);
				doc.add(new Paragraph("total item = " + String.valueOf(_tot)));
				doc.close();
			} catch (FileNotFoundException e) {
				System.out.println("masuk");
				e.printStackTrace();
			} catch (DocumentException e) {
				System.out.println("masuk f");
				e.printStackTrace();
			}
		return "";
	}
	}
	public static String salesPrintFaktur(String _by, String dateTo,String dateFrom, String salesID, String salesName,String _userName,String discMin,String discMax){
		{
			Document doc = new Document(PageSize.A4.rotate());
			SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
			try {
				ResultSet rs = null;
				Statement st = null;
			      int _tot = 0;
				
				 String _ak=	System.getProperty("os.name");
			      String _urlPdf = "";
			      if(_ak.startsWith("Windows")){
			    	  _urlPdf = "D:/fileImgSk/penjualan_"+salesID+"_faktur.pdf";
			      }else{
			    	  _urlPdf = "/fileImgSk/penjualan_"+salesID+"_faktur.pdf";
			      }
				doc.setMargins(5, 5, 5, 5);
				PdfWriter.getInstance(doc, new FileOutputStream(_urlPdf));
				doc.open();
				
				
				PdfPTable _table = new PdfPTable(10);
				_table.setWidthPercentage(100);
				_table.setWidths(new float[]{1,2,2,2,5,2,2,2,2,2}); // UNTUK MENGATUR KAN UKURAN CELL
				_table.setTotalWidth(100f);
				_table.setHeaderRows(1);
				_table.addCell(new Phrase("No\nUrut",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Tanggal",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("No. Bukti",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Salesman",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Nama Customer",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Kota",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Bruto (Rp)",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Netto (Rp)",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Rp ---",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Disc --",new Font(FontFamily.TIMES_ROMAN, 10)));
				 
				try {
					  String myDriver = "org.gjt.mm.mysql.Driver";
				      String myUrl = "jdbc:mysql://localhost/sk-gudang";
				      Class.forName(myDriver);
				      
				      BigDecimal _totBrut = BigDecimal.ZERO;
				      BigDecimal _totNett =  BigDecimal.ZERO;
				      BigDecimal _totTotDisc = BigDecimal.ZERO;
				      BigDecimal _totTotDiscPersen = BigDecimal.ZERO;
				      
				      Connection conn = null;
				      if(ExportToPdf._osName.startsWith("Windows")){
				    	  conn = (Connection) DriverManager.getConnection(myUrl, "root", "");
				      }else{
				    	  conn = (Connection) DriverManager.getConnection(myUrl, "radot", "p@ssw0rd");
				      }
				      String query = "SELECT p.tanggal_order ,p.no_order ,c.cust_name ,c.cust_city ,s.salesman_code ,p.tot_penjualan_nett_idr, dj.disc_persen as disc, p.ppn as ppnJual ,p.tot_penjualan_bruto_idr ,p.tot_disc_penjualan ,avg(dj.disc_persen) as avgdiscpersen FROM sk_penjualan p join sk_salesman s on p.salesman_code_fk = s.rec_id join sk_customers c on p.customer_code_fk = c.rec_id join sk_detail_jual dj on p.rec_id = dj.penjualan_fk";
				      		 query = query+" WHERE salesman_code_fk = "+_by+" and tanggal_order between '"+dateFrom+"' and '"+dateTo+"' and dj.disc_persen between '"+discMin+"' and '"+discMax+"'  group by dj.penjualan_fk ORDER BY tanggal_order DESC ";
				      System.out.println(query);
				      st = conn.createStatement();
				      rs = st.executeQuery(query);
				      while (rs.next())
				      {
				    	  _tot = _tot+1;
						String _a ="";
						PdfPCell _cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
					
						
						
						_a = String.valueOf(_tot);
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
					
						_cell.setBorderWidthLeft(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						

						_a = dt1.format(rs.getDate("tanggal_order"));
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						_a =rs.getString("no_order");
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						_a =rs.getString("salesman_code");
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						_a = rs.getString("cust_name");
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);

						
						_a =rs.getString("cust_city");
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
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
						_a =convertRp(_bruto);
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						_a =convertRp(_nett);
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						_a =convertRp(_totDisc);
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						_a =_totDispersen.divide(BigDecimal.ONE, 2, RoundingMode.HALF_UP).toString()+" %";
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
				      }	
				      // ADD TOTAL
				      PdfPCell _cell = new PdfPCell(new Phrase("",new Font(FontFamily.TIMES_ROMAN, 10)));
				      _cell = new PdfPCell(new Phrase("",new Font(FontFamily.TIMES_ROMAN, 10)));
				      _cell.setBorder(1);
				      _cell.setBorderWidthTop(1);
				      _table.addCell(_cell);
				      _table.addCell(_cell);
				      _table.addCell(_cell);
				      _table.addCell(_cell);
				      _table.addCell(_cell);
				      _cell = new PdfPCell(new Phrase("Total ",new Font(FontFamily.TIMES_ROMAN, 10)));
				      _cell.setBorder(1);
				      _cell.setBorderWidthTop(1);
				      _table.addCell(_cell);
				      _cell = new PdfPCell(new Phrase(convertRp(_totBrut),new Font(FontFamily.TIMES_ROMAN, 10)));
				      _cell.setBorder(1);
				      _cell.setBorderWidthTop(1);
				      _table.addCell(_cell);
				      _cell = new PdfPCell(new Phrase(convertRp(_totNett),new Font(FontFamily.TIMES_ROMAN, 10)));
				      _cell.setBorder(1);
				      _cell.setBorderWidthTop(1);
				      _table.addCell(_cell);
				      _cell = new PdfPCell(new Phrase(convertRp(_totTotDisc),new Font(FontFamily.TIMES_ROMAN, 10)));
				      _cell.setBorder(1);
				      _cell.setBorderWidthTop(1);
				      _table.addCell(_cell);
				      BigDecimal _avgTotDiscPerser = _totTotDiscPersen.divide(BigDecimal.valueOf(Long.valueOf(_tot)), 2,RoundingMode.HALF_UP);
				      _cell = new PdfPCell(new Phrase(_avgTotDiscPerser.toString()+" %",new Font(FontFamily.TIMES_ROMAN, 10)));
				      _cell.setBorder(1);
				      _cell.setBorderWidthTop(1);
				      _table.addCell(_cell);
				      st.close();
				      conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				Paragraph _p = new Paragraph("LAPORAN PENJUALAN SALESMAN PERIODE "+dateFrom+" s/d "+dateTo+"\n\n" , new Font(FontFamily.TIMES_ROMAN, 10));
				_p.setAlignment(Element.ALIGN_LEFT);
				
			
				doc.add(_p);
				/*doc.add(new Paragraph("\n"));*/
				
				doc.add(_table);
				SimpleDateFormat dt2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				doc.add(new Paragraph("PRINTED BY : " + _userName.toUpperCase() +" "+dt2.format(new Date())));
				doc.close();
			} catch (FileNotFoundException e) {
				System.out.println("masuk");
				e.printStackTrace();
			} catch (DocumentException e) {
				System.out.println("masuk f");
				e.printStackTrace();
			}
		return "";
	}
	
	}
	public static String custPrintFaktur(String _by, String dateTo,String dateFrom, String custcode, String salesName,String _userName,String discMin,String discMax){
		{
			Document doc = new Document(PageSize.A4.rotate());
			SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
			try {
				ResultSet rs = null;
				Statement st = null;
			      int _tot = 0;
				
				 String _ak=	System.getProperty("os.name");
			      String _urlPdf = "";
			      if(_ak.startsWith("Windows")){
			    	  _urlPdf = "D:/fileImgSk/customer_"+custcode+"_faktur.pdf";
			      }else{
			    	  _urlPdf = "/fileImgSk/customer_"+custcode+"_faktur.pdf";
			      }
				doc.setMargins(5, 5, 5, 5);
				PdfWriter.getInstance(doc, new FileOutputStream(_urlPdf));
				doc.open();
				
				
				PdfPTable _table = new PdfPTable(10);
				_table.setWidthPercentage(100);
				_table.setWidths(new float[]{1,2,2,2,5,2,2,2,2,2}); // UNTUK MENGATUR KAN UKURAN CELL
				_table.setTotalWidth(100f);
				_table.setHeaderRows(1);
				_table.addCell(new Phrase("No\nUrut",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Tanggal",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("No. Bukti",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Salesman",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Nama Customer",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Kota",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Bruto (Rp)",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Netto (Rp)",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Rp ---",new Font(FontFamily.TIMES_ROMAN, 10)));
				_table.addCell(new Phrase("Disc --",new Font(FontFamily.TIMES_ROMAN, 10)));
				 
				try {
					  String myDriver = "org.gjt.mm.mysql.Driver";
				      String myUrl = "jdbc:mysql://localhost/sk-gudang";
				      Class.forName(myDriver);
				      
				      BigDecimal _totBrut = BigDecimal.ZERO;
				      BigDecimal _totNett =  BigDecimal.ZERO;
				      BigDecimal _totTotDisc = BigDecimal.ZERO;
				      BigDecimal _totTotDiscPersen = BigDecimal.ZERO;
				      
				      Connection conn = null;
				      if(ExportToPdf._osName.startsWith("Windows")){
				    	  conn = (Connection) DriverManager.getConnection(myUrl, "root", "");
				      }else{
				    	  conn = (Connection) DriverManager.getConnection(myUrl, "radot", "p@ssw0rd");
				      }
				      String query = "SELECT p.tanggal_order ,p.no_order ,c.cust_name ,c.cust_city ,s.salesman_code, dj.disc_persen as disc, p.ppn as ppnJual ,p.tot_penjualan_nett_idr ,p.tot_penjualan_bruto_idr ,p.tot_disc_penjualan ,avg(dj.disc_persen) as avgdiscpersen FROM sk_penjualan p join sk_salesman s on p.salesman_code_fk = s.rec_id join sk_customers c on p.customer_code_fk = c.rec_id join sk_detail_jual dj on p.rec_id = dj.penjualan_fk";
				      		 query = query+" WHERE customer_code_fk = "+_by+" and tanggal_order between '"+dateFrom+"' and '"+dateTo+"' and dj.disc_persen between '"+discMin+"' and '"+discMax+"' group by dj.penjualan_fk ORDER BY tanggal_order DESC ";
				      //System.out.println(query);
				      st = conn.createStatement();
				      rs = st.executeQuery(query);
				      while (rs.next())
				      {
				    	  _tot = _tot+1;
						String _a ="";
						PdfPCell _cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						
						_a = String.valueOf(_tot);
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
					
						_cell.setBorderWidthLeft(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						

						_a = dt1.format(rs.getDate("tanggal_order"));
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						_a =rs.getString("no_order");
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						_a =rs.getString("salesman_code");
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						_a = rs.getString("cust_name");
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);

						
						_a =rs.getString("cust_city");
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
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
						
						
						BigDecimal _totDispersen = rs.getBigDecimal("avgdiscpersen");
						_totBrut = _totBrut.add(_bruto);
						_totNett = _totNett.add(_nett);
						_totTotDisc = _totTotDisc.add(_totDisc);
						_totTotDiscPersen = _totTotDiscPersen.add(_totDispersen);
						_a =convertRp(_bruto);
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						_a =convertRp(_nett);
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						_a =convertRp(_totDisc);
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
						_a =_totDispersen.divide(BigDecimal.ONE, 2, RoundingMode.HALF_UP).toString()+" %";
						_cell = new PdfPCell(new Phrase(_a,new Font(FontFamily.TIMES_ROMAN, 10)));
						_cell.setBorder(1);
						_cell.setBorderWidthRight(1);
						_table.addCell(_cell);
						
				      }	
				      // ADD TOTAL
				      PdfPCell _cell = new PdfPCell(new Phrase("",new Font(FontFamily.TIMES_ROMAN, 10)));
				      _cell = new PdfPCell(new Phrase("",new Font(FontFamily.TIMES_ROMAN, 10)));
				      _cell.setBorder(1);
				      _cell.setBorderWidthTop(1);
				      _table.addCell(_cell);
				      _table.addCell(_cell);
				      _table.addCell(_cell);
				      _table.addCell(_cell);
				      _table.addCell(_cell);
				      _cell = new PdfPCell(new Phrase("Total ",new Font(FontFamily.TIMES_ROMAN, 10)));
				      _cell.setBorder(1);
				      _cell.setBorderWidthTop(1);
				      _table.addCell(_cell);
				      _cell = new PdfPCell(new Phrase(convertRp(_totBrut),new Font(FontFamily.TIMES_ROMAN, 10)));
				      _cell.setBorder(1);
				      _cell.setBorderWidthTop(1);
				      _table.addCell(_cell);
				      _cell = new PdfPCell(new Phrase(convertRp(_totNett),new Font(FontFamily.TIMES_ROMAN, 10)));
				      _cell.setBorder(1);
				      _cell.setBorderWidthTop(1);
				      _table.addCell(_cell);
				      _cell = new PdfPCell(new Phrase(convertRp(_totTotDisc),new Font(FontFamily.TIMES_ROMAN, 10)));
				      _cell.setBorder(1);
				      _cell.setBorderWidthTop(1);
				      _table.addCell(_cell);
				      BigDecimal _avgTotDiscPerser = BigDecimal.ZERO;
				      if(_totTotDiscPersen != BigDecimal.ZERO){
				    	  _avgTotDiscPerser = _totTotDiscPersen.divide(BigDecimal.valueOf(Long.valueOf(_tot)), 2,RoundingMode.HALF_UP);
				      }
				      _cell = new PdfPCell(new Phrase(_avgTotDiscPerser.toString()+" %",new Font(FontFamily.TIMES_ROMAN, 10)));
				      _cell.setBorder(1);
				      _cell.setBorderWidthTop(1);
				      _table.addCell(_cell);
				      st.close();
				      conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				Paragraph _p = new Paragraph("LAPORAN PENJUALAN CUSTOMER PERIODE "+dateFrom+" s/d "+dateTo+"\n\n" , new Font(FontFamily.TIMES_ROMAN, 10));
				_p.setAlignment(Element.ALIGN_LEFT);
				
			
				doc.add(_p);
				/*doc.add(new Paragraph("\n"));*/
				
				doc.add(_table);
				SimpleDateFormat dt2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				doc.add(new Paragraph("PRINTED BY : " + _userName.toUpperCase() +" "+dt2.format(new Date())));
				doc.close();
			} catch (FileNotFoundException e) {
				System.out.println("masuk");
				e.printStackTrace();
			} catch (DocumentException e) {
				System.out.println("masuk f");
				e.printStackTrace();
			}
		return "";
	}
	
	}
	
	public static void main(String[] args) {
	 System.out.println(Md5.decript("123456"));
		
	}
}
