package com.spring.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.models.Product;
import com.spring.repositories.ProductRepository;

@Service
public class ProductService { 
	
	
	private ProductRepository repo;
	
	public ProductService(ProductRepository repo) {
		this.repo = repo;
	}

	public List<Product> getProducts() {
      return repo.findAll();
        
    }

	public Product getProductById(int id) {
		Optional<Product> product = repo.findById(id);
		return product.orElse(null);
	}

	public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
		
		product.setImageName(imageFile.getOriginalFilename());
		product.setImageType(imageFile.getContentType());
		product.setImageData(imageFile.getBytes());
		
		return repo.save(product);
		
	}

	public ResponseEntity<byte[]> getImage(int id) {
		Product product =  repo.findById(id).get();
		byte[] imageFile =  product.getImageData();
		return ResponseEntity.ok().body(imageFile);
	}

	public Product updateProduct(int id, Product product, MultipartFile imageFile) throws IOException {
		Product productFound = repo.findById(id).orElse(null);
		if(productFound != null) {
			
			product.setImageName(imageFile.getOriginalFilename());
			product.setImageType(imageFile.getContentType());
			product.setImageData(imageFile.getBytes());
			
			return repo.save(product);
		}
		return productFound;
	}

	public void deleteProduct(int id) {
		repo.deleteById(id);
	}

	public List<Product> searchProducts(String keyword) {
		
		return repo.searchProducts(keyword);
	}


}
