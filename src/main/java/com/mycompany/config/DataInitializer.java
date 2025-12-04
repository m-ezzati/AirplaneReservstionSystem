package com.mycompany.config;

import com.mycompany.model.User;
import com.mycompany.model.enums.Role;
import com.mycompany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    @Value("${admin.email}")
    private String adminEmail;

    @Bean
    public CommandLineRunner createAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            boolean adminExists = userRepository.findByUsername(adminUsername).isPresent();

            if (!adminExists) {
                User admin = User.builder()
                        .username(adminUsername)
                        .password(passwordEncoder.encode(adminPassword))
                        .email(adminEmail)
                        .role(Role.ROLE_ADMIN)
                        .build();

                userRepository.save(admin);
                System.out.println("Admin user created: username=" + adminUsername + ", password=" + adminPassword);
            } else {
                System.out.println("Admin user already exists.");
            }
        };
    }


    @Bean
    public CommandLineRunner createUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            boolean userExists = userRepository.findByUsername("Mary").isPresent();

            if (!userExists) {
                User user = User.builder()
                        .username("Mary")
                        .password(passwordEncoder.encode("123"))
                        .email("mary@email.com")
                        .role(Role.ROLE_USER)
                        .build();

                userRepository.save(user);
                System.out.println("Mary user created: username= Mary, password= 123");
            } else {
                System.out.println("User Mary is already exists!");
            }
        };
    }
}
