package com.example.demo2.service;

import com.example.demo2.entity.Customer;
import com.example.demo2.entity.Room;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomService {
    public void deleteRoom(int id);
    public List<Room> listRoom();
    public List<Room> list(Customer customer);
}
