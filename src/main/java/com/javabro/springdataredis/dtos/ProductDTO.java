package com.javabro.springdataredis.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash(value = "product")
public class ProductDTO implements Serializable {
    private Long id;
    private String name;
    private Integer quantity;
    private Double price;
}
