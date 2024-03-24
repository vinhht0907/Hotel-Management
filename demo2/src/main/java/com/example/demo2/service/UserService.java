package com.example.demo2.service;

import java.util.List;

import com.example.demo2.entity.User;
import com.example.demo2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Boolean checkPasswordUser(String username,String password);
    User getUserbyUsername(String username);
    public List<User> ListAll();

}
