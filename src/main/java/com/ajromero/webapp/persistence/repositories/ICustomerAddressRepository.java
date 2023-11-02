package com.ajromero.webapp.persistence.repositories;

import com.ajromero.webapp.persistence.domain.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerAddressRepository extends JpaRepository<CustomerAddress,Long> {
    //
}
