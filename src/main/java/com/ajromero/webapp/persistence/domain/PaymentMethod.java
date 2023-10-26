package com.ajromero.webapp.persistence.domain;

import com.ajromero.common.persistence.IEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payment__methods")
@Getter
@Setter
@NoArgsConstructor
public class PaymentMethod implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "method_name", length = 50, nullable = false, unique = true)
    private String methodName;

    public PaymentMethod(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentMethod that = (PaymentMethod) o;
        return this.getMethodName().equals(that.getMethodName());
    }

    @Override
    public int hashCode() {
        return this.getMethodName().hashCode() + 47;
    }

    @Override
    public String toString() {
        return "PaymentMethod (" +
                "id=" + id +
                ", methodName='" + methodName + '\'' +
                ')';
    }
}
