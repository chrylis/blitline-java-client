package com.blitline.image.functions.params;

public enum FontWeight {
	NORMAL("normal"),
	BOLD("bold");
	
	private final String blitlineValue;
	
	private FontWeight(String blitlineValue) {
		this.blitlineValue = blitlineValue;
	}
	
	@Override
	public String toString() {
		return blitlineValue;
	}

}
