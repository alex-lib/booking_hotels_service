package com.service.bookinghotels.web.controllers;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/")
public class DefaultController {

    @ResponseStatus(HttpStatus.PERMANENT_REDIRECT)
    @GetMapping
    public String redirectToSwagger() {
        return "redirect:/swagger-ui/index.html";
    }
}