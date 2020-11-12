package com.org.discount.dto;

import com.org.discount.constant.ItemType;

import javax.validation.constraints.NotNull;

public class PurchaseItem {

    @NotNull(message = "Please add valid itemType")
    private ItemType itemType;
    @NotNull(message = "Please add amount")
    private Double amount;

    public ItemType getItemType() {
        return itemType;
    }

    public PurchaseItem setItemType(ItemType itemType) {
        this.itemType = itemType;
        return this;
    }

    public Double getAmount() {
        return amount;
    }

    public PurchaseItem setAmount(Double amount) {
        this.amount = amount;
        return this;
    }
}
