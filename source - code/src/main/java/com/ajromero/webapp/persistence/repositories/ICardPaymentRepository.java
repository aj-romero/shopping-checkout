package com.ajromero.webapp.persistence.repositories;

import com.ajromero.webapp.persistence.domain.CardPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICardPaymentRepository extends JpaRepository<CardPayment,Long> {
}
