package com.example.demo2.controller;

import com.example.demo2.entity.User;
import com.example.demo2.repository.UserRepository;
import com.example.demo2.service.Impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @Autowired
    UserRepository repoUser;
    @Autowired
    UserServiceImpl userService;

    @GetMapping("/login")
    public String getCkeck(){

        return "login";
    }

    @PostMapping ("/login")
    public String getCkeck(@Param("username") String username, @Param("password") String password, HttpServletRequest request){
        if(userService.checkPasswordUser(username,password)){
            request.getSession().setAttribute("account",repoUser.findUserByUsername(username));
            if(repoUser.findUserByUsername(username).getRole().getId()==2)
                return "redirect:/admin/trangchu";
            else{
                return "redirect:/user/trangchu";
            }
        }
        else
            return "redirect:/login";
    }

    @GetMapping("/user/trangchu")
    public String index(){
        return "user/index";
    }

    @GetMapping("/admin/trangchu")
    public String admin(){
        return "admin/index";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        session.invalidate();
        return "redirect:/login";
    }

}
