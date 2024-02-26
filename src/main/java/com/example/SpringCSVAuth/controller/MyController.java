package com.example.SpringCSVAuth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/al")
    public String message(Authentication authentication){
        return "Welcome"+" "+authentication.getName();
    }



}
