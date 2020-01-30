package com.example.project.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import com.example.project.domain.Product;
import com.example.project.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping(value = "/list")
	public ResponseEntity<List<Product>> list() {
		return ResponseEntity.ok(productService.list());
	}

	@GetMapping(value = "id={id}")
	public ResponseEntity<Product> getById(@PathVariable Integer id) {
		return ResponseEntity.ok(productService.findById(id));
	}

	@GetMapping(value = "image={id}")
	public ResponseEntity<?> getImageById(@PathVariable Integer id) {
		Resource file = productService.downloadFile(id);
		
		String mimeType = "";
		
		try {
			mimeType = Files.probeContentType(file.getFile().toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@PutMapping(value = "image={id}")
	public ResponseEntity<?> putMethodName(@PathVariable Integer id, MultipartFile file) {
		return ResponseEntity.ok(productService.uploadFile(file, id));
	}

}