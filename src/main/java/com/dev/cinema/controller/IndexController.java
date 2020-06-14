package com.dev.cinema.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/")
    public String index(@RequestParam(
            required = false,
            defaultValue = "Oleksii"
    ) String name) {
        return String.format("Hi there, %s!", name);
    }
}
