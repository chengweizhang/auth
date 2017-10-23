package com.zcw.auth.dao.base;

import com.zcw.auth.dao.common.BaseQuery;
import com.zcw.auth.dao.common.Page;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by zhangsan on 2015/8/8.
 */
public abstract class BaseDaoImpl<T, U extends Serializable> implements
        BaseDao<T, U> {

    private static final int BATCH_SIZE = 100;

    @Autowired
    private EntityManagerFactory entityManagerFactory;


    private SessionFactory sessionFactory;

    Class<T> entityClass;

    String tableName;

    public BaseDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.tableName = entityClass.getSimpleName();
    }

    @Override
    public List<T> getByPage(Page page) {
        Criteria criteria = getCriteria();
        return findByCriteria(criteria, page);
    }

    @Override
    public List<T> findByCriteria(Criteria criteria, Page page,
                                  List<Order> orders) {
        addPageAndOrder(criteria, page, orders);
        return criteria.list();
    }

    @Override
    public List<T> findByCriteria(Criteria criteria, Page page) {
        return findByCriteria(criteria, page, null);
    }

    @Override
    public List<T> findByCriterion(Criterion criterion, Page page) {
        Criteria criteria = getCriteria();
        if (criterion != null) {
            criteria.add(criterion);
        }
        return findByCriteria(criteria, page);
    }

    @Override
    public T getById(U id) {
        return (T) getCurrentSession().get(entityClass, id);
    }

    @Override
    public U insert(T obj) {
        return (U) getCurrentSession().save(obj);
    }

    @Override
    public void saveOrUpdate(T obj) {
        getCurrentSession().saveOrUpdate(obj);
    }

    @Override
    public void saveOrUpdate(Collection<T> objs) {
        int i = 0;
        Session session = getCurrentSession();
        for (T obj : objs) {
            session.saveOrUpdate(obj);
            i++;
            if (i % BATCH_SIZE == 0) {
                session.flush();
                session.clear();
            }
        }
    }

    @Override
    public void insert(Collection<T> objs) {
        int i = 0;
        Session session = getCurrentSession();
        for (T obj : objs) {
            session.save(obj);
            i++;
            if (i % BATCH_SIZE == 0) {
                session.flush();
                session.clear();
            }
        }
    }

    @Override
    public void update(Collection<T> objs) {
        int i = 0;
        Session session = getCurrentSession();
        for (T obj : objs) {
            session.update(obj);
            i++;
            if (i % BATCH_SIZE == 0) {
                session.flush();
                session.clear();
            }
        }
    }

    @Override
    public void update(T obj) {
        getCurrentSession().update(obj);
    }

    @Override
    public void merge(T obj) {
        getCurrentSession().merge(obj);
    }

    @Override
    public void delete(T obj) {
        getCurrentSession().delete(obj);
    }

    @Override
    public int deleteAll() {
        String hql = String.format("delete from %s", tableName);
        return executeHqlUpdate(hql);
    }

    @Override
    public int deleteById(U id) {
        return getDeleteByIdHql(id);
    }

    protected Session getCurrentSession() {
        SessionFactory usedSessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Table annotation = entityClass.getAnnotation(Table.class);
        if (annotation == null || "esign_treaty".equals(annotation.schema())) {
        }
        return usedSessionFactory.getCurrentSession();
    }

    protected int getDeleteByIdHql(U id) {
        StringBuilder hql = new StringBuilder("delete from ");
        hql.append(tableName).append(" where id = :id");
        Query q = getCurrentSession().createQuery(hql.toString());
        q.setString("id", id.toString());
        getCurrentSession().flush();
        return q.executeUpdate();
    }

    @Override
    public Long getCount(Criteria criteria) {
        criteria.setProjection(Projections.countDistinct("id"));
        List list = criteria.list();
        return (Long) list.get(0);
    }

    @Override
    public Long getCount(Criterion criterion) {
        Criteria criteria = getCriteria();
        if (criterion != null) {
            criteria.add(criterion);
        }
        return getCount(criteria);
    }

    protected int executeHqlUpdate(String hql) {
        return getCurrentSession().createQuery(hql).executeUpdate();
    }

    public Criteria getCriteria() {
        return getCurrentSession().createCriteria(entityClass);
    }

    /**
     * 添加分页和排序 默认排序
     *
     * @param criteria
     * @param page
     * @return
     */
    public static Criteria addPageAndOrder(Criteria criteria, Page page,
                                           List<Order> orders) {
        if (!Page.isPageValid(page)) {
            page = Page.DEFAULT_PAGE;
        }
        criteria.setFirstResult(page.getFirstNumber()).setMaxResults(
                page.getPageSize());
        if (orders == null || orders.size() == 0) {
            criteria.addOrder(Page.DEFAULT_ORDER);
        } else {
            for (Order order : orders) {
                criteria.addOrder(order);
            }
        }
        return criteria;
    }

    @Override
    public List<T> query(BaseQuery query) {
        return this.findByCriteria(query.buildCriteria(getCriteria()), query.getPage(), query.getOrder());
    }

    @Override
    public Long getCount(BaseQuery query) {
        return this.getCount(query.buildCriteria(getCriteria()));
    }
}
