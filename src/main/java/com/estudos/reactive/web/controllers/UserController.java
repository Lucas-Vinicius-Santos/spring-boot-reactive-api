package com.estudos.reactive.web.controllers;

import com.estudos.reactive.api.dto.user.NewUserRequest;
import com.estudos.reactive.api.dto.user.UserResponse;
import com.estudos.reactive.domain.services.UserService;
import com.estudos.reactive.api.mappers.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    ResponseEntity<Flux<UserResponse>> getAllUsers() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getUsers().map(UserMapper::toUserResponse));
    }

    @PostMapping
    ResponseEntity<Mono<UserResponse>> createNewUser(@RequestBody @Valid NewUserRequest userRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.createUser(userRequest).map(UserMapper::toNewUserResponse));
    }
}
