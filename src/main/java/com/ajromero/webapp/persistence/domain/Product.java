package com.ajromero.webapp.persistence.domain;

import com.ajromero.common.persistence.IEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product implements IEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", length = 50, nullable = false, unique = true)
    private String code;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "stock",  nullable = false)
    private Integer stock;

    @Column(name = "price",  nullable = false)
    private Double price;

    public Product(String code, String name, Integer stock, Double price) {
        this.code = code;
        this.name = name;
        this.stock = stock;
        this.price = price;
    }

    public void adjustStock(int quantity) {
        int newQuantity = this.stock - quantity;
        if (newQuantity >= 0) {
            this.stock = newQuantity;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return this.getCode().equals(product.getCode());
    }

    @Override
    public int hashCode() {
        return this.getCode().hashCode() + 32;
    }

    @Override
    public String toString() {
        return "Product ("
                + "id=" + id
                + ", code='" + code + '\''
                + ", name='" + name + '\''
                + ", stock=" + stock
                + ", price=" + price
                + ')';
    }

}
