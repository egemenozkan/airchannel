package com.project.airlinechannel.data.service.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.project.airlinechannel.data.enums.ProviderEnum;
import com.project.airlinechannel.data.model.CheapAirlines;
import com.project.airlinechannel.data.model.Flight;
import com.project.airlinechannel.data.service.IAirlinesService;

@Service
public class CheapAirlinesService implements IAirlinesService {
	
	Logger logger = LoggerFactory.getLogger(CheapAirlinesService.class);
	
	@Value("${api.endpoint.airlines.cheap}")
	private String API_ENDPOINT_URL;

	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<Flight> getFlights() {
		if (!getCheapFlights().isEmpty()) {
			List<Flight> flights = new ArrayList<>();
			for (CheapAirlines cheapFlight : getCheapFlights()) {
				Flight flight = new Flight();
				flight.setId(String.valueOf(cheapFlight.getId()));
				flight.setDeparture(cheapFlight.getDeparture());
				flight.setArrival(cheapFlight.getArrival());
				flight.setDepartureDateTime(cheapFlight.getDepartureTime());
				flight.setArrivalDateTime(cheapFlight.getArrivalTime());
				flight.setProvider(ProviderEnum.CHEAP);
				flights.add(flight);
			}
			return flights;
		}
		return Collections.emptyList();
	}

	private List<CheapAirlines> getCheapFlights() {
		StringBuilder apiEndPointStrBuilder = new StringBuilder();
		apiEndPointStrBuilder.append(API_ENDPOINT_URL);

		try {
			CheapAirlines[] response = restTemplate.getForObject(apiEndPointStrBuilder.toString(), CheapAirlines[].class);
			if (response != null) {
				return Arrays.asList(response);
			}
			return Collections.emptyList();
		} catch (Exception e) {
			logger.error("::getCheapFlights error: {}", e);
		}

		return Collections.emptyList();
	}

}
