package com.example.homeWork.service;

import com.example.homeWork.model.Product;
import com.example.homeWork.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product save(Product product) {
        // сюда можно добавить бизнес-логику
        return repository.save(product);
    }

    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product update(Product product) {
        // валидация, проверка прав и т.п.
        return repository.update(product);
    }

    public void delete(Long id) {
        repository.delete(id);
    }
}