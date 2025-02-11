package com.groupeisi.keycloak.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
    @GetMapping(name="welcome", value = "/welcome")
    public String welcome() {
        return "welcome";
    }

}
