package com.blitline.image.functions.params;

import com.fasterxml.jackson.annotation.JsonValue;

public enum FontStyle {

	NORMAL("normal"),
	ITALIC("italic"),
	OBLIQUE("oblique");
	
	private final String blitlineValue;
	
	private FontStyle(String blitlineValue) {
		this.blitlineValue = blitlineValue;
	}
	
	@JsonValue
	@Override
	public String toString() {
		return blitlineValue;
	}
}
