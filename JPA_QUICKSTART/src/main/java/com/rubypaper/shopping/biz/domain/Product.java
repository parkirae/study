package com.rubypaper.shopping.biz.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "S_PRODUCT")
@Entity
public class Product {

    @Id @GeneratedValue
    @Column(name = "PRODUCT_ID")
    private Long id;

    private String name;

    private int price;

    private int quantity;

    // 주문 정보 생성 시 재고 수량을 감소 시킵니다.
    public void reduceStock(int quantity) {
        this.quantity = this.quantity - quantity;

        if (quantity < 0 ) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }
    }

    // 주문 취소 시 재고 수량을 증가 시킵니다.
    public void restoreStock(int quantity) {
        this.quantity = this.quantity + quantity;
    }
}
