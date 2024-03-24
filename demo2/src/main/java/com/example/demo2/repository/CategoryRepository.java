package com.example.demo2.repository;

import com.example.demo2.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    @Query("SELECT r FROM Category r WHERE r.name = ?1")
    public Category findByName(String name);

    @Query(value="SELECT c.name AS categoryName, SUM(c.price * b.numofday) AS totalRevenue " +
            "FROM Customer b JOIN  b.room r JOIN r.category c" +
            "      GROUP BY c.id")
    List<Object[]> doanhthu();
}
