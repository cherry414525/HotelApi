package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String id;
    private String name;
    private Address address;
    private String price;
    private String currency;

    // getters and setters
}