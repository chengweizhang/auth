package com.zcw.auth.dao.base;

import com.zcw.auth.dao.common.BaseQuery;
import com.zcw.auth.dao.common.Page;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2015/11/21.
 */
public interface BaseDao<T, U extends Serializable> {

    U insert(T obj);

    void saveOrUpdate(T obj);

    void saveOrUpdate(Collection<T> obj);

    void insert(Collection<T> objs);

    void update(Collection<T> objs);

    void update(T obj);

    void merge(T obj);

    T getById(U id);

    List<T> getByPage(Page page);

    List<T> findByCriteria(Criteria criteria, Page page, List<Order> orders);

    Criteria getCriteria();

    List<T> findByCriterion(Criterion criterion, Page page);

    List<T> findByCriteria(Criteria criteria, Page page);

    Long getCount(Criteria criteria);

    Long getCount(Criterion criterion);

    void delete(T obj);

    int deleteAll();

    int deleteById(U id);

    List<T> query(BaseQuery query);

    Long getCount(BaseQuery query);
}
