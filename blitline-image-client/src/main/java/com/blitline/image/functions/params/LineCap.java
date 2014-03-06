package com.blitline.image.functions.params;

public enum LineCap {
	BUTT("butt"),
	ROUND("round"),
	SQUARE("square");
	
	private final String blitlineValue;
	
	private LineCap(String blitlineValue) {
		this.blitlineValue = blitlineValue;
	}
	
	@Override
	public String toString() {
		return blitlineValue;
	}

}
