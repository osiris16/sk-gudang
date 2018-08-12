package org.radot.utils;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

import org.apache.poi.hssf.record.BottomMarginRecord;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class ExportToPdfFaturPenjualan {

	
	static String[] nomina={"","satu","dua","tiga","empat","lima","enam",
            "tujuh","delapan","sembilan","sepuluh","sebelas"};

	public static String bilangx(double angka){
		if(angka<12)
		{
		return nomina[(int)angka];
		}
		
		if(angka>=12 && angka <=19)
		{
		return nomina[(int)angka%10] +" belas ";
		}
		
		if(angka>=20 && angka <=99)
		{
		return nomina[(int)angka/10] +" puluh "+nomina[(int)angka%10];
		}
		
		if(angka>=100 && angka <=199)
		{
		return "seratus "+ bilangx(angka%100);
		}
		
		if(angka>=200 && angka <=999)
		{
		return nomina[(int)angka/100]+" ratus "+bilangx(angka%100);
		}
		
		if(angka>=1000 && angka <=1999)
		{
		return "seribu "+ bilangx(angka%1000);
		}
		
		if(angka >= 2000 && angka <=999999)
		{
		return bilangx((int)angka/1000)+" ribu "+ bilangx(angka%1000);
		}
		
		if(angka >= 1000000 && angka <=999999999)
		{
		return bilangx((int)angka/1000000)+" juta "+ bilangx(angka%1000000);
		}
		
		return "";
	}
	
	
	
	
	public static String printExcel(JsonArray element, JsonArray destination){
		 String _ak=	System.getProperty("os.name");
		 HSSFWorkbook _wb = new HSSFWorkbook();
		 
			HSSFSheet _sheet = _wb.createSheet("faktur"+"_"+destination.get(0).toString().replace("\"", ""));
			int _row = 4;
			HSSFRow row = _sheet.createRow((short)0);
		    row = _sheet.createRow((short)0);
		    HSSFCell _cell = row.createCell((short)0);
		    HSSFCellStyle _style =  _wb.createCellStyle();
		    _style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		    _cell.setCellValue(new HSSFRichTextString("texttest"));
		    _cell.setCellStyle(_style);
		    HSSFRichTextString _des = new HSSFRichTextString("No Faktur ");
		    row = _sheet.createRow((short)3);
		     row.createCell((short) 0).setCellValue(_des);
		     _des = new HSSFRichTextString(destination.get(0).toString().replace("\"", ""));
		     row.createCell((short) 1).setCellValue(_des);
		     
		     row = _sheet.createRow((short)1);
			 _des = new HSSFRichTextString("Tanggal Faktur ");
			 row.createCell((short) 0).setCellValue(_des);
			 _des = new HSSFRichTextString(destination.get(1).toString().replace("\"", ""));
			 row.createCell((short) 1).setCellValue(_des);
		     
		    //HEADER
		      row = _sheet.createRow((short)_row); 
		    HSSFRichTextString _s = new HSSFRichTextString("No Karton");
		    row.createCell((short) 0).setCellValue(_s);
		    _s = new HSSFRichTextString("Banyaknya");
		    row.createCell((short) 1).setCellValue(_s);
		    _s = new HSSFRichTextString("Ctn");
		    row.createCell((short) 2).setCellValue(_s);
		    _s = new HSSFRichTextString("Nama Barang");
		    row.createCell((short) 3).setCellValue(_s);
		    _s = new HSSFRichTextString("Harga Satuan");
		    row.createCell((short) 4).setCellValue(_s);
		    _s = new HSSFRichTextString("Disc %");
		    row.createCell((short) 5).setCellValue(_s);
		    _s = new HSSFRichTextString("Harga Netto");
		    row.createCell((short) 6).setCellValue(_s);
		    _s = new HSSFRichTextString("Jumlah Netto (Rp)");
		    
		  
		    //END Header
		    try {
		    	for (int i = 0; i < element.size(); i++) {
		    		JsonParser jsonParser = new JsonParser();
		    		String _aElem = element.get(i).toString();
		    		JsonArray _jsonArray = (JsonArray) jsonParser.parse(_aElem);
		    		
		    		_row = _row+1;
			    	row = _sheet.createRow((short)_row);
			    	_s = new HSSFRichTextString(String.valueOf(_jsonArray.get(0).toString().replace("\"", ""))); 
			    	row.createCell((short) 0).setCellValue(_s);
			    	  
			    	_s = new HSSFRichTextString(_jsonArray.get(1).toString().replace("\"", ""));
					row.createCell((short) 1).setCellValue(_s);
					
					_s = new HSSFRichTextString(_jsonArray.get(2).toString().replace("\"", ""));
					row.createCell((short) 2).setCellValue(_s);
					
					_s = new HSSFRichTextString(_jsonArray.get(3).toString().replace("\"", "")+" "+ _jsonArray.get(4).toString().replace("\"", ""));
					row.createCell((short) 3).setCellValue(_s);
					
					_s = new HSSFRichTextString(_jsonArray.get(5).toString().replace("\"", ""));
					row.createCell((short) 4).setCellValue(_s);
					
					_s = new HSSFRichTextString(_jsonArray.get(6).toString().replace("\"", ""));
					row.createCell((short) 5).setCellValue(_s);
					
					_s = new HSSFRichTextString(_jsonArray.get(7).toString().replace("\"", ""));
					row.createCell((short) 6).setCellValue(_s);
				
		    	}
		    	_wb.getSheetAt(0).autoSizeColumn((short) 0);
			      _wb.getSheetAt(0).autoSizeColumn((short) 1);
			      _wb.getSheetAt(0).autoSizeColumn((short) 2);
			      _wb.getSheetAt(0).autoSizeColumn((short) 3);
			      _wb.getSheetAt(0).autoSizeColumn((short) 4);
			      _wb.getSheetAt(0).autoSizeColumn((short) 5);
			      _wb.getSheetAt(0).autoSizeColumn((short) 6);
		    	 FileOutputStream fileOut = null;
			      if(_ak.startsWith("Windows")){
			    	  fileOut = new FileOutputStream("C:/work/back up/projects/project-3/fileImgSk/"+destination.get(0).toString().replace("\"", "")+".xls");
			      }else{
			    	  fileOut = new FileOutputStream("../fileImgSk/"+destination.get(0).toString().replace("\"", "")+".xls");
			      }
			     
			      _wb.write(fileOut);
				    fileOut.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		  
		return "";
	}
	
	public static String print(JsonArray element, JsonArray destination, Boolean type){
		
			Document doc = new Document(PageSize.LETTER);
			//BaseFont _fontFaktur = BaseFont.createFont("D:/fileImgSk/font/Tahoma.ttf", BaseFont.WINANSI);
			String _ifSuratJalan =  destination.get(0).toString().replace("\"", "");
			try {
			      int _tot = 0;
			      String _ak=	System.getProperty("os.name");
			      String _urlPdf = "";
			      
			      if(type){
			    	  _ifSuratJalan = "JALAN"+String.valueOf(new Date().getTime());
						
			      }
			      Font _fontFakt = null;
			      if(_ak.startsWith("Windows")){
			    	  _urlPdf = "C:/work/back up/projects/project-3/fileImgSk/"+_ifSuratJalan+".pdf";
			    	   _fontFakt = FontFactory.getFont("C:/work/back up/projects/project-3/fileImgSk/font/Arial.ttf");
						
			      }else{
			    	  _urlPdf = "/fileImgSk/"+_ifSuratJalan+".pdf";
			    	  _fontFakt = FontFactory.getFont("../fileImgSk/font/Arial.ttf");
			      }
			      BaseFont _font = _fontFakt.getBaseFont();
			      
				doc.setMargins(30, 30, 70, 5);
				PdfWriter.getInstance(doc, new FileOutputStream(_urlPdf));
				doc.open();
				
				//header Data
				PdfPTable _tableHeader = new PdfPTable(4);
				_tableHeader.setWidthPercentage(100);
				_tableHeader.setWidths(new float[]{4,1,10,15}); // UNTUK MENGATUR KAN UKURAN CELL
				_tableHeader.setTotalWidth(100f);
				PdfPCell _cellHeader = new PdfPCell(new Phrase("NO FAKTUR",new Font(_font, 10)));
				_cellHeader.setBorder(0);
				_tableHeader.addCell(_cellHeader);

				_cellHeader = new PdfPCell(new Phrase(":",new Font(_font, 10)));
				
				_cellHeader.setBorder(0);
				
				_tableHeader.addCell(_cellHeader);
				
				if(type){
					_cellHeader = new PdfPCell(new Phrase(" - ",new Font(_font, 10)));
				}else{
					_cellHeader = new PdfPCell(new Phrase(destination.get(0).toString().replace("\"", ""),new Font(_font, 10)));
				}
				_cellHeader.setBorder(0);
				_cellHeader.setBorderWidthRight(1);
				_tableHeader.addCell(_cellHeader);
				_cellHeader = new PdfPCell(new Phrase("Kepada Yth,",new Font(_font, 10)));
				
				_cellHeader.setBorder(0);
				
				_tableHeader.addCell(_cellHeader);
				
				//Tanggal
				_cellHeader = new PdfPCell(new Phrase("TANGGAL",new Font(_font, 10)));
				_cellHeader.setBorder(0);
				_tableHeader.addCell(_cellHeader);

				_cellHeader = new PdfPCell(new Phrase(":",new Font(_font, 10)));
				
				_cellHeader.setBorder(0);
				
				_tableHeader.addCell(_cellHeader);
				if(type){
					_cellHeader = new PdfPCell(new Phrase(" - ",new Font(_font, 10)));
				}else{
					_cellHeader = new PdfPCell(new Phrase(destination.get(1).toString().replace("\"", ""),new Font(_font, 10)));
				}
				
				_cellHeader.setBorder(0);
				_cellHeader.setBorderWidthRight(1);
				
				_tableHeader.addCell(_cellHeader);
				_cellHeader = new PdfPCell(new Phrase(destination.get(2).toString().replace("\"", ""),new Font(_font, 10)));
				_cellHeader.setBorder(0);
				
				_tableHeader.addCell(_cellHeader);
				//sales
				_cellHeader = new PdfPCell(new Phrase("==============================================",new Font(_font, 10)));
				_cellHeader.setBorder(0);
				_cellHeader.setColspan(3);
				
				_cellHeader.setBorder(0);
				_cellHeader.setBorderWidthRight(1);
				_tableHeader.addCell(_cellHeader);
				
				
				_cellHeader = new PdfPCell(new Phrase(destination.get(3).toString().replace("\"", ""),new Font(_font, 10)));
				_cellHeader.setBorder(0);
				//---
				_tableHeader.addCell(_cellHeader);
				_cellHeader = new PdfPCell(new Phrase("S",new Font(_font, 10)));
				_cellHeader.setBorder(0);
				_tableHeader.addCell(_cellHeader);

				_cellHeader = new PdfPCell(new Phrase(":",new Font(_font, 10)));
				_cellHeader.setBorder(0);
				_tableHeader.addCell(_cellHeader);
				
				_cellHeader = new PdfPCell(new Phrase(destination.get(5).toString().replace("\"", "")+" || "+destination.get(9).toString().replace("\"", ""),new Font(_font, 10)));
				_cellHeader.setBorder(0);
				
				_cellHeader.setBorderWidthRight(1);
				_tableHeader.addCell(_cellHeader);
				_cellHeader = new PdfPCell(new Phrase(destination.get(4).toString().replace("\"", ""),new Font(_font, 10)));
				_cellHeader.setBorder(0);
				
				_tableHeader.addCell(_cellHeader);
				//end header data
				
				//Body data
				
				PdfPTable _table = new PdfPTable(10);
				
				_table.setWidthPercentage(100);
				_table.setWidths(new float[]{5,5,4,12,17,9,4,9,10,1}); // UNTUK MENGATUR KAN UKURAN CELL
				_table.setTotalWidth(100f);
				_table.setHeaderRows(1);
				int BorderW = 1;
				PdfPCell _cell2 = new PdfPCell(new Phrase("No. Karton",new Font(_font, 10)));
				_cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				_cell2.setBorder(Rectangle.BOX);
				_table.addCell(_cell2);
				_cell2 = new PdfPCell(new Phrase("PCS",new Font(_font, 10)));
				_cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				_cell2.setBorder(Rectangle.BOX);
				_table.addCell(_cell2);
				_cell2 = new PdfPCell(new Phrase("CTN",new Font(_font, 10)));
				_cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				_cell2.setBorder(Rectangle.BOX);
				_table.addCell(_cell2);
				_cell2 = new PdfPCell(new Phrase("Kd Barang",new Font(_font, 10)));
				_cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				_cell2.setBorder(Rectangle.BOX);
				_table.addCell(_cell2);
				_cell2 = new PdfPCell(new Phrase("Nama Barang",new Font(_font, 10)));
				_cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				_cell2.setBorder(Rectangle.BOX);
				_table.addCell(_cell2);
				_cell2 = new PdfPCell(new Phrase("Harga/Pcs",new Font(_font, 10)));
				_cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				_cell2.setBorder(Rectangle.BOX);
				_table.addCell(_cell2);
				_cell2 = new PdfPCell(new Phrase("Disc %",new Font(_font, 10)));
				_cell2.setBorder(Rectangle.BOX);
				_table.addCell(_cell2);
				_cell2 = new PdfPCell(new Phrase("H.Nett/Pcs",new Font(_font, 10)));
				_cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				_cell2.setBorder(Rectangle.BOX);
				_table.addCell(_cell2);
				_cell2 = new PdfPCell(new Phrase("Rp.Harga Nett",new Font(_font, 10)));
				_cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				_cell2.setBorder(Rectangle.BOX);
				_table.addCell(_cell2);
				_cell2 = new PdfPCell(new Phrase("",new Font(_font, 10)));
				_cell2.setBorder(Rectangle.NO_BORDER);
				_table.addCell(_cell2);
				_table.setHorizontalAlignment(Element.ALIGN_CENTER);
			
				try {
				    	for (int i = 0; i < element.size(); i++) {
							//array_type array_element = element[i];
				    		JsonParser jsonParser = new JsonParser();
				    		String _aElem = element.get(i).toString();
				    		JsonArray _jsonArray = (JsonArray) jsonParser.parse(_aElem);
				    	Paragraph _p = new Paragraph();
				    	_tot = _tot+1;
						String _a ="";
						PdfPCell _cell = new PdfPCell(new Phrase(_a,new Font(_font, 9)));
						
						
						_a = _jsonArray.get(0).toString();
						_cell = new PdfPCell(new Phrase(_a.replace("\"", ""),new Font(_font, 9)));
						_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						_cell.setBorder(Rectangle.LEFT);
						_table.addCell(_cell);
						/*_p = new Paragraph(new Phrase(_a.replace("\"", ""),new Font(_font, 9)));
						_p.setAlignment(Element.ALIGN_CENTER);
						_cell = new PdfPCell();
						_cell.addElement(_p);
						_cell.setBorder(Rectangle.LEFT);
						_table.addCell(_cell);*/
						

						_a = _jsonArray.get(1).toString();
						_cell = new PdfPCell(new Phrase(_a.replace("\"", ""),new Font(_font, 9)));
						_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						_cell.setBorder(Rectangle.LEFT);
						_table.addCell(_cell);
						
						_a =_jsonArray.get(2).toString();
						_cell = new PdfPCell(new Phrase(_a.replace("\"", ""),new Font(_font, 9)));
						_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						_cell.setBorder(Rectangle.LEFT);
						
						_table.addCell(_cell);
						
						_a =_jsonArray.get(3).toString();
						_cell = new PdfPCell(new Phrase(_a.replace("\"", ""),new Font(_font, 9)));
						_cell.setBorder(Rectangle.LEFT);
						
						_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						_table.addCell(_cell);
						
						_a = _jsonArray.get(4).toString();
						_cell = new PdfPCell(new Phrase(_a.replace("\"", ""),new Font(_font, 9)));
						_cell.setBorder(Rectangle.LEFT);
						_table.addCell(_cell);
						
					
						_a =_jsonArray.get(5).toString();
						_cell = new PdfPCell(new Phrase(_a.replace("\"", ""),new Font(_font, 9)));
						_cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						_cell.setBorder(Rectangle.LEFT);
						_table.addCell(_cell);
						
						_a =_jsonArray.get(6).toString();
						_cell = new PdfPCell(new Phrase(_a.replace("\"", ""),new Font(_font, 9)));
						_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						_cell.setBorder(Rectangle.LEFT);
						_table.addCell(_cell);
						
						_a =_jsonArray.get(7).toString();
						_cell = new PdfPCell(new Phrase(_a.replace("\"", ""),new Font(_font, 9)));
						_cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						_cell.setBorder(Rectangle.LEFT);
						
						_table.addCell(_cell);
						
						_a =_jsonArray.get(8).toString();
						_cell = new PdfPCell(new Phrase(_a.replace("\"", ""),new Font(_font, 9)));
						_cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						_cell.setBorder(Rectangle.LEFT);
						_table.addCell(_cell);
						
						_cell = new PdfPCell(new Phrase("",new Font(_font, 10)));
						_cell.setBorder(Rectangle.LEFT);
						_cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						_table.addCell(_cell);
						
				      }
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				int maxtotData = 20;
				int _sizeElem = element.size();
				int ModuloElemen = _sizeElem%maxtotData;
				if(_sizeElem<24){
					ModuloElemen = maxtotData-_sizeElem;
					
				}else{
					ModuloElemen = maxtotData-ModuloElemen;
					//System.out.println("masuk 2");
				}
				
				for (int i = 0; i < ModuloElemen; i++) {
					PdfPCell _cell = new PdfPCell(new Phrase("  ",new Font(_font, 9)));
					
					_cell.setBorder(Rectangle.LEFT);
					_table.addCell(_cell);
					_cell = new PdfPCell(new Phrase("  ",new Font(_font, 9)));
					_cell.setBorder(Rectangle.LEFT);
					_table.addCell(_cell);
					_cell = new PdfPCell(new Phrase("  ",new Font(_font, 9)));
					_cell.setBorder(Rectangle.LEFT);
					_table.addCell(_cell);
					_cell = new PdfPCell(new Phrase("  ",new Font(_font, 9)));
					_cell.setBorder(Rectangle.LEFT);
					_table.addCell(_cell);
					_cell = new PdfPCell(new Phrase("  ",new Font(_font, 9)));
					_cell.setBorder(Rectangle.LEFT);
					_table.addCell(_cell);
					_cell = new PdfPCell(new Phrase("  ",new Font(_font, 9)));
					_cell.setBorder(Rectangle.LEFT);
					_table.addCell(_cell);
					_cell = new PdfPCell(new Phrase("  ",new Font(_font, 9)));
					_cell.setBorder(Rectangle.LEFT);
					_table.addCell(_cell);
					_cell = new PdfPCell(new Phrase("  ",new Font(_font, 9)));
					_cell.setBorder(Rectangle.LEFT);
					_table.addCell(_cell);
					
					_cell = new PdfPCell(new Phrase("  ",new Font(_font, 9)));
					_cell.setBorder(Rectangle.LEFT);
					_table.addCell(_cell);
					
					_cell = new PdfPCell(new Phrase("",new Font(_font, 10)));
					_cell.setBorder(Rectangle.LEFT);
					_table.addCell(_cell);
					
					
				}
				Paragraph _p = new Paragraph("INVOICE" , new Font(_font, 11));
				if(type){
					_p = new Paragraph("SURAT JALAN PENJUALAN" , new Font(_font, 11));
				}
				_p.setAlignment(Element.ALIGN_CENTER);
				
				Paragraph _NettoBeforePpn = new Paragraph("  Total = RP "+destination.get(6).toString().replace("\"", ""), new Font(_font, 10));
				_NettoBeforePpn.setAlignment(Element.ALIGN_RIGHT);
				Paragraph _ppn = new Paragraph("  PPN  = RP "+destination.get(7).toString().replace("\"", ""), new Font(_font, 10));
				_ppn.setAlignment(Element.ALIGN_RIGHT);
				
				
				Paragraph _totalNetto= new Paragraph("  Total Netto = RP "+destination.get(8).toString().replace("\"", ""), new Font(_font, 10));
				
				_totalNetto.setAlignment(Element.ALIGN_RIGHT);
				Paragraph _n = new Paragraph("\n" , new Font(_font, 20));
				Paragraph _linel = new Paragraph("==============================================================================================" , new Font(_font, 10));
				_n.setAlignment(Element.ALIGN_CENTER);
				
				
				doc.add(_p);
				
				doc.add(_linel);
				doc.add(_tableHeader);
				doc.add(_linel);
			
				doc.add(_table);
				doc.add(_linel);
				Double nettoTOtal = new Double(destination.get(8).toString().replace("\"", "").replaceAll(",", ""));
				Paragraph _terbilang = new Paragraph("Terbilang : "+bilangx(nettoTOtal), new Font(_font, 8));
				_terbilang.setAlignment(Element.ALIGN_LEFT);
				
			
				
				doc.add(_NettoBeforePpn);
				if(!destination.get(7).toString().replace("\"", "").trim().equalsIgnoreCase("0")){
					doc.add(_ppn);
				}
				doc.add(_totalNetto);
				doc.add(_terbilang);
				Paragraph _hormatKami = new Paragraph("Hormat Kami \n\n\n\n", new Font(_font, 12));
				
				doc.add(_hormatKami);
				Paragraph _namaTTD = new Paragraph(destination.get(10).toString().replace("\"", ""), new Font(_font, 10));
				doc.add(_namaTTD);
				
				doc.close();
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			} catch (DocumentException e) {
				
				e.printStackTrace();
			}
		return _ifSuratJalan;
	}
	public static String setRaw(int lengtChar,String _char){
		String _ret = _char;
		int loop = lengtChar-_char.length();
			if(loop<1){
				_ret = _char;
			}else{
				for (int i = 0; i < loop; i++) {
					_ret = _ret+" ";
				}
			}
			
		return _ret;
	}
	/*public static PrintService findPrintService(String printerName) {

	    PrintService service = null;

	    // Get array of all print services - sort order NOT GUARANTEED!
	    PrintService[] services = PrinterJob.lookupPrintServices();

	    // Retrieve specified print service from the array
	    for (int index = 0; service == null && index < services.length; index++) {

	        if (services[index].getName().equalsIgnoreCase(printerName)) {

	            service = services[index];
	        }
	    }

	    // Return the print service
	    return service;
	}*/
	public static String getUnderline(int callLine){
		String _ret = "";
			for (int i = 0; i < callLine; i++) {
				_ret = _ret+"-";
			}
		return _ret;
	}
	public static String printRawText(JsonArray element, JsonArray destination){
		String _rawColom = "";
		_rawColom = _rawColom+setRaw(20, "NO FAKTUR ")+":"+setRaw(45,destination.get(0).toString().replace("\"", ""))+"| ";
		_rawColom = _rawColom+setRaw(20, "Kepada Yth,")+"\n";
		_rawColom = _rawColom+setRaw(20, "TANGGAL ")+":"+setRaw(45,destination.get(1).toString().replace("\"", ""))+"| ";
		_rawColom = _rawColom+setRaw(20, destination.get(2).toString().replace("\"", ""))+"\n";
		_rawColom = _rawColom+getUnderline(66)+"| "+setRaw(60, destination.get(3).toString().replace("\"", ""))+"\n";
		_rawColom = _rawColom+setRaw(20, "S ")+":"+setRaw(45,destination.get(5).toString().replace("\"", ""))+"| ";//"\n"
		_rawColom = _rawColom+setRaw(40, destination.get(4).toString().replace("\"", ""))+"\n";
		_rawColom = _rawColom+getUnderline(136)+"\n";
		_rawColom = _rawColom+setRaw(10,"Nomor");//Nomor karton
		_rawColom = _rawColom+setRaw(10,"Banyak");//jumlah pcs
		
		_rawColom = _rawColom+"\n";
		_rawColom = _rawColom+setRaw(10,"Karton");//Nomor karton
		_rawColom = _rawColom+setRaw(10,"(PCS)");//jumlah pcs
		_rawColom = _rawColom+setRaw(6,"CTN");//CTN
		_rawColom = _rawColom+setRaw(15,"Kode Barang");//CODE
		_rawColom = _rawColom+setRaw(25,"Nama barang");//NAME
		_rawColom = _rawColom+setRaw(20,"Harga/Pcs");// HARGA
		_rawColom = _rawColom+setRaw(7,"Disc %");// DISC
		_rawColom = _rawColom+setRaw(20,"H.Nett/Pcs");//H.Nett/Pcs
		_rawColom = _rawColom+setRaw(20,"Harga Netto");//Harga Netto
		_rawColom = _rawColom+"\n";
		_rawColom = _rawColom+getUnderline(136)+"\n";
		for (int i = 0; i < element.size(); i++) {
			JsonParser jsonParser = new JsonParser();
    		String _aElem = element.get(i).toString();
    		JsonArray _jsonArray = (JsonArray) jsonParser.parse(_aElem);
    		_rawColom = _rawColom+setRaw(10,_jsonArray.get(0).toString().replace("\"", ""));//Nomor karton
    		_rawColom = _rawColom+setRaw(10,_jsonArray.get(1).toString().replace("\"", ""));//jumlah pcs
    		_rawColom = _rawColom+setRaw(6,_jsonArray.get(2).toString().replace("\"", ""));//CTN
    		_rawColom = _rawColom+setRaw(15,_jsonArray.get(3).toString().replace("\"", ""));//CODE
    		_rawColom = _rawColom+setRaw(25,_jsonArray.get(4).toString().replace("\"", ""));//NAME
    		_rawColom = _rawColom+setRaw(20,_jsonArray.get(5).toString().replace("\"", ""));// HARGA
    		_rawColom = _rawColom+setRaw(7,_jsonArray.get(6).toString().replace("\"", ""));// DISC
    		_rawColom = _rawColom+setRaw(20,_jsonArray.get(7).toString().replace("\"", ""));//H.Nett/Pcs
    		_rawColom = _rawColom+setRaw(20,_jsonArray.get(8).toString().replace("\"", ""));//Harga Netto
    		_rawColom = _rawColom+"\n";
		}
		if(element.size() <75){
			int _selisih = 75-element.size();
			for (int i = 0; i < _selisih; i++) {
				_rawColom = _rawColom+"\n";
			}
		}
		_rawColom = _rawColom+getUnderline(136)+"\n";
		
		
		_rawColom = _rawColom+setRaw(85,"");
		_rawColom = _rawColom+setRaw(36,"Total       = RP "+destination.get(6).toString().replace("\"", ""))+"\n";
		if(!destination.get(7).toString().replace("\"", "").trim().equalsIgnoreCase("0.00")){
			_rawColom = _rawColom+setRaw(85,"");
			_rawColom = _rawColom+setRaw(36,"PPN         = RP "+destination.get(7).toString().replace("\"", ""))+"\n";
		}
		_rawColom = _rawColom+setRaw(85,"");
		_rawColom = _rawColom+setRaw(36,"Total Netto = RP "+destination.get(8).toString().replace("\"", ""))+"\n";
		
		System.out.println(_rawColom);
		return _rawColom;
	}
	public static void main(String[] args) {
		Double k = new Double("17,980,000".replaceAll(",", ""));
		System.out.println(k);
		try {
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
