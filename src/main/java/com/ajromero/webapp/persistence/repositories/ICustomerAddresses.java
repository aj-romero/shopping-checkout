package com.ajromero.webapp.persistence.repositories;

import com.ajromero.webapp.persistence.domain.Customer;
import com.ajromero.webapp.persistence.domain.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICustomerAddresses extends JpaRepository<CustomerAddress,Long> {


}
