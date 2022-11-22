package com.rubypaper.shopping.biz.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "S_CUSTOMER")
@Entity
public class Customer {

    @Id @GeneratedValue
    @Column(name = "CUSTOMER_ID")
    private Long id;

    private String name;

    private String phone;

    private String comments;

    private String city;

    private String roadName;

    private String zipCode;
}
