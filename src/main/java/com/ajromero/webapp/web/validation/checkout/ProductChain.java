package com.ajromero.webapp.web.validation.checkout;

import com.ajromero.webapp.persistence.domain.Product;
import com.ajromero.webapp.persistence.repositories.IProductRepository;
import com.ajromero.webapp.web.dto.CheckoutBasicDto;
import com.ajromero.webapp.web.dto.CheckoutProductDto;
import com.ajromero.webapp.web.validation.IVerifyContent;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Component;


@Component
public class ProductChain implements ICheckoutChain<CheckoutBasicDto> {

    private ICheckoutChain<CheckoutBasicDto> validator;

    private IProductRepository products;
    private IVerifyContent verifyContent;

    public ProductChain(IProductRepository products, IVerifyContent verifyContent) {
        this.products = products;
        this.verifyContent = verifyContent;
    }

    @Override
    public Boolean validate(CheckoutBasicDto resource) {
        boolean result = true;
        Set<CheckoutProductDto> productsDto = resource.getProducts();
        for (CheckoutProductDto item : productsDto) {
            Long id = item.getIdProduct();
            Optional<Product> newProduct = products.findById(id);
            if (newProduct.isEmpty()) {
                verifyContent.verifyContent(true,
                        "Product with id " + id + " no found");
                result = false;
            } else {
                if (newProduct.get().getStock() < item.getQuantity()) {
                    verifyContent.verifyBadRequest(true,
                            "Product with id " + id + " hasn't that quantity");
                    result = false;
                }
                item.setPrice(newProduct.get().getPrice());
            }

        }
        if (validator != null) {
            return validator.validate(resource);
        }

        return result;
    }

    @Override
    public void nextValidate(ICheckoutChain<CheckoutBasicDto> validator) {
        this.validator = validator;
    }
}
