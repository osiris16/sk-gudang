package org.radot.hibernate.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.radot.hibernate.HibernateManager;
import org.radot.utils.ClassUtils;

@MappedSuperclass()
public class BaseEntity implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1017495034139090659L;
	
	@Id()
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="rec_id")
	private Long recId;
	
	@JoinColumn(name="created_by_fk", updatable=false)
	@OneToOne()
	private UserEntity createdBy;

	@JoinColumn(name="modified_by_fk")
	@OneToOne()
	private UserEntity modifiedBy;

	@Column(name="created_on")
	private Date createdOn;

	@Column(name="modified_on")
	private Date modifiedOn;

	@Column(name="is_deleted")
	private Boolean isDeleted;

	public final Long getRecId() {
		return recId;
	}

	public final void setRecId(final Long recId) {
		this.recId = recId;
	}

	public final UserEntity getCreatedBy() {
		return createdBy;
	}

	public final void setCreatedBy(final UserEntity createdBy) {
		this.createdBy = createdBy;
	}

	public final UserEntity getModifiedBy() {
		return modifiedBy;
	}

	public final void setModifiedBy(final UserEntity modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public final Date getCreatedOn() {
		return createdOn;
	}

	public final void setCreatedOn(final Date createdOn) {
		this.createdOn = createdOn;
	}

	public final Date getModifiedOn() {
		return modifiedOn;
	}

	public final void setModifiedOn(final Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public final Boolean getIsDeleted() {
		return isDeleted;
	}

	public final void setIsDeleted(final Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
	public final void save() throws PersistenceException {
		PersistenceException _exception = (PersistenceException) null;
		StatelessSession _session = (StatelessSession) null;
		Transaction _transaction = (Transaction) null;
		try {
			_session = HibernateManager.getStatelessSession();
			if (null == _session) {
				throw new PersistenceException("");
			}

			_transaction = _session.getTransaction();
			_transaction.begin();

			this.setRecId((Long) null);
			this.setIsDeleted(false);
			final Date _now = new Date();
			this.setCreatedOn(_now);
			this.setModifiedOn(_now);
			_session.insert(this);
			_transaction.commit();
		} catch (final Throwable t) {
			if (t instanceof PersistenceException) {
				_exception = (PersistenceException) t;
			} else {
				_exception = new PersistenceException(t);
			}

			try {
				if (null != _transaction) {
					_transaction.rollback();
				}
			} catch (final Throwable ex) {
				ex.getMessage();
			}
		}

		try {
			if (null != _session) {
				_session.close();
			}
		} catch (final Throwable t) {
			t.getMessage();
		}

		if (null != _exception) {
			throw _exception;
		}
	}

	public final void modify() throws PersistenceException {
		PersistenceException _exception = (PersistenceException) null;
		StatelessSession _session = (StatelessSession) null;
		Transaction _transaction = (Transaction) null;
		try {
			_session = HibernateManager.getStatelessSession();
			if (null == _session) {
				throw new PersistenceException("");
			}

			_transaction = _session.getTransaction();
			_transaction.begin();

			final Date _now = new Date();
			this.setModifiedOn(_now);
			_session.update(this);
			_transaction.commit();
		} catch (final Throwable t) {
			if (t instanceof PersistenceException) {
				_exception = (PersistenceException) t;
			} else {
				_exception = new PersistenceException(t);
			}

			try {
				if (null != _transaction) {
					_transaction.rollback();
				}
			} catch (final Throwable ex) {
				ex.getMessage();
			}
		}

		try {
			if (null != _session) {
				_session.close();
			}
		} catch (final Throwable t) {
			t.getMessage();
		}

		if (null != _exception) {
			throw _exception;
		}
	}

	public final void delete() throws PersistenceException {
		this.setIsDeleted(true);
		this.modify();
	}

	public final void erase() throws PersistenceException {
		PersistenceException _exception = (PersistenceException) null;
		StatelessSession _session = (StatelessSession) null;
		Transaction _transaction = (Transaction) null;
		try {
			_session = HibernateManager.getStatelessSession();
			if (null == _session) {
				throw new PersistenceException("");
			}

			_transaction = _session.getTransaction();
			_transaction.begin();
			_session.delete(this);
			_transaction.commit();
		} catch (final Throwable t) {
			if (t instanceof PersistenceException) {
				_exception = (PersistenceException) t;
			} else {
				_exception = new PersistenceException(t);
			}

			try {
				if (null != _transaction) {
					_transaction.rollback();
				}
			} catch (final Throwable ex) {
				ex.getMessage();
			}
		}

		try {
			if (null != _session) {
				_session.close();
			}
		} catch (final Throwable t) {
			t.getMessage();
		}

		if (null != _exception) {
			throw _exception;
		}
	}
	
	public final static <E extends BaseEntity> void saveChain(final E... entities) throws PersistenceException {
		if (null == entities || entities.length < 1) {
			throw new PersistenceException("");
		}

		PersistenceException _exception = (PersistenceException) null;
		StatelessSession _session = (StatelessSession) null;
		Transaction _transaction = (Transaction) null;
		try {
			_session = HibernateManager.getStatelessSession();
			if (null == _session) {
				throw new PersistenceException("");
			}

			_transaction = _session.getTransaction();
			_transaction.begin();

			final Date _now = new Date();
			for (final E entity: entities) {
				if (null == entity) {
					continue;
				}
				
				if (null == entity.getClass().getAnnotation(Entity.class)) {
					continue;
				}
				
				entity.setModifiedOn(_now);
				if (null == entity.getRecId()) {
					entity.setCreatedOn(_now);
					_session.insert(entity);
				} else {
					if (entity.getRecId() < 1) {
						throw new PersistenceException("");
					}
					
					if (null == entity.createdOn) {
						entity.setCreatedOn(_now);
					}
					
					_session.update(entity);
				}
			}
						
			_transaction.commit();
		} catch (final Throwable t) {
			if (t instanceof PersistenceException) {
				_exception = (PersistenceException) t;
			} else {
				_exception = new PersistenceException(t);
			}

			try {
				if (null != _transaction) {
					_transaction.rollback();
				}
			} catch (final Throwable ex) {
				ex.getMessage();
			}
		}

		try {
			if (null != _session) {
				_session.close();
			}
		} catch (final Throwable t) {
			t.getMessage();
		}

		if (null != _exception) {
			throw _exception;
		}
	}
	
	@Override()
	public final String toString() {
		final StringBuilder _ret = new StringBuilder();
		_ret.append('{');
		try {
			final String _name = this.getClass().getAnnotation(Entity.class).name();
			
			if (null == this.recId) {
				_ret.append("new ").append(_name);
			} else {
				if (this.recId < 1) {
					_ret.append("invalid ").append(_name);
				} else {					
					_ret.append(_name).append('[').append(this.recId);
					final Object _desc = ClassUtils.getFieldValue(this, "name");
					if (_desc instanceof String) {
						_ret.append(',').append((String) _desc);
					}
					_ret.append(']');
				}
			}
			
		} catch (final Throwable t) {
			t.getMessage();
		}
		_ret.append('}');
		return _ret.toString();
	}

	public final static <E extends BaseEntity> List<E> list(final Class<E> clazz, final List<Criterion> criterions, final List<Order> orders) throws PersistenceException {
		final List<Criterion> _criterions = new ArrayList<Criterion>();
		if (null != criterions && !criterions.isEmpty()) {
			_criterions.addAll(criterions);
		}
		
		final Criterion _notDeleted = Restrictions.or(
				Restrictions.isNull("isDeleted"),
				Restrictions.eq("isDeleted", false)
		);
		_criterions.add(_notDeleted);

		return BaseEntity.listIgnoreDeleted(clazz, _criterions, orders);
	}

	@SuppressWarnings("unchecked")
	public final static <E extends BaseEntity> List<E> listIgnoreDeleted(final Class<E> clazz, final List<Criterion> criterions, final List<Order> orders) throws PersistenceException {
		List<E> _ret = (List<E>) null;		
		PersistenceException _exception = (PersistenceException) null;
		StatelessSession _session = (StatelessSession) null;
		try {
			if (null == clazz) {
				throw new PersistenceException("Invalid Class projection");
			}
			
			if (null == clazz.getAnnotation(Entity.class)) {
				throw new PersistenceException("Non entity Class");
			}
			
			_session = HibernateManager.getStatelessSession();
			if (null == _session) {
				throw new PersistenceException("");
			}
			
			final Criteria _crit = _session.createCriteria(clazz);			
			if (null != criterions && !criterions.isEmpty()) {
				for (final Criterion _ctr: criterions) {
					if (null == _ctr) {
						continue;
					}
					
					_crit.add(_ctr);
				}
			}

			if (null != orders && !orders.isEmpty()) {
				for (final Order _ord: orders) {
					if (null == _ord) {
						continue;
					}
					
					_crit.addOrder(_ord);
				}
			}
			
			//_crit.setMaxResults(2); //ini limit
			_ret = (List<E>) _crit.list();
		} catch (final Throwable t) {
			if (t instanceof PersistenceException) {
				_exception = (PersistenceException) t;
			} else {
				_exception = new PersistenceException(t);
			}
		}

		try {
			if (null != _session) {
				_session.close();
			}
		} catch (final Throwable t) {
			t.getMessage();
		}

		if (null != _exception) {
			throw _exception;
		}
		
		return _ret;
	}
	

	@SuppressWarnings("unchecked")
	public final static <E extends BaseEntity> List<E> listDataOffset(final Class<E> clazz, final List<Criterion> criterions, final List<Order> orders, Integer limit, Long offset) throws PersistenceException {
		List<E> _ret = (List<E>) null;		
		PersistenceException _exception = (PersistenceException) null;
		StatelessSession _session = (StatelessSession) null;
		try {
			if (null == clazz) {
				throw new PersistenceException("Invalid Class projection");
			}
			
			if (null == clazz.getAnnotation(Entity.class)) {
				throw new PersistenceException("Non entity Class");
			}
			
			_session = HibernateManager.getStatelessSession();
			if (null == _session) {
				throw new PersistenceException("");
			}
			
			final Criteria _crit = _session.createCriteria(clazz);			
			if (null != criterions && !criterions.isEmpty()) {
				for (final Criterion _ctr: criterions) {
					if (null == _ctr) {
						continue;
					}
					
					_crit.add(_ctr);
				}
			}

			if (null != offset) {
				_crit.add(Restrictions.ge("recId", offset));
			}
			
			if (null != orders && !orders.isEmpty()) {
				for (final Order _ord: orders) {
					if (null == _ord) {
						continue;
					}
					
					_crit.addOrder(_ord);
				}
			}
			
			if (null != limit) {
				_crit.setMaxResults(limit.intValue()); //ini limit
			}
			_ret = (List<E>) _crit.list();
		} catch (final Throwable t) {
			if (t instanceof PersistenceException) {
				_exception = (PersistenceException) t;
			} else {
				_exception = new PersistenceException(t);
			}
		}

		try {
			if (null != _session) {
				_session.close();
			}
		} catch (final Throwable t) {
			t.getMessage();
		}

		if (null != _exception) {
			throw _exception;
		}
		
		return _ret;
	}

	
	public final static void mainx(String[] args) {
		final Order _order = Order.asc("name");
		final Criterion _crit = Restrictions.ilike("name", "m", MatchMode.ANYWHERE);
		System.out.println(BaseEntity.list(UserEntity.class,
				new ArrayList<Criterion>() {
					private static final long serialVersionUID = 3331000168691005360L;
					{
						add(_crit);
					}
				}, 
				
				new ArrayList<Order>() {
					private static final long serialVersionUID = 5425189759612732416L;
					{
						add(_order);
					}
				}
			)
		);
	}
}
