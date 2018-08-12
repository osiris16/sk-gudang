package org.radot.hibernate.persistences;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.StatelessSession;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.radot.hibernate.HibernateManager;
import org.radot.hibernate.entities.ProductEntity;
import org.radot.hibernate.entities.StockEntity;

/**
 * 
 * @author Iman
 *
 */
public class StockPersistence extends BasePersistence<StockEntity> {

	public StockEntity getByProdEnt(final ProductEntity prodEnt) throws PersistenceException {
		StockEntity _ret = (StockEntity) null;
		StatelessSession _slSession = (StatelessSession) null;
		try {
			_slSession = HibernateManager.getStatelessSession();
			final Criteria _crit = _slSession.createCriteria(this.entity);
			_crit.setMaxResults(1);
			_crit.setFetchSize(1);
			
			_crit.add(Restrictions.eq("productEnt", prodEnt));
			final List<?> _list = _crit.list();
			if (null != _list && !_list.isEmpty()) {
				_ret = (StockEntity) _list.get(0);
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
	public StockEntity getByTripNumStock(final String  tripNumStock) throws PersistenceException {
		StockEntity _ret = (StockEntity) null;
		StatelessSession _slSession = (StatelessSession) null;
		try {
			_slSession = HibernateManager.getStatelessSession();
			final Criteria _crit = _slSession.createCriteria(this.entity);
			_crit.setMaxResults(100);
			_crit.setFetchSize(100);
			_crit.add(Restrictions.like("tripNumStok", tripNumStock, MatchMode.END));
			
			final List<?> _list = _crit.list();
			if (null != _list && !_list.isEmpty()) {
				_ret = (StockEntity) _list;
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
	public Number getCountByTotStok(final String type, final BigDecimal _query){
		StatelessSession _slSession = (StatelessSession) null;
		Number _crit = 0;
//		ArrayList<Criterion> _arrayCritPemb = new ArrayList<Criterion>();
//		 Criterion _critEnt = Restrictions.eq("tripEnt", _trip);
//		 _arrayCritPemb.add(_critEnt);
		try {
			_slSession = HibernateManager.getStatelessSession();
			Criteria criteria = _slSession.createCriteria(this.entity);
			criteria.add(Restrictions.eq("stokCtn", _query));
		    
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
	public Number getCountByTripNum(final String type, final String _query){
		StatelessSession _slSession = (StatelessSession) null;
		Number _crit = 0;
//		ArrayList<Criterion> _arrayCritPemb = new ArrayList<Criterion>();
//		 Criterion _critEnt = Restrictions.eq("tripEnt", _trip);
//		 _arrayCritPemb.add(_critEnt);
		try {
			_slSession = HibernateManager.getStatelessSession();
			Criteria criteria = _slSession.createCriteria(this.entity);
			criteria.add(Restrictions.ilike("tripNumStok", _query));
		    
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
	@SuppressWarnings("unchecked")
	public List<ProductEntity> getByName(final String name) throws PersistenceException {
		List<ProductEntity> _ret = (List<ProductEntity>) null;
		StatelessSession _slSession = (StatelessSession) null;
		try {
			_slSession = HibernateManager.getStatelessSession();
			final Criteria _crit = _slSession.createCriteria(this.entity);
			_crit.setMaxResults(100);
			_crit.setFetchSize(100);
			_crit.add(Restrictions.like("name", name, MatchMode.END));
			
			final List<?> _list = _crit.list();
			if (null != _list && !_list.isEmpty()) {
				_ret = (List<ProductEntity>) _list;
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
	
	public List<ProductEntity> getByCode(final String code) throws PersistenceException {
		List<ProductEntity> _ret = (List<ProductEntity>) null;
		StatelessSession _slSession = (StatelessSession) null;
		try {
			_slSession = HibernateManager.getStatelessSession();
			final Criteria _crit = _slSession.createCriteria(this.entity);
			_crit.setMaxResults(100);
			_crit.setFetchSize(100);
			_crit.add(Restrictions.like("code", code, MatchMode.END));
			
			final List<?> _list = _crit.list();
			if (null != _list && !_list.isEmpty()) {
				_ret = (List<ProductEntity>) _list;
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