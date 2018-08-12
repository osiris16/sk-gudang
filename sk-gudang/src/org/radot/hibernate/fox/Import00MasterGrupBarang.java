package org.radot.hibernate.fox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.radot.hibernate.entities.ProductGroupEntity;


public class Import00MasterGrupBarang {

	public static void main(String[] args) throws Throwable {
				
		final Connection _conn = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Visual FoxPro Driver};SourceType=DBF;SourceDB=" + FoxCfg.FOX_PATH + ";");
		final PreparedStatement _ps = _conn.prepareStatement("SELECT kelompok FROM neostok");
		final ResultSet _rs = _ps.executeQuery();
		
		while (_rs.next()) {
			
			try {
				final ProductGroupEntity _ent = new ProductGroupEntity();
				_ent.setName(_rs.getString("kelompok").trim());
				_ent.save();
			} catch (final Throwable t) {
				t.printStackTrace();
			}
			
		}
		System.out.println("selesai");
		_rs.close();
		_ps.close();
		_conn.close();
		
	}
	
}
