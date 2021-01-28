package com.bteam.bstore.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.bteam.bstore.service.ScanService;
import com.bteam.bstore.utils.AppConstants;


@RestController
public class ScanController {
	
	@Autowired
	private ScanService service;
	
	@Value("#{'${cloud.indicator}'}")
	private int cloudIndicator;	
	
	//To get the highest confidence item for the image from cloud
	@RequestMapping(value="/items",method=RequestMethod.POST)
	public String scannedItems(@RequestBody Object img) throws Exception {
		
		String item = AppConstants.EMPTY;
		
		//Get response based on type of cloud service indicator
		switch(cloudIndicator) {
		case 1:
			item = service.getAWSResponse(img);
			break;
		}
		
		if(item.isEmpty()) {
			throw new Exception(AppConstants.NO_RESULTS);
		}
		return item;
		
	}

}
