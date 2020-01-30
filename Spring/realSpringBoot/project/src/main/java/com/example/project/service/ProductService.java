package com.example.project.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import com.example.project.domain.Product;
import com.example.project.exception.BusinessRuleException;
import com.example.project.exception.DataNotFoundException;
import com.example.project.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.util.StringUtils;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private String path = "/home/jorgearanha/Pictures/";
    // private String path = "C:\\Users\\jorge.aranha\\Pictures\\";

    public List<Product> list() {
        return productRepository.findAll();
    }

    public Product findById(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(() -> new DataNotFoundException("Product not found"));
    }

    public String uploadFile(MultipartFile file, Integer id) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        String image = path + "/" + fileName;
        // String image = path + "\\" + fileName;

        if (file.isEmpty()) {
            throw new BusinessRuleException("Failed to store empty file");
        }

        try {
            Files.copy(file.getInputStream(), Paths.get(image), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Product product = findById(id);
        product.setImage(image);

        productRepository.save(product);

        return image;

    }

    public Resource downloadFile(Integer id) {

        Product product = findById(id);

        Path path = Paths.get(product.getImage());
        Resource resource = null;

        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return resource;
    }

}