package com.rubypaper.shopping.biz.repository;

import com.rubypaper.shopping.biz.domain.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProductRepository {

    @PersistenceContext
    private EntityManager em;

    // 상품 등록 또는 수정
    public void insertOrUpdateProduct(Product product) {
        // 매개변수로 전달된 상품에 아이디 존재 여부에 따라서 등록/수정이 분기 처리 됩니다.
        if (product.getId() == null) {
            em.persist(product);
        } else {
            em.merge(product);
        }
    }

    // 상품 상세 조회
    public Product getProduct(Long id) {
        return em.find(Product.class, id);
    }

    // 상품 목록 조회
    public List<Product> getProductList() {
        return em.createQuery("SELECT p FROM Product p ORDER BY p.id",
                Product.class).getResultList();
    }
}
