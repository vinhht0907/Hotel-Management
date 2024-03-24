package com.example.demo2.repository;

import com.example.demo2.entity.Service;
import com.example.demo2.entity.ServiceKey;
import com.example.demo2.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, ServiceKey> {
    @Query(value = "SELECT r FROM Service  r WHERE r.id.custemerId = ?1")
    public List<Service> findByCus(int id);
}
