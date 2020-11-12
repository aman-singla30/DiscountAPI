package com.org.discount.service;

import com.org.discount.dto.DiscountRequest;

public interface DiscountService {

    Double getAmountAfterApplyingDiscount(DiscountRequest discountRequest);
}
