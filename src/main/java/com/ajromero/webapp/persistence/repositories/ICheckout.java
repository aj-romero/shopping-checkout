package com.ajromero.webapp.persistence.repositories;

import com.ajromero.webapp.persistence.domain.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICheckout extends JpaRepository<Checkout,Long> {
}
