package com.example.demo2.service;

import com.example.demo2.entity.Customer;
import com.example.demo2.entity.Item;
import com.example.demo2.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public void deleteProduct(int id);
    public List<Product> list(Customer customer);

    List<Item> listItem(int id);
}
