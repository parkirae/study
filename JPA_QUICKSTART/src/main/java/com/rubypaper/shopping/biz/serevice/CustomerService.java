package com.rubypaper.shopping.biz.serevice;

import com.rubypaper.shopping.biz.domain.Customer;
import com.rubypaper.shopping.biz.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // 회원 등록
    public void insertCustomer(Customer customer) {
        customerRepository.insertCustomer(customer);
    }

    // 회원 상세 조회
    public Customer getCustomer(Long customerId) {
        return customerRepository.getCustomer(customerId);
    }

    // 회원 목록 검색
    public List<Customer> getCustomerList() {
        return customerRepository.getCustomerList();
    }
}
