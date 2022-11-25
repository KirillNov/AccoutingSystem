package com.novikovkirill.test.accountingsystem.controller;


import com.novikovkirill.test.accountingsystem.models.Appeal;
import com.novikovkirill.test.accountingsystem.models.Directory;
import com.novikovkirill.test.accountingsystem.models.User;
import com.novikovkirill.test.accountingsystem.services.AppealService;
import com.novikovkirill.test.accountingsystem.services.DirectoryService;
import com.novikovkirill.test.accountingsystem.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Контроллер для работы с обращениями пользователя.
 */


@Controller
@RequestMapping("/appeal")
@Slf4j
public class AppealController {

    private final AppealService appealService;
    private final DirectoryService directoryService;

    private final UserService userService;



    @Autowired
    public AppealController(AppealService appealService, DirectoryService directoryService, UserService userService) {
        this.appealService = appealService;
        this.directoryService = directoryService;
        this.userService = userService;
    }


    /**
     * Метод который отображает все обращения аутентифицированного пользователя в таблице
     */

    @GetMapping("/")
    public String showAllAppealByEmail(Model model) {
        User user = initUser();
        List<Appeal> appeals = appealService.findAllByUser_Email(user.getEmail());
        model.addAttribute("appeals", appeals);
        model.addAttribute("user", user);
        return "user/appeal";
    }

    @GetMapping("/new")
    public String newAppeal (Model model) {
        List<Directory> directories = directoryService.findAll();
        User user = initUser();
        model.addAttribute("path", "new/");
        model.addAttribute("directories", directories);
        model.addAttribute("user", user);
        model.addAttribute("appeal", new Appeal());
        model.addAttribute("name", "Новое обращение");
        model.addAttribute("add", "Добавить");
        return "user/new";
    }

    @PostMapping("/new")
    public String createAppeal (@Valid @ModelAttribute("appeal") Appeal appeal,
                                BindingResult bindingResult, Model model) {
        User user = initUser();
        if(bindingResult.hasErrors()) {
            List<Directory> directories = directoryService.findAll();
            model.addAttribute("path", "new/");
            model.addAttribute("directories", directories);
            model.addAttribute("user", user);
            model.addAttribute("name", "Новое обращение");
            model.addAttribute("add", "Добавить");
            return "user/new";
        } else {
            appeal.setUser(user);
            appeal.setDate(initDate());
            appealService.save(appeal);
            return "redirect:/appeal/";
        }
    }

    @GetMapping("/edit/{id}")
    public String editAppeal (@PathVariable("id") Long id, Model model) {
        Appeal appeal = appealService.findById(id);
        List<Directory> directories = directoryService.findAll();
        model.addAttribute("path", "edit");
        model.addAttribute("appeal", appeal);
        model.addAttribute("directories", directories);
        model.addAttribute("user", appeal.getUser());
        model.addAttribute("name", "Редактирование");
        model.addAttribute("add", "Сохранить");
        return "user/new";
    }

    @PostMapping("/edit")
    public String updateAppeal (@Valid @ModelAttribute ("appeal") Appeal appeal,
                                    BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            List<Directory> directories = directoryService.findAll();
            model.addAttribute("path", "edit");
            model.addAttribute("user", appeal.getUser());
            model.addAttribute("directories", directories);
            model.addAttribute("name", "Редактирование");
            model.addAttribute("add", "Сохранить");

            return "user/new";
        } else {
            User user = initUser();
            appeal.setUser(user);
            appeal.setDate(initDate());
            appealService.save(appeal);
            return "redirect:/appeal/";
        }
    }

    @GetMapping("/show/{id}")
    public String showAppeal (@PathVariable("id") Long id, Model model) {
        Appeal appeal = appealService.findById(id);
        model.addAttribute("path", "appeal/");
        model.addAttribute("appeal", appeal);
        return "user/show";
    }

    @GetMapping("/delete/{id}")
    public String deleteAppeal(@PathVariable("id") Long id) {
        appealService.delete(id);
        return "redirect:/appeal/";
    }

    /**
     * Метод для инициализации аутентифицированного пользователя
     */

    private User initUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByEmail(auth.getName());
    }

    /**
     * Метод для инициализации даты и времени по определенному паттерну
     */

    private String initDate(){
        LocalDateTime dateNow = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss");
        return dateNow.format(format);
    }

}
