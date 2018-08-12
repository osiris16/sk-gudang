package org.radot.hibernate;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.PersistenceException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.radot.utils.ClassUtils;

/**
 * 
 * @author Iman
 *
 */
public class HibernateManager {

	private static final SessionFactory SESS_FACT;
	private static final Configuration ANNOT_CONF;

	private static final String PROP_PACKAGE_TO_SCAN = "hibernate.entities.package_to_scan";
	
	static {
		synchronized (HibernateManager.class) {
			try {
				Class.forName("com.mchange.v2.resourcepool.BasicResourcePool$AsyncTestIdleResourceTask");
			} catch (final Throwable t) {
				t.getMessage();
			}
			
			final Properties _props = new Properties();
			try {
				final InputStream _instream = HibernateManager.class.getResourceAsStream("hibernate.properties");
				_props.load(_instream);
				_instream.close();
			} catch (final Throwable t) {
				t.printStackTrace(System.out);
			}

			System.out.println(_props);
			Configuration _cfg = (Configuration) null;
			try {
				System.out.println("creating hibernate config");
				_cfg = new Configuration();
			} catch (final Throwable t) {
				t.printStackTrace(System.out);
			}
			
			ANNOT_CONF = _cfg;
			SessionFactory _sessFactory = (SessionFactory) null;
			try {
				HibernateManager.ANNOT_CONF.addProperties(_props);
				final String _entityPackages = HibernateManager.ANNOT_CONF.getProperty(HibernateManager.PROP_PACKAGE_TO_SCAN);
				final String[] _allPackages = null == _entityPackages? new String[]{}: _entityPackages.split(",");
				
				final List<String> _packList = new ArrayList<String>(0);
				for (final String _pack: _allPackages) {
					_packList.add(_pack.trim());
				}

				for (final String _pack: _packList) {
					System.out.println("finding classess under: " + _pack);
					final List<Class<?>> _classes = ClassUtils.getClassesUnderPackage(_pack);
					if (!_classes.isEmpty()) {
						for (final Class<?> _class: _classes) {
							if (null != _class.getAnnotation(Entity.class) || null != _class.getAnnotation(MappedSuperclass.class)) {
								HibernateManager.ANNOT_CONF.addAnnotatedClass(_class);
							}
						}
					}
				}
				
				final ServiceRegistry _svcReg = new ServiceRegistryBuilder().applySettings(HibernateManager.ANNOT_CONF.getProperties()). buildServiceRegistry();
				_sessFactory = HibernateManager.ANNOT_CONF.buildSessionFactory(_svcReg);
			} catch (final Throwable t) {
				System.out.println("ERROR: " + t.getMessage());
				t.printStackTrace(System.out);
			}

			SESS_FACT = _sessFactory;
		}
	}
	
	/**
	 * Create new database connection with hibernate connection manager.
	 * @return {@link Session}, new hibernate connection session
	 * @author Iman
	 */
	public static final Session getSession() throws PersistenceException {
		if (null == HibernateManager.SESS_FACT) {
			System.out.println("Unable to establish database connection");
			throw new HibernateException("Unable to establish database connection");
		}

		if (HibernateManager.SESS_FACT.isClosed()) {
			System.out.println("Database connection is closed");
			throw new HibernateException("Database connection is closed");
		}

		Session _ret = (Session) null;
		try {
			_ret = HibernateManager.SESS_FACT.openSession();
		} catch (final Throwable t) {
			System.out.println("ERROR: " + t.getMessage());
			t.printStackTrace(System.out);
		}
		
		return _ret;
	}

	public static final StatelessSession getStatelessSession() throws PersistenceException {
		if (null == HibernateManager.SESS_FACT) {
			System.out.println("Unable to establish database connection");
			throw new HibernateException("Unable to establish database connection");
		}
		
		if (HibernateManager.SESS_FACT.isClosed()) {
			System.out.println("Database connection is closed");
			throw new HibernateException("Database connection is closed");
		}

		StatelessSession _ret = (StatelessSession) null;
		try {
			_ret = HibernateManager.SESS_FACT.openStatelessSession();
		} catch (final Throwable t) {
			System.out.println("ERROR: " + t.getMessage());
			t.printStackTrace(System.out);
		}

		return _ret;
	}
	

}
