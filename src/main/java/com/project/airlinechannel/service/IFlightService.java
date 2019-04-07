package com.project.airlinechannel.service;

import com.project.airlinechannel.data.model.FlightSearchRequest;
import com.project.airlinechannel.data.model.FlightSearchResponse;

public interface IFlightService {
	FlightSearchResponse searchFlights(FlightSearchRequest flightSearchRequest);
}
