package com.ajromero.webapp.persistence.repositories;

import com.ajromero.webapp.persistence.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProducts extends JpaRepository<Product,Long> {
}
