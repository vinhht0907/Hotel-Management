package com.example.demo2.controller;

import com.example.demo2.entity.*;
import com.example.demo2.repository.CusRepository;
import com.example.demo2.repository.ProductRepository;
import com.example.demo2.repository.RoomRepository;
import com.example.demo2.repository.ServiceRepository;
import com.example.demo2.service.Impl.CustomerServiceImpl;
import com.example.demo2.service.Impl.ProductServiceImpl;
import com.example.demo2.service.Impl.RoomServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    CusRepository cusRepository;
    @Autowired
    RoomServiceImpl roomService;
    @Autowired
    CustomerServiceImpl customerService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    ServiceRepository serviceRepository;

    @GetMapping("/user/book/create")
    public String create(Model model){
        List<Room> list = roomService.listRoom();
        model.addAttribute("rList",list);
        return "user/book/create";
    }

    @PostMapping ("/user/book/create")
    public String create(@ModelAttribute  Customer customer){
        customerService.saveCustomer(customer);
        return "redirect:/user/book/list";
    }

    @GetMapping("/user/book/list")
    public String list(Model model){
        List<Customer> list = cusRepository.listCus();
        model.addAttribute("cList",list);
        return "user/book/index";
    }

    @GetMapping("/user/book/edit")
    public String edit(@RequestParam("id") int id, Model model){
        Customer customer = cusRepository.findById(id).get();
        model.addAttribute("customer",customer);
        List<Room> list = roomService.list(customer);
        model.addAttribute("rList",list);
        List<Product> plist = productService.list(customer);
        model.addAttribute("pList",plist);
        return "user/book/edit";
    }

    @PostMapping("/user/book/edit")
    public String edit(@ModelAttribute Customer customer,HttpServletRequest request){
        //customerService.saveCustomer(customer);
        return "redirect:/user/book/list";
    }

    @GetMapping("/user/book/service")
    public String service(@RequestParam("id") int id,Model model){
        Customer customer = cusRepository.findById(id).get();
        model.addAttribute("customer",customer);
        List<Product> list = productRepository.findProduct();
        model.addAttribute("pList",list);
        return "user/book/service";
    }

    @PostMapping("/user/book/service")
    public String service(HttpServletRequest request,Model model){
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = cusRepository.findById(id).get();
        model.addAttribute("customer",customer);
        String[] num = request.getParameterValues("product_id");
        List<Item> plist = new ArrayList<>();
        for(int i=0;i<num.length;i++){
            int it = Integer.parseInt(num[i]);
            Product p = productRepository.findById(it).get();
            Item t = new Item(p,1);
            plist.add(t);
        }
        model.addAttribute("pList",plist);
        return "user/book/check";
    }

    @PostMapping("/user/book/save")
    public String saveSer(@RequestParam("id") int id,HttpServletRequest request,Model model){
        Customer customer = cusRepository.findById(id).get();
        model.addAttribute("customer",customer);
        String[] num = request.getParameterValues("num");
        String[] pro = request.getParameterValues("id_p");
        for(int i=0;i<pro.length;i++){
            int soluong = Integer.parseInt(num[i]);
            int id_p = Integer.parseInt(pro[i]);
            //Product p = productRepository.findById(id_p).get();
            ServiceKey s = new ServiceKey(id,id_p);
            Service ser = new Service(s,soluong);
            serviceRepository.save(ser);
        }
        return "redirect:/user/book/list";
    }





    @GetMapping("/admin/book/create")
    public String createc(Model model){
        List<Room> list = roomService.listRoom();
        model.addAttribute("rList",list);
        return "admin/book/create";
    }

    @PostMapping ("/admin/book/create")
    public String createc(@ModelAttribute  Customer customer){
        customerService.saveCustomer(customer);
        return "redirect:/admin/book/list";
    }

    @GetMapping("/admin/book/list")
    public String listc(Model model){
        List<Customer> list = cusRepository.listCus();
        model.addAttribute("cList",list);
        return "admin/book/index";
    }

    @GetMapping("/admin/book/edit")
    public String editc(@RequestParam("id") int id, Model model){
        Customer customer = cusRepository.findById(id).get();
        model.addAttribute("customer",customer);
        List<Room> list = roomService.list(customer);
        model.addAttribute("rList",list);
        List<Product> plist = productService.list(customer);
        model.addAttribute("pList",plist);
        return "admin/book/edit";
    }

    @PostMapping("/admin/book/edit")
    public String editc(@ModelAttribute Customer customer,HttpServletRequest request){
        //customerService.saveCustomer(customer);
        return "redirect:/admin/book/list";
    }

    @GetMapping("/admin/book/service")
    public String servicec(@RequestParam("id") int id,Model model){
        Customer customer = cusRepository.findById(id).get();
        model.addAttribute("customer",customer);
        List<Product> list = productRepository.findProduct();
        model.addAttribute("pList",list);
        return "admin/book/service";
    }

    @PostMapping("/admin/book/service")
    public String servicec(HttpServletRequest request,Model model){
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = cusRepository.findById(id).get();
        model.addAttribute("customer",customer);
        String[] num = request.getParameterValues("product_id");
        List<Item> plist = new ArrayList<>();
        for(int i=0;i<num.length;i++){
            int it = Integer.parseInt(num[i]);
            Product p = productRepository.findById(it).get();
            Item t = new Item(p,1);
            plist.add(t);
        }
        model.addAttribute("pList",plist);
        return "admin/book/check";
    }

    @PostMapping("/admin/book/save")
    public String saveSerc(@RequestParam("id") int id,HttpServletRequest request,Model model){
        Customer customer = cusRepository.findById(id).get();
        model.addAttribute("customer",customer);
        String[] num = request.getParameterValues("num");
        String[] pro = request.getParameterValues("id_p");
        for(int i=0;i<pro.length;i++){
            int soluong = Integer.parseInt(num[i]);
            int id_p = Integer.parseInt(pro[i]);
            //Product p = productRepository.findById(id_p).get();
            ServiceKey s = new ServiceKey(id,id_p);
            Service ser = new Service(s,soluong);
            serviceRepository.save(ser);
        }
        return "redirect:/admin/book/list";
    }
}
