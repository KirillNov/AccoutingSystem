package com.novikovkirill.test.accountingsystem.controller;

import com.novikovkirill.test.accountingsystem.models.Appeal;
import com.novikovkirill.test.accountingsystem.models.User;
import com.novikovkirill.test.accountingsystem.services.AppealService;
import com.novikovkirill.test.accountingsystem.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

/**
 * Контроллер для работы с админской панелью.
 */

@Controller
@RequestMapping("/admin")
@Slf4j
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private final UserService userService;
    private final AppealService appealService;

    @Autowired
    public AdminController(UserService userService, AppealService appealService) {
        this.userService = userService;
        this.appealService = appealService;
    }

    @GetMapping("/")
    public String showAdminConsole() {
        return "admin/admin";
    }

    @GetMapping("/users")
    public String showAllUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin/users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/users/";
    }

    @GetMapping("/appeals")
    public String showAllAppeals(Model model) {
        List<Appeal> appeals = appealService.findAll();
        Collections.reverse(appeals);
        model.addAttribute("appeals", appeals);
        return "admin/appeals";
    }

    @GetMapping("appeals/show/{id}")
    public String showAppeal (@PathVariable("id") Long id, Model model) {
        Appeal appeal = appealService.findById(id);
        model.addAttribute("path", "admin/appeals/");
        model.addAttribute("appeal", appeal);
        return "user/show";
    }

    @GetMapping("/appeals/delete/{id}")
    public String deleteAppeal(@PathVariable("id") Long id) {
        appealService.delete(id);
        return "redirect:/admin/appeals/";
    }



}
