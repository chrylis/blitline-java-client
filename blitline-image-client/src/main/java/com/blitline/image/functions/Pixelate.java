package com.blitline.image.functions;

import org.apache.commons.lang3.Validate;

public class Pixelate extends AbstractFunction {
	@Override
	public String getName() {
		return "pixelate";
	}
	
	public Pixelate xOffset(int xOffset) {
		params.put("x", xOffset);
		return this;
	}
	
	public Pixelate yOffset(int yOffset) {
		params.put("y", yOffset);
		return this;		
	}
	
	public Pixelate width(int width) {
		params.put("width", width);
		return this;
	}
	
	public Pixelate height(int height) {
		params.put("height", height);
		return this;
	}
	
	public Pixelate amount(int amount) {
		Validate.inclusiveBetween(0, 100, amount);
		params.put("amount", amount);
		return this;
	}
}
