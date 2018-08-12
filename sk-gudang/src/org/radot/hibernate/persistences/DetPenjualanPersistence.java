package org.radot.hibernate.persistences;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.StatelessSession;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.radot.hibernate.HibernateManager;
import org.radot.hibernate.entities.BaseEntity;
import org.radot.hibernate.entities.DetailPenjualanEntity;
import org.radot.hibernate.entities.PenjualanEntity;
/**
 * 
 * @author Iman
 *
 */

public class DetPenjualanPersistence extends BasePersistence<DetailPenjualanEntity> {

	public DetailPenjualanEntity getById(final String id) throws PersistenceException {
		DetailPenjualanEntity _ret = (DetailPenjualanEntity) null;
		StatelessSession _slSession = (StatelessSession) null;
		try {
			_slSession = HibernateManager.getStatelessSession();
			final Criteria _crit = _slSession.createCriteria(this.entity);
			_crit.setMaxResults(1);
			_crit.setFetchSize(1);
			//_crit.add(Restrictions.eq("productEnt", prodEnt));
			_crit.add(Restrictions.ilike("id", id, MatchMode.EXACT));
			final List<?> _list = _crit.list();
			if (null != _list && !_list.isEmpty()) {
				_ret = (DetailPenjualanEntity) _list.get(0);
			}
		} catch (final Throwable t) {
			t.printStackTrace(System.out);
		}
		
		try {
			if (null != _slSession) {
				_slSession.close();
			}
		} catch (final Throwable t) {
			t.getMessage();
		}
		
		return _ret;
	}
	
	public PenjualanEntity getByOrderNumb(final String orderNumb) throws PersistenceException {
		PenjualanEntity _ret = (PenjualanEntity) null;
		StatelessSession _slSession = (StatelessSession) null;
		try {
			_slSession = HibernateManager.getStatelessSession();
			final Criteria _crit = _slSession.createCriteria(this.entity);
			_crit.setMaxResults(1);
			_crit.setFetchSize(1);
			_crit.add(Restrictions.eq("orderNumb", orderNumb));
			final List<?> _list = _crit.list();
			if (null != _list && !_list.isEmpty()) {
				_ret = (PenjualanEntity) _list.get(0);
			}
		} catch (final Throwable t) {
			t.printStackTrace(System.out);
		}
		
		try {
			if (null != _slSession) {
				_slSession.close();
			}
		} catch (final Throwable t) {
			t.getMessage();
		}
		
		return _ret;
	}
	
	public PenjualanEntity getByFakturNumb(final String fakturNumb) throws PersistenceException {
		PenjualanEntity _ret = (PenjualanEntity) null;
		StatelessSession _slSession = (StatelessSession) null;
		try {
			_slSession = HibernateManager.getStatelessSession();
			final Criteria _crit = _slSession.createCriteria(this.entity);
			_crit.setMaxResults(1);
			_crit.setFetchSize(1);
			_crit.add(Restrictions.eq("fakturNumb", fakturNumb));
			final List<?> _list = _crit.list();
			if (null != _list && !_list.isEmpty()) {
				_ret = (PenjualanEntity) _list.get(0);
			}
		} catch (final Throwable t) {
			t.printStackTrace(System.out);
		}
		
		try {
			if (null != _slSession) {
				_slSession.close();
			}
		} catch (final Throwable t) {
			t.getMessage();
		}
		
		return _ret;
	}
	
	public PenjualanEntity getByKodeJual(final String kodeJual) throws PersistenceException {
		PenjualanEntity _ret = (PenjualanEntity) null;
		StatelessSession _slSession = (StatelessSession) null;
		try {
			_slSession = HibernateManager.getStatelessSession();
			final Criteria _crit = _slSession.createCriteria(this.entity);
			_crit.setMaxResults(1);
			_crit.setFetchSize(1);
			_crit.add(Restrictions.eq("kodeJual", kodeJual));
			final List<?> _list = _crit.list();
			if (null != _list && !_list.isEmpty()) {
				_ret = (PenjualanEntity) _list.get(0);
			}
		} catch (final Throwable t) {
			t.printStackTrace(System.out);
		}
		
		try {
			if (null != _slSession) {
				_slSession.close();
			}
		} catch (final Throwable t) {
			t.getMessage();
		}
		
		return _ret;
	}

