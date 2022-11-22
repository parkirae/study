package com.rubypaper.shopping.biz.repository;

import com.rubypaper.shopping.biz.domain.Customer;
import com.rubypaper.shopping.biz.domain.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {

    @PersistenceContext
    private EntityManager em;

    // 주문 등록
    public void insertOrder(Order order) {
        em.persist(order);
    }

    // 주문 상세 조회
    public Order getOrder(Long id) {
        return em.find(Order.class, id);
    }

    // 주문 목록 검색
    public List<Order> getOrderList(Order order) {
        // Criteria를 이용한 동적 쿼리
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = builder.createQuery(Order.class);

        // FROM ORDER ord
        Root<Order> ord = criteriaQuery.from(Order.class);

        List<Predicate> criteria = new ArrayList<Predicate>();

        if (order.getSearchCustomerName() != null) {
            // AND m.name LIKE %order.customerName%
            Predicate name = builder.like(ord.<Customer>get("customer").
                    <String>get("name"), "%" + order.getSearchCustomerName() + "%");
            criteria.add(name);
        }

        if (order.getSearchOrderStatus() != null) {
            // ord.status == order.searchOrderStatus
            Predicate status = builder.equal(ord.get("status"),
                    order.getSearchOrderStatus());
            criteria.add(status);
        }

        // Predicate 배열을 이용하여 동적 WHERE절 생성
        criteriaQuery.where(
                builder.and(criteria.toArray(new Predicate[criteria.size()])));

        // SELECT 전송
        TypedQuery<Order> query = em.createQuery(criteriaQuery).setMaxResults(1000);
        return query.getResultList();
    }
}
