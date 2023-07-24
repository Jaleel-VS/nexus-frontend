package com.nexus.vouchers.services;

import com.nexus.vouchers.models.User;
import com.nexus.vouchers.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService<T extends User> {

    @Autowired
    private UserRepository userRepository;

    public T save(T user) {
        return userRepository.save(user);
    }

    // find by id
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }








}
