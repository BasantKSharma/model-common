package com.gogo.model.common.domain.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * List of url for the services
 */
@Component
@ConfigurationProperties(prefix = "service.url")
@Getter
@Setter
public class ServiceUrlConfig {

    private String security;

    private String ui;

    private String user;

    private String customer;

    private String address;

    private String userModule;

    private String productCatalogCategory;

    private String productCatalogBrand;

    private String productCatalogProduct;

    private String setting;

    private String country;

    private String state;

    private String currency;

    private String shippingRate;

    private String checkout;

    private String order;

    private String cart;

    private String payment;

    public String getCustomerSecurity(){
        return customer + "/security";
    }
}
