package org.radot.hibernate.fox;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.radot.hibernate.entities.ProductEntity;
import org.radot.hibernate.entities.StockEntity;
import org.radot.hibernate.persistences.ProductPersistence;


public class Import01MasterStockBarang {

	@SuppressWarnings("null")
	public static void main(String[] args) throws Throwable {
				
		final Connection _conn = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Visual FoxPro Driver};SourceType=DBF;SourceDB=" + FoxCfg.FOX_PATH + ";");
		final PreparedStatement _ps = _conn.prepareStatement("SELECT * FROM neostok");
		final ResultSet _rs = _ps.executeQuery();
		
		while (_rs.next()) {
			
			try {
				
				ProductPersistence _p = new ProductPersistence();
				ProductEntity _pe = _p.getByProdId(_rs.getString("ID_BRGDG").trim());
				
				final StockEntity _ent = new StockEntity();
				_ent.setProductEnt(_pe);
				BigDecimal _stokAwal = _rs.getBigDecimal("QTYOPN");
				BigDecimal _stokBeli = _rs.getBigDecimal("QTYBELI");
				BigDecimal _stokJual = _rs.getBigDecimal("QTYJUAL");
				BigDecimal _returJual = _rs.getBigDecimal("QTYRETJ");
				BigDecimal _returBeli = _rs.getBigDecimal("QTYRETB");
				BigDecimal _stokCtn = _stokAwal.add(_stokBeli).add(_returJual).subtract(_stokJual).subtract(_returBeli);
				BigDecimal _default = new BigDecimal(0);
				
				if (_stokCtn == null){
					_ent.setStokCtn(_default);
				}
				else{
					_ent.setStokCtn(_stokCtn);
				}
				/*BigDecimal _isiCtn =(_rs.getBigDecimal("ISI_CTN"));
				
				Integer _isiPcs = 0 ;
				String _a = _rs.getString("ISI_PCS");
				if (_a.equalsIgnoreCase("0") ||  null == _a){
					_isiPcs = 1 ;
				}else {
					_isiPcs = Integer.valueOf(_a);
				}
				
				BigDecimal _isiQtyOpn =(_rs.getBigDecimal("qtyopn"));
				
				BigDecimal _tot = _isiCtn.multiply(BigDecimal.valueOf(_isiPcs)).multiply(_isiQtyOpn);
				
				_ent.setTotStokPcs(_tot);*/
				
				_ent.setTripNumStok(_rs.getString("CLASTTRIP").trim());
				_ent.setTripDateStok(_rs.getDate("DLASTTRIP"));
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
