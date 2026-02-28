package com.ansari.projects.airBnbApp.strategy;

import com.ansari.projects.airBnbApp.entity.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


public interface PricingStrategy {


    BigDecimal calculatePrice(Inventory inventory);

}
