package com.example.demo2;

import com.example.demo2.entity.Role;
import com.example.demo2.entity.User;
import com.example.demo2.repository.RoleRepository;
import com.example.demo2.repository.UserRepository;
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
public class UserRepositoryTest {
    @Autowired
    UserRepository repoUser;
    @Autowired
    RoleRepository repoRole;

    @Test
    public void testAddRoleToNewUser(){
        User user = new User();
        user.setName("Trần Vũ Minh Quý");
        user.setGender("Nam");
        user.setAddress("Hà Đông");
        user.setEmail("minhquy3107@gmail.com");
        user.setSdt("0342939269");
        user.setCccd("030201007931");
        user.setUsername("tranminh3107");
        user.setPassword("12345678");
        Role roleUser = repoRole.findByName("User");
        user.setRole(roleUser);
        User saveUser = repoUser.save(user);
        assertThat(saveUser.getRole()).toString();
    }
}
