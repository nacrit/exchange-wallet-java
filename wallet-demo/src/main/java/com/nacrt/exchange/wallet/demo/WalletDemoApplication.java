package com.nacrt.exchange.wallet.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableCaching
@EnableAsync
@SpringBootApplication
public class WalletDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WalletDemoApplication.class, args);
    }

}
