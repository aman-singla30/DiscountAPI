package com.org.discount.exception;

import java.util.Arrays;
import java.util.List;

public class ApiError {

    private String status;
    private List<String> reason;

    //

    public ApiError() {
        super();
    }

    public ApiError(final String status, final List<String> reason) {
        super();
        this.status = status;
        this.reason = reason;
    }

    public ApiError(final String status,final String message) {
        super();
        this.status = status;
        reason = Arrays.asList(message);
    }

    public String getStatus() {
        return status;
    }

    public ApiError setStatus(String status) {
        this.status = status;
        return this;
    }

    public List<String> getReason() {
        return reason;
    }

    public ApiError setReason(List<String> reason) {
        this.reason = reason;
        return this;
    }
}
