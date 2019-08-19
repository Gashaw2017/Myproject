package com.geosolution.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geosolution.app.entity.Store;
import com.geosolution.app.service.StoreService;

@RestController
@RequestMapping("/api")
public class StoreListConroller {
	
	@Autowired
	private StoreService storeService;
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/stores")
	public List<Store> getAllStores() {
		return storeService.getAllStores();
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/stores")
	public void addNewStore(@RequestBody Store store) {
		storeService.addNewStore(store);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/stores/{storeName}")
	public List<Store> searchStoreByName(@PathVariable(value = "storeName") String storeName) {
		return storeService.getStoresByName(storeName.toLowerCase());
	}
	

}
