package com.ajromero.webapp.service;

import com.ajromero.webapp.persistence.domain.*;
import com.ajromero.webapp.persistence.repositories.*;
import com.ajromero.webapp.web.dto.CheckoutBasicDto;
import com.ajromero.webapp.web.dto.CheckoutProductDto;
import com.ajromero.webapp.web.dto.CheckoutShippingDto;
import com.ajromero.webapp.web.mapper.CheckoutBasicMapper;
import com.ajromero.webapp.web.mapper.CheckoutProductMapper;
import com.ajromero.webapp.web.mapper.CheckoutShippingMapper;
import com.ajromero.webapp.web.validation.IVerifyContent;
import com.ajromero.webapp.web.validation.checkout.CheckoutValidator;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CheckoutServiceFacade {

    private final ICustomerRepository customers;
    private final CheckoutBasicMapper ckMapper;
    private final CheckoutShippingMapper ckShippingMapper;
    private final IProductRepository products;
    private final IVerifyContent verifyContent;
    private final CheckoutProductMapper ckProductMapper;
    private final ICheckoutProductRepository ckProductRepository;
    private final ICustomerAddressRepository addressesRepository;
    private final ICardPaymentRepository cardRepository;
    private final ICheckoutRepository checkouts;

    private CheckoutValidator basicValidator;

    public Checkout basicRespCheckout(CheckoutBasicDto resource) {
        boolean valid = basicValidator.validate(resource);
        Customer customer;
        Checkout checkout = ckMapper.toEntity(resource);
        if (valid) {
            customer = customers.findById(this.getUserId()).orElseThrow();
            checkout.setCustomer(customer);
        }
        checkout.getProducts().stream().forEach(product -> {product.setCheckout(checkout);});

        checkout.setStatus(Checkout.Status.OPEN);

        return checkout;
    }
    //falta validar que el checkout pertenezca al usuario que esta logeado
    public CheckoutProduct addProduct(Long id, CheckoutProductDto productDto) {
        this.checkoutVerification(id);/*
        verifyContent.verifyBadRequest(!checkouts.existsById(id),id + " id URI not found in checkouts");
        verifyContent.verifyBadRequest(checkouts.findById(id).
                orElseThrow().getCustomer().getId() != this.getUserId(), "Can not add product to this checkout"
        );*/
        basicValidator.validateProduct(productDto);
        /*CheckoutProduct newProduct = ckProductMapper.toEntity(productDto);

        if (valid) {
            newProduct = ckProductMapper.toEntity(productDto);
        }*/

        return ckProductMapper.toEntity(productDto);
    }

    public Checkout updateProductQuantity(Long id, Long idProduct, Integer qty, ICheckoutRepository checkouts) {
        verifyContent.verifyBadRequest(!checkouts.existsById(id),id + " id URI not found in checkouts");
        Checkout checkout = checkouts.findById(id).orElseThrow();
        boolean idExistsInCk = checkout.getProducts().stream().
                anyMatch(item -> item.getProduct().getId().equals(idProduct));
        verifyContent.verifyContent(!idExistsInCk, "Product with id "+idProduct+" no found in checkout");
        basicValidator.validate(ckMapper.toDto(checkout));
        CheckoutProduct ckproduct = checkout.getProducts().stream()
                .filter(detail -> detail.getProduct().getId().equals(idProduct))
                .findFirst().orElseThrow();
        ckproduct.adjustQuatity(qty); //previo a esto se tendria que validar que si la nueva cantidad es menor que zero elimnar el checkout
        checkout.addDetail(ckproduct);
        return checkout;

    }

    public CheckoutBasicDto toDto(Checkout resource) {
        return ckMapper.toDto(resource);
    }



    private String getUserId(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public Checkout removeCheckoutProduct(Long id, Long idProduct) {
        this.checkoutVerification(id);
        Checkout checkout = checkouts.findById(id).orElseThrow();
        boolean idExistsInCk = checkout.getProducts().stream().
                anyMatch(item -> item.getProduct().getId().equals(idProduct));
        verifyContent.verifyContent(!idExistsInCk, "Product with id "+idProduct+" no found in checkout");

        CheckoutProduct rmProduct = checkout.getProducts().stream()
                .filter(detail -> detail.getProduct().getId().equals(idProduct))
                .findFirst().orElseThrow();

        checkout.subtractDetail(rmProduct);
        ckProductRepository.delete(rmProduct);

        return checkout;
    }

    public CheckoutShippingDto saveShippingAddress(Long id, Long idAddress, ICheckoutRepository checkouts) {
        this.checkoutVerification(id);
        verifyContent.verifyBadRequest(!addressesRepository.existsById(idAddress),
                id + " id URI not found in Customer Addresses");
        CustomerAddress address = addressesRepository.findById(idAddress).orElseThrow();

        Checkout checkout = checkouts.findById(id).orElseThrow();
        verifyContent.verifyBadRequest(checkout.getShippingAddress() != null,
                "Address already added");
        checkout.setShippingAddress(address);

        return ckShippingMapper.toDto(checkouts.save(checkout));
    }

    public CheckoutShippingDto updateShippingAddress(Long id, Long idAddress, ICheckoutRepository checkouts) {
        verifyContent.verifyBadRequest(!checkouts.existsById(id),id + " id URI not found in checkouts");
        verifyContent.verifyBadRequest(!addressesRepository.existsById(idAddress),
                id + " id URI not found in Customer Addresses");
        CustomerAddress address = addressesRepository.findById(idAddress).orElseThrow();

        Checkout checkout = checkouts.findById(id).orElseThrow();
        checkout.setShippingAddress(address);
        /*verifyContent.verifyBadRequest(checkout.getOrderShipping() == null,
                " save an address first");
        OrderShipping orderShipping = checkout.getOrderShipping();
        orderShipping.setAddress(address);*/
        //orderShipping.setCheckout(checkout);

        //checkout.setOrderShipping(orderShipping);

       // return ckShippingMapper.toDto(checkouts.save(checkout));
        return ckShippingMapper.toDto(checkout);
    }

    public String setPaymentMethod(Long id, Long idCustomerCard) {
        this.checkoutVerification(id);
        verifyContent.verifyBadRequest(!cardRepository.existsById(idCustomerCard),
                idCustomerCard + "not valid payment method");
        CardPayment payment = cardRepository.findById(idCustomerCard).orElseThrow();
        Checkout checkout = checkouts.findById(id).orElseThrow();

        verifyContent.verifyBadRequest(checkout.getCardPayment() !=null,
                "Payment method already added");

        checkout.setCardPayment(payment);
        return "Payment added success";
    }

    private void checkoutVerification(Long id) {
        verifyContent.verifyBadRequest(!this.checkouts.existsById(id),id + " id URI not found in checkouts");
        verifyContent.verifyBadRequest(!this.checkouts.findById(id).
                orElseThrow().getCustomer().getId().equals(this.getUserId()), "user not allowed to this checkout"
        );
    }
}
