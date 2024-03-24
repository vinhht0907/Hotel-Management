package com.example.demo2.repository;

import com.example.demo2.entity.Role;
import com.example.demo2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User,Integer> {
    //@Query("SELECT r FROM User r WHERE r.username = ?1")
    User findUserByUsername(String name);
}
