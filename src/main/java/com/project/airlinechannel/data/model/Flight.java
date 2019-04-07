package com.project.airlinechannel.data.model;

import java.time.LocalDateTime;

import com.project.airlinechannel.data.enums.ProviderEnum;

public class Flight {
	private String id;
	private String departure;
	private String arrival;
	private LocalDateTime departureDateTime;
	private LocalDateTime arrivalDateTime;
	private ProviderEnum provider;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public LocalDateTime getDepartureDateTime() {
		return departureDateTime;
	}

	public void setDepartureDateTime(LocalDateTime departureDateTime) {
		this.departureDateTime = departureDateTime;
	}

	public LocalDateTime getArrivalDateTime() {
		return arrivalDateTime;
	}

	public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
	}

	public ProviderEnum getProvider() {
		return provider;
	}

	public void setProvider(ProviderEnum provider) {
		this.provider = provider;
	}

}
