package com.fastree.springboot.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoleController {
    @GetMapping("/system/role")
    public String index() {
        return "system/role";
    }
}
