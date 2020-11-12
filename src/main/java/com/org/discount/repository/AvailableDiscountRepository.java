package com.org.discount.repository;

import com.org.discount.model.AvailableDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvailableDiscountRepository extends JpaRepository<AvailableDiscount, Long> {

    Optional<AvailableDiscount> findByDiscountType(String discountType);
}
