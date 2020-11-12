package com.org.discount.service;

import com.org.discount.constant.DiscountCondition;
import com.org.discount.constant.ItemType;
import com.org.discount.constant.UserType;
import com.org.discount.dto.DiscountRequest;
import com.org.discount.dto.PurchaseItem;
import com.org.discount.model.AvailableDiscount;
import com.org.discount.repository.AvailableDiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountServiceImpl implements DiscountService {


    @Autowired
    private AvailableDiscountRepository repository;

    @Value("${discount.customer.old.age}")
    private int oldAge;

    @Value("${above.amount}")
    public int aboveAmount;

    @Value("${discount.above.amount}")
    public int discountAboveAmount;


    @Override
    public Double getAmountAfterApplyingDiscount(DiscountRequest discountRequest) {

        Double billAfterDiscount;
        Double userTypePercent = 0D;
        Double applicablePercent;
        //userType percentage
        if(discountRequest.getUserType() != UserType.CUSTOMER) {
            userTypePercent = getDiscountPercent(DiscountCondition.valueOf(discountRequest.getUserType().toString()));
        }
        applicablePercent = userTypePercent;
        //Old customer
        if(discountRequest.getUserType() == UserType.CUSTOMER  && discountRequest.getCustomerOldAge() > oldAge) {
            Double oldPercent = getDiscountPercent(DiscountCondition.OLD_CUSTOMER);
            if(oldPercent > userTypePercent) {
                applicablePercent = oldPercent;
            }
        }

        Double totalAmount = discountRequest.getPurchaseItems().stream()
                .mapToDouble(PurchaseItem::getAmount).sum();
        if(totalAmount >= aboveAmount) {
            int billAmount = (int)Math.round(totalAmount);
            int totalDiscount = (billAmount/aboveAmount) * discountAboveAmount;
            billAfterDiscount = totalAmount - totalDiscount;
        } else {
            billAfterDiscount = totalAmount;
        }

        List<PurchaseItem> purchaseItems = discountRequest.getPurchaseItems();
        for (PurchaseItem item:purchaseItems) {
            if(item.getItemType() == ItemType.OTHERS) {
                billAfterDiscount -= (item.getAmount() * applicablePercent)/100;
            }
        }
        return billAfterDiscount;
    }


    private Double getDiscountPercent(DiscountCondition condition) {
        Double discountPercent = 0D;
        Optional<AvailableDiscount> availableDiscount;
            availableDiscount = repository.findByDiscountType(condition.toString());
            if(availableDiscount.isPresent()) {
                discountPercent = availableDiscount.get().getDiscountPercent();
            }
        return discountPercent;
    }
}
