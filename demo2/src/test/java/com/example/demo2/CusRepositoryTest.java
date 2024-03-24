package com.example.demo2;

import com.example.demo2.entity.Customer;
import com.example.demo2.entity.Room;
import com.example.demo2.repository.CusRepository;
import com.example.demo2.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CusRepositoryTest {
    @Autowired
    CusRepository cusRepository;
    @Autowired
    RoomRepository roomRepository;

//    @Test
//    public void testAddRoomToNewCus(){
//        Customer cus = new Customer();
//        cus.setName("Bùi Anh Hào");
//        cus.setSdt("0934235277");
//        cus.setCccd("030201007930");
//        cus.setNumofpeople(3);
//        Date date = new Date("2023/06/13 00:00:00");
//        cus.setCheckin(date);
//        Date date1 = new Date("2023/06/15 00:00:00");
//        cus.setCheckout(date1);
//        Room room = roomRepository.findByName("P201");
//        Room room1 = roomRepository.findByName("P202");
//        cus.addRoom(room);
//        cus.addRoom(room1);
//
//        Customer saveCus = cusRepository.save(cus);
//
//        assertThat(saveCus.getRoom().size()).isEqualTo(2);
//    }
}
