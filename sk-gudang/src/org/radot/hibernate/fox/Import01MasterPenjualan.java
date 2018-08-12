package org.radot.hibernate.fox;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.radot.hibernate.entities.CustomerEntity;
import org.radot.hibernate.entities.PenjualanEntity;
import org.radot.hibernate.entities.SalesmanEntity;
import org.radot.hibernate.persistences.CustomerPersistence;
import org.radot.hibernate.persistences.SalesmanPersistence;


public class Import01MasterPenjualan {

	public static void main(String[] args) throws Throwable {
				
		final Connection _conn = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Visual FoxPro Driver};SourceType=DBF;SourceDB=" + FoxCfg.FOX_PATH + ";");
		final PreparedStatement _ps = _conn.prepareStatement("SELECT * FROM HTRANSJ");
//		>= Convert(datetime, '2010-04-01' )
		final ResultSet _rs = _ps.executeQuery();
		System.out.println(_rs.getRow());
		while (_rs.next()) {
			
			try {
				
				SalesmanPersistence _s = new SalesmanPersistence();
				SalesmanEntity _se = _s.getByCode(_rs.getString("SALES").trim());
				
				CustomerPersistence _c = new CustomerPersistence();
				CustomerEntity _ce = _c.getByCustId(_rs.getString("ID_CUST").trim());
				
				final PenjualanEntity _ent = new PenjualanEntity();
				if(_ent !=null){
				_ent.setSalesmanEnt(_se);
				_ent.setCustomerEnt(_ce);
				_ent.setKodeJual(_rs.getString("IDHTRANS").trim());
				_ent.setOrderDate(_rs.getDate("TGL_NOTA"));
				
				
				_ent.setOrderNumb(_rs.getString("NO_NOTA").trim());
				_ent.setFakturNumb(_rs.getString("NO_KIRIM").trim());
				}
				
				_ent.setTotDiscPenjualan(_rs.getBigDecimal("N_DISC1"));
				_ent.setTotPenjualanBrutoIdr(_rs.getBigDecimal("N_BRUTO"));
				_ent.setTotPenjualanNettIdr(_rs.getBigDecimal("N_NETTO"));
				_ent.setTotPpn(_rs.getBigDecimal("N_PPN"));
				BigDecimal _default = new BigDecimal(0);
				_ent.setPpn(_default);
				
				_ent.setTerkirim("Terkirim");
				_ent.save();
				//System.out.println("IDHTRANS = "+ _rs.getTime("IDHTRANS"));
			} 
			catch (final Throwable t) {
				//System.out.println("error " +_rs.getString("NO_KIRIM").trim()+" ++ "+_rs.getString("NO_NOTA").trim());
				t.printStackTrace();
			}
			
		}
		
		_rs.close();
		_ps.close();
		_conn.close();
		
	}
	
}
