package com.estudos.reactive.repositories;

import com.estudos.reactive.domain.models.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {

    public Mono<User> findByEmail(String email);
}
