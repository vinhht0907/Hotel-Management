package com.example.demo2.controller;

import com.example.demo2.entity.Category;
import com.example.demo2.entity.Room;
import com.example.demo2.entity.Status;
import com.example.demo2.repository.CategoryRepository;
import com.example.demo2.repository.RoomRepository;
import com.example.demo2.repository.StatusRepository;
import com.example.demo2.service.Impl.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("/user/room")
public class RoomController {
    @Autowired
    RoomServiceImpl roomService;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    StatusRepository statusRepository;

    @GetMapping("/user/room/list")
    public String ListRoom(Model model){
        List<Room> rooms = roomRepository.findRoom();
        model.addAttribute("rList",rooms);
        List<Category> cates = categoryRepository.findAll();
        model.addAttribute("cList",cates);
        return "user/room/index";
    }

    @GetMapping("/user/room/delete")
    public String delete(@RequestParam("id") int id){
        roomService.deleteRoom(id);
        return "redirect:/user/room/list";
    }

    @GetMapping("/user/room/create")
    public String create(Model model){
        List<Category> cates = categoryRepository.findAll();
        model.addAttribute("cList",cates);
        List<Status> statuss = statusRepository.findAll();
        model.addAttribute("sList",statuss);
        return "user/room/create";
    }

    @PostMapping("/user/room/create")
    public String create(@ModelAttribute Room room){
        roomRepository.save(room);
        return "redirect:/user/room/list";
    }

    @GetMapping("/user/room/edit")
    public String edit(@RequestParam("id") int id, Model model){
        Room room = roomRepository.findById(id).get();
        model.addAttribute("room",room);
        List<Category> category = categoryRepository.findAll();
        model.addAttribute("cList", category);
        List<Status> statuss = statusRepository.findAll();
        model.addAttribute("sList",statuss);
        return "user/room/edit";
    }

    @PostMapping("/user/room/edit")
    public String edit(@ModelAttribute("room") Room room){
        roomRepository.save(room);
        return "redirect:/user/room/list";
    }



    @GetMapping("/admin/room/list")
    public String ListR(Model model){
        List<Room> rooms = roomRepository.findRoom();
        model.addAttribute("rList",rooms);
        List<Category> cates = categoryRepository.findAll();
        model.addAttribute("cList",cates);
        return "admin/room/index";
    }

    @GetMapping("/admin/room/delete")
    public String deleteR(@RequestParam("id") int id){
        roomService.deleteRoom(id);
        return "redirect:/admin/room/list";
    }

    @GetMapping("/admin/room/create")
    public String createR(Model model){
        List<Category> cates = categoryRepository.findAll();
        model.addAttribute("cList",cates);
        List<Status> statuss = statusRepository.findAll();
        model.addAttribute("sList",statuss);
        return "admin/room/create";
    }

    @PostMapping("/admin/room/create")
    public String createR(@ModelAttribute Room room){
        roomRepository.save(room);
        return "redirect:/admin/room/list";
    }

    @GetMapping("/admin/room/edit")
    public String editR(@RequestParam("id") int id, Model model){
        Room room = roomRepository.findById(id).get();
        model.addAttribute("room",room);
        List<Category> category = categoryRepository.findAll();
        model.addAttribute("cList", category);
        List<Status> statuss = statusRepository.findAll();
        model.addAttribute("sList",statuss);
        return "admin/room/edit";
    }

    @PostMapping("/admin/room/edit")
    public String editR(@ModelAttribute("room") Room room){
        roomRepository.save(room);
        return "redirect:/admin/room/list";
    }
}
