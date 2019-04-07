package com.project.airlinechannel.data.model;

import java.util.List;

public class FlightSearchResponse {
	private boolean success;
	private String errorCode;
	private String errorMessage;
	private List<Flight> flights;
	private int size;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public int getSize() {
		return this.flights.size();
	}

}
