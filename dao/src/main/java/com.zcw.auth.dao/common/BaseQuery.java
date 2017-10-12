package com.zcw.auth.dao.common;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import java.util.List;

public interface BaseQuery {
    Criteria buildCriteria(Criteria criteria);

    Page getPage();

    List<Order> getOrder();
}
