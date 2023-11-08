package com.ajromero.webapp.persistence.domain;

import com.ajromero.common.persistence.IEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer__addresses")
@Getter
@Setter
@NoArgsConstructor
public class CustomerAddress implements IEntity,Comparable<CustomerAddress> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address_name", length = 50, nullable = false)
    private String addressName;

    @Column(name = "state", length = 50)
    private String state;

    @Column(name = "city", length = 50)
    private String city;

    @Column(name = "street", length = 100)
    private String street;

    @Column(name = "reference_phone", length = 30)
    private String referencePhone;

    @Column(name = "house_number", length = 10)
    private String houseNumber;

    @Column(name = "reference_name", length = 50)
    private String referenceName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_customer", nullable = false)
    private Customer customer;

    @Override
    public int compareTo(CustomerAddress o) {
        return this.getAddressName().compareTo(o.getAddressName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomerAddress that = (CustomerAddress) o;
        return this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return this.getId().hashCode() + 33;
    }

    @Override
    public String toString() {
        return "CustomerAddress ("
                + "id=" + id
                + ", addressName='" + addressName + '\''
                + ')';
    }
}
