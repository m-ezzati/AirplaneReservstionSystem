package com.mycompany.config.security;

import com.mycompany.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final CustomLoginSuccessHandler customLoginSuccessHandler;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService, CustomLoginSuccessHandler customLoginSuccessHandler, CustomAccessDeniedHandler customAccessDeniedHandler) {
        this.customUserDetailsService = customUserDetailsService;
        this.customLoginSuccessHandler = customLoginSuccessHandler;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {



        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/do-login", "/access-denied", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().permitAll()
                );

        http.formLogin(form-> form
                .loginPage("/login")
                .loginProcessingUrl("/do-login")
                .successHandler(customLoginSuccessHandler)
                .failureUrl("/login?error=true")
                .permitAll()
        );

        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
        );

        http.exceptionHandling(exception ->
                exception.accessDeniedHandler(customAccessDeniedHandler)
        );
        http.authenticationProvider(authenticationProvider());

        return http.build();
    }
}
