package com.ajromero.webapp.persistence.repositories;

import com.ajromero.webapp.persistence.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomers extends JpaRepository<Customer,String> {

    boolean existsByEmail(String email);
}
