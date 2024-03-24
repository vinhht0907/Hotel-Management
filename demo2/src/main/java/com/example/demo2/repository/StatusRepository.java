package com.example.demo2.repository;

import com.example.demo2.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StatusRepository extends JpaRepository<Status,Integer> {
    @Query("SELECT r FROM Status r WHERE r.name = ?1")
    public Status findByName(String name);
}
