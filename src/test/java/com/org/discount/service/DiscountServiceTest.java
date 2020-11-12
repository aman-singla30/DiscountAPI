package com.org.discount.service;

import com.org.discount.constant.DiscountCondition;
import com.org.discount.constant.ItemType;
import com.org.discount.constant.UserType;
import com.org.discount.dto.DiscountRequest;
import com.org.discount.dto.PurchaseItem;
import com.org.discount.model.AvailableDiscount;
import com.org.discount.repository.AvailableDiscountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DiscountServiceTest {

    @Mock
    private AvailableDiscountRepository repository;

    @InjectMocks
    private DiscountServiceImpl discountService;

    private AvailableDiscount empDiscount;
    private AvailableDiscount affiliateDiscount;
    private AvailableDiscount customerDiscount;

    private static final int aboveAmount = 100;
    private static final int discountAboveAmount = 5;

    @BeforeEach
    public void setup() {
        empDiscount = new AvailableDiscount().
                setDiscountType("EMPLOYEE").setDiscountPercent(30.00);
        affiliateDiscount = new AvailableDiscount().
                setDiscountType("AFFILIATE").setDiscountPercent(10.00);
        customerDiscount = new AvailableDiscount().
                setDiscountType("OLD_CUSTOMER").setDiscountPercent(5.00);
    }

    @Test
    void testEmpDiscount() {
        List<PurchaseItem> purchaseItems = new LinkedList<>();
        purchaseItems.add(new PurchaseItem().setItemType(ItemType.GROCERY).setAmount(520.00));
        DiscountRequest request = new DiscountRequest()
                .setCustomerOldAge(1)
                .setUserId("Aman12345")
                .setUserType(UserType.EMPLOYEE)
                .setPurchaseItems(purchaseItems);
        discountService.aboveAmount = aboveAmount;
        discountService.discountAboveAmount = discountAboveAmount;
        given(repository.findByDiscountType(DiscountCondition.EMPLOYEE.toString())).
                willReturn(Optional.of(empDiscount));
        final Optional<Double> expected  =
                Optional.of(discountService.getAmountAfterApplyingDiscount(request));

        assertThat(expected).isNotNull();
        Assertions.assertEquals(495.00, expected.get().doubleValue());
    }

    @Test
    void testAffiliateDiscount() {
        List<PurchaseItem> purchaseItems = new LinkedList<>();
        purchaseItems.add(new PurchaseItem().setItemType(ItemType.OTHERS).setAmount(520.00));
        DiscountRequest request = new DiscountRequest()
                .setCustomerOldAge(2)
                .setUserId("Aman12345")
                .setUserType(UserType.AFFILIATE)
                .setPurchaseItems(purchaseItems);
        discountService.aboveAmount = aboveAmount;
        discountService.discountAboveAmount = discountAboveAmount;
        given(repository.findByDiscountType(DiscountCondition.AFFILIATE.toString())).
                willReturn(Optional.of(affiliateDiscount));
        final Optional<Double> expected  =
                Optional.of(discountService.getAmountAfterApplyingDiscount(request));

        assertThat(expected).isNotNull();
        Assertions.assertEquals(443.00, expected.get().doubleValue());
    }

    @Test
    void testCustomerDiscount() {
        List<PurchaseItem> purchaseItems = new LinkedList<>();
        purchaseItems.add(new PurchaseItem().setItemType(ItemType.OTHERS).setAmount(520.00));
        DiscountRequest request = new DiscountRequest()
                .setCustomerOldAge(2)
                .setUserId("Aman12345")
                .setUserType(UserType.CUSTOMER)
                .setPurchaseItems(purchaseItems);
        discountService.aboveAmount = aboveAmount;
        discountService.discountAboveAmount = discountAboveAmount;
        given(repository.findByDiscountType(DiscountCondition.OLD_CUSTOMER.toString())).
                willReturn(Optional.of(customerDiscount));
        final Optional<Double> expected  =
                Optional.of(discountService.getAmountAfterApplyingDiscount(request));

        assertThat(expected).isNotNull();
        Assertions.assertEquals(469.00, expected.get().doubleValue());

    }
}
