package com.nacrt.exchange.wallet.assetBusiness.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.nacrt.exchange.wallet.assetBusiness"})
public class AssetBusinessApplication {
    public static void main(String[] args) {
        SpringApplication.run(AssetBusinessApplication.class, args);
    }
}
