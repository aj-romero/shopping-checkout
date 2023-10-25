package com.ajromero.webapp.persistence.domain;

import com.ajromero.common.persistence.IEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "checkout__products")
@Getter
@Setter
@NoArgsConstructor
public class CheckoutProducts implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity",  nullable = false)
    private Integer quantity;

    @Column(name = "price",  nullable = false)
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product", nullable = false)
    private Products product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_checkout", nullable = false)
    private Checkouts checkout;
}
