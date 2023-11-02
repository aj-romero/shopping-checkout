package com.ajromero.webapp.service;

import com.ajromero.webapp.persistence.domain.Checkout;
import com.ajromero.webapp.persistence.domain.CheckoutProduct;
import com.ajromero.webapp.persistence.repositories.ICheckoutRepository;
import com.ajromero.webapp.service.interfaces.ICheckoutService;
import com.ajromero.webapp.web.dto.CheckoutBasicDto;
import com.ajromero.webapp.web.dto.CheckoutProductDto;
import com.ajromero.webapp.web.dto.CheckoutShippingDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional (readOnly = true)
public class CheckoutService implements ICheckoutService {

   private CheckoutServiceFacade facade;
   private ICheckoutRepository checkouts;


    @Override
    @Transactional
    public CheckoutBasicDto saveCheckoutBasic(CheckoutBasicDto resource) {
        Checkout checkout = facade.basicRespCheckout(resource);
        //checkout.getProducts();
        Checkout result = checkouts.save(checkout);

        return facade.toDto(result);
    }

    @Override
    @Transactional
    public CheckoutBasicDto addProduct(Long id, CheckoutProductDto product) {
        CheckoutProduct newProduct = facade.addProduct(id, product,checkouts);
        Checkout checkout = checkouts.findById(id).orElseThrow();
        checkout.addDetail(newProduct);
        Checkout result = checkouts.save(checkout);
        return facade.toDto(result);
    }

    @Override
    public CheckoutBasicDto updateQuantityProduct(Long id, Long idProduct, Integer quantity) {
        Checkout updateCheckout = facade.updateProductQuantity(id, idProduct,quantity,checkouts);
        Checkout result = checkouts.save(updateCheckout);
        return facade.toDto(result);
    }

    @Override
    @Transactional
    public void deleteCheckoutProduct(Long id, Long idProduct) {
        Checkout checkout = facade.removeCheckoutProduct(id, idProduct, checkouts);

        if (checkout.getProducts().isEmpty() &&
                checkout.getStatus().equals(Checkout.Status.OPEN)) {
            checkouts.delete(checkout);
        }
    }

    @Override
    @Transactional
    public CheckoutShippingDto saveShippingAddress(Long id, Long idCustomerAddress) {
        return facade.saveShippingAddress(id,idCustomerAddress,checkouts);
    }

    @Override
    @Transactional
    public CheckoutShippingDto updateShippingAddress(Long id, Long idCustomerAddress) {
        return facade.updateShippingAddress(id,idCustomerAddress,checkouts);
    }


}
