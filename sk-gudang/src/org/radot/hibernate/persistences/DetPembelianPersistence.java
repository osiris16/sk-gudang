package org.radot.hibernate.persistences;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.swing.text.StyledEditorKit.BoldAction;

import org.hibernate.Criteria;
import org.hibernate.StatelessSession;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.radot.hibernate.HibernateManager;
import org.radot.hibernate.entities.BaseEntity;
import org.radot.hibernate.entities.DetailPembelianEntity;
import org.radot.hibernate.entities.StockEntity;
import org.radot.hibernate.entities.TripEntity;

/**
 * 
 * @author Iman
 *
 */
public class DetPembelianPersistence extends BasePersistence<DetailPembelianEntity> {

	public DetailPembelianEntity getById(final String id) throws PersistenceException {
		DetailPembelianEntity _ret = (DetailPembelianEntity) null;
		StatelessSession _slSession = (StatelessSession) null;
		try {
			_slSession = HibernateManager.getStatelessSession();
			final Criteria _crit = _slSession.createCriteria(this.entity);
			_crit.setMaxResults(1);
			_crit.setFetchSize(1);
			
			_crit.add(Restrictions.ilike("id", id, MatchMode.EXACT));
			final List<?> _list = _crit.list();
			if (null != _list && !_list.isEmpty()) {
				_ret = (DetailPembelianEntity) _list.get(0);
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

	

	

public DetailPembelianEntity getByTripEnt(final TripEntity tripEnt) throws PersistenceException {
	DetailPembelianEntity _ret = (DetailPembelianEntity) null;
	StatelessSession _slSession = (StatelessSession) null;
	try {
		_slSession = HibernateManager.getStatelessSession();
		final Criteria _crit = _slSession.createCriteria(this.entity);
		_crit.setMaxResults(1);
		_crit.setFetchSize(1);
		
		_crit.add(Restrictions.eq("tripEnt", tripEnt));
		final List<?> _list = _crit.list();
		if (null != _list && !_list.isEmpty()) {
			_ret = (DetailPembelianEntity) _list.get(0);
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


public List<DetailPembelianEntity> getByTripList(final TripEntity _trip)throws PersistenceException{
	List<DetailPembelianEntity> _ret = new ArrayList<DetailPembelianEntity>();
	 ArrayList<Criterion> _arrayCritPemb = new ArrayList<Criterion>();
	 Criterion _crit = Restrictions.eq("tripEnt", _trip);
	 Criterion _isNotDel = Restrictions.eq("isDeleted", false);
	 _arrayCritPemb.add(_crit);
	 _arrayCritPemb.add(_isNotDel);
	 final List<DetailPembelianEntity> _pemb = BaseEntity.listDataOffset(DetailPembelianEntity.class, _arrayCritPemb, null, null, null);
	_ret = _pemb;
	return _ret;
}
public Number getCountByTripSeq(final String _tripSeq){
	StatelessSession _slSession = (StatelessSession) null;
	Number _crit = 0;
//	ArrayList<Criterion> _arrayCritPemb = new ArrayList<Criterion>();
//	 Criterion _critEnt = Restrictions.eq("tripEnt", _trip);
//	 _arrayCritPemb.add(_critEnt);
	try {
		_slSession = HibernateManager.getStatelessSession();
		Criteria criteria = _slSession.createCriteria(this.entity);
	    criteria.add(Restrictions.ilike("tripNumSeqStock", _tripSeq));
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
public Number getCountByPembe(final StockEntity _stock){
	StatelessSession _slSession = (StatelessSession) null;
	Number _crit = 0;
//	ArrayList<Criterion> _arrayCritPemb = new ArrayList<Criterion>();
//	 Criterion _critEnt = Restrictions.eq("tripEnt", _trip);
//	 _arrayCritPemb.add(_critEnt);
	try {
		_slSession = HibernateManager.getStatelessSession();
		Criteria criteria = _slSession.createCriteria(this.entity);
	    criteria.add(Restrictions.eq("stockEnt", _stock));
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


public Number getCountByPembe2(final TripEntity _trip){
	StatelessSession _slSession = (StatelessSession) null;
	Number _crit = 0;
//	ArrayList<Criterion> _arrayCritPemb = new ArrayList<Criterion>();
//	 Criterion _critEnt = Restrictions.eq("tripEnt", _trip);
//	 _arrayCritPemb.add(_critEnt);
	try {
		_slSession = HibernateManager.getStatelessSession();
		Criteria criteria = _slSession.createCriteria(this.entity);
	    criteria.add(Restrictions.eq("tripEnt", _trip));
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
	/*@SuppressWarnings("unchecked")
	public List<DetailPembelianEntity> getByCode(final String code) throws PersistenceException {
		List<ProductEntity> _ret = (List<ProductEntity>) null;
		StatelessSession _slSession = (StatelessSession) null;
		try {
			_slSession = HibernateManager.getStatelessSession();
			final Criteria _crit = _slSession.createCriteria(this.entity);
			_crit.setMaxResults(100);
			_crit.setFetchSize(100);
			_crit.add(Restrictions.like("name", code, MatchMode.END));
			
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

}*/