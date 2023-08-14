package com.nexus.nexusapi.dto.response;

import com.nexus.nexusapi.model.Influencer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class InfluencerDTO extends UserDTO {
    private String firstName;
    private String lastName;
    private String instagramHandle;

    // Getters and setters

    public static InfluencerDTO fromEntity(Influencer influencer) {
        InfluencerDTO dto = new InfluencerDTO();
        dto.setId(influencer.getId());
        dto.setUsername(influencer.getUsername());
        dto.setEmail(influencer.getEmail());
        dto.setAddress(influencer.getAddress());
        dto.setUserType(influencer.getUserType());
        dto.setFirstName(influencer.getFirstName());
        dto.setLastName(influencer.getLastName());
        dto.setInstagramHandle(influencer.getInstagramHandle());
        return dto;
    }
}
    

