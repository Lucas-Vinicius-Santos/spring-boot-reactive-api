package com.estudos.reactive.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@Table("users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @NotNull
    @NotEmpty
    @Positive
    private Long id;

    @NotNull
    @NotEmpty
    @NotBlank
    @Length(min = 3, max = 255)
    private String name;

    @Email
    @NotNull
    @NotEmpty
    @NotBlank
    @Length(min = 3, max = 255)
    private String email;

    @NotNull
    @NotEmpty
    @NotBlank
    @Length(min = 3, max = 255)
    private String password;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
