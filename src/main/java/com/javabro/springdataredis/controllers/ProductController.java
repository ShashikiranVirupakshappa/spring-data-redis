package com.javabro.springdataredis.controllers;

import com.javabro.springdataredis.dtos.ProductDTO;
import com.javabro.springdataredis.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/products")
@CacheConfig(cacheNames = "product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ProductDTO saveProduct(@RequestBody ProductDTO productDTO) {
        return productService.saveProduct(productDTO);
    }

    @GetMapping
    @Cacheable(cacheNames = "product", cacheManager = "productCacheManager")
    public List<ProductDTO> getAllProducts() {
        System.out.println("getAllProducts is called");
        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    @Cacheable(value = "product", key = "#id", cacheManager = "productCacheManager")
    public ProductDTO getProductById(@PathVariable Long id) throws Exception {
        System.out.println("getProductById is called ");
        return productService.getProductById(id);
    }

    @DeleteMapping("{id}")
    @CacheEvict(cacheNames = "product", key = "#id", cacheManager = "productCacheManager")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @PutMapping("{id}")
    @CachePut(cacheNames = "product", key = "#id", cacheManager = "productCacheManager")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO, @PathVariable Long id) {
        return productService.updateProduct(productDTO, id);
    }
}
