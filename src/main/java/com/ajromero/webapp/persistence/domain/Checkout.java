package com.ajromero.webapp.persistence.domain;

import com.ajromero.common.persistence.IEntity;
import com.ajromero.webapp.payment.IPaymentProcessor;
import com.ajromero.webapp.web.dto.CheckoutProductDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "checkouts")
@Getter
@Setter
@NoArgsConstructor
//@Builder
public class Checkout implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paymentCode;

    @Column(name = "ck_status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_shipping_address")
    private CustomerAddress shippingAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_payment")
    private CardPayment cardPayment;

    @OneToMany(mappedBy = "checkout", cascade = CascadeType.ALL)
    private Set<CheckoutProduct> products;



    @Transient
    private IPaymentProcessor paymentProcessor;

    public IPaymentProcessor getPaymentProcessor() {
        return paymentProcessor;
    }

    public void setPaymentProcessor(IPaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
        this.paymentCode = paymentProcessor.pay(this);
    }


    public double getTotalCheckout() {
        return this.products.stream().mapToDouble(CheckoutProduct::getTotal).sum();
    }

    public void addDetail(CheckoutProduct detail){
        if(this.products == null) {
            this.products = new TreeSet<>();
        }
        detail.setCheckout(this);
        products.add(detail);
    }

    public void subtractDetail(CheckoutProduct detail){
        if (this.products != null) {
            detail.setCheckout(this);
            products.remove(detail);
        }
    }

    public enum Status {
        OPEN,DONE;
    }

    @PrePersist
    protected void prePersist() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    @PreUpdate
    protected void preUpdate() {
        this.updatedAt = new Date();
    }

}
