package com.fastree.springboot.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmpController {
    @GetMapping("/hr/emp")
    public String index() {
        return "hr/emp";
    }
}
