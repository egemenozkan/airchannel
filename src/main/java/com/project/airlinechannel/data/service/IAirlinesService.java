package com.project.airlinechannel.data.service;

import java.util.List;

import com.project.airlinechannel.data.enums.ProviderEnum;
import com.project.airlinechannel.data.model.Flight;

public interface IAirlinesService {

	List<Flight> getFlights();

}
