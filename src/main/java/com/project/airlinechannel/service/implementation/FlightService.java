package com.project.airlinechannel.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.airlinechannel.comparator.FlightDepartureDateAscComparator;
import com.project.airlinechannel.data.enums.ProviderEnum;
import com.project.airlinechannel.data.model.Flight;
import com.project.airlinechannel.data.model.FlightSearchRequest;
import com.project.airlinechannel.data.model.FlightSearchResponse;
import com.project.airlinechannel.data.service.IAirlinesService;
import com.project.airlinechannel.service.IFlightService;

@Service
public class FlightService implements IFlightService {

	Logger logger = LoggerFactory.getLogger(FlightService.class);

	private static final FlightDepartureDateAscComparator FLIGT_DEPARTURE_ASC_COMPARATOR = new FlightDepartureDateAscComparator();

	@Autowired
	IAirlinesService cheapAirlinesService;
	@Autowired
	IAirlinesService businessAirlinesService;

	@Override
	public FlightSearchResponse searchFlights(FlightSearchRequest flightSearchRequest) {
		FlightSearchResponse flightSearchResponse = new FlightSearchResponse();

		try {
			flightSearchResponse.setSuccess(true);
			List<Flight> flights = new ArrayList<>();
			flightSearchResponse.setFlights(flights);

			/* Cheap Airlines */
			if (flightSearchRequest.getProvider() == null || flightSearchRequest.getProvider() == ProviderEnum.CHEAP) {
				flights.addAll(cheapAirlinesService.getFlights());
			}
			/* Business Airlines */
			if (flightSearchRequest.getProvider() == null || flightSearchRequest.getProvider() == ProviderEnum.BUSINESS) {
				flights.addAll(businessAirlinesService.getFlights());
			}
			/* Filter */
			if (!flights.isEmpty()) {
				flights.removeIf(flight -> flightFilter(flightSearchRequest, flight));
			}

			/* Pagination */
			if (!flights.isEmpty()) {
				flights = flights.subList(flightSearchRequest.getPage() * flightSearchRequest.getLimit(),
						(flightSearchRequest.getPage() + 1) + flightSearchRequest.getLimit());
			}
			/* Sorting */
			/* TODO: Improve - ForNow only by departureDate **/
			flights.sort(FLIGT_DEPARTURE_ASC_COMPARATOR);

		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("::searchFlights error: {}", e);
			}
			flightSearchResponse.setSuccess(false);
			flightSearchResponse.setErrorMessage(e.getMessage());
		}

		return flightSearchResponse;
	}

	private boolean flightFilter(FlightSearchRequest flightSearchRequest, Flight flight) {
		if (flightSearchRequest != null) {
			/* TODO: Improve **/
			return (flightSearchRequest.getDeparture() != null
					&& !flightSearchRequest.getDeparture().equalsIgnoreCase(flight.getDeparture()))
					|| (flightSearchRequest.getArrival() != null && !flightSearchRequest.getArrival().equals(flight.getArrival()));
		}
		return false;

	}

}
