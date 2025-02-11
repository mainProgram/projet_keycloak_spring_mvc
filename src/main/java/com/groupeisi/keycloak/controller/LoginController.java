package com.groupeisi.keycloak.controller;

import com.groupeisi.keycloak.dto.UserDto;
import com.groupeisi.keycloak.service.IUserService;
import com.groupeisi.keycloak.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final IUserService userService = new UserService();

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/login")
    public String login(HttpSession session, @RequestParam String email, @RequestParam String password){

        try {
            Optional<UserDto> userDto = userService.login(email, password);

            if (userDto.isPresent()) {
                session.setAttribute("username", email);
                return "redirect:/welcome";
            } else {

                return "redirect:/";
            }
        } catch (Exception e) {
            logger.error("Erreur de connexion : {}", e.getMessage());

            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
