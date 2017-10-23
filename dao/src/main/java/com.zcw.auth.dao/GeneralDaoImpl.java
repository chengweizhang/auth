package com.zcw.auth.dao;

import com.zcw.auth.dao.base.BaseDaoImpl;
import com.zcw.auth.dao.common.Page;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import java.io.Serializable;
import java.util.List;
import java.util.Map;


@Repository
public class GeneralDaoImpl {

    private SessionFactory sessionFactory;

    @Autowired
    public GeneralDaoImpl(EntityManagerFactory entityManagerFactory) {
        sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
    }

    public List findByCriterion(Criteria criteria) {
        BaseDaoImpl.addPageAndOrder(criteria, Page.MAX_PAGE, null);
        return criteria.list();
    }

    public Object findOneResult(Criteria criteria) {
        return criteria.uniqueResult();
    }

    public int delete(String hql, Map<String, String> properties) {
        Query query = getCurrentSession().createQuery(hql);
        for (Map.Entry<String, String> property : properties.entrySet()) {
            query.setString(property.getKey(), property.getValue());
        }

        return query.executeUpdate();
    }

    public Serializable saveObj(Object obj) {
        return getCurrentSession().save(obj);
    }

    public void saveOrUpdateObj(Object obj) {
        getCurrentSession().saveOrUpdate(obj);
    }

    public void deleteObj(Object obj) {
        getCurrentSession().delete(obj);
    }

    public void updateObj(Object obj) {
        getCurrentSession().update(obj);
    }

    public void saveObjs(List objs) {
        int i = 0;
        Session session = getCurrentSession();
        for (Object obj : objs) {
            session.save(obj);
            i++;
            if (i % Page.BATCH_SIZE == 0) {
                session.flush();
                session.clear();
            }
        }
    }

    public void saveOrUpdateObj(List objs) {
        int i = 0;
        Session session = getCurrentSession();
        for (Object obj : objs) {
            session.saveOrUpdate(obj);
            i++;
            if (i % Page.BATCH_SIZE == 0) {
                session.flush();
                session.clear();
            }
        }
    }

    public <T> Criteria getCriteria(Class<T> entityClass) {
        return getCurrentSession().createCriteria(entityClass);
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
