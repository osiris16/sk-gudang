package org.radot.hibernate.persistences;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.StatelessSession;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.radot.hibernate.HibernateManager;
import org.radot.hibernate.entities.UserEntity;

/**
 * 
 * @author Iman
 *
 */
public class UserPersistence extends BasePersistence<UserEntity> {

	public UserEntity getByName(final String name) throws PersistenceException {
		UserEntity _ret = (UserEntity) null;
		StatelessSession _slSession = (StatelessSession) null;
		try {
			_slSession = HibernateManager.getStatelessSession();
			final Criteria _crit = _slSession.createCriteria(this.entity);
			_crit.setMaxResults(1);
			_crit.setFetchSize(1);
			
			_crit.add(Restrictions.ilike("name", name, MatchMode.EXACT));
			final List<?> _list = _crit.list();
			if (null != _list && !_list.isEmpty()) {
				_ret = (UserEntity) _list.get(0);
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
	
	
	public static void main(String[] args) throws Throwable {
		System.out.println(new UserPersistence().getByName("IMAn"));
	}
}
