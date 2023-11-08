package com.ajromero.webapp.persistence.repositories;

import com.ajromero.webapp.persistence.domain.CheckoutProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICheckoutProductRepository extends JpaRepository<CheckoutProduct, Long> {
}
