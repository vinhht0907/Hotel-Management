package com.example.demo2.controller;

import com.example.demo2.entity.Product;
import com.example.demo2.repository.ProductRepository;
import com.example.demo2.service.Impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/user/product/list")
    public String listPro(Model model){
        List<Product> list = productRepository.findProduct();
        model.addAttribute("pList",list);
        return "user/product/index";
    }

    @GetMapping("/user/product/create")
    public String create(){
        return "user/product/create";
    }

    @PostMapping("/user/product/create")
    public String create(@ModelAttribute Product product){
        productRepository.save(product);
        return "redirect:/user/product/list";
    }

    @GetMapping("/user/product/edit")
    public String edit(@RequestParam("id") int id,Model model){
        Product pro = productRepository.findById(id).get();
        model.addAttribute("product",pro);
        return "user/product/edit";
    }

    @PostMapping("/user/product/edit")
    public String edit(@ModelAttribute("product") Product product){
        productRepository.save(product);
        return "redirect:/user/product/list";
    }

    @GetMapping("/user/product/delete")
    public String delete(@RequestParam("id") int id){
        productService.deleteProduct(id);
        return "redirect:/user/product/list";
    }




    @GetMapping("/admin/product/list")
    public String listProd(Model model){
        List<Product> list = productRepository.findProduct();
        model.addAttribute("pList",list);
        return "admin/product/index";
    }

    @GetMapping("/admin/product/create")
    public String createp(){
        return "admin/product/create";
    }

    @PostMapping("/admin/product/create")
    public String createp(@ModelAttribute Product product){
        productRepository.save(product);
        return "redirect:/admin/product/list";
    }

    @GetMapping("/admin/product/edit")
    public String editp(@RequestParam("id") int id,Model model){
        Product pro = productRepository.findById(id).get();
        model.addAttribute("product",pro);
        return "admin/product/edit";
    }

    @PostMapping("/admin/product/edit")
    public String editp(@ModelAttribute("product") Product product){
        productRepository.save(product);
        return "redirect:/admin/product/list";
    }

    @GetMapping("/admin/product/delete")
    public String deletep(@RequestParam("id") int id){
        productService.deleteProduct(id);
        return "redirect:/admin/product/list";
    }
}
