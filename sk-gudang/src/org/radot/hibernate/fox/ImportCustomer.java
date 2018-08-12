package org.radot.hibernate.fox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.radot.hibernate.entities.CustomerEntity;


public class ImportCustomer {

	public static void main(String[] args) throws Throwable {
		
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		
		final Connection _conn = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Visual FoxPro Driver};SourceType=DBF;SourceDB=D:\\projects\\project-2\\sk-gudang\\dbf;");
		final PreparedStatement _ps = _conn.prepareStatement("SELECT * FROM CUSTOMER");
		final ResultSet _rs = _ps.executeQuery();
		
		while (_rs.next()) {
			try {
				final CustomerEntity _cust = new CustomerEntity();
				String _nama =_rs.getString("nm_cust").trim();
				_nama =  _nama.replace("'", "");
				
				_cust.setCustId(_rs.getString("IDCUST").trim());
				_cust.setCode(_rs.getString("kd_cust").trim());
				_cust.setName(_nama);
				_cust.setAddress1(_rs.getString("al_cust1").trim());
				_cust.setAddress2(_rs.getString("al_cust2").trim());
				_cust.setCity(_rs.getString("kota").trim());
				_cust.setPhone(_rs.getString("no_telp").trim());
				_cust.setFax(_rs.getString("no_fax").trim());
				_cust.setTax(_rs.getString("npwp").trim());
				_cust.setContact(_rs.getString("kontak").trim());
				
				_cust.save();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println();
		}
		
		_rs.close();
		_ps.close();
		_conn.close();
		
	}
	
}
