package com.zcw.auth.dao;

import com.zcw.auth.dao.base.BaseDaoImpl;
import com.zcw.auth.dao.common.Page;
import com.zcw.auth.dao.entity.Account;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDaoImpl extends BaseDaoImpl<Account, String> implements AccountDao {

    public AccountDaoImpl() {
        super(Account.class);
    }

    @Override
    public Account getById(String id) {
        return (Account) getCriteria().setFetchMode("accountRoleRs", FetchMode.JOIN).add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public List<Account> findByQuery(AccountQuery query, Page page) {
        Criteria criteria = query == null ? getCriteria() : query.buildCriteria(getCriteria());
        return findByCriteria(criteria, page);
    }

    @Override
    public Long getCountByQuery(AccountQuery query) {
        Criteria criteria = query != null ? query.buildCriteria(getCriteria()) : getCriteria();
        return getCount(criteria);
    }

    @Override
    public List<Account> findByQuery(AccountQuery query) {
        Criteria criteria = query == null ? getCriteria() : query.buildCriteria(getCriteria());
        return criteria.list();
    }

    @Override
    public Account findUniqueByQuery(AccountQuery query) {
        Criteria criteria = query == null ? getCriteria() : query.buildCriteria(getCriteria());
        return (Account) criteria.uniqueResult();
    }
}
