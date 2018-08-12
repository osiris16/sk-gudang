package org.radot.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.radot.hibernate.entities.DetailPembelianEntity;
import org.radot.hibernate.entities.DetailPenjualanEntity;

public class Md5 {
	
	public static BigDecimal getTotalBruto(List<DetailPenjualanEntity> _detailPenjualan) {
		BigDecimal _totJualBruto = BigDecimal.ZERO;
		
		if(null != _detailPenjualan){
			for (final DetailPenjualanEntity _ent: _detailPenjualan) {
				//try nya disinni kalo null
				_totJualBruto = _totJualBruto.add(_ent.getTotJualBrutoIdr());
				
			}
		}
		return _totJualBruto;
		
	}
	
	public static BigDecimal getTotalNetto(List<DetailPenjualanEntity> _detailPenjualan) {
		
		BigDecimal _totJualNetto = BigDecimal.ZERO;
		BigDecimal _cepe = new BigDecimal(100);
		if(null != _detailPenjualan){
			for (final DetailPenjualanEntity _ent: _detailPenjualan) {
				//try nya disinni kalo null
				BigDecimal _jualBrutoIdr = _ent.getTotJualBrutoIdr();
				BigDecimal _disc = _ent.getDiscPersen();
				BigDecimal _totJualAfterDisc = _jualBrutoIdr.multiply(_disc);
				BigDecimal _totJualAfterDisc2 = _totJualAfterDisc.divide(_cepe,100, RoundingMode.HALF_UP);
				BigDecimal _totJualAfterDiscFinal = _jualBrutoIdr.subtract(_totJualAfterDisc2);
				
				_totJualNetto = _totJualNetto.add(_totJualAfterDiscFinal);
				
			}
		}
		return _totJualNetto;
		
	}
	
	
	
	public static BigDecimal getTotalBrutoBeliIdr(List<DetailPembelianEntity> _detailPembelian) {
		BigDecimal _totBeliBrutoIdr = BigDecimal.ZERO;
		if(null != _detailPembelian){
			for (final DetailPembelianEntity _ent: _detailPembelian) {
				//try nya disinni kalo null
				_totBeliBrutoIdr = _totBeliBrutoIdr.add(_ent.getTotHargaBrutB_Idr());
			}
		}
		return _totBeliBrutoIdr;
		
	}
	
	public static BigDecimal getTotalBrutoBeliVta(List<DetailPembelianEntity> _detailPembelian) {
		BigDecimal _totBeliBrutoVta = BigDecimal.ZERO;
		if(null != _detailPembelian){
			for (final DetailPembelianEntity _ent: _detailPembelian) {
				//try nya disinni kalo null
				_totBeliBrutoVta = _totBeliBrutoVta.add(_ent.getTotHargaBrutB_Vta());
			}
		}
		return _totBeliBrutoVta;
		
	}
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////
	///////PASSWORD/////////////////////
	///////////////////////////
	public static String decript(String _clearText) {
		String passwordToHash = _clearText;
		String generatedPassword = null;
		try {
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("MD5");
			//Add password bytes to digest
			md.update(passwordToHash.getBytes());
			//Get the hash's bytes 
			byte[] bytes = md.digest();
			//This bytes[] has bytes in decimal format;
			//Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for(int i=0; i< bytes.length ;i++)
			{
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			//Get complete hashed password in hex format
			generatedPassword = sb.toString();
		} 
		catch (NoSuchAlgorithmException e) 
		{
			e.printStackTrace();
		}
	
		return generatedPassword;
		
	}
	
	public static void main(String[] args) {
		//misal
		
		
		System.out.println(Md5.decript("1"));
		
//		 ArrayList<Criterion> _arrayCrit = new ArrayList<Criterion>();
//		 	Calendar _startCal = new GregorianCalendar();
//		 	String _starDate = "10/03/2013";
//		 	String _startArray[] = _starDate.split("/");
//		 	
//		 	_startCal.set(GregorianCalendar.YEAR, Integer.valueOf(_startArray[2]));
//		 	_startCal.set(GregorianCalendar.MONTH, Integer.valueOf(_startArray[0]));
//		 	_startCal.set(GregorianCalendar.DATE, Integer.valueOf(_startArray[1]));
//		 	
//		 	Calendar _endCal = new GregorianCalendar();
//		 	String _endDate = "10/03/2017";
//		 	String _endArray[] = _endDate.split("/");
//		 	
//		 	_endCal.set(GregorianCalendar.YEAR, Integer.valueOf(_endArray[2]));
//		 	_endCal.set(GregorianCalendar.MONTH, Integer.valueOf(_endArray[0]));
//		 	_endCal.set(GregorianCalendar.DATE, Integer.valueOf(_endArray[1]));
//			Date _s = new Date();
//			Date _e = new Date();
//			_s.setTime(_startCal.getTimeInMillis());
//			_e.setTime(_endCal.getTimeInMillis());
//			Criterion _start = Restrictions.gt("trip_date", _s);
//			Criterion _end = Restrictions.lt("trip_date", _e);
//		
//			
//			Criterion _between = Restrictions.and(_start, _end);
//			_arrayCrit.add(_between);
//			final List<TripEntity> _entst = BaseEntity.listDataOffset(TripEntity.class, _arrayCrit,null,  null, null);
//			System.out.println(_entst.get(0).getTrip_noteNumber());
		
		
	}
}
