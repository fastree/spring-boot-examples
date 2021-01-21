package com.fastree.springboot.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {
    @GetMapping("/hr/post")
    public String index() {
        return "hr/post";
    }
}
