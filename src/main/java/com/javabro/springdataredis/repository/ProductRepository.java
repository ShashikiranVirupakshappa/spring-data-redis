package com.javabro.springdataredis.repository;

import com.javabro.springdataredis.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductRepository {
    public Product saveProduct(Product product);
    public List<Product> getAllProducts();
    public Product getProductById(Integer id);
    public void deleteProduct(Integer id);
    public Product updateProduct(Product product, Integer id);

}
