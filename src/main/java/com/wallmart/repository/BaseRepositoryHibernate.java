package com.wallmart.repository;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.wallmart.exception.DAOException;
import com.wallmart.repository.interfaces.IBaseRepositoryHibernate;


@SuppressWarnings("all")
@Transactional
public abstract class BaseRepositoryHibernate<T> extends HibernateDaoSupport implements IBaseRepositoryHibernate<T>{
	
	protected abstract Class<T> getEntityClass();
	
	public T findOneByCriterion( Criterion... criterion )
    {
        try
        {
            List<T> lista = this.findListByCriterion( criterion );
            return ( lista != null && lista.size() != 0 ) ? lista.get( 0 ) : null;
        } catch ( HibernateException ex )
        {
        	throw new DAOException( ex, getEntityClass(), "erro.dao.find.criterion" );
        }
    }
	
	public List<T> findListByCriterion( Criterion... criterion )
    {
        try
        {
            DetachedCriteria criteria = DetachedCriteria.forClass( getEntityClass() );
            for ( Criterion c : criterion )
                criteria.add( c );
            return getHibernateTemplate().findByCriteria( criteria );
            
        } catch ( Exception ex )
        {
        	throw new DAOException( ex, getEntityClass(), "erro.dao.find.criterion" );
        }
    }
	
	
    public T findByOneCriteria( DetachedCriteria criteria )
    {
        try
        {
            List<T> list = findByCriteria( criteria );
            if ( list != null && list.size() > 0 )
                return list.get( 0 );
            return null;
        } catch ( HibernateException ex )
        {
            throw new DAOException( ex, getEntityClass(), "erro.dao.findcriteria" );
        }
    }
    
    public List<T> findByCriteria( DetachedCriteria criteria )
    {
        try
        {
            return getHibernateTemplate().findByCriteria( criteria );
        } catch ( HibernateException ex )
        {
            throw new DAOException( ex, getEntityClass(), "erro.dao.findcriteria" );
        }
    }
    
    public void deletar (T entity)
    {
    	getHibernateTemplate().delete(entity);
    }
    
    public void deleteAll (List<T> listEntities)
    {
    	getHibernateTemplate().deleteAll( listEntities );
    }
	
	public T salvar(T t){
		try {
			getHibernateTemplate().save(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
	
	public T saveOrUpdate(T t){
		try {
			getHibernateTemplate().saveOrUpdate(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
	
	public T merge(T t){
		try {
			getHibernateTemplate().merge(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
	
	public List<T> saveAll(List<T> listT){
		try {
			getHibernateTemplate().saveOrUpdateAll( listT );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listT;
	}
	
	public T buscar( Long id )
	{
		return (T) getHibernateTemplate().get( getEntityClass(), id );
	}
	
	public List<T> listar( )
	{
		return getHibernateTemplate().loadAll( getEntityClass() );
	}
	
	public int executeUpdate(String sql)
	{
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		return sqlQuery.executeUpdate();
	}
	
	
	@Autowired
	public void init(@Qualifier(value="sessionFactory")SessionFactory factory) {
	    setSessionFactory(factory);
	}
	
}
