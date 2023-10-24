package com.ajromero.webapp.persistence.repositories;

import com.ajromero.webapp.persistence.domain.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProducts extends JpaRepository<Products,Long> {
}
