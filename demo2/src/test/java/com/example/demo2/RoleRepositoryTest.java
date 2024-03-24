package com.example.demo2;

import com.example.demo2.entity.Role;
import com.example.demo2.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RoleRepositoryTest {
    @Autowired
    RoleRepository repo;

    @Test
    public void testCreateRole(){
        Role user = new Role("User");
        Role admin = new Role("Admin");
        repo.saveAll(List.of(user,admin));

        List<Role> listRole = repo.findAll();

        assertThat(listRole.size()).isEqualTo(2);
    }
}
