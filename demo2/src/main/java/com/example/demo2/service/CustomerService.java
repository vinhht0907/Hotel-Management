package com.example.demo2.service;

import com.example.demo2.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    public void saveCustomer(Customer customer);
}
