package com.blitline.image.functions;

public class PhotographEffect extends AbstractFunction {
	@Override
	public String getName() {
		return "photograph";
	}
	
	public PhotographEffect rotateClockwise(double degrees) {
		params.put("angle", degrees);
		return this;
	}
}
