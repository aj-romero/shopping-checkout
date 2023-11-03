package com.ajromero.webapp.service;

import com.ajromero.webapp.persistence.domain.*;
import com.ajromero.webapp.persistence.repositories.*;
import com.ajromero.webapp.web.dto.*;
import com.ajromero.webapp.web.mapper.CheckoutBasicMapper;
import com.ajromero.webapp.web.mapper.CheckoutInfoMapper;
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
    private final CheckoutInfoMapper ckInfoMapper;

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

    public CheckoutProduct addProduct(Long id, CheckoutProductDto productDto) {
        this.checkoutVerification(id);
        basicValidator.validateProduct(productDto);
        Checkout checkout = checkouts.findById(id).orElseThrow();
        verifyContent.verifyBadRequest(checkout.getStatus()
                .equals(Checkout.Status.DONE),"Checkout is not available for update");

        return ckProductMapper.toEntity(productDto);
    }

    public Checkout updateProductQuantity(Long id, CheckoutProductDto pdt) {
        this.checkoutVerification(id);

        Checkout checkout = checkouts.findById(id).orElseThrow();
        verifyContent.verifyBadRequest(checkout.getStatus()
                .equals(Checkout.Status.DONE),"Checkout is not available for update");
        boolean idExistsInCk = checkout.getProducts().stream().
                anyMatch(item -> item.getProduct().getId().equals(pdt.getIdProduct()));

        verifyContent.verifyContent(!idExistsInCk, "Product with id "+pdt.getIdProduct()+" no found in checkout");
        basicValidator.validate(ckMapper.toDto(checkout));

        CheckoutProduct ckproduct = checkout.getProducts().stream()
                .filter(detail -> detail.getProduct().getId().equals(pdt.getIdProduct()))
                .findFirst().orElseThrow();
        ckproduct.adjustQuatity(pdt.getQuantity());
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
        verifyContent.verifyBadRequest(checkout.getStatus()
                .equals(Checkout.Status.DONE),"Checkout is not available for update");
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

    public CheckoutWithShippingDto saveShippingAddress(Long id, ShippingDto resource) {
        this.checkoutVerification(id);
        verifyContent.verifyBadRequest(!addressesRepository.existsById(resource.getIdShipping()),
                id + " id URI not found in Customer Addresses");
        CustomerAddress address = addressesRepository.findById(resource.getIdShipping()).orElseThrow();

        Checkout checkout = checkouts.findById(id).orElseThrow();
        verifyContent.verifyBadRequest(checkout.getStatus()
                .equals(Checkout.Status.DONE),"Checkout is not available for update");
        verifyContent.verifyBadRequest(checkout.getShippingAddress() != null,
                "Address already added");
        checkout.setShippingAddress(address);

        return ckShippingMapper.toDto(checkouts.save(checkout));
    }

    public CheckoutWithShippingDto updateShippingAddress(Long id, ShippingDto resource) {
        this.checkoutVerification(id);
        verifyContent.verifyBadRequest(!id.equals(resource.getId()),
                "id and URI id don't match");
        verifyContent.verifyBadRequest(!addressesRepository.existsById(resource.getIdShipping()),
                id + " id URI not found in Customer Addresses");
        CustomerAddress address = addressesRepository.findById(resource.getIdShipping()).orElseThrow();

        Checkout checkout = checkouts.findById(id).orElseThrow();
        verifyContent.verifyBadRequest(checkout.getStatus()
                .equals(Checkout.Status.DONE),"Checkout is not available for update");
        checkout.setShippingAddress(address);
        return ckShippingMapper.toDto(checkout);
    }

    public String setPaymentMethod(Long id, CheckoutPaymentDto resource) {
        this.checkoutVerification(id);
        verifyContent.verifyBadRequest(!cardRepository.existsById(resource.getIdCard()),
                resource.getIdCard() + "not valid payment method");
        CardPayment payment = cardRepository.findById(resource.getIdCard()).orElseThrow();
        verifyContent.verifyBadRequest(!payment.getCustomer()
                .getId().equals(this.getUserId()),"Card id is not valid");
        Checkout checkout = checkouts.findById(id).orElseThrow();
        verifyContent.verifyBadRequest(checkout.getStatus()
                .equals(Checkout.Status.DONE),"Checkout is not available for update");
        verifyContent.verifyBadRequest(checkout.getCardPayment() !=null,
                "Payment method already added");

        checkout.setCardPayment(payment);
        return "Payment added success";
    }

    public String updatePaymentMethod(Long id, CheckoutPaymentDto card) {
        this.checkoutVerification(id);
        verifyContent.verifyBadRequest(!id.equals(card.getId()),
                "id and URI id don't match");
        verifyContent.verifyBadRequest(!cardRepository.existsById(card.getIdCard()),
                card.getId() + "not valid payment method");

        CardPayment payment = cardRepository.findById(card.getIdCard()).orElseThrow();
        verifyContent.verifyBadRequest(!payment.getCustomer()
                .getId().equals(this.getUserId()),"Card id is not valid");
        Checkout checkout = checkouts.findById(id).orElseThrow();
        verifyContent.verifyBadRequest(checkout.getStatus()
                .equals(Checkout.Status.DONE),"Checkout is not available for update");
        verifyContent.verifyBadRequest(checkout.getCardPayment() == null,
                "Payment method was not added to this checkout");
        checkout.setCardPayment(payment);
        return "Payment updated success";

    }

    private void checkoutVerification(Long id) {
        verifyContent.verifyBadRequest(!this.checkouts.existsById(id),id + " id URI not found in checkouts");
        verifyContent.verifyBadRequest(!this.checkouts.findById(id).
                orElseThrow().getCustomer().getId().equals(this.getUserId()), "user not allowed to this checkout"
        );
    }

    public CheckoutInfoDto getCheckoutInformation(Long id) {
        this.checkoutVerification(id);
        Checkout checkout = checkouts.findById(id).orElseThrow();
        return ckInfoMapper.toDto(checkout);
    }
}
