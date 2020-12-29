package com.fastree.springboot.flowable.controller;

import org.flowable.common.engine.api.delegate.event.FlowableEvent;
import org.flowable.common.engine.api.delegate.event.FlowableEventListener;
import org.flowable.engine.*;
import org.flowable.engine.delegate.event.BaseEntityEventListener;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowableController {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private FormService formService;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private ManagementService managementService;
    @Autowired
    private DynamicBpmnService dynamicBpmnService;

    @GetMapping("/hello")
    public String hello() {
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        return null;
    }

    @GetMapping("/auth")
    public String auth() {
        return null;
    }
}
