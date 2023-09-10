package com.estudos.reactive.config;

import com.estudos.reactive.domain.services.UserService;
import com.estudos.reactive.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;

@Configuration
@EnableR2dbcAuditing
public class UserConfig {

    @Bean
    UserService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }
}
