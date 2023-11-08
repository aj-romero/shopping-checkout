package com.ajromero.webapp.web.validation.checkout;

import com.ajromero.webapp.persistence.domain.Product;
import com.ajromero.webapp.persistence.repositories.IProductRepository;
import com.ajromero.webapp.web.dto.CheckoutProductDto;
import com.ajromero.webapp.web.validation.IVerifyContent;
import java.util.Optional;

public class AddProductChain implements ICheckoutChain<CheckoutProductDto> {

    private ICheckoutChain<CheckoutProductDto> validator;

    private IProductRepository products;
    private IVerifyContent verifyContent;

    public AddProductChain(IProductRepository products, IVerifyContent verifyContent) {
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

        } else {
            if ((newProduct.get().getStock() < resource.getQuantity())) {
                verifyContent.verifyBadRequest(true,
                        "Product with id " + id + " hasn't that quantity");
                result = false;

            }
            resource.setPrice(newProduct.get().getPrice());
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
