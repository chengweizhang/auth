package com.zcw.auth.dao;

import com.zcw.auth.dao.entity.Account;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

public class AccountQuery {

    /**
     * 名称
     */
    private String name;

    private Account.AccountType type;
    /**
     * 登录用户名
     */
    private String userName;

    private String mobile;

    private String email;

    public Criteria buildCriteria(Criteria criteria) {

        criteria.setFetchMode("accountRoleRs", FetchMode.JOIN);

        if (StringUtils.isNotBlank(name)) {
            SimpleExpression nameExp = Restrictions.eq("name", name);
            criteria.add(nameExp);
        }

        if (type != null) {
            SimpleExpression typeExp = Restrictions.eq("type", type);
            criteria.add(typeExp);
        }

        if (StringUtils.isNotBlank(userName)) {
            SimpleExpression nameExp = Restrictions.eq("userName", userName);
            criteria.add(nameExp);
        }

        if (StringUtils.isNotBlank(email)) {
            SimpleExpression identityNumExp = Restrictions.eq("email", email);
            criteria.add(identityNumExp);
        }

        if (StringUtils.isNotBlank(mobile)) {
            SimpleExpression identityNumExp = Restrictions.eq("mobile", mobile);
            criteria.add(identityNumExp);
        }
        return criteria;
    }

    public String getName() {
        return name;
    }

    public AccountQuery setName(String name) {
        this.name = name;
        return this;
    }

    public Account.AccountType getType() {
        return type;
    }

    public AccountQuery setType(Account.AccountType type) {
        this.type = type;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public AccountQuery setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public AccountQuery setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AccountQuery setEmail(String email) {
        this.email = email;
        return this;
    }
}
