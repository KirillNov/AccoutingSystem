package com.novikovkirill.test.accountingsystem.controller;


import com.novikovkirill.test.accountingsystem.models.Directory;
import com.novikovkirill.test.accountingsystem.services.DirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

/**
 * Контроллер для работы с категориями. Доступна админу
 */


@Controller
@RequestMapping("/admin/directories")
@PreAuthorize("hasAuthority('ADMIN')")
public class DirectoryController {
    private final DirectoryService directoryService;

    @Autowired
    public DirectoryController(DirectoryService directoryService) {
        this.directoryService = directoryService;
    }


    @GetMapping("/")
    public String newDirectory (Model model) {
        List<Directory> directories = directoryService.findAll();
        Collections.reverse(directories);
        model.addAttribute("directories", directories);
        model.addAttribute("directory", new Directory());
        return "admin/directories";
    }

    @PostMapping("/")
    public String createDirectory (@Valid @ModelAttribute("directory") Directory directory,
                                   BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            List<Directory> directories = directoryService.findAll();
            Collections.reverse(directories);
            model.addAttribute("directories", directories);
            return "admin/directories";
        } else {
            directoryService.save(directory);
            return "redirect:/admin/directories/";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteDirectory(@PathVariable("id") Long id) {
        directoryService.delete(id);
        return "redirect:/admin/directories/";
    }

}
