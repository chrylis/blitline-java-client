package com.blitline.image.functions.params;

public enum FontStyle {

	NORMAL("normal"),
	ITALIC("italic"),
	OBLIQUE("oblique");
	
	private final String blitlineValue;
	
	private FontStyle(String blitlineValue) {
		this.blitlineValue = blitlineValue;
	}
	
	@Override
	public String toString() {
		return blitlineValue;
	}
}
