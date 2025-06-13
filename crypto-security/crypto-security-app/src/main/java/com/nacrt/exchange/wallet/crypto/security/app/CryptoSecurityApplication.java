package com.nacrt.exchange.wallet.crypto.security.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan(basePackages = {
    "com.nacrt.exchange.wallet.crypto.security",
    "com.nacrt.exchange.wallet.crypto.security.service",
    "com.nacrt.exchange.wallet.crypto.security.open"
})
@EntityScan("com.nacrt.exchange.wallet.crypto.security.dao.model")
@EnableJpaRepositories("com.nacrt.exchange.wallet.crypto.security.dao.repository")
@PropertySource(value = {
    "classpath:application.yml",
    "classpath*:application.yml",
    "classpath*:application-${spring.profiles.active}.yml"
}, ignoreResourceNotFound = true)
public class CryptoSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(CryptoSecurityApplication.class, args);
    }
}
