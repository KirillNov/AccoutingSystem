package com.novikovkirill.test.accountingsystem.controller;


import com.novikovkirill.test.accountingsystem.models.User;
import com.novikovkirill.test.accountingsystem.models.enums.Role;
import com.novikovkirill.test.accountingsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер для работы с пользователями который позволяет регистрироваться,
 * идентифицироваться и аутентифицироваться
 */


@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "registration/login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration/registration";
    }

    @PostMapping("/registration")
    public String createUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration/registration";
        } else {

            userService.createUser(user);
            return "redirect:/login";
        }

    }
}
