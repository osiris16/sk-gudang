package org.radot.hibernate.persistences;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.StatelessSession;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.radot.hibernate.HibernateManager;
import org.radot.hibernate.entities.ProductGroupEntity;

/**
 * 
 * @author Iman
 *
 */
public class ProductGroupPersistence extends BasePersistence<ProductGroupEntity> {

	public static final ProductGroupPersistence INSTANCE = new ProductGroupPersistence();
		public ProductGroupEntity getByName(final String name) throws PersistenceException {
			ProductGroupEntity _ret = (ProductGroupEntity) null;
		StatelessSession _slSession = (StatelessSession) null;
		try {
			_slSession = HibernateManager.getStatelessSession();
			final Criteria _crit = _slSession.createCriteria(this.entity);
			_crit.setMaxResults(1);
			_crit.setFetchSize(1);
			_crit.add(Restrictions.ilike("name", name, MatchMode.EXACT));
			final List<?> _list = _crit.list();
			if (null != _list && !_list.isEmpty()) {
				_ret = (ProductGroupEntity) _list.get(0);
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
