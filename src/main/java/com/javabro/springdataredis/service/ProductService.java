package com.javabro.springdataredis.service;

import com.javabro.springdataredis.model.Product;
import com.javabro.springdataredis.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.saveProduct(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }


    public Product getProductById(Integer id) {
        return productRepository.getProductById(id);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteProduct(id);
    }

    public Product updateProduct(Product product, Integer id) {
        return productRepository.updateProduct(product, id);
    }
}
