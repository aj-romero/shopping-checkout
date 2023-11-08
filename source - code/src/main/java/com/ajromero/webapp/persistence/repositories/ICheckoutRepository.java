package com.ajromero.webapp.persistence.repositories;

import com.ajromero.webapp.persistence.domain.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICheckoutRepository extends JpaRepository<Checkout,Long> {
}
