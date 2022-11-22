package com.rubypaper.shopping.biz.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@ToString(exclude = {"customer", "searchCustomerName", "searchOrderStatus"})
@Table(name = "S_ORDER")
@Entity
public class Order {

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Date orderDate;

    @Transient
    private String searchCustomerName;

    @Transient
    private OrderStatus searchOrderStatus;

    public Order(Customer customer) {
        setCustomer(customer);
        this.status = OrderStatus.ORDER;
        this.orderDate = new Date();
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        customer.getOrderList().add(this);
    }
}
