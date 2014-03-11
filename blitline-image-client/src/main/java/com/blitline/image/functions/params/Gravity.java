package com.blitline.image.functions.params;

import com.fasterxml.jackson.annotation.JsonValue;

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
	
	@JsonValue
	@Override
	public String toString() {
		return blitlineValue;
	}
}
