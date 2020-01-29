package com.example.project.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import com.example.project.domain.Product;
import com.example.project.exception.BusinessRuleException;
import com.example.project.exception.DataNotFoundException;
import com.example.project.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.util.StringUtils;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private String path = "C:\\Users\\jorge.aranha\\Pictures";

    public String uploadFile(MultipartFile file, Integer id) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        if (file.isEmpty()) {
            throw new BusinessRuleException("Failed to store empty file");
        }

        try {
            String path = "C:\\Users\\jorge.aranha\\Pictures\\";
			Files.copy(file.getInputStream(), Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
        }

        String image = path + "\\" + fileName;
        Product product = findById(id);
        product.setImage(image);

        productRepository.save(product);

        return image;

    }

    public List<Product> list() {
        return productRepository.findAll();
    }

    public Product findById(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(() -> new DataNotFoundException("Product not found"));
    }
    
}