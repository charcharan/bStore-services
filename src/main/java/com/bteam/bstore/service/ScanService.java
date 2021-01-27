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

@Service
public class ScanService {

	@Autowired
    private RestTemplate restTemplate;
	
	@Value("#{'${aws.api.url}'}")
	private String awsRequestURI;
	
	//Sorting the list of items based on confidence
	public String getHighestConfidenceItem(ArrayList<ScannedItem> scannedItems){
		Collections.sort(scannedItems);
		return scannedItems.get(0).getName();
	}
	
	public String getAWSResponse(){
		
		  HttpHeaders headers = new HttpHeaders();
		  headers.setContentType(MediaType.APPLICATION_JSON);
		 
		  //Passing encoded(base 64) string of image to AWS
		  HttpEntity<?> entity = new HttpEntity<Object>("{\r\n  \"Image\":\"data:image/jpeg;base64,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\",\r\n  \"name\":\"firstImage\"\r\n}",headers); 	
		  
		  ResponseEntity<Map> response =  restTemplate.postForEntity(awsRequestURI, entity, Map.class); 
		  List<Object> items =   (List<Object>) response.getBody().get("Labels");
		  
		  //Parsing the list of matching items(confidence,name)
		  ArrayList<ScannedItem> scannedItems = new ArrayList<ScannedItem>();
		  for(Object item:items) {
			  String name = item.toString().split(",")[0].split("=")[1];
			  Double confidence = Double.parseDouble(item.toString().split(",")[1].split("=")[1]);
			  ScannedItem it = new ScannedItem(confidence,name);
			  scannedItems.add(it);
		  }
		  return getHighestConfidenceItem(scannedItems);
	}
}
