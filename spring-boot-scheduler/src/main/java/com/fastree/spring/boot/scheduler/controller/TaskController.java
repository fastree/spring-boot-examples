package com.fastree.spring.boot.scheduler.controller;

import com.fastree.spring.boot.scheduler.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
    @Autowired
    private TaskService quartzService;

    @GetMapping("/task/start")
    public String hello(String name) {
        quartzService.taskStart(name);
        return "this task has started ...";
    }

    @GetMapping("/task/stop")
    public String stop(String name) {
        quartzService.taskStop();
        return "the task has stopped ...";
    }
}
