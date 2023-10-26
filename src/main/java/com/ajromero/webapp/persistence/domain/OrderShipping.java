package com.ajromero.webapp.persistence.domain;

import com.ajromero.common.persistence.IEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order__shipping")
@Getter
@Setter
@NoArgsConstructor
public class OrderShipping implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_address", nullable = false)
    private CustomerAddress address;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_checkout", nullable = false, referencedColumnName = "id")
    private Checkout checkout;


}
