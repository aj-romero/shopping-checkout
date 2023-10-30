package com.ajromero.webapp.web.validation.checkout;

import com.ajromero.webapp.persistence.domain.Product;
import com.ajromero.webapp.persistence.repositories.IProducts;
import com.ajromero.webapp.web.dto.CheckoutBasicDto;
import com.ajromero.webapp.web.dto.CheckoutProductDto;
import com.ajromero.webapp.web.validation.IVerifyContent;

import java.util.Optional;
import java.util.Set;

public class AddProductChain implements ICheckoutChain<CheckoutProductDto> {

    private ICheckoutChain<CheckoutProductDto> validator;

    private IProducts products;
    private IVerifyContent verifyContent;

    public AddProductChain(IProducts products, IVerifyContent verifyContent) {
        this.products = products;
        this.verifyContent = verifyContent;
    }

    @Override
    public Boolean validate(CheckoutProductDto resource) {
        boolean result = true;

        Long id = resource.getIdProduct();
        Optional<Product> newProduct = products.findById(id);
        if (newProduct.isEmpty()) {
            verifyContent.verifyContent(true,
                    "Product with id " + id + " no found");
            result = false;

        }
        if (newProduct.get().getStock() < resource.getQuantity()) {
            verifyContent.verifyBadRequest(true,
                    "Product with id " + id + " hasn't that quantity");
            result = false;

        }
        if (resource.getPrice() < newProduct.get().getPrice()) {
            verifyContent.verifyBadRequest(true,
                    "Check the price for the product with id " + id);
            result = false;

        }


        if (this.validator != null) {
            return validator.validate(resource);
        }

        return result;
    }

    @Override
    public void nextValidate(ICheckoutChain<CheckoutProductDto> validator) {
        this.validator = validator;
    }
}
