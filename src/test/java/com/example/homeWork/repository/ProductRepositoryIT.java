package com.example.homeWork.repository;

import com.example.homeWork.DemoApplication;
import com.example.homeWork.model.Product;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = DemoApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ProductRepositoryIT {

    @Autowired
    private SessionFactory sessionFactory;

    private ProductRepository repository;

    @BeforeEach
    void setUp() {
        repository = new ProductRepository(sessionFactory);
    }

    @Test
    void saveAndFindById() {
        Product p = new Product("A", 10.0);
        repository.save(p);

        var found = repository.findById(p.getId());
        assertTrue(found.isPresent());
        assertEquals("A", found.get().getName());
    }

    @Test
    void findAllUpdateDelete() {
        Product p1 = new Product("A", 10.0);
        Product p2 = new Product("B", 20.0);
        repository.save(p1);
        repository.save(p2);

        List<Product> all = repository.findAll();
        assertEquals(2, all.size());

        p1.setPrice(15.0);
        repository.update(p1);
        assertEquals(15.0, repository.findById(p1.getId()).get().getPrice());

        repository.delete(p2.getId());
        assertTrue(repository.findById(p2.getId()).isEmpty());
    }
}