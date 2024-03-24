package com.example.demo2.controller;

import com.example.demo2.entity.*;
import com.example.demo2.repository.CusRepository;
import com.example.demo2.repository.PayRepository;
import com.example.demo2.repository.RoomRepository;
import com.example.demo2.repository.StatusRepository;
import com.example.demo2.service.CustomerService;
import com.example.demo2.service.Impl.ProductServiceImpl;
import com.example.demo2.service.Impl.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
public class BillController {
    @Autowired
    CusRepository cusRepository;
    @Autowired
    CustomerService customerService;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    RoomServiceImpl roomService;
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    StatusRepository statusRepository;
    @Autowired
    PayRepository payRepository;

    @GetMapping("/user/bill/list")
    public String listBill(Model model){
        List<Customer>  list = cusRepository.findAll();
        model.addAttribute("bList",list);
        return "user/bill/index";
    }

    @GetMapping("/user/bill/detail")
    public String detailBill(@RequestParam("id") int id,Model model){
        Customer customer = cusRepository.findById(id).get();
        model.addAttribute("customer",customer);
        List<Room> rlist = roomService.list(customer);
        model.addAttribute("rList",rlist);
        List<Item> ilist = productService.listItem(id);
        model.addAttribute("iList",ilist);
        //int day = (int)ChronoUnit.DAYS.between(customer.getCheckin(),customer.getCheckout());
        int tmp=0,cmp=0,snp=0;
        for(Room i:rlist){
            tmp += i.getCategory().getPrice()*customer.getNumofday();
        }
        model.addAttribute("tmp",tmp);
        for(Item i:ilist){
            cmp += i.getP().getPrice()*i.getNum();
        }
        model.addAttribute("cmp",cmp);
        snp = tmp + cmp;
        model.addAttribute("snp",snp);
        return "user/bill/edit";
    }

    @GetMapping("/user/bill/pay")
    public String pay(@RequestParam("id") int id,@RequestParam("price") int price,@RequestParam("roomp") int room,
                      @RequestParam("servicep") int service){
        Customer customer = cusRepository.findById(id).get();
        customer.setStatus(1);
        Status s = statusRepository.findByName("Đang dọn dẹp");
        for(Room i:customer.getRoom()){
            i.setStatus(s);
            roomRepository.save(i);
        }
        cusRepository.save(customer);
        Pay pay = new Pay();
        pay.setCustomer(customer);
        pay.setStatus(price);
        pay.setRoom_revenue(room);
        pay.setService_revenue(service);
        payRepository.save(pay);
        return "redirect:/user/bill/list";
    }




    @GetMapping("/admin/bill/list")
    public String listBillp(Model model){
        List<Customer>  list = cusRepository.findAll();
        model.addAttribute("bList",list);
        return "admin/bill/index";
    }

    @GetMapping("/admin/bill/detail")
    public String detailBillp(@RequestParam("id") int id,Model model){
        Customer customer = cusRepository.findById(id).get();
        model.addAttribute("customer",customer);
        List<Room> rlist = roomService.list(customer);
        model.addAttribute("rList",rlist);
        List<Item> ilist = productService.listItem(id);
        model.addAttribute("iList",ilist);
        //int day = (int)ChronoUnit.DAYS.between(customer.getCheckin(),customer.getCheckout());
        int tmp=0,cmp=0,snp=0;
        for(Room i:rlist){
            tmp += i.getCategory().getPrice()*customer.getNumofday();
        }
        model.addAttribute("tmp",tmp);
        for(Item i:ilist){
            cmp += i.getP().getPrice()*i.getNum();
        }
        model.addAttribute("cmp",cmp);
        snp = tmp + cmp;
        model.addAttribute("snp",snp);
        return "admin/bill/edit";
    }

    @GetMapping("/admin/bill/pay")
    public String payp(@RequestParam("id") int id,@RequestParam("price") int price,@RequestParam("roomp") int room,
                       @RequestParam("servicep") int service){
        Customer customer = cusRepository.findById(id).get();
        customer.setStatus(1);
        Status s = statusRepository.findByName("Đang dọn dẹp");
        for(Room i:customer.getRoom()){
            i.setStatus(s);
            roomRepository.save(i);
        }
        cusRepository.save(customer);
        Pay pay = new Pay();
        pay.setCustomer(customer);
        pay.setStatus(price);
        pay.setRoom_revenue(room);
        pay.setService_revenue(service);
        payRepository.save(pay);
        return "redirect:/admin/bill/list";
    }
}
