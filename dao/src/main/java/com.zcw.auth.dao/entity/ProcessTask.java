package com.zcw.auth.dao.entity;


import com.zcw.auth.dao.entity.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "app_process_task")
public class ProcessTask extends BaseEntity<String> {

    private String staffId;

    /**
     * 0:未完成  1：同意  2:拒绝   3:转交
     */
    private int status;

    /**
     * 评论内容
     */
    private String remark;

    /**
     * 0:开始; 1:任务结束或转交 2:评论
     */
    private int type;
    private String title;

    private Date startTime;
    private Date endTime;

    public String getStaffId() {
        return staffId;
    }

    public ProcessTask setStaffId(String staffId) {
        this.staffId = staffId;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public ProcessTask setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public Date getStartTime() {
        return startTime;
    }

    public ProcessTask setStartTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    public Date getEndTime() {
        return endTime;
    }

    public ProcessTask setEndTime(Date endTime) {
        this.endTime = endTime;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ProcessTask setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public ProcessTask setStatus(int status) {
        this.status = status;
        return this;
    }

    public int getType() {
        return type;
    }

    public ProcessTask setType(int type) {
        this.type = type;
        return this;
    }
}
