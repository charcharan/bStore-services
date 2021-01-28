package com.bteam.bstore.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bteam.bstore.beans.ScannedItem;
import com.bteam.bstore.utils.AppConstants;

@Service
public class ScanService {

	@Autowired
    private RestTemplate restTemplate;
	
	@Value("#{'${aws.api.url}'}")
	private String awsRequestURI;
	
	/**
	 * @param scannedItems
	 * @return the highest confidence item 
	 * sorting the list of items based on confidence
	 */
	public String getHighestConfidenceItem(List<ScannedItem> scannedItems){
		Collections.sort(scannedItems);
		return scannedItems.get(0).getName();
	}
	
	/**
	 * @param img
	 * @return the highest confidence item 
	 * call the AWS URL to get the list of matching items
	 */
	public String getAWSResponse(Object img){
		
		  HttpHeaders headers = new HttpHeaders();
		  headers.setContentType(MediaType.APPLICATION_JSON);
		 
		  HttpEntity<?> entity = new HttpEntity<Object>(img,headers);
		  
		  //Passing encoded(base 64) string of image to AWS
		  ResponseEntity<Map> response =  restTemplate.postForEntity(awsRequestURI, entity, Map.class); 
		  List<Object> items =   (List<Object>) response.getBody().get(AppConstants.LABELS);
		  
		  //Parsing the list of matching items(confidence,name)
		  List<ScannedItem> scannedItems = new ArrayList<ScannedItem>();
		  for(Object item:items) {
			  String name = item.toString().split(AppConstants.COMMA)[0].split(AppConstants.EQUALS)[1];
			  double confidence = Double.parseDouble(item.toString().split(AppConstants.COMMA)[1].split(AppConstants.EQUALS)[1]);
			  ScannedItem it = new ScannedItem(confidence,name);
			  scannedItems.add(it);
		  }
		  return getHighestConfidenceItem(scannedItems);
	}
}
