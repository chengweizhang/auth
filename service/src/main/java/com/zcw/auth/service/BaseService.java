package com.zcw.auth.service;


import com.zcw.auth.dao.common.Page;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/11/21.
 */
public interface BaseService<T, U extends Serializable> {

    U insert(T obj);

    void insert(List<T> obj);

    void saveOrUpdate(T obj);

    void update(T obj);

    T getById(U id);

    List<T> getByPage(Page page);

    void delete(T obj);

    int deleteAll();

    int deleteById(U id);
}
