package com.ajromero.webapp.persistence.repositories;

import com.ajromero.webapp.persistence.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer,String> {

    boolean existsByEmail(String email);
}
