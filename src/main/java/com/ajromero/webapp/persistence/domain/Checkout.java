package com.ajromero.webapp.persistence.domain;

import com.ajromero.common.persistence.IEntity;
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

    private String payment;

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
    @JoinColumn(name = "id_address_billing")
    private CustomerAddress addressBilling;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_payment")
    private PaymentMethod paymentMethod;

    @OneToMany(mappedBy = "checkout", cascade = CascadeType.ALL)
    private Set<CheckoutProduct> products;

    @OneToOne(mappedBy = "checkout", cascade = CascadeType.ALL)
    private OrderShipping orderShipping;

    public void addDetail(CheckoutProduct detail){
        if(this.products == null) {
            this.products = new TreeSet<>();
        }
        detail.setCheckout(this);
        products.add(detail);
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
