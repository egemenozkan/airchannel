package com.project.airlinechannel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.airlinechannel.data.model.FlightSearchRequest;
import com.project.airlinechannel.data.model.FlightSearchResponse;
import com.project.airlinechannel.service.IFlightService;

@RestController
public class ChannelRestController {

	@Autowired
	IFlightService flightService;

	@GetMapping("/flights/return")
	public FlightSearchResponse flightSearch(@RequestParam(required = false) String departure,
			@RequestParam(required = false) String arrival, @RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int limit) {
		FlightSearchRequest flightSearchRequest = new FlightSearchRequest();
		flightSearchRequest.setDeparture(departure);
		flightSearchRequest.setArrival(arrival);
		flightSearchRequest.setPage(page);
		flightSearchRequest.setLimit(limit);

		return flightService.searchFlights(flightSearchRequest);
	}
}
