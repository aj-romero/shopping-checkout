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
public class CheckoutProduct implements IEntity, Comparable<CheckoutProduct> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity",  nullable = false)
    private Integer quantity;

    @Column(name = "price",  nullable = false)
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;

    @Override
    public String toString() {
        return "CheckoutProduct (" +
                "id=" + id +
                ", quantity=" + quantity +
                ", price=" + price +
                ')';
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_checkout", nullable = false)
    private Checkout checkout;

    @Override
    public int compareTo(CheckoutProduct o) {
        return this.getProduct().getId().compareTo(o.getProduct().getId());
    }
}
