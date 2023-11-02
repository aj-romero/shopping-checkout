package com.ajromero.webapp.persistence.domain;

import com.ajromero.common.persistence.IEntity;
import com.ajromero.webapp.payment.CalcTotalEntity;
import com.ajromero.webapp.web.utils.ICalcTotal;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "checkout__products")
@Getter
@Setter
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



    @ManyToOne
    @JoinColumn(name = "id_checkout", nullable = false)
    private Checkout checkout;

    @Transient
    private ICalcTotal<CheckoutProduct> total;

    public CheckoutProduct() {
        total = new CalcTotalEntity();
    }

    public Double getTotal() {
        return this.total.calculateTotal(this);
    }

    @Override
    public int compareTo(CheckoutProduct o) {
        return this.getProduct().getId().compareTo(o.getProduct().getId());
    }

    public void adjustQuatity(int qty) {
        int newQuantity = this.quantity + qty;
        if (newQuantity >= 1) {
            this.quantity = newQuantity;
        }
    }

    @Override
    public String toString() {
        return "CheckoutProduct (" +
                "id=" + id +
                ", quantity=" + quantity +
                ", price=" + price +
                ')';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckoutProduct that = (CheckoutProduct) o;
        return this.getProduct().getId().equals(that.getProduct().getId());
    }

    @Override
    public int hashCode() {
        return this.getProduct().getId().hashCode() + 43;
    }
}
