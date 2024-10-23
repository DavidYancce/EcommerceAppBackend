package com.ecommerceproject.config;

import com.mercadopago.MercadoPagoConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class MercadoPagoConfigSetup {

    @Value("${mercado-pago.token}")
    private String accessToken;

    @PostConstruct
    public void initializeMercadoPago() {
        MercadoPagoConfig.setAccessToken(accessToken);
    }
}
