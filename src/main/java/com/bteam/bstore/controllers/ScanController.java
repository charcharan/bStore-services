package com.bteam.bstore.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bteam.bstore.beans.ScannedItem;
import com.bteam.bstore.service.ScanService;


@RestController
public class ScanController {
	
	@Autowired
	private ScanService service;
	
	private static final int CLOUD_INDICATOR = 1;
	
	private static final ScannedItem[] scannedItems = { new ScannedItem(1.0, "Tennis Bat"),
			new ScannedItem(2.0, "Wrist Watch"),
			new ScannedItem(3.0, "T Shirt") };
	
	//To get the highest confidence item for the image from cloud
	@RequestMapping(value="/items",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public String scannedItems() throws Exception {
		
		String item = "";
		
		//Get response based on type of cloud service indicator
		item = CLOUD_INDICATOR==1 ? service.getAWSResponse() : item;
		if(item.isEmpty()) {
			throw new Exception("No results found");
		}
		return item;
		
	}

	@GetMapping("/items/{id}")
	public ScannedItem scanItem(@PathVariable int name) {
		for (ScannedItem scannedItem : scannedItems) {
			if (scannedItem.getName().equals(name)) {
				return scannedItem;
			}

		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found");
	}

}
