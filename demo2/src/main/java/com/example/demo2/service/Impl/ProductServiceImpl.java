package com.example.demo2.service.Impl;

import com.example.demo2.entity.Customer;
import com.example.demo2.entity.Item;
import com.example.demo2.entity.Product;
//import com.example.demo2.entity.Service;
import com.example.demo2.entity.Room;
import com.example.demo2.repository.CategoryRepository;
import com.example.demo2.repository.ProductRepository;
import com.example.demo2.repository.ServiceRepository;
import com.example.demo2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ServiceRepository serviceRepository;
    @Override
    public void deleteProduct(int id) {
        Product product = productRepository.findById(id).get();
        product.setDeleted(1);
        productRepository.save(product);
    }

    @Override
    public List<Product> list(Customer customer) {
        List<Product> result = new ArrayList<>();
        List<com.example.demo2.entity.Service> list = serviceRepository.findByCus(customer.getId());
        for(com.example.demo2.entity.Service i:list){
            Product p = productRepository.findById(i.getId().getPruductId()).get();
            result.add(p);
        }
        return result;
    }

    @Override
    public List<Item> listItem(int id) {
        List<Item> result = new ArrayList<>();
        List<com.example.demo2.entity.Service> list = serviceRepository.findByCus(id);
        for(com.example.demo2.entity.Service i:list){
            Item t = new Item();
            Product p = productRepository.findById(i.getId().getPruductId()).get();
            t.setP(p);
            t.setNum(i.getSoluong());
            result.add(t);
        }
        return result;
    }


}
