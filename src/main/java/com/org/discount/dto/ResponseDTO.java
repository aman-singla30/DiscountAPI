package com.org.discount.dto;

public class ResponseDTO {

    private String userId;
    private Double retAmount;

    public String getUserId() {
        return userId;
    }

    public ResponseDTO setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public Double getRetAmount() {
        return retAmount;
    }

    public ResponseDTO setRetAmount(Double retAmount) {
        this.retAmount = retAmount;
        return this;
    }
}
