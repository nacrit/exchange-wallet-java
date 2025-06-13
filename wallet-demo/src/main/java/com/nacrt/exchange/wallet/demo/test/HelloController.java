package com.nacrt.exchange.wallet.common.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private final GreetingService greetingService;

    public HelloController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/hello")
    public Greeting sayHello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return greetingService.createGreeting(name);
    }
}