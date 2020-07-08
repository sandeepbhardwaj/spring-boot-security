package com.koko.springsecurity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
public class WelcomeController {

    @GetMapping
    public String welcomeMessage()
    {
        return "<H1>Welcome to spring security</H1>";
    }

    @GetMapping("/hello")
    public String helloMessage()
    {
        return "<H1>Hello from unsecured page</H1>";
    }
}
