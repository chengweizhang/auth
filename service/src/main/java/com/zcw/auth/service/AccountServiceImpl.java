package com.zcw.auth.service;

import com.zcw.auth.dao.AccountDao;
import com.zcw.auth.dao.AccountQuery;
import com.zcw.auth.dao.GeneralDaoImpl;
import com.zcw.auth.dao.common.Page;
import com.zcw.auth.dao.entity.Account;
import com.zcw.auth.dao.entity.AccountRoleR;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private GeneralDaoImpl generalDao;

    @Override
    public List<Account> findByQuery(AccountQuery query) {
        return accountDao.findByQuery(query);
    }

    @Override
    public List<Account> findByQuery(AccountQuery query, Page page) {
        return accountDao.findByQuery(query, page);
    }

    @Override
    public Long getCountByQuery(AccountQuery query) {
        return accountDao.getCountByQuery(query);
    }

    @Override
    public Account findUniqueByQuery(AccountQuery query) {
        return accountDao.findUniqueByQuery(query);
    }

    @Override
    public String insert(Account obj) {
        return accountDao.insert(obj);
    }

    @Override
    public void saveOrUpdate(Account obj) {
        log.debug("service insert begin!");
        accountDao.saveOrUpdate(obj);
    }

    @Override
    public void insert(List<Account> objs) {
        log.debug("service insert begin!");
        accountDao.insert(objs);
    }

    @Override
    public void update(List<Account> objs) {
        log.debug("service insert begin!");
        accountDao.update(objs);
    }


    @Override
    public void update(Account baAccount) {
        accountDao.update(baAccount);
    }

    public List<Account> getByPage(Page page) {
        return accountDao.getByPage(page);
    }

    @Override
    public Account getById(String id) {
        return accountDao.getById(id);
    }

    public void delete(Account obj) {
        accountDao.delete(obj);
    }

    public int deleteById(String id) {
        Account account = getById(id);
        Set<AccountRoleR> accountRoleRs = account.getAccountRoleRs();
        accountRoleRs.forEach(a ->
                generalDao.deleteObj(a)
        );
        return accountDao.deleteById(id);
    }

    @Override
    public int deleteAll() {
        return accountDao.deleteAll();
    }
}
