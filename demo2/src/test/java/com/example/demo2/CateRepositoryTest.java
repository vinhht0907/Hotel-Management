package com.example.demo2;

import com.example.demo2.entity.Category;
import com.example.demo2.entity.Role;
import com.example.demo2.repository.CategoryRepository;
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
public class CateRepositoryTest {
    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void testCreateCate(){
        Category cate = new Category("Standart",1500000);
        Category cate1 = new Category("VIP",2500000);
        Category cate2 = new Category("Normal",700000);

        categoryRepository.saveAll(List.of(cate,cate1,cate2));

        List<Category> listCate = categoryRepository.findAll();

        assertThat(listCate.size()).isEqualTo(3);
    }
}
