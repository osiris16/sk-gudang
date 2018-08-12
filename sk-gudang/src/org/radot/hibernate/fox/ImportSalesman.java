package org.radot.hibernate.fox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.radot.hibernate.entities.SalesmanEntity;


public class ImportSalesman {

	public static void main(String[] args) throws Throwable {
		
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		
		final Connection _conn = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Visual FoxPro Driver};SourceType=DBF;SourceDB=D:\\projects\\project-2\\sk-gudang\\dbf;");
		final PreparedStatement _ps = _conn.prepareStatement("SELECT * FROM SALESMAN");
		final ResultSet _rs = _ps.executeQuery();
		
		while (_rs.next()) {
			try {
				final SalesmanEntity _ent = new SalesmanEntity();
				_ent.setCode(_rs.getString("kode").trim());
				_ent.setName(_rs.getString("nama").trim());
				_ent.setAddress1(_rs.getString("alamat1"));
				_ent.setAddress2(_rs.getString("alamat2"));
				_ent.setPhone(_rs.getString("hp"));
				
				
				_ent.save();
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
