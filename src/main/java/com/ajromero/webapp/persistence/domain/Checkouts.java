package com.ajromero.webapp.persistence.domain;

import com.ajromero.common.persistence.IEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "checkouts")
@Getter
@Setter
//@NoArgsConstructor
@Builder
public class Checkouts implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String payment;
    private String status;
    private Date createdAt;
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_customer")
    private Customers customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_address_billing")
    private CustomerAddresses addressBilling;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_payment")
    private PaymentMethods paymentMethod;

    @OneToMany(mappedBy = "checkout", cascade = CascadeType.ALL)
    private Set<CheckoutProducts> products;


}
