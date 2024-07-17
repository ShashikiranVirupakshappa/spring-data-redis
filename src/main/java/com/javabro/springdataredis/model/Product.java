package com.javabro.springdataredis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash
@Builder
public class Product implements Serializable {
    @Id
    private Integer id;
    private String name;
    private Integer quantity;
    private Long price;
}
