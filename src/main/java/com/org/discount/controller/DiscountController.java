package com.org.discount.controller;

import com.org.discount.dto.DiscountRequest;
import com.org.discount.dto.ResponseDTO;
import com.org.discount.service.DiscountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "DiscountController")
@RestController
@RequestMapping("/api/v1")
public class DiscountController {

    @Autowired
    private DiscountService service;

    @ApiOperation(value = "Get the discounted amount after applying the respective discount")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @PostMapping("/applyDiscount")
    public ResponseEntity<ResponseDTO> applyDiscount(@Valid @RequestBody DiscountRequest discountRequest) {
        Double amount = service.getAmountAfterApplyingDiscount(discountRequest);
        ResponseDTO responseDTO = new ResponseDTO().
                setRetAmount(amount).setUserId(discountRequest.getUserId());

        return new ResponseEntity<>(responseDTO,
                HttpStatus.OK);
    }
}
