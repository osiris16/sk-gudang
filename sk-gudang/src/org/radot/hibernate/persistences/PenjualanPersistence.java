package org.radot.hibernate.persistences;

import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.StatelessSession;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.radot.hibernate.HibernateManager;
import org.radot.hibernate.entities.CustomerEntity;
import org.radot.hibernate.entities.PenjualanEntity;
import org.radot.hibernate.entities.ProductEntity;
import org.radot.hibernate.entities.SalesmanEntity;
import org.radot.hibernate.entities.StockEntity;
/**
 * 
 * @author Iman
 *
 */

public class PenjualanPersistence extends BasePersistence<PenjualanEntity> {

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
	public PenjualanEntity getByFakturNumbLike(final String fakturNumb) throws PersistenceException {
		PenjualanEntity _ret = (PenjualanEntity) null;
		StatelessSession _slSession = (StatelessSession) null;
		try {
			_slSession = HibernateManager.getStatelessSession();
			final Criteria _crit = _slSession.createCriteria(this.entity);
			_crit.setMaxResults(1);
			
			_crit.setFetchSize(1);
			_crit.add(Restrictions.ilike("fakturNumb", fakturNumb));
			_crit.addOrder(Order.desc("fakturNumb"));
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
	public PenjualanEntity getByOrderNumbLike(final String orderNumb) throws PersistenceException {
		PenjualanEntity _ret = (PenjualanEntity) null;
		StatelessSession _slSession = (StatelessSession) null;
		try {
			_slSession = HibernateManager.getStatelessSession();
			final Criteria _crit = _slSession.createCriteria(this.entity);
			_crit.setMaxResults(1);
			
			_crit.setFetchSize(1);
			_crit.add(Restrictions.ilike("orderNumb", orderNumb));
			_crit.addOrder(Order.desc("orderNumb"));
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
	public Number getCountBySales(final SalesmanEntity _query, Date _from , Date to){
		StatelessSession _slSession = (StatelessSession) null;
		Number _crit = 0;
//		ArrayList<Criterion> _arrayCritPemb = new ArrayList<Criterion>();
//		 Criterion _critEnt = Restrictions.eq("tripEnt", _trip);
//		 _arrayCritPemb.add(_critEnt);
		try {
			_slSession = HibernateManager.getStatelessSession();
			Criteria criteria = _slSession.createCriteria(this.entity);
				criteria.add(Restrictions.eq("salesmanEnt", _query));
				criteria.add(Restrictions.gt("orderDate", _from));
				criteria.add(Restrictions.lt("orderDate", to));
		    
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
	
	
	public Number getCountByCust(final CustomerEntity _query,Date _from , Date to){
		StatelessSession _slSession = (StatelessSession) null;
		Number _crit = 0;
		try {
			_slSession = HibernateManager.getStatelessSession();
			Criteria criteria = _slSession.createCriteria(this.entity);
				criteria.add(Restrictions.eq("customerEnt", _query));
				criteria.add(Restrictions.gt("orderDate", _from));
				criteria.add(Restrictions.lt("orderDate", to));
				
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
	public Number getCountByPenjualan(final String type, final String _query,Date _from , Date to){
		StatelessSession _slSession = (StatelessSession) null;
		Number _crit = 0;
		try {
			_slSession = HibernateManager.getStatelessSession();
			Criteria criteria = _slSession.createCriteria(this.entity);
			if(type.equalsIgnoreCase("orderNumb")){
				criteria.add(Restrictions.ilike("orderNumb", _query));
			}else{
				 criteria.add(Restrictions.ilike("fakturNumb", _query));
			}
			criteria.add(Restrictions.gt("orderDate", _from));
			criteria.add(Restrictions.lt("orderDate", to));
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
	
	
	public PenjualanEntity getByCustEnt(final CustomerEntity custEnt) throws PersistenceException {
		PenjualanEntity _ret = (PenjualanEntity) null;
		StatelessSession _slSession = (StatelessSession) null;
		try {
			_slSession = HibernateManager.getStatelessSession();
			final Criteria _crit = _slSession.createCriteria(this.entity);
			_crit.setMaxResults(1);
			_crit.setFetchSize(1);
			
			_crit.add(Restrictions.eq("customerEnt", custEnt));
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
	
	@SuppressWarnings("unchecked")
	public List<CustomerEntity> getByName(final String name) throws PersistenceException {
		List<CustomerEntity> _ret = (List<CustomerEntity>) null;
		StatelessSession _slSession = (StatelessSession) null;
		try {
			_slSession = HibernateManager.getStatelessSession();
			final Criteria _crit = _slSession.createCriteria(this.entity);
			_crit.setMaxResults(100);
			_crit.setFetchSize(100);
			_crit.add(Restrictions.like("custName", name, MatchMode.END));
			
			final List<?> _list = _crit.list();
			if (null != _list && !_list.isEmpty()) {
				_ret = (List<CustomerEntity>) _list;
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