package com.rubypaper.shopping.biz.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@ToString(exclude = {"order", "product"})
@Table(name = "S_ITEM")
@Entity
public class Item {

    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private int count;

    public Item(Product product, int count) {
        this.product = product;
        this.count = count;

        reduceStock(count);
    }

    public void reduceStock(int count) {
        product.reduceStock(count);
    }

    public void restoreStock() {
        product.restoreStock(count);
    }
}
