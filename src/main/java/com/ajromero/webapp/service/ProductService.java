package com.ajromero.webapp.service;

import com.ajromero.webapp.persistence.domain.Product;
import com.ajromero.webapp.persistence.repositories.IProductRepository;
import com.ajromero.webapp.service.interfaces.IProductService;
import com.ajromero.webapp.web.validation.IVerifyContent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Transactional (readOnly = true)
public class ProductService implements IProductService {
    private final IProductRepository products;

    private final IVerifyContent verifyContent;

    @Override
    public List<Product> findAll() {
        List<Product> list = products.findAll();
        verifyContent.verifyContent(list.isEmpty(), "No products available");
        return list;
    }

    @Override
    public Optional<Product> findById(Long id) {
        Optional<Product> product = products.findById(id);
        verifyContent.verifyContent(product.isEmpty(), "Product not found");
        return product;
    }

}
