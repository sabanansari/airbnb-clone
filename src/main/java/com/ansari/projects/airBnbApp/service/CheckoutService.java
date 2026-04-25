package com.ansari.projects.airBnbApp.service;

import com.ansari.projects.airBnbApp.entity.Booking;

public interface CheckoutService {

    String getCheckoutSession(Booking booking, String successUrl, String failureUrl);
}
