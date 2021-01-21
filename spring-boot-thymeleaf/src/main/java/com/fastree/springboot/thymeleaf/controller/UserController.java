package com.fastree.springboot.thymeleaf.controller;

import com.fastree.springboot.thymeleaf.entity.MenuEntity;
import com.fastree.springboot.thymeleaf.service.MenuService;
import com.fastree.springboot.thymeleaf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(Principal principal, Model model) {
        String name = principal.getName();
        Long userId = userService.getUserIdByName(name);
        List<MenuEntity> userMenuTree = menuService.getUserMenuTree(userId);
        model.addAttribute("menus", userMenuTree);
        return "index";
    }

    @GetMapping("/user/main")
    public String main() {
        return "user/main";
    }

}
