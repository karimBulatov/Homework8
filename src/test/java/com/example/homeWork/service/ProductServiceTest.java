package com.example.homeWork.service;

import com.example.homeWork.model.Product;
import com.example.homeWork.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService service;

    private Product sample;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sample = new Product("Test", 99.99);
        sample.setId(1L);
    }

    @Test
    void save_ShouldReturnSaved() {
        when(repository.save(sample)).thenReturn(sample);

        Product result = service.save(sample);

        assertEquals(sample, result);
        verify(repository, times(1)).save(sample);
    }

    @Test
    void findById_WhenExists() {
        when(repository.findById(1L)).thenReturn(Optional.of(sample));

        Optional<Product> result = service.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(sample, result.get());
        verify(repository).findById(1L);
    }

    @Test
    void findAll_ShouldReturnList() {
        when(repository.findAll()).thenReturn(List.of(sample));

        List<Product> list = service.findAll();

        assertEquals(1, list.size());
        verify(repository).findAll();
    }

    @Test
    void update_ShouldReturnUpdated() {
        sample.setPrice(199.99);
        when(repository.update(sample)).thenReturn(sample);

        Product updated = service.update(sample);

        assertEquals(199.99, updated.getPrice());
        verify(repository).update(sample);
    }

    @Test
    void delete_ShouldInvokeRepository() {
        doNothing().when(repository).delete(1L);

        service.delete(1L);

        verify(repository).delete(1L);
    }
}