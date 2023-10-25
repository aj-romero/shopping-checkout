package com.ajromero.webapp.persistence.repositories;

import com.ajromero.webapp.persistence.domain.CustomerAddresses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerAddresses extends JpaRepository<CustomerAddresses,Long> {
}
