package com.blitline.image.functions.params;

import com.fasterxml.jackson.annotation.JsonValue;

public enum FontWeight {
	NORMAL("normal"),
	BOLD("bold");
	
	private final String blitlineValue;
	
	private FontWeight(String blitlineValue) {
		this.blitlineValue = blitlineValue;
	}
	
	@JsonValue
	@Override
	public String toString() {
		return blitlineValue;
	}

}
