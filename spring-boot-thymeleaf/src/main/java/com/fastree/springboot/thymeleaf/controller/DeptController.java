package com.fastree.springboot.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeptController {
    @GetMapping("/hr/dept")
    public String index() {
        return "hr/dept";
    }
}
