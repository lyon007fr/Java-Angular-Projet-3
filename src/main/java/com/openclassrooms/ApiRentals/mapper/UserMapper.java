package com.openclassrooms.ApiRentals.mapper;

import com.openclassrooms.ApiRentals.dto.UserDTO;
import com.openclassrooms.ApiRentals.model.User;

public class UserMapper {

    public static UserDTO convertToDTO(User user) {
        return new UserDTO(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getPassword(),
            user.getCreated_at(),
            user.getUpdated_at()
        );
    }
}
