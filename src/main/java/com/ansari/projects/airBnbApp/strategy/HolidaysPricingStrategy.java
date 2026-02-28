package com.ansari.projects.airBnbApp.strategy;

import com.ansari.projects.airBnbApp.entity.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class HolidaysPricingStrategy implements PricingStrategy {

    private final PricingStrategy wrapped;

    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        BigDecimal price = wrapped.calculatePrice(inventory);
        boolean isHoliday = true; //checkIfHoliday(inventory.getDate());

        if(isHoliday){
            price = price.multiply(BigDecimal.valueOf(1.25)); // 25% increase for holidays
        }

        return price;
    }
}
