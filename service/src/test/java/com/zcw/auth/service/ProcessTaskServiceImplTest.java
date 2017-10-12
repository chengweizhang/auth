package com.zcw.auth.service;

import com.zcw.auth.dao.entity.ProcessTask;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhangchengwei on 09/10/2017.
 */
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ProcessTaskServiceImplTest extends BaseTest {
    @Autowired
    private ProcessTaskService processTaskService;

    @Test
    public void curdTest() throws Exception {
        ProcessTask task = new ProcessTask();
        task.setStatus(2);
        task.setRemark("拒绝理由2");
        task.setStaffId("staffId");

        processTaskService.saveTask(task);

        ProcessTask processTask = processTaskService.getById(task.getId());

        System.out.println(processTask.getId());
    }
}
