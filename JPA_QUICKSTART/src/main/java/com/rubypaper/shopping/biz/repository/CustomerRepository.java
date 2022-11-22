package com.rubypaper.shopping.biz.repository;

import com.rubypaper.shopping.biz.domain.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CustomerRepository {

    @PersistenceContext
    private EntityManager em;

    // 회원 등록
    public void insertCustomer(Customer customer) {
        em.persist(customer);
    }

    // 회원 상세 조회
    public Customer getCustomer(Long id) {
        return em.find(Customer.class, id);
    }

    // 회원 목록 검색
    public List<Customer> getCustomerList() {
        return em.createQuery("SELECT c FROM Customer c ORDER BY c.id",
            Customer.class).getResultList();
    }
}
