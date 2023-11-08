package com.ajromero.webapp.persistence.repositories;

import com.ajromero.webapp.persistence.domain.Customer;
import com.ajromero.webapp.persistence.domain.CustomerAddress;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerAddressRepository extends JpaRepository<CustomerAddress,Long> {
    //
    List<CustomerAddress> findByCustomer(Customer resource);
}
