package com.example.demo2;

import com.example.demo2.entity.Category;
import com.example.demo2.entity.Room;
import com.example.demo2.entity.Status;
import com.example.demo2.repository.CategoryRepository;
import com.example.demo2.repository.RoomRepository;
import com.example.demo2.repository.StatusRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RoomRepositoryTest {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    StatusRepository statusRepository;

    @Test
    public void testCreateRoom(){
        Room room = new Room();
        room.setName("P204");
        Status status = statusRepository.findByName("Trống");
        room.setStatus(status);
        Category cate = categoryRepository.findByName("Normal");
        room.setCategory(cate);
        //room.setDeleted(0);
        Room saveRoom = roomRepository.save(room);
        assertThat(saveRoom.getCategory()).toString();
    }
}
