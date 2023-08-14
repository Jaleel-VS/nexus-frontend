// Group all entities related to the user here, TODO: split in separate services later

package com.nexus.nexusapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexus.nexusapi.repository.UserRepository;
import com.nexus.nexusapi.model.Brand;
import com.nexus.nexusapi.model.Influencer;
import com.nexus.nexusapi.model.Supplier;
import com.nexus.nexusapi.model.User;
import com.nexus.nexusapi.dto.request.LoginRequestDTO;
import com.nexus.nexusapi.dto.response.BrandDTO;
import com.nexus.nexusapi.dto.response.InfluencerDTO;
import com.nexus.nexusapi.dto.response.SupplierDTO;
import com.nexus.nexusapi.dto.response.UserDTO;
import com.nexus.nexusapi.exceptions.NotFoundException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        return UserDTO.fromEntity(user);
    }

    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found"));
        return UserDTO.fromEntity(user);
    }

    public BrandDTO getBrandById(Long brandId) {
        User user = userRepository.findById(brandId).orElseThrow(() -> new NotFoundException("Brand not found"));
        if (user instanceof Brand) {
            return BrandDTO.fromEntity((Brand) user);
        } else {
            throw new NotFoundException("Brand not found");
        }
    }

    public InfluencerDTO getInfluencerById(Long influencerId) {
        User user = userRepository.findById(influencerId).orElseThrow(() -> new NotFoundException("Influencer not found"));
        if (user instanceof Influencer) {
            return InfluencerDTO.fromEntity((Influencer) user);
        } else {
            throw new NotFoundException("Influencer not found");
        }
    }

    public SupplierDTO getSupplierById(Long supplierId) {
        User user = userRepository.findById(supplierId).orElseThrow(() -> new NotFoundException("Supplier not found"));
        if (user instanceof Supplier) {
            return SupplierDTO.fromEntity((Supplier) user);
        } else {
            throw new NotFoundException("Supplier not found");
        }
    }

    // get all users
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> UserDTO.fromEntity(user)).collect(java.util.stream.Collectors.toList());
    }

    // get all brands
    public List<BrandDTO> getAllBrands() {
        List<User> users = userRepository.findAll();
        return users.stream().filter(user -> user instanceof Brand).map(user -> BrandDTO.fromEntity((Brand) user)).collect(java.util.stream.Collectors.toList());
    }

    // get all influencers
    public List<InfluencerDTO> getAllInfluencers() {
        List<User> users = userRepository.findAll();
        return users.stream().filter(user -> user instanceof Influencer).map(user -> InfluencerDTO.fromEntity((Influencer) user)).collect(java.util.stream.Collectors.toList());
    }

    // get all suppliers
    public List<SupplierDTO> getAllSuppliers() {
        List<User> users = userRepository.findAll();
        return users.stream().filter(user -> user instanceof Supplier).map(user -> SupplierDTO.fromEntity((Supplier) user)).collect(java.util.stream.Collectors.toList());
    }

    public UserDTO authenticate(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByUsername(loginRequestDTO.getUsername()).orElseThrow(() -> new NotFoundException("User not found"));
        if (user.getPassword().equals(loginRequestDTO.getPassword())) {
            return UserDTO.fromEntity(user);
        } else {
            throw new NotFoundException("User not found");
        }
    }

}
