package com.example.demo2;

import com.example.demo2.entity.Category;
import com.example.demo2.entity.Product;
import com.example.demo2.repository.ProductRepository;
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
public class ProRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    public void testCreatePro(){
        Product pro = new Product("Giặt là",70000,"");
        Product pro1 = new Product("Thuê xe đưa đón",200000,"");
        Product pro2 = new Product("Karaoke",300000,"");
        Product pro3 = new Product("Bể bơi 4 mùa", 150000,"");

        productRepository.saveAll(List.of(pro,pro1,pro2,pro3));

        List<Product> listPro = productRepository.findAll();

        assertThat(listPro.size()).isEqualTo(4);
    }
}
