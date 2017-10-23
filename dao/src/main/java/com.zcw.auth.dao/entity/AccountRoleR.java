package com.zcw.auth.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zcw.auth.dao.entity.base.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "auth_account_role_r")
public class AccountRoleR extends BaseEntity {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "accountId", nullable = false, updatable = false)
    private Account account;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roleId", nullable = false, updatable = false)
    private Role role;

    public Account getAccount() {
        return account;
    }


    public AccountRoleR() {
    }

    public AccountRoleR(Account account, String roleId) {
        this.account = account;
        this.role = new Role(roleId);
    }

    public AccountRoleR(Account account, Role role) {
        this.account = account;
        this.role = role;
    }

    public AccountRoleR setAccount(Account account) {
        this.account = account;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public AccountRoleR setRole(Role role) {
        this.role = role;
        return this;
    }
}
