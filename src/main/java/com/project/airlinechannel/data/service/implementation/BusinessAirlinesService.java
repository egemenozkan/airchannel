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
import com.project.airlinechannel.data.model.BusinessAirlines;
import com.project.airlinechannel.data.model.Flight;
import com.project.airlinechannel.data.service.IAirlinesService;

@Service
public class BusinessAirlinesService implements IAirlinesService {

	Logger logger = LoggerFactory.getLogger(BusinessAirlinesService.class);
	
	@Value("${api.endpoint.airlines.business}")
	private String API_ENDPOINT_URL;

	private static final String FLIGHT_ROUTE_SPLITTER = "->";

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<Flight> getFlights() {
		if (!getBusinessFlights().isEmpty()) {
			List<Flight> flights = new ArrayList<>();
			for (BusinessAirlines businessFlight : getBusinessFlights()) {
				Flight flight = new Flight();
				String flightRoute = businessFlight.getFlight();
				if (flightRoute != null && !flightRoute.isBlank() && flightRoute.contains(FLIGHT_ROUTE_SPLITTER)) {
					flight.setDeparture(flightRoute.split(FLIGHT_ROUTE_SPLITTER)[0].trim());
					flight.setArrival(flightRoute.split(FLIGHT_ROUTE_SPLITTER)[1].trim());
				}

				flight.setId(businessFlight.getUuid());
				flight.setDepartureDateTime(businessFlight.getDeparture());
				flight.setArrivalDateTime(businessFlight.getArrival());
				flight.setProvider(ProviderEnum.BUSINESS);
				flights.add(flight);
			}
			return flights;
		}
		return Collections.emptyList();
	}

	private List<BusinessAirlines> getBusinessFlights() {
		StringBuilder apiEndPointStrBuilder = new StringBuilder();
		apiEndPointStrBuilder.append(API_ENDPOINT_URL);

		try {
			BusinessAirlines[] response = restTemplate.getForObject(apiEndPointStrBuilder.toString(), BusinessAirlines[].class);
			if (response != null) {
				return Arrays.asList(response);
			}
			return Collections.emptyList();
		} catch (Exception e) {
			logger.error("::getBusinessFlights error: {}", e);
		}

		return Collections.emptyList();
	}

}
