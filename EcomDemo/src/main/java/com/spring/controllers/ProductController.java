package com.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.models.Product;
import com.spring.services.ProductService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts() {
	    return new ResponseEntity<List<Product>>(service.getProducts(), HttpStatus.OK);
	    
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable int id) {
	     Product product = service.getProductById(id);
	     if(product == null) return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	     return new ResponseEntity<Product>(product, HttpStatus.OK);
	    
	}
	
	@PostMapping("/product")
	public ResponseEntity<?> addProduct(@RequestPart Product product,@RequestPart MultipartFile imageFile){
		
		try {
			Product productAdded = service.addProduct(product, imageFile);
			return new ResponseEntity<>(productAdded,HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}	
		
	}
	
	@GetMapping("/product/{id}/image")
	public ResponseEntity<byte[]> getImage(@PathVariable int id) {
		return service.getImage(id);
	}
	
	@PutMapping("/product/{id}")
	public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestPart Product product, @RequestPart MultipartFile imageFile) {
		
		try {
		Product productupdated = service.updateProduct(id, product, imageFile);
		if(productupdated != null) {
			return ResponseEntity.ok().body("Product Updated");
		}
		else {
			return new ResponseEntity<String>("Update Operation Failed", HttpStatus.BAD_REQUEST);
		}
		}catch (Exception e) {
			return new ResponseEntity<>("IOException occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable int id){
		Product product  = service.getProductById(id);
		if(product != null) {
			service.deleteProduct(id);
			return new ResponseEntity<String>("Deleted Prduct", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Product Not Found with id: "+id, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/products/search")
	public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){
		List<Product> products = service.searchProducts(keyword);
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	


}
