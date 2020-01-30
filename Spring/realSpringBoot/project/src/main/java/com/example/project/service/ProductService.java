package com.example.project.service;

import java.util.List;
import java.util.Optional;

import com.example.project.domain.dto.entities.Product;
import com.example.project.exception.DataNotFoundException;
import com.example.project.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> list() {
        return productRepository.findAll();
    }

    public Product findById(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(() -> new DataNotFoundException("Product not found"));
    }

    public String updateImage(String image, Integer id) {
        Product product = findById(id);
        
        product.setImage(image);
        productRepository.save(product);

        return image;
    }

    public String getImage(Integer id) {

        Product product = findById(id);

        if (product.getImage() == null)
            throw new DataNotFoundException("Não há imagem cadastrada");

        return product.getImage();

    }

	public Product createProduct(Product entity) {
        return productRepository.save(entity);
	}
    
}