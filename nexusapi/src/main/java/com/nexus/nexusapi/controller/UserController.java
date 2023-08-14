package com.nexus.nexusapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nexus.nexusapi.service.UserService;
import com.nexus.nexusapi.dto.request.LoginRequestDTO;
import com.nexus.nexusapi.dto.response.BrandDTO;
import com.nexus.nexusapi.dto.response.InfluencerDTO;
import com.nexus.nexusapi.dto.response.SupplierDTO;
import com.nexus.nexusapi.dto.response.UserDTO;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    // Get user by id
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        UserDTO user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Get brand by id
    @GetMapping("/brands/{brandId}")
    public ResponseEntity<UserDTO> getBrandById(@PathVariable Long brandId) {
        UserDTO brand = userService.getBrandById(brandId);
        return ResponseEntity.ok(brand);
    }

    // Get all brands
    @GetMapping("/brands")
    public ResponseEntity<List<BrandDTO>> getAllBrands() {
        List<BrandDTO> brands = userService.getAllBrands();
        return ResponseEntity.ok(brands);
    }

    // Get influencer by id
    @GetMapping("/influencers/{influencerId}")
    public ResponseEntity<InfluencerDTO> getInfluencerById(@PathVariable Long influencerId) {
        InfluencerDTO influencer = userService.getInfluencerById(influencerId);
        return ResponseEntity.ok(influencer);
    }

    // Get all influencers
    @GetMapping("/influencers")
    public ResponseEntity<List<InfluencerDTO>> getAllInfluencers() {
        List<InfluencerDTO> influencers = userService.getAllInfluencers();
        return ResponseEntity.ok(influencers);
    }

    // Get supplier by id
    @GetMapping("/suppliers/{supplierId}")
    public ResponseEntity<UserDTO> getSupplierById(@PathVariable Long supplierId) {
        UserDTO supplier = userService.getSupplierById(supplierId);
        return ResponseEntity.ok(supplier);
    }

    // Get all suppliers
    @GetMapping("/suppliers")
    public ResponseEntity<List<SupplierDTO>> getAllSuppliers() {
        List<SupplierDTO> suppliers = userService.getAllSuppliers();
        return ResponseEntity.ok(suppliers);
    }

    // login
    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        UserDTO user = userService.authenticate(loginRequestDTO);
        return ResponseEntity.ok(user);
    }

}

    
