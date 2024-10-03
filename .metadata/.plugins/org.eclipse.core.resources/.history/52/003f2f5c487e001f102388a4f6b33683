package com.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	@Query("SELECT p FROM Product p WHERE " +
	           "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
	           "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
	           "LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
	           "LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
	           "LOWER(CAST(p.price AS string)) LIKE LOWER(CONCAT('%', :keyword, '%'))")
	List<Product> searchProducts(String keyword);

}
