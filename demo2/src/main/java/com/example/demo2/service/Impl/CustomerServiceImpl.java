package com.example.demo2.service.Impl;

import com.example.demo2.entity.Customer;
import com.example.demo2.entity.Room;
import com.example.demo2.entity.Status;
import com.example.demo2.repository.CategoryRepository;
import com.example.demo2.repository.CusRepository;
import com.example.demo2.repository.RoomRepository;
import com.example.demo2.repository.StatusRepository;
import com.example.demo2.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CusRepository cusRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    StatusRepository statusRepository;

    @Override
    public void saveCustomer(Customer customer) {
        Status status = statusRepository.findByName("Đã đặt");
        for(Room i:customer.getRoom()){
            i.setStatus(status);
        }
        cusRepository.save(customer);
    }
}
