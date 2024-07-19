package com.javabro.springdataredis.service;

import com.javabro.springdataredis.dtos.ProductDTO;
import com.javabro.springdataredis.model.Product;
import com.javabro.springdataredis.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepositoryNew;

    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product = productRepositoryNew.save(product);
        productDTO.setId(product.getId());
        return productDTO;
    }

    public List<ProductDTO> getAllProducts() {
        System.out.println("getAllProducts in service");
        return productRepositoryNew.findAll().stream().map(product -> {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setName(product.getName());
            productDTO.setPrice(product.getPrice());
            productDTO.setQuantity(product.getQuantity());
            productDTO.setId(product.getId());
            return productDTO;
        }).collect(Collectors.toList());
    }


    public ProductDTO getProductById(Long id) {
        System.out.println("getProductById in service");
        Product product = productRepositoryNew.findById(id).orElseThrow(() -> {
            try {
                throw new Exception("product not found in the catalog");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(product.getName());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setPrice(product.getPrice());
        productDTO.setId(product.getId());
        return productDTO;
    }

    public void deleteProduct(Long id) {
        productRepositoryNew.deleteById(id);
    }

    public ProductDTO updateProduct(ProductDTO productDTO, Long id) {
        Product product = new Product();
        product.setQuantity(productDTO.getQuantity());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setId(id);
        Product updatedProduct = productRepositoryNew.save(product);
        productDTO.setId(updatedProduct.getId());
        return productDTO;
    }
}