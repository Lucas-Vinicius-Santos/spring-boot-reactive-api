package com.estudos.reactive.api.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    Long id;
    String name;
    String email;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
