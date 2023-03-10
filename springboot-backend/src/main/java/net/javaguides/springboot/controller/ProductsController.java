package net.javaguides.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Products;
import net.javaguides.springboot.repository.ProductsRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class ProductsController {

	@Autowired
	private ProductsRepository productsRepository;
	
	// get all products
	@GetMapping("/products")
	public List<Products> getAllProducts(){
		return productsRepository.findAll();
	}		
	
	// create product rest api
	@PostMapping("/products")
	public Products createProduct(@RequestBody Products products) {
		return productsRepository.save(products);
	}
	
	// get product by id rest api
	@GetMapping("/products/{id}")
	public ResponseEntity<Products> getProductById(@PathVariable Long id) {
		Products products = productsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not exist with id :" + id));
		return ResponseEntity.ok(products);
	}
	
	// update product rest api
	
	@PutMapping("/products/{id}")
	public ResponseEntity<Products> updateProduct(@PathVariable Long id,
			@RequestBody Products productDetails){
		Products product = productsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not exist with id :" + id));
		
		product.setProductName(productDetails.getProductName());
		product.setBrand(productDetails.getBrand());
		product.setCategory(productDetails.getCategory());
		product.setPrice(productDetails.getPrice());
		
		Products updatedProducts = productsRepository.save(product);
		return ResponseEntity.ok(updatedProducts);
	}
	
	// delete product rest api
	@DeleteMapping("/products/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable Long id){
		Products product = productsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not exist with id :" + id));
		
		productsRepository.delete(product);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
}
