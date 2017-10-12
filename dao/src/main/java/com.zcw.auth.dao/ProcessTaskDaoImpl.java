package com.zcw.auth.dao;

import com.zcw.auth.dao.base.BaseDaoImpl;
import com.zcw.auth.dao.entity.ProcessTask;
import org.springframework.stereotype.Repository;

@Repository
public class ProcessTaskDaoImpl extends BaseDaoImpl<ProcessTask, String> implements ProcessTaskDao {
    public ProcessTaskDaoImpl() {
        super(ProcessTask.class);
    }
}
