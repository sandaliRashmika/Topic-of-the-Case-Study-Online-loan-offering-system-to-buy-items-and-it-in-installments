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
import net.javaguides.springboot.model.Inventory;
import net.javaguides.springboot.repository.InventoryRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class InventoryController {

	@Autowired
	private InventoryRepository inventoryRepository;
	
	// get all inventory
	@GetMapping("/inventories")
	public List<Inventory> getAllInventories(){
		return inventoryRepository.findAll();
	}		
	
	// create product rest api
	@PostMapping("/inventories")
	public Inventory createInventory(@RequestBody Inventory inventory) {
		return inventoryRepository.save(inventory);
	}
	
	// get inventory by id rest api
	@GetMapping("/inventories/{id}")
	public ResponseEntity<Inventory> getInventoryById(@PathVariable Long id) {
		Inventory inventory = inventoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Inventory not exist with id :" + id));
		return ResponseEntity.ok(inventory);
	}
	
	// update inventory rest api
	
	@PutMapping("/inventories/{id}")
	public ResponseEntity<Inventory> updateInventory(@PathVariable Long id, @RequestBody Inventory inventoryDetails){
		Inventory inventory = inventoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Inventory not exist with id :" + id));
		
		inventory.setBrand(inventoryDetails.getBrand());
		inventory.setCategory (inventoryDetails.getCategory ());
		inventory.setCount(	inventoryDetails.getCount());
		
		
		Inventory updatedInvetory = inventoryRepository.save(inventory);
		return ResponseEntity.ok(updatedInvetory);
	}
	
	// delete inventory  rest api
	@DeleteMapping("/inventories/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteInventory(@PathVariable Long id){
		Inventory inventory = inventoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Inventory not exist with id :" + id));
		
		inventoryRepository.delete(inventory);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
}
