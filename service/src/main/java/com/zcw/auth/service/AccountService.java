package com.zcw.auth.service;


import com.zcw.auth.dao.AccountQuery;
import com.zcw.auth.dao.common.Page;
import com.zcw.auth.dao.entity.Account;

import java.util.List;

public interface AccountService extends BaseService<Account, String> {

    void update(List<Account> objs);

    List<Account> findByQuery(AccountQuery query);

    Long getCountByQuery(AccountQuery query);

    Account findUniqueByQuery(AccountQuery query);

    List<Account> findByQuery(AccountQuery query, Page page);
}
