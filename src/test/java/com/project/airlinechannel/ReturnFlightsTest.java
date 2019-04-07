package com.project.airlinechannel;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.airlinechannel.data.model.FlightSearchResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ReturnFlightsTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void test()  throws Exception {
		Map<String, Object> urlVariables = new HashMap<>();
		urlVariables.put("departure", "Salguero");
		urlVariables.put("departure", "Mumbai");
		urlVariables.put("page", 1);
		urlVariables.put("limit", 5);
		ResponseEntity<FlightSearchResponse> response = restTemplate.getForEntity("/flights/return", FlightSearchResponse.class, urlVariables);
//		Assertions.
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().isSuccess()).isEqualTo(true);
	
		
	}

}
