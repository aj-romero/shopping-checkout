package com.ajromero.webapp.persistence.domain;

import com.ajromero.common.persistence.IEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class Customer implements IEntity {

    @Id
    private String id;

    @Column(name = "first_name", length = 30)
    private String firstName;

    @Column(name = "last_name", length = 30)
    private String lastName;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "phone", length = 100)
    private String phone;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<CustomerAddress> addresses;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<CardPayment> cards;

    public Customer(String id, String firstName, String lastName, String email, String phone) {
        this();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public Customer() {
        this.addresses = new TreeSet<>();
        this.cards = new TreeSet<>();
    }

    public void addAddress(CustomerAddress address){
        address.setCustomer(this);
        this.addresses.add(address);
    }

    public void addCardPayment(CardPayment card) {
        card.setCustomer(this);
        this.cards.add(card);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return this.getId().equals(customer.getId());
    }

    @Override
    public int hashCode() {
        return this.getId().hashCode() + 57;
    }

    @Override
    public String toString() {
        return "Customer (" +
                "id='" + id + '\'' +
                ')';
    }
}
