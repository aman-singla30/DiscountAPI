package com.org.discount.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.discount.constant.ItemType;
import com.org.discount.constant.UserType;
import com.org.discount.dto.DiscountRequest;
import com.org.discount.dto.PurchaseItem;
import com.org.discount.dto.ResponseDTO;
import com.org.discount.model.AvailableDiscount;
import com.org.discount.service.DiscountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.violations.ConstraintViolationProblemModule;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DiscountController.class)
class DiscountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DiscountServiceImpl discountService;

    @MockBean DiscountController controller;

    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();

    private AvailableDiscount empDiscount;

    private static final int aboveAmount = 100;
    private static final int discountAboveAmount = 5;

    @BeforeEach
    public void setup() {
        empDiscount = new AvailableDiscount().
                setDiscountType("EMPLOYEE").setDiscountPercent(30.00);
        objectMapper.registerModule(new ProblemModule());
        objectMapper.registerModule(new ConstraintViolationProblemModule());
    }

    @Test
    void testApplyDiscount() throws Exception {
        List<PurchaseItem> purchaseItems = new LinkedList<>();
        purchaseItems.add(new PurchaseItem().setItemType(ItemType.OTHERS).setAmount(520.00));
        purchaseItems.add(new PurchaseItem().setItemType(ItemType.OTHERS).setAmount(520.00));
        DiscountRequest request = new DiscountRequest()
                .setCustomerOldAge(1)
                .setUserId("Aman12345")
                .setUserType(UserType.EMPLOYEE)
                .setPurchaseItems(purchaseItems);
        discountService.aboveAmount = aboveAmount;
        discountService.discountAboveAmount = discountAboveAmount;
        ResponseDTO responseDTO = new ResponseDTO().setUserId("Aman12345").setRetAmount(834.0);
        given(discountService.getAmountAfterApplyingDiscount(request)).willReturn(834.0);

        this.mockMvc.perform(post("/api/v1/applyDiscount")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }


}
