package org.radot.hibernate.fox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.radot.hibernate.entities.CustomerEntity;


public class ReadFox {

	public static void main(String[] args) throws Throwable {
		
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		
		final Connection _conn = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Visual FoxPro Driver};SourceType=DBF;SourceDB=D:\\Workspace\\Private\\sk-gudang\\dbf;");
		final PreparedStatement _ps = _conn.prepareStatement("SELECT * FROM CUSTOMER");
		final ResultSet _rs = _ps.executeQuery();
		
		while (_rs.next()) {
			CustomerEntity _cust = new CustomerEntity();
			try {
				_cust.setCode(_rs.getString("km_cust").trim());
				_cust.setName(_rs.getString("nm_cust").trim());
				_cust.save();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			System.out.println();
		}
		
		_rs.close();
		_ps.close();
		_conn.close();
		
	}
	
}
