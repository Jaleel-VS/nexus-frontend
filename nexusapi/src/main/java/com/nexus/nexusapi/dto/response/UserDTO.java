package com.nexus.nexusapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.nexus.nexusapi.model.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String address;
    private String metamaskAddress;
    private String profilePictureUrl;
    private String userType; // New field for user type

    public static UserDTO fromEntity(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setAddress(user.getAddress());
        dto.setMetamaskAddress(user.getMetamaskAddress());
        dto.setProfilePictureUrl(user.getProfilePictureUrl());
        dto.setUserType(user.getUserType()); // Set user type from entity
        return dto;
    }
}