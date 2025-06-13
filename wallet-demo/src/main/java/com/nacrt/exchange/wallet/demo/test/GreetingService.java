package com.nacrt.exchange.wallet.common.test;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class GreetingService {
    private final AtomicLong counter = new AtomicLong();
    
    public Greeting createGreeting(String name) {
        return new Greeting(counter.incrementAndGet(), "Hello, %s!".formatted(name));
    }
}