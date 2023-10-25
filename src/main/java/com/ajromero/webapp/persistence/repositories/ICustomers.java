package com.ajromero.webapp.persistence.repositories;

import com.ajromero.webapp.persistence.domain.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomers extends JpaRepository<Customers,String> {
}
