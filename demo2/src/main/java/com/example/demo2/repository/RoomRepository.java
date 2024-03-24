package com.example.demo2.repository;

import com.example.demo2.entity.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Integer> {
    @Query("SELECT r FROM Room r WHERE r.deleted = 0")
    List<Room> findRoom();
    @Query("SELECT r FROM Room r WHERE r.name = ?1")
    Room findByName(String name);
}
