package com.zcw.auth.service;

import com.zcw.auth.dao.GeneralDaoImpl;
import com.zcw.auth.dao.common.Page;
import com.zcw.auth.dao.entity.Role;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class GeneralServiceImpl {

    private static final Logger log = LoggerFactory.getLogger(GeneralServiceImpl.class);

    @Autowired
    private GeneralDaoImpl generalDao;

    public int deleteAccountRoleRByMixId(String accountId, String roleId) {
        String hql = "delete from AccountRoleR  where accountId=:accountId and roleId=:roleId";
        Map<String, String> properties = new HashMap<>();
        properties.put("accountId", accountId);
        properties.put("roleId", roleId);

        return generalDao.delete(hql, properties);
    }

    public int deleteRecordById(String id) {
        String hql = "DELETE FROM TABLE where id=:id";
        Map<String, String> properties = new HashMap<>();
        properties.put("entId", id);
        return generalDao.delete(hql, properties);
    }

    public Long getObjectCount(Class entityClass) {
        Criteria criteria = generalDao.getCriteria(entityClass);
        criteria.setProjection(Projections.countDistinct("id"));
        List list = criteria.list();
        return (Long) list.get(0);
    }

    public <T> List<T> getObject(Class<T> entityClass, Page page) {
        Criteria criteria = generalDao.getCriteria(entityClass);
        criteria.addOrder(Order.asc("modifyTime"));
        if (!Page.isPageValid(page)) {
            page = Page.DEFAULT_PAGE;
        }
        criteria.setFirstResult(page.getFirstNumber()).setMaxResults(
                page.getPageSize());
        List objects = criteria.list();
        return objects;
    }


    public List<Role> getRoles(Page page) {
        Criteria criteria = generalDao.getCriteria(Role.class);
        return (List) generalDao.findByCriterion(criteria);
    }

    public String saveObj(Object obj) {
        return (String) generalDao.saveObj(obj);
    }

    public void saveOrUpdateObj(Object obj) {
        generalDao.saveOrUpdateObj(obj);
    }

    public void saveOrUpdateObj(List objs) {
        generalDao.saveOrUpdateObj(objs);
    }

    public void updateObj(Object obj) {
        generalDao.updateObj(obj);
    }

    public void deleteObj(Object obj) {
        generalDao.deleteObj(obj);
    }
}
