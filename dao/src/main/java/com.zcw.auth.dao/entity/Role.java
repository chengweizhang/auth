package com.zcw.auth.dao.entity;

import com.zcw.auth.dao.entity.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by zhangchengwei on 2016/3/7.
 */
@Entity
@Table(name = "auth_role", uniqueConstraints =
@UniqueConstraint(columnNames = {"name"}))
public class Role extends BaseEntity {

    public enum RoleType {
        /**
         *
         */
        USER,
        /**
         * 超级管理员
         */
        ADMINISTRATOR
    }

    /**
     * 角色类型
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleType type;

    public Role(RoleType type) {
        this.type = type;
    }

    /**
     * 角色名
     */

    private String name;

    /**
     * 角色描述
     */

    private String description;


    public Role() {
    }

    public Role(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Role setName(String name) {
        this.name = name;
        return this;
    }

    public RoleType getType() {
        return type;
    }

    public Role setType(RoleType type) {
        this.type = type;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Role setDescription(String description) {
        this.description = description;
        return this;
    }
}
