package com.zcw.auth.service;

import com.zcw.auth.dao.ProcessTaskDao;
import com.zcw.auth.dao.entity.ProcessTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ProcessTaskServiceImpl implements ProcessTaskService {
    private static final Logger log = LoggerFactory.getLogger(ProcessTaskServiceImpl.class);

    @Autowired
    private ProcessTaskDao processTaskDao;


    @Override
    public void saveTask(ProcessTask processTask) {
        processTaskDao.saveOrUpdate(processTask);
    }

    @Override
    public ProcessTask getById(String id) {
        return processTaskDao.getById(id);
    }
}
