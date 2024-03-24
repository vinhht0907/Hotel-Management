package com.example.demo2;

import com.example.demo2.entity.Role;
import com.example.demo2.entity.Status;
import com.example.demo2.repository.StatusRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class StatusRepositoryTest {
    @Autowired
    StatusRepository statusRepository;

    @Test
    public void testCreateStatus(){
        Status sa = new Status("Trống");
        Status sa1 = new Status("Đã đặt");
        Status sa2 = new Status("Có người");
        Status sa3 = new Status("Đang dọn dẹp");

        statusRepository.saveAll(List.of(sa,sa1,sa2,sa3));

        List<Status> listSta = statusRepository.findAll();

        assertThat(listSta.size()).isEqualTo(4);
    }
}
