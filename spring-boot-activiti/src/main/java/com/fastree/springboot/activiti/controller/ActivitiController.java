package com.fastree.springboot.activiti.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivitiController {


    @GetMapping("/hello")
    public String hello() {
        return null;
    }
}
