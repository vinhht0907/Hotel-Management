package com.example.demo2.controller;

import com.example.demo2.entity.Role;
import com.example.demo2.entity.User;
import com.example.demo2.repository.RoleRepository;
import com.example.demo2.repository.UserRepository;
import com.example.demo2.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserRepository repoUser;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/admin/users/list")
    public String listUser(Model model){
        List<User> list = userService.ListAll();
        model.addAttribute("uList",list);
        return "admin/users/index";
    }

    @GetMapping("/admin/users/create")
    public String create(Model model){
        List<Role> role = roleRepository.findAll();
        model.addAttribute("rList",role);
        return "admin/users/create";
    }

    @PostMapping("/admin/users/create")
    public String create(@ModelAttribute User user){
        repoUser.save(user);
        return "redirect:/admin/users/list";
    }

    @GetMapping("/admin/users/edit")
    public String edit(@RequestParam("id") int id, Model model){
        User user = repoUser.findById(id).get();
        model.addAttribute("user",user);
        List<Role> role = roleRepository.findAll();
        model.addAttribute("rList",role);
        return "admin/users/edit";
    }

    @PostMapping("/admin/users/edit")
    public String edit(@ModelAttribute("user") User user){
        repoUser.save(user);
        return "redirect:/admin/users/list";
    }

    @GetMapping("/admin/users/delete")
    public String delete(@RequestParam("id") int id){
        repoUser.deleteById(id);
        return "redirect:/admin/users/list";
    }
}
