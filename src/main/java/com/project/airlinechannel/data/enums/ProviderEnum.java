package com.project.airlinechannel.data.enums;


public enum ProviderEnum {
	ALL(0), CHEAP(1), BUSINESS(2);
	
	private final int id;

	private ProviderEnum(int id) {
		this.id = id;
	}

	public static ProviderEnum getById(int id) {
		for (ProviderEnum type : ProviderEnum.values()) {
			if (type.id == id) {
				return type;
			}
		}
		return ProviderEnum.ALL;
	}

}
