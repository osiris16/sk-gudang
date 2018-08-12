package org.radot.hibernate.fox;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.radot.hibernate.entities.ProductEntity;
import org.radot.hibernate.entities.ProductGroupEntity;
import org.radot.hibernate.persistences.ProductGroupPersistence;


public class Import01MasterBarang {

	@SuppressWarnings("null")
	public static void main(String[] args) throws Throwable {
				
		final Connection _conn = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Visual FoxPro Driver};SourceType=DBF;SourceDB=" + FoxCfg.FOX_PATH + ";");
		final PreparedStatement _ps = _conn.prepareStatement("SELECT * FROM neostok");
//		>= Convert(datetime, '2010-04-01' )
		final ResultSet _rs = _ps.executeQuery();
		System.out.println(_rs.getRow());
		while (_rs.next()) {
			
			try {
				
				ProductGroupPersistence _pg = new ProductGroupPersistence();
				ProductGroupEntity _pge = _pg.getByName(_rs.getString("kelompok").trim());
				
				final ProductEntity _ent = new ProductEntity();
				_ent.setProductGroupEnt(_pge);
				_ent.setProdId(_rs.getString("ID_BRGDG").trim());
				_ent.setCode(_rs.getString("kd_brgdg").trim());
				_ent.setName(_rs.getString("nm_brgdg").trim());
				_ent.setMadeIn(_rs.getString("made_in").trim());
				_ent.setMerk(_rs.getString("merek").trim());
				_ent.setPartNumb(_rs.getString("partno").trim());
				
				
				
				String _satIsiCtn = _rs.getString("SATBELI").trim();
				_ent.setSatIsiCtn(_satIsiCtn);
				
				BigDecimal _isiLsn = new BigDecimal(12);
				BigDecimal _isiPcs = new BigDecimal(1);
				if (_satIsiCtn.equalsIgnoreCase("LSN")){
					_ent.setIsiPcs(_isiLsn);
				}
				if(_satIsiCtn.equalsIgnoreCase("PCS")||_satIsiCtn.equalsIgnoreCase("KTG")||_satIsiCtn.equalsIgnoreCase("BOX")){
					_ent.setIsiPcs(_isiPcs);
				}
				else {
					Integer _isiPcss = 0 ;
					String _a = _rs.getString("ISI_PCS");
					if (_a.equalsIgnoreCase("0") ||  null == _a){
						_isiPcss = 1 ;
					}else {
						_isiPcss = Integer.valueOf(_a);
					}
					
					_ent.setIsiPcs(BigDecimal.valueOf(_isiPcss));
					}
					
					Integer _isiCtnn = 0 ;
					String _b = _rs.getString("ISI_CTN");
					if (_b.equalsIgnoreCase("0") ||  null == _b){
					_isiCtnn = 1 ;
					}else {
					_isiCtnn = Integer.valueOf(_b);
					}
				
				_ent.setIsiCtn(BigDecimal.valueOf(_isiCtnn));
				BigDecimal _isiCtn = BigDecimal.valueOf(_isiCtnn);
				BigDecimal _hargaJualIsiCtn =_rs.getBigDecimal("HJ_RP");
				BigDecimal _hargaJualCtn = _hargaJualIsiCtn.multiply(_isiCtn);
				_ent.setHJstdCtn(_hargaJualCtn);
				
				
				
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
