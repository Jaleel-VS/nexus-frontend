package com.nexus.nexusapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexus.nexusapi.dto.response.ProductDTO;
import com.nexus.nexusapi.model.Product;
import com.nexus.nexusapi.repository.ProductRepository;
import com.nexus.nexusapi.exceptions.NotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Get product by id
    public ProductDTO getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found"));

        return convertToDTO(product);
    }

    // Get all products
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> convertToDTO(product)).collect(Collectors.toList());
    }

    // Get products by brand id
    public List<ProductDTO> getProductsByBrandId(Long brandId) {
        List<Product> products = productRepository.findByBrandId(brandId);
        return products.stream().map(product -> convertToDTO(product)).collect(Collectors.toList());
    }

    // convert to DTO
    private ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setProductName(product.getProductName());
        dto.setProductDescription(product.getProductDescription());
        dto.setProductPrice(product.getProductPrice());
        dto.setBrandId(product.getBrand().getId());
        dto.setSupplierIds(new HashSet<>(product.getSuppliers().stream().map(supplier -> supplier.getId()).collect(Collectors.toList())));
        dto.setProductImage(product.getProductImageUrl());

        return dto;
    }
}
