package com.example.demo2.controller;

import com.example.demo2.entity.Category;
import com.example.demo2.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/user/category/create")
    public String create(){
        return "user/category/create";
    }

    @PostMapping("/user/category/create")
    public String create(@ModelAttribute("category") Category category){
        categoryRepository.save(category);
        return "redirect:/user/room/list";
    }

    @GetMapping("/user/category/delete")
    public String delete(@RequestParam("id") int id){
        categoryRepository.deleteById(id);
        return "redirect:/user/room/list";
    }

    @GetMapping("/user/category/edit")
    public String edit(@RequestParam("id") int id, Model model){
        Category category = categoryRepository.findById(id).get();
        model.addAttribute("category",category);
        return "user/category/edit";
    }

    @PostMapping("/user/category/edit")
    public String edit(@ModelAttribute("category") Category category){
        categoryRepository.save(category);
        return "redirect:/user/room/list";
    }




    @GetMapping("/admin/category/create")
    public String createC(){
        return "admin/category/create";
    }

    @PostMapping("/admin/category/create")
    public String createC(@ModelAttribute("category") Category category){
        categoryRepository.save(category);
        return "redirect:/admin/room/list";
    }

    @GetMapping("/admin/category/delete")
    public String deleteC(@RequestParam("id") int id){
        categoryRepository.deleteById(id);
        return "redirect:/admin/room/list";
    }

    @GetMapping("/admin/category/edit")
    public String editC(@RequestParam("id") int id, Model model){
        Category category = categoryRepository.findById(id).get();
        model.addAttribute("category",category);
        return "admin/category/edit";
    }

    @PostMapping("/admin/category/edit")
    public String editC(@ModelAttribute("category") Category category){
        categoryRepository.save(category);
        return "redirect:/admin/room/list";
    }
}
