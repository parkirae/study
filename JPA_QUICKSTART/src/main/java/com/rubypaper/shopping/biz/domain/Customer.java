package com.rubypaper.shopping.biz.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = "orderList")
@Table(name = "S_CUSTOMER")
@Entity
public class Customer {

    @Id @GeneratedValue
    @Column(name = "CUSTOMER_ID")
    private Long id;

    private String name;

    private String phone;

    private String comments;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Order> orderList = new ArrayList<Order>();
}
