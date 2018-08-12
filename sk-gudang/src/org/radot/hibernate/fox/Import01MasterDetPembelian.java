package org.radot.hibernate.fox;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.radot.hibernate.entities.DetailPembelianEntity;
import org.radot.hibernate.entities.ProductEntity;
import org.radot.hibernate.entities.StockEntity;
import org.radot.hibernate.entities.TripEntity;
import org.radot.hibernate.persistences.ProductPersistence;
import org.radot.hibernate.persistences.StockPersistence;
import org.radot.hibernate.persistences.TripPersistence;


public class Import01MasterDetPembelian {

	public static void main(String[] args) throws Throwable {
				
		final Connection _conn = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Visual FoxPro Driver};SourceType=DBF;SourceDB=" + FoxCfg.FOX_PATH + ";");
		final PreparedStatement _ps = _conn.prepareStatement("SELECT * FROM DTRANSB");
//		>= Convert(datetime, '2010-04-01' )
		final ResultSet _rs = _ps.executeQuery();
		System.out.println(_rs.getRow());
		while (_rs.next()) {
			
			try {
				TripPersistence _t = new TripPersistence();
				TripEntity _te = _t.getByKodeTrip(_rs.getString("IDHTRANS").trim());
				
				StockPersistence _s = new StockPersistence();
				ProductEntity _prodEnt = new ProductPersistence().getByProdId(_rs.getString("ID_BRGDG").trim());
				StockEntity _se = _s.getByProdEnt(_prodEnt);
				
				final DetailPembelianEntity _ent = new DetailPembelianEntity();
				_ent.setTripEnt(_te);
				_ent.setStockEnt(_se);
				_ent.setQtyBeliCtn(_rs.getBigDecimal("Q_CARTON"));
				
				BigDecimal _isiCtn = _se.getProductEnt().getIsiCtn();
				
				BigDecimal _hargaJisiCtn= _rs.getBigDecimal("HJ_SAT");
				BigDecimal _hargaJCtn = _hargaJisiCtn.multiply(_isiCtn);
				_ent.setHargaJ_CtnNew(_hargaJCtn); // harga jual per ctn
				_ent.setHargaJ_CtnOld(BigDecimal.ZERO);
				
				_ent.setCost(_rs.getBigDecimal("P_COST"));
				_ent.setDisc(_rs.getBigDecimal("P_DISC1"));
				String _trip = _te.getTrip_numb()+"-"+_rs.getString("NO_URUT").trim();
				_ent.setTripNumSeqStock(_trip);
				
				BigDecimal _hBisiCtnVta =(_rs.getBigDecimal("HPP_SAT"));
				BigDecimal _hbCtnVta = _hBisiCtnVta.multiply(_isiCtn); // harga beli / karton VTa
				
				BigDecimal _RateVta =(_rs.getBigDecimal("RATE_BELI"));
				
				BigDecimal _qtyCtn =(_rs.getBigDecimal("Q_CARTON"));
				
				BigDecimal _hbCtnIdr = _hbCtnVta.multiply(_RateVta); // harga beli / karton Idr
				
				BigDecimal _totHargaBrutoVta = _hbCtnVta.multiply(_qtyCtn);
				
				BigDecimal _totHargaBrutoIdr = _totHargaBrutoVta.multiply(_RateVta);
				
				BigDecimal _cepe = new BigDecimal(100);
				BigDecimal _cost = _rs.getBigDecimal("P_COST");
				BigDecimal _disc = _rs.getBigDecimal("P_DISC1");
				BigDecimal _costA = _cost.divide(_cepe);
				BigDecimal _discA = _disc.divide(_cepe);
				BigDecimal _totCost = _totHargaBrutoIdr.multiply(_costA);
				
				BigDecimal _totBrutoIdr = _totHargaBrutoIdr.add(_totCost);
				BigDecimal _totBrutoVta = _totBrutoIdr.divide(_RateVta,2, RoundingMode.HALF_UP);
				BigDecimal _totDisc = _totBrutoIdr.multiply(_discA);
				
				BigDecimal _totNettoIdr = _totBrutoIdr.subtract(_totDisc);
				BigDecimal _totNettoVta = _totBrutoVta.subtract(_totDisc);
				
				_ent.setHargaBeliCtnIdr(_hbCtnIdr);
				_ent.setHargaBeliCtnVta(_hbCtnVta);
				_ent.setTotHargaBrutB_Idr(_totHargaBrutoIdr);
				_ent.setTotHargaBrutB_Vta(_totHargaBrutoVta);
				_ent.setTotHargaNettB_Vta(_totNettoVta);
				_ent.setTotHargaNettB_Idr(_totNettoIdr);
				
				
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
