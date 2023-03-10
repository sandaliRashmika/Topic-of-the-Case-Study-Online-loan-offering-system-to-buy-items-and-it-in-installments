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
import net.javaguides.springboot.model.Category;
import net.javaguides.springboot.repository.CategoryRepository;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	// get all category
	@GetMapping("/categories")
	public List<Category> getAllCategories(){
		return categoryRepository.findAll();
	}		
	
	// create category rest api
	@PostMapping("/categories")
	public Category createCategory(@RequestBody Category category) {
		return categoryRepository.save(category);
	}
	
	// get category by id rest api
	@GetMapping("/categories/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category not exist with id :" + id));
		return ResponseEntity.ok(category);
	}
	
	// update category rest api
	
	@PutMapping("/categories/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails){
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category not exist with id :" + id));
		
		category.setName(categoryDetails.getName());
		
		
		Category updatedCategory = categoryRepository.save(category);
		return ResponseEntity.ok(updatedCategory);
	}
	
	// delete product rest api
	@DeleteMapping("/categories/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable Long id){
	   Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category not exist with id :" + id));
		
	   categoryRepository.delete(category);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
}
