package com.bytescolaborativos.gamehub.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemporalTestController {

    @GetMapping("/test")
    public String testEndpoint() {
        return "Hello from Spring Boot!";
    }
}
