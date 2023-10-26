package com.ajromero.webapp.service;

import com.ajromero.webapp.persistence.domain.Product;

import java.util.List;

public interface IProductService {

    List<Product> findAll();
}