	public List<DetailPenjualanEntity> getByPenjualanList(final PenjualanEntity _penjualan)throws PersistenceException{
		List<DetailPenjualanEntity> _ret = new ArrayList<DetailPenjualanEntity>();
		 ArrayList<Criterion> _arrayCritDetPenj = new ArrayList<Criterion>();
		 Criterion _crit = Restrictions.eq("penjualanEnt", _penjualan);
		 _arrayCritDetPenj.add(_crit);
		final List<DetailPenjualanEntity> DetPenj = BaseEntity.listDataOffset(DetailPenjualanEntity.class, _arrayCritDetPenj, null, null, null);
		_ret = DetPenj;
		return _ret;
	}
	
	public Number getCountByDetPenj(final PenjualanEntity _penjualan){
		StatelessSession _slSession = (StatelessSession) null;
		Number _crit = 0;
//		ArrayList<Criterion> _arrayCritPemb = new ArrayList<Criterion>();
//		 Criterion _critEnt = Restrictions.eq("tripEnt", _trip);
//		 _arrayCritPemb.add(_critEnt);
		try {
			_slSession = HibernateManager.getStatelessSession();
			Criteria criteria = _slSession.createCriteria(DetailPenjualanEntity.class);
		    criteria.add(Restrictions.eq("penjualanEnt", _penjualan));
		    criteria.setProjection(Projections.count("recId"));
		           
		    @SuppressWarnings("unchecked")
			List<Number> rows = criteria.list(); 
		     _crit = rows.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (null != _slSession) {
				_slSession.close();
			}
		} catch (final Throwable t) {
			t.getMessage();
		}
		return _crit;
	}
	

	
	public Number getCountByPenjualan(final String type, final String _query){
		StatelessSession _slSession = (StatelessSession) null;
		Number _crit = 0;
//		ArrayList<Criterion> _arrayCritPemb = new ArrayList<Criterion>();
//		 Criterion _critEnt = Restrictions.eq("tripEnt", _trip);
//		 _arrayCritPemb.add(_critEnt);
		try {
			_slSession = HibernateManager.getStatelessSession();
			Criteria criteria = _slSession.createCriteria(this.entity);
			if (type !=null){
			if(type.equalsIgnoreCase("orderNumb")){
				criteria.add(Restrictions.ilike("orderNumb", _query));
			}else{
				 criteria.add(Restrictions.ilike("fakturNumb", _query));
			}
			}
		    criteria.setProjection(Projections.count("recId"));
		           
		    @SuppressWarnings("unchecked")
			List<Number> rows = criteria.list(); 
		     _crit = rows.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (null != _slSession) {
				_slSession.close();
			}
		} catch (final Throwable t) {
			t.getMessage();
		}
		return _crit;
	}
	
	public DetailPenjualanEntity getByPenjEnt(final PenjualanEntity penjEnt) throws PersistenceException {
		DetailPenjualanEntity _ret = (DetailPenjualanEntity) null;
		StatelessSession _slSession = (StatelessSession) null;
		try {
			_slSession = HibernateManager.getStatelessSession();
			final Criteria _crit = _slSession.createCriteria(this.entity);
			_crit.setMaxResults(1);
			_crit.setFetchSize(1);
			
			_crit.add(Restrictions.eq("penjualanEnt", penjEnt));
			final List<?> _list = _crit.list();
			if (null != _list && !_list.isEmpty()) {
				_ret = (DetailPenjualanEntity) _list.get(0);
			}
		} catch (final Throwable t) {
			t.printStackTrace(System.out);
		}
		
		try {
			if (null != _slSession) {
				_slSession.close();
			}
		} catch (final Throwable t) {
			t.getMessage();
		}
		
		return _ret;
	}
	
}