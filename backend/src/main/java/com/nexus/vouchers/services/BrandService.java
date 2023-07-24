package com.nexus.vouchers.services;

import com.nexus.vouchers.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandService extends UserService {
    @Autowired
    private BrandRepository brandRepository;


}
