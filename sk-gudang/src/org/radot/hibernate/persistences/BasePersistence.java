package org.radot.hibernate.persistences;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.StatelessSession;
import org.hibernate.criterion.Restrictions;
import org.radot.base.BaseErasure;
import org.radot.hibernate.HibernateManager;
import org.radot.hibernate.entities.BaseEntity;

/**
 * 
 * @author Iman
 *
 * @param <E>
 */
public class BasePersistence <E extends BaseEntity> extends BaseErasure {

	protected final Class<E> entity;
	
	@SuppressWarnings("unchecked")
	public final E getByRecId(final Long recId) throws PersistenceException {
		E _ret = (E) null;
		StatelessSession _slSession = (StatelessSession) null;
		try {
			_slSession = HibernateManager.getStatelessSession();
			final Criteria _crit = _slSession.createCriteria(this.entity);
			_crit.setMaxResults(1);
			_crit.setFetchSize(1);
			
			_crit.add(Restrictions.eq("recId", recId));
			final List<?> _list = _crit.list();
			if (null != _list && !_list.isEmpty()) {
				_ret = (E) _list.get(0);
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
	public BasePersistence() throws PersistenceException {
		if (null == this.getErasureType(0)) {
			throw new PersistenceException("Invalid erasure entity model");
		}
		
		this.entity = (Class<E>) this.getErasureType(0);
	}
	
}
