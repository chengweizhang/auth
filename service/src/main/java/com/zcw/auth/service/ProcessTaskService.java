
package com.zcw.auth.service;

import com.zcw.auth.dao.entity.ProcessTask;

/**
 * Created by zhangchengwei on 2017/6/14.
 */
public interface ProcessTaskService {

    void saveTask(ProcessTask processTask);

    ProcessTask getById(String id);
}

