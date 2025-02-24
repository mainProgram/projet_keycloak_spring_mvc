package com.groupeisi.keycloak.controller;

import org.keycloak.KeycloakPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
public class LogoutController {

    private String keycloakServerUrl = "http://localhost:8081";

    private String realm = "demo";

    private String clientId = "tp-keycloak";

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }

        String redirectUri = URLEncoder.encode(
                "http://localhost:8085/keycloak/",
                StandardCharsets.UTF_8.name()
        );

        String logoutUrl = keycloakServerUrl
                + "/realms/" + realm
                + "/protocol/openid-connect/logout"
                + "?client_id=" + clientId
                + "&post_logout_redirect_uri=" + redirectUri;

        if (auth != null && auth.getPrincipal() instanceof KeycloakPrincipal) {
            KeycloakPrincipal<?> principal = (KeycloakPrincipal<?>) auth.getPrincipal();
            String idToken = principal.getKeycloakSecurityContext().getIdTokenString();
            logoutUrl += "&id_token_hint=" + idToken;
        }

        return "redirect:" + logoutUrl;
    }
}
