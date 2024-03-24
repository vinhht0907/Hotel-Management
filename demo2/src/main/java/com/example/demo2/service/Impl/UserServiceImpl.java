package com.example.demo2.service.Impl;

import com.example.demo2.entity.Role;
import com.example.demo2.entity.User;
import com.example.demo2.repository.UserRepository;
import com.example.demo2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public Boolean checkPasswordUser(String username, String password) {
        if(username.equals("")||password.equals("")) return false;
        User user = userRepository.findUserByUsername(username);
        if(user == null) return false;
        if(user.getPassword().equals(password)) return true;
        return false;
    }

    @Override
    public User getUserbyUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        return user;
    }

    @Override
    public List<User> ListAll() {
        return userRepository.findAll();
    }
}
