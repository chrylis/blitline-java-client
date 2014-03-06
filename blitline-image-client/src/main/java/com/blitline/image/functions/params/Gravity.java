package com.blitline.image.functions.params;

public enum Gravity {
	EAST("EastGravity"),
	WEST("WestGravity"),
	NORTH("NorthGravity"),
	SOUTH("SouthGravity"),
	NORTHWEST("NorthWestGravity"),
	NORTHEAST("NorthEastGravity"),
	SOUTHWEST("SouthWestGravity"),
	SOUTHEAST("SouthEastGravity"),
	CENTER("CenterGravity");
	
	private final String blitlineValue;
	
	private Gravity(String blitlineValue) {
		this.blitlineValue = blitlineValue;
	}
	
	@Override
	public String toString() {
		return blitlineValue;
	}
}
