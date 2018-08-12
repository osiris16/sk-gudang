package org.radot.hibernate.persistences;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.StatelessSession;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.radot.hibernate.HibernateManager;
import org.radot.hibernate.entities.SalesmanEntity;

/**
 * 
 * @author Iman
 *
 */
public class SalesmanPersistence extends BasePersistence<SalesmanEntity> {

	public static final SalesmanPersistence INSTANCE = new SalesmanPersistence();
	
	public SalesmanEntity getByCode(final String code) throws PersistenceException {
		SalesmanEntity _ret = (SalesmanEntity) null;
		StatelessSession _slSession = (StatelessSession) null;
		try {
			_slSession = HibernateManager.getStatelessSession();
			final Criteria _crit = _slSession.createCriteria(this.entity);
			_crit.setMaxResults(1);
			_crit.setFetchSize(1);
			
			_crit.add(Restrictions.ilike("code", code, MatchMode.EXACT));
			final List<?> _list = _crit.list();
			if (null != _list && !_list.isEmpty()) {
				_ret = (SalesmanEntity) _list.get(0);
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
	
	public SalesmanEntity getByName(final String name) throws PersistenceException {
		SalesmanEntity _ret = (SalesmanEntity) null;
		StatelessSession _slSession = (StatelessSession) null;
		try {
			_slSession = HibernateManager.getStatelessSession();
			final Criteria _crit = _slSession.createCriteria(this.entity);
			_crit.setMaxResults(1);
			_crit.setFetchSize(1);
			
			_crit.add(Restrictions.ilike("name", name));
			final List<?> _list = _crit.list();
			if (null != _list && !_list.isEmpty()) {
				_ret = (SalesmanEntity) _list.get(0);
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
	public Number getCountBySalesman(final String type, final String _query){
		StatelessSession _slSession = (StatelessSession) null;
		Number _crit = 0;
//		ArrayList<Criterion> _arrayCritPemb = new ArrayList<Criterion>();
//		 Criterion _critEnt = Restrictions.eq("tripEnt", _trip);
//		 _arrayCritPemb.add(_critEnt);
		try {
			_slSession = HibernateManager.getStatelessSession();
			Criteria criteria = _slSession.createCriteria(this.entity);
			if (type !=null){
			if(type.equalsIgnoreCase("name")){
				criteria.add(Restrictions.ilike("name", _query));
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

}
