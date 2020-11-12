package com.org.discount.model;

import javax.persistence.*;

@Entity
@Table(name = "availableDiscount")
public class AvailableDiscount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String discountType;

    @Column
    private Double discountPercent;

    public String getDiscountType() {
        return discountType;
    }

    public AvailableDiscount setDiscountType(String discountType) {
        this.discountType = discountType;
        return this;
    }

    public Double getDiscountPercent() {
        return discountPercent;
    }

    public AvailableDiscount setDiscountPercent(Double discountPercent) {
        this.discountPercent = discountPercent;
        return this;
    }

    public Long getId() {
        return id;
    }

    public AvailableDiscount setId(Long id) {
        this.id = id;
        return this;
    }
}
