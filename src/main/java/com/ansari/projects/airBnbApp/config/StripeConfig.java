package com.ansari.projects.airBnbApp.config;

import com.stripe.Stripe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfig {

    public StripeConfig(@Value("${stripe.api.secret}") String apiKey){
        Stripe.apiKey = apiKey;
    }

}
