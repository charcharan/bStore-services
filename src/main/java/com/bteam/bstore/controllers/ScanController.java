package com.bteam.bstore.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bteam.bstore.service.ScanService;
import com.bteam.bstore.utils.AppConstants;


@RestController
public class ScanController {
	
	@Autowired
	private ScanService service;
	
	@Value("#{'${cloud.indicator}'}")
	private int cloudInd;	
	
	/**
	 * @param img
	 * @param cloudIndicator
	 * @return the highest confidence item for the image from cloud
	 * @throws Exception
	 * 
	 */
	@RequestMapping(value="/items",method=RequestMethod.POST)
	public String scannedItems(@RequestParam(required = false, defaultValue = "0") int cloudIndicator, @RequestBody Object img) throws Exception {
		
		String item = AppConstants.EMPTY;
		
		//If cloud indicator is not mentioned then setting default indicator as 1(AWS)
		int indicator = cloudIndicator == AppConstants.ZERO ? cloudInd : cloudIndicator;
		
		//Get response based on type of cloud service indicator
		switch(indicator) {
			case 1:
			item = service.getAWSResponse(img);
			break;
			default:
			break;
		}
		
		if(item.isEmpty()) {
			throw new Exception(AppConstants.NO_RESULTS);
		}
		return item;
		
	}

}
