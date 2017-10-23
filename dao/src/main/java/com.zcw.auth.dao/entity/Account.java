package com.zcw.auth.dao.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zcw.auth.dao.entity.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * 个人基本信息
 */
@Entity
@Table(name = "auth_account")

public class Account extends BaseEntity {

    public enum AccountType {
        PERSON, ORGAN
    }

    /**
     * 姓名
     */
    @NotNull
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AccountType type = AccountType.PERSON;

    private Integer status = 1;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 密码
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /**
     * 电子邮箱
     */
    private String email;


    @OneToMany(mappedBy = "account")
    private Set<AccountRoleR> accountRoleRs;

    public String getName() {
        return name;
    }

    public Account() {
    }

    public Account(String id) {
        this.id = id;
    }

    public Account setName(String name) {
        this.name = name;
        return this;
    }

    public AccountType getType() {
        return type;
    }

    public Account setType(AccountType type) {
        this.type = type;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Account setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public Account setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public Account setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Account setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Account setEmail(String email) {
        this.email = email;
        return this;
    }

    public Set<AccountRoleR> getAccountRoleRs() {
        return accountRoleRs;
    }

    public Account setAccountRoleRs(Set<AccountRoleR> accountRoleRs) {
        this.accountRoleRs = accountRoleRs;
        return this;
    }
}
