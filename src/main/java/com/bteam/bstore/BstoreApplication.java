package com.bteam.bstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author b-team
 *
 */
@SpringBootApplication
public class BstoreApplication {

	/**
	 * @return rest template object
	 */
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(BstoreApplication.class, args);
	}

}
