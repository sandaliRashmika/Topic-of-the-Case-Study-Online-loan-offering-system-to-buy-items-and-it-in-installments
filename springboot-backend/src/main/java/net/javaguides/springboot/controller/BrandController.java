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
import net.javaguides.springboot.model.Brand;
import net.javaguides.springboot.repository.BrandRepository;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class BrandController {

	@Autowired
	private BrandRepository brandRepository;
	
	// get all products
	@GetMapping("/brands")
	public List<Brand> getAllBrands(){
		return brandRepository.findAll();
	}		
	
	// create brand rest api
	@PostMapping("/brands")
	public Brand createBrand(@RequestBody Brand brand) {
		return brandRepository.save(brand);
	}
	
	// get brand by id rest api
	@GetMapping("/brands/{id}")
	public ResponseEntity<Brand> getBrandById(@PathVariable Long id) {
		Brand brand = brandRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Brand not exist with id :" + id));
		return ResponseEntity.ok(brand);
	}
	
	// update brand rest api
	
	@PutMapping("/brands/{id}")
	public ResponseEntity<Brand> updateBrand(@PathVariable Long id, @RequestBody Brand brandDetails){
		Brand brand = brandRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Brand not exist with id :" + id));
		
		brand.setName(brandDetails.getName());
		
		
		Brand updatedBrand = brandRepository.save(brand);
		return ResponseEntity.ok(updatedBrand);
	}
	
	// delete product rest api
	@DeleteMapping("/brands/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteBrand(@PathVariable Long id){
	 	Brand brand = brandRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Brand not exist with id :" + id));
		
		brandRepository.delete(brand);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
}
