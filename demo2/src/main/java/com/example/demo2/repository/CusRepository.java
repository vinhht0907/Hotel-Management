package com.example.demo2.repository;

import com.example.demo2.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CusRepository extends JpaRepository<Customer,Integer> {
    @Query("SELECT r FROM Customer r WHERE r.status = 0")
    List<Customer> listCus();

    @Query(value = "SELECT MONTH(b.customer.checkin) AS month, " +
            "SUM(b.room_revenue) + SUM(b.service_revenue) AS totalRevenue " +
            "FROM Pay b " +
            "WHERE b.customer.checkin >= :startDate AND b.customer.checkin <= :endDate " +
            "GROUP BY YEAR(b.customer.checkin), MONTH(b.customer.checkin) " +
            "ORDER BY YEAR(b.customer.checkin), MONTH(b.customer.checkin)")
    List<Object[]> doanhthu(LocalDate startDate, LocalDate endDate);
}
