package org.radot.hibernate.persistences;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.StatelessSession;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.radot.hibernate.HibernateManager;
import org.radot.hibernate.entities.AnnounceEntity;

/**
 * 
 * @author Iman
 *
 */
public class AnnouncePersistence extends BasePersistence<AnnounceEntity> {
	public Number getCountByAnnounce(final String type, final String _query){
		StatelessSession _slSession = (StatelessSession) null;
		Number _crit = 0;
//		ArrayList<Criterion> _arrayCritPemb = new ArrayList<Criterion>();
//		 Criterion _critEnt = Restrictions.eq("tripEnt", _trip);
//		 _arrayCritPemb.add(_critEnt);
		try {
			_slSession = HibernateManager.getStatelessSession();
			Criteria criteria = _slSession.createCriteria(this.entity);
			if (type !=null){
			if(type.equalsIgnoreCase("destination")){
				criteria.add(Restrictions.ilike("destination", _query));
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
