package br.com.example.domain.model.enumeration;

import java.util.List;

import lombok.Getter;

public enum PhoneType {

	HOME(0, "Home Phone"),
	MOBILE(1, "Mobile Phone"),
	COMMERCIAL(2, "Commercial Phone");
	
	@Getter
	private final Integer codValue; 
	@Getter
	private final String description;
	
	PhoneType(Integer codValue, String description){
		this.codValue = codValue;
		this.description = description;
	}
	
	public static PhoneType valueOfCodValue(final Integer codValue) {
		return List.of(values()).stream().filter(pt -> pt.codValue == codValue).findFirst().orElseThrow();
	}
}
