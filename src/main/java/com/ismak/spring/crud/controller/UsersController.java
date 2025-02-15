package com.ismak.spring.crud.controller;

import com.ismak.spring.crud.model.User;
import com.ismak.spring.crud.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public String index(Model model) {
        List<User> list = userService.index();
        model.addAttribute("people", list);
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@RequestParam("id") int id, Model model) {
        User user = userService.show(id);
            model.addAttribute("user", user);
        return "people/show";
        ///user?id=1
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("user") User user) {
        return "people/new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "people/new";
        } else {
            userService.save(user);
            return "redirect:/users";
        }
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam("id") int id) {
        User user = userService.show(id);
        model.addAttribute("user", user);
        return "people/edit";

    }

    @PatchMapping("/update")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @RequestParam("id") int id) {
        if (bindingResult.hasErrors()) {
            return "people/edit";
        } else {
            userService.update(id, user);
        }
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@RequestParam("id") int id) {
        userService.delete(id);
        return "redirect:users";
    }

}
