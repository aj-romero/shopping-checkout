package com.ajromero.webapp.web.validation.checkout;

import com.ajromero.webapp.persistence.domain.Product;
import com.ajromero.webapp.persistence.repositories.IProducts;
import com.ajromero.webapp.web.dto.CheckoutBasicDto;
import com.ajromero.webapp.web.dto.CheckoutProductDto;
import com.ajromero.webapp.web.validation.IVerifyContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

@Component
public class ProductChain implements ICheckoutChain<CheckoutBasicDto>{

    private ICheckoutChain<CheckoutBasicDto> validator;

    private IProducts products;
    private IVerifyContent verifyContent;

    public ProductChain(IProducts products, IVerifyContent verifyContent) {
        this.products = products;
        this.verifyContent = verifyContent;
    }

    @Override
    public Boolean validate(CheckoutBasicDto resource) {
        boolean result = true;
        Set<CheckoutProductDto> productsDto = resource.getProducts();
        for (CheckoutProductDto item: productsDto) {
            Long id = item.getIdProduct();
            Optional<Product> newProduct = products.findById(id);
          if(newProduct.isEmpty()) {
              verifyContent.verifyContent(true,
                      "Product with id " + id+ " no found");
              result = false;
              //break;
          }
          if(newProduct.get().getStock() > item.getQuantity()) {
              verifyContent.verifyBadRequest(true,
                      "Product with id "+ id +" hasn't that quantity");
              result = false;
              //break;
          }
          if(item.getPrice() >= newProduct.get().getPrice()) {
                verifyContent.verifyBadRequest(true,
                        "Check the price for the product with id " + id);
                result = false;
                //break;
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
