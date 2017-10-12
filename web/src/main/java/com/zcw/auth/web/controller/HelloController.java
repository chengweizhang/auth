package com.zcw.auth.web.controller;

import com.zcw.auth.dao.entity.ProcessTask;
import com.zcw.auth.service.ProcessTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/hello")
public class HelloController {

    @Autowired
    private ProcessTaskService processTaskService;


    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/add")
    public ProcessTask add() {
        ProcessTask task = new ProcessTask();
        task.setStatus(2);
        task.setRemark("拒绝理由2");
        task.setStaffId("staffId");

        processTaskService.saveTask(task);
        return task;
    }

    @RequestMapping("/{id}")
    public ProcessTask getById(@PathVariable String id, HttpServletRequest request) {
        ProcessTask processTask = processTaskService.getById(id);
        return processTask;
    }
}