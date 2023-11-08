package com.ajromero.webapp.service;

import com.ajromero.webapp.payment.CardProcessor;
import com.ajromero.webapp.persistence.domain.*;
import com.ajromero.webapp.persistence.repositories.*;
import com.ajromero.webapp.web.dto.*;
import com.ajromero.webapp.web.mapper.*;
import com.ajromero.webapp.web.validation.IVerifyContent;
import com.ajromero.webapp.web.validation.checkout.CheckoutValidator;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Check;
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
    private final CardMapper cardMapper;

    private CheckoutValidator ckValidator;

    public Checkout basicRespCheckout(CheckoutBasicDto resource) {
        boolean valid = ckValidator.validate(resource);
        Customer customer;
        Checkout checkout = ckMapper.toEntity(resource);
        if (valid) {
            customer = customers.findById(this.getUserId()).orElseThrow();
            checkout.setCustomer(customer);
        }
        checkout.getProducts().forEach(product -> {product.setCheckout(checkout);});

        checkout.setStatus(Checkout.Status.OPEN);

        return checkout;
    }

    public CheckoutProduct addProduct(Long id, CheckoutProductDto productDto) {
        this.checkoutVerification(id);
        ckValidator.validateProduct(productDto);

        return ckProductMapper.toEntity(productDto);
    }

    public CheckoutBasicDto updateProductQuantity(Long id, CheckoutProductDto pdt) {
        Checkout checkout = this.checkoutVerification(id);

        boolean idExistsInCk = checkout.getProducts().stream().
                anyMatch(item -> item.getProduct().getId().equals(pdt.getIdProduct()));

        verifyContent.verifyContent(!idExistsInCk,
                "Product with id "+pdt.getIdProduct()+" no found in checkout");
        ckValidator.validate(ckMapper.toDto(checkout));

        CheckoutProduct ckproduct = checkout.getProducts().stream()
                .filter(detail -> detail.getProduct().getId().equals(pdt.getIdProduct()))
                .findFirst().orElseThrow();
        ckproduct.adjustQuatity(pdt.getQuantity());
        return this.toDto(checkout);

    }

    public CheckoutBasicDto toDto(Checkout resource) {
        return ckMapper.toDto(resource);
    }

    private String getUserId(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public Checkout removeCheckoutProduct(Long id, Long idProduct) {
        Checkout checkout = this.checkoutVerification(id);

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
        Checkout checkout = this.checkoutVerification(id);
        verifyContent.verifyBadRequest(!addressesRepository.existsById(resource.getIdShipping()),
                id + " id URI not found in Customer Addresses");
        CustomerAddress address = addressesRepository.findById(resource.getIdShipping()).orElseThrow();

        verifyContent.verifyBadRequest(checkout.getShippingAddress() != null,
                "Address already added");
        checkout.setShippingAddress(address);

        return ckShippingMapper.toDto(checkouts.save(checkout));
    }

    public CheckoutWithShippingDto updateShippingAddress(Long id, ShippingDto resource) {
        Checkout checkout = this.checkoutVerification(id);
        verifyContent.verifyBadRequest(!id.equals(resource.getId()),
                "id and URI id don't match");
        verifyContent.verifyBadRequest(!addressesRepository.existsById(resource.getIdShipping()),
                id + " id URI not found in Customer Addresses");
        CustomerAddress address = addressesRepository.findById(resource.getIdShipping()).orElseThrow();

        checkout.setShippingAddress(address);
        return ckShippingMapper.toDto(checkout);
    }

    public String setPaymentMethod(Long id, CheckoutPaymentDto resource) {
        Checkout checkout = this.checkoutVerification(id);
        verifyContent.verifyBadRequest(!cardRepository.existsById(resource.getIdCard()),
                resource.getIdCard() + "not valid payment method");
        CardPayment payment = cardRepository.findById(resource.getIdCard()).orElseThrow();
        verifyContent.verifyBadRequest(!payment.getCustomer()
                .getId().equals(this.getUserId()),"Card id is not valid");

        verifyContent.verifyBadRequest(checkout.getCardPayment() !=null,
                "Payment method already added");

        checkout.setCardPayment(payment);
        return "Payment added success";
    }

    public String updatePaymentMethod(Long id, CheckoutPaymentDto card) {
        Checkout checkout = this.checkoutVerification(id);
        verifyContent.verifyBadRequest(!id.equals(card.getId()),
                "id and URI id don't match");
        verifyContent.verifyBadRequest(!cardRepository.existsById(card.getIdCard()),
                card.getId() + "not valid payment method");

        CardPayment payment = cardRepository.findById(card.getIdCard()).orElseThrow();
        verifyContent.verifyBadRequest(!payment.getCustomer()
                .getId().equals(this.getUserId()),"Card id is not valid");
        verifyContent.verifyBadRequest(checkout.getCardPayment() == null,
                "Payment method was not added to this checkout");
        checkout.setCardPayment(payment);
        return "Payment updated success";

    }

    private Checkout checkoutVerification(Long id) {
        Checkout ck = this.checkouts.findById(id).orElseThrow();
        verifyContent.verifyBadRequest(ck == null,id + " id URI not found in checkouts");
        verifyContent.verifyBadRequest(!ck.getCustomer()
                .getId().equals(this.getUserId()), "user not allowed to this checkout"
        );

        verifyContent.verifyBadRequest(ck.getStatus()
                .equals(Checkout.Status.DONE),"Checkout is not available for update");
        return ck;
    }

    public CheckoutInfoDto getCheckoutInformation(Long id) {
        verifyContent.verifyBadRequest(!this.checkouts.existsById(id),id + " id URI not found in checkouts");
        Checkout checkout = checkouts.findById(id).orElseThrow();
        verifyContent.verifyBadRequest(!checkout.getCustomer()
                .getId().equals(this.getUserId()), "user not allowed to this checkout"
        );
        return ckInfoMapper.toDto(checkout);
    }

    public CheckoutInfoDto confirmOrder(Long id,CheckoutConfirmDto detail) {
        Checkout checkout = this.checkoutVerification(id);
        verifyContent.verifyBadRequest(!id.equals(detail.getId()),
                "id and URI id don't match");

        this.ckValidator.validateOrder(checkout);

        CCheckoutDto card = cardMapper.toDto(checkout.getCardPayment());
        card.setSecurityCode(detail.getSecurityCode());
        checkout.setPaymentProcessor(new CardProcessor(card));
        verifyContent.verifyBadRequest(checkout.getPaymentCode().isEmpty()
                ,"Card got issue from the bank");
        checkout.setStatus(Checkout.Status.DONE);
        return ckInfoMapper.toDto(checkout);

    }
}
