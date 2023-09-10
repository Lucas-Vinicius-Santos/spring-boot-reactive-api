package com.estudos.reactive.api.mappers;

import com.estudos.reactive.api.dto.user.UserResponse;
import com.estudos.reactive.domain.models.User;

public class UserMapper {

    public static UserResponse toNewUserResponse(User user) {
        UserResponse newUserResponse = new UserResponse();
        newUserResponse.setId(user.getId());
        newUserResponse.setName(user.getName());
        newUserResponse.setEmail(user.getEmail());
        return newUserResponse;
    }

    public static UserResponse toUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setCreatedAt(user.getCreatedAt());
        userResponse.setUpdatedAt(user.getUpdatedAt());
        return userResponse;
    }
}
