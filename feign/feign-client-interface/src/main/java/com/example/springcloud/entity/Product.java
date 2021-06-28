package com.example.springcloud.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {

    private Long productId;

    private String description;

    private Long stock;

}
