package org.radot.hibernate.fox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.radot.enums.CurrencyType;
import org.radot.hibernate.entities.VendorEntity;


public class Import00Vendor {

	public static void main(String[] args) throws Throwable {
				
		final Connection _conn = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Visual FoxPro Driver};SourceType=DBF;SourceDB=D:\\projects\\project-2\\sk-gudang\\dbf;");
		final PreparedStatement _ps = _conn.prepareStatement("SELECT * FROM VENDORS");
		final ResultSet _rs = _ps.executeQuery();
		
		while (_rs.next()) {
			
			try {
				final VendorEntity _ent = new VendorEntity();
				_ent.setVendId(_rs.getString("ID_VENDOR").trim());
				_ent.setName(_rs.getString("nm_vendor").trim());
				_ent.setAddress1(_rs.getString("alamat_1").trim());
				_ent.setAddress2(_rs.getString("alamat_2").trim());
				_ent.setCity(_rs.getString("kota").trim());
				_ent.setCountry(_rs.getString("negara").trim());
				_ent.setVta(_rs.getString("VTA_JUAL").trim());
				_ent.setTax(_rs.getString("npwp").trim());
				_ent.setCargo(_rs.getString("cargo").trim());
				_ent.setPhone(_rs.getString("no_telp").trim());
				_ent.setFax(_rs.getString("no_fax").trim());
				_ent.setEmail(_rs.getString("no_email").trim());

				_ent.save();
			} catch (final Throwable t) {
				t.printStackTrace();
			}
			
		}
		
		_rs.close();
		_ps.close();
		_conn.close();
		
	}
	
}
