package com.estudos.reactive.domain.services;

import com.estudos.reactive.api.dto.user.NewUserRequest;
import com.estudos.reactive.domain.models.User;
import com.estudos.reactive.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Flux<User> getUsers() {
        return userRepository.findAll();
    }

    public Mono<User> createUser(NewUserRequest newUserRequest) {
        return userRepository.findByEmail(newUserRequest.getEmail())
                .flatMap(existingUser -> Mono.<User>error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists")))
                .switchIfEmpty(Mono.defer(() -> {
                    User newUser = new User();
                    newUser.setName(newUserRequest.getName());
                    newUser.setEmail(newUserRequest.getEmail());
                    newUser.setPassword(newUserRequest.getPassword());
                    return userRepository.save(newUser);
                }));
    }

}

