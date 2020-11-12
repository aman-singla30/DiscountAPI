package com.org.discount.dto;

import com.org.discount.constant.UserType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class DiscountRequest {

    @NotEmpty(message = "user id can not be null")
    private String userId;
    @NotNull(message = "Please add valid userType[EMPLOYEE,AFFILIATE,CUSTOMER]")
    private UserType userType;
    @NotNull(message = "Please give valid customerOldAge")
    private int customerOldAge;
    @NotNull(message = "Please add valid list of purchaseItems")
    private List<PurchaseItem> purchaseItems;

    public String getUserId() {
        return userId;
    }

    public DiscountRequest setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public UserType getUserType() {
        return userType;
    }

    public DiscountRequest setUserType(UserType userType) {
        this.userType = userType;
        return this;
    }

    public int getCustomerOldAge() {
        return customerOldAge;
    }

    public DiscountRequest setCustomerOldAge(int customerOldAge) {
        this.customerOldAge = customerOldAge;
        return this;
    }

    public List<PurchaseItem> getPurchaseItems() {
        return purchaseItems;
    }

    public DiscountRequest setPurchaseItems(List<PurchaseItem> purchaseItems) {
        this.purchaseItems = purchaseItems;
        return this;
    }
}
