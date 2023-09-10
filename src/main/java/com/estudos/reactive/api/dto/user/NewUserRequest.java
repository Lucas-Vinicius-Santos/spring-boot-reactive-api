package com.estudos.reactive.api.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
public class NewUserRequest {
    @NotNull(message = "name must not be null")
    @NotEmpty(message = "name must not be empty")
    @Length(min = 3, max = 255, message = "Name must be between 1 and 255 characters")
    String name;

    @Email(message = "email must be valid")
    @NotNull(message = "email must not be null")
    @NotEmpty(message = "email must not be empty")
    @Length(min = 3, max = 255, message = "Email must be between 1 and 255 characters")
    String email;

    @NotNull(message = "password must not be null")
    @NotEmpty(message = "password must not be empty")
    @Length(min = 3, max = 255, message = "Password must be between 1 and 255 characters")
    String password;
}
