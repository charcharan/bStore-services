package com.bteam.bstore.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bteam.bstore.beans.ScannedItem;

@RestController
public class ScanController {

	private static final ScannedItem[] scannedItems = { new ScannedItem(1L, "Tennis Bat"),
			new ScannedItem(2L, "Wrist Watch"),
			new ScannedItem(3L, "T Shirt") };

	@GetMapping("/items")
	public ScannedItem[] scannedItems() {
		return scannedItems;
	}

	@GetMapping("/items/{id}")
	public ScannedItem scanItem(@PathVariable int id) {
		for (ScannedItem scannedItem : scannedItems) {
			if (scannedItem.getId() == id) {
				return scannedItem;
			}

		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found");
	}

}
