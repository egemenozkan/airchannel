package com.project.airlinechannel.comparator;

import java.util.Comparator;

import com.project.airlinechannel.data.model.Flight;

public class FlightDepartureDateAscComparator implements Comparator<Flight> {


	@Override
	public int compare(Flight o1, Flight o2) {
		return o1.getDepartureDateTime().compareTo(o2.getDepartureDateTime());
	}    
}
