package com.zcw.auth.dao.entity.base;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;


@MappedSuperclass
public abstract class BaseEntity<T> implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @CreationTimestamp
    private Date createTime;

    @UpdateTimestamp
    private Date updateTime;

    public BaseEntity() {
    }

    public Date getCreateTime() {
        return createTime = createTime == null ? null : (Date) createTime.clone();
    }


    public Date getUpdateTime() {
        return updateTime == null ? null : (Date) updateTime.clone();
    }


    public String getId() {
        return id;
    }

    public BaseEntity setUpdateTime(Date updateTime) {
        this.updateTime = updateTime == null ? null : (Date) updateTime.clone();
        return this;
    }

    public BaseEntity setCreateTime(Date createTime) {
        this.createTime = createTime == null ? null : (Date) createTime.clone();
        return this;
    }

    public BaseEntity setId(String id) {
        this.id = id;
        return this;
    }
}
