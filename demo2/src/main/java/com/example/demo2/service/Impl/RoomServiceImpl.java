package com.example.demo2.service.Impl;

import com.example.demo2.entity.Customer;
import com.example.demo2.entity.Room;
import com.example.demo2.repository.RoomRepository;
import com.example.demo2.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomRepository roomRepository;
    @Override
    public void deleteRoom(int id) {
        Room room = roomRepository.findById(id).get();
        room.setDeleted(1);
        roomRepository.save(room);
    }

    @Override
    public List<Room> listRoom() {
        List<Room> list = roomRepository.findRoom();
        List<Room> result = new ArrayList<>();
        for(Room i:list){
            if(i.getStatus().getId()==1)
                result.add(i);
        }
        return result;
    }

    @Override
    public List<Room> list(Customer customer) {
        List<Room> list = roomRepository.findRoom();
        List<Room> result = new ArrayList<>();
        for(Room i:list){
//            if(i.getStatus().getId()==1) {
//                result.add(i);
//            }
            for(Room j: customer.getRoom()){
                if(i.getId()==j.getId()){
                    result.add(i);
                }
            }
        }
        return result;
    }
}
