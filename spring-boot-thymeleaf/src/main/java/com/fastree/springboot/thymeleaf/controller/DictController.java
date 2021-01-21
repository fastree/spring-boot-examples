package com.fastree.springboot.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DictController {
    @GetMapping("/system/dict")
    public String index() {
        return "system/dict";
    }
}
