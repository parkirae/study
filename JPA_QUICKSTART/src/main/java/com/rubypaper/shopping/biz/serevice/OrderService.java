package com.rubypaper.shopping.biz.serevice;

import com.rubypaper.shopping.biz.domain.*;
import com.rubypaper.shopping.biz.repository.CustomerRepository;
import com.rubypaper.shopping.biz.repository.OrderRepository;
import com.rubypaper.shopping.biz.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class OrderService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    // 주문 등록
    public void insertOrder(Long customerId, Long productId, int count) {
        Customer customer = customerRepository.getCustomer(customerId);
        Product product = productRepository.getProduct(productId);

        Item item = new Item(product, count);
        Order order = new Order(customer, item);

        orderRepository.insertOrder(order);
    }

    // 주문 취소
    public void orderCancel(Long orderId) {
        Order order = orderRepository.getOrder(orderId);
        order.orderCancel();
    }

    // 주문 목록 검색
    public List<Order> getOrderList(Order order) {
        return orderRepository.getOrderList(order);
    }
}
