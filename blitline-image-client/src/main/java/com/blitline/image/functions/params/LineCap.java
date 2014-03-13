package com.blitline.image.functions.params;

import com.fasterxml.jackson.annotation.JsonValue;

public enum LineCap {
	BUTT("butt"),
	ROUND("round"),
	SQUARE("square");
	
	private final String blitlineValue;
	
	private LineCap(String blitlineValue) {
		this.blitlineValue = blitlineValue;
	}
	
	@JsonValue
	@Override
	public String toString() {
		return blitlineValue;
	}

}
