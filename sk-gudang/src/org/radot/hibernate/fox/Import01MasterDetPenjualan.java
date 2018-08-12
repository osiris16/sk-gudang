package org.radot.hibernate.fox;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.radot.hibernate.entities.DetailPenjualanEntity;
import org.radot.hibernate.entities.PenjualanEntity;
import org.radot.hibernate.entities.ProductEntity;
import org.radot.hibernate.entities.StockEntity;
import org.radot.hibernate.persistences.PenjualanPersistence;
import org.radot.hibernate.persistences.ProductPersistence;
import org.radot.hibernate.persistences.StockPersistence;


public class Import01MasterDetPenjualan {

	public static void main(String[] args) throws Throwable {
				
		final Connection _conn = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Visual FoxPro Driver};SourceType=DBF;SourceDB=" + FoxCfg.FOX_PATH + ";");
		final PreparedStatement _ps = _conn.prepareStatement("SELECT * FROM DTRANSJ");
//		>= Convert(datetime, '2010-04-01' )
		final ResultSet _rs = _ps.executeQuery();
		System.out.println(_rs.getRow());
		while (_rs.next()) {
			
			try {
				PenjualanPersistence _p = new PenjualanPersistence();
				PenjualanEntity _pe = _p.getByKodeJual(_rs.getString("IDHTRANS").trim());
				
				StockPersistence _s = new StockPersistence();
				ProductEntity _prodEnt = new ProductPersistence().getByProdId(_rs.getString("ID_BRGDG").trim());
				StockEntity _se = _s.getByProdEnt(_prodEnt);
				
				final DetailPenjualanEntity _ent = new DetailPenjualanEntity();
				_ent.setPenjualanEnt(_pe);
				_ent.setStockEnt(_se);
				
				
				BigDecimal _cepe = new BigDecimal(100);
				BigDecimal _HjCtnIdr =(_rs.getBigDecimal("HJ_SAT"));
				BigDecimal _QtyJual = (_rs.getBigDecimal("Q_KIRIM"));
				BigDecimal _QtyJualPcs = (_rs.getBigDecimal("Q_KIRIM2"));
				
				BigDecimal _TotJualBrutoIdr= _HjCtnIdr.multiply(_QtyJual);
				BigDecimal _disc = (_rs.getBigDecimal("P_DISC1"));
				BigDecimal _totDisc = _disc.divide(_cepe);
				BigDecimal _TotJualBrutoIdr2 = _TotJualBrutoIdr.multiply(_totDisc);
				BigDecimal _TotJualNettoIdr = _TotJualBrutoIdr.subtract(_TotJualBrutoIdr2);
				
				_ent.setHjCtn(_HjCtnIdr);
				_ent.setTotQtyJualCtn(_QtyJual);
				_ent.setTotQtyJualPcs(_QtyJualPcs);
				_ent.setTotJualBrutoIdr(_TotJualBrutoIdr);
				_ent.setTotJualNettoIdr(_TotJualNettoIdr);
				
				_ent.setDiscPersen(_disc);
				
				
				
				
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
