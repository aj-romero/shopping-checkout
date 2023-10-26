package com.ajromero.webapp.service;

import com.ajromero.webapp.persistence.domain.Product;
import com.ajromero.webapp.persistence.repositories.IProducts;
import com.ajromero.webapp.web.exception.ResourceNotFoundException;
import com.ajromero.webapp.web.validation.IVerifyContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService implements IProductService{

    @Autowired
    private IProducts products;

    @Autowired
    IVerifyContent verifyContent;

    public ProductService(IProducts repository){
        products = repository;
    }

    @Override
    public List<Product> findAll() {
        List<Product> list = products.findAll();
        verifyContent.verifyContent(list.isEmpty(), "No products available");
        return list;
    }

}
