package com.mycompany.config.security;

import com.mycompany.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

//        request.getSession().setAttribute("user", authentication.getPrincipal());

        CustomUserDetails details = (CustomUserDetails) authentication.getPrincipal();
        User user = details.getUser();
        request.getSession().setAttribute("user", user);

        var authorities = authentication.getAuthorities();

        String redirectUrl = "/";

        if (authorities.stream().anyMatch(a ->
                a.getAuthority().equals("ROLE_ADMIN"))) {
            redirectUrl = "/admin/dashboard";

        } else if (authorities.stream().anyMatch(a ->
                a.getAuthority().equals("ROLE_USER"))) {
            redirectUrl = "/user/dashboard";
        }

        response.sendRedirect(redirectUrl);
    }

}
