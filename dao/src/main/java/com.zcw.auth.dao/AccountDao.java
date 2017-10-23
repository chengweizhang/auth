package com.zcw.auth.dao;


import com.zcw.auth.dao.base.BaseDao;
import com.zcw.auth.dao.common.Page;
import com.zcw.auth.dao.entity.Account;

import java.util.List;

public interface AccountDao extends BaseDao<Account, String> {
    List<Account> findByQuery(AccountQuery query);

    Long getCountByQuery(AccountQuery query);

    Account findUniqueByQuery(AccountQuery query);

    List<Account> findByQuery(AccountQuery query, Page page);
}
