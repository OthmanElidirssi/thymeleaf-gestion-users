package com.example.thymleaf.controllers;


import com.example.thymleaf.entities.User;
import com.example.thymleaf.repositories.UserRepositiry;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {



    @Autowired
    private UserRepositiry userRepositiry;


    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return"add-user";
    }


    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return"add-user";
        }

        userRepositiry.save(user);
        model.addAttribute("users", userRepositiry.findAll());
        return"index";
    }


    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id")long id, Model model) {
        User user = userRepositiry.findById(id)
                .orElseThrow(() ->new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return"update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return"update-user";
        }

        userRepositiry.save(user);
        model.addAttribute("users", userRepositiry.findAll());
        return"index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userRepositiry.findById(id)
                .orElseThrow(() ->new IllegalArgumentException("Invalid user Id:" + id));
        userRepositiry.delete(user);
        model.addAttribute("users", userRepositiry.findAll());
        return"index";
    }





}
