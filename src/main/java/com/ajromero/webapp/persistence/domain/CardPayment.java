package com.ajromero.webapp.persistence.domain;

import com.ajromero.common.persistence.IEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "card__payments")
@Getter
@Setter
@NoArgsConstructor
public class CardPayment implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_customer", nullable = false)
    private Customer customer;

    @Column(name = "card_number", length = 50, nullable = false, unique = true)
    private String cardNumber;

    @Column(name = "card_holdername", length = 50, nullable = false)
    private String cardHoldername;

    @Column(name = "expiration_date", length = 5, nullable = false)
    private String expirationDate;


}
