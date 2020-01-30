package com.example.project.controller;

import java.util.List;

import com.example.project.domain.Product;
import com.example.project.service.ProductService;
import com.example.project.service.FileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private FileService fileService;

	@GetMapping(value = "/list")
	public ResponseEntity<List<Product>> list() {
		return ResponseEntity.ok(productService.list());
	}

	@GetMapping(value = "id={id}")
	public ResponseEntity<Product> getById(@PathVariable Integer id) {
		return ResponseEntity.ok(productService.findById(id));
	}

	@GetMapping(value = "image={id}")
	public ResponseEntity<Resource> getImageById(@PathVariable Integer id) {
		Resource resource = fileService.stringToResource(productService.getImage(id));

		return ResponseEntity.ok()
			.contentType(fileService.tipoResource(resource))
			//.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
			.body(resource);
	}

	@PutMapping(value = "image={id}")
	public ResponseEntity<String> putMethodName(@PathVariable Integer id, MultipartFile file) {
		String imagePath = fileService.saveImage(file);
		return ResponseEntity.ok(productService.updateImage(imagePath, id));
	}

	@PostMapping(value="table_to_product")
	public ResponseEntity<String> tableToProduct() {
		fileService.tableToProduct();		
		return ResponseEntity.ok("Salvo com sucesso");
	}
	
}