package com.javabro.springdataredis.repository;

import com.javabro.springdataredis.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;


@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


    @Override
    public Product saveProduct(Product product) {
        redisTemplate.opsForHash().put("Product", product.getId(), product);
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return redisTemplate.opsForHash().values("Product").stream().map(o -> (Product)o).collect(Collectors.toList());
    }

    @Override
    public Product getProductById(Integer id) {
        return (Product)redisTemplate.opsForHash().get("Product", id);
    }

    @Override
    public void deleteProduct(Integer id) {
        redisTemplate.opsForHash().delete("Product", id);
    }

    @Override
    public Product updateProduct(Product product, Integer id) {
        redisTemplate.opsForHash().put("Product", product.getId(), product);
        return product;
    }
}
