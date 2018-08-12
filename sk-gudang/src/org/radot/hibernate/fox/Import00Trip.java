package org.radot.hibernate.fox;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.radot.hibernate.entities.TripEntity;
import org.radot.hibernate.entities.VendorEntity;
import org.radot.hibernate.persistences.VendorPersistence;


public class Import00Trip {

	public static void main(String[] args) throws Throwable {
				
		final Connection _conn = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Visual FoxPro Driver};SourceType=DBF;SourceDB=D:\\projects\\project-2\\sk-gudang\\dbf;");
		final PreparedStatement _ps = _conn.prepareStatement("SELECT * FROM HTRANSB");
		final ResultSet _rs = _ps.executeQuery();
		
while (_rs.next()) {
			
			try {
				VendorPersistence _v = new VendorPersistence();
				VendorEntity _ve = _v.getByVendId(_rs.getString("ID_VEND").trim());
				
				final TripEntity _ent = new TripEntity();
				_ent.setVendorEnt(_ve);
				_ent.setKodeTrip(_rs.getString("IDHTRANSB").trim());
				_ent.setTrip_date(_rs.getDate("TGL_TRIP"));
				_ent.setTrip_numb(_rs.getString("NO_TRIP").trim());
				_ent.setTrip_dateNote(_rs.getDate("TGL_NOTA"));
				_ent.setTrip_noteNumber(_rs.getString("NO_NOTA").trim());
				_ent.setCurrencyIDR(_rs.getBigDecimal("RATE"));
				_ent.setDateReceive(_rs.getDate("TGL_TERIMA"));
				_ent.setTotPembelianNettoVta(_rs.getBigDecimal("N_NETTO"));
				_ent.setTotPembelianBrutoVta(_rs.getBigDecimal("N_BRUTO"));
				_ent.setTotCarton(_rs.getBigDecimal("J_KOLI"));
				_ent.setTotCost(_rs.getBigDecimal("N_KSALES"));
				_ent.setTotDisc(_rs.getBigDecimal("P_DISC1"));
				
				
				
				
				BigDecimal _totPembelianBrutoVta = (_rs.getBigDecimal("N_BRUTO"));
				
				BigDecimal _kurs = (_rs.getBigDecimal("RATE"));
				
				/*if (_kurs == null) 
				{
					BigDecimal _kursA = new BigDecimal("1") ;
				}else {
					BigDecimal _kursA = new BigDecimal("RATE");
				}*/
				
				BigDecimal _disc = (_rs.getBigDecimal("P_DISC1"));
				BigDecimal _cepe = new BigDecimal(100);
				BigDecimal _totDisc = _disc.divide(_cepe);
				
				
				BigDecimal _totPembelianBrutoIdr = _totPembelianBrutoVta.multiply(_kurs);		
				BigDecimal _totNilaiDisc = _totPembelianBrutoIdr.multiply(_totDisc);
				BigDecimal _totPembelianNettoIdr = _totPembelianBrutoIdr.subtract(_totNilaiDisc);
				
				
				_ent.setTotPembelianNettoIdr(_totPembelianNettoIdr);
				_ent.setTotPembelianBrutoIdr(_totPembelianBrutoIdr);
				
				
				_ent.save();
			} catch (final Throwable t) {
				t.printStackTrace();
			}
			System.out.println("Selesai");
		}
		
		_rs.close();
		_ps.close();
		_conn.close();
		
	}
	
}
