package com.groupeisi.keycloak.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
    @GetMapping( "/")
    public String welcome() {
        return "welcome";
    }

    @GetMapping( "/welcome")
    public String welcome2() {
        return "welcome";
    }

}
