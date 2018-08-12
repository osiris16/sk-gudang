package org.radot.hibernate.persistences;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.StatelessSession;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.radot.hibernate.HibernateManager;
import org.radot.hibernate.entities.ParamsEntity;
import org.radot.hibernate.entities.ProductEntity;
import org.radot.hibernate.entities.StockEntity;
import org.radot.hibernate.entities.TripEntity;

/**
 * 
 * @author Iman
 *
 */
public class ParamsPersistence extends BasePersistence<ParamsEntity> {

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
	
	@SuppressWarnings("unchecked")
	public ParamsEntity getByName(final String name) throws PersistenceException {
		ParamsEntity _ret = (ParamsEntity) null;
		StatelessSession _slSession = (StatelessSession) null;
		try {
			_slSession = HibernateManager.getStatelessSession();
			final Criteria _crit = _slSession.createCriteria(this.entity);
			_crit.setMaxResults(1);
			_crit.setFetchSize(1);
			_crit.add(Restrictions.eq("paramsKey", name));
			final List<?> _list = _crit.list();
			if (null != _list && !_list.isEmpty()) {
				_ret = (ParamsEntity) _list.get(0);
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